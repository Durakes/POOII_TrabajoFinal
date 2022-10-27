package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class VistaLogin implements ActionListener{
    
    public JFrame frame;
    public JButton btnRegistrar, btnLogin;
    public JLabel lblTitulo, lblRegistro, lblUsuario, lblContrasena, lblLogo;
    public JPasswordField pwContrasena;
    public JTextField tfUsuario;
    public VistaLogin () throws MalformedURLException
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        lblTitulo = new JLabel("Nombre del Proyecto");
        lblTitulo.setBounds(210,20,150,20);
        lblTitulo.setForeground(Color.WHITE);
        
        //Imagen
        URL url = new URL("https://media.cdnandroid.com/item_images/1141766/imagen-logo-maker-create-logos-and-icon-design-creator-0big.jpg");
        BufferedImage logo = null;
        try 
        {
            logo = ImageIO.read(url);
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
        }
            
        Image dimg = logo.getScaledInstance(170, 170,Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(dimg);
        
        lblLogo = new JLabel(); 
        lblLogo.setIcon(logoIcon);
        lblLogo.setBounds(50,70,170,170);
        
        //Labels y TF
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(280,60,100,20);
        lblUsuario.setForeground(Color.WHITE);
        tfUsuario = new JTextField();
        tfUsuario.setBounds(280,80,200,20);
        
        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(280,120,100,20);
        lblContrasena.setForeground(Color.WHITE);
        pwContrasena = new JPasswordField();
        pwContrasena.setBounds(280,140,200,20);
        
        //Botones
        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(280,180,200,20);
        btnLogin.setBackground(new Color(252,152,53));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(this);
        
        lblRegistro = new JLabel("¿No tienes una cuenta?");
        lblRegistro.setBounds(280,220,150,20);
        lblRegistro.setForeground(Color.WHITE);
        
        btnRegistrar = new JButton("Regístrate");
        btnRegistrar.setBounds(400,220,100,20);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setOpaque(false);
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegistrar.setForeground(Color.ORANGE);
        btnRegistrar.addActionListener(this);
        
        frame.add(lblTitulo);
        frame.add(lblLogo);
        frame.add(lblUsuario);
        frame.add(lblContrasena);
        frame.add(tfUsuario);
        frame.add(pwContrasena);
        frame.add(btnLogin);
        frame.add(lblRegistro);
        frame.add(btnRegistrar);
        
        frame.setSize(550,350);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnLogin)
        {
            VistaPerfil obj = new VistaPerfil();
            frame.dispose();
        }
        if(e.getSource() == btnRegistrar)
        {
            try {
                VistaRegistro objRegistro = new VistaRegistro();
            } catch (MalformedURLException ex) {
                
            }
        }
    }
    
}
