/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUI.IngresoProd;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rojeru San
 */
public class Guardar {
    public static String ruta;
    IngresoProd ing = new IngresoProd();
    public static boolean guardarImagen(String nombre) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Guardar Código de Barras");
        FileFilter filter = new FileNameExtensionFilter("Barras Image", "PNG");
        
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setName(nombre);
        File f = new File(nombre);
        fileChooser.setSelectedFile(f);
        //fileChooser.setSelectedFile(new File("nombre.txt"));
        fileChooser.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator")+ "Music"));
        int userSelection = fileChooser.showSaveDialog(new IngresoProd());
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File guardarBarras = fileChooser.getSelectedFile();
            if (!guardarBarras.toString().endsWith(".png")) {
                guardarBarras = new File(fileChooser.getSelectedFile() + ".png");
                ruta = guardarBarras.toString();
                //IngresoProd.jLabel10.setText(ruta);
                System.out.println(ruta);
            }
            try {
                ImageIO.write(IngresoProd.imagen, "png", guardarBarras);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(guardarBarras);
        } else {
            return false;
        }
        return false;
    }
    
}
