package dat;

import clases.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entitymanager.Entity;
import entitymanager.EntityException;

public class DATUsuario {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String database;
    String user;
    String password;
    Entity entity = new Entity();
    String url = "jdbc:mysql://localhost:3306/";

    public DATUsuario() {
        try {
            this.database = entity.getEntity("database");
            this.user = entity.getEntity("user");
            this.password = entity.getEntity("password");
        } catch (EntityException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean nuevoUsuario(Usuario usuario, String maq) throws SQLException {
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "INSERT INTO usuario (Cedula_Usuario,Nombre, Usuario, Contrasena, Rol, maquina) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getCedulaUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getRol());
            ps.setString(6, maq);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El usuario ya está registrado o uno de los campos\n"
                    + "que ha llenado han sido ingresados en otro usuario.\n"
                    + "Pueden ser:"
                    + "\n* Nombre"
                    + "\n* Usuario"
                    + "\n* Contraseña");
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

    public ArrayList<Usuario> obtenerCedula(String userStr) {
        ArrayList<Usuario> cedula = new ArrayList<Usuario>();
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "SELECT cedula_usuario FROM usuario WHERE usuario = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, userStr);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ced = rs.getString(1);
                Usuario usuario = new Usuario(ced);
                cedula.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 026", "Error!", JOptionPane.ERROR_MESSAGE);
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

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> listadoUsuarios = new ArrayList<Usuario>();
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "SELECT nombre, usuario, rol FROM usuario ORDER BY nombre";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String userStr = rs.getString(2);
                int rol = rs.getInt(3);
                Usuario usuario = new Usuario(nombre, userStr, rol);
                listadoUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 027", "Error!", JOptionPane.ERROR_MESSAGE);
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
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "UPDATE usuario SET login = 1, maquina = ? WHERE cedula_usuario = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            ps.setString(2, us);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar el acceso en la base de datos\nError 028", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Usuario> cierraPrograma(String host) {
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentecnia = "SELECT login FROM usuario WHERE login = 1 AND maquina = ?";
            ps = con.prepareStatement(sentecnia);
            ps.setString(1, host);
            rs = ps.executeQuery();
            while (rs.next()) {
                boolean login = rs.getBoolean(1);
                Usuario userThis = new Usuario(login);
                resultado.add(userThis);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 029", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    public void startupLogin(String maq) {
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "UPDATE usuario SET login = 0 WHERE maquina = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el acceso del usuario en la base de datos\nError 030", "Error!", JOptionPane.ERROR_MESSAGE);
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
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "SELECT usuario, cedula_usuario, rol FROM usuario WHERE  login = 1 AND maquina = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, maq);
            rs = ps.executeQuery();
            while (rs.next()) {
                String strUsuario = rs.getString(1);
                String cedula = rs.getString(2);
                int rol = rs.getInt(3);
                Usuario userThis = new Usuario(strUsuario, cedula, rol);//en objeto (nombre, usuario,rol)
                listaUsuario.add(userThis);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 031", "Error!", JOptionPane.ERROR_MESSAGE);
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
