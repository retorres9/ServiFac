package Clases;
public class Clientes {
    private String strNombre;
    private String strCedula;
    private int intTelf;
    private double dblDeuda;
    private String strDireccion;

    public Clientes() {
        /*Constructor vacio para usar posteriormente en la 
        BLCLientes*/
    }

    public Clientes(String strNombre) {
        this.strNombre = strNombre;
    }

    public Clientes(double dblDeuda, String strNombre) {
        this.strNombre = strNombre;
        this.dblDeuda = dblDeuda;
    }

    public Clientes(String strNombre, String strCedula, int intTelf, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.intTelf = intTelf;
        this.strDireccion = strDireccion;
    }
    
    public Clientes(String strNombre, String strCedula, int intTelf, double dblDeuda, String strDireccion) {
        this.strNombre = strNombre;
        this.strCedula = strCedula;
        this.intTelf = intTelf;
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

    public int getIntTelf() {
        return intTelf;
    }

    public void setIntTelf(int intTelf) {
        this.intTelf = intTelf;
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
