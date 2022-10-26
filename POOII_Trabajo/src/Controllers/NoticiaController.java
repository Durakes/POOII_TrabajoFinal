/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Model.Noticia;
import View.VistaNoticia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lizdi
 */
public class NoticiaController implements ActionListener {
    private VistaNoticia vista;
    private Noticia noticia;

    public NoticiaController(VistaNoticia vista, Noticia noticia) {
        this.vista = vista;
        this.noticia = noticia;
        iniciarBotones();
    }

    public String getTitulos() {
        return noticia.getTitulo();
    }

    
    public void setTitulos(String titulo) {
        noticia.setTitulo(titulo);
    }

    public String getAutores() {
        return noticia.getAutor();
    }

    public void setAutores(String autor) {
        noticia.setAutor(autor);
    }

    public String getLogos() {
        return noticia.getLogo();
    }

    
    public void setLogos(String logo) {
        noticia.setLogo(logo);
    }

    public String getImagenes() {
        return noticia.getImagen();
    }

  
    public void setImagenes(String imagen) {
        noticia.setImagen(imagen);
    }

   
    public String getResumenes() {
        return noticia.getResumen();
    }

   
    public void setResumenes(String resumen) {
        noticia.setResumen(resumen);
    }

    public String getLinks() {
        return noticia.getLink();
    }

    public void setLinks(String link) {
        noticia.setLink(link);
    }

    public int getAños() {
        return noticia.getAño();
    }

    public void setAños(int año) {
        noticia.setAño(año);
    }

    public int getMeses() {
        return noticia.getMes();
    }

    public void setMeses(int mes) {
        noticia.setMes(mes);
    }
    
    public void iniciarBotones(){
        for(int i=0; i<10; i++){
            final int final_i = i;
            String comando;
            comando="Editar"+i;
            vista.editar[i].addActionListener(e->vista.titulo[final_i].setText("paso"));
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
