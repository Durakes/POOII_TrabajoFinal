/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pooii_trabajo;

import Controllers.NoticiaController;
import Model.Noticia;
import View.VistaNoticia;
import java.io.IOException;
import View.VistaLogin;
import Model.Usuario;
import Controllers.UsuarioController;
import Controllers.AccesoArchivo;

/**
 *
 * @author apa16
 */
public class POOII_Trabajo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Noticia modelo=new Noticia();
        VistaNoticia vista=new VistaNoticia();
        NoticiaController controlador=new NoticiaController(vista, modelo);*/
        
        
        Usuario modeloUsuario = new Usuario();
        VistaLogin login = new VistaLogin();
        AccesoArchivo archivo = new AccesoArchivo();
        UsuarioController controladorUsuario = new UsuarioController(login, modeloUsuario,archivo);
        
    }
    
}
