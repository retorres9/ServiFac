package Clases;

public class Proveedor {
    private String strEmpresa;
    private String strNombreCuenta;
    private String strTipo;
    private String strNumCuenta;
    private double dblDeuda;
    private int intTelf;

    public int getIntTelf() {
        return intTelf;
    }

    public void setIntTelf(int intTelf) {
        this.intTelf = intTelf;
    }

    public Proveedor() {
    }

    public Proveedor(String strEmpresa, double dblDeuda) {
        this.strEmpresa = strEmpresa;
        this.dblDeuda = dblDeuda;
    }

    public Proveedor(String strEmpresa) {
        this.strEmpresa = strEmpresa;
    }

    public Proveedor(String strEmpresa, String strNombreCuenta, String strTipo, String strNumCuenta, double dblDeuda, int intTelf) {
        this.strEmpresa = strEmpresa;
        this.strNombreCuenta = strNombreCuenta;
        this.strTipo = strTipo;
        this.strNumCuenta = strNumCuenta;
        this.dblDeuda = dblDeuda;
        this.intTelf = intTelf;
    }

    public String getStrEmpresa() {
        return strEmpresa;
    }

    public void setStrEmpresa(String strEmpresa) {
        this.strEmpresa = strEmpresa;
    }

    public String getStrNumCuenta() {
        return strNumCuenta;
    }

    public void setStrNumCuenta(String strNumCuenta) {
        this.strNumCuenta = strNumCuenta;
    }

    public String getStrNombreCuenta() {
        return strNombreCuenta;
    }

    public void setStrNombreCuenta(String strNombreCuenta) {
        this.strNombreCuenta = strNombreCuenta;
    }

    public String getStrTipo() {
        return strTipo;
    }

    public void setStrTipo(String strTipo) {
        this.strTipo = strTipo;
    }

    public double getDblDeuda() {
        return dblDeuda;
    }

    public void setDblDeuda(double dblDeuda) {
        this.dblDeuda = dblDeuda;
    }
    @Override
    public String toString(){
        return this.strEmpresa;
    }
}