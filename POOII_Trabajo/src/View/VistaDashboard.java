package View;
import javax.swing.*;
import javax.swing.border.Border;
import Helpers.PaintChart;
import java.awt.Color;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class VistaDashboard
{
    public JFrame frame;
    public JButton buttonDivisas, buttonNoticias;
    public JButton buttonTipoDivisas[] = new JButton[8];
    public JButton cambioCripto;
    public JLabel divisaPrincipal;
    public JButton buttonTipoCriptos[] = new JButton[4];
    public JButton perfil;
    public JButton button5d, button1m, button6m, button1y; 
    public JLabel titulo;
    public JButton registarTransaccion;
    public JTextField monedaIni, monedaFin;
    public JLabel monedaIniLabel, monedaFinLabel;
    public JButton cambioMoneda;
    public JPanel panelDivisas, panelCriptos;
    public JLabel iconoFlecha;
    public ImageIcon profileIcon,arrowIcon;
    public PaintChart chart;
    public String divisas[] = {"PEN","USD","EUR","JPY","GBP","CAD","AUD","MXN","NZD"};

    public VistaDashboard()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23,23,23));

        titulo = new JLabel("Nombre del Proyecto");
        titulo.setBounds(430,40,150,20);
        titulo.setForeground(Color.WHITE);
        
        profileIcon = new ImageIcon("src/images/offline_user.png");
        Image profileImage = profileIcon.getImage();
        Image newImage = profileImage.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newImage);

        perfil = new JButton();
        perfil.setIcon(profileIcon);
        perfil.setBounds(880,20,65,70);
        perfil.setText("Perfil");
        perfil.setBackground(new Color(23,23,23));
        perfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        perfil.setForeground(Color.WHITE);
        perfil.setIconTextGap(2);
        perfil.setVerticalTextPosition(SwingConstants.BOTTOM);
        perfil.setHorizontalTextPosition(SwingConstants.CENTER);
        
        buttonDivisas = new JButton("Cambio de divisas");
        buttonDivisas.setBounds(20,95,450,20);
        buttonDivisas.setBackground(new Color(252,152,53));
        buttonDivisas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonNoticias = new JButton("Noticias");
        buttonNoticias.setBounds(490,95,450,20);
        buttonNoticias.setBackground(new Color(252,152,53));
        buttonNoticias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelDivisas = new JPanel();
        panelDivisas.setBackground(new Color(61,61,61));
        panelDivisas.setBounds(40,172,170,597);

        int yPosDiv = 20;

        for(int i = 0; i < 8; i++)
        {
            buttonTipoDivisas[i] = new JButton(divisas[i+1]);
            buttonTipoDivisas[i].setBounds(10, yPosDiv, 150, 60);
            buttonTipoDivisas[i].setBackground(new Color(252,152,53));
            buttonTipoDivisas[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            yPosDiv+=70;

            panelDivisas.add(buttonTipoDivisas[i]);
        }
        panelDivisas.setLayout(null);
        panelDivisas.setVisible(true);

        panelCriptos = new JPanel();
        panelCriptos.setBackground(new Color(61,61,61));
        panelCriptos.setBounds(40,172,170,597);
        panelCriptos.setLayout(null);

        int yPosCripto = 150;

        for(int i = 0; i < 4; i++)
        {
            buttonTipoCriptos[i] = new JButton("BTC 3.98");
            buttonTipoCriptos[i].setBounds(10, yPosCripto, 150, 60);
            buttonTipoCriptos[i].setBackground(new Color(252,152,53));
            buttonTipoCriptos[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            yPosCripto+=70;

            panelCriptos.add(buttonTipoCriptos[i]);
        }
        panelCriptos.setVisible(false);

        cambioCripto = new JButton("Criptomonedas");
        cambioCripto.setBounds(50, 780, 150, 40);

        divisaPrincipal = new JLabel("PEN");
        divisaPrincipal.setBounds(100, 150, 150, 20);
        divisaPrincipal.setForeground(Color.WHITE);

        monedaIniLabel = new JLabel("PEN");
        monedaIniLabel.setBounds(350, 690, 150, 40);
        monedaIniLabel.setForeground(Color.WHITE);

        monedaFinLabel = new JLabel("USD");
        monedaFinLabel.setBounds(730, 690, 150, 40);
        monedaFinLabel.setForeground(Color.WHITE);

        monedaIni = new JTextField();
        monedaIni.setBounds(330,730,100,20);

        monedaFin = new JTextField();
        monedaFin.setBounds(700,730,100,20);

        arrowIcon = new ImageIcon("src/images/right-arrow.png");

        iconoFlecha = new JLabel();
        iconoFlecha.setIcon(arrowIcon);
        iconoFlecha.setBounds(550,640,200,200);
        
        registarTransaccion = new JButton("Registrar");
        registarTransaccion.setBounds(490,790,150,20);
        registarTransaccion.setBackground(new Color(252,152,53));
        registarTransaccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        cambioMoneda = new JButton("Cambiar");
        cambioMoneda.setBounds(810,730,100,20);
        cambioMoneda.setBackground(new Color(252,152,53));
        cambioMoneda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button5d = new JButton("5 Días");
        button5d.setBounds(320,180,100,20);

        button1m = new JButton("1 Mes");
        button1m.setBounds(470,180,100,20);
        
        button6m = new JButton("6 Meses");
        button6m.setBounds(620,180,100,20);

        button1y = new JButton("1 Año");
        button1y.setBounds(770,180,100,20);

        ArrayList<Double> scores = new ArrayList<>();
        chart = new PaintChart(scores);
        chart.setBounds(300, 215, 600, 450);
        
        frame.add(button5d);
        frame.add(button1m);
        frame.add(button6m);
        frame.add(button1y);
        frame.add(chart);
        frame.add(panelCriptos);
        frame.add(panelDivisas);
        frame.add(iconoFlecha);
        frame.add(cambioMoneda);
        frame.add(registarTransaccion);
        frame.add(monedaIni);
        frame.add(monedaFin);
        frame.add(monedaIniLabel);
        frame.add(monedaFinLabel);
        frame.add(divisaPrincipal);
        frame.add(cambioCripto);
        frame.add(buttonDivisas);
        frame.add(buttonNoticias);
        frame.add(perfil);
        frame.add(titulo);
        frame.setSize(980, 890);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
