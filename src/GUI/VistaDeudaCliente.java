package GUI;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class VistaDeudaCliente extends javax.swing.JFrame {

    String nombre, total, cedula, venta;
    
    public VistaDeudaCliente() {
        initComponents();
        cargarTabla();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
    }

    public static void cargarTabla(){
        String nombre = "Roberth Torres";
        int fila=0;
        try {
            
            BLClientes manejadorClientes = new BLClientes();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nro Venta");
            modelo.addColumn("Deuda");
            modelo.addColumn("Total Venta");
            modelo.addColumn("Valor Cancelado");
            modelo.addColumn("Fecha");
            modelo.addColumn("nombre");
            String fecha = txtNombreCliente.getText();
            for (int i = 0; i < manejadorClientes.getClientexNombre(fecha).size(); i++) {
                modelo.addRow(manejadorClientes.getClientexNombre(fecha).get(i));
            }
            tblDeudaCliente.setModel(modelo);
            /*for (Object[] dato : datos) {
                String idVenta = String.valueOf(dato[0]);
                String deuda = String.valueOf(dato[1]);
                String totalVenta = String.valueOf(dato[2]);
                String valorCancelado = String.valueOf(dato[3]);
                String fecha = String.valueOf(dato[4]);
                String nombres = String.valueOf(dato[5]);
                modelo.setValueAt(idVenta, fila, 0);
                modelo.setValueAt(deuda, fila, 1);
                modelo.setValueAt(totalVenta, fila, 2);
                modelo.setValueAt(valorCancelado, fila, 3);
                modelo.setValueAt(fecha, fila, 4);
                modelo.setValueAt(nombres, fila, 4);
                fila++;
            }*/
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VistaDeudaCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VistaDeudaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeudaCliente = new javax.swing.JTable();
        txtNombreCliente = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(934, 323));

        jLabel1.setFont(new java.awt.Font("Roboto Cn", 0, 36)); // NOI18N
        jLabel1.setText("Compras Realizadas por:");

        tblDeudaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblDeudaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeudaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDeudaCliente);

        txtNombreCliente.setFont(new java.awt.Font("Roboto Cn", 0, 36)); // NOI18N
        txtNombreCliente.setText("Roberth Torres");

        jButton1.setText("Ver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atrás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(120, 120, 120)
                            .addComponent(jButton2)
                            .addGap(501, 501, 501)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel1)
                            .addGap(10, 10, 10)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDeudaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeudaClienteMouseClicked
        int fila = tblDeudaCliente.getSelectedRow();
        if(tblDeudaCliente.getSelectedRows().length>0){
            venta = (String) tblDeudaCliente.getValueAt(fila, 0).toString();
            nombre = txtNombreCliente.getText();
            cedula = (String) tblDeudaCliente.getValueAt(fila, 1).toString();
            total = (String) tblDeudaCliente.getValueAt(fila, 3).toString();
        }
    }//GEN-LAST:event_tblDeudaClienteMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VistaFactura vf = new VistaFactura();
        VistaFactura.txtCliente.setText(nombre);
        VistaFactura.txtCedula.setText(cedula);
        VistaFactura.txtTotal.setText(total);
        VistaFactura.txtVenta.setText(venta);
        VistaFactura.cargarTabla();
        vf.setVisible(true);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaDeudaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblDeudaCliente;
    public static javax.swing.JLabel txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
