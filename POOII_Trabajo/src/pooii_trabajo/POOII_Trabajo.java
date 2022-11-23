
package pooii_trabajo;
import View.VistaNotificaciones;

import java.io.IOException;
import View.VistaLogin;
import Model.Usuario;
import Controllers.UsuarioController;
import Controllers.AccesoArchivo;
import Controllers.DivisaController;
import View.VistaDashboard;
import java.time.*;

public class POOII_Trabajo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Noticia modelo=new Noticia();
        VistaNoticia vista=new VistaNoticia();
        NoticiaController controlador=new NoticiaController(vista, modelo);*/
        
        UsuarioController controladorUsuario = new UsuarioController();
        //VistaNotificaciones vn= new VistaNotificaciones(); 
        
        //UsuarioController controladorUsuario = new UsuarioController(login, modeloUsuario,archivo);
        //Noticia modelo=new Noticia();
        //VistaNoticia vista=new VistaNoticia();
        //NoticiaController controlador=new NoticiaController(vista, modelo);
        
        
        //Para ver flujo de usuario: 
        //VistaLogin obj = new VistaLogin();
        
        //Dashboard
        //VistaDashboard objDashboard    = new VistaDashboard();
        
        //DivisaController dv = new DivisaController();

        /*LocalDate today = LocalDate.now();
        LocalDate pastDate = LocalDate.parse("2022-10-01");
        int compareValue = today.compareTo(pastDate);
        
        System.out.println(compareValue);*/
        
    }
    
}
