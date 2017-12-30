package Dat;

import Clases.Producto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageInputStream;

public class DATMaterial {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    FileImageInputStream fis;
    int longitudbytes = 9;
    Producto prod = new Producto();

    public ArrayList<Producto> Consultar() throws ClassNotFoundException {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto ORDER BY Nombre_Producto Asc";
            ps = con.prepareStatement(Sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String codigo = rs.getString(2);
                double precio = rs.getDouble(3);
                double precioMayor = rs.getDouble(4);
                String ubicacion = rs.getString(5);
                int cant = rs.getInt(6);

                prod = new Producto(nombre, codigo, precio, precioMayor, ubicacion, cant, 2, null, null, null);
                listaProductos.add(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProductos;
    }

    public ArrayList<Producto> ConsultarMinimo() throws SQLException {
        ArrayList<Producto> listadoMinimos = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad, Cantidad_Minima, Empresa FROM producto WHERE Cantidad <= Cantidad_Minima";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProd = rs.getString(1);
                String cod = rs.getString(2);
                double precio = rs.getDouble(3);
                double precioProdMayor = rs.getDouble(4);
                String ubi = rs.getString(5);
                int cant = rs.getInt(6);
                Producto prod = new Producto(nombreProd, cod, precio, precioProdMayor, ubi, cant);
                listadoMinimos.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            con.close();
        }
        return listadoMinimos;
    }

    public ArrayList<Producto> ConsultarPorNombre(String nombre) throws SQLException {
        ArrayList<Producto> listaProductosNombre = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto WHERE Nombre_Producto REGEXP CONCAT('^',?) OR Codigo REGEXP CONCAT ('^',?) ORDER BY Nombre_Producto Asc";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, nombre);
            ps.setString(2, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProd = rs.getString(1);
                String cod = rs.getString(2);
                double precio = rs.getDouble(3);
                double precioProdMayor = rs.getDouble(4);
                String ubi = rs.getString(5);
                int cant = rs.getInt(6);
                prod = new Producto(nombreProd, cod, precio, precioProdMayor, ubi, cant);
                listaProductosNombre.add(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            con.close();
        }
        return listaProductosNombre;
    }
//
//    public ResultSet ConsultarCodigo(String codigo) throws ClassNotFoundException, SQLException {
//        //Statement st = c.getConnection().createStatement();
//        String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto WHERE Codigo REGEXP CONCAT('^',?)";
//        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
//        //ResultSet re = st.executeQuery(Sentencia);
//        ps.setString(1, codigo);
//        return ps.executeQuery();
//    }

    public void IngresarProducto(String strNombre, String strCodigo, double fltPrecio, double fltPrecioMayor, String strUbicacion, int intCantidad, int intCantidadminima, String strEmpresa, File imagen) throws ClassNotFoundException {

        try {
            //File fotoProd = prod.getImgCodigoProd();
            //FileInputStream fis = new FileInputStream(fotoProd);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "INSERT INTO producto (Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad, Cantidad_Minima, Empresa, Imagen_Codigo)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, strNombre);
            ps.setString(2, strCodigo);
            ps.setDouble(3, fltPrecio);
            ps.setDouble(4, fltPrecioMayor);
            ps.setString(5, strUbicacion);
            ps.setInt(6, intCantidad);
            ps.setInt(7, intCantidadminima);
            ps.setString(8, strEmpresa);
            ps.setBinaryStream(9, fis, (int) prod.getImgCodigoProd().length());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {

                }

            }
        }
    }

    public void UpdateFormaCantMin(Producto producto) throws ClassNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET Cantidad_Minima = ? WHERE Nombre_Producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, producto.getIntCantidad());
            ps.setString(2, producto.getStrNombreProd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ps.close();
            con.close();
        }
    }
//
    public int UpdateCantFactura(String nombre, int strCant) throws ClassNotFoundException, SQLException {
        String Sentencia = "UPDATE producto SET Cantidad = ? WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setInt(1, strCant);
        ps.setString(2, nombre);
        return ps.executeUpdate();
    }
//

    public void UpdateProducto(Producto producto){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "UPDATE producto SET Nombre_Producto = ? WHERE Codigo = ?";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, producto.getStrNombreProd());
            ps.setString(2, producto.getStrCod());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void UpdateNombreProducto(Producto producto, String codigo){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "UPDATE producto SET Nombre_Producto = ?,Precio = ?, Precio_Mayor = ?, Cantidad = ?, Ubicacion = ? WHERE Codigo = ?";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, producto.getStrNombreProd());
            ps.setDouble(2, producto.getFltPrecio());
            ps.setDouble(3, producto.getFltPrecioMayor());
            ps.setInt(4, producto.getIntCantidad());
            ps.setString(5, producto.getStrUbicacion());
            ps.setString(6, producto.getStrCod());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//    //mod abajo
    public int CuentaRegistros() {
        int cant = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT count(*) FROM producto WHERE Cantidad <= Cantidad_Minima";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                cant = rs.getInt(1);
                Producto prod = new Producto();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cant;
    }
//
//    public int eliminarProducto(String strNombre) throws ClassNotFoundException, SQLException {
//        String sentencia = "DELETE FROM producto WHERE Nombre_Producto = ?";
//        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
//        ps.setString(1, strNombre);
//        return ps.executeUpdate();
//    }
//
//    public ResultSet Venta(String codigo) throws ClassNotFoundException, SQLException {
//        String Sentencia = "SELECT Nombre_Producto, Precio, Codigo FROM producto WHERE Cantidad > 0 AND Codigo = ?";
//        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
//        ps.setString(1, codigo);
//        return ps.executeQuery();
//    }
//
//    public ResultSet comprobarCant(String nombre) throws ClassNotFoundException, SQLException {
//        String sentencia = "SELECT Cantidad FROM producto Where Nombre_Producto = ?";
//        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
//        ps.setString(1, nombre);
//        return ps.executeQuery();
//    }
}
