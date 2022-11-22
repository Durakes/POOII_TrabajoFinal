package View;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaBilletera implements ActionListener {
    public JFrame frame;
    public JButton btnAgregarFon, btnCripto, btnDiv, btnGuardar, btnCancelar;;
    public JLabel lblBilletera, lblTitulo, lblFondo, lblMoneda;
    public JTextField tfFondo;
    public JComboBox<Object> cbDivisas;
    public DefaultTableModel modeloTablaDiv = new DefaultTableModel();
    public DefaultTableModel modeloTablaCripto = new DefaultTableModel();
    public JTable tblDivisas, tblCripto;
    public JScrollPane scrollPaneDiv, scrollPaneCripto;
    String fondosDiv [][] = {{"PEN","674"},{"USD","674"},{"EUR","674"},{"JPY","674"},{"GBP","674"},{"BRL","674"}};
    public String cabeceraDiv[] = {"Divisa","Fondos"};
    public DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public VistaBilletera()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        //Label
        lblBilletera = new JLabel("Billetera");
        lblBilletera.setBounds(180,10,100,20);
        lblBilletera.setForeground(Color.white);
        
        //Tabla divisas
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        modeloTablaDiv.setDataVector(fondosDiv, cabeceraDiv);
        tblDivisas = new JTable();
        tblDivisas.setModel(modeloTablaDiv);
        tblDivisas.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblDivisas.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblDivisas.setBackground(new Color(61,61,61));
        tblDivisas.setForeground(Color.white);
        tblDivisas.setRowHeight(35);
        tblDivisas.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblDivisas.getTableHeader().setOpaque(false);
        tblDivisas.getTableHeader().setBackground(new Color(252,178,30));
        tblDivisas.getTableHeader().setForeground(Color.black);
        tblDivisas.setFocusable(false);
        tblDivisas.setDefaultEditor(Object.class, null);
        scrollPaneDiv = new JScrollPane(tblDivisas);
        scrollPaneDiv.setBounds(75,60,250,338);
        scrollPaneDiv.setBackground(Color.red);
        
        //Boton Divisas
        btnDiv = new JButton("Divisas");
        btnDiv.setBounds(75,340,135,20);
        btnDiv.setBackground(new Color(23,23,23));
        btnDiv.setForeground(Color.white);
        btnDiv.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDiv.addActionListener(this);
        btnDiv.setVisible(false);
        
        //Boton Agregar Fondos
        btnAgregarFon = new JButton("Agregar fondos");
        btnAgregarFon.setBounds(75,450,135,20);
        btnAgregarFon.setBackground(new Color(252,152,53));
        btnAgregarFon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAgregarFon.addActionListener(this);
        
        //Campos Agregar Fondos
        lblMoneda = new JLabel("Moneda:");
        lblMoneda.setBounds(75,500,200,20);
        lblMoneda.setForeground(Color.WHITE);
        lblMoneda.setVisible(false);
        String[] divisas = {"PEN","USD","EUR","JPY","GBP","BRL"};
        cbDivisas = new JComboBox<>(divisas);
        cbDivisas.setBounds(190,500,120,20);
        cbDivisas.setVisible(false);
        cbDivisas.setMaximumRowCount(5);
        
        lblFondo = new JLabel("Ingrese el monto:");
        lblFondo.setBounds(75,540,100,20);
        lblFondo.setForeground(Color.WHITE);
        lblFondo.setVisible(false);
        tfFondo = new JTextField();
        tfFondo.setBounds(190,540,120,20);
        tfFondo.setVisible(false);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(210,580,110,20);
        btnGuardar.setBackground(new Color(252,152,53));
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGuardar.setVisible(false);
        btnGuardar.addActionListener(this);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(80,580,110,20);
        btnCancelar.setBackground(new Color(252,152,53));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.setVisible(false);
        btnCancelar.addActionListener(this);
        
        frame.add(lblBilletera);
        frame.add(scrollPaneDiv);
        frame.add(scrollPaneCripto);
        frame.add(btnCripto);
        frame.add(btnDiv);
        frame.add(btnAgregarFon);
        frame.add(lblMoneda);
        frame.add(cbDivisas);
        frame.add(lblFondo);
        frame.add(tfFondo);
        frame.add(btnGuardar);
        frame.add(btnCancelar);
        
        frame.setSize(410,720);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnCripto)
        {
            scrollPaneDiv.setVisible(false);
            scrollPaneCripto.setVisible(true);
            btnCripto.setVisible(false);
            btnDiv.setVisible(true);
            btnAgregarFon.setBounds(75,370,135,20);
        }
        if(e.getSource() == btnDiv)
        {
            scrollPaneDiv.setVisible(true);
            scrollPaneCripto.setVisible(false);
            btnCripto.setVisible(true);
            btnDiv.setVisible(false);
            btnAgregarFon.setBounds(75,450,135,20);
        }
        if(e.getSource() == btnAgregarFon)
        {
            lblMoneda.setVisible(true);
            cbDivisas.setVisible(true);
            lblFondo.setVisible(true);
            tfFondo.setVisible(true);
            btnGuardar.setVisible(true);
            btnCancelar.setVisible(true);
        }
        if(e.getSource() == btnGuardar || e.getSource() == btnCancelar)
        {
            lblMoneda.setVisible(false);
            cbDivisas.setVisible(false);
            lblFondo.setVisible(false);
            tfFondo.setVisible(false);
            btnGuardar.setVisible(false);
            btnCancelar.setVisible(false);
        }
    }
}
