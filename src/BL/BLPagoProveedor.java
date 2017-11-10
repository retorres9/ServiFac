package BL;

import Clases.PagoProveedorClase;
import Dat.DATPagoProveedor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLPagoProveedor {

    
    DATPagoProveedor manejadorPago = new DATPagoProveedor();
    
    public ArrayList<Object[]> verPagoPorFecha(String strFecha) throws SQLException, ClassNotFoundException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorPago.verPagosPorFecha(strFecha);
        ResultSetMetaData rm = rs.getMetaData();
        int col = rm.getColumnCount();
        while(rs.next()){
            Object[] filas = new Object[col];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ArrayList<Object[]> verPagos(String empresa) throws SQLException, ClassNotFoundException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorPago.verPagos(empresa);
        ResultSetMetaData rm = rs.getMetaData();
        int col = rm.getColumnCount();
        while(rs.next()){
            Object[] filas = new Object[col];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public int pago(PagoProveedorClase objPp) throws ClassNotFoundException, SQLException{
        return manejadorPago.pagoProveedor(objPp.getStrEmpresa(), objPp.getStrUsuario(), objPp.getDblMontoCancelado(), objPp.getStrFecha(),
                objPp.getStrTipo());
    }
    
}
