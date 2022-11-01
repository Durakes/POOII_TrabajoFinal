package Model;

public class Divisa
{
    private String date;
    private double exchangeRate;
    private double exchangeRateInv;
    private double maxVariation;
    private double minVariation;
    private double promVariation;
    private double dayVariaton;
    
    public Divisa(String date, double exchangeRate,double exchangeRateInv)
    {
        this.date = date;
        this.exchangeRate = exchangeRate;
        this.exchangeRateInv = exchangeRateInv;
    }
    public Divisa(String date, double exchangeRate, double maxVariation, double minVariation, double promVariation,double dayVariaton)
    {
        this.date = date;
        this.exchangeRate = exchangeRate;
        this.maxVariation = maxVariation;
        this.minVariation = minVariation;
        this.promVariation = promVariation;
        this.dayVariaton = dayVariaton;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getExchangeRateInv() {
        return exchangeRateInv;
    }
    public void setExchangeRateInv(double exchangeRateInv) {
        this.exchangeRateInv = exchangeRateInv;
    }
    
    public double getMaxVariation() {
        return maxVariation;
    }
    public void setMaxVariation(double maxVariation) {
        this.maxVariation = maxVariation;
    }
    public double getMinVariation() {
        return minVariation;
    }
    public void setMinVariation(double minVariation) {
        this.minVariation = minVariation;
    }
    public double getPromVariation() {
        return promVariation;
    }
    public void setPromVariation(double promVariation) {
        this.promVariation = promVariation;
    }
    public double getDayVariaton() {
        return dayVariaton;
    }
    public void setDayVariaton(double dayVariaton) {
        this.dayVariaton = dayVariaton;
    }


}
