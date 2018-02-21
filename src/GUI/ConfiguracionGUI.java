package GUI;

import javax.swing.JOptionPane;
import Clases.Configuracion;
import Clases.Usuario;
import Dat.DATUsuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public final class ConfiguracionGUI extends javax.swing.JFrame {

    boolean bandera = true;
    String nombreEmp = "";
    String direccionEmp = "";
    String propietarioEmp = "";
    String rucEmp = "";
    String telefonoEmp = "";
    Configuracion configu = new Configuracion();
    Principal pr = new Principal();
    DefaultTableModel modelo = new DefaultTableModel();
    DATUsuario manejadorUsuario;
    Usuario objUusuario = new Usuario();

    public ConfiguracionGUI() {
        manejadorUsuario = new DATUsuario();
        initComponents();
        encabezados();
        cargarTabla();
        configuracion();
        getTexto();
        this.setLocationRelativeTo(null);
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
    }

    public void encabezados() {
        modelo.addColumn("Nombre");
        modelo.addColumn("Usuario");
        modelo.addColumn("Puesto");
    }
    
    public void cargarTabla(){
        String strRol;
        ArrayList<Usuario> listadoUsuarios = manejadorUsuario.listarClientes();
        int cantUusarios = listadoUsuarios.size();
        modelo.setNumRows(cantUusarios);
        for (int i = 0; i < cantUusarios; i++) {
            objUusuario = listadoUsuarios.get(i);
            String nombre = objUusuario.getNombre();
            String user = objUusuario.getUsuario();
            int rol = objUusuario.getRol();
            modelo.setValueAt(nombre, i, 0);
            modelo.setValueAt(user, i, 1);
            if(rol == 0){
                strRol = "Vendedor";
            } else {
                strRol = "Administrador";
            }
            modelo.setValueAt(strRol, i, 2);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        txtNombreEmp = new javax.swing.JTextField();
        txtDireccionEmp = new javax.swing.JTextField();
        txtPropietarioEmp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRucEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefonoEmp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel1.setText("Configuración de la Empresa");

        jLabel7.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel7.setText("Teléfono:");

        btnGuardar.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtNombreEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDireccionEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPropietarioEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel2.setText("Nombre de la Empresa:");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel3.setText("Dirección:");

        txtRucEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel4.setText("Propietario:");

        txtTelefonoEmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel5.setText("IVA:");

        jLabel6.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        jLabel6.setText("RUC:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRucEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(txtDireccionEmp)
                    .addComponent(txtPropietarioEmp))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(132, 132, 132))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccionEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPropietarioEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtRucEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefonoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Empresa", jPanel1);

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Eliminar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Empleados", jPanel2);

        btnCancelar.setFont(new java.awt.Font("Roboto Cn", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/error.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        comparar();
        if (bandera == false) {
            int n = JOptionPane.showConfirmDialog(null, "Ha realizado cambios en la configuración de la empresa\n"
                    + "¿Esta seguro que desea guardar los cambios?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                actualizarConfig();
                SwingUtilities.updateComponentTreeUI(pr);
                this.setVisible(false);
                pr.setVisible(true);
            }
        } else {

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        comparar();

        if (bandera == false) {
            int n = JOptionPane.showConfirmDialog(null, "Se ha realizado cambios en la configuración\n"
                    + "¿Desea guardar los cambios?", "Aviso!", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                actualizarConfig();
                pr.setVisible(true);
                this.dispose();
            } else if (n == JOptionPane.NO_OPTION) {
                pr.setVisible(true);
                this.dispose();
            }
        } else {
            pr.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void actualizarConfig() {
        try {
            configu.actualizaNombreEmp(txtNombreEmp.getText());
            configu.actualizaDireccionEmp(txtDireccionEmp.getText());
            configu.actualizaPropietarioEmp(txtPropietarioEmp.getText());
            configu.actualizaRucEmp(txtRucEmp.getText());
            configu.actualizaTelefonoEmp(txtTelefonoEmp.getText());
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracionGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configuracion() {
        txtNombreEmp.setText(Constantes.Constantes.NOMBRE_EMPRESA);
        txtDireccionEmp.setText(Constantes.Constantes.DIRECCION_EMPRESA);
        txtPropietarioEmp.setText(Constantes.Constantes.PROPIETARIO_EMPRESA);
        txtRucEmp.setText(Constantes.Constantes.RUC_EMPRESA);
        txtTelefonoEmp.setText(Constantes.Constantes.RUC_EMPRESA);
    }

    public void getTexto() {
        nombreEmp = txtNombreEmp.getText();
        direccionEmp = txtDireccionEmp.getText();
        propietarioEmp = txtPropietarioEmp.getText();
        rucEmp = txtRucEmp.getText();
        telefonoEmp = txtTelefonoEmp.getText();
    }

    public void comparar() {
        if (nombreEmp.equals(txtNombreEmp.getText()) && direccionEmp.equals(this.txtDireccionEmp.getText())
                && propietarioEmp.equals(txtPropietarioEmp.getText()) && rucEmp.equals(txtRucEmp.getText())
                && telefonoEmp.equals(txtTelefonoEmp.getText())) {

        } else {
            bandera = false;
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
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField txtDireccionEmp;
    private javax.swing.JTextField txtNombreEmp;
    private javax.swing.JTextField txtPropietarioEmp;
    private javax.swing.JTextField txtRucEmp;
    private javax.swing.JTextField txtTelefonoEmp;
    // End of variables declaration//GEN-END:variables
}
