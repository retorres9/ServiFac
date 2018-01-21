package Dat;

import Clases.AbonoCliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATAbonoCliente {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void abonoCliente(AbonoCliente abonoCl) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO abono_cliente (Cedula, Usuario, Monto_Abono, Fecha) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, abonoCl.getStrCedula());
            ps.setString(2, abonoCl.getStrUsuario());
            ps.setDouble(3, abonoCl.getDblMontoAbono());
            ps.setString(4, abonoCl.getFechaAbono());
            ps.executeUpdate();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATAbonoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet verAbonos(String strFecha) throws SQLException, ClassNotFoundException {
        String sentencia = "SELECT c.Nombres, c.Cedula, ac.Monto_Abono, c.Deuda, ac.Usuario, ac.Fecha "
                + "FROM clientes c, abono_cliente ac "
                + "WHERE c.Cedula = ac.Cedula AND Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strFecha);
        return ps.executeQuery();
    }
}
