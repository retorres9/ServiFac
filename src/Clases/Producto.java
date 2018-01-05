package Clases;

import java.io.File;

public class Producto {
    private String strNombreProd;
    private String strCod;
    private double fltPrecio;
    private double precioCompra;
    private double ganancia;
    private double gananciaMayor;
    private boolean stock;
    private double fltPrecioMayor;
    private int intUbicacion;
    private int idCategoria;
    private int intCantidad;
    private int intCantidadMinima;
    private String strRUC;
    private File imgCodigoProd;
    private File fotoProd;
    private boolean iva;
    private int bodega;

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

    public Producto(String strNombreProd, double fltPrecio, double fltPrecioMayor, int intCantidad, int strUbicacion, String strCod) {
        this.strNombreProd = strNombreProd;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.intCantidad = intCantidad;
        this.intUbicacion = strUbicacion;
        this.strCod = strCod;
    }
    
    public Producto(String strNombreProd, String strCod, double fltPrecio, double fltPrecioMayor, int strUbicacion, int intCantidad) {
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.intUbicacion = strUbicacion;
        this.intCantidad = intCantidad;
    }

    public Producto(String strNombreProd, String strCod, double precioCompra, double fltPrecio, double fltPrecioMayor, double ganancia, double gananciaMayor, boolean stock, int intUbicacion, int idCategoria, int intCantidad, int intCantidadMinima, String strRUC, File imgCodigoProd, File fotoProd, boolean iva, int bodega) {
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
        this.fltPrecio = fltPrecio;
        this.precioCompra = precioCompra;
        this.ganancia = ganancia;
        this.gananciaMayor = gananciaMayor;
        this.stock = stock;
        this.fltPrecioMayor = fltPrecioMayor;
        this.intUbicacion = intUbicacion;
        this.idCategoria = idCategoria;
        this.intCantidad = intCantidad;
        this.intCantidadMinima = intCantidadMinima;
        this.strRUC = strRUC;
        this.imgCodigoProd = imgCodigoProd;
        this.fotoProd = fotoProd;
        this.iva = iva;
        this.bodega = bodega;
    }
    
    
    
    ///////////
    public Producto(String strNombreProd, String strCod, double fltPrecio, double fltPrecioMayor, int strUbicacion, int intCantidad, int intCantidadMinima, String strEmpresa, File imgCodigoProd, File fotoProd) {
        this.strNombreProd = strNombreProd;
        this.strCod = strCod;
        this.fltPrecio = fltPrecio;
        this.fltPrecioMayor = fltPrecioMayor;
        this.intUbicacion = strUbicacion;
        this.intCantidad = intCantidad;
        this.intCantidadMinima = intCantidadMinima;
        this.strRUC = strEmpresa;
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

    public int getIntUbicacion() {
        return intUbicacion;
    }

    public void setIntUbicacion(int intUbicacion) {
        this.intUbicacion = intUbicacion;
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
        return strRUC;
    }

    public void setStrEmpresa(String strEmpresa) {
        this.strRUC = strEmpresa;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double getGananciaMayor() {
        return gananciaMayor;
    }

    public void setGananciaMayor(double gananciaMayor) {
        this.gananciaMayor = gananciaMayor;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getStrRUC() {
        return strRUC;
    }

    public void setStrRUC(String strRUC) {
        this.strRUC = strRUC;
    }

    public File getFotoProd() {
        return fotoProd;
    }

    public void setFotoProd(File fotoProd) {
        this.fotoProd = fotoProd;
    }

    public boolean isIva() {
        return iva;
    }

    public void setIva(boolean iva) {
        this.iva = iva;
    }    

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getBodega() {
        return bodega;
    }

    public void setBodega(int bodega) {
        this.bodega = bodega;
    }
    
}
