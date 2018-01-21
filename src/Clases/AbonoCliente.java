package Clases;

import java.sql.Date;

public class AbonoCliente {

    private String strCedula;
    private String strUsuario;
    private double dblMontoAbono;
    private String fechaAbono;

    public AbonoCliente(String strCedula, String strUsuario, double dblMontoAbono, String fechaAbono) {
        this.strCedula = strCedula;
        this.strUsuario = strUsuario;
        this.dblMontoAbono = dblMontoAbono;
        this.fechaAbono = fechaAbono;
    }

    public AbonoCliente() {
        
    }

    public String getStrCedula() {
        return strCedula;
    }

    public void setStrCedula(String strCedula) {
        this.strCedula = strCedula;
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

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String strFecha) {
        this.fechaAbono = strFecha;
    }    
}
