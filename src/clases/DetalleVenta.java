package clases;

public class DetalleVenta {

    private String strCed;
    private int intCant;
    private String strCod;
    private double dblPrecioVenta;
    private String strUsuario;
    private int id_Venta;
    //Para cargar consultas cruzadas//
    private String nombreProd;
    private String fecha;

    public DetalleVenta() {
    }

    public DetalleVenta(String strCed, int intCant, String strCod, double dblPrecioVenta, String strUsuario, int id_Venta) {
        this.strCed = strCed;
        this.intCant = intCant;
        this.strCod = strCod;
        this.dblPrecioVenta = dblPrecioVenta;
        this.strUsuario = strUsuario;
        this.id_Venta = id_Venta;
    }

    public DetalleVenta(int intCant, int id_Venta, String nombreProd, double dblPrecioVenta, String fecha) {
        this.intCant = intCant;
        this.id_Venta = id_Venta;
        this.nombreProd = nombreProd;
        this.dblPrecioVenta = dblPrecioVenta;
        this.fecha = fecha;
    }

    public String getStrCed() {
        return strCed;
    }

    public void setStrCed(String strCed) {
        this.strCed = strCed;
    }

    public int getIntCant() {
        return intCant;
    }

    public void setIntCant(int intCant) {
        this.intCant = intCant;
    }

    public String getStrCod() {
        return strCod;
    }

    public void setStrCod(String strCod) {
        this.strCod = strCod;
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

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
