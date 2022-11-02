package Model;

public class Transaccion
{
    private int codUsuario;
    private String fechaTransaccion;
    private double monedaPrim;
    private double monedaFin;
    private String ordenDivisas;

    public Transaccion(int codUsuario, String fechaTransaccion, double monedaPrim, double monedaFin, String ordenDivisas)
    {
        this.fechaTransaccion = fechaTransaccion;
        this.codUsuario = codUsuario;
        this.monedaPrim = monedaPrim;
        this.monedaFin = monedaFin;
        this.ordenDivisas = ordenDivisas;
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

    public double getMonedaPrim() {
        return monedaPrim;
    }
    public void setMonedaPrim(double monedaPrim) {
        this.monedaPrim = monedaPrim;
    }
    public double getMonedaFin() {
        return monedaFin;
    }
    public void setMonedaFin(double monedaFin) {
        this.monedaFin = monedaFin;
    }
    public String getOrdenDivisas() {
        return ordenDivisas;
    }
    public void setOrdenDivisas(String ordenDivisas) {
        this.ordenDivisas = ordenDivisas;
    }
}
