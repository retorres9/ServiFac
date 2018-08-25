/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author rober
 */
public class Renderer implements TableCellRenderer {

    private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
    
    @Override
    public Component getTableCellRendererComponent(JTable tblBodega, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component c = RENDERER.getTableCellRendererComponent(tblBodega, value, isSelected, hasFocus, row, column);
        if (column == 3) {
            String valor = tblBodega.getValueAt(row, column).toString();
            Double cant = Double.parseDouble(valor);
            Color color = null;
            if (cant > 0) {
                //this.setOpaque(true);
                color = Color.RED;
                //this.setForeground(Color.YELLOW);
            } else {
                color = Color.BLUE;
            }
            c.setForeground(color);
        } else {
            c.setForeground(Color.BLACK);
        }
        return c;
    }
    
}
