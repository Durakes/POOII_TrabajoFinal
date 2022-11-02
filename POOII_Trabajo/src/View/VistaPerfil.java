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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaPerfil implements ActionListener {
    public JFrame frame;
    public JButton btnEditarP, btnProfAdmin, btnTodasOp, btnBilletera, btnAtras;
    public JLabel lblOpRec, lblUsuario, lblLogo, lblDivisaP;
    public DefaultTableModel modeloTabla = new DefaultTableModel();
    public JTable tblOpRec;
    public JScrollPane scrollPane;
    String operacionesRec [][] = { {"20/10","352","674","USD/PEN"},    
                                {"20/10","352","674","USD/PEN"},    
                                {"20/10","352","674","USD/PEN"},{"20/10","352","674","USD/PEN"}};
    public String cabecera[] = {"Fecha","Monto cambiado","Monto recibido","Monedas"}; /* Agregar PEN/USD Y FECHA */
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public VistaPerfil() throws MalformedURLException
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));
        
        /*********Provisional********/
        btnProfAdmin = new JButton("admin");
        btnProfAdmin.setBounds(50,5,80,20);
        btnProfAdmin.addActionListener(this);
        /*******Fin Provisional*****/

        btnAtras = new JButton("atras");
        btnAtras.setBounds(300,5,80,20);

        
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
        lblUsuario = new JLabel();
        lblUsuario.setBounds(180,35,200,30);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 23));
        
        lblDivisaP = new JLabel("Divisa principal: ");
        lblDivisaP.setBounds(180,80,200,20);
        lblDivisaP.setForeground(Color.WHITE);
        lblDivisaP.setFont(new Font("Arial", Font.PLAIN, 15));
        
        //Boton editar
        btnEditarP = new JButton("Editar perfil");
        btnEditarP.setBounds(180,120,150,20);
        btnEditarP.setBackground(new Color(252,152,53));
        btnEditarP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditarP.addActionListener(this);
        
        //Operaciones recientes
        lblOpRec = new JLabel("Operaciones recientes");
        lblOpRec.setBounds(125,180,200,20);
        lblOpRec.setForeground(Color.WHITE);
        lblOpRec.setFont(new Font("Arial", Font.PLAIN, 15));
        
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
        scrollPane.setBounds(50,210,300,303);
        scrollPane.setBackground(Color.red);
        
        //Boton ver todas las operaciones
        btnTodasOp = new JButton();
        btnTodasOp.setText("<html><center>"+"Ver todas las"+"<br>"+"operaciones"+"</center></html>");
        btnTodasOp.setBounds(210,530,140,40);
        btnTodasOp.setBackground(new Color(252,152,53));
        btnTodasOp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTodasOp.addActionListener(this);
        
        //Boton billetera
        btnBilletera = new JButton("Billetera");
        btnBilletera.setBounds(50,530,100,40);
        btnBilletera.setBackground(new Color(252,152,53));
        btnBilletera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBilletera.addActionListener(this);
        
        frame.add(btnAtras);
        frame.add(lblLogo);
        frame.add(lblUsuario);
        frame.add(lblDivisaP);
        frame.add(btnEditarP);
        frame.add(lblOpRec);
        frame.add(scrollPane);
        frame.add(btnProfAdmin);
        frame.add(btnTodasOp);
        frame.add(btnBilletera);
        
        frame.setSize(410,620);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        try {
            if(e.getSource() == btnEditarP)
            {
                VistaEditarPerfil obj = new VistaEditarPerfil();
            }
            if(e.getSource() == btnProfAdmin)
            {
                VistaPerfilAdmin obj = new VistaPerfilAdmin();
            }
            
            if(e.getSource() == btnTodasOp)
            {
                VistaHistorialUser obj = new VistaHistorialUser();
            }
            
        } catch (Exception ex) {
        }
    }
}
