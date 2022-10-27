package View;

import java.awt.Color;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public class VistaPerfilAdmin implements ActionListener{
    public JFrame frame;
    public JButton btnEditarP, btnNoticias;
    public JLabel lblOpRec, lblUsuario, lblLogo, lblDivisaP;
    
    public VistaPerfilAdmin() throws MalformedURLException
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        //Imagen
        URL url = new URL("https://img.freepik.com/vector-premium/perfil-avatar-hombre-icono-redondo_24640-14044.jpg?w=2000");
        BufferedImage logo = null;
        try 
        {
            logo = ImageIO.read(url);
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
        }
            
        Image dimg = logo.getScaledInstance(120, 120,Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(dimg);
        
        lblLogo = new JLabel(); 
        lblLogo.setIcon(logoIcon);
        lblLogo.setBounds(40,30,120,120);
        
        //Campos de info
        lblUsuario = new JLabel("JuanFr");
        lblUsuario.setBounds(180,35,100,30);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 23));
        
        //Boton noticias
        btnNoticias = new JButton("Noticias");
        btnNoticias.setBounds(180,80,150,20);
        btnNoticias.setBackground(new Color(252,152,53));
        btnNoticias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //Boton editar
        btnEditarP = new JButton("Editar perfil");
        btnEditarP.setBounds(180,120,150,20);
        btnEditarP.setBackground(new Color(252,152,53));
        btnEditarP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditarP.addActionListener(this);
        

        frame.add(lblLogo);
        frame.add(lblUsuario);
        frame.add(btnEditarP);
        frame.add(btnNoticias);
        
        frame.setSize(410,250);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e)
    {
        try {
            if(e.getSource() == btnEditarP)
            {
                VistaEditarPerfilAdm obj = new VistaEditarPerfilAdm();
            }
        } catch (Exception ex) {
        }
    }
}
