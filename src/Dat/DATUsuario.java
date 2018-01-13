package Dat;

import Clases.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATUsuario {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean nuevoUsuario(Usuario usuario) throws SQLException{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO usuario (Cedula_Usuario,Nombre, Usuario, Contrasena, Rol) "
                    + "VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getCedulaUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El usuario ya está registrado o uno de los campos\n"
                    + "que ha llenado han sido ingresados en otro usuario");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;

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
