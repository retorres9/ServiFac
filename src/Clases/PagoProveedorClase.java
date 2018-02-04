package Clases;

public class PagoProveedorClase {

    private String strRuc;
    private String strCedulaUsuario;
    private double dblMontoCancelado;
    private String strFecha;
    private String strTipo;
    private String strDescripcion;

    public PagoProveedorClase(String strEmpresa, String strUsuario, double dblMontoCancelado, String strFecha, String strTipo, String strDescripcion) {
        this.strRuc = strEmpresa;
        this.strCedulaUsuario = strUsuario;
        this.dblMontoCancelado = dblMontoCancelado;
        this.strFecha = strFecha;
        this.strTipo = strTipo;
        this.strDescripcion = strDescripcion;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public PagoProveedorClase() {
        
    }

    public String getStrRuc() {
        return strRuc;
    }

    public void setStrRuc(String strRuc) {
        this.strRuc = strRuc;
    }

    public String getStrCedUsuario() {
        return strCedulaUsuario;
    }

    public void setStrCedUsuario(String strCedulaUsuario) {
        this.strCedulaUsuario = strCedulaUsuario;
    }

    public double getDblMontoCancelado() {
        return dblMontoCancelado;
    }

    public void setDblMontoCancelado(double dblMontoCancelado) {
        this.dblMontoCancelado = dblMontoCancelado;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrTipo() {
        return strTipo;
    }

    public void setStrTipo(String strTipo) {
        this.strTipo = strTipo;
    }
    
}
