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

/**
 *
 * @author rober
 */
public class DATUbicacion {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public ArrayList<Ubicacion> obtenerUbicacion(){
        ArrayList<Ubicacion> listaUbicacion = new ArrayList<Ubicacion>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
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
            Logger.getLogger(DATUbicacion.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void crearUbicacion(Ubicacion ubi){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO ubicacion(nombre_ubicacion) VALUES(?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, ubi.getStrUbicacion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUbicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
