package BL;

import Clases.Venta;
import Dat.DATVenta;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLVenta {
    
    Venta objV = new Venta();
    DATVenta manejadorVenta = new DATVenta();
    public int crearVenta(Venta objVenta) throws SQLException, ClassNotFoundException{
        return manejadorVenta.crearVenta(objVenta.getIntIdVenta(), objVenta.getDblTotalVenta(),
                objVenta.getDblValCancelado(), objVenta.getStrFecha(), objV.getCedulaUser());
    }
    
    public int cuentaVentas() throws SQLException, ClassNotFoundException {
        int numRows = 0;
        ResultSet rs = manejadorVenta.CuentaVentas();
        while (rs.next()) {
            numRows = rs.getInt(1);
        }
        return numRows;
    }
    
    public ArrayList<Object[]> vistaVenta(String fecha) throws SQLException, ClassNotFoundException{
        ArrayList <Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorVenta.vistaVenta(fecha);
        ResultSetMetaData rm = rs.getMetaData();
        int nroCol = rm.getColumnCount();
        while(rs.next()){
            Object[] filas = new Object[nroCol];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object[]> cargaVentasNoCancel() throws SQLException, ClassNotFoundException{
        ArrayList <Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorVenta.cargaVentasNoCalcel();
        ResultSetMetaData rm = rs.getMetaData();
        int nroCol = rm.getColumnCount();
        while(rs.next()){
            Object[] filas = new Object[nroCol];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public int actualizaDeudaCompra(Venta objV) throws SQLException, ClassNotFoundException{
        return manejadorVenta.actualizaPago(objV.getIntIdVenta(), objV.getDblValCancelado());
    }
}
