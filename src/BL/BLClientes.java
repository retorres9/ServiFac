package BL;

import Clases.Clientes;
import Dat.DATClientes;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLClientes {

    DATClientes manejadorClientes = new DATClientes();

    public ArrayList<Object[]> clientePagosGUI() throws SQLException, ClassNotFoundException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.ConsultarPagosGui();
        ResultSetMetaData rm = rs.getMetaData();
        int cols = rm.getColumnCount();
        while(rs.next()){
            Object[]filas = new Object[cols];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object[]> getClientexNombre(String nombre)
            throws ClassNotFoundException, SQLException {

        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.ConsultarxNombre(nombre);
        ResultSetMetaData rm = rs.getMetaData();
        int nroColumnas = rm.getColumnCount();

        while (rs.next()) {

            Object[] filas = new Object[nroColumnas];

            for (int i = 0; i < filas.length; i++) {

                filas[i] = rs.getObject(i + 1);

            }

            datos.add(filas);

        }

        return datos;
    }
    
    public ArrayList<Object[]> getCliente()
            throws ClassNotFoundException, SQLException {

        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.ConsultarPagosGui();
        ResultSetMetaData rm = rs.getMetaData();
        int nroColumnas = rm.getColumnCount();

        while (rs.next()) {

            Object[] filas = new Object[nroColumnas];

            for (int i = 0; i < filas.length; i++) {

                filas[i] = rs.getObject(i + 1);

            }

            datos.add(filas);

        }

        return datos;
    }
    public ArrayList<Object[]> busqCliente (String nombre) 
            throws ClassNotFoundException, SQLException{
        
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.ConsultarPorNombre(nombre);
        ResultSetMetaData rm = rs.getMetaData();
        int nroColumnas = rm.getColumnCount();
        
        while (rs.next()) {

            Object[] filas = new Object[nroColumnas];

            for (int i =0; i < filas.length; i++) {

                filas[i] = rs.getObject(i + 1);

            }

            datos.add(filas);

        }
       
        return datos;
    }
    public ArrayList<Object[]> busqClienteCedula (String nombre) 
            throws ClassNotFoundException, SQLException{
        
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.ConsultarCedula(nombre);
        ResultSetMetaData rm = rs.getMetaData();
        int nroColumnas = rm.getColumnCount();
        
        while (rs.next()) {

            Object[] filas = new Object[nroColumnas];

            for (int i =0; i < filas.length; i++) {

                filas[i] = rs.getObject(i + 1);

            }

            datos.add(filas);

        }
       
        return datos;
    }
    
    public ArrayList<Object[]> ClienteFactura (String cedula) 
            throws ClassNotFoundException, SQLException{
        
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorClientes.FacturaCliente(cedula);
        ResultSetMetaData rm = rs.getMetaData();
        int nroColumnas = rm.getColumnCount();
        
        while (rs.next()) {

            Object[] filas = new Object[nroColumnas];

            for (int i =0; i < filas.length; i++) {

                filas[i] = rs.getObject(i + 1);

            }

            datos.add(filas);

        }
       
        return datos;
    }

//    public int InsertarCliente(Clientes objClientes) throws ClassNotFoundException, SQLException {
//        return manejadorClientes.InsertarCliente(objClientes.getStrNombre(), objClientes.getIntCedula(), objClientes.getIntTelf(), objClientes.getDblDeuda()
//        , objClientes.getStrDireccion());
//    }

    public int InsertarDeuda(Clientes objClientes) throws ClassNotFoundException, SQLException {
        return manejadorClientes.agregarDeuda(objClientes.getDblDeuda(), objClientes.getStrNombre());
    }
    
    public int updateCliente(Clientes objClientes, String n) throws ClassNotFoundException, SQLException{
        return manejadorClientes.actualizarCliente(objClientes.getStrNombre(), objClientes.getIntCedula(), objClientes.getIntTelf(),n);
    }
    public int eliminarCliente(Clientes objClientes) throws ClassNotFoundException, SQLException{
        return manejadorClientes.eliminarCliente(objClientes.getStrNombre());
    }
}
