/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author apa16
 */
public class Noticia {
    private String titulo;
    private String autor;
    private String logo;
    private String imagen;
    private String resumen;
    private String link;
    private int año;
    private int mes;

    public Noticia() {
    }

    public Noticia(String titulo, String autor, String logo, String imagen, String resumen, String link, int año, int mes) {
        this.titulo = titulo;
        this.autor = autor;
        this.logo = logo;
        this.imagen = imagen;
        this.resumen = resumen;
        this.link = link;
        this.año = año;
        this.mes = mes;
    }

    
    public String getTitulo() {
        return titulo;
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLogo() {
        return logo;
    }

    
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImagen() {
        return imagen;
    }

  
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

   
    public String getResumen() {
        return resumen;
    }

   
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }


    public int getMes() {
        return mes;
    }


    public void setMes(int mes) {
        this.mes = mes;
    }
}
