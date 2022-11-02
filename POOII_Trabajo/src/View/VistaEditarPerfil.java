package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VistaEditarPerfil implements ActionListener{
    public JFrame frame;
    public JButton btnGuardar, btnContrasena, btnCancelar;
    public JLabel lblTitulo, lblDivisaP, lblUsuario;
    public JTextField tfUsuario;
    public JComboBox<Object> cbDivisas;
    
    public VistaEditarPerfil()
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
        tfUsuario.setText("pepeTS");
        tfUsuario.setBounds(190,60,120,20);
        
        lblDivisaP = new JLabel("Divisa principal:");
        lblDivisaP.setBounds(80,100,200,20);
        lblDivisaP.setForeground(Color.WHITE);

        String[] divisas = {"USD","EUR","PEN","CAD","NZD","MXN"};
        cbDivisas = new JComboBox<>(divisas);
        cbDivisas.setBounds(190,100,120,20);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(220,160,110,20);
        btnGuardar.setBackground(new Color(252,152,53));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //btnGuardar.addActionListener(this);
        
        btnContrasena = new JButton("Cambiar contraseña");
        btnContrasena.setBounds(60,160,150,20);
        btnContrasena.setBackground(new Color(252,152,53));
        btnContrasena.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnContrasena.addActionListener(this);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(140,200,110,20);
        btnCancelar.setBackground(new Color(252,152,53));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        frame.add(lblTitulo);
        frame.add(lblUsuario);
        frame.add(tfUsuario);
        frame.add(lblDivisaP);
        frame.add(btnGuardar);
        frame.add(cbDivisas);
        frame.add(btnContrasena);
        frame.add(btnCancelar);
        
        frame.setSize(390,300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e)
    {
        try 
        {
            if(e.getSource() == btnGuardar)
            {
                frame.dispose();
            }
            if(e.getSource() == btnContrasena)
            {
                VistaCambiarPW obj = new VistaCambiarPW();
            }
            
            
        } catch (Exception ex) {
        }
    }
}
