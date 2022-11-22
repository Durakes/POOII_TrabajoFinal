package Model;
public class Solicitud
{
    int idSolicitud;
    String nomUsuario;
    int codUsuario;
    String tipoOperacion;
    String moneda;
    Double monto;
    Double tipoCambio;
    Boolean estado;

    public Solicitud(int id,String nomUsuario,int codUsuario, String tipoOperacion, String moneda, Double monto,Double tipoCambio, Boolean estado) {
        this.idSolicitud = id;
        this.nomUsuario = nomUsuario;
        this.codUsuario = codUsuario;
        this.tipoOperacion = tipoOperacion;
        this.moneda = moneda;
        this.monto = monto;
        this.tipoCambio = tipoCambio;
        this.estado = estado;
    }
    public int getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    public Double getTipoCambio() {
        return tipoCambio;
    }
    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    public String getNomUsuario() {
        return nomUsuario;
    }
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}