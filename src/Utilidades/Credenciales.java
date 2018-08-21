package Utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Credenciales {

    public Credenciales() {

    }

    public static void  main (String[]args){
        OutputStream os = null;
        try {
            Properties preferences = new Properties();
            os = new FileOutputStream("config.ini");
            preferences.setProperty("url", "localhost:3306/empresa");
            preferences.setProperty("user", "root");
            preferences.setProperty("pass", "ticowrc2017");
            
            preferences.store(os, "Configuracion de conexión");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Credenciales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Credenciales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(Credenciales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
