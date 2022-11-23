package Controllers;

import Model.Divisa;
import Model.Noticia;
import Model.TipoDivisa;
import Model.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccesoArchivo
{
    ArrayList<Noticia> arrayNoticias;
    ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
    Divisa cambiosFlat;
    TipoDivisa tcBCP, tcINT, tcBBVA;
    RandomAccessFile  archivoUsuarios;
    private Noticia noticia;
    private Usuario usuario;
    
    int posicionNoticias;
    
    public AccesoArchivo()
    {
        try {
            crearArchivoNoticias();
            arrayNoticias = new ArrayList<>();
            cargarDatosArrayNoticias();
            archivoUsuarios = new RandomAccessFile("usuarios.txt", "rw");
            cargarDatosArrayUsuarios();
            cambiosFlat = new Divisa();
            cargarTipoCambios();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
    }
    
    public void crearArchivoNoticias()
    {
        try
        {
            File file = new File("Noticias.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
        }catch(IOException ex){System.out.println("Error: " + ex.getMessage());}
    }
    
    public void MandarInformacionAArchivoNoticias(int cod, String titulo, String autor, String logo, String imagen, String link, String resumen, int año, int mes)
    {
        try{
            File file = new File("Noticias.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.append(cod + ";");
                    bw.append(titulo + ";");
                    bw.append(autor + ";");
                    bw.append(logo + ";");
                    bw.append(imagen + ";");
                    bw.append(link + ";");
                    bw.append(resumen + ";");
                    bw.append(año + ";");
                    bw.append(mes + "\n");
                    bw.close();
                }catch(IOException ex){System.out.println("Error: " + ex.getMessage());}
        }catch(IOException ex){System.out.println("Error: " + ex.getMessage());}
    }
    
    public void actualizarArchivoNoticias()
    {
        try {
            
            FileWriter fw = new FileWriter("Noticias.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            //Volver a escribir el archivo
            for(Noticia x:arrayNoticias)
            {
                String registro =x.getCod()+";"+ x.getTitulo()+";"+x.getAutor()+";"+x.getLogo()+";"+x.getImagen()+";"+x.getResumen()+";"+x.getAño()+";"+x.getMes();
                pw.println(registro);
            }

            pw.close();
        } catch (Exception e) {
            System.out.println("error Noticias:"+e.toString());
        }
        
    }
    public void cargarDatosArrayNoticias()
    {
        try 
        {
            
            String tittle, autor, logo, imagen,resumen, link;
            int año, mes, cod;

            BufferedReader br = new BufferedReader(new FileReader("Noticias.txt"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal;
                temporal = line.split(";");
                cod= Integer.parseInt(temporal[0]);
                tittle = temporal[1];
                autor = temporal[2];
                logo=temporal[3];
                imagen=temporal[4];
                resumen=temporal[6];
                link=temporal[5];
                año = Integer.parseInt(temporal[7]);
                mes = Integer.parseInt(temporal[8]);
                
                Noticia objN = new Noticia(cod,tittle, autor, logo, imagen, link, resumen, año, mes);
                arrayNoticias.add(objN);
                
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDatNoticia:"+e.toString());
        }
    }
    
    public int getPosNoticia(String cod)
    {   
        for(Noticia x: arrayNoticias)
        {
            if( x.getCod() == Integer.parseInt(cod) )
            {    
                posicionNoticias = arrayNoticias.indexOf(x);
            }
        }       
        return posicionNoticias;
        //resetFile();
    }
    
    public Divisa getCambiosFlat()
    {
        return cambiosFlat;
    }

    public void cargarTipoCambios()
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/General/Flat.csv"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal = new String[6];
                temporal = line.split(",");
                cambiosFlat.setFecha(temporal[0]);
                cambiosFlat.getTasasDeCambio()[0] = Double.parseDouble(temporal[1]);
                cambiosFlat.getTasasDeCambio()[1] = Double.parseDouble(temporal[2]);
                cambiosFlat.getTasasDeCambio()[2] = Double.parseDouble(temporal[3]);
                cambiosFlat.getTasasDeCambio()[3] = Double.parseDouble(temporal[4]);
                cambiosFlat.getTasasDeCambio()[4] = Double.parseDouble(temporal[5]);
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDat:"+e.toString());
        }

        if(!cambiosFlat.getFecha().equals(LocalDate.now().toString()))
        {
            double nuevasTasas[] = new double[5];
            for(int i = 0; i < 5; i++)
            {
                int random_int = (int)Math.floor(Math.random()*(100-(-100)+1)+(-100));
                double randomFin = (double) random_int / 10000.00;
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                nuevasTasas[i] = Double.parseDouble(df.format(cambiosFlat.getTasasDeCambio()[i] * (1 + randomFin)));
            }
            cambiosFlat.setFecha(LocalDate.now().toString());
            cambiosFlat.setTasasDeCambio(nuevasTasas);
            
            try 
            {
                FileWriter fw = new FileWriter("src/db/Divisas/General/Flat.csv", true);
                PrintWriter pw = new PrintWriter(fw);
                
                //Agregar un registro
                String registro = cambiosFlat.getFecha() + ",";
                
                for(int i = 0; i < 5; i++)
                {
                    registro+= String.valueOf(cambiosFlat.getTasasDeCambio()[i]) + ",";
                }

                registro += "\n";
                    
                pw.append(registro);
    
                pw.close();
            } catch (Exception e) {
                System.out.println("error:"+e.toString());
            }
        }
    }

    public TipoDivisa tipoDeCambioBancos(String banco, String moneda, int id)
    {
        TipoDivisa tasaDia = new TipoDivisa();
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + banco +"/" + moneda +".csv"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal = new String[6];
                temporal = line.split(",");
                tasaDia.setFecha(temporal[0]);
                tasaDia.setCambioCompra(Double.parseDouble(temporal[1]));
                tasaDia.setCambioVenta(Double.parseDouble(temporal[2]));
            }
            br.close();
        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDat:"+e.toString());
        }

        if(!tasaDia.getFecha().equals(LocalDate.now().toString()))
        {
            int random_int = (int)Math.floor(Math.random()*(100-(-100)+1)+(-100));
            double randomFin = (double) random_int / 10000.00;

            DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.CEILING);

            tasaDia.setFecha(LocalDate.now().toString());
            tasaDia.setCambioCompra(Double.parseDouble(df.format(cambiosFlat.getTasasDeCambio()[id]*(1+randomFin))));
            tasaDia.setCambioVenta(Double.parseDouble(df.format(tasaDia.getCambioCompra() + 0.03)));

            try 
            {
                FileWriter fw = new FileWriter("src/db/Divisas/" + banco +"/" + moneda +".csv", true);
                PrintWriter pw = new PrintWriter(fw);
                
                //Agregar un registro
                String registro = tasaDia.getFecha() + "," + String.valueOf(tasaDia.getCambioCompra()) + "," + String.valueOf(tasaDia.getCambioVenta()) + "\n";
                    
                pw.append(registro);
    
                pw.close();
            } catch (Exception e) {
                System.out.println("error:"+e.toString());
            }
        }

        return tasaDia;
    }

    public void cargarDatosArrayUsuarios()
    {
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
                arrayUsuarios.add(objTemp);
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDat:"+e.toString());
        }
    }
    //Registrar nuevo usuario
    public void registrarUsuario(Usuario usuario)
    {
        try {
            FileWriter fw = new FileWriter("usuarios.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            
            //Agregar un registro
            String registro = String.valueOf(usuario.getCodUsuario() )+ ";" + usuario.getUser() + ";" + usuario.getPassword() + ";" + String.valueOf(usuario.getTipo()) + "\n";
                
            pw.append(registro);

            pw.close();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
    }
}
