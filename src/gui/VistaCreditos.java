package gui;

import clases.PagoProveedorClase;
import dat.DATPagoProveedor;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class VistaCreditos extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableCellRenderer celda = new DefaultTableCellRenderer();
    DATPagoProveedor manejadorPago;
    PagoProveedorClase objPago = new PagoProveedorClase();
    PagoProveedor pagoFrame;
    String emp;

    public VistaCreditos() {
        manejadorPago = new DATPagoProveedor();
        pagoFrame = new PagoProveedor();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle(constantes.Constantes.NOMBRE_PROGRAMA);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        
        emp = pagoFrame.getRuc();
        encabezados();
        cargarTabla();
        txtAreaComentario.setVisible(false);
    }

    public void encabezados() {
        modelo.addColumn("Pagos");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha");
        modelo.addColumn("Descripcion");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaComentario = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblVistaCreditos.setModel(modelo);
        tblVistaCreditos.getTableHeader().setReorderingAllowed(false);
        tblVistaCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVistaCreditosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVistaCreditos);

        txtEmpresa.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        txtEmpresa.setText("Créditos realizados a:");

        jLabel1.setText(" ");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtAreaComentario.setColumns(20);
        txtAreaComentario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtAreaComentario.setRows(5);
        jScrollPane2.setViewportView(txtAreaComentario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtEmpresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PagoProveedor objFramePago = new PagoProveedor();
        this.dispose();
        objFramePago.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        PagoProveedor pp = new PagoProveedor();
        pp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void tblVistaCreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVistaCreditosMouseClicked
        int fila = tblVistaCreditos.rowAtPoint(evt.getPoint());
        String comentario = (String) tblVistaCreditos.getValueAt(fila, 3);
        txtAreaComentario.setText(comentario);
        txtAreaComentario.setVisible(true);
    }//GEN-LAST:event_tblVistaCreditosMouseClicked

    public void cargarTabla() {

        ArrayList<PagoProveedorClase> lista = manejadorPago.verPagos(emp);
        int cant = lista.size();
        modelo.setNumRows(cant);
        for (int i = 0; i < cant; i++) {
            objPago = lista.get(i);
            double montocancelado = objPago.getDblMontoCancelado();
            String usuario = objPago.getUsuario();
            String strFecha = objPago.getFecha();
            String desc = objPago.getStrDescripcion();
            modelo.setValueAt(montocancelado, i, 0);
            modelo.setValueAt(usuario, i, 1);
            modelo.setValueAt(strFecha, i, 2);
            modelo.setValueAt(desc, i, 3);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JTable tblVistaCreditos = new javax.swing.JTable();
    private javax.swing.JTextArea txtAreaComentario;
    public static final javax.swing.JLabel txtEmpresa = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
