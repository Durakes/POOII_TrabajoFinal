
package Model;


public class Usuario 
{
    private int codUsuario;
    private String user;
    private String password;
    private int tipo;

    public Usuario(int codUsuario, String user, String password, int tipo)
    {
        this.codUsuario = codUsuario;
        this.user = user;
        this.password = password;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
