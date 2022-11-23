package Controllers;

import Model.Noticia;
import View.VistaNoticia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoticiaController implements ActionListener
{
    private VistaNoticia vista;
    private Noticia noticia;
    private AccesoArchivo acceder;
    

    public NoticiaController(VistaNoticia vista, Noticia noticia) throws IOException {
        this.vista = vista;
        this.noticia = noticia;
        acceder=new AccesoArchivo();
        ejecutarArray();
        vista.agregar.addActionListener(this);
        vista.agregar.setActionCommand("Agregar");
        vista.buscar.addActionListener(this);
        vista.buscar.setActionCommand("buscar");
    }

    public int getCodigo(){
        return noticia.getCod();
    }
    
    public void getCodigo(int cod){
        noticia.setCod(cod);
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
    
    public void ejecutarArray(){
        if(!acceder.arrayNoticias.isEmpty()){
            try{
                vista.visualizar(acceder.arrayNoticias);
                for(int i=0; i<10; i++){
                    final int final_i = i;
                }
            }catch(IOException ex){System.out.println("Error: " + ex.getMessage());}
            
            
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if("Agregar".equals(e.getActionCommand()))
        {
            vista.VistaAgregar();
            //tituloAgregar, logoAgregar, imagenAgregar, linkAgregar, resumenAgregar;
            
            
            vista.BGuardar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int codigo=0;
                    if(!acceder.arrayNoticias.isEmpty()){
                        int cod=acceder.arrayNoticias.size()-1;
                        codigo=acceder.arrayNoticias.get(cod).getCod()+1;
                    }
                    
                    String tituloAgregado=vista.tituloAgregar.getText();
                    noticia.setCod(codigo);
                    String autorAgregado=vista.autorAgregar.getText();
                    noticia.setAutor(autorAgregado);
                    String logoAgregado=vista.logoAgregar.getText();
                    noticia.setLogo(logoAgregado);
                    String imagenAgregado=vista.imagenAgregar.getText();
                    noticia.setImagen(imagenAgregado);
                    String linkAgregado=vista.linkAgregar.getText();
                    noticia.setLink(linkAgregado);
                    String resumenAgregado=vista.resumenAgregar.getText();
                    noticia.setResumen(resumenAgregado);
                    Date fechaAgregada = new Date();
                    int añoAgregado=fechaAgregada.getYear()+1900;
                    noticia.setAño(añoAgregado);
                    int mesAgregado=fechaAgregada.getMonth()+1;
                    noticia.setMes(mesAgregado);
                    
                    acceder.MandarInformacionAArchivoNoticias(codigo, tituloAgregado, autorAgregado, logoAgregado, imagenAgregado, linkAgregado, resumenAgregado, añoAgregado, mesAgregado);
                    vista.VistaAgregarNoticia.dispose();
                    acceder.arrayNoticias.clear();
                    System.out.println(acceder.arrayNoticias.size());
                    acceder.cargarDatosArrayNoticias();
                    try {
                        vista.visualizar(acceder.arrayNoticias);
                    } catch (IOException ex) {
                        Logger.getLogger(NoticiaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
        }
    }
}
