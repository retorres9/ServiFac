package Dat;

import Clases.ExistenciasBodega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar las existencias en bodega\nen la base de datos\nError 044", "Error!", JOptionPane.ERROR_MESSAGE);
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
            String sentencia = "UPDATE existenciasBodega SET cantidad = cantidad + ? WHERE codigo = ?";
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
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar las existencias en bodega\nen la base de datos\nError 045", "Error!", JOptionPane.ERROR_MESSAGE);
            }
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 046", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATExistenciasBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la transacción de inventario\nError 047", "Error!", JOptionPane.ERROR_MESSAGE);
            }
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
