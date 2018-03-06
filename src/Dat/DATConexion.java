package Dat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATConexion {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public DATConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empresa";
        Class.forName(driver);
        return DriverManager.getConnection(url, "root", "ticowrc2017");
    }

    public Connection AbrirConexion() {
        try {
            con = getConnection();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void CerrarConexion() throws ClassNotFoundException, SQLException {
        con = null;
    }
    
    
    Connection conectar = null;
    public Connection conexion(){
        try{
          Class.forName("com.mysql.jdbc.Driver");
          conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
        }catch(ClassNotFoundException | SQLException e){
            System.out.print(e.getMessage());
        }
        return conectar;
    }
}
