package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
public class VistaRegistro {
    public JFrame frame;
    public JButton btnRegistrar;
    public JLabel lblTitulo, lblUsuario, lblContrasena, lblLogo, lblDivisaP,lblConfirmaContra;
    public JTextField tfUsuario;
    public JPasswordField pwContrasena, pwConfirmaContra;;
    public JComboBox<Object> cbDivisas;
    
    public VistaRegistro() throws MalformedURLException
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
        lblLogo.setBounds(50,100,170,170);
        
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
        
        lblConfirmaContra = new JLabel("Confirmar contraseña:");
        lblConfirmaContra.setBounds(280,180,150,20);
        lblConfirmaContra.setForeground(Color.WHITE);
        pwConfirmaContra = new JPasswordField();
        pwConfirmaContra.setBounds(280,200,200,20);
        
        lblDivisaP = new JLabel("Seleccione su divisa principal:");
        lblDivisaP.setBounds(280,240,200,20);
        lblDivisaP.setForeground(Color.WHITE);
        
        String[] divisas = {"USD","EUR","PEN","CAD","NZD","MXN"};
        cbDivisas = new JComboBox<>(divisas);
        cbDivisas.setBounds(280,260,100,20);
        
        //Boton
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(280,300,200,20);
        btnRegistrar.setBackground(new Color(252,152,53));
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        frame.add(lblTitulo);
        frame.add(lblLogo);
        frame.add(lblUsuario);
        frame.add(lblContrasena);
        frame.add(lblDivisaP);
        frame.add(cbDivisas);
        frame.add(tfUsuario);
        frame.add(pwContrasena);
        frame.add(lblConfirmaContra);
        frame.add(pwConfirmaContra);
        frame.add(btnRegistrar);
        
        frame.setSize(550,400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
