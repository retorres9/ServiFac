package BL;

import Clases.Producto;
import Dat.DATConexion;
import Dat.DATMaterial;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLMaterial {

    DATMaterial ManejadorMaterial = new DATMaterial();
    DATConexion con = new DATConexion();

//    public ArrayList<Object[]> getMaterial()
//            throws ClassNotFoundException, SQLException {
//
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.Consultar();
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//        while (rs.next()) {
//            Object[] filas = new Object[nroColumnas];
//            for (int i = 0; i < filas.length; i++) {
//                filas[i] = rs.getObject(i + 1);
//            }
//            datos.add(filas);
//        }
//        return datos;
//    }
//
//    public ArrayList<Object[]> getMaterialMinimo() throws SQLException, ClassNotFoundException {
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.ConsultarMinimo();
//        ResultSetMetaData rm = rs.getMetaData();
//        try {
//
//            int nroColumnas = rm.getColumnCount();
//
//            while (rs.next()) {
//
//                Object[] filas = new Object[nroColumnas];
//
//                for (int i = 0; i < filas.length; i++) {
//
//                    filas[i] = rs.getObject(i + 1);
//
//                }
//
//                datos.add(filas);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BLMaterial.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return datos;
//    }
//
//    public ArrayList<Object[]> getMaterial2(String nombre)
//            throws ClassNotFoundException, SQLException {
//
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.ConsultarPorNombre(nombre);
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//
//        while (rs.next()) {
//
//            Object[] filas = new Object[nroColumnas];
//
//            for (int i = 0; i < filas.length; i++) {
//
//                filas[i] = rs.getObject(i + 1);
//
//            }
//
//            datos.add(filas);
//
//        }
//        return datos;
//    }
//
//    public ArrayList<Object[]> getMaterialCodigo(String codigo)
//            throws ClassNotFoundException, SQLException {
//
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.ConsultarCodigo(codigo);
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//
//        while (rs.next()) {
//
//            Object[] filas = new Object[nroColumnas];
//
//            for (int i = 0; i < filas.length; i++) {
//
//                filas[i] = rs.getObject(i + 1);
//
//            }
//
//            datos.add(filas);
//
//        }
//        return datos;
//    }
//
//    public ArrayList<Object[]> venta(String codigo)
//            throws ClassNotFoundException, SQLException {
//
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.Venta(codigo);
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//
//        while (rs.next()) {
//
//            Object[] filas = new Object[nroColumnas];
//
//            for (int i = 0; i < filas.length; i++) {
//
//                filas[i] = rs.getObject(i + 1);
//
//            }
//
//            datos.add(filas);
//
//        }
//        return datos;
//    }
//
    public void IngresarProductoBl(Producto objMaterial) throws ClassNotFoundException, SQLException {
        ManejadorMaterial.IngresarProducto(objMaterial.getStrNombreProd(),
                objMaterial.getStrCod(),
                objMaterial.getFltPrecio(),
                objMaterial.getFltPrecioMayor(),
                objMaterial.getStrUbicacion(),
                objMaterial.getIntCantidad(),
                objMaterial.getIntCantidadMinima(),
                objMaterial.getStrEmpresa(),
                objMaterial.getImgCodigoProd()
        );
    }
//
//    public int updateCantMin(Producto objMaterial) throws SQLException, ClassNotFoundException {
//        return ManejadorMaterial.UpdateFormaCantMin(objMaterial.getIntCantidadMinima(), objMaterial.getStrNombreProd());
//    }
//
//    public int updateCant(Producto objMaterial) throws SQLException, ClassNotFoundException {
//        return ManejadorMaterial.UpdateCantFactura(objMaterial.getStrNombreProd(), objMaterial.getIntCantidad());
//    }
//
//    public int updateProducto(Producto objMaterial) throws ClassNotFoundException, SQLException {
//        return ManejadorMaterial.UpdateProducto(objMaterial.getStrNombreProd(), objMaterial.getFltPrecio(),
//                objMaterial.getFltPrecioMayor(), objMaterial.getStrUbicacion());
//    }
//
//    public int updateProducto2(Producto objMaterial, String n) throws ClassNotFoundException, SQLException {
//        return ManejadorMaterial.UpdateProducto2(objMaterial.getStrNombreProd(), objMaterial.getFltPrecio(),
//                objMaterial.getFltPrecioMayor(), objMaterial.getStrUbicacion(), n);
//    }
//
//    public int CuentaRegistros() throws ClassNotFoundException, SQLException {
//        int numRows = 0;
//        ResultSet rs = (ResultSet) ManejadorMaterial.CuentaRegistros();
//        if (rs.next()) {
//            numRows = rs.getInt(1);
//        }
//        return numRows;
//    }
//
//    public int eliminarProducto(Producto objProducto) throws ClassNotFoundException, SQLException {
//        return ManejadorMaterial.eliminarProducto(objProducto.getStrNombreProd());
//    }
//
//    public ArrayList<Object[]> comprobarCant(String nombre)
//            throws ClassNotFoundException, SQLException {
//
//        ArrayList<Object[]> datos = new ArrayList<>();
//        ResultSet rs = ManejadorMaterial.comprobarCant(nombre);
//        ResultSetMetaData rm = rs.getMetaData();
//        int nroColumnas = rm.getColumnCount();
//
//        while (rs.next()) {
//
//            Object[] filas = new Object[nroColumnas];
//
//            for (int i = 0; i < filas.length; i++) {
//
//                filas[i] = rs.getObject(i + 1);
//
//            }
//
//            datos.add(filas);
//
//        }
//        return datos;
//    }
}
