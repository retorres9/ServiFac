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

/**
 *
 * @author rober
 */
public class DATBodega {
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    
    public ArrayList<Bodega> obtenerBodega(){
        ArrayList<Bodega> listadoBodega = new ArrayList<Bodega>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "Select nombre_bodega From Bodega";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getString(1);
                Bodega bodega = new Bodega(nombre);
                listadoBodega.add(bodega);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATBodega.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATBodega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoBodega;
    }
}
