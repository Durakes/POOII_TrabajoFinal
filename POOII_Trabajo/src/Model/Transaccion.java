package Model;

public class Transaccion
{
    private int codUsuario;
    private String fechaTransaccion;
    private String modoTransaccion;
    private String moneda;
    private Double monto;
    private String entidad;

    public Transaccion(int codUsuario, String fechaTransaccion, String modoTransaccion, String moneda, Double monto,String entidad) {
        this.codUsuario = codUsuario;
        this.fechaTransaccion = fechaTransaccion;
        this.modoTransaccion = modoTransaccion;
        this.moneda = moneda;
        this.monto = monto;
        this.entidad = entidad;
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }
    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    public String getModoTransaccion() {
        return modoTransaccion;
    }
    public void setModoTransaccion(String modoTransaccion) {
        this.modoTransaccion = modoTransaccion;
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
    public String getEntidad() {
        return entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
}
