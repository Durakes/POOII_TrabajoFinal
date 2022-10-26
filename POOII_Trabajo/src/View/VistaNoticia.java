/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    public JFrame frame;
    int i;
    public JTextField result;
    public JButton eliminar[] = new JButton[10];
    public JButton editar[] = new JButton[10];
    public JButton agregar;
    public JLabel titulo[]=new JLabel[10];
    public JLabel fecha[]=new JLabel[10];
    public JLabel fuente[]=new JLabel[10];
    public JTextField Año;
    public JTextField mes;
    
    int xFecha = 25;
    int yFecha = 135;

    int xfuente = 135;
    int yfuente = 135;
    
    int xtitulo = 25;
    int ytitulo = 80;
    
    int xEliminar = 700;
    int yEliminar = 105;
    
    int xEditar = 645;
    int yEditar = 105;
    

    public VistaNoticia() throws MalformedURLException, IOException
    {
        frame = new JFrame("Calculadora");
        Font fuenteTitulo=new Font("Comic Sans MC", Font.BOLD, 20);
        agregar=new JButton("Agregar");
        agregar.setBounds(600, 25, 100, 45);
        Año= new JTextField();
        Año.setBounds(150, 25, 100, 45);
        mes= new JTextField();
        mes.setBounds(25, 25, 100, 45);
        for(i = 0; i < 10; i++)
        {
            titulo[i] = new JLabel("Titulo");
            titulo[i].setFont(fuenteTitulo);
            titulo[i].setBounds(xtitulo, ytitulo, 400, 45);
            frame.add(titulo[i]);
            
            fecha[i] = new JLabel("fecha");
            fecha[i].setBounds(xFecha, yFecha, 45, 45);
            frame.add(fecha[i]);
            
            
            URL url = new URL("http://www.bypeople.org/wp-content/uploads/2017/01/comercio_logo.jpg");
            BufferedImage imagen = ImageIO.read(url);
            Image image = imagen.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
            fuente[i] = new JLabel(new ImageIcon(image));
            fuente[i].setBounds(xfuente, yfuente, 45, 45);
            frame.add(fuente[i]);
            
            
            
            editar[i] = new JButton(String.valueOf(i+1));
            editar[i].setBounds(xEditar, yEditar, 45, 45);
            editar[i].setBackground(Color.orange);
            frame.add(editar[i]);
            
            eliminar[i] = new JButton(String.valueOf(i+1));
            eliminar[i].setBounds(xEliminar, yEliminar, 45, 45);
            
            frame.add(eliminar[i]);
            
            
            yFecha =yFecha+145;

            
            yfuente =yfuente+ 145;

            
            ytitulo = ytitulo + 145;

            yEliminar =yEliminar+ 145;

            yEditar =yEditar+ 145;
            
            
        }
        

            

        result = new JTextField();
        result.setBounds(100, 50, 195, 45);
        frame.add(agregar);
        frame.add(Año);
        frame.add(mes);
        frame.setSize(810, 640);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
