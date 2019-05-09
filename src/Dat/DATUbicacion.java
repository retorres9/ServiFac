package Dat;

import Clases.Ubicacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entitymanager.Entity;
import entitymanager.EntityException;

/**
 *
 * @author rober
 */
public class DATUbicacion {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String database;
    String user;
    String password;
    Entity entity = new Entity();
    
    public DATUbicacion() {
        try {
            this.database = entity.getEntity("database");
            this.user = entity.getEntity("user");
            this.password = entity.getEntity("password");
        } catch (EntityException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Ubicacion> obtenerUbicacion(){
        ArrayList<Ubicacion> listaUbicacion = new ArrayList<Ubicacion>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
            String sentencia = "SELECT id_ubicacion, nombre_ubicacion FROM ubicacion ORDER BY nombre_ubicacion";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();            
            while(rs.next()){
                int idUbic = rs.getInt(1);
                String ubicacion = rs.getString(2);
                Ubicacion ubic = new Ubicacion(idUbic, ubicacion);
                listaUbicacion.add(ubic);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 060", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally{
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUbicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUbicacion;
    }
    
    public boolean crearUbicacion(Ubicacion ubi)throws SQLException{
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
            String sentencia = "INSERT INTO ubicacion(nombre_ubicacion) VALUES(?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, ubi.getStrUbicacion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nombre de ubicación ya existente");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUbicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
