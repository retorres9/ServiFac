package Clases;
public class Clientes {
    private String strNombre;
    private String strCedula;
    private String strTelf;
    private double dblDeuda;
    private String strDireccion;

    public Clientes() {
        /*Constructor vacio para usar posteriormente en la 
        BLCLientes*/
    }

    public Clientes(String strCedula) {
        this.strCedula = strCedula;
    }

    public Clientes(String strNombre, double dblDeuda, String strDireccion) {
        this.strNombre = strNombre;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
    }

    public Clientes(double dblDeuda, String strCedula) {
        this.strCedula = strCedula;
        this.dblDeuda = dblDeuda;
    }
    
    public Clientes(String strNombre, String strCedula) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
    }

    public Clientes(String strNombre, String strCedula, String strTelf, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.strDireccion = strDireccion;
    }
    
    public Clientes(String strNombre, String strCedula, String strTelf, double dblDeuda, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.strTelf = strTelf;
        this.dblDeuda = dblDeuda;
        this.strDireccion = strDireccion;
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
    
    public String getStrDireccion(){
        return strDireccion;
    }
    
    public void setStrDireccion(String strDireccion){
        this.strDireccion = strDireccion;
    }
    
    
}
