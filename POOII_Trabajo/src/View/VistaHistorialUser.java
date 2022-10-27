package View;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaHistorialUser {
    public JFrame frame;
    public DefaultTableModel modeloTabla = new DefaultTableModel();
    public JTable tblOpRec;
    public JButton btnMostrarDat;
    public JScrollPane scrollPane;
    String operacionesRec [][] = { {"20/10","352","674"},    
                                {"20/10","352","674"},    
                                {"20/10","352","674"},{"20/10","352","674"}};
    String cabecera[] = {"Fecha","Monto cambiado","Monto recibido"};
    public DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public JComboBox<Object> cbMes, cbAnio;
    
    public VistaHistorialUser()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));

        String[] meses = {"ENERO","FEBRERO","MARZO","ARBIL","MAYO","JUNIO"};
        cbMes = new JComboBox<>(meses);
        cbMes.setBounds(310,20,100,20);
        
        String[] anios = {"2022","2021","2020","2019","2018","2017"};
        cbAnio = new JComboBox<>(anios);
        cbAnio.setBounds(450,20,100,20);
        
        btnMostrarDat = new JButton("Mostrar");
        btnMostrarDat.setBounds(390,60,100,20);
        btnMostrarDat.setBackground(new Color(252,152,53));

        //Tabla
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        modeloTabla.setDataVector(operacionesRec, cabecera);
        modeloTabla.setRowCount(8);
        tblOpRec = new JTable();
        tblOpRec.setModel(modeloTabla);
        tblOpRec.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblOpRec.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblOpRec.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        tblOpRec.setBackground(new Color(61,61,61));
        tblOpRec.setForeground(Color.white);
        tblOpRec.setRowHeight(35);
        tblOpRec.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblOpRec.getTableHeader().setOpaque(false);
        tblOpRec.getTableHeader().setBackground(new Color(252,178,30));
        tblOpRec.getTableHeader().setForeground(Color.black);
        tblOpRec.setFocusable(false);
        tblOpRec.setDefaultEditor(Object.class, null);
        scrollPane = new JScrollPane(tblOpRec);
        scrollPane.setBounds(50,110,300,303);
        scrollPane.setBackground(Color.red);
        
        
        frame.add(scrollPane);
        frame.add(cbAnio);
        frame.add(cbMes);
        frame.add(btnMostrarDat);
        
               
        frame.setSize(860,520);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
