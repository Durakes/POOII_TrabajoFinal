
package Controllers;

import View.VistaDashboard;
import View.VistaLogin;
import View.VistaRegistro;
import View.VistaPerfil;
import View.VistaPerfilAdmin;
import Model.Usuario;
import Controllers.AccesoArchivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
public class UsuarioController implements ActionListener
{
    VistaLogin vistaLogin;
    VistaRegistro vistaRegistro;
    VistaPerfil vistaPerfil;
    VistaPerfilAdmin vistaPerfilAdmin;
    VistaDashboard vistaDashboard;
    DivisaController divisaController;
    Usuario usuario;
    AccesoArchivo objArchivo;
    public UsuarioController(VistaLogin vistaLogin, Usuario usuario, AccesoArchivo objArchivo) 
    {
        this.vistaLogin = vistaLogin;
        this.usuario = usuario;
        this.objArchivo = objArchivo;
        
        this.vistaLogin.btnLogin.addActionListener(this);
        this.vistaLogin.btnRegistrar.addActionListener(this);
    }
    
    public int getUltCodigo()
    {
        int codigo;
        int n = objArchivo.arrayUsuarios.size();
        if(n!=0)
        {
            codigo = objArchivo.arrayUsuarios.get(n-1).getCodUsuario() + 1;
        }
        else
        {
            codigo = 1;
        }
        return codigo;
    }

    public boolean verificarLogin(String user, String passw)
    {
        Boolean bandera = false;
        for(Usuario usuario : objArchivo.arrayUsuarios)
        {
            if(user.equals(usuario.getUser()) && passw.equals(String.valueOf(usuario.getPassword())))
            {
                bandera = true;
                return bandera;
            }
        }
        return bandera;
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        try 
        {
            if(evt.getSource() == vistaLogin.btnLogin)
            {
                String user = vistaLogin.tfUsuario.getText();
                String passw = String.valueOf(vistaLogin.pwContrasena.getPassword());
                
                Boolean verifica = verificarLogin(user, passw);
                if (verifica == true) 
                {
                    divisaController = new DivisaController();
                    //vistaPerfil.lblUsuario.setText(user);
                    vistaLogin.frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(vistaLogin.frame,"Usuario o contraseña incorrectos");
                }
                
            }
            if(evt.getSource() == vistaLogin.btnRegistrar)
            { 
                vistaRegistro = new VistaRegistro();
                vistaRegistro.btnRegistrar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int codigo = getUltCodigo();
                        String user = vistaRegistro.tfUsuario.getText();
                        String passw = String.valueOf(vistaRegistro.pwConfirmaContra.getPassword());
                        String divisa = vistaRegistro.cbDivisas.getItemAt(vistaRegistro.cbDivisas.getSelectedIndex()).toString();
                        int tipo = 1;
                        
                        Usuario nUsuario = new Usuario(codigo,user,passw,tipo,divisa);
                        objArchivo.registrarUsuario(nUsuario);
                        try {
                            vistaPerfil = new VistaPerfil();
                            vistaPerfil.lblUsuario.setText(user);
                        } catch (Exception exec) {
                        }
                        
                        
                    }
                });
                vistaLogin.frame.dispose();
            }
        } catch (Exception ex) {
        }
    }
    
}
