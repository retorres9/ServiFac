package Dat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATConexion {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public DATConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empresa";
        Class.forName(driver);
        return DriverManager.getConnection(url, "root", "ticowrc2017");
    }
    public Connection AbrirConexion() throws ClassNotFoundException, SQLException{
        con = getConnection();
        return con;
    }
    public void CerrarConexion() throws ClassNotFoundException, SQLException{
            con = null;
    }
}
