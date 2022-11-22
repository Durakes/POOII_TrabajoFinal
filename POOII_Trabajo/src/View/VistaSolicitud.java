package View;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VistaSolicitud implements ActionListener
{
    public JFrame frame;
    public JButton btnGuardar, btnCancelar;
    public JLabel lblTitulo, lblDivisaP, lblMonto;
    public JTextField tfMonto, textTasaCambio;
    
    public VistaSolicitud()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        lblTitulo = new JLabel("Generar Solicitud");
        lblTitulo.setBounds(160,10,150,20);
        lblTitulo.setForeground(Color.white);
        
        lblMonto = new JLabel("Monto: ");
        lblMonto.setBounds(80,60,100,20);
        lblMonto.setForeground(Color.WHITE);
        tfMonto = new JTextField();
        tfMonto.setBounds(190,60,120,20);
        
        lblDivisaP = new JLabel("Tipo de Cambio: ");
        lblDivisaP.setBounds(80,100,200,20);
        lblDivisaP.setForeground(Color.WHITE);
        textTasaCambio = new JTextField();
        textTasaCambio.setBounds(190,100,120,20);

        btnGuardar = new JButton("Enviar");
        btnGuardar.setBounds(80,160,110,20);
        btnGuardar.setBackground(new Color(252,152,53));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //btnGuardar.addActionListener(this);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220,160,110,20);
        btnCancelar.setBackground(new Color(252,152,53));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        frame.add(lblTitulo);
        frame.add(lblMonto);
        frame.add(tfMonto);
        frame.add(textTasaCambio);
        frame.add(lblDivisaP);
        frame.add(btnGuardar);
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
            
        } catch (Exception ex) {
        }
    }
}