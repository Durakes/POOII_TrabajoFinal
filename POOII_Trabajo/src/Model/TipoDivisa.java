package Model;

public class TipoDivisa
{
    String fecha;
    Double cambioCompra;
    Double cambioVenta;
        
    public TipoDivisa(){}
    
    public TipoDivisa(Double cambioCompra, Double cambioVenta, String fecha) {
        this.cambioCompra = cambioCompra;
        this.cambioVenta = cambioVenta;
        this.fecha = fecha;
    }

    public Double getCambioCompra() {return cambioCompra;}
    public void setCambioCompra(Double cambioCompra) {this.cambioCompra = cambioCompra;}
    
    public Double getCambioVenta() {return cambioVenta;}
    public void setCambioVenta(Double cambioVenta) {this.cambioVenta = cambioVenta;}
    
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}

}
