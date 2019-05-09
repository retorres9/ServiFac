package Dat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entitymanager.Entity;
import entitymanager.EntityException;

public class DATConexion {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String database;
    String user;
    String password;
    Entity entity = new Entity();
    
    

    public DATConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.database = entity.getEntity("database");
            this.user = entity.getEntity("user");
            this.password = entity.getEntity("password");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EntityException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/"+database;
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    public Connection AbrirConexion() {
        try {
            con = getConnection();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al conectarse con la base de datos\nError 1x100", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    public void CerrarConexion() throws ClassNotFoundException, SQLException {
        con = null;
    }
}
