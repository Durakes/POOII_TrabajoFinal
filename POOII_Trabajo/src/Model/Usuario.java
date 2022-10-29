
package Model;


public class Usuario 
{
    private String user;
    private String password;
    private String tipo;
    private String idDivisa;

    public Usuario(String user, String password, String tipo, String idDivisa) {
        this.user = user;
        this.password = password;
        this.tipo = tipo;
        this.idDivisa = idDivisa;
    }

    public Usuario() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(String idDivisa) {
        this.idDivisa = idDivisa;
    }
    
    
    
}
