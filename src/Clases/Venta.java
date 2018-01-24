package Clases;

public class Venta {
    private int intIdVenta;
    private double dblTotalVenta;
    private String strFecha;
    private double dblValCancelado;
    private String cedulaUser;

    public Venta(int intIdVenta, double dblTotalVenta, double dblValCancelado, String strFecha, String cedulaUser) {
        this.intIdVenta = intIdVenta;
        this.dblTotalVenta = dblTotalVenta;
        this.dblValCancelado = dblValCancelado;
        this.strFecha = strFecha;
        this.cedulaUser = cedulaUser;
    }

    public  Venta(int intIdVenta ,double dblValCancelado){
        this.intIdVenta = intIdVenta;
        this.dblValCancelado = dblValCancelado;
    }
    
    public Venta() {
        
    }

    public int getIntIdVenta() {
        return intIdVenta;
    }

    public void setIntIdVenta(int intIdVenta) {
        this.intIdVenta = intIdVenta;
    }

    public double getDblTotalVenta() {
        return dblTotalVenta;
    }

    public double getDblValCancelado() {
        return dblValCancelado;
    }

    public void setDblValCancelado(double dblValCancelado) {
        this.dblValCancelado = dblValCancelado;
    }

    public void setDblTotalVenta(double dblTotalVenta) {
        this.dblTotalVenta = dblTotalVenta;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getCedulaUser() {
        return cedulaUser;
    }

    public void setCedulaUser(String cedulaUser) {
        this.cedulaUser = cedulaUser;
    }
    
}
