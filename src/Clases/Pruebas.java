/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;

/**
 *
 * @author rober
 */
public class Pruebas {
    private int idCategoria;
    private String strCat;
    private File imagen;

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public Pruebas(File imagen) {
        this.imagen = imagen;
    }

    public Pruebas() {
    }
    
    public Pruebas(String strCat) {
        this.strCat = strCat;
    }

    public Pruebas(int idCategoria, String strCat) {
        this.idCategoria = idCategoria;
        this.strCat = strCat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getStrCat() {
        return strCat;
    }

    public void setStrCat(String strCat) {
        this.strCat = strCat;
    }
    
    @Override
    public String toString(){
        return this.strCat;
    }
}
