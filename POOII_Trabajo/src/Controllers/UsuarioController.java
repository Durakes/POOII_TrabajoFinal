
package Controllers;

import View.VistaLogin;
import View.VistaRegistro;
import View.VistaPerfil;
import View.VistaPerfilAdmin;
import Model.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UsuarioController implements ActionListener
{
    VistaLogin vistaLogin;
    VistaRegistro vistaRegistro;
    VistaPerfil vistaPerfil;
    VistaPerfilAdmin vistaPerfilAdmin;
    Usuario usuario;
    public UsuarioController(VistaLogin vistaLogin, Usuario usuario) 
    {
        this.vistaLogin = vistaLogin;
        this.usuario = usuario;
        
        this.vistaLogin.btnLogin.addActionListener(this);
        this.vistaLogin.btnRegistrar.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        try 
        {
            if(evt.getSource() == vistaLogin.btnLogin)
            {
                vistaPerfil = new VistaPerfil();
                vistaLogin.frame.dispose();
            }
            if(evt.getSource() == vistaLogin.btnRegistrar)
            {
                vistaRegistro = new VistaRegistro();
                vistaLogin.frame.dispose();
            }
        } catch (Exception ex) {
        }
    }
    
}
