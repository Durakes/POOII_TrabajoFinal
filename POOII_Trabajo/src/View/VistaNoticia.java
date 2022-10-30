/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Noticia;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author lizdi
 */
public class VistaNoticia {
    //Vista general
    
    public JFrame VistaNoticia;
    int i;
    public JButton eliminar[] = new JButton[10];
    public JButton editar[] = new JButton[10];
    public JButton agregar, buscar;
    public JLabel titulo[]=new JLabel[10];
    public JLabel fecha[]=new JLabel[10];
    public JLabel fuente[]=new JLabel[10];
    public JLabel LAño, LMes;
    public JTextField Año;
    public JTextField mes;
    
    //Vista agregar
    
    public JFrame VistaAgregarNoticia;
    public JTextField tituloAgregar, logoAgregar, autorAgregar, imagenAgregar, linkAgregar, resumenAgregar;
    public JLabel EtiquetaTituloAgregar, EtiquetaLogoAgregar,EtiquetaAutorAgregar, EtiquetaImagenAgregar, EtiquetaLinkAgregar, EtiquetaResumenAgregar;
    public JButton BGuardar;
    
    //Vista modificar
    
    public JFrame VistaModificarNoticia;
    public JTextField tituloModificar,autorModificar, logoModificar, imagenModificar, linkModificar, resumenModificar;
    public JLabel EtiquetaTituloModificar,EtiquetaAutorModificar, EtiquetaLogoModificar, EtiquetaImagenModificar, EtiquetaLinkModificar, EtiquetaResumenModificar;
    public JButton BGuardarCambios; 
    
    
    int xFecha = 25;
    int yFecha = 155;

    int xfuente = 135;
    int yfuente = 155;
    
    int xtitulo = 25;
    int ytitulo = 100;
    
    int xEliminar = 700;
    int yEliminar = 125;
    
    int xEditar = 645;
    int yEditar = 125;
    

    public VistaNoticia()
    {
        VistaNoticia = new JFrame("Calculadora");
        agregar=new JButton("Agregar");
        agregar.setBounds(600, 25, 100, 30);
        buscar=new JButton("Buscar");
        buscar.setBounds(25, 65, 100, 30);
        Año= new JTextField();
        Año.setBounds(315, 25, 100, 30);
        mes= new JTextField();
        mes.setBounds(115, 25, 100, 30);
        LAño=new JLabel("Año: ");
        LAño.setBounds(225, 25, 80, 30);
        LMes=new JLabel("Mes: ");
        LMes.setBounds(25, 25, 80,30);
        
        VistaNoticia.add(agregar);
        VistaNoticia.add(buscar);
        VistaNoticia.add(Año);
        VistaNoticia.add(mes);
        VistaNoticia.add(LAño);
        VistaNoticia.add(LMes);
        VistaNoticia.setSize(810, 640);
        VistaNoticia.setLayout(null);
        VistaNoticia.setLocationRelativeTo(null);
        VistaNoticia.setVisible(true);
        VistaNoticia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void visualizar(ArrayList<Noticia> list) throws IOException{
        
        Font fuenteTitulo=new Font("Comic Sans MC", Font.BOLD, 20);
        
        for(i = 0; i < list.size(); i++)
        {
            titulo[i] = new JLabel(list.get(i).getTitulo());
            titulo[i].setFont(fuenteTitulo);
            titulo[i].setBounds(xtitulo, ytitulo, 400, 30);
            VistaNoticia.add(titulo[i]);
            
            fecha[i] = new JLabel(list.get(i).getMes()+"/"+list.get(i).getAño());
            fecha[i].setBounds(xFecha, yFecha, 45, 30);
            VistaNoticia.add(fecha[i]);
            
            
            URL url = new URL(list.get(i).getLogo());
            BufferedImage imagen = ImageIO.read(url);
            Image image = imagen.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
            fuente[i] = new JLabel(new ImageIcon(image));
            fuente[i].setBounds(xfuente, yfuente, 45, 45);
            VistaNoticia.add(fuente[i]);
            
            
            
            editar[i] = new JButton(String.valueOf(i+1));
            editar[i].setBounds(xEditar, yEditar, 45, 45);
            editar[i].setBackground(Color.orange);
            VistaNoticia.add(editar[i]);
            
            eliminar[i] = new JButton(String.valueOf(i+1));
            eliminar[i].setBounds(xEliminar, yEliminar, 45, 45);
            
            VistaNoticia.add(eliminar[i]);
            
            
            yFecha +=145;

            
            yfuente +=145;

            
            ytitulo +=145;

            yEliminar +=145;

            yEditar +=145;
            
        }
    }
    
    public void VistaAgregar(){
        VistaAgregarNoticia=new JFrame("Agrega una noticia");
        EtiquetaTituloAgregar=new JLabel("Titulo: ");
        EtiquetaAutorAgregar=new JLabel("Autor: ");
        EtiquetaLogoAgregar=new JLabel("Logo(URL): "); 
        EtiquetaImagenAgregar=new JLabel("Imagen(URL): "); 
        EtiquetaLinkAgregar=new JLabel("Link: ");
        EtiquetaResumenAgregar=new JLabel("Resumen: ");
        tituloAgregar=new JTextField();
        autorAgregar=new JTextField();
        logoAgregar=new JTextField();
        imagenAgregar=new JTextField();
        linkAgregar=new JTextField();
        resumenAgregar=new JTextField();
        BGuardar=new JButton("Guardar");
        
        EtiquetaTituloAgregar.setBounds(25, 25, 100, 30);
        EtiquetaAutorAgregar.setBounds(25, 65, 100, 30);
        EtiquetaLogoAgregar.setBounds(25, 105, 100, 30); 
        EtiquetaImagenAgregar.setBounds(25, 145, 100, 30); 
        EtiquetaLinkAgregar.setBounds(25, 185, 100, 30);
        EtiquetaResumenAgregar.setBounds(25, 225, 100, 30);
        tituloAgregar.setBounds(135, 25, 100, 30);
        autorAgregar.setBounds(135, 65, 100, 30);
        logoAgregar.setBounds(135, 105, 100, 30);
        imagenAgregar.setBounds(135, 145, 100, 30);
        linkAgregar.setBounds(135, 185, 100, 30);
        resumenAgregar.setBounds(135, 225, 100, 30);
        BGuardar.setBounds(80, 265, 100, 30);
        
        VistaAgregarNoticia.add(EtiquetaTituloAgregar);
        VistaAgregarNoticia.add(EtiquetaAutorAgregar);        
        VistaAgregarNoticia.add(EtiquetaLogoAgregar);
        VistaAgregarNoticia.add(EtiquetaImagenAgregar);
        VistaAgregarNoticia.add(EtiquetaLinkAgregar);
        VistaAgregarNoticia.add(EtiquetaResumenAgregar);
        VistaAgregarNoticia.add(tituloAgregar);
        VistaAgregarNoticia.add(autorAgregar);        
        VistaAgregarNoticia.add(logoAgregar);
        VistaAgregarNoticia.add(imagenAgregar);
        VistaAgregarNoticia.add(linkAgregar);
        VistaAgregarNoticia.add(resumenAgregar);
        VistaAgregarNoticia.add(BGuardar);
        VistaAgregarNoticia.setSize(260, 350);
        VistaAgregarNoticia.setLayout(null);
        VistaAgregarNoticia.setLocationRelativeTo(null);
        VistaAgregarNoticia.setVisible(true);
    }
    
}
