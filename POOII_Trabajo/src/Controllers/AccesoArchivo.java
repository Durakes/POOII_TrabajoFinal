package Controllers;

import Model.Noticia;
import Model.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AccesoArchivo
{
    ArrayList<Noticia> arrayNoticias;
    ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
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
    
    public void cargarDatosArrayUsuarios()
    {
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
            String registro = String.valueOf(usuario.getCodUsuario() )+ ";" + usuario.getUser() + ";" + usuario.getPassword() + ";" + String.valueOf(usuario.getTipo())
                                + ";" + usuario.getDivisa() + "\n";
                
            pw.append(registro);

            pw.close();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
    }
}
