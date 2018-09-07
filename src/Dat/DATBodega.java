package Dat;

import Clases.Bodega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class DATBodega {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;

    public ArrayList<Bodega> obtenerBodega() {
        ArrayList<Bodega> listadoBodega = new ArrayList<Bodega>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "Select id_bodega, nombre_bodega From Bodega";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                Bodega bodega = new Bodega(id, nombre);
                listadoBodega.add(bodega);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 032", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoBodega;
    }

    public void nuevaBodega(Bodega bodega) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO bodega (nombre_bodega) VALUES(?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, bodega.getStrBodega());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear la nueva bodega\nError 033", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
