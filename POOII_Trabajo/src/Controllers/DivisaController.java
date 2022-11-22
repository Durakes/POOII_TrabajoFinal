package Controllers;
import Model.Usuario;
import View.VistaDashboard;
import View.VistaPerfil;
import Model.Transaccion;
import Model.Billetera;
import Model.Solicitud;
import Model.TipoDivisa;
import View.VistaBilletera;
import View.VistaEditarPerfil;
import View.VistaNotificaciones;
import View.VistaSolicitud;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.*;
import java.awt.Color;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.time.*;
//import Controllers.AccesoArchivo;

public class DivisaController implements ActionListener
{
    private ArrayList<Transaccion> registros = new ArrayList<>();
    private ArrayList<Solicitud> solicitudes = new ArrayList<>();
    private ArrayList<Billetera> billeteras = new ArrayList<>();
    String bancoSelect, modoCompra, monedaSelect;
    AccesoArchivo archivo;
    Usuario usuarioActivo;
    VistaDashboard vDashboard;
    //VistaSolicitud vSolicitud;
    TipoDivisa tcBcp, tcBbva, tcInter;
    VistaPerfil vPerfil;
    Double tasaDeCambioBase, tasaCambioFinal;
    VistaBilletera vistaBilletera;
    VistaEditarPerfil vistaEditarPerfil;
    ArrayList<Double> ratesGraph;

    public DivisaController(Usuario usuario)
    {
        this.usuarioActivo = usuario;
        /* Iniciar objetos */
        archivo = new AccesoArchivo();
        tcBcp = new TipoDivisa();
        tcBbva = new TipoDivisa();
        tcInter = new TipoDivisa();
        vDashboard = new VistaDashboard();
        archivo.cargarTipoCambios();
        cargarSolicitudes();
        tasaDeCambioBase = archivo.cambiosFlat.getTasasDeCambio()[0];
        bancoSelect = vDashboard.botonBancos[0].getText();
        modoCompra = "Compra";
        vDashboard.labelModoActivo.setText("");
        vDashboard.labelModoActivo.setText("MODO: " + modoCompra);
        System.out.println("prueba 0");
        try{vPerfil = new VistaPerfil();}catch(Exception exception){
            System.out.println("Error: " + exception.toString());
        }
        vPerfil.frame.setVisible(false);
        System.out.println("prueba 0.5");
        try{vistaBilletera = new VistaBilletera();}catch(Exception exception){
            System.out.println("Error1: " + exception.toString());
        }
        vistaBilletera.frame.setVisible(false);
        
        try{vistaEditarPerfil = new VistaEditarPerfil();}catch(Exception exception){
            System.out.println("Error2: " + exception.toString());
        }
        vistaEditarPerfil.frame.setVisible(false);
        System.out.println("prueba 1");
        tcBcp = archivo.tipoDeCambioBancos("BCP",vDashboard.botonTipoDivisas[0].getText(), 0);
        tcBbva = archivo.tipoDeCambioBancos("BBVA",vDashboard.botonTipoDivisas[0].getText(), 0);
        tcInter = archivo.tipoDeCambioBancos("INTERBANK",vDashboard.botonTipoDivisas[0].getText(), 0);
        vDashboard.labelBancos[0].setText(String.valueOf(tcBcp.getCambioCompra()));
        vDashboard.labelBancos[1].setText(String.valueOf(tcBbva.getCambioCompra()));
        vDashboard.labelBancos[2].setText(String.valueOf(tcInter.getCambioCompra()));
        System.out.println("prueba 2");
        vDashboard.botonPerfil.setText(usuarioActivo.getUser());
        vDashboard.botonRegistarTransaccion.addActionListener(this);

        for(int i = 0; i < 5; i++)
        {
            final int final_i = i;
            vDashboard.botonTipoDivisas[i].addActionListener(e -> cambiarMoneda(final_i));
        }

        for(int i = 0; i < 3; i++)
        {
            final int final_i = i;
            vDashboard.botonBancos[i].addActionListener(e -> seleccionBanco(final_i));
        }

        vDashboard.labelBancoSeleccionado.setText(vDashboard.labelBancoSeleccionado.getText() + vDashboard.botonBancos[0].getText());
        tasaCambioFinal = Double.parseDouble(vDashboard.labelBancos[0].getText());

        vDashboard.textMonedaNacional.setEnabled(false);
        vDashboard.textMonedaExtranjera.addActionListener(this);
        vDashboard.botonRegistarTransaccion.setEnabled(false);
        vDashboard.botonCompraVenta.addActionListener(this);
        vDashboard.botonUsuario.addActionListener(this);
        vDashboard.botonPerfil.addActionListener(this);
        
        vDashboard.botonNotif.addActionListener(this);
    }

    public void procesarSolicitud(int id)
    {
        for(Solicitud solicitud: solicitudes)
        {
            if(solicitud.getIdSolicitud() == id)
            {
                boolean trasnValida = true;
                Double monto = solicitud.getMonto();
                Double tipoCambio = solicitud.getTipoCambio();
                cargarBilletera();

                Billetera billeteraAceptante = null;
                Billetera billeteraSolicitante = null;
                for(Billetera billetera: billeteras)
                {
                    if(billetera.getCodUsuario() == usuarioActivo.getCodUsuario())
                    {
                        billeteraAceptante = billetera;
                        break;
                    }
                }
                for(Billetera billetera: billeteras)
                {
                    if(billetera.getCodUsuario() == solicitud.getCodUsuario())
                    {
                        billeteraSolicitante = billetera;
                        break;
                    }
                }

                double temporalCantidadesSol[] = billeteraSolicitante.getCantidades();
                double temporalCantidadesAce[] = billeteraAceptante.getCantidades();

                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);

                Double calculo = Double.parseDouble(df.format(solicitud.getMonto() * solicitud.getTipoCambio()));

                if(solicitud.getTipoOperacion().equals("Venta"))
                {
                    for(int i = 0; i < 5; i++)
                    {
                        if(solicitud.getMoneda().equals(vDashboard.divisas[i]))
                        {
                            temporalCantidadesSol[i+1] -= solicitud.getMonto();
                            temporalCantidadesAce[i+1] += solicitud.getMonto();
                            break;
                        }
                    }
                    temporalCantidadesSol[0] += calculo;
                    temporalCantidadesAce[0] -= calculo;
                }else
                {
                    for(int i = 0; i < 5; i++)
                    {
                        if(solicitud.getMoneda().equals(vDashboard.divisas[i]))
                        {
                            temporalCantidadesSol[i+1] += solicitud.getMonto();
                            temporalCantidadesAce[i+1] -= solicitud.getMonto();
                            break;
                        }
                    }
                    temporalCantidadesSol[0] -= calculo;
                    temporalCantidadesAce[0] += calculo;
                }

                for(int i = 0; i < 6; i++)
                {
                    if(temporalCantidadesAce[i] < 0)
                    {
                        trasnValida = false;
                        break;
                    }
                }
                
                if(trasnValida)
                {
                    String modoAce = "";

                    if(solicitud.getTipoOperacion().equals("Venta"))
                    {
                        modoAce = "Compra";
                    }else
                    {
                        modoAce = "Venta";
                    }

                    billeteraSolicitante.setCantidades(temporalCantidadesSol);
                    billeteraAceptante.setCantidades(temporalCantidadesAce);
                    try
                    {
                        FileWriter fw = new FileWriter("src/db/registros.csv", true);
                        PrintWriter pw = new PrintWriter(fw);

                        //Agregar un registro
                        String registroSol = String.valueOf(solicitud.getCodUsuario()) + ";" + String.valueOf(LocalDate.now())+ ";" + solicitud.getTipoOperacion() + ";" + solicitud.getMoneda() + ";" + solicitud.getMonto() + ";" + usuarioActivo.getUser() + "\n";

                        String registroAce = String.valueOf(usuarioActivo.getCodUsuario()) + ";" + String.valueOf(LocalDate.now())+ ";" + modoAce + ";" + solicitud.getMoneda() + ";" + solicitud.getMonto() + ";" + solicitud.getNomUsuario() + "\n";
                        pw.append(registroSol);
                        pw.append(registroAce);

                        pw.close();

                    } catch (Exception ex)
                    {
                    System.out.println("error:"+ex.toString());
                    }

                    for(Billetera billetera: billeteras)
                    {
                        if(billetera.getCodUsuario() == billeteraSolicitante.getCodUsuario())
                        {
                            billetera.setCantidades(billeteraSolicitante.getCantidades());
                            break;
                        }
                    }

                    for(Billetera billetera: billeteras)
                    {
                        if(billetera.getCodUsuario() == billeteraAceptante.getCodUsuario())
                        {
                            billetera.setCantidades(billeteraAceptante.getCantidades());
                            break;
                        }
                    }

                    try
                    {
                        FileWriter fw = new FileWriter("src/db/billetera.csv");
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        for(Billetera billetera:billeteras)
                        {
                            String registroBilletera = String.valueOf(billetera.getCodUsuario());                            
                            for (int i = 0; i< 6; i++)
                            {
                                registroBilletera += ";" + df.format(billetera.getCantidades()[i]);
                            }
                            pw.println(registroBilletera);
                        }
                        pw.close();
                    }catch (Exception exception)
                    {
                        System.out.println("error:"+exception.toString());
                    }

                    solicitud.setEstado(false);
                    for(Solicitud sol: solicitudes)
                    {
                        if(sol.getIdSolicitud() == solicitud.getIdSolicitud())
                        {
                            sol.setEstado(false);
                        }
                    }

                    try
                    {
                        FileWriter fw = new FileWriter("src/db/Registros/solicitudes.csv");
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        for(Solicitud sol:solicitudes)
                        {
                            String solString = sol.getIdSolicitud() + "," + sol.getNomUsuario() + "," + sol.getCodUsuario() + "," + 
                            sol.getTipoOperacion() + "," + sol.getMoneda() + "," + sol.getMonto() + "," + sol.getTipoCambio() + "," + sol.getEstado();
                            pw.println(solString);
                        }
                        pw.close();
                    }catch (Exception exception)
                    {
                        System.out.println("error:"+exception.toString());
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No tiene los fondos suficientes","Error Registro", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        try
        {    
            FileWriter fw = new FileWriter("src/db/Registros/solicitudes.csv");
            PrintWriter pw = new PrintWriter(fw);
            
            //Volver a escribir el archivo
            for(Solicitud x:solicitudes)
            {
                String registro =x.getIdSolicitud() + "," +x.getNomUsuario()+","+ x.getCodUsuario() + ","+ x.getTipoOperacion()+","+x.getMoneda()+","+x.getMonto()+","+x.getTipoCambio()+","+x.getEstado();
                pw.println(registro);
            }

            pw.close();
        }catch (Exception e)
        {
            System.out.println("error Solicitudes:"+e.toString());
        }
    }

    public void registrarSolicitud(String monto, String tasaCambio)
    {
        try 
            {
                FileWriter fw = new FileWriter("src/db/Registros/solicitudes.csv", true);
                PrintWriter pw = new PrintWriter(fw);
                
                //Agregar un registro
                String registro = String.valueOf(solicitudes.size()+1) +","+ usuarioActivo.getUser() + "," + usuarioActivo.getCodUsuario() + "," + modoCompra + "," + vDashboard.labelMonedaExtranjera.getText() + "," + monto + "," + tasaCambio + ",true" +"\n";
                    
                pw.append(registro);
    
                pw.close();
            } catch (Exception e) {
                System.out.println("error:"+e.toString());
            }
    }

    public void cargarSolicitudes()
    {
        try 
        {
            
            String nombre, modo,moneda;
            Double cantidad,tipoCambio;
            Boolean estado;

            BufferedReader br = new BufferedReader(new FileReader("src/db/Registros/solicitudes.csv"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal;
                temporal = line.split(",");
                if(temporal[7].equals("true"))
                {
                    nombre = temporal[1];
                    modo = temporal[3];
                    moneda = temporal[4];
                    cantidad = Double.parseDouble(temporal[5]);
                    tipoCambio = Double.parseDouble(temporal[6]);
                    estado = Boolean.parseBoolean(temporal[7]);
                    Solicitud objS = new Solicitud(Integer.parseInt(temporal[0]),nombre,Integer.parseInt(temporal[2]) ,modo, moneda, cantidad, tipoCambio, estado);
                    solicitudes.add(objS);
                }
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarSolicitudes: "+e.toString());
        }
    }

    public void seleccionBanco(int banco)
    {
        vDashboard.labelBancoSeleccionado.setText("");
        vDashboard.labelBancoSeleccionado.setText("Banco Seleccionado: " + vDashboard.botonBancos[banco].getText());
        tasaCambioFinal = Double.parseDouble(vDashboard.labelBancos[banco].getText());

        vDashboard.textMonedaNacional.setText("");
        vDashboard.textMonedaExtranjera.setText("");
        vDashboard.botonRegistarTransaccion.setEnabled(false);
        bancoSelect = vDashboard.botonBancos[banco].getText();
    }

    public void cambiarMoneda(int tasa)
    {

        tasaDeCambioBase = archivo.cambiosFlat.getTasasDeCambio()[tasa];
        vDashboard.labelMonedaExtranjera.setText(vDashboard.botonTipoDivisas[tasa].getText());
        tcBcp = archivo.tipoDeCambioBancos("BCP",vDashboard.botonTipoDivisas[tasa].getText(), tasa);
        tcBbva = archivo.tipoDeCambioBancos("BBVA",vDashboard.botonTipoDivisas[tasa].getText(), tasa);
        tcInter = archivo.tipoDeCambioBancos("INTERBANK",vDashboard.botonTipoDivisas[tasa].getText(), tasa);

        if(vDashboard.botonCompraVenta.getText().equals("Venta"))
        {
            vDashboard.labelBancos[0].setText(String.valueOf(tcBcp.getCambioCompra()));
            vDashboard.labelBancos[1].setText(String.valueOf(tcBbva.getCambioCompra()));
            vDashboard.labelBancos[2].setText(String.valueOf(tcInter.getCambioCompra()));
        }else
        {
            vDashboard.labelBancos[0].setText(String.valueOf(tcBcp.getCambioVenta()));
            vDashboard.labelBancos[1].setText(String.valueOf(tcBbva.getCambioVenta()));
            vDashboard.labelBancos[2].setText(String.valueOf(tcInter.getCambioVenta()));
        }
        tasaCambioFinal = Double.parseDouble(vDashboard.labelBancos[0].getText());
        
        seleccionBanco(0);
    }

    public void cargarBilletera()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/db/billetera.csv")); 
            String line;
            Billetera billetera;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal;
                temporal = line.split(";");

                billetera = new Billetera(Integer.parseInt(temporal[0]));
                double tempDouble[] = new double[6];
                for(int i = 0; i < 6; i++)
                {
                    tempDouble[i] = Double.parseDouble(temporal[i+1]);
                }
                billetera.setCantidades(tempDouble);
                billeteras.add(billetera);
            }
            br.close();
        }catch(Exception exception)
        {

        }
    }

    public void actionPerformed(ActionEvent e) 
    {

        if(e.getSource() == vDashboard.botonRegistarTransaccion) /*Agregar id usuario*/
        {
            billeteras.clear();
            String modo;
            Boolean trasnValida = true;
            if(vDashboard.botonCompraVenta.getText().equals("Venta"))
            {
                modo = "Compra";
            }else
            {
                modo = "Venta";
            }
            cargarBilletera();
            Billetera billeteraChange = null;

            for(Billetera billetera: billeteras)
            {
                if(billetera.getCodUsuario() == usuarioActivo.getCodUsuario())
                {
                    billeteraChange = billetera;
                    break;
                }
            }
            double temporalCantidades[] = billeteraChange.getCantidades();
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            if(vDashboard.botonCompraVenta.getText().equals("Venta"))
            {
                for(int i = 0; i < 5; i++)
                {
                    if(vDashboard.labelMonedaExtranjera.getText() == vDashboard.divisas[i])
                    {
                        temporalCantidades[i+1] += Double.parseDouble(vDashboard.textMonedaExtranjera.getText());
                        break;
                    }
                }
                temporalCantidades[0] -= Double.parseDouble(vDashboard.textMonedaNacional.getText());
            }else
            {
                for(int i = 0; i < 5; i++)
                {
                    if(vDashboard.labelMonedaExtranjera.getText() == vDashboard.divisas[i])
                    {
                        temporalCantidades[i+1] -= Double.parseDouble(vDashboard.textMonedaExtranjera.getText());
                        break;
                    }
                }
                temporalCantidades[0] += Double.parseDouble(vDashboard.textMonedaNacional.getText());
            }
            
            for(int i = 0; i < 6; i++)
            {
                if(temporalCantidades[i] < 0)
                {
                    trasnValida = false;
                    break;
                }
            }

            if(trasnValida)
            {
                billeteraChange.setCantidades(temporalCantidades);
                try
                {
                    FileWriter fw = new FileWriter("src/db/registros.csv", true);
                    PrintWriter pw = new PrintWriter(fw);

                    //Agregar un registro
                    String registro = String.valueOf(usuarioActivo.getCodUsuario()) + ";" + String.valueOf(LocalDate.now())+ ";" + modo + ";" + vDashboard.labelMonedaExtranjera.getText() + ";" + vDashboard.textMonedaExtranjera.getText() + ";" + bancoSelect + "\n";
                    pw.append(registro);
                    pw.close();

                } catch (Exception ex)
                {
                System.out.println("error:"+ex.toString());
                }

                for(Billetera billetera: billeteras)
                {
                    if(billetera.getCodUsuario() == billeteraChange.getCodUsuario())
                    {
                        billetera.setCantidades(billeteraChange.getCantidades());
                        break;
                    }
                }

                try
                {
                    FileWriter fw = new FileWriter("src/db/billetera.csv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    for(Billetera billetera:billeteras)
                    {
                        String registroBilletera = String.valueOf(billetera.getCodUsuario());                            
                        for (int i = 0; i< 6; i++)
                        {
                            registroBilletera += ";" + df.format(billetera.getCantidades()[i]);
                        }
                        pw.println(registroBilletera);
                    }
                    pw.close();
                }catch (Exception exception)
                {
                    System.out.println("error:"+exception.toString());
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No tiene los fondos suficientes","Error Registro", JOptionPane.WARNING_MESSAGE);

            

        }else if(e.getSource() == vDashboard.botonUsuario)
        {
            VistaSolicitud vSolicitud = new VistaSolicitud();

            vSolicitud.btnGuardar.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    registrarSolicitud(vSolicitud.tfMonto.getText(), vSolicitud.textTasaCambio.getText());
                    vSolicitud.frame.dispose();
                }
            });
            vSolicitud.btnCancelar.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    vSolicitud.frame.dispose();
                }
            });

        }else if(e.getSource() == vDashboard.botonCompraVenta)
        {
            if(vDashboard.botonCompraVenta.getText().equals("Venta"))
            {

                vDashboard.botonCompraVenta.setText("Compra");
                modoCompra = "Venta";
                vDashboard.labelModoActivo.setText("MODO: " + modoCompra);
                cambiarMoneda(0);
            }else
            {
                vDashboard.botonCompraVenta.setText("Venta");
                modoCompra = "Compra";
                vDashboard.labelModoActivo.setText("MODO: " + modoCompra);
                cambiarMoneda(0);
            }

        }else if(e.getSource() == vDashboard.textMonedaExtranjera)
        {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);

            vDashboard.textMonedaNacional.setText(df.format(Double.parseDouble(vDashboard.textMonedaExtranjera.getText()) * tasaCambioFinal));
            vDashboard.botonRegistarTransaccion.setEnabled(true);

        }else if(e.getSource() == vDashboard.botonNotif)
        {
            try {
                VistaNotificaciones vNotificaciones = new VistaNotificaciones();
                Object datos[][] = new Object[solicitudes.size()][5];
                int index = 0;
                int yPos = 130;
                if(solicitudes.size()!=0)
                {
                    try 
                    {
                        for(int i = 0; i < solicitudes.size(); i++)
                        {
                            final int final_i = i;
                            if(!solicitudes.get(i).getNomUsuario().equals(usuarioActivo.getUser()))
                            {
                                vNotificaciones.aceptarButton.add(new JButton("Aceptar"));
                                datos[index][0] = solicitudes.get(i).getNomUsuario();
                                datos[index][1] = solicitudes.get(i).getTipoOperacion();
                                datos[index][2] = solicitudes.get(i).getMoneda();
                                datos[index][3] = solicitudes.get(i).getMonto();
                                datos[index][4] = solicitudes.get(i).getTipoCambio();

                                vNotificaciones.aceptarButton.get(index).setBounds(575,yPos, 100,30);
                                vNotificaciones.aceptarButton.get(index).setBackground(new Color(252, 152, 53));
                                vNotificaciones.aceptarButton.get(index).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                
                                final int final_id = solicitudes.get(final_i).getIdSolicitud();
                                vNotificaciones.aceptarButton.get(index).addActionListener(new ActionListener()
                                {  
                                    public void actionPerformed(ActionEvent e)
                                    {  
                                        procesarSolicitud(final_id); 
                                        vNotificaciones.frame.dispose();
                                    }  
                                });
                                vNotificaciones.frame.add(vNotificaciones.aceptarButton.get(index));
                                index++;
                                yPos += 50;
                            }
                        }
                    } 
                    catch (Exception exception) 
                    {
                        System.out.println("error obtenerDat:"+exception.toString());
                    }
                }
                vNotificaciones.modeloTabla.setDataVector(datos, vNotificaciones.cabecera);
                vNotificaciones.modeloTabla.setRowCount(index);
            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            

        }else if(e.getSource() == vDashboard.botonPerfil)
        {
            try 
            {
                int codigo;
                String fechaRegistro, modoTransaccion, divisa, entidad;
                double monto;
                BufferedReader br = new BufferedReader(new FileReader("src/db/registros.csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(";");
                    if(Integer.parseInt(temporal[0]) == usuarioActivo.getCodUsuario())
                    {
                        codigo = Integer.parseInt(temporal[0]);
                        fechaRegistro = temporal[1];
                        modoTransaccion = temporal[2];
                        divisa = temporal[3];
                        monto = Double.parseDouble(temporal[4]);
                        entidad = temporal[5];

                        Transaccion transaccion= new Transaccion(codigo,fechaRegistro,modoTransaccion,divisa,monto,entidad);
                        registros.add(transaccion);
                    }
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDat:"+exception.toString());
            }

            Object datos[][] = new Object[registros.size()][5];
            if(registros.size()!=0)
            {
                try 
                {
                    for(int i = 0; i < registros.size(); i++)
                    {
                        datos[i][0] = registros.get(i).getFechaTransaccion();
                        datos[i][1] = registros.get(i).getModoTransaccion();
                        datos[i][2] = registros.get(i).getMoneda();
                        datos[i][3] = registros.get(i).getMonto();
                        datos[i][4] = registros.get(i).getEntidad();
                    }
                } 
                catch (Exception exception) 
                {
                    System.out.println("error obtenerDat:"+exception.toString());
                }
            }

            Object datosPerfil[][] = new Object[8][5];

            for(int i = 0; i < 8; i++)
            {
                datosPerfil[i][0] = registros.get(registros.size() - (i+1)).getFechaTransaccion();
                datosPerfil[i][1] = registros.get(registros.size() - (i+1)).getModoTransaccion();
                datosPerfil[i][2] = registros.get(registros.size() - (i+1)).getMoneda();
                datosPerfil[i][3] = registros.get(registros.size() - (i+1)).getMonto();
                datosPerfil[i][4] = registros.get(registros.size() - (i+1)).getEntidad();
            }

            try
            {
                vPerfil = new VistaPerfil();
                vPerfil.lblUsuario.setText(usuarioActivo.getUser());
                vDashboard.frame.setVisible(false);

                vPerfil.btnAtras.addActionListener(this);
                vPerfil.btnBilletera.addActionListener(this);
                vPerfil.modeloTabla.setDataVector(datosPerfil, vPerfil.cabecera);
                vPerfil.tblOpRec.setModel(vPerfil.modeloTabla);
                vPerfil.btnEditarP.addActionListener(this);

            }catch(Exception exception)
            {
            }
            
        }else if(e.getSource() == vPerfil.btnAtras)
        {
            vDashboard.frame.setVisible(true);
            vPerfil.frame.setVisible(false);
        }else if(e.getSource() == vPerfil.btnBilletera)
        {
            Billetera billetera = new Billetera(usuarioActivo.getCodUsuario());
            try 
            {
                BufferedReader br = new BufferedReader(new FileReader("src/db/billetera.csv")); 
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(";");
                    
                    if(Integer.parseInt(temporal[0]) == usuarioActivo.getCodUsuario())
                    {
                        billetera = new Billetera(Integer.parseInt(temporal[0]));
                        double tempDouble[] = new double[6];
                        for(int i = 0; i < 6; i++)
                        {
                            tempDouble[i] = Double.parseDouble(temporal[i+1]);
                        }
                        billetera.setCantidades(tempDouble);
                        break;
                    }
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarBilletera:"+exception.toString());
            }

            //System.out.println("prueba");
            Object[][] tablaBilletera = new Object[6][2];

            for(int i = 0; i < 6; i++)
            {
                tablaBilletera[i][0] = vistaBilletera.divisas[i];
                tablaBilletera[i][1] = billetera.getCantidades()[i];
            }

            vistaBilletera = new VistaBilletera();
            vistaBilletera.modeloTablaDiv.setDataVector(tablaBilletera, vistaBilletera.cabeceraDiv);
            vistaBilletera.tblDivisas.setModel(vistaBilletera.modeloTablaDiv);
            vistaBilletera.btnGuardar.addActionListener(this);
        }if(e.getSource() == vistaBilletera.btnGuardar)
        {
            double montoAgregar = Double.parseDouble(vistaBilletera.tfFondo.getText());

            billeteras.clear();

            cargarBilletera();
            Billetera billeteraChange = null;

            for(Billetera billetera: billeteras)
            {
                if(billetera.getCodUsuario() == usuarioActivo.getCodUsuario())
                {
                    billeteraChange = billetera;
                    break;
                }
            }
            double temporalCantidades[] = billeteraChange.getCantidades();
            for(int i = 0; i < 6; i++)
            {
                if(vistaBilletera.cbDivisas.getSelectedItem().toString() == vDashboard.divisas[i])
                {
                    temporalCantidades[i] += montoAgregar;
                }
                
            }
            billeteraChange.setCantidades(temporalCantidades);

            for(Billetera billetera: billeteras)
            {
                if(billetera.getCodUsuario() == billeteraChange.getCodUsuario())
                {
                    billetera.setCantidades(billeteraChange.getCantidades());
                    break;
                }
            }

            try
            {
                FileWriter fw = new FileWriter("src/db/billetera.csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                for(Billetera billetera:billeteras)
                {
                    String registroBilletera = String.valueOf(billetera.getCodUsuario());                            
                    for (int i = 0; i< 6; i++)
                    {
                        registroBilletera += ";" + String.valueOf(billetera.getCantidades()[i]);
                    }
                    pw.println(registroBilletera);
                }
                pw.close();
            }catch (Exception exception)
            {
                System.out.println("error:"+exception.toString());
            }
            
            Object[][] tablaBilletera = new Object[6][2];

            for(int i = 0; i < 6; i++)
            {
                tablaBilletera[i][0] = vDashboard.divisas[i];
                tablaBilletera[i][1] = billeteraChange.getCantidades()[i];
            }

            vistaBilletera.modeloTablaDiv.setDataVector(tablaBilletera, vistaBilletera.cabeceraDiv);
            vistaBilletera.tblDivisas.setModel(vistaBilletera.modeloTablaDiv);
        
            vistaBilletera.lblMoneda.setVisible(false);
            vistaBilletera.cbDivisas.setVisible(false);
            vistaBilletera.lblFondo.setVisible(false);
            vistaBilletera.tfFondo.setVisible(false);
            vistaBilletera.btnGuardar.setVisible(false);
            vistaBilletera.btnCancelar.setVisible(false);

        }else if(e.getSource() == vPerfil.btnEditarP)
        {
            vistaEditarPerfil = new VistaEditarPerfil();
            vistaEditarPerfil.tfUsuario.setText(usuarioActivo.getUser());
            vistaEditarPerfil.btnGuardar.addActionListener(this);
        }else if(e.getSource() == vistaEditarPerfil.btnGuardar)
        {
            if(!vistaEditarPerfil.tfUsuario.getText().equals(usuarioActivo.getUser()))
            {
                ArrayList<Usuario> usuarios = new ArrayList<>();
                try 
                {
                int codUsuario, tipo;
                String user, password;

                BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal = new String[5];
                    temporal = line.split(";");
                    codUsuario = Integer.parseInt(temporal[0]);
                    user = temporal[1];
                    password = temporal[2];
                    tipo = Integer.parseInt(temporal[3]);

                    Usuario objTemp = new Usuario(codUsuario,user,password,tipo);
                    usuarios.add(objTemp);
                }
                br.close();

                } 
                catch (Exception exception) 
                {
                    System.out.println("error cargarDat:"+exception.toString());
                }
                try
                {
            
                    FileWriter fw = new FileWriter("usuarios.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    
                    Usuario usuarioActu = null;

                    for(Usuario usuario: usuarios)
                    {
                        if(usuario.getCodUsuario() == usuarioActivo.getCodUsuario())
                        {
                            usuarioActu = usuario;
                            break;
                        }
                    }
                    
                    usuarioActu.setUser(vistaEditarPerfil.tfUsuario.getText());
                    usuarioActivo = usuarioActu;
                    for(Usuario usuario: usuarios)
                    {
                        if(usuario.getCodUsuario() == usuarioActu.getCodUsuario())
                        {
                            usuario.setUser(usuarioActu.getUser());
                            break;
                        }
                    }

                    //Volver a escribir el archivo
                    for(Usuario usuario:usuarios)
                    {
                        String registro = usuario.getCodUsuario()+";"+ usuario.getUser()+";"+usuario.getPassword()+";"+usuario.getTipo();
                        pw.println(registro);
                    }
        
                    pw.close();
                } catch (Exception exception) {
                    System.out.println("error Noticias:"+exception.toString());
                }
                
            }
            vistaEditarPerfil.frame.dispose();
        }
        
    }
}
