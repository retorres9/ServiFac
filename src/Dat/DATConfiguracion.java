/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dat;

import Clases.Configuracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class DATConfiguracion {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    public ArrayList<Configuracion> cargaConfig(){
        ArrayList<Configuracion> config = new ArrayList<Configuracion>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "SELECT * FROM configuracion";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while(rs.next()){
                String empresa = rs.getString(1);
                String direccion = rs.getString(2);
                String propietario = rs.getString(3);
                int iva = rs.getInt(4);
                String ruc = rs.getString(5);
                String telf = rs.getString(6);
                Configuracion objConfig = new Configuracion(empresa, direccion, propietario, iva, ruc, telf);
                config.add(objConfig);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cargar la configuración desde la base de datos\nError 023", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }
    
    public ArrayList<Configuracion> obtenerCredencial(){
        ArrayList<Configuracion> credencial = new ArrayList<Configuracion>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentecia = "SELECT contrasena, empresa FROM configuracion";
            ps = con.prepareStatement(sentecia);
            rs = ps.executeQuery();
            while(rs.next()){
                String pass = rs.getString(1);
                String emp = rs.getString(2);
                Configuracion objConf = new Configuracion(emp, pass);
                credencial.add(objConf);
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Ha ocurrido un error al obtener las credenciales del usuario\nError 024", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return credencial;
    }
    
    public void actualizaConfig(Configuracion config){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "UPDATE configuracion SET empresa = ?, direccion = ?,"
                    + "propietario = ?, iva = ?, ruc = ?, telefono = ? WHERE id = 1";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, config.getEmpresa());
            ps.setString(2, config.getDireccion());
            ps.setString(3, config.getPropietario());
            ps.setInt(4, config.getIva());
            ps.setString(5, config.getRuc());
            ps.setString(6, config.getTelefono());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar la configuración en la base de datos\nError 024", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
