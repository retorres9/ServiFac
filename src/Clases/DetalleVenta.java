package Clases;

public class DetalleVenta {
    private int intCed;
    private int intCant;
    private int intCod;
    private double dblPrecioVenta;
    private String strUsuario;
    private int id_Venta;

    public DetalleVenta() {
    }

    public DetalleVenta(int intCed, int intCant, int intCod, double dblPrecioVenta, String strUsuario, int id_Venta) {
        this.intCed = intCed;
        this.intCant = intCant;
        this.intCod = intCod;
        this.dblPrecioVenta = dblPrecioVenta;
        this.strUsuario = strUsuario;
        this.id_Venta = id_Venta;
    }

    public int getIntCed() {
        return intCed;
    }

    public void setIntCed(int intCed) {
        this.intCed = intCed;
    }

    public int getIntCant() {
        return intCant;
    }

    public void setIntCant(int intCant) {
        this.intCant = intCant;
    }

    public int getIntCod() {
        return intCod;
    }

    public void setIntCod(int intCod) {
        this.intCod = intCod;
    }

    public double getDblPrecioVenta() {
        return dblPrecioVenta;
    }

    public void setDblPrecioVenta(double dblPrecioVenta) {
        this.dblPrecioVenta = dblPrecioVenta;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public int getId_Venta() {
        return id_Venta;
    }

    public void setId_Venta(int id_Venta) {
        this.id_Venta = id_Venta;
    }
}
