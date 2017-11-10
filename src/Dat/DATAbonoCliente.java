package Dat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DATAbonoCliente {

    Dat.DATConexion c = new DATConexion();
    
    public int abonoCliente(int intCedula, String strUsuario, double dblMonto, String strFecha)
    throws SQLException, ClassNotFoundException{
        String sentencia = "INSERT INTO abono_cliente (Cedula, Usuario, Monto_Abono, Fecha) VALUES (?,?,?,?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setInt(1, intCedula);
        ps.setString(2, strUsuario);
        ps.setDouble(3, dblMonto);
        ps.setString(4, strFecha);
        return ps.executeUpdate();
    }
    
    public ResultSet verAbonos(String strFecha) throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT c.Nombres, c.Cedula, ac.Monto_Abono, c.Deuda, ac.Usuario, ac.Fecha "
                + "FROM clientes c, abono_cliente ac "
                + "WHERE c.Cedula = ac.Cedula AND Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strFecha);
        return ps.executeQuery();
    }
}
