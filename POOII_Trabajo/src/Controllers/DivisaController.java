package Controllers;
import Model.Noticia;
import Model.Usuario;
import Model.Divisa;
import Helpers.PaintChart;
import View.VistaDashboard;
import View.VistaPerfil;
import Model.Usuario;
import Model.TipoDivisa;
import Model.Transaccion;
import Model.Billetera;
import View.VistaBilletera;
import View.VistaEditarPerfil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.*;

import Helpers.PaintChart;

import java.time.*;
import java.security.Key;

public class DivisaController implements ActionListener
{
    private ArrayList<Divisa> divisaRate = new ArrayList<>();
    private ArrayList<Transaccion> registros = new ArrayList<>();
    private ArrayList<Billetera> billeteras = new ArrayList<>();
    Usuario usuarioActivo;
    VistaDashboard vDashboard;
    VistaPerfil vPerfil;
    VistaBilletera vistaBilletera;
    VistaEditarPerfil vistaEditarPerfil;
    double exchangeRate;
    double exchangeRateInv;
    ArrayList<Double> ratesGraph;
    public DivisaController(Usuario usuario)
    {
        this.usuarioActivo = usuario;
        vDashboard = new VistaDashboard();
        try{vPerfil = new VistaPerfil();}catch(Exception exception){}
        vPerfil.frame.setVisible(false);
        try{vistaBilletera = new VistaBilletera();}catch(Exception exception){}
        vistaBilletera.frame.setVisible(false);
        try{vistaEditarPerfil = new VistaEditarPerfil();}catch(Exception exception){}
        vistaEditarPerfil.frame.setVisible(false);
        vDashboard.perfil.setText(usuarioActivo.getUser());
        vDashboard.divisaPrincipal.setText(usuarioActivo.getDivisa());
        int index = 0;
        for(int i = 0; i < 9; i++)
        {
            if(vDashboard.divisas[i].equals(usuarioActivo.getDivisa()))
            {
                continue;
            }
            vDashboard.buttonTipoDivisas[index].setText(vDashboard.divisas[i]);
            index++;
        }
        cargarTipoCambio();
        vDashboard.monedaIni.addActionListener(this);
        vDashboard.cambioMoneda.addActionListener(this);
        exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
        exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
        vDashboard.registarTransaccion.addActionListener(this);
        vDashboard.cambioCripto.addActionListener(this);

        ratesGraph = new ArrayList<>();
        for(int i = 5; i > 0; i--)
        {
            ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
        }

        //vDashboard.chart = new PaintChart(ratesGraph);
        vDashboard.chart.changeRates(ratesGraph);
        //vDashboard.chart.setBounds(300, 175, 600, 450);
        vDashboard.perfil.addActionListener(this);
        vDashboard.button5d.addActionListener(this);
        vDashboard.button1m.addActionListener(this);
        vDashboard.button6m.addActionListener(this);
        vDashboard.button1y.addActionListener(this);

        for(int i = 0; i < 8; i++)
        {
            final int final_i = i;
            vDashboard.buttonTipoDivisas[i].addActionListener(this);
        }
        
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
                double tempDouble[] = new double[9];
                for(int i = 0; i < 9; i++)
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

    public void cargarTipoCambio()
    {
        try 
        {
            String date;
            double firstRate, secondRate;
            BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/"+ usuarioActivo.getDivisa() +"/"+vDashboard.buttonTipoDivisas[0].getText() +".csv"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal;
                temporal = line.split(",");
                date = temporal[0];
                firstRate = Double.parseDouble(temporal[1]);
                secondRate = Double.parseDouble(temporal[2]);
                
                Divisa objDiv = new Divisa(date,firstRate, secondRate);
                divisaRate.add(objDiv);
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDatNoticia:"+e.toString());
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == vDashboard.monedaIni)
        {
            double result = Math.round((Double.parseDouble(vDashboard.monedaIni.getText()) * exchangeRate)*100);
            result /= 100;
            vDashboard.monedaFin.setText(String.valueOf(result));
            
        }else if(e.getSource() == vDashboard.cambioMoneda)
        {
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");

            String tempText;
            double tempRate;
            
            tempText = vDashboard.monedaIniLabel.getText();
            vDashboard.monedaIniLabel.setText(vDashboard.monedaFinLabel.getText());
            vDashboard.monedaFinLabel.setText(tempText);
            
            tempRate = exchangeRate;
            exchangeRate = exchangeRateInv;
            exchangeRateInv = tempRate;

        }else if(e.getSource() == vDashboard.registarTransaccion) /*Agregar id usuario*/
        {
            billeteras.clear();
            try
            {
                FileWriter fw = new FileWriter("src/db/registros.csv", true);
                PrintWriter pw = new PrintWriter(fw);
                
                //Agregar un registro
                String registro = String.valueOf(usuarioActivo.getCodUsuario()) + ";" + String.valueOf(LocalDate.now())+ ";" + vDashboard.monedaIni.getText() + ";" + vDashboard.monedaFin.getText() + ";" + vDashboard.monedaIniLabel.getText() + "/" + vDashboard.monedaFinLabel.getText() + "\n";
                pw.append(registro);
                pw.close();

            } catch (Exception ex)
            {
                System.out.println("error:"+ex.toString());
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
            for(int i = 0; i < 9; i++)
            {
                if(vDashboard.monedaIniLabel.getText() == vDashboard.divisas[i])
                {
                    temporalCantidades[i] -= Double.parseDouble(vDashboard.monedaIni.getText());
                    System.out.println(temporalCantidades[i]);
                }
                if(vDashboard.monedaFinLabel.getText() == vDashboard.divisas[i])
                {
                    temporalCantidades[i] += Double.parseDouble(vDashboard.monedaFin.getText());
                    System.out.println(temporalCantidades[i]);
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
                    for (int i = 0; i< 9; i++)
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


        }else if(e.getSource() == vDashboard.cambioCripto)
        {
            if(vDashboard.cambioCripto.getText() == "Criptomonedas")
            {
                vDashboard.cambioCripto.setText("Divisas");
                vDashboard.panelDivisas.setVisible(false);
                vDashboard.panelCriptos.setVisible(true);
            }else if(vDashboard.cambioCripto.getText() == "Divisas")
            {
                vDashboard.cambioCripto.setText("Criptomonedas");
                vDashboard.panelDivisas.setVisible(true);
                vDashboard.panelCriptos.setVisible(false);
            }
        }else if(e.getSource() == vDashboard.button5d)
        {
            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button1m)
        {
            ratesGraph.clear();
            for(int i = 30; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button6m)
        {
            ratesGraph.clear();
            for(int i = 180; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button1y)
        {
            ratesGraph.clear();
            for(int i = 360; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.perfil)
        {
            try 
            {
                int codigo;
                String fechaRegistro;
                double monedaInicio,monedaFinal;
                String divisas;
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
                        monedaInicio = Double.parseDouble(temporal[2]);
                        monedaFinal = Double.parseDouble(temporal[3]);
                        divisas = temporal[4];

                        Transaccion transaccion= new Transaccion(codigo,fechaRegistro,monedaInicio,monedaFinal,divisas);
                        registros.add(transaccion);
                    }
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDat:"+exception.toString());
            }

            Object datos[][] = new Object[registros.size()][4];
            if(registros.size()!=0)
            {
                try 
                {
                    for(int i = 0; i < registros.size(); i++)
                    {
                        datos[i][0] = registros.get(i).getFechaTransaccion();
                        datos[i][1] = registros.get(i).getMonedaPrim();
                        datos[i][2] = registros.get(i).getMonedaFin();
                        datos[i][3] = registros.get(i).getOrdenDivisas();
                    }
                } 
                catch (Exception exception) 
                {
                    System.out.println("error obtenerDat:"+exception.toString());
                }
            }

            Object datosPerfil[][] = new Object[8][4];

            for(int i = 0; i < 8; i++)
            {
                datosPerfil[i][0] = registros.get(registros.size() - (i+1)).getFechaTransaccion();
                datosPerfil[i][1] = registros.get(registros.size() - (i+1)).getMonedaPrim();
                datosPerfil[i][2] = registros.get(registros.size() - (i+1)).getMonedaFin();
                datosPerfil[i][3] = registros.get(registros.size() - (i+1)).getOrdenDivisas();
            }

            try
            {
                vPerfil = new VistaPerfil();
                vPerfil.lblUsuario.setText(usuarioActivo.getUser());
                vDashboard.frame.setVisible(false);
                vPerfil.lblDivisaP.setText("Divisa principal: " + usuarioActivo.getDivisa());
                vPerfil.btnAtras.addActionListener(this);
                vPerfil.btnBilletera.addActionListener(this);
                vPerfil.modeloTabla.setDataVector(datosPerfil, vPerfil.cabecera);
                vPerfil.tblOpRec.setModel(vPerfil.modeloTabla);
                vPerfil.btnEditarP.addActionListener(this);

            }catch(Exception exception)
            {
            }
            
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[0])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[0].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }
            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }
        else if(e.getSource() == vDashboard.buttonTipoDivisas[1])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[1].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }
            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[2])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[2].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[3])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[3].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[4])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[4].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[5])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[5].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[6])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[6].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[7])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[7].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
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
                        double tempDouble[] = new double[9];
                        for(int i = 0; i < 9; i++)
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
            Object[][] tablaBilletera = new Object[9][2];

            for(int i = 0; i < 9; i++)
            {
                tablaBilletera[i][0] = vDashboard.divisas[i];
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
            for(int i = 0; i < 9; i++)
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
                    for (int i = 0; i< 9; i++)
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
            
            Object[][] tablaBilletera = new Object[9][2];

            for(int i = 0; i < 9; i++)
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
            vistaEditarPerfil.cbDivisas.setSelectedItem(usuarioActivo.getDivisa());
            vistaEditarPerfil.btnGuardar.addActionListener(this);
        }else if(e.getSource() == vistaEditarPerfil.btnGuardar)
        {
            if(!vistaEditarPerfil.tfUsuario.getText().equals(usuarioActivo.getUser()) ||!vistaEditarPerfil.cbDivisas.getSelectedItem().toString().equals(usuarioActivo.getDivisa()))
            {
                ArrayList<Usuario> usuarios = new ArrayList<>();
                try 
                {
                int codUsuario, tipo;
                String user, password, divisa;

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
                    divisa = (temporal[4]);

                    Usuario objTemp = new Usuario(codUsuario,user,password,tipo,divisa);
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
                    usuarioActu.setCodDivisa(vistaEditarPerfil.cbDivisas.getSelectedItem().toString());
                    usuarioActivo = usuarioActu;
                    for(Usuario usuario: usuarios)
                    {
                        if(usuario.getCodUsuario() == usuarioActu.getCodUsuario())
                        {
                            usuario.setUser(usuarioActu.getUser());
                            usuario.setCodDivisa(usuarioActu.getDivisa());
                            break;
                        }
                    }

                    //Volver a escribir el archivo
                    for(Usuario usuario:usuarios)
                    {
                        String registro = usuario.getCodUsuario()+";"+ usuario.getUser()+";"+usuario.getPassword()+";"+usuario.getTipo()+";"+usuario.getDivisa();
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
