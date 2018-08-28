package Clases;

public class Clientes {

    private String strNombre;
    private String strCedula;
    private String strTelf;
    private double dblDeuda;
    private String strDireccion;
    private boolean credito;

    /*Atributos externos*/
    private String usuario;
    private double cant;

    public Clientes() {
        /*Constructor vacio para usar posteriormente en la 
        BLCLientes*/
    }

    public Clientes(String strCedula) {
        this.strCedula = strCedula;
    }

    public Clientes(String strNombre, double dblDeuda, String strDireccion, String strTelf, boolean credito, double cant) {
        this.strNombre = strNombre;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
        this.strTelf = strTelf;
        this.credito = credito;
        this.cant = cant;
    }

    public Clientes(double dblDeuda, String strCedula) {//Usado en mas de dos frames
        this.strCedula = strCedula;
        this.dblDeuda = dblDeuda;
    }

    public Clientes(String strNombre, String strCedula) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
    }

    public Clientes(String strNombre, String strCedula, String strTelf, double dblDeuda, String strDireccion, boolean credito, double cant) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
        this.credito = credito;
        this.cant = cant;
    }
    
    public Clientes(String strNombre, String strCedula, String strTelf, double dblDeuda, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
    }

    public Clientes(String strNombre, String strCedula, String strTelf, double dblDeuda, String strDireccion, boolean credito, String usuario, double cant) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
        this.usuario = usuario;
        this.credito = credito;
        this.cant = cant;
    }

    public Clientes(String strNombre, String strCedula, String strTelf, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.strDireccion = strDireccion;
    }

    public Clientes(double nuevoMonto, String cedUsuario, String usuario) {
        this.cant = nuevoMonto;
        this.strCedula = cedUsuario;
        this.usuario = usuario;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrCedula() {
        return strCedula;
    }

    public void setIntCedula(String strCedula) {
        this.strCedula = strCedula;
    }

    public String getStrTelf() {
        return strTelf;
    }

    public void setStrTelf(String strTelf) {
        this.strTelf = strTelf;
    }

    public double getDblDeuda() {
        return dblDeuda;
    }

    public void setDblDeuda(double dblDeuda) {
        this.dblDeuda = dblDeuda;
    }

    public String getStrDireccion() {
        return strDireccion;
    }

    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getCant() {
        return cant;
    }

    public void setCant(double cant) {
        this.cant = cant;
    }

}
