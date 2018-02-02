package GUI;

import Clases.Venta;
import Dat.DATVenta;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public final class VistaDeudaCliente extends javax.swing.JFrame {

    public String nombre, total, cedula, idVenta;
    DefaultTableModel modelo = new DefaultTableModel();
    Venta venta = new Venta();
    DATVenta manejadorVenta;
    String nombreCli;

    public VistaDeudaCliente() {
        initComponents();
        manejadorVenta = new DATVenta();
        cargaEncabezado();
        cargarTabla();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        nombre = getCliente();
    }

    public String getCliente() {
        return nombre;
    }

    public void setCliente(String nombre) {
        this.nombre = nombre;
    }

    public void cargaEncabezado() {
        modelo.addColumn("Nro Venta");
        modelo.addColumn("Deuda");
        modelo.addColumn("Total Venta");
        modelo.addColumn("Valor Cancelado");
        modelo.addColumn("Fecha");
        modelo.addColumn("nombre");
    }

    public void cargarTabla() {
        String cliente = txtNombreCliente.getText();

        ArrayList<Venta> listadoVenta = manejadorVenta.ConsultarComprasCL(nombre);//cambio
        int cantLista = listadoVenta.size();
        modelo.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            venta = listadoVenta.get(i);
            int intIdVenta = venta.getIntIdVenta();
            double deuda = venta.getDeudaCliente();
            double totalDeuda = venta.getDblTotalVenta();
            double totalCancelado = venta.getDblValCancelado();
            String fechaVenta = venta.getStrFecha();
            String nomCliente = venta.getStrCliente();

            modelo.setValueAt(intIdVenta, i, 0);
            modelo.setValueAt(deuda, i, 1);
            modelo.setValueAt(totalDeuda, i, 2);
            modelo.setValueAt(totalCancelado, i, 3);
            modelo.setValueAt(fechaVenta, i, 4);
            modelo.setValueAt(nomCliente, i, 5);
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

        tblDeudaCliente.setModel(modelo);
        tblDeudaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeudaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDeudaCliente);

        txtNombreCliente.setFont(new java.awt.Font("Roboto Cn", 0, 36)); // NOI18N

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton2)
                        .addGap(501, 501, 501)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(69, 69, 69)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDeudaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeudaClienteMouseClicked
        int fila = tblDeudaCliente.getSelectedRow();
        if (tblDeudaCliente.getSelectedRows().length > 0) {
            idVenta = (String) tblDeudaCliente.getValueAt(fila, 0).toString();
            nombreCli = txtNombreCliente.getText();
            cedula = (String) tblDeudaCliente.getValueAt(fila, 1).toString();
            total = (String) tblDeudaCliente.getValueAt(fila, 3).toString();
        }
    }//GEN-LAST:event_tblDeudaClienteMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VistaFactura vf = new VistaFactura();
        VistaFactura.txtCliente.setText(nombreCli);
        VistaFactura.txtCedula.setText(cedula);
        VistaFactura.txtTotal.setText(total);
        VistaFactura.txtVenta.setText(idVenta);
        vf.setId(idVenta);
        vf.cargarTabla();
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
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaDeudaCliente.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
