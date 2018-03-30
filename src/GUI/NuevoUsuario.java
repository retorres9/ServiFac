package GUI;

import Clases.Configuracion;
import Clases.Usuario;
import Dat.DATUsuario;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public final class NuevoUsuario extends javax.swing.JFrame {

    Usuario objUs = new Usuario();
    DATUsuario usuario;
    Configuracion config = new Configuracion();
    String clave = config.claveAdmin();

    public NuevoUsuario() {
        initComponents();
        combo();
        txtCedulaUser.setTransferHandler(null);
        txtNombre.setTransferHandler(null);
        usuario = new DATUsuario();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        txtNombreEmp.setText(config.configNombreEmpresa());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        System.out.println(clave);
    }

    public void combo() {
        jComboBox1.addItem("Seleccione...");
        jComboBox1.addItem("Administrador");
        jComboBox1.addItem("Vendedor");
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ha habido un error al codificar su contraseña");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        txtPass = new javax.swing.JPasswordField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtConf = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtNombreEmp = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCedulaUser = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel4.setText("Rol:");

        txtNombre.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtUsuario.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

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

        jLabel6.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel6.setText("Confirmar Contraseña:");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnet-de-identidad.png"))); // NOI18N

        txtNombreEmp.setFont(new java.awt.Font("Roboto Cn", 1, 26)); // NOI18N
        txtNombreEmp.setText("Nombre de Empresa");

        jLabel8.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel8.setText("Cédula:");

        txtCedulaUser.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        txtCedulaUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaUserKeyTyped(evt);
            }
        });

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
                            .addComponent(jComboBox1, 0, 300, Short.MAX_VALUE)
                            .addComponent(txtConf)
                            .addComponent(txtPass)
                            .addComponent(txtNombre)
                            .addComponent(txtUsuario)
                            .addComponent(txtCedulaUser))
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Login objV = new Login();
        objV.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            try {
                if (n.equals(clave)) {

                    String cedula = txtCedulaUser.getText();
                    String nom = txtNombre.getText();
                    String usu = txtUsuario.getText();
                    String pass = txtPass.getText();
                    String rol = (String) jComboBox1.getSelectedItem();
                    if (rol.equals("Vendedor")) {
                        rolUs = 0;
                    }
                    if (txtPass.getText().equals(txtConf.getText())) {
                        String newPass = pass;
                        pass = getMD5(newPass);
                        objUs = new Usuario(cedula, nom, usu, pass, rolUs);
                        try {
                            if (usuario.nuevoUsuario(objUs)) {
                                JOptionPane.showMessageDialog(null, "El usuario se guardó"
                                        + " correctamente, ahora por favor vuelva a ingresar\n"
                                        + "su usuario y contraseña");
                                Login valid = new Login();
                                valid.setVisible(true);
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
            try {
                if (n.equals(clave)) {
                    String cedula = txtCedulaUser.getText();
                    String nom = txtNombre.getText();
                    String usu = txtUsuario.getText();
                    String pass = txtPass.getText();
                    String rol = (String) jComboBox1.getSelectedItem();
                    if (rol.equals("Administrador")) {
                        rolUs = 1;
                    }
                    if (txtPass.getText().equals(txtConf.getText())) {
                        String newPass = pass;
                        pass = getMD5(newPass);
                        objUs = new Usuario(cedula, nom, usu, pass, rolUs);
                        try {
                            if (usuario.nuevoUsuario(objUs)) {
                                JOptionPane.showMessageDialog(null, "El usuario se guardó"
                                        + " correctamente, ahora por favor vuelva a ingresar\n"
                                        + "su usuario y contraseña");
                                Login valid = new Login();
                                valid.setVisible(true);
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (jComboBox1.getSelectedItem().equals("Seleccione...")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un rol para el usuario");
        } else if (jComboBox1.getSelectedItem().equals("Administrador")) {
            nuevoUsuarioAdmin();
        } else if (jComboBox1.getSelectedItem().equals("Vendedor")) {
            nuevoUsuarioVendedor();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCedulaUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaUserKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) || (txtCedulaUser.getText().length() > 9)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaUserKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char caracter = evt.getKeyChar();
        if ((!Character.isLetter(caracter)) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_SPACE) || (txtNombre.getText().length() > 49)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        if (txtUsuario.getText().length() > 14) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

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
            java.util.logging.Logger.getLogger(NuevoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoUsuario().setVisible(true);
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
