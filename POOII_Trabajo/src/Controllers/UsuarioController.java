
package Controllers;

import View.VistaDashboard;
import View.VistaLogin;
import View.VistaRegistro;
import View.VistaPerfil;
import View.VistaPerfilAdmin;
import Model.Billetera;
import Model.Usuario;
import Controllers.AccesoArchivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;   
import javax.swing.Action;
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
    public UsuarioController() 
    {
        
        try 
        {this.vistaLogin = new VistaLogin();
        }catch(Exception e)
        {

        }
        this.usuario = new Usuario();
        this.objArchivo = new AccesoArchivo();
        
        vistaLogin.btnLogin.addActionListener(this);
        vistaLogin.btnRegistrar.addActionListener(this);
        
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

    public Usuario verificarLogin(String user, String passw)
    {
        Boolean bandera = false;
        for(Usuario usuario : objArchivo.arrayUsuarios)
        {
            if(user.equals(usuario.getUser()) && passw.equals(String.valueOf(usuario.getPassword())))
            {
                bandera = true;
                return usuario;
            }
        }
        return null;
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        try 
        {
            if(evt.getSource() == vistaLogin.btnLogin)
            {
                String user = vistaLogin.tfUsuario.getText();
                String passw = String.valueOf(vistaLogin.pwContrasena.getPassword());
                
                Usuario usuario = verificarLogin(user, passw);
                if (usuario != null) 
                {
                    divisaController = new DivisaController(usuario);
                    //vistaPerfil.lblUsuario.setText(user);
                    this.vistaLogin.frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(vistaLogin.frame,"Usuario o contraseña incorrectos");
                }
                
            }
            if(evt.getSource() == vistaLogin.btnRegistrar)
            { 
                vistaRegistro = new VistaRegistro();
                vistaLogin.frame.dispose();
                vistaRegistro.btnRegistrar.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        int codigo = getUltCodigo();
                        String user = vistaRegistro.tfUsuario.getText();
                        String passw = String.valueOf(vistaRegistro.pwConfirmaContra.getPassword());
                        int tipo = 1;
                        
                        Usuario nUsuario = new Usuario(codigo,user,passw,tipo);
                        objArchivo.registrarUsuario(nUsuario);
                        try
                        {
                            //! Revisar redireccion a Dashboard despues de registrado.
                            vistaPerfil = new VistaPerfil();
                            vistaPerfil.lblUsuario.setText(user);
                        } catch (Exception exec)
                        {

                        }

                        try
                        {
                            FileWriter fw = new FileWriter("src/db/billetera.csv", true);
                            PrintWriter pw = new PrintWriter(fw);
                            
                            Billetera billetera = new Billetera(nUsuario.getCodUsuario());

                            String registroBilletera = String.valueOf(billetera.getCodUsuario());
                            
                            for (int i = 0; i< 9; i++)
                            {
                                registroBilletera += ";" + String.valueOf(billetera.getCantidades()[i]);
                            }

                            pw.append(registroBilletera + "\n");
                            pw.close();
                        } catch (Exception exception) {
                            System.out.println("error:"+exception.toString());
                        }
                    }
                });
            }
        } catch (Exception ex) {
        }
    }
    
}
