/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dat;

import Clases.ExistenciasBodega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */
public class DATExistenciasBodega {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public void ingresarEnBodega(ExistenciasBodega existencia) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO existenciasBodega (id_bodega, codigo, cantidad) VALUES (?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, existencia.getIdBodega());
            ps.setString(2, existencia.getCodigo());
            ps.setInt(3, existencia.getCantidad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actualizarCant(ExistenciasBodega existencia, int cant) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            con.setAutoCommit(false);
            String sentencia = "UPDATE existenciasBodega SET cantidad = ? WHERE codigo = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, existencia.getCantidad());
            ps.setString(2, existencia.getCodigo());
            ps.executeUpdate();

            String sentencia2 = "UPDATE producto SET cantidad = cantidad - ? WHERE codigo = ?";
            ps = con.prepareStatement(sentencia2);
            ps.setInt(1, cant);
            ps.setString(2, existencia.getCodigo());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int contadorProdBodega(String cod) {
        int cant = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT count(*) FROM existenciasbodega WHERE codigo = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                cant = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cant;
    }

    public void ingresarEnBodegaInventario(ExistenciasBodega existencia, int cant) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            con.setAutoCommit(false);
            String sentencia = "INSERT INTO existenciasBodega (id_bodega, codigo, cantidad) VALUES (?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, existencia.getIdBodega());
            ps.setString(2, existencia.getCodigo());
            ps.setInt(3, existencia.getCantidad());
            ps.executeUpdate();

            String Sentencia2 = "UPDATE producto SET cantidad = cantidad - ? WHERE codigo = ?";
            ps = con.prepareStatement(Sentencia2);
            ps.setInt(1, cant);
            ps.setString(2, existencia.getCodigo());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
