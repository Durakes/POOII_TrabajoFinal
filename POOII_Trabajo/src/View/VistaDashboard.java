package View;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.Image;

public class VistaDashboard {
    public JFrame frame;
    /* Header */
    public JLabel labelTitulo;
    public JButton botonDivisas, botonNoticias, botonPerfil, botonNotif;
    public ImageIcon iconoPerfil, iconoNotif;

    /* Side Bar */
    public JPanel panelDivisas;
    public JLabel labelDivisaPrincipal;
    public JButton botonCompraVenta;
    public JButton botonTipoDivisas[] = new JButton[5];

    /* Opciones Principales */
    public JLabel labelModoActivo, labelUsuarioNotif;
    public JLabel labelBancos[] = new JLabel[3];
    public JButton botonBancos[] = new JButton[3];
    public JButton botonUsuario;

    /* Registro */
    public ImageIcon iconoFlecha;
    public JLabel labelMonedaExtranjera, labelMonedaNacional, labelFlecha, labelBancoSeleccionado;
    public JTextField textMonedaExtranjera, textMonedaNacional;
    public JButton botonRegistarTransaccion;

    /* Adicionales */
    public String divisas[] = { "USD", "EUR", "JPY", "GBP", "BRL" };
    public String bancos[] = { "BCP", "BBVA", "INTERBANK" };

    public VistaDashboard() {

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(23, 23, 23));

        /* Header */
        labelTitulo = new JLabel("MONEY HOUSE");
        labelTitulo.setBounds(430, 40, 150, 20);
        labelTitulo.setForeground(Color.WHITE);

        iconoPerfil = new ImageIcon("src/images/offline_user.png");
        Image profileImage = iconoPerfil.getImage();
        Image newImage = profileImage.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
        iconoPerfil = new ImageIcon(newImage);

        iconoNotif = new ImageIcon("src/images/campana.png");
        Image notifImage = iconoNotif.getImage();
        Image nuevaImagen = notifImage.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
        iconoNotif = new ImageIcon(nuevaImagen);

        botonNotif = new JButton();
        botonNotif.setIcon(iconoNotif);
        botonNotif.setBounds(800, 20, 60, 70);
        botonNotif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonNotif.setBackground(new Color(23,23,23));

        botonPerfil = new JButton();
        botonPerfil.setIcon(iconoPerfil);
        botonPerfil.setBounds(880, 20, 65, 70);
        // ? perfil.setText("Perfil"); Verificar si poner el text
        botonPerfil.setBackground(new Color(23, 23, 23));
        botonPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonPerfil.setForeground(Color.WHITE);
        botonPerfil.setIconTextGap(2);
        botonPerfil.setVerticalTextPosition(SwingConstants.BOTTOM);
        botonPerfil.setHorizontalTextPosition(SwingConstants.CENTER);

        botonDivisas = new JButton("Cambio de divisas");
        botonDivisas.setBounds(50, 95, 800, 20);
        botonDivisas.setBackground(new Color(252, 152, 53));
        botonDivisas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botonNoticias = new JButton("Noticias");
        botonNoticias.setBounds(490, 95, 450, 20);
        botonNoticias.setBackground(new Color(252, 152, 53));
        botonNoticias.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /* Side Bar */

        labelDivisaPrincipal = new JLabel("SOLES");
        labelDivisaPrincipal.setBounds(100, 150, 150, 20);
        labelDivisaPrincipal.setForeground(Color.WHITE);

        panelDivisas = new JPanel();
        panelDivisas.setBackground(new Color(61, 61, 61));
        panelDivisas.setBounds(50, 172, 170, 597);

        int yPosDiv = 20;

        for (int i = 0; i < 5; i++) {
            botonTipoDivisas[i] = new JButton(divisas[i]);
            botonTipoDivisas[i].setBounds(10, yPosDiv, 150, 30);
            botonTipoDivisas[i].setBackground(new Color(252, 152, 53));
            botonTipoDivisas[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            yPosDiv += 90;

            panelDivisas.add(botonTipoDivisas[i]);
        }

        botonCompraVenta = new JButton("Venta");
        botonCompraVenta.setBounds(10, yPosDiv, 150, 30);
        botonCompraVenta.setBackground(new Color(252, 152, 53));
        botonCompraVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelDivisas.add(botonCompraVenta);
        panelDivisas.setLayout(null);
        panelDivisas.setVisible(true);

        /* Opciones Principales */

        labelModoActivo = new JLabel("MODO: ");
        labelModoActivo.setBounds(450, 150, 150, 20);
        labelModoActivo.setForeground(Color.WHITE);

        int posX = 350;

        for (int i = 0; i < 3; i++) {
            labelBancos[i] = new JLabel("0.00",SwingConstants.CENTER);
            botonBancos[i] = new JButton(bancos[i]);
            
            botonBancos[i].setBounds(posX, 250, 120, 30);
            botonBancos[i].setBackground(new Color(252, 152, 53));
            botonBancos[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            labelBancos[i].setBounds(posX, 200, 120, 30);
            labelBancos[i].setOpaque(true);
            labelBancos[i].setBackground(Color.WHITE);

            posX += 170;

            frame.add(botonBancos[i]);
            frame.add(labelBancos[i]);
        }

        labelUsuarioNotif = new JLabel("Si desea enviar solicitud a otro usuario > ");
        labelUsuarioNotif.setBounds(370, 320, 400, 30);
        labelUsuarioNotif.setForeground(Color.WHITE);

        botonUsuario = new JButton("Solicitud");
        botonUsuario.setBounds(670,320,120,30);
        botonUsuario.setBackground(new Color(252, 152, 53));
        botonUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        /* Registro */

        labelBancoSeleccionado = new JLabel("Banco Seleccionado: ");
        labelBancoSeleccionado.setBounds(480, 350, 400, 300);
        labelBancoSeleccionado.setForeground(Color.WHITE);

        labelMonedaExtranjera = new JLabel("USD");
        labelMonedaExtranjera.setBounds(410, 550, 150, 30);
        labelMonedaExtranjera.setForeground(Color.WHITE);

        labelMonedaNacional = new JLabel("PEN");
        labelMonedaNacional.setBounds(700, 550, 150, 30);
        labelMonedaNacional.setForeground(Color.WHITE);

        textMonedaExtranjera = new JTextField();
        textMonedaExtranjera.setBounds(370, 600, 120, 30);

        textMonedaNacional = new JTextField();
        textMonedaNacional.setBounds(660, 600, 120, 30);

        iconoFlecha = new ImageIcon("src/images/right-arrow.png");

        labelFlecha = new JLabel();
        labelFlecha.setIcon(iconoFlecha);
        labelFlecha.setBounds(550,515,200,200);

        botonRegistarTransaccion = new JButton("Registrar");
        botonRegistarTransaccion.setBounds(500, 680, 150, 20);
        botonRegistarTransaccion.setBackground(new Color(252, 152, 53));
        botonRegistarTransaccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        frame.add(labelFlecha);
        frame.add(textMonedaExtranjera);
        frame.add(textMonedaNacional);
        frame.add(labelMonedaNacional);
        frame.add(labelMonedaExtranjera);
        frame.add(labelBancoSeleccionado);
        frame.add(botonUsuario);
        frame.add(labelUsuarioNotif);
        frame.add(botonNotif);
        frame.add(labelModoActivo);
        frame.add(panelDivisas);
        frame.add(botonRegistarTransaccion);
        frame.add(labelDivisaPrincipal);
        frame.add(botonDivisas);
        //frame.add(botonNoticias);
        frame.add(botonPerfil);
        frame.add(labelTitulo);
        frame.setSize(980, 890);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
