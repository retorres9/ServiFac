package BL;
import Dat.DATConexion;
import java.sql.SQLException;
public class BLConexion {
    DATConexion ManejadorConexion = new DATConexion();
    
    public void AbrirConexion() throws ClassNotFoundException, SQLException{
        ManejadorConexion.getConnection();
    }
    
    public void CerrarConexion() throws SQLException, ClassNotFoundException{
        ManejadorConexion.CerrarConexion();
    }
}
