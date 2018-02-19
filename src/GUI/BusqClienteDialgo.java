package GUI;

import Clases.Clientes;
import Dat.DATClientes;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rober
 */
public class BusqClienteDialgo extends javax.swing.JDialog {

    /**
     * Creates new form BusqClienteDialgo
     */
    
    Clientes cliente = new Clientes();
    DATClientes manejadorCliente;
    DefaultTableModel modelo;
    
    
    public BusqClienteDialgo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        initComponents();
        manejadorCliente = new DATClientes();
        cargarEncabezado();
        this.setLocationRelativeTo(null);
        cargarTabla();
    }
    
    public void cargarTabla(){
        ArrayList<Clientes> listadoCliente = manejadorCliente.ObtenerClientes();
        int filas = listadoCliente.size();
        modelo.setNumRows(filas);
        for (int i = 0; i < filas; i++) {
            cliente = listadoCliente.get(i);
            String nombre = cliente.getStrNombre();
            String cedula = cliente.getStrCedula();
            double deuda = cliente.getDblDeuda();
            String telf = cliente.getStrTelf();
            String direccion = cliente.getStrDireccion();
            
            modelo.setValueAt(nombre, i, 0);
            modelo.setValueAt(cedula, i, 1);
            modelo.setValueAt(deuda, i, 2);
            modelo.setValueAt(telf, i, 3);
            modelo.setValueAt(direccion, i, 4);
        }
    }
    
    public void cargarEncabezado(){
        modelo.addColumn("Nombre");
        modelo.addColumn("Cedula");
        modelo.addColumn("Deuda");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        btnSleccion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Buscar:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        btnAtras.setText("Atrás");

        btnSleccion.setText("Seleccionar");
        btnSleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSleccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(btnAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSleccion)
                .addGap(195, 195, 195))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSleccion))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        String busq = txtNombre.getText();
        ArrayList<Clientes> listadoCliente = manejadorCliente.ConsultarPorNombre(busq);
        int filas = listadoCliente.size();
        modelo.setNumRows(filas);
        for (int i = 0; i < filas; i++) {
            cliente = listadoCliente.get(i);
            String nombre = cliente.getStrNombre();
            String cedula = cliente.getStrCedula();
            double deuda = cliente.getDblDeuda();
            String telf = cliente.getStrTelf();
            String direccion = cliente.getStrDireccion();
            
            modelo.setValueAt(nombre, i, 0);
            modelo.setValueAt(cedula, i, 1);
            modelo.setValueAt(deuda, i, 2);
            modelo.setValueAt(telf, i, 3);
            modelo.setValueAt(direccion, i, 4);
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnSleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSleccionActionPerformed
        if (jTable1.getSelectedRowCount()> 0 ){
            int fila = jTable1.getSelectedRow();
            String ced = (String) jTable1.getValueAt(fila, 1);
            String nombre = (String) jTable1.getValueAt(fila, 0);
            String dir = (String) jTable1.getValueAt(fila, 4);
            System.out.println(ced);
            Factura.txtCed.setText(ced);
            Factura.txtCliente.setText(nombre);
            Factura.txtDireccionCl.setText(dir);
            this.dispose();
        }
    }//GEN-LAST:event_btnSleccionActionPerformed

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
            java.util.logging.Logger.getLogger(BusqClienteDialgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusqClienteDialgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusqClienteDialgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusqClienteDialgo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BusqClienteDialgo dialog = new BusqClienteDialgo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnSleccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}