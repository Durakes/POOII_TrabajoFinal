/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Model.Noticia;
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
    RandomAccessFile archivoNoticias;
    private Noticia noticia;
    
    public AccesoArchivo()
    {
        try {
            archivoNoticias = new RandomAccessFile("Noticias.txt","rw");
            cargarDatosArray();
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
}
