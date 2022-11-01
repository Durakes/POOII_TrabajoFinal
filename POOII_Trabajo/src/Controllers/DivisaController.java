package Controllers;
import Model.Noticia;
import Model.Usuario;
import Model.Divisa;
import Helpers.PaintChart;
import View.VistaDashboard;
import View.VistaPerfil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.*;

import Helpers.PaintChart;

import java.time.*;
import java.security.Key;

public class DivisaController implements ActionListener
{
    private ArrayList<Divisa> divisaRate = new ArrayList<>();
    VistaDashboard vDashboard;
    VistaPerfil vPerfil;
    double exchangeRate;
    double exchangeRateInv;
    ArrayList<Double> ratesGraph;
    public DivisaController()
    {
        vDashboard = new VistaDashboard();
        cargarTipoCambio();
        vDashboard.monedaIni.addActionListener(this);
        vDashboard.cambioMoneda.addActionListener(this);
        exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
        exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
        vDashboard.registarTransaccion.addActionListener(this);
        vDashboard.cambioCripto.addActionListener(this);

        ratesGraph = new ArrayList<>();
        for(int i = 5; i > 0; i--)
        {
            ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
        }

        //vDashboard.chart = new PaintChart(ratesGraph);
        vDashboard.chart.changeRates(ratesGraph);
        //vDashboard.chart.setBounds(300, 175, 600, 450);
        vDashboard.button5d.addActionListener(this);
        vDashboard.button1m.addActionListener(this);
        vDashboard.button6m.addActionListener(this);
        vDashboard.button1y.addActionListener(this);
        vDashboard.buttonTipoDivisas[3].addActionListener(this);

        for(int i = 0; i < 8; i++)
        {
            final int final_i = i;
            vDashboard.buttonTipoDivisas[i].addActionListener(this);
        }
    }

    public void cargarTipoCambio()
    {
        try 
        {
            String date;
            double firstRate, secondRate;
            BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/PEN/USD.csv"));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] temporal;
                temporal = line.split(",");
                date = temporal[0];
                firstRate = Double.parseDouble(temporal[1]);
                secondRate = Double.parseDouble(temporal[2]);
                
                Divisa objDiv = new Divisa(date,firstRate, secondRate);
                divisaRate.add(objDiv);
            }
            br.close();

        } 
        catch (Exception e) 
        {
            System.out.println("error cargarDatNoticia:"+e.toString());
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == vDashboard.monedaIni)
        {
            double result = Math.round((Double.parseDouble(vDashboard.monedaIni.getText()) * exchangeRate)*100);
            result /= 100;
            vDashboard.monedaFin.setText(String.valueOf(result));
            
        }else if(e.getSource() == vDashboard.cambioMoneda)
        {
            vDashboard.monedaIni.setText("");
            vDashboard.monedaFin.setText("");

            String tempText;
            double tempRate;
            
            tempText = vDashboard.monedaIniLabel.getText();
            vDashboard.monedaIniLabel.setText(vDashboard.monedaFinLabel.getText());
            vDashboard.monedaFinLabel.setText(tempText);
            
            tempRate = exchangeRate;
            exchangeRate = exchangeRateInv;
            exchangeRateInv = tempRate;

        }else if(e.getSource() == vDashboard.registarTransaccion) /*Agregar id usuario*/
        {
            try
            {
                FileWriter fw = new FileWriter("src/db/registros.csv", true);
                PrintWriter pw = new PrintWriter(fw);
                
                //Agregar un registro
                String registro = String.valueOf(LocalDate.now())+ ";" + vDashboard.monedaIni.getText() + ";" + vDashboard.monedaFin.getText() + ";" + vDashboard.monedaIniLabel.getText() + "/" + vDashboard.monedaFinLabel.getText() + "\n";
                pw.append(registro);
                pw.close();

            } catch (Exception ex)
            {
                System.out.println("error:"+ex.toString());
            }
        }else if(e.getSource() == vDashboard.cambioCripto)
        {
            if(vDashboard.cambioCripto.getText() == "Criptomonedas")
            {
                vDashboard.cambioCripto.setText("Divisas");
                vDashboard.panelDivisas.setVisible(false);
                vDashboard.panelCriptos.setVisible(true);
            }else if(vDashboard.cambioCripto.getText() == "Divisas")
            {
                vDashboard.cambioCripto.setText("Criptomonedas");
                vDashboard.panelDivisas.setVisible(true);
                vDashboard.panelCriptos.setVisible(false);
            }
        }else if(e.getSource() == vDashboard.button5d)
        {
            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button1m)
        {
            ratesGraph.clear();
            for(int i = 30; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button6m)
        {
            ratesGraph.clear();
            for(int i = 180; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.button1y)
        {
            ratesGraph.clear();
            for(int i = 360; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);
        }else if(e.getSource() == vDashboard.perfil)
        {
            try
            {
                vPerfil = new VistaPerfil();
                vDashboard.frame.dispose();
            }catch(Exception exception)
            {

            }
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[0])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[0].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();

            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

        }else if(e.getSource() == vDashboard.buttonTipoDivisas[0])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[0].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }
        else if(e.getSource() == vDashboard.buttonTipoDivisas[1])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[1].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
        }else if(e.getSource() == vDashboard.buttonTipoDivisas[2])
        {
            String monedaPrincipal = vDashboard.divisaPrincipal.getText();
            String divisaACambio = vDashboard.buttonTipoDivisas[2].getText();
            divisaRate.clear();
            try 
            {
                String date;
                double firstRate, secondRate;
                BufferedReader br = new BufferedReader(new FileReader("src/db/Divisas/" + monedaPrincipal + "/" + divisaACambio + ".csv"));
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] temporal;
                    temporal = line.split(",");
                    date = temporal[0];
                    firstRate = Double.parseDouble(temporal[1]);
                    secondRate = Double.parseDouble(temporal[2]);

                    Divisa objDiv = new Divisa(date,firstRate, secondRate);
                    divisaRate.add(objDiv);
                }
                br.close();
            } 
            catch (Exception exception) 
            {
                System.out.println("error cargarDatNoticia:"+e.toString());
            }

            ratesGraph.clear();
            for(int i = 5; i > 0; i--)
            {
                ratesGraph.add(divisaRate.get(divisaRate.size()-i).getExchangeRate());
            }
            vDashboard.chart.changeRates(ratesGraph);

            exchangeRate = divisaRate.get(divisaRate.size()-1).getExchangeRate();
            exchangeRateInv = divisaRate.get(divisaRate.size()-1).getExchangeRateInv();
            
            vDashboard.monedaIniLabel.setText(monedaPrincipal);
            vDashboard.monedaFinLabel.setText(divisaACambio);
            vDashboard.monedaIni.repaint();
        }
    }
}
