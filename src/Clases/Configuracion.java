package Clases;

import GUI.Factura;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Configuracion {

    public static String guardar(String nombre) {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombre + ".png"));
            s1 = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String validacion() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("validacion.txt"));
            s1 = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String vendedor() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("Vendedor.txt"));
            s1 = br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String configNombreEmpresa() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("ConfigNombreEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String configDireccionEmpresa() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("ConfigDireccionEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String configPropietarioEmpresa() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("ConfigPropietarioEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String configRUCEmpresa() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("ConfigRUCEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public static String configTelefonoEmpresa() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("ConfigTelefonoEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public void actualizaNombreEmp(String updNombre) throws IOException {
        String ruta = "ConfigNombreEmpresa.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(updNombre);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public void actualizaDireccionEmp(String updNombre) throws IOException {
        String ruta = "ConfigDireccionEmpresa.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(updNombre);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }

    }

    public void actualizaPropietarioEmp(String updNombre) throws IOException {
        String ruta = "ConfigPropietarioEmpresa.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(updNombre);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public void actualizaRucEmp(String updNombre) throws IOException {
        String ruta = "ConfigRUCEmpresa.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(updNombre);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public void actualizaTelefonoEmp(String updNombre) throws IOException {
        String ruta = "ConfigTelefonoEmpresa.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(updNombre);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public void actualizaValidacion(String valid) throws IOException {
        String ruta = "Validacion.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(valid);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public void vendedor(String valid) throws IOException {
        String ruta = "Vendedor.txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(valid);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un error");
        }
    }

    public static String vendedor_venta() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("Vendedor.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }
    
    public static String iva() {
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rober\\Desktop\\ServiFac\\Iva.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }
    
    public static String claveAdmin(){
        String s1 = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rober\\Desktop\\ServiFac\\ClaveAdmin.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }
}
