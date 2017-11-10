package Dat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATUsuario {
    Dat.DATConexion c = new DATConexion();
    public void nuevoUsuario(int cedulaUser,String nombre, String usuario, String cont, int rol) throws ClassNotFoundException, SQLException{
        String sentencia = "INSERT INTO usuario (Cedula_Usuario,Nombre, Usuario, Contrasena, Rol) "
                + "VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = c.getConnection().prepareStatement(sentencia)) {
            ps.setInt(1, cedulaUser);
            ps.setString(2, nombre);
            ps.setString(3, usuario);
            ps.setString(4, cont);
            ps.setInt(5, rol);
            ps.execute();
            ps.close();
        }
    }
    
//    public int compUsuario() throws ClassNotFoundException, SQLException{
//        String sentencia = "SELECT Usuario, Contrasena FROM usuario WHERE Usuario = ? && Contrasena = ?";
//        PreparedStatement st = c.getConnection().prepareStatement(sentencia);
//        st.setString(1, usuario);
//        st.setString(2, pass);
//        return st.executeUpdate();
//    }
    
    public ResultSet validUser(String usuario, String pass) throws ClassNotFoundException, SQLException{
        String sentencia = "SELECT count(*) FROM usuario WHERE Usuario = ? && Contrasena = ?";
        PreparedStatement pst = c.getConnection().prepareStatement(sentencia);
        pst.setString(1, usuario);
        pst.setString(2, pass);
        return pst.executeQuery();
    }
}
