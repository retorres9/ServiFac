package Dat;

import Clases.Clientes;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATClientes {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Clientes cliente = new Clientes();

    public ArrayList<Clientes> ObtenerClientes() {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT nombres, cedula_cliente, telefono, deuda, direccion, credito, monto_aprobado FROM clientes WHERE nombres != 'CONSUMIDOR FINAL' ORDER BY nombres";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String cedula = rs.getString(2);
                String telf = rs.getString(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                boolean credito = rs.getBoolean(6);
                double monto = rs.getDouble(7);
                cliente = new Clientes(nombre, cedula, telf, deuda, direccion, credito, monto);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar los clientes en la base de datos\nError 035", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public void apruebaCrédito(Clientes cliente) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes set credito = true, monto aprobado = ?, aprobado_por = ?"
                    + "WHERE cedula_cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, cliente.getCant());
            ps.setString(2, cliente.getUsuario());
            ps.setString(3, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el cliente en la base de datos\nError 036", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try{
                ps.close();
                con.close();
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cerrar la conexión en apruebaCredito", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actualizaCredito(Clientes cliente) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes SET credito = true, monto_aprobado = ?, modificado_por = ?"
                    + "WHERE cedula_cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, cliente.getCant());
            ps.setString(2, cliente.getUsuario());//Se usa getNombre para obtener el numero de cedula de quien modificó el crédito
            ps.setString(3, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar el crédito en la base de datos\nError 037", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Clientes> ConsultarPorNombre(String nombre) {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE DEUDA > 0.00 AND estado = true AND Nombres REGEXP CONCAT('^',?) ORDER BY Nombres";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreCliente = rs.getString(1);
                String cedula = rs.getString(2);
                String telf = rs.getString(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                cliente = new Clientes(nombreCliente, cedula, telf, deuda, direccion);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 038", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public ArrayList<Clientes> ConsultarCedula(String cedulaCli) {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE Deuda> 0.00 AND Cedula_Cliente REGEXP CONCAT('^',?) ORDER BY Nombres";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, cedulaCli);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreCliente = rs.getString(1);
                String cedula = rs.getString(2);
                String telf = rs.getString(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                cliente = new Clientes(nombreCliente, cedula, telf, deuda, direccion);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 039", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public boolean InsertarCliente(Clientes cliente) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO clientes (Nombres, Cedula_Cliente, Telefono, Deuda, Direccion)"
                    + "VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrNombre());
            ps.setString(2, cliente.getStrCedula());
            ps.setString(3, cliente.getStrTelf());
            ps.setDouble(4, cliente.getDblDeuda());
            ps.setString(5, cliente.getStrDireccion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El nombre o la cédula ya están asignados a otro cliente");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public boolean InsertarCliente2(Clientes cliente) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO clientes (Nombres, Cedula_Cliente, Telefono, Deuda, Direccion, Credito, "
                    + "autorizado_por, monto_aprobado)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrNombre());
            ps.setString(2, cliente.getStrCedula());
            ps.setString(3, cliente.getStrTelf());
            ps.setDouble(4, cliente.getDblDeuda());
            ps.setString(5, cliente.getStrDireccion());
            ps.setBoolean(6, cliente.isCredito());
            ps.setString(7, cliente.getUsuario());
            ps.setDouble(8, cliente.getCant());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El nombre o la cédula ya están asignados a otro cliente");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void agregarDeuda(Clientes cliente) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes SET Deuda = ? WHERE Cedula_Cliente = ? ";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, cliente.getDblDeuda());
            ps.setString(2, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar deuda del cliente en la base de datos\nError 040", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actualizarCliente(Clientes cliente) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes SET Nombres = ?, Telefono = ?, Direccion = ? WHERE Cedula_Cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrNombre());
            ps.setString(2, cliente.getStrTelf());
            ps.setString(3, cliente.getStrDireccion());
            ps.setString(4, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "El nombre o número cédula ingresado ya está\n"
                    + "asignado a otro cliente");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean eliminarCliente(Clientes cliente) {
        boolean bandera;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes SET estado = false WHERE cedula_cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrCedula());
            /////////////////////////////
            /////////////////////////////
            /////////////////////////////
            /////////////////////////////
            /////////////////////////////falta executequery
            
            
            bandera = true;
        } catch (SQLException ex) {
            bandera= false;
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al dar de baja al cliente en la base de datos\nError 042", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public ArrayList<Clientes> FacturaCliente(String nombre) {
        ArrayList<Clientes> listaCliente = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT nombres, direccion, deuda, telefono, credito, monto_aprobado FROM clientes WHERE cedula_cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreCli = rs.getString(1);
                String direccion = rs.getString(2);
                double deuda = rs.getDouble(3);
                String telf = rs.getString(4);
                boolean credito = rs.getBoolean(5);
                double cant = rs.getDouble(6);
                cliente = new Clientes(nombreCli, deuda, direccion, telf, credito, cant);
                listaCliente.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 043", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCliente;
    }
}
