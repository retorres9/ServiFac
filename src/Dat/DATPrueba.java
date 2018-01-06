/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dat;

import Clases.Categoria;
import Clases.Pruebas;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */
public class DATPrueba {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void IngresarCat(Pruebas prueba) throws ClassNotFoundException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            FileInputStream fis = new FileInputStream(prueba.getImagen());
            String Sentencia = "INSERT INTO prueba (imagen)"
                    + "VALUES (?)";
            ps = con.prepareStatement(Sentencia);
            ps.setBinaryStream(1, fis, (int) prueba.getImagen().length());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DATPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    public ArrayList<Pruebas> obtenerCategoria() throws ClassNotFoundException{
        ArrayList<Pruebas> listaCategoria = new ArrayList<Pruebas>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT nombre_Cat FROM categorias";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String cat = rs.getString("nombre_Cat");
                Pruebas objCat = new Pruebas(cat);
                listaCategoria.add(objCat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaCategoria;
    }
}
