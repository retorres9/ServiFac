package Clases;

public class AbonoCliente {

    private int intCedula;
    private String strUsuario;
    private double dblMontoAbono;
    private String strFecha;

    public AbonoCliente(int intCedula, String strUsuario, double dblMontoAbono, String strFecha) {
        this.intCedula = intCedula;
        this.strUsuario = strUsuario;
        this.dblMontoAbono = dblMontoAbono;
        this.strFecha = strFecha;
    }

    public AbonoCliente() {
        
    }

    public int getIntCedula() {
        return intCedula;
    }

    public void setIntCedula(int intCedula) {
        this.intCedula = intCedula;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public double getDblMontoAbono() {
        return dblMontoAbono;
    }

    public void setDblMontoAbono(double dblMontoAbono) {
        this.dblMontoAbono = dblMontoAbono;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }
    
    
    
}
