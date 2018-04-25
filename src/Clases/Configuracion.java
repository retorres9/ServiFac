package Clases;

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

    public String validacion() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "validacion.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String configNombreEmpresa() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ConfigNombreEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String configDireccionEmpresa() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ConfigDireccionEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String configPropietarioEmpresa() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ConfigPropietarioEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String configRUCEmpresa() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ConfigRUCEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String configTelefonoEmpresa() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ConfigTelefonoEmpresa.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;

    }

    public void actualizaNombreEmp(String updNombre) throws IOException {
        
        File yourFile = new File("C:\\Program Files\\dist" + File.separator + "ConfigNombreEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = "C:\\Program Files\\dist" + File.separator + "ConfigNombreEmpresa.txt";
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
        File yourFile = new File("C:\\Program Files\\dist" + File.separator + "ConfigDireccionEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = "C:\\Program Files\\dist" + File.separator + "ConfigDireccionEmpresa.txt";
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
        File yourFile = new File("C:\\Program Files\\dist" + File.separator + "ConfigPropietarioEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = "C:\\Program Files\\dist" + File.separator + "ConfigPropietarioEmpresa.txt";
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
        File yourFile = new File("C:\\Program Files\\dist" + File.separator + "ConfigRUCEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = "C:\\Program Files\\dist" + File.separator + "ConfigRUCEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigTelefonoEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "ConfigTelefonoEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Validacion.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "Validacion.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Vendedor.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }

        String ruta = workingDirectory + File.separator + "Vendedor.txt";
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

    public String vendedor_venta() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "Vendedor.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String iva() {

        String s1 = "";
        try {
            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "Iva.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String claveAdmin() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "ClaveAdmin.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String usuario() {
        String s1 = "";
        try {

            String dir = "C:\\Program Files\\dist";
            BufferedReader br = new BufferedReader(new FileReader(dir + File.separator + "Usuario.txt"));
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
