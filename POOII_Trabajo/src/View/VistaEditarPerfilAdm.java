package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VistaEditarPerfilAdm {
    public JFrame frame;
    public JButton btnGuardar, btnContrasena, btnCancelar;
    public JLabel lblTitulo, lblUsuario;
    public JTextField tfUsuario;
    
    public VistaEditarPerfilAdm()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        lblTitulo = new JLabel("Editar perfil");
        lblTitulo.setBounds(160,10,100,20);
        lblTitulo.setForeground(Color.white);
        
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(80,60,100,20);
        lblUsuario.setForeground(Color.WHITE);
        tfUsuario = new JTextField();
        tfUsuario.setText("JuanFR");
        tfUsuario.setBounds(190,60,120,20);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(220,100,110,20);
        btnGuardar.setBackground(new Color(252,152,53));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        btnContrasena = new JButton("Cambiar contraseña");
        btnContrasena.setBounds(60,100,150,20);
        btnContrasena.setBackground(new Color(252,152,53));
        btnContrasena.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(140,140,110,20);
        btnCancelar.setBackground(new Color(252,152,53));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        frame.add(lblTitulo);
        frame.add(lblUsuario);
        frame.add(tfUsuario);
        frame.add(btnGuardar);
        frame.add(btnContrasena);
        frame.add(btnCancelar);
        
        frame.setSize(390,220);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
