package BL;

import Clases.Proveedor;
import Dat.DATProveedor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLProveedor {
    DATProveedor manejadorProveedor = new DATProveedor();
    
//    public int crearProveedor(Proveedor objP) throws ClassNotFoundException, SQLException{
//        return manejadorProveedor.ingresoProveedor(objP.getStrEmpresa(), objP.getStrNombreCuenta(), objP.getStrTipo(), objP.getStrNumCuenta(), objP.getDblDeuda(), objP.getIntTelf());
//    }
    
    public ArrayList<Object[]> cargarTabla() throws ClassNotFoundException, SQLException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorProveedor.cargarTabla();
        ResultSetMetaData rsm = rs.getMetaData();
        int numCol = rsm.getColumnCount();
        
        while(rs.next()){
            Object[] filas = new Object[numCol];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object []> cargarProveedores() throws SQLException, ClassNotFoundException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorProveedor.cargarProveedores();
        ResultSetMetaData rm = rs.getMetaData();
        int col = rm.getColumnCount();
        
        while(rs.next()){
            Object[] filas = new Object[col];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i+1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object[]> buscarNombre(String nombre) throws ClassNotFoundException, SQLException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorProveedor.buscarNombre(nombre);
        ResultSetMetaData rsm = rs.getMetaData();
        int numCol = rsm.getColumnCount();
        
        while(rs.next()){
            Object[] filas = new Object[numCol];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object[]> buscarNombreEmpresa (String texto) throws ClassNotFoundException, SQLException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorProveedor.buscarNombreCuenta(texto);
        ResultSetMetaData rsm = rs.getMetaData();
        int numCol = rsm.getColumnCount();
        
        while(rs.next()){
            Object[] filas = new Object[numCol];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    public int updateDeuda(Proveedor objP) throws ClassNotFoundException, SQLException{
        return manejadorProveedor.updateDeuda(objP.getDblDeuda(), objP.getStrEmpresa());
    }
    public int eliminarProveedor(Proveedor objP) throws ClassNotFoundException, SQLException{
        return manejadorProveedor.eliminarProveedor(objP.getStrEmpresa());
    }
}
