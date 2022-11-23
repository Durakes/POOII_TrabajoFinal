package View;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaNotificaciones implements ActionListener
{
    public JFrame frame;
    public ArrayList<JButton> aceptarButton = new ArrayList<>();
    public JLabel labelSolicitudes;
    public DefaultTableModel modeloTabla = new DefaultTableModel();
    public JTable tablaNotificaciones;
    public JScrollPane scrollPane;
    String operacionesRec [][] = { {"AnaS","Compra","USD","100","3.90"}};
    public String cabecera[] = {"Usuario","Operacion","Moneda","Monto","Tasa de Cambio"};
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public VistaNotificaciones() throws MalformedURLException
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));

        labelSolicitudes = new JLabel("Solicitudes Pendientes"); 
        labelSolicitudes.setBounds(250,5,250,120);
        labelSolicitudes.setForeground(Color.WHITE);
        
        //Tabla
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        modeloTabla.setDataVector(operacionesRec, cabecera);
        modeloTabla.setRowCount(1);
        tablaNotificaciones = new JTable();
        tablaNotificaciones.setModel(modeloTabla);
        tablaNotificaciones.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tablaNotificaciones.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tablaNotificaciones.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tablaNotificaciones.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        tablaNotificaciones.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        tablaNotificaciones.setBackground(Color.BLACK/*new Color(61,61,61)*/);
        tablaNotificaciones.setForeground(Color.WHITE);
        tablaNotificaciones.setRowHeight(50);
        tablaNotificaciones.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablaNotificaciones.getTableHeader().setOpaque(false);
        tablaNotificaciones.getTableHeader().setBackground(new Color(252,178,30));
        tablaNotificaciones.getTableHeader().setForeground(Color.black);
        tablaNotificaciones.setFocusable(false);
        tablaNotificaciones.setDefaultEditor(Object.class, null);

        scrollPane = new JScrollPane(tablaNotificaciones);
        scrollPane.setBounds(60,100,500,300);
        scrollPane.setBackground(Color.red);

        frame.add(labelSolicitudes);
        frame.add(scrollPane);
        
        frame.setSize(700,450);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        
    }
}
