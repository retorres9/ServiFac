package clases;

public class PagoProveedorClase {

    private String strRuc;
    private String strCedulaUsuario;
    private double dblMontoCancelado;
    private String strFecha;
    private String strTipo;
    private String strDescripcion;

    /*Variables para consultas cruzadas*/
    private double deuda;
    private String usuario;
    private String fecha;
    private String empresa;

    public PagoProveedorClase(double dblMontoCancelado, String usuario, String fecha, String strDescripcion) {
        this.dblMontoCancelado = dblMontoCancelado;
        this.strDescripcion = strDescripcion;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public PagoProveedorClase(String strEmpresa, String strUsuario, double dblMontoCancelado, String strFecha, String strTipo, String strDescripcion) {
        this.strRuc = strEmpresa;
        this.strCedulaUsuario = strUsuario;
        this.dblMontoCancelado = dblMontoCancelado;
        this.strFecha = strFecha;
        this.strTipo = strTipo;
        this.strDescripcion = strDescripcion;
    }

    public PagoProveedorClase(String strRuc, double dblMontoCancelado, double deuda, String usuario, String fecha) {
        this.strRuc = strRuc;
        this.dblMontoCancelado = dblMontoCancelado;
        this.deuda = deuda;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public PagoProveedorClase(double dblMontoCancelado, double deuda, String usuario, String empresa) {
        this.dblMontoCancelado = dblMontoCancelado;
        this.deuda = deuda;
        this.usuario = usuario;
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getStrCedulaUsuario() {
        return strCedulaUsuario;
    }

    public void setStrCedulaUsuario(String strCedulaUsuario) {
        this.strCedulaUsuario = strCedulaUsuario;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
