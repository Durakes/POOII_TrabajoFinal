/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Model.Noticia;
import Model.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author apa16
 */
public class AccesoArchivo {
    ArrayList<Noticia> arrayNoticias = new ArrayList<>();
    ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
    RandomAccessFile archivoNoticias, archivoUsuarios;
    private Noticia noticia;
    private Usuario usuario;
    
    public AccesoArchivo()
    {
        try {
            archivoNoticias = new RandomAccessFile("Noticias.txt","rw");
            cargarDatosArray();
            archivoUsuarios = new RandomAccessFile("usuarios.txt", "rw");
            cargarDatosArrayUsuarios();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
        
    }
    public void actualizarArchivo()
    {
        try {
            FileWriter fw = new FileWriter("productos.txt");
            PrintWriter pw = new PrintWriter(fw);
            
            //Volver a escribir el archivo
            for(Noticia x:arrayNoticias)
            {
                String registro = x.getTitulo()+";"+x.getAutor()+";"+x.getLogo()+";"+x.getImagen()+";"+x.getResumen()+";"+x.getAño()+";"+x.getMes();
                pw.println(registro);
            }

            pw.close();
        } catch (Exception e) {
            System.out.println("error:"+e.toString());
        }
        
    }
    public void cargarDatosArray()
    {
        try 
        {
            String tittle, autor, logo, imagen,resumen, link;
            int año, mes;
     
            BufferedReader br = new BufferedReader(new FileReader("productos.txt"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal = new String[8];
                temporal = line.split(";");
                tittle = temporal[0];
                autor = temporal[1];
                logo=temporal[2];
                imagen=temporal[3];
                resumen=temporal[4];
                link=temporal[5];
                año = Integer.parseInt(temporal[6]);
                mes = Integer.parseInt(temporal[7]);
                
                Noticia objN = new Noticia(tittle, autor, logo, imagen, resumen, link, año, mes);
                arrayNoticias.add(objN);
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDat:"+e.toString());
        }
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
