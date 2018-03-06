package Clases;

import Dat.DATConexion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author rober
 */
public class GenerarReporte {
    DATConexion cc = new DATConexion();
    Connection cn = cc.conexion();
    public void generarFactura(String cedula){
        
        try{
            JasperReport reporte = (JasperReport) JRLoader.loadObject("factura.jasper");
            Map parametro = new HashMap();
            
            parametro.put("cedula", cedula);
            System.out.println(parametro);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, new JREmptyDataSource());
            JasperViewer.viewReport(j);
            //jv.setTitle("factura");
        } catch (JRException ex){
            ex.printStackTrace();
        }
        
    }
    
}
