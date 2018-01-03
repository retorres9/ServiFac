package Clases;

import java.io.File;

public class Producto {
    private String strNombreProd;
    private String strCod;
    private double fltPrecio;
    private double precioCompra;
    private double ganancia;
    private boolean stock;
    private double fltPrecioMayor;
    private String strUbicacion;//
    private int categoria;
    private int intCantidad;
    private int intCantidadMinima;
    private String strEmpresa;
    private File imgCodigoProd;
    private File fotoProd;

    public Producto(int intCantidadMinima, String strNombreProd) {
        this.strNombreProd = strNombreProd;
        this.intCantidadMinima = intCantidadMinima;
    }

    public Producto(String strNombreProd) {
        this.strNombreProd = strNombreProd;
    }
    
    public Producto(String strNombreProd, String strCod){
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
    }
    
    public Producto(){
        
    }

    public Producto(String strNombreProd, int intCantidad) {
        this.strNombreProd = strNombreProd;
        this.intCantidad = intCantidad;
    }

    public Producto(String strNombreProd, double fltPrecio, double fltPrecioMayor, int intCantidad, String strUbicacion, String strCod) {
        this.strNombreProd = strNombreProd;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.intCantidad = intCantidad;
        this.strUbicacion = strUbicacion;
        this.strCod = strCod;
    }
    
    public Producto(String strNombreProd, String strCod, double fltPrecio, double fltPrecioMayor, String strUbicacion, int intCantidad) {
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.strUbicacion = strUbicacion;
        this.intCantidad = intCantidad;
    }
    
    public Producto(String strNombreProd, String strCod, double fltPrecio, double fltPrecioMayor, String strUbicacion, int intCantidad, int intCantidadMinima, String strEmpresa, File imgCodigoProd, File fotoProd) {
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.strUbicacion = strUbicacion;
        this.intCantidad = intCantidad;
        this.intCantidadMinima = intCantidadMinima;
        this.strEmpresa = strEmpresa;
        this.imgCodigoProd = imgCodigoProd;
        this.fotoProd = fotoProd;
    }

    public File getImgCodigoProd() {
        return imgCodigoProd;
    }

    public void setImgCodigoProd(File imgCodigoProd) {
        this.imgCodigoProd = imgCodigoProd;
    }

    public String getStrNombreProd() {
        return strNombreProd;
    }

    public void setStrNombreProd(String strNombreProd) {
        this.strNombreProd = strNombreProd;
    }

    public String getStrCod() {
        return strCod;
    }

    public void setStrCod(String strCod) {
        this.strCod = strCod;
    }

    public double getFltPrecio() {
        return fltPrecio;
    }

    public void setFltPrecio(double fltPrecio) {
        this.fltPrecio = fltPrecio;
    }

    public double getFltPrecioMayor() {
        return fltPrecioMayor;
    }

    public void setFltPrecioMayor(double fltPrecioMayor) {
        this.fltPrecioMayor = fltPrecioMayor;
    }

    public String getStrUbicacion() {
        return strUbicacion;
    }

    public void setStrUbicacion(String strUbicacion) {
        this.strUbicacion = strUbicacion;
    }

    public int getIntCantidad() {
        return intCantidad;
    }

    public void setIntCantidad(int intCantidad) {
        this.intCantidad = intCantidad;
    }

    public int getIntCantidadMinima() {
        return intCantidadMinima;
    }

    public void setIntCantidadMinima(int intCantidadMinima) {
        this.intCantidadMinima = intCantidadMinima;
    }

    public String getStrEmpresa() {
        return strEmpresa;
    }

    public void setStrEmpresa(String strEmpresa) {
        this.strEmpresa = strEmpresa;
    }
    
}
