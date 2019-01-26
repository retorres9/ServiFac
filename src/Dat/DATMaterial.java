package Dat;

import Clases.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATMaterial {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Producto> Consultar() throws ClassNotFoundException {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            //con = DriverManager.getConnection("jdbc:mysql://54.207.39.14:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT p.Nombre_Producto, p.Codigo,"
                    + " p.precio_compra, p.precio, p.Precio_Mayor, "
                    + " u.nombre_ubicacion, p.Cantidad , p.iva FROM "
                    + "producto p, ubicacion u WHERE p.id_ubicacion = u.id_ubicacion "
                    + "AND stock = true ORDER BY p.Nombre_Producto Asc";
            ps = con.prepareStatement(Sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String codigo = rs.getString(2);
                double precioCompra = rs.getDouble(3);
                double precio = rs.getDouble(4);
                double precioMayor = rs.getDouble(5);
                String ubicacion = rs.getString(6);
                int cant = rs.getInt(7);
                String iva = rs.getString(8);
                Producto prod = new Producto(nombre, codigo, precio, precioCompra, precioMayor, cant, ubicacion, iva);
                listaProductos.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 001", "Error!", JOptionPane.ERROR_MESSAGE);
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

    public ArrayList<Producto> busquedaProducto(String codigo) {
        ArrayList<Producto> listadoProd = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT nombre_producto, precio, precio_mayor, cantidad FROM producto WHERE codigo = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                double precioPublico = rs.getDouble(2);
                double precioMayor = rs.getDouble(3);
                int cantidad = rs.getInt(4);
                Producto prod = new Producto(nombre, precioPublico, precioMayor, cantidad);
                listadoProd.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 002", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoProd;
    }

    public boolean actualizaCategoria(int id, String nombre) {
        boolean bandera;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET id_categoria = ? WHERE nombre_producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.executeUpdate();
            bandera = true;
        } catch (SQLException ex) {
            bandera = false;
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar la categoria del producto");
        }
        return bandera;
    }

    public boolean actualizaProveedor(String ruc, String nombre) {
        boolean bandera;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET ruc = ? WHERE nombre_producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, ruc);
            ps.setString(2, nombre);
            ps.executeUpdate();
            bandera = true;
        } catch (SQLException ex) {
            bandera = false;
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar la categoria del producto");
        }
        return bandera;
    }

    public ArrayList<Producto> existenciasBodega() {
        ArrayList<Producto> listadoEnBodega = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT p.Nombre_Producto, p.Codigo, "
                    + "b.nombre_bodega, eb.cantidad, p.precio FROM "
                    + "producto p, existenciasbodega eb, bodega b WHERE eb.id_bodega = b.id_bodega "
                    + "AND p.codigo = eb.codigo AND stock = true AND eb.cantidad > 0 ORDER BY p.Nombre_Producto Asc";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String codigo = rs.getString(2);
                String bodega = rs.getString(3);
                int cant = rs.getInt(4);
                double precio = rs.getDouble(5);
                Producto prod = new Producto(nombre, codigo, cant, bodega, precio);
                listadoEnBodega.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 003", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoEnBodega;
    }

    public ArrayList<Producto> ConsultarMinimos() {
        ArrayList<Producto> listadoMinimos = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT distinct p.Nombre_Producto, p.Codigo, p.Precio, "
                    + "p.Precio_Mayor, u.nombre_ubicacion, p.Cantidad, "
                    + "p.Cantidad_Minima, pr.Empresa FROM categoria cat, producto p, ubicacion u, "
                    + "proveedores pr WHERE p.id_ubicacion = u.id_ubicacion  AND "
                    + "p.ruc = pr.ruc AND cantidad<cantidad_minima ORDER BY pr.Empresa, p.Nombre_Producto ";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProd = rs.getString(1);
                String cod = rs.getString(2);
                double precio = rs.getDouble(3);
                double precioProdMayor = rs.getDouble(4);
                String ubi = rs.getString(5);
                int cant = rs.getInt(6);
                int cantMin = rs.getInt(7);
                String empresa = rs.getString(8);
                Producto prod = new Producto(nombreProd, cod, precio, precioProdMayor, ubi, cant, cantMin, empresa);
                listadoMinimos.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 004", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoMinimos;
    }

    public ArrayList<Producto> obtenerDetalleProd(String cod) {
        ArrayList<Producto> detalleProd = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT p.nombre_producto, p.precio, p.precio_mayor, p.cantidad, u.nombre_ubicacion, c.nombre_categoria, prov.empresa "
                    + "FROM producto p, ubicacion u, categoria c, proveedores prov WHERE codigo = ? AND p.id_ubicacion = u.id_ubicacion "
                    + "AND p.id_categoria = c.id_categoria AND p.ruc = prov.ruc";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProd = rs.getString(1);
                double precioNormal = rs.getDouble(2);
                double precioMayor = rs.getDouble(3);
                int cantidad = rs.getInt(4);
                String ubicacion = rs.getString(5);
                String categoria = rs.getString(6);
                String proveedor = rs.getString(7);
                Producto prod = new Producto(nombreProd, precioNormal, precioMayor, cantidad, ubicacion, categoria, proveedor);
                detalleProd.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 005", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return detalleProd;
    }

    public ArrayList<Producto> ConsultarMinimo(String prov) {
        ArrayList<Producto> listadoMinimos = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT distinct p.Nombre_Producto, p.Codigo, p.Precio, "
                    + "p.Precio_Mayor, u.nombre_ubicacion, p.Cantidad, "
                    + "p.Cantidad_Minima, pr.Empresa FROM categoria cat, producto p, ubicacion u, "
                    + "proveedores pr WHERE p.id_ubicacion = u.id_ubicacion  AND "
                    + "p.ruc = pr.ruc AND cantidad<cantidad_minima " + "AND pr.empresa ='" + prov + "' ORDER BY pr.Empresa, p.Nombre_Producto ";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProd = rs.getString(1);
                String cod = rs.getString(2);
                double precio = rs.getDouble(3);
                double precioProdMayor = rs.getDouble(4);
                String ubi = rs.getString(5);
                int cant = rs.getInt(6);
                int cantMin = rs.getInt(7);
                String empresa = rs.getString(8);
                Producto prod = new Producto(nombreProd, cod, precio, precioProdMayor, ubi, cant, cantMin, empresa);
                listadoMinimos.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 006", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoMinimos;
    }

    public ArrayList<Producto> ConsultarPorNombre(String nombre) throws SQLException {
        ArrayList<Producto> listaProductosNombre = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT p.Nombre_Producto, p.Codigo, p.precio_compra,"
                    + " p.precio, p.Precio_Mayor,"
                    + " u.nombre_ubicacion, p.Cantidad, p.iva FROM producto p, ubicacion u "
                    + "WHERE  p.id_ubicacion = u.id_ubicacion AND stock = true AND Nombre_Producto LIKE '%" + nombre + "%'"
                    + "ORDER BY Nombre_Producto Asc";
            ps = con.prepareStatement(Sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreprod = rs.getString(1);
                String codigo = rs.getString(2);
                double precioCompra = rs.getDouble(3);
                double precio = rs.getDouble(4);
                double precioMayor = rs.getDouble(5);
                String ubicacion = rs.getString(6);
                int cant = rs.getInt(7);
                String iva = rs.getString(8);
                Producto prod = new Producto(nombreprod, codigo, precio, precioCompra, precioMayor, cant, ubicacion, iva);
                listaProductosNombre.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 007", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            rs.close();
            con.close();
        }
        return listaProductosNombre;
    }

    public ArrayList<Producto> ConsultarPorCodigo(String nombre) {
        ArrayList<Producto> listaProductosNombre = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT p.Nombre_Producto, p.Codigo, p.precio_compra,"
                    + " p.precio, p.Precio_Mayor,"
                    + " u.nombre_ubicacion, p.Cantidad, p.iva FROM producto p, ubicacion u "
                    + "WHERE  p.id_ubicacion = u.id_ubicacion AND stock = true AND Codigo "
                    + "REGEXP CONCAT ('^',?) ORDER BY Nombre_Producto Asc";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreprod = rs.getString(1);
                String codigo = rs.getString(2);
                double precioCompra = rs.getDouble(3);
                double precio = rs.getDouble(4);
                double precioMayor = rs.getDouble(5);
                String ubicacion = rs.getString(6);
                int cant = rs.getInt(7);
                String iva = rs.getString(8);
                Producto prod = new Producto(nombreprod, codigo, precio, precioCompra, precioMayor, cant, ubicacion, iva);
                listaProductosNombre.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 008", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProductosNombre;
    }

    public void actualizarUbicacion(Producto producto) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET id_ubicacion = ? WHERE nombre_producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, producto.getIntUbicacion());
            ps.setString(2, producto.getStrNombreProd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar la ubicacion del producto\nen la base de datos\nError 009", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean IngresarProducto(Producto producto) throws SQLException, ClassNotFoundException {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "INSERT INTO producto (nombre_producto, codigo,"
                    + " precio_Compra, precio, precio_mayor, ganancia, ganancia_Mayor,"
                    + "id_categoria, id_ubicacion, cantidad, cantidad_Minima,"
                    + " ruc, iva)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, producto.getStrNombreProd());
            ps.setString(2, producto.getStrCod());
            ps.setDouble(3, producto.getPrecioCompra());
            ps.setDouble(4, producto.getFltPrecio());
            ps.setDouble(5, producto.getFltPrecioMayor());
            ps.setDouble(6, producto.getGanancia());
            ps.setDouble(7, producto.getGananciaMayor());
            ps.setInt(8, producto.getIdCategoria());
            ps.setInt(9, producto.getIntUbicacion());
            ps.setInt(10, producto.getIntCantidad());
            ps.setInt(11, producto.getIntCantidadMinima());
            ps.setString(12, producto.getStrRUC());
            ps.setString(13, producto.getIva());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nombre o codigo ya ingresados");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void ActualizaPrecio(Producto prod) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET precio_Compra = ?, precio = ?, precio_mayor = ?, ganancia = ?, ganancia_Mayor = ?"
                    + "WHERE codigo = ?";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, prod.getPrecioCompra());
            ps.setDouble(2, prod.getFltPrecio());
            ps.setDouble(3, prod.getFltPrecioMayor());
            ps.setDouble(4, prod.getGanancia());
            ps.setDouble(5, prod.getGananciaMayor());
            ps.setString(6, prod.getStrCod());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 010", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 011", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            ps.close();
            con.close();
        }
    }
//

    public void UpdateProducto(Producto producto) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "UPDATE producto SET Nombre_Producto = ? WHERE Codigo = ?";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, producto.getStrNombreProd());
            ps.setString(2, producto.getStrCod());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 012", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void UpdateNombreProducto(Producto producto, String codigo) {
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 013", "Error!", JOptionPane.ERROR_MESSAGE);
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
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 014", "Error!", JOptionPane.ERROR_MESSAGE);
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

    public void eliminarProducto(String nombre) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET stock = false WHERE codigo = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int validaCodigo(String codigo) {
        int cant = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "select count(codigo) from producto where codigo LIKE ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                cant = rs.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 015", "Error!", JOptionPane.ERROR_MESSAGE);
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

    public ArrayList<Producto> cargaProductoFact(String codigo) {
        ArrayList<Producto> listadoProducto = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://54.207.39.14:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Nombre_Producto, Precio, precio_mayor, Codigo, IVA FROM producto WHERE Codigo = ? AND Cantidad > 0";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                double precio = rs.getDouble(2);
                double precioMayor = rs.getDouble(3);
                String cod = rs.getString(4);
                String iva = rs.getString(5);
                Producto prod = new Producto(nombre, cod, precio, precioMayor, iva);
                listadoProducto.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 016", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoProducto;
    }

    public ArrayList<Producto> comprobarCant(String nombre) {
        ArrayList<Producto> cantidadProd = new ArrayList<Producto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT cantidad FROM producto Where nombre_producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cant = rs.getInt(1);
                Producto prod = new Producto(cant);
                cantidadProd.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 017", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cantidadProd;
    }

    public void UpdateCantFactura(Producto prod) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET Cantidad = cantidad - ? WHERE Nombre_Producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, prod.getIntCantidadMinima());//se recupera ubicacion porque constructor (String, int) ya esta asiganado para actualizar la ubicacion
            ps.setString(2, prod.getStrNombreProd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 018", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void AumentaCant(Producto prod) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET Cantidad = cantidad + ? WHERE Nombre_Producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, prod.getIntCantidadMinima());//se recupera ubicacion porque constructor (String, int) ya esta asiganado para actualizar la ubicacion
            ps.setString(2, prod.getStrNombreProd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 019", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void moverBodega(Producto prod) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE producto SET Cantidad = cantidad + ? WHERE nombre_producto = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, prod.getIntCantidadMinima());
            ps.setString(2, prod.getStrNombreProd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del producto\nError 020", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
