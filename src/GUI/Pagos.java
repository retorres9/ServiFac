package GUI;

import Clases.AbonoCliente;
import Clases.Clientes;
import Clases.Configuracion;
import Clases.Usuario;
import Clases.Venta;
import Dat.DATAbonoCliente;
import Dat.DATClientes;
import Dat.DATUsuario;
import Dat.DATVenta;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class Pagos extends javax.swing.JFrame {

    NewCliente objClienteNew = new NewCliente();
    Inventario objBuspProd = new Inventario();
    IngresoProd objIngrProd = new IngresoProd();
    int fila, idVenta;
    Venta objVenta = new Venta();
    String strTotal, nombre;
    AbonoCliente abono = new AbonoCliente();
    String vendedor = Constantes.Constantes.USUARIO;
    DefaultTableModel modelo = new DefaultTableModel();
    DATAbonoCliente manejadorAbono;
    DATClientes cliente;
    Clientes objCliente;
    DATVenta manejadorVenta;
    DATUsuario manejadorUsuario;
    Usuario usuario = new Usuario();
    String cedUsuario;

    public Pagos() {
        initComponents();
        cliente = new DATClientes();
        manejadorAbono = new DATAbonoCliente();
        manejadorVenta = new DATVenta();
        manejadorUsuario = new DATUsuario();
        cargaColumnas();
        setAnchoColumnas();
        cargaCombo();
        usuario();
        iconos();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        cargarTabla();
        permisos();
        txtVendedor.setText(vendedor);
        System.out.println(cedUsuario);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccion = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnAceptar = new javax.swing.JButton();
        txtMonto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        txtVendedor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDeuda = new javax.swing.JLabel();
        txtTelf = new javax.swing.JLabel();
        btnActualizarNombre = new javax.swing.JLabel();
        btnActualizaCedula = new javax.swing.JLabel();
        btnActualizaTelf = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JLabel();
        btnActualizaDir = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jmiElimProd = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jmiElimCliente = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jmiElimProv = new javax.swing.JMenuItem();
        jmConfig = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 755));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblClientes.setModel(modelo);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Monto a cancelar:");

        jButton2.setText("Atrás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Buscar por:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Roboto Cn", 1, 24)); // NOI18N
        jLabel9.setText("Pago de clientes");

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        txtVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnetVendedor.png"))); // NOI18N
        txtVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del cliente"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nombre de Cliente:");

        txtNombre.setText("----------------------------");
        txtNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Cedula:");

        txtCedula.setText("------------------");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Telefono:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Deuda:");

        txtDeuda.setText("----------");

        txtTelf.setText("------------------");

        btnActualizarNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizarNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarNombreMouseClicked(evt);
            }
        });

        btnActualizaCedula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaCedula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaCedulaMouseClicked(evt);
            }
        });

        btnActualizaTelf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaTelf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaTelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaTelfMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Dirección:");

        txtDireccion.setText("--------------------------------------------------");
        txtDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnActualizaDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaDir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaDir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaDirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDeuda)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizaDir)
                            .addComponent(btnActualizarNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizaCedula)
                            .addComponent(btnActualizaTelf))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtNombre))
                            .addComponent(btnActualizarNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCedula)))
                    .addComponent(btnActualizaCedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTelf))
                    .addComponent(btnActualizaTelf, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDeuda))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnActualizaDir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setText("Productos");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Ingresar Producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Buscar Producto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jmiElimProd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jmiElimProd.setText("Quitar Producto");
        jmiElimProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiElimProdActionPerformed(evt);
            }
        });
        jMenu2.add(jmiElimProd);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Clientes");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Ingresar Cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Buscar Cliente");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jmiElimCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        jmiElimCliente.setText("Quitar Cliente");
        jmiElimCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiElimClienteActionPerformed(evt);
            }
        });
        jMenu3.add(jmiElimCliente);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Proveedores");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setText("Ingresar Proveedor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setText("Buscar Proveedor");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jmiElimProv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK));
        jmiElimProv.setText("Quitar Proveedor");
        jmiElimProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiElimProvActionPerformed(evt);
            }
        });
        jMenu4.add(jmiElimProv);

        jMenuBar1.add(jMenu4);

        jmConfig.setText("Configuracion");
        jMenuBar1.add(jmConfig);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAceptar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVer)))))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(197, 197, 197))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVendedor))))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void usuario() {
        ArrayList<Usuario> cedula = manejadorUsuario.obtenerCedula(vendedor);
        int cantUser = cedula.size();
        for (int i = 0; i < cantUser; i++) {
            usuario = cedula.get(i);
            cedUsuario = usuario.getCedulaUsuario();
        }
    }

    public static void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblClientes.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblClientes.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblClientes.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (65 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (70 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void cargaColumnas() {
        modelo.addColumn("Nombres");
        modelo.addColumn("Cedula");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Deuda");
        modelo.addColumn("Dirección");
    }

    public void permisos() {
        String rol = Configuracion.validacion();
        if (rol.equals("0")) {
            jmiElimProd.setEnabled(false);
            jmiElimCliente.setEnabled(false);
            jmiElimProv.setEnabled(false);
            jmConfig.setEnabled(false);
        }
        if (rol.equals("1")) {
            jmiElimProd.setEnabled(true);
            jmiElimCliente.setEnabled(true);
            jmiElimProv.setEnabled(true);
            jmConfig.setEnabled(true);
        }
    }

    public void iconos() {
        btnActualizarNombre.setVisible(false);
        btnActualizaCedula.setVisible(false);
        btnActualizaTelf.setVisible(false);
        btnActualizaDir.setVisible(false);
        btnActualizarNombre.setToolTipText("Haga clic para actualizar el nombre del cliente");
        btnActualizaCedula.setToolTipText("Haga clic para actualizar el número de cédula del cliente");
        btnActualizaTelf.setToolTipText("Haga clic para actualizar el número de teléfono del cliente");
        btnActualizaDir.setToolTipText("Haga clic para actualizar la dirección del cliente");
    }

    public void cargaCombo() {
        cmbClientes.addItem("Nombre");
        cmbClientes.addItem("Cedula");
    }
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        fila = tblClientes.rowAtPoint(evt.getPoint());
        String strNombre = (String) tblClientes.getModel().getValueAt(fila, 0);
        txtNombre.setText(strNombre);
        nombre = txtNombre.getText();
        String cedula = (String) tblClientes.getModel().getValueAt(fila, 1);
        txtCedula.setText(cedula);
        String telf = (String) tblClientes.getModel().getValueAt(fila, 2);
        txtTelf.setText(telf);
        String deuda = tblClientes.getModel().getValueAt(fila, 3).toString();
        txtDeuda.setText(deuda);
        String direccion = (String) tblClientes.getValueAt(fila, 4);
        txtDireccion.setText(direccion);
        btnActualizarNombre.setVisible(true);
        btnActualizaCedula.setVisible(true);
        btnActualizaTelf.setVisible(true);
        btnActualizaDir.setVisible(true);
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (txtNombre.getText().equals("----------------------------")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente");
        } else {
            actualCuenta();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    public void pagoVentaXVenta() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            double cantPaga, totalVenta;
            int idVenta;
            ArrayList<Venta> datos = manejadorVenta.cargaVentasNoCancel(txtCedula.getText());
            int cant = datos.size();

            for (int i = 0; i < cant; i++) {
                objVenta = datos.get(i);
                int intIdVenta = objVenta.getIntIdVenta();
                totalVenta = objVenta.getDblTotalVenta();
                cantPaga = objVenta.getDblValCancelado();
                double aux = totalVenta - cantPaga;
                if (monto <= aux) {
                    cantPaga = cantPaga + monto;
                    manejadorVenta.actualizaPago(intIdVenta, cantPaga);
                    break;
                } else {
                    //se borra la siguiente linea de este if  --if (aux < monto)--
                    monto = monto - aux;
                    cantPaga = totalVenta;
                    manejadorVenta.actualizaPago(intIdVenta, cantPaga);
                }
            }
        } catch (NumberFormatException ex) {
            //Do nothing
        }

    }

    public void actualCuenta() {
        if (txtMonto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un monto");
        } else {
            int n = JOptionPane.showConfirmDialog(null, ("El Cliente: "
                    + txtNombre.getText() + " ha realizado un pago de $" + txtMonto.getText()),
                    "Aviso!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                pago();
                cargarTabla();
                pagoVentaXVenta();
                vistaReporte();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha realizado ninguna acción");
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Principal objPrin = new Principal();
        objPrin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String dato = txtBuscar.getText();
        if (cmbClientes.getSelectedItem().equals("Nombre")) {
            ArrayList<Clientes> listadoClienteNom = cliente.ConsultarPorNombre(dato);
            int cantidadListado = listadoClienteNom.size();
            modelo.setNumRows(cantidadListado);
            for (int i = 0; i < cantidadListado; i++) {
                objCliente = listadoClienteNom.get(i);
                String nombreCli = objCliente.getStrNombre();
                String cedula = objCliente.getStrCedula();
                String telf = objCliente.getStrTelf();
                double deuda = objCliente.getDblDeuda();
                String direccion = objCliente.getStrDireccion();
                modelo.setValueAt(nombreCli, i, 0);
                modelo.setValueAt(cedula, i, 1);
                modelo.setValueAt(telf, i, 2);
                modelo.setValueAt(deuda, i, 3);
                modelo.setValueAt(direccion, i, 4);
            }
        }
        if (cmbClientes.getSelectedItem().equals("Cedula")) {
            try {
                ArrayList<Clientes> listadoClienteCed = cliente.ConsultarCedula(dato);
                int cantListado = listadoClienteCed.size();
                modelo.setNumRows(cantListado);
                for (int i = 0; i < cantListado; i++) {
                    objCliente = listadoClienteCed.get(i);
                    String nombreCli = objCliente.getStrNombre();
                    String cedula = objCliente.getStrCedula();
                    String telf = objCliente.getStrTelf();
                    double deuda = objCliente.getDblDeuda();
                    String direccion = objCliente.getStrDireccion();
                    modelo.setValueAt(nombreCli, i, 0);
                    modelo.setValueAt(cedula, i, 1);
                    modelo.setValueAt(telf, i, 2);
                    modelo.setValueAt(deuda, i, 3);
                    modelo.setValueAt(direccion, i, 4);
                }
            } catch (NumberFormatException ex) {

            }

        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnActualizarNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarNombreMouseClicked
        try {
            String n;
            n = JOptionPane.showInputDialog(null, "Actualice el nuevo nombre del cliente:\n" + txtNombre.getText().toUpperCase());
            if (n.isEmpty()) {

            } else {
                String telf = txtTelf.getText();
                String direccion = txtDireccion.getText();
                String cedula = txtCedula.getText();
                objCliente = new Clientes(n, cedula, telf, direccion);
                try {
                    cliente.actualizarCliente(objCliente);
                } catch (SQLException ex) {
                    Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtNombre.setText(n);
                cargarTabla();
            }

        } catch (NullPointerException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnActualizarNombreMouseClicked

    private void btnActualizaCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaCedulaMouseClicked
        try {
            String ced;
            ced = JOptionPane.showInputDialog(null, "Actualice el numero de cédula del cliente:\n" + txtNombre.getText());
            String nom = txtNombre.getText();
            if (ced.length() != 10 && ced.length() != 13) {
                JOptionPane.showMessageDialog(null, "Ha ingresado un número de cedula o RUC inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                objCliente = new Clientes(nom, ced);
                cliente.actualizarCedulaCliente(objCliente);
                txtCedula.setText(ced);
                cargarTabla();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato de Cédula Incorrecto", "Aviso", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {

        }
    }//GEN-LAST:event_btnActualizaCedulaMouseClicked

    private void btnActualizaTelfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaTelfMouseClicked
        try {
            String telf;
            telf = JOptionPane.showInputDialog(null, "Actualice el nuevo número de teléfono del cliente:\n" + txtNombre.getText());
            if (telf.length() != 7 && telf.length() != 10) {
                JOptionPane.showMessageDialog(null, "El número ingresado no es un número de teléfono valido", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombreCli = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String cedula = txtCedula.getText();
                objCliente = new Clientes(nombreCli, cedula, telf, direccion);
                cliente.actualizarCliente(objCliente);
                txtTelf.setText(telf);
                cargarTabla();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato de telefono Incorrecto", "Aviso", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizaTelfMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objPrin = new Principal();
        objPrin.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IngresoProd objIp = new IngresoProd();
        objIp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Inventario objInv = new Inventario();
        objInv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jmiElimProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiElimProdActionPerformed
        EliminarProducto objElmProd = new EliminarProducto();
        objElmProd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jmiElimProdActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        NewCliente objNc = new NewCliente();
        objNc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Pagos objPa = new Pagos();
        objPa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmiElimClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiElimClienteActionPerformed
        EliminarCliente objElmCl = new EliminarCliente();
        objElmCl.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jmiElimClienteActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        NuevoProveedor objNp = new NuevoProveedor();
        objNp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        PagoProveedor objPP = new PagoProveedor();
        objPP.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jmiElimProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiElimProvActionPerformed
        EliminarProveedor objElimProv = new EliminarProveedor();
        objElimProv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jmiElimProvActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        VistaDeudaCliente vf = new VistaDeudaCliente();
        if (tblClientes.getSelectedRowCount() > 0) {
            int filaSelec = tblClientes.getSelectedRow();
            String nombreCli = (String) tblClientes.getValueAt(filaSelec, 0);
            VistaDeudaCliente.txtNombreCliente.setText(nombreCli);
            vf.setCliente(nombreCli);
            vf.cargarTabla();
            vf.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnActualizaDirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaDirMouseClicked
        String direccion;
        try {
            direccion = JOptionPane.showInputDialog(null, "Ingrese la nueva dirección para el cliente:\n" + txtNombre.getText().toUpperCase());
            if (direccion.isEmpty()) {

            } else {
                String nom = txtNombre.getText();
                String ced = txtCedula.getText();
                String telf = txtTelf.getText();
                objCliente = new Clientes(nom, ced, telf, direccion);
                cliente.actualizarCliente(objCliente);
                txtDireccion.setText(direccion);
                cargarTabla();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "No ha ingresado ninguna dirección", "Erroe", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizaDirMouseClicked

    public void cargarTabla() {
        ArrayList<Clientes> listadoClientes = cliente.ObtenerClientes();
        int cantLista = listadoClientes.size();
        modelo.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            objCliente = listadoClientes.get(i);
            String nombres = objCliente.getStrNombre();
            String cedula = objCliente.getStrCedula();
            String telf = objCliente.getStrTelf();
            double deuda = objCliente.getDblDeuda();
            String direccion = objCliente.getStrDireccion();
            modelo.setValueAt(nombres, i, 0);
            modelo.setValueAt(cedula, i, 1);
            modelo.setValueAt(telf, i, 2);
            modelo.setValueAt(deuda, i, 3);
            modelo.setValueAt(direccion, i, 4);
        }
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(ahora);
    }

    public void vistaReporte() { //Este metodo es para llemar en la interfaz de Reportes
        try {
            String cedulaCliente = txtCedula.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            abono = new AbonoCliente(cedulaCliente, cedUsuario, monto, getFechaActual());
            manejadorAbono.abonoCliente(abono);
        } catch (NumberFormatException ex) {
            //Do nothing
        }
    }

    public void pago() {
        try {
            double deuda2;
            double monto = Double.parseDouble(txtMonto.getText());
            double deuda = Double.parseDouble(txtDeuda.getText());
            if (deuda <= 0) {
                JOptionPane.showMessageDialog(null, "No puede realizar esta accion ya que no existe deuda del cliente: " + txtNombre.getText());
            }
            if (monto > deuda) {
                JOptionPane.showMessageDialog(null, "El monto pagado excede el monto adeudado");
            }
            if (monto <= deuda) {
                deuda2 = deuda - monto;
                txtDeuda.setText(Double.toString(deuda2));
                String ced = txtCedula.getText();
                objCliente = new Clientes(deuda2, ced);
                cliente.agregarDeuda(objCliente);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor ingresado incorrecto");
        }
    }

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
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Seleccion;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel btnActualizaCedula;
    private javax.swing.JLabel btnActualizaDir;
    private javax.swing.JLabel btnActualizaTelf;
    private javax.swing.JLabel btnActualizarNombre;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private static final javax.swing.JTable tblClientes = new javax.swing.JTable();
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtCedula;
    private javax.swing.JLabel txtDeuda;
    private javax.swing.JLabel txtDireccion;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtTelf;
    private javax.swing.JLabel txtVendedor;
    // End of variables declaration//GEN-END:variables
}
