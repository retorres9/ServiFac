package Dat;

import Clases.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATUsuario {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    

    public boolean nuevoUsuario(Usuario usuario) throws SQLException {
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

    public ArrayList<Usuario> obtenerCedula(String user) {
        ArrayList<Usuario> cedula = new ArrayList<Usuario>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT cedula_usuario FROM usuario WHERE usuario = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ced = rs.getString(1);
                Usuario usuario = new Usuario(ced);
                cedula.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cedula;
    }

    public ArrayList<Usuario> listarClientes() {
        ArrayList<Usuario> listadoUsuarios = new ArrayList<Usuario>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT nombre, usuario, rol FROM usuario ORDER BY nombre";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String user = rs.getString(2);
                int rol = rs.getInt(3);
                Usuario usuario = new Usuario(nombre, user, rol);
                listadoUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoUsuarios;
    }

    public void setLogin(String us, String maq) {
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE usuario SET login = 1, maquina = ? WHERE cedula_usuario = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            ps.setString(2, us);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startupLogin(String maq) {
        try {
            //con = DriverManager.getConnection("jdbc:mysql://192.168.1.7:3306/empresa", "root", "ticowrc2017");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE usuario SET login = 0 WHERE maquina = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Usuario> obtenerUserLog(String maq) {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT usuario, cedula_usuario, rol FROM usuario WHERE  login = 1 AND maquina = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            rs = ps.executeQuery();
            while (rs.next()) {
                String strUsuario = rs.getString(1);                
                String cedula = rs.getString(2);
                int rol = rs.getInt(3);
                Usuario user = new Usuario(strUsuario, cedula, rol);//en objeto (nombre, usuario,rol)
                listaUsuario.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuario;

    }
}
