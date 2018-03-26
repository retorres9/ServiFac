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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "validacion.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\validacion.txt"));
            s1 = br.readLine();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s1;
    }

    public String vendedor() {
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Vendedor.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\Vendedor.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigNombreEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ConfigNombreEmpresa.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigDireccionEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ConfigDireccionEmpresa.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigPropietarioEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ConfigPropietarioEmpresa.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigRUCEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ConfigTelefonoEmpresa.txt"));
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

        
        String workingDirectory = System.getProperty("java.io.tmpdir");
        try {
            File yourFile = new File(workingDirectory + File.separator + "ConfigTelefonoEmpresa.txt");
            yourFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ConfigTelefonoEmpresa.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigNombreEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "ConfigNombreEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigDireccionEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "ConfigDireccionEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigPropietarioEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "ConfigPropietarioEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ConfigRUCEmpresa.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ruta = workingDirectory + File.separator + "ConfigRUCEmpresa.txt";
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Vendedor.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\Vendedor.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Iva.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\Iva.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "ClaveAdmin.txt");
        try {
            yourFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\ClaveAdmin.txt"));
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
        String workingDirectory = System.getProperty("java.io.tmpdir");
        File yourFile = new File(workingDirectory + File.separator + "Usuario.txt");
        try {
            yourFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        String s1 = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(workingDirectory + "\\Usuario.txt"));
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
