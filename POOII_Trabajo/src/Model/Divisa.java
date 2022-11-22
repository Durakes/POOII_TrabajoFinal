package Model;

public class Divisa
{
    private String fecha;
    private double tasasDeCambio[];
    
    public Divisa()
    {
        tasasDeCambio = new double[5];
    }

    public Divisa(String fecha, double tasas[]) {
        this.fecha = fecha;
        this.tasasDeCambio = tasas;
    }

    public double[] getTasasDeCambio() {
        return tasasDeCambio;
    }

    public void setTasasDeCambio(double[] tasasDeCambio) {
        this.tasasDeCambio = tasasDeCambio;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
