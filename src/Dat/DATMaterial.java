package Dat;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.stream.FileImageInputStream;

public class DATMaterial {

    Dat.DATConexion c = new DATConexion();
    FileImageInputStream fis;
    int longitudbytes = 9;

    public ResultSet Consultar() throws ClassNotFoundException, SQLException {
        Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto ORDER BY Nombre_Producto Asc";
        ResultSet re = st.executeQuery(Sentencia);
        c.CerrarConexion();
        return re;
    }

    public ResultSet ConsultarMinimo() throws ClassNotFoundException, SQLException {
        Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad, Cantidad_Minima, Empresa FROM producto WHERE Cantidad <= Cantidad_Minima";
        ResultSet re = st.executeQuery(Sentencia);
        //re.close();
        return re;
    }

    public ResultSet Consultar2(String nombre) throws ClassNotFoundException, SQLException {
        //Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto WHERE Nombre_Producto REGEXP CONCAT('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        //ResultSet re = st.executeQuery(Sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }

    public ResultSet ConsultarCodigo(String codigo) throws ClassNotFoundException, SQLException {
        //Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad FROM producto WHERE Codigo REGEXP CONCAT('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        //ResultSet re = st.executeQuery(Sentencia);
        ps.setString(1, codigo);
        return ps.executeQuery();
    }

    public int IngresarProducto(String strNombre1, String strCodigo, double fltPrecio, double fltPrecioMayor, String strUbicacion, int intCantidad, int intCantidadminima, String strEmpresa, InputStream imagen) throws ClassNotFoundException, SQLException {
        String Sentencia = "INSERT INTO producto (Nombre_Producto, Codigo, Precio, Precio_Mayor, Ubicacion, Cantidad, Cantidad_Minima, Empresa, Imagen_Codigo)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, strNombre1);
        ps.setString(2, strCodigo);
        ps.setDouble(3, fltPrecio);
        ps.setDouble(4, fltPrecioMayor);
        ps.setString(5, strUbicacion);
        ps.setInt(6, intCantidad);
        ps.setInt(7, intCantidadminima);
        ps.setString(8, strEmpresa);
        ps.setBlob(9, imagen);
        return ps.executeUpdate();
    }

    public int UpdateFormaCantMin(int strCantMin, String nombre) throws ClassNotFoundException, SQLException {
        String Sentencia = "UPDATE producto SET Cantidad_Minima = ? WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setInt(1, strCantMin);
        ps.setString(2, nombre);
        return ps.executeUpdate();
    }

    public int UpdateCant(String nombre, int strCant) throws ClassNotFoundException, SQLException {
        String Sentencia = "UPDATE producto SET Cantidad = ? WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setInt(1, strCant);
        ps.setString(2, nombre);
        return ps.executeUpdate();
    }

    public int UpdateProducto(String nombre, double dblPrecio, double dblPrecioMayor, String strUbicacion) throws ClassNotFoundException, SQLException {
        String Sentencia = "UPDATE producto SET Nombre_Producto = ?,Precio = ?, Precio_Mayor = ?, Ubicacion = ? WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, nombre);
        ps.setDouble(2, dblPrecio);
        ps.setDouble(3, dblPrecioMayor);
        ps.setString(4, strUbicacion);
        ps.setString(5, nombre);
        return ps.executeUpdate();
    }

    public int UpdateProducto2(String nombre, double dblPrecio, double dblPrecioMayor, String strUbicacion, String n) throws ClassNotFoundException, SQLException {
        String Sentencia = "UPDATE producto SET Nombre_Producto = ?,Precio = ?, Precio_Mayor = ?, Ubicacion = ? WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, nombre);
        ps.setDouble(2, dblPrecio);
        ps.setDouble(3, dblPrecioMayor);
        ps.setString(4, strUbicacion);
        ps.setString(5, n);
        return ps.executeUpdate();
    }

    //mod abajo
    public ResultSet CuentaRegistros() throws ClassNotFoundException, SQLException {
        ResultSet rs= null;
        String sentencia = "SELECT count(*) FROM producto WHERE Cantidad <= Cantidad_Minima";
        PreparedStatement pst = c.getConnection().prepareStatement(sentencia);
        rs = pst.executeQuery();
        return rs;
        
    }

    public int eliminarProducto(String strNombre) throws ClassNotFoundException, SQLException {
        String sentencia = "DELETE FROM producto WHERE Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strNombre);
        return ps.executeUpdate();
    }

    public ResultSet Venta(String codigo) throws ClassNotFoundException, SQLException {
        String Sentencia = "SELECT Nombre_Producto, Precio, Codigo FROM producto WHERE Cantidad > 0 AND Codigo = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, codigo);
        return ps.executeQuery();
    }

    public ResultSet comprobarCant(String nombre) throws ClassNotFoundException, SQLException {
        String sentencia = "SELECT Cantidad FROM producto Where Nombre_Producto = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }
}