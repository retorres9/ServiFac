/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Producto;
import Dat.DATMaterial;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class ActualizacionPrecio extends javax.swing.JDialog {

    DATMaterial manejadorMaterial;
    Producto objProd = new Producto();
    double precioVenta;
    double precioVentaMayor;
    
    public ActualizacionPrecio(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblCod.setVisible(false);
    }
    
    public void muestraPrecioxMayor() {
        if (txtPrecioCompra.getText().isEmpty() || txtGananciaMayor.getText().isEmpty()) {
        } else {
            try {
                double precio = Double.parseDouble(txtPrecioCompra.getText());
                double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
                if (lblIVA.getText().equals("12%")) {
                    precioVentaMayor = ((precio) + precio * 0.12) + (precio * (gananciaMayor / 100));
                } else {
                    precioVentaMayor = precio + (precio * (gananciaMayor / 100));
                }
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambioMayor = new DecimalFormat("0.00", simbolos);
                String strPrecioMayor = dcmlCambioMayor.format(precioVentaMayor);
                precioVentaMayor = Double.parseDouble(strPrecioMayor);
                lblPrecioMayor.setText("Precio de venta al por mayor: $" + precioVentaMayor);
            } catch (NumberFormatException ex) {
            }

        }
    }

    public void muestraPrecio() {
        if (txtPrecioCompra.getText().isEmpty() || txtGanancia.getText().isEmpty()) {
        } else {
            try {
                double precioInput = Double.parseDouble(txtPrecioCompra.getText());
                double ganancia = Double.parseDouble(txtGanancia.getText());
                if (lblIVA.getText().equals("12%")) {
                    precioVenta = ((precioInput) + precioInput * 0.12) + (precioInput * (ganancia / 100));
                } else {
                    precioVenta = precioInput + (precioInput * (ganancia / 100));
                }
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                String strPrecio = dcmlCambio.format(precioVenta);
                precioVenta = Double.parseDouble(strPrecio);
                lblPrecioPublico.setText("Precio de venta al público: $" + precioVenta);
            } catch (NumberFormatException ex) {

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPrecioCompra = new javax.swing.JTextField();
        txtGanancia = new javax.swing.JTextField();
        txtGananciaMayor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblPrecioMayor = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblPrecioPublico = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblCod = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });

        txtGanancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaKeyTyped(evt);
            }
        });

        txtGananciaMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaMayorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaMayorKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Precio:");

        lblPrecioMayor.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPrecioMayor.setForeground(new java.awt.Color(0, 153, 51));
        lblPrecioMayor.setText("Precio de venta al por mayor:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Ganancia %");

        lblPrecioPublico.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPrecioPublico.setForeground(new java.awt.Color(0, 153, 0));
        lblPrecioPublico.setText("Precio de venta al público:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Ganancia por mayor");

        jLabel1.setText("IVA:");

        lblIVA.setText("jLabel2");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblCod.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecioMayor)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtGananciaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPrecioPublico)
                            .addComponent(lblIVA)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCod)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCod)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblIVA))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtGananciaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecioPublico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrecioMayor)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyReleased
        if (txtGanancia.getText().isEmpty() || txtGananciaMayor.getText().isEmpty()) {

        } else {
            muestraPrecio();
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_txtPrecioCompraKeyReleased

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9') || txtPrecioCompra.getText().length() > 5)
            && (caracter != KeyEvent.VK_BACK_SPACE)
            && (caracter != '.')) {
            evt.consume();
        }
        if (!txtGanancia.getText().isEmpty() || !txtGananciaMayor.getText().isEmpty()) {
            try {
                double precio = Double.parseDouble(txtPrecioCompra.getText());
                double ganancia = Double.parseDouble(txtGanancia.getText());
                double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
                precioVenta = precio + (precio * (ganancia / 100));
                lblPrecioPublico.setText("Precio de venta al público: $" + precioVenta);
                precioVentaMayor = precio + (precio * (gananciaMayor / 100));
                lblPrecioMayor.setText("Precio de venta al por mayor: $" + precioVentaMayor);
            } catch (NumberFormatException ex) {

            }

        }
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    private void txtGananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyReleased
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
        if ((txtGanancia.getText().isEmpty()) || (txtGanancia.getText().equals("")) || (txtPrecioCompra.getText().isEmpty())) {
            lblPrecioPublico.setText("Precio de venta al público:");
        } else {
            muestraPrecio();
        }
    }//GEN-LAST:event_txtGananciaKeyReleased

    private void txtGananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGananciaKeyTyped

    private void txtGananciaMayorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMayorKeyReleased
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
        if ((txtGananciaMayor.getText().isEmpty()) || (txtGananciaMayor.getText().equals("")) || (txtPrecioCompra.getText().isEmpty())) {
            lblPrecioMayor.setText("Precio de venta al por mayor:");
        } else {
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_txtGananciaMayorKeyReleased

    private void txtGananciaMayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMayorKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGananciaMayorKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
        Double precio = precioVenta;
        Double precioMayor = precioVentaMayor;
        Double ganancia = Double.parseDouble(txtGanancia.getText());
        Double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
        String codigo = lblCod.getText();
        objProd = new Producto(precioCompra, precio, precioMayor, ganancia, gananciaMayor, codigo);
        manejadorMaterial.ActualizaPrecio(objProd);
        JOptionPane.showMessageDialog(null, "Se ha actualizado el precio correctamente");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActualizacionPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizacionPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizacionPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizacionPrecio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizacionPrecio dialog = new ActualizacionPrecio(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel lblCod;
    public javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblPrecioMayor;
    private javax.swing.JLabel lblPrecioPublico;
    private javax.swing.JTextField txtGanancia;
    private javax.swing.JTextField txtGananciaMayor;
    private javax.swing.JTextField txtPrecioCompra;
    // End of variables declaration//GEN-END:variables
}