/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author rober
 */
public class ExistenciasBodega {
    private int idBodega;
    private String codigo;
    private int cantidad;
    
    public ExistenciasBodega(){
        
    }

    public ExistenciasBodega(int idBodega, String codigo, int cantidad) {
        this.idBodega = idBodega;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
