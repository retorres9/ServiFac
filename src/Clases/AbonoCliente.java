package Clases;

import java.sql.Date;

public class AbonoCliente {

    private int intCedula;
    private String strUsuario;
    private double dblMontoAbono;
    private Date fechaAbono;

    public AbonoCliente(int intCedula, String strUsuario, double dblMontoAbono, Date fechaAbono) {
        this.intCedula = intCedula;
        this.strUsuario = strUsuario;
        this.dblMontoAbono = dblMontoAbono;
        this.fechaAbono = fechaAbono;
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

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date strFecha) {
        this.fechaAbono = strFecha;
    }
    
    
    
}
