package BL;

import Clases.Fechas;
import Clases.DetalleVenta;
import Dat.DATReporte;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLReporte {

    DetalleVenta objRep = new DetalleVenta();
    DATReporte manejadorReporte = new DATReporte();
    Fechas ocjF = new Fechas();

    public int creaReport(DetalleVenta objRep) throws ClassNotFoundException, SQLException {
        return manejadorReporte.CreaReport(objRep.getIntCed(), objRep.getIntCant(), objRep.getIntCod(), objRep.getDblPrecioVenta(), objRep.getStrUsuario(), objRep.getId_Venta());
    }

    public ArrayList<Object[]> verRep(String fecha)
            throws ClassNotFoundException, SQLException {

        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorReporte.reporte(fecha);
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
    
    public ArrayList<Object[]> verFact(int id) throws SQLException, ClassNotFoundException{
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorReporte.detalleVenta(id);
        ResultSetMetaData rm = rs.getMetaData();
        int col = rm.getColumnCount();
        while(rs.next()){
            Object[]filas = new Object[col];
            for(int i = 0; i<filas.length;i++){
                filas[i] = rs.getObject(i+1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
    public ResultSet suma(String fecha) throws ClassNotFoundException, SQLException {
        return manejadorReporte.suma(fecha);
    }

//    public int fecha(Fechas objFecha) throws ClassNotFoundException, SQLException {
//        return manejadorReporte.creaFecha(objFecha.getStrFecha());
//    }
//
//    public ResultSet fechacom() throws ClassNotFoundException, SQLException {
//        return manejadorReporte.fechasCom();
//    }
//
//    public ArrayList<Object[]> llencom()
//            throws ClassNotFoundException, SQLException {
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = manejadorReporte.fechasCom();
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//
//        while (rs.next()) {
//            Object[] filas = new Object[nroColumnas];
//            for (int i = 0; i < filas.length; i++) {
//                filas[i] = rs.getObject(i + 1);
//            }
//            datos.add(filas);
//        }
//        return datos;
//    }

    
}
