package Clases;

public class PagoProveedorClase {

    private String strEmpresa;
    private String strUsuario;
    private double dblMontoCancelado;
    private String strFecha;
    private String strTipo;

    public PagoProveedorClase(String strEmpresa, String strUsuario, double dblMontoCancelado, String strFecha, String strTipo) {
        this.strEmpresa = strEmpresa;
        this.strUsuario = strUsuario;
        this.dblMontoCancelado = dblMontoCancelado;
        this.strFecha = strFecha;
        this.strTipo = strTipo;
    }

    public PagoProveedorClase() {
        
    }

    public String getStrEmpresa() {
        return strEmpresa;
    }

    public void setStrEmpresa(String strEmpresa) {
        this.strEmpresa = strEmpresa;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
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
