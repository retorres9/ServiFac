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
            String sentencia = "SELECT nombre_ubic FROM ubicacion";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String ubicacion = rs.getString(1);
                
                Ubicacion ubic = new Ubicacion(ubicacion);
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
}
