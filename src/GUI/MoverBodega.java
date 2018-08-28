package GUI;

import Clases.Bodega;
import Clases.ExistenciasBodega;
import Dat.DATBodega;
import Dat.DATExistenciasBodega;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class MoverBodega extends javax.swing.JDialog {

    DefaultComboBoxModel<Bodega> modeloBodega;
    DATBodega manejadorBodega;
    DATExistenciasBodega manejadorExistencias;
    ExistenciasBodega objExistencias = new ExistenciasBodega();
    int total;
    Inventario inv;

    public MoverBodega(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        manejadorBodega = new DATBodega();
        manejadorExistencias = new DATExistenciasBodega();
        modeloBodega = new DefaultComboBoxModel<>();
        cargarModeloBodega();
        initComponents();
        //inv = new Inventario();
        txtAyudaCantIni.setVisible(false);
        txtAyudaIndice.setVisible(false);
        txtAyudaCant.setVisible(false);
        txtAyudaCod.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    private void cargarModeloBodega() {
        ArrayList<Bodega> listaBodegas;
        listaBodegas = manejadorBodega.obtenerBodega();
        for (Bodega bodega : listaBodegas) {
            modeloBodega.addElement(bodega);
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

        cmbBodega = new javax.swing.JComboBox<>();
        txtCant = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtAyudaCant = new javax.swing.JLabel();
        txtAyudaCod = new javax.swing.JLabel();
        txtAyudaIndice = new javax.swing.JLabel();
        txtAyudaCantIni = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblProd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbBodega.setModel(modeloBodega);

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

        txtAyudaCant.setText("jLabel1");

        txtAyudaCod.setText("jLabel1");

        txtAyudaIndice.setText("jLabel1");

        txtAyudaCantIni.setText("jLabel1");

        jLabel1.setText("Ingrese cuantas unidades va a mover de:");

        lblProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProd.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(72, 72, 72)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1)))
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(163, 163, 163))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblProd, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAyudaCantIni)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAyudaCant)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAyudaCod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAyudaIndice)))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAyudaCant)
                    .addComponent(txtAyudaCod)
                    .addComponent(txtAyudaIndice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAyudaCantIni)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int auxCantActual = Integer.parseInt(txtAyudaCant.getText());
        int cant = Integer.parseInt(txtCant.getText());
        int filaSelec = Integer.parseInt(txtAyudaIndice.getText());
        int cantInicial = Integer.parseInt(txtAyudaCantIni.getText());
        Bodega bod = (Bodega) cmbBodega.getSelectedItem();
        int getCant;
        if (txtCant.getText().trim().isEmpty()) {
//            Do nothing
        } else {
            if (cant > auxCantActual) {
                JOptionPane.showMessageDialog(null, "Está intentando mover una cantidad mayor a la existente");
            } else {
                total = cantInicial - Integer.parseInt(txtCant.getText());
                System.out.println(cmbBodega.getSelectedItem());
                getCant = manejadorExistencias.contadorProdBodega(txtAyudaCod.getText());
                System.out.println(bod.getIntIdBodega());

                if (getCant > 0) {
                    objExistencias = new ExistenciasBodega(txtAyudaCod.getText(), Integer.parseInt(txtCant.getText()));
                    manejadorExistencias.actualizarCant(objExistencias, cant);
                } else {
                    objExistencias = new ExistenciasBodega(bod.getIntIdBodega(), txtAyudaCod.getText(), Integer.parseInt(txtCant.getText()));
                    manejadorExistencias.ingresarEnBodegaInventario(objExistencias, cant);
                }
                Inventario.bandera = false;
                JOptionPane.showMessageDialog(null, "Operación realizada exitosamente!!!");
                Inventario.tblProd.setValueAt(total, filaSelec, 8);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MoverBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoverBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoverBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoverBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MoverBodega dialog = new MoverBodega(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Bodega> cmbBodega;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel lblProd;
    public javax.swing.JLabel txtAyudaCant;
    public javax.swing.JLabel txtAyudaCantIni;
    public javax.swing.JLabel txtAyudaCod;
    public javax.swing.JLabel txtAyudaIndice;
    private javax.swing.JTextField txtCant;
    // End of variables declaration//GEN-END:variables
}
