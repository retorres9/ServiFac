package dat;

import clases.AbonoCliente;
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

public class DATAbonoCliente {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AbonoCliente abono = new AbonoCliente();
    String database;
    String user;
    String password;
    Entity entity = new Entity();
    String url = "jdbc:mysql://localhost:3306/";
    
    public DATAbonoCliente(){
        try {
            this.database = entity.getEntity("database");
            this.user = entity.getEntity("user");
            this.password = entity.getEntity("password");
        } catch (EntityException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abonoCliente(AbonoCliente abonoCl) {
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "INSERT INTO abono_cliente (Cedula_cliente, cedula_usuario, Monto_Abono, Fecha) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, abonoCl.getStrCedula());
            ps.setString(2, abonoCl.getStrUsuario());
            ps.setDouble(3, abonoCl.getDblMontoAbono());
            ps.setString(4, abonoCl.getFechaAbono());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar los datos del abono del cliente\nError 021", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATAbonoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<AbonoCliente> verAbonos(String strFecha) {
        ArrayList<AbonoCliente> listadoPagos = new ArrayList<AbonoCliente>();
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "SELECT c.Nombres, c.Cedula_cliente, ac.Monto_Abono, c.Deuda, u.usuario "
                    + "FROM clientes c, abono_cliente ac, usuario u "
                    + "WHERE u.cedula_usuario = ac.cedula_usuario AND c.Cedula_cliente = ac.Cedula_cliente AND Fecha = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, strFecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombres = rs.getString(1);
                String cedula = rs.getString(2);
                double monto = rs.getDouble(3);
                double deuda = rs.getDouble(4);
                String usuario = rs.getString(5);
                abono = new AbonoCliente(cedula, usuario, monto, nombres, deuda);
                listadoPagos.add(abono);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cargar los datos del abono del cliente\nError 022", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATAbonoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoPagos;
    }
}
