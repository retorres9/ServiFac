/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import Clases.Pruebas;
import Dat.DATPrueba;
import java.sql.SQLException;

/**
 *
 * @author rober
 */
public class BLPrueba {
    DATPrueba ManejadorMaterial = new DATPrueba();
    Pruebas objCat = new Pruebas();
    public void IngresarProductoBl(Pruebas objPrueba) throws ClassNotFoundException, SQLException {
        ManejadorMaterial.IngresarCat(objPrueba.getIdCategoria(),objPrueba.getStrCat());
    }
}
