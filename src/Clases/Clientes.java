package Clases;
public class Clientes {
    private String strNombre;
    private int intCedula;
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

    public Clientes(String strNombre, int intCedula, int intTelf) {
        this.strNombre = strNombre;
        this.intCedula = intCedula;
        this.intTelf = intTelf;
    }
    
    public Clientes(String strNombre, int intCedula, int intTelf, double dblDeuda, String strDireccion) {
        this.strNombre = strNombre;
        this.intCedula = intCedula;
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

    public int getIntCedula() {
        return intCedula;
    }

    public void setIntCedula(int intCedula) {
        this.intCedula = intCedula;
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
