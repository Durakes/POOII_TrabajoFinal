package Model;

public class Billetera
{
    private int codUsuario;
    public double cantidades[] = new double[6];

    //private double penCantidad,usdCantidad,eurCantidad,jpyCantidad,gbpCantidad,cadCantidad,audCantidad,mxnCantidad,nzdCantidad;

    public Billetera(int codUsuario, double[] cantidades) {
        this.codUsuario = codUsuario;
        this.cantidades = cantidades;
    }
    public Billetera(int codUsuario)
    {
        this.codUsuario = codUsuario;
        for(int i = 0; i < 6; i++)
        {
            cantidades[i] = 0.0;
        }
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public double[] getCantidades() {
        return cantidades;
    }
    public void setCantidades(double[] cantidades) {
        this.cantidades = cantidades;
    }
}
