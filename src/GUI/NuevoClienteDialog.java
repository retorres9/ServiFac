package GUI;

import Clases.Clientes;
import Dat.DATClientes;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class NuevoClienteDialog extends javax.swing.JDialog {

    /**
     * Creates new form NuevoClienteDialog
     */
    Clientes objCliente = new Clientes();
    String informacion = "";
    public static int valid = 1;
    DATClientes cliente;
    
    public NuevoClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        cliente = new DATClientes();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
    }
    
    public String getInformacion(){
        return informacion;
    }
    
    public void guardarCliente() {
        try {
            String nombre = txtNombre.getText().toUpperCase();
            String dir = txtDireccion.getText().toUpperCase();
            String cedula = txtCedula.getText();
            String telf = txtTelf.getText();
            double deuda = Double.parseDouble(txtDeuda.getText());

            objCliente = new Clientes(nombre, cedula, telf, deuda, dir);
            try {
                if (cliente.InsertarCliente(objCliente)) {
                    JOptionPane.showMessageDialog(null, "Cliente creado satisfactoriamente");
                }
            } catch (SQLException ex) {
                Logger.getLogger(NewCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NumberFormatException ex) {
            //JOptionPane.showMessageDialog(null, "El valor ingresado en deuda no"
              //      + " es valido\nEjemplo (99.99)");
              Logger.getLogger(NewCliente.class.getName()).log(Level.SEVERE, null, ex);
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

        btnGuardarCli = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTelf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDeuda = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGuardarCli.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        btnGuardarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/save.png"))); // NOI18N
        btnGuardarCli.setText("Guardar");
        btnGuardarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCliActionPerformed(evt);
            }
        });

        btnAtras.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto Cn", 1, 24)); // NOI18N
        jLabel7.setText("Ingreso de Clientes");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel1.setText("Datos del Cliente");

        txtTelf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelfKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel3.setText("Nombres:");

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel4.setText("Cedula:");

        jLabel10.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel10.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel5.setText("Telefono:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel6.setText("Deuda:");

        txtDeuda.setText("0.00");
        txtDeuda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDeudaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeudaFocusLost(evt);
            }
        });
        txtDeuda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeudaKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDeuda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                .addComponent(txtTelf, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 80, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addGap(79, 79, 79)
                .addComponent(btnGuardarCli)
                .addGap(162, 162, 162))
            .addGroup(layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnGuardarCli))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliActionPerformed
        int digitosCedula = txtCedula.getText().length();
        int digitTelf = txtTelf.getText().length();
        if (txtNombre.getText().isEmpty() || txtCedula.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios que no se pueden guardar");
        } else if ((txtCedula.getText().length() != 10) && (txtCedula.getText().length() != 13)) {
            JOptionPane.showMessageDialog(null, "Número de cédula o RUC incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (digitTelf != 7 && digitTelf != 10) {
            JOptionPane.showMessageDialog(null, "Número telefónico incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            guardarCliente();
            Factura.txtCed.setText(txtCedula.getText());
            Factura.txtCliente.setText(txtNombre.getText().toUpperCase());
            Factura.txtDireccionCl.setText(txtDireccion.getText());
            informacion = "bandera";
            this.dispose();
        }
    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        Principal objP = new Principal();
        if (!txtNombre.getText().isEmpty()) {
            int n = JOptionPane.showConfirmDialog(
                null,
                "Si retrocede se perderan los cambios no guardados?",
                "Aviso!",
                JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                objP.setVisible(true);
                this.dispose();
            }
        } else {
            objP.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtTelfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelfKeyTyped
        char caracter = evt.getKeyChar();
        String Caracteres = txtTelf.getText();
        if (Caracteres.length() >= 10) {
            evt.consume();
        }
        if (Character.isLetter(caracter)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelfKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        char caracter = evt.getKeyChar();
        String Caracteres = txtCedula.getText();
        if (Caracteres.length() >= 13) {
            evt.consume();
        }
        if (Character.isLetter(caracter)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (txtDireccion.getText().length() > 149) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDeudaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeudaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9') || (txtDeuda.getText().length() > 6)) && (c !='.')) {

            evt.consume();
        }
    }//GEN-LAST:event_txtDeudaKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char caracter = evt.getKeyChar();
        if ((!Character.isLetter(caracter)) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_SPACE) || (txtNombre.getText().length() > 49)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDeudaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeudaFocusGained
        if(txtDeuda.getText().equals("0.00")){
            txtDeuda.setText("");
        } else {
            //Do nothing
        }
    }//GEN-LAST:event_txtDeudaFocusGained

    private void txtDeudaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeudaFocusLost
        if(txtDeuda.getText().equals("")){
            txtDeuda.setText("0.00");
        } else {
           //Do nothing 
        }
    }//GEN-LAST:event_txtDeudaFocusLost

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
            java.util.logging.Logger.getLogger(NuevoClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevoClienteDialog dialog = new NuevoClienteDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardarCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDeuda;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelf;
    // End of variables declaration//GEN-END:variables
}
