package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
public class VistaCambiarPW {
    public JFrame frame;
    public JButton btnGuardar, btnCancelar;
    public JLabel lblTitulo, lblContraAct, lblNuevaContra, lblConfirmaContra;
    public JPasswordField pwContraAct, pwNuevaContra, pwConfirmaContra;
    
    public VistaCambiarPW()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        lblTitulo = new JLabel("Cambiar contraseña");
        lblTitulo.setBounds(120,10,150,20);
        lblTitulo.setForeground(Color.white);
        
        lblContraAct = new JLabel("Contraseña actual:");
        lblContraAct.setBounds(60,60,150,20);
        lblContraAct.setForeground(Color.WHITE);
        pwContraAct = new JPasswordField();
        pwContraAct.setBounds(200,60,120,20);
        
        lblNuevaContra = new JLabel("Nueva contraseña:");
        lblNuevaContra.setBounds(60,100,150,20);
        lblNuevaContra.setForeground(Color.WHITE);
        pwNuevaContra = new JPasswordField();
        pwNuevaContra.setBounds(200,100,120,20);
        
        lblConfirmaContra = new JLabel("Confirmar contraseña:");
        lblConfirmaContra.setBounds(60,140,150,20);
        lblConfirmaContra.setForeground(Color.WHITE);
        pwConfirmaContra = new JPasswordField();
        pwConfirmaContra.setBounds(200,140,120,20);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(210,180,110,20);
        btnGuardar.setBackground(new Color(252,152,53));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(60,180,110,20);
        btnCancelar.setBackground(new Color(252,152,53));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        frame.add(lblTitulo);
        frame.add(lblContraAct);
        frame.add(pwContraAct);
        frame.add(lblNuevaContra);
        frame.add(pwNuevaContra);
        frame.add(lblConfirmaContra);
        frame.add(pwConfirmaContra);
        frame.add(btnGuardar);
        frame.add(btnCancelar);
        
        frame.setSize(390,280);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
}
