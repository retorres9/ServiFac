package Dat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATUsuario {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void nuevoUsuario(int cedulaUser,String nombre, String usuario, String cont, int rol){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO usuario (Cedula_Usuario,Nombre, Usuario, Contrasena, Rol) "
                + "VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, cedulaUser);
            ps.setString(2, nombre);
            ps.setString(3, usuario);
            ps.setString(4, cont);
            ps.setInt(5, rol);
            ps.executeUpdate();            
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
            
        
    }
    
//    public int compUsuario() throws ClassNotFoundException, SQLException{
//        String sentencia = "SELECT Usuario, Contrasena FROM usuario WHERE Usuario = ? && Contrasena = ?";
//        PreparedStatement st = c.getConnection().prepareStatement(sentencia);
//        st.setString(1, usuario);
//        st.setString(2, pass);
//        return st.executeUpdate();
//    }
    
//    public ResultSet validUser(String usuario, String pass) throws ClassNotFoundException, SQLException{
//        String sentencia = "SELECT count(*) FROM usuario WHERE Usuario = ? && Contrasena = ?";
//        PreparedStatement pst = c.getConnection().prepareStatement(sentencia);
//        pst.setString(1, usuario);
//        pst.setString(2, pass);
//        return pst.executeQuery();
//    }
}
