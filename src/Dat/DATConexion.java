package Dat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATConexion {
    Connection con = null;
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
