package Clases;

public class Configuracion {
    
    private String empresa;
    private String propietario;
    private String direccion;
    private int iva;
    private String ruc;
    private String telefono;
    private String pass;

    public Configuracion() {
    
    }

    public Configuracion(String empresa, String propietario, String direccion, int iva, String ruc, String telefono) {
        this.empresa = empresa;
        this.propietario = propietario;
        this.direccion = direccion;
        this.iva = iva;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    public Configuracion(String empresa, String pass) {
        this.empresa = empresa;
        this.pass = pass;
    }
    
    public Configuracion(String pass){
        this.pass = pass;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
