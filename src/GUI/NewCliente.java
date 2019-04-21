package GUI;

import Clases.Clientes;
import Clases.Configuracion;
import Clases.Usuario;
import Dat.DATClientes;
import Dat.DATUsuario;
import Utilidades.Utilidades;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public final class NewCliente extends javax.swing.JFrame {

    Clientes objCliente = new Clientes();
    Configuracion config = new Configuracion();
    DATUsuario manejadorUsuario;
    Utilidades util = new Utilidades();
    Usuario objU = new Usuario();
    String host;
    public boolean bandera;
    String usuario;
    int rol = 0;
    DATClientes cliente;

    public NewCliente() {
        cliente = new DATClientes();
        manejadorUsuario = new DATUsuario();
        initComponents();
        txtNombre.setText(null);
        txtCedula.setTransferHandler(null);
        txtCredito.setTransferHandler(null);
        txtDeuda.setTransferHandler(null);
        txtTelf.setTransferHandler(null);
        txtAyuda.setVisible(false);
        txtAyudaCed.setVisible(false);
        rbtnCredito.setSelected(false);
        txtCredito.setEnabled(false);
        host = util.getPcName();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        permisos();
    }

    public void permisos() {
        ArrayList<Usuario> rolLogged = manejadorUsuario.obtenerUserLog(host);
        int cantRol = rolLogged.size();
        for (int i = 0; i < cantRol; i++) {
            objU = rolLogged.get(i);
            rol = objU.getRol();
            usuario = objU.getCedulaUsuario();
            if (rol == 0) {
                jmiElimCliente.setEnabled(false);
                jmiElimProv.setEnabled(false);
                jmConfig.setEnabled(false);
            }
            if (rol == 1) {
                jmiElimCliente.setEnabled(true);
                jmiElimProv.setEnabled(true);
                jmConfig.setEnabled(true);
            }
        }
    }

    public void guardarCliente() {
        double deuda = 0.00;
        boolean credito = false;
        try {
            String nombre = txtNombre.getText().toUpperCase();
            String dir = txtDireccion.getText().toUpperCase();
            String cedula = txtCedula.getText();
            String telf = txtTelf.getText();
            if (txtDeuda.getText().isEmpty()) {

            } else {
                deuda = Double.parseDouble(txtDeuda.getText());
            }
            if (rbtnCredito.isSelected()) {
                credito = true;
                System.out.println(credito);
                double cantidad = Double.parseDouble(txtCredito.getText());
                objCliente = new Clientes(nombre, cedula, telf, deuda, dir, credito, txtAyuda.getText(), cantidad);
            } else {
                objCliente = new Clientes(nombre, cedula, telf, deuda, dir);
            }

            try {
                if (rbtnCredito.isSelected()) {
                    if (cliente.InsertarCliente2(objCliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente creado satisfactoriamente");
                        txtNombre.setText("");
                        txtDireccion.setText("");
                        txtCedula.setText("");
                        txtTelf.setText("");
                        txtDeuda.setText("0.00");
                        txtCredito.setText("");
                        rbtnCredito.setSelected(false);
                        txtCredito.setEnabled(false);
                    }
                } else {
                    if (cliente.InsertarCliente(objCliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente creado satisfactoriamente");
                        txtNombre.setText("");
                        txtDireccion.setText("");
                        txtCedula.setText("");
                        txtTelf.setText("");
                        txtDeuda.setText("0.00");
                        txtCredito.setText("");
                        rbtnCredito.setSelected(false);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(NewCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El valor ingresado en deuda o crédito no"
                    + " es valido\nEjemplo (99.99)");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnGuardarCli = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtTelf = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtDeuda = new javax.swing.JTextField();
        rbtnCredito = new javax.swing.JRadioButton();
        txtCredito = new javax.swing.JTextField();
        txtAyuda = new javax.swing.JLabel();
        txtAyudaCed = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jmiElimCliente = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jmiElimProv = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jmConfig = new javax.swing.JMenu();

        jLabel2.setText("jLabel2");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(606, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel1.setText("Datos del Cliente");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel4.setText("Cedula:");

        jLabel5.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel5.setText("Telefono:");

        jLabel6.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel6.setText("Deuda:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

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

        txtTelf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelfKeyTyped(evt);
            }
        });

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto Condensed Light", 1, 14)); // NOI18N
        jLabel10.setText("Direccion:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

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

        rbtnCredito.setText("Crédito");
        rbtnCredito.setToolTipText("Seleccione para permitir crédito al cliente que está ingresando");
        rbtnCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCreditoActionPerformed(evt);
            }
        });

        txtCredito.setText(" ");
        txtCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCreditoKeyTyped(evt);
            }
        });

        txtAyuda.setText("jLabel9");

        txtAyudaCed.setText("jLabel9");

        jMenu2.setText("Producto");

        jMenuItem1.setText("Buscar producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Clientes");

        jMenuItem3.setText("Ingresar Cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Buscar Cliente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jmiElimCliente.setText("Quitar Cliente");
        jmiElimCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiElimClienteActionPerformed(evt);
            }
        });
        jMenu3.add(jmiElimCliente);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Proveedores");

        jMenuItem5.setText("Ingresar Proveedor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Buscar Proveedor");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jmiElimProv.setText("Quitar Proveedor");
        jmiElimProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiElimProvActionPerformed(evt);
            }
        });
        jMenu4.add(jmiElimProv);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Backup");

        jMenuItem7.setText("Crear Backup");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jmConfig.setText("Configuracion");
        jMenuBar1.add(jmConfig);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAyuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAyudaCed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbtnCredito)
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
                                        .addComponent(jLabel4)))))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDeuda, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txtTelf)
                            .addComponent(txtNombre)
                            .addComponent(txtCedula)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txtCredito))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addGap(79, 79, 79)
                .addComponent(btnGuardarCli)
                .addGap(166, 166, 166))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAyuda)
                        .addComponent(txtAyudaCed)))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnCredito)
                    .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnGuardarCli))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliActionPerformed

        int digitTelf = txtTelf.getText().length();
        if (txtNombre.getText().isEmpty() || txtCedula.getText().isEmpty() || txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios que no se pueden guardar");
        } else if ((txtCedula.getText().length() != 10) && (txtCedula.getText().length() != 13)) {
            JOptionPane.showMessageDialog(null, "Número de cédula o RUC incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (digitTelf != 7 && digitTelf != 10) {
            JOptionPane.showMessageDialog(null, "Número telefónico incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (rol == 1) {
                guardarCliente();
                txtAyuda.setText("nada");
            } else if (rol == 0) {
                int j = JOptionPane.showConfirmDialog(null, "No tiene permisos para asignar créditos.\n"
                        + "Solo un administrador puede aprobar créditos\n"
                        + "Si un administrador va a aprobar el crédito\n"
                        + "presione (Si) de lo contrario presione (No)", "Importante",
                        JOptionPane.YES_NO_OPTION);
                if (j == JOptionPane.YES_OPTION) {
                    
                    PermisoDialg pd = new PermisoDialg(null, true);
                    pd.setVisible(true);
                    
                    if (txtAyuda.getText().equals("ok")) {
                        guardarCliente();
                        txtAyuda.setText("nada");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se habilitó el crédito al usuario");
                    }

                }
            }
        }

    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (!txtNombre.getText().isEmpty()) {
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Si retrocede se perderan los cambios no guardados?",
                    "Aviso!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Principal prin = new Principal();
                prin.setVisible(true);
                this.dispose();
            }
        } else {
            Principal prin = new Principal();
            prin.setVisible(true);
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char caracter = evt.getKeyChar();
        if ((!Character.isLetter(caracter)) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != KeyEvent.VK_SPACE) || (txtNombre.getText().length() > 49)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (txtDireccion.getText().length() > 149) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDeudaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeudaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9') || (txtDeuda.getText().length() > 6)) && (c != '.')) {

            evt.consume();
        }
    }//GEN-LAST:event_txtDeudaKeyTyped

    private void txtDeudaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeudaFocusLost
        if (txtDeuda.getText().equals("")) {
            txtDeuda.setText("0.00");
        } else {
            //Do nothing 
        }
    }//GEN-LAST:event_txtDeudaFocusLost

    private void txtDeudaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeudaFocusGained
        if (txtDeuda.getText().equals("0.00")) {
            txtDeuda.setText("");
        } else {
            //Do nothing
        }
    }//GEN-LAST:event_txtDeudaFocusGained

    private void rbtnCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCreditoActionPerformed
        if (rbtnCredito.isSelected()) {
            txtCredito.setEnabled(true);
        } else {
            txtCredito.setEnabled(false);
            txtCredito.setText("");
        }
    }//GEN-LAST:event_rbtnCreditoActionPerformed

    private void txtCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditoKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9') || (txtCredito.getText().length() > 7)) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCreditoKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        BusquedaProd busq = new BusquedaProd(this, true);
        busq.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        NuevoClienteDialog objNc = new NuevoClienteDialog(this, rootPaneCheckingEnabled);
        objNc.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Pagos objPa = new Pagos();
        objPa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmiElimClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiElimClienteActionPerformed
        EliminarCliente objElmCl = new EliminarCliente();
        objElmCl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiElimClienteActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        NuevoProveedorDialgo objNp = new NuevoProveedorDialgo(this, rootPaneCheckingEnabled);
        objNp.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        PagoProveedor objPP = new PagoProveedor();
        objPP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jmiElimProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiElimProvActionPerformed
        EliminarProveedor objElimProv = new EliminarProveedor();
        objElimProv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jmiElimProvActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Backup back = new Backup(this, true);
        back.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardarCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JRadioButton rbtnCredito;
    public static javax.swing.JLabel txtAyuda;
    public static javax.swing.JLabel txtAyudaCed;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCredito;
    private javax.swing.JTextField txtDeuda;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelf;
    // End of variables declaration//GEN-END:variables
}
