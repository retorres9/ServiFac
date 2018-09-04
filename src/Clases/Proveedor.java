package Clases;

public class Proveedor {
    private String strEmpresa;
    private String ruc;
    private String strNombreCuenta;
    private String strTipo;
    private String strNumCuenta;
    private double dblDeuda;
    private String strTelf;

    public Proveedor(String strEmpresa, String ruc, String strNombreCuenta, String strTipo, String strNumCuenta, double dblDeuda, String strTelf) {
        this.strEmpresa = strEmpresa;
        this.ruc = ruc;
        this.strNombreCuenta = strNombreCuenta;
        this.strTipo = strTipo;
        this.strNumCuenta = strNumCuenta;
        this.dblDeuda = dblDeuda;
        this.strTelf = strTelf;
    }

    public Proveedor(String strEmpresa, String ruc) {
        this.strEmpresa = strEmpresa;
        this.ruc = ruc;
    }
    
    public Proveedor(String ruc, double dblDeuda) {
        this.dblDeuda = dblDeuda;
        this.ruc = ruc;
    }

    public Proveedor(String strNombreCuenta, String strTipo, String strNumCuenta, String strTelf, String ruc) {
        this.strNombreCuenta = strNombreCuenta;
        this.strTipo = strTipo;
        this.strNumCuenta = strNumCuenta;
        this.strTelf = strTelf;
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getStrTelf() {
        return strTelf;
    }

    public void setIntTelf(String strTelf) {
        this.strTelf = strTelf;
    }

    public Proveedor() {
    }

    public Proveedor(String strEmpresa) {
        this.strEmpresa = strEmpresa;
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