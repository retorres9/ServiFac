/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import clases.Configuracion;
import clases.Usuario;
import dat.DATConfiguracion;
import dat.DATUsuario;
import utilidades.Utilidades;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public final class NuevoUsuarioDialog extends javax.swing.JDialog {

    Usuario objUs = new Usuario();
    DATUsuario usuario;
    Configuracion config = new Configuracion();
    DATConfiguracion manejadorConf;
    Utilidades util = new Utilidades();
    String host;
    String pass;
    
    public NuevoUsuarioDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        manejadorConf = new DATConfiguracion();
        usuario = new DATUsuario();
        initComponents();  
        host = util.getPcName();
        obtenerCredencial();
        combo();
        txtConf.setTransferHandler(null);
        txtPass.setTransferHandler(null);
        txtCedulaUser.setTransferHandler(null);
        txtNombre.setTransferHandler(null);
        txtUsuario.setTransferHandler(null);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(constantes.Constantes.NOMBRE_PROGRAMA);
        System.out.println(pass);
    }
    
    public void obtenerCredencial(){
        ArrayList<Configuracion> cred = manejadorConf.obtenerCredencial();
        int cantCred = cred.size();
        for (int i = 0; i < cantCred; i++) {
            config = cred.get(i);
            pass = config.getPass();
            String emp = config.getEmpresa();
            txtNombreEmp.setText(emp);
        }
    }

    public void combo() {
        jComboBox1.addItem("Seleccione...");
        jComboBox1.addItem("Administrador");
        jComboBox1.addItem("Vendedor");
    }
    
    public void nuevoUsuarioVendedor() {
        int rolUs = 0;
        int digitosCedula = txtCedulaUser.getText().length();
        if (txtConf.getText().isEmpty() || txtNombre.getText().isEmpty()
                || txtPass.getText().isEmpty() || txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else if (digitosCedula != 10) {
            JOptionPane.showMessageDialog(null, "Número de cédula incorrecto");
        } else {
            String n = JOptionPane.showInputDialog(null, "Ingrese el codigo de confirmación para poder crear\n"
                    + "un usuario con rol de Vendedor");
            String cred = Utilidades.getMD5(n);
            try {
                if (cred.equals(pass)) {

                    String cedula = txtCedulaUser.getText();
                    String nom = txtNombre.getText();
                    String usu = txtUsuario.getText().toLowerCase();
                    String passUsuario = txtPass.getText();
                    String rol = (String) jComboBox1.getSelectedItem();
                    if (rol.equals("Vendedor")) {
                        rolUs = 0;
                    }
                    if (txtPass.getText().equals(txtConf.getText())) {
                        String newPass = passUsuario;
                        passUsuario = Utilidades.getMD5(newPass);
                        objUs = new Usuario(cedula, nom, usu, passUsuario, rolUs);
                        try {
                            if (usuario.nuevoUsuario(objUs, host)) {
                                JOptionPane.showMessageDialog(null, "El usuario se guardó"
                                        + " correctamente, ahora por favor vuelva a ingresar\n"
                                        + "su usuario y contraseña");
                                this.dispose();
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "error");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Codigo Incorrecto");
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "No ha ingresado el código de verificación");
            }
        }
    }

    public void nuevoUsuarioAdmin() {
        int rolUs = 0;
        int digitosCedula = txtCedulaUser.getText().length();

        if (txtConf.getText().isEmpty() || txtNombre.getText().isEmpty()
                || txtPass.getText().isEmpty() || txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else if (digitosCedula != 10) {
            JOptionPane.showMessageDialog(null, "Número de cédula incorrecto");
        } else {
            String n = JOptionPane.showInputDialog(null, "Ingrese el codigo de confirmación para poder crear\n"
                    + "un usuario con rol de Administrador");
            String cred = Utilidades.getMD5(n);
            System.out.println(cred);
            try {
                if (cred.equals(pass)) {
                    String cedula = txtCedulaUser.getText();
                    String nom = txtNombre.getText();
                    String usu = txtUsuario.getText();
                    String passUsuario = txtPass.getText();
                    String rol = (String) jComboBox1.getSelectedItem();
                    if (rol.equals("Administrador")) {
                        rolUs = 1;
                    }
                    if (txtPass.getText().equals(txtConf.getText())) {
                        String newPass = passUsuario;
                        passUsuario = Utilidades.getMD5(newPass);
                        objUs = new Usuario(cedula, nom, usu, passUsuario, rolUs);
                        try {
                            if (usuario.nuevoUsuario(objUs, host)) {
                                JOptionPane.showMessageDialog(null, "El usuario se guardó"
                                        + " correctamente, ahora por favor vuelva a ingresar\n"
                                        + "su usuario y contraseña");
                                this.dispose();
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "error");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseñas no coinciden");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Codigo Incorrecto");
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "No ha ingresado el código de verificación");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtNombreEmp = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCedulaUser = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        txtConf = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel6.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel6.setText("Confirmar Contraseña:");

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnet-de-identidad.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel4.setText("Rol:");

        txtNombreEmp.setFont(new java.awt.Font("Roboto Cn", 1, 26)); // NOI18N
        txtNombreEmp.setText("Nombre de Empresa");

        jLabel8.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel8.setText("Cédula:");

        jComboBox1.setFont(new java.awt.Font("Roboto Condensed Light", 0, 13)); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Roboto Condensed", 0, 13)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Roboto Condensed", 0, 13)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/error.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel5.setText("Registro de Nuevo Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNombreEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCedulaUser)
                            .addComponent(txtNombre)
                            .addComponent(txtUsuario)
                            .addComponent(txtPass)
                            .addComponent(txtConf))
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(191, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(128, 128, 128)
                        .addComponent(btnGuardar)
                        .addGap(146, 146, 146)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(215, 215, 215))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtNombreEmp)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCedulaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (jComboBox1.getSelectedItem().equals("Seleccione...")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un rol para el usuario");
        } else if (jComboBox1.getSelectedItem().equals("Administrador")) {
            nuevoUsuarioAdmin();
        } else if (jComboBox1.getSelectedItem().equals("Vendedor")) {
            nuevoUsuarioVendedor();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoUsuarioDialog dialog = new NuevoUsuarioDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCedulaUser;
    private javax.swing.JPasswordField txtConf;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtNombreEmp;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
