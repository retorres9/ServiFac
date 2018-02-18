package GUI;

import Clases.PagoProveedorClase;
import Dat.DATPagoProveedor;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class VistaCreditos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableCellRenderer celda = new DefaultTableCellRenderer();
    DATPagoProveedor manejadorPago;
    PagoProveedorClase objPago = new PagoProveedorClase();

    public VistaCreditos() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        
        manejadorPago = new DATPagoProveedor();
        encabezados();
        //cargarTabla();
    }

    public void encabezados() {
        modelo.addColumn("Empresa");
        modelo.addColumn("Deuda");
        modelo.addColumn("Pagos");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVistaCreditos = new javax.swing.JTable();
        txtEmpresa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tblVistaCreditos.setModel(modelo);
        jScrollPane1.setViewportView(tblVistaCreditos);

        txtEmpresa.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        txtEmpresa.setText("Créditos realizados a:");

        jLabel1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresa)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarTabla() {
        String empresa;
        empresa = txtEmpresa.getText();
        ArrayList<PagoProveedorClase> lista = manejadorPago.verPagos(empresa);
        int cant = lista.size();
        modelo.setNumRows(cant);
        for (int i = 0; i < cant; i++) {
            objPago = lista.get(i);
            String strEmpresa = objPago.getStrRuc();
            double deuda = objPago.getDeuda();
            double montocancelado = objPago.getDblMontoCancelado();
            String strFecha = objPago.getFecha();
            String usuario = objPago.getUsuario();
            modelo.setValueAt(strEmpresa, i, 0);
            modelo.setValueAt(deuda, i, 1);
            modelo.setValueAt(montocancelado, i, 2);
            modelo.setValueAt(strFecha, i, 3);
            modelo.setValueAt(usuario, i, 4);
        }

    }

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
            java.util.logging.Logger.getLogger(VistaCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCreditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCreditos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblVistaCreditos;
    public static javax.swing.JLabel txtEmpresa;
    // End of variables declaration//GEN-END:variables
}
