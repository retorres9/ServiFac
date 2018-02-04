package GUI;

import Clases.PagoProveedorClase;
import Clases.Proveedor;
import Clases.Usuario;
import Dat.DATPagoProveedor;
import Dat.DATProveedor;
import Dat.DATUsuario;
import com.sun.glass.events.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class PagoProveedor extends javax.swing.JFrame {

    int fila;
    Proveedor objProveedor = new Proveedor();
    DefaultTableModel modelo = new DefaultTableModel();
    PagoProveedorClase objPagoProv = new PagoProveedorClase();
    DATProveedor manejadorProveedor;
    DATPagoProveedor manejadorPago;
    String usuario;
    DATUsuario manejadorUsuario;
    Usuario user = new Usuario();

    public PagoProveedor() {
        initComponents();
        manejadorProveedor = new DATProveedor();
        manejadorUsuario = new DATUsuario();
        manejadorPago = new DATPagoProveedor();
        usuario = Constantes.Constantes.USUARIO;
        cedula();
        cargarColumnas();
        cargarTabla();
        combo();
        seleccion();
        this.txtDeuda.setEditable(false);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
    }

    public void cedula() {
        ArrayList<Usuario> cedula = manejadorUsuario.obtenerCedula(usuario);
        int cant = cedula.size();
        for (int i = 0; i < cant; i++) {
            user = cedula.get(i);
            usuario = user.getCedulaUsuario();
        }
    }

    public void combo() {
        cmbBusq.addItem("Nombre de la Empresa");
        cmbBusq.addItem("Nombre de la Cuenta");
    }

    public void seleccion() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opc1);
        grupo.add(ocp2);
    }

    public void cargarColumnas() {
        modelo.addColumn("Empresa");
        modelo.addColumn("RUC");
        modelo.addColumn("Nombre de la Cta");
        modelo.addColumn("Tipo de Cuenta");
        modelo.addColumn("N° de cuenta");
        modelo.addColumn("Deuda");
        modelo.addColumn("Telefono");
    }

    public void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblProv.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblProv.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblProv.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (55 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (35 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (45 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (25 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void cargarTablaBusquedaNombre(String busq) {
        setAnchoColumnas();
        ArrayList<Proveedor> listadoProv = manejadorProveedor.buscarNombreCuenta(busq);
        int cantLista = listadoProv.size();
        modelo.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            objProveedor = listadoProv.get(i);
            String empresa = objProveedor.getStrEmpresa();
            String ruc = objProveedor.getRuc();
            String nombre = objProveedor.getStrNombreCuenta();
            String tipo = objProveedor.getStrTipo();
            String numero = objProveedor.getStrNumCuenta();
            double deuda = objProveedor.getDblDeuda();
            String telf = objProveedor.getStrTelf();

            modelo.setValueAt(empresa, i, 0);
            modelo.setValueAt(ruc, i, 1);
            modelo.setValueAt(nombre, i, 2);
            modelo.setValueAt(tipo, i, 3);
            modelo.setValueAt(numero, i, 4);
            modelo.setValueAt(deuda, i, 5);
            modelo.setValueAt(telf, i, 6);
        }
    }

    public void cargarTablaBusquedaEmpresa(String busq) {
        setAnchoColumnas();
        ArrayList<Proveedor> listadoProv = manejadorProveedor.buscarEmpresa(busq);
        int cantLista = listadoProv.size();
        modelo.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            objProveedor = listadoProv.get(i);
            String empresa = objProveedor.getStrEmpresa();
            String ruc = objProveedor.getRuc();
            String nombre = objProveedor.getStrNombreCuenta();
            String tipo = objProveedor.getStrTipo();
            String numero = objProveedor.getStrNumCuenta();
            double deuda = objProveedor.getDblDeuda();
            String telf = objProveedor.getStrTelf();

            modelo.setValueAt(empresa, i, 0);
            modelo.setValueAt(ruc, i, 1);
            modelo.setValueAt(nombre, i, 2);
            modelo.setValueAt(tipo, i, 3);
            modelo.setValueAt(numero, i, 4);
            modelo.setValueAt(deuda, i, 5);
            modelo.setValueAt(telf, i, 6);
        }
    }

    public void cargarTabla() {
        setAnchoColumnas();
        ArrayList<Proveedor> listaProv = manejadorProveedor.obtenerTODOempresa();
        int cantLista = listaProv.size();
        modelo.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            objProveedor = listaProv.get(i);
            String empresa = objProveedor.getStrEmpresa();
            String ruc = objProveedor.getRuc();
            String nombre = objProveedor.getStrNombreCuenta();
            String tipo = objProveedor.getStrTipo();
            String numero = objProveedor.getStrNumCuenta();
            double deuda = objProveedor.getDblDeuda();
            String telf = objProveedor.getStrTelf();

            modelo.setValueAt(empresa, i, 0);
            modelo.setValueAt(ruc, i, 1);
            modelo.setValueAt(nombre, i, 2);
            modelo.setValueAt(tipo, i, 3);
            modelo.setValueAt(numero, i, 4);
            modelo.setValueAt(deuda, i, 5);
            modelo.setValueAt(telf, i, 6);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtn = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProv = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbBusq = new javax.swing.JComboBox<>();
        txtBusc = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDeuda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumCuenta = new javax.swing.JLabel();
        txtTelf = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        ocp2 = new javax.swing.JRadioButton();
        opc1 = new javax.swing.JRadioButton();
        txtTexto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
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
        setMinimumSize(new java.awt.Dimension(1024, 754));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblProv.setModel(modelo);
        tblProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProvMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProv);

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        txtBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscKeyReleased(evt);
            }
        });

        jButton3.setText("Ver");
        jButton3.setToolTipText("Haga clic aquí para ver las transacciones con el proveedor seleccionado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("Descripcion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles del proveedor"));

        jLabel3.setText("Deuda:");

        txtDeuda.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtDeuda.setForeground(new java.awt.Color(255, 0, 0));
        txtDeuda.setEnabled(false);

        jLabel4.setText("Empresa:");

        txtEmpresa.setText("---------------------");

        jLabel5.setText("N° de Cuenta:");

        txtNumCuenta.setText("---------------------");

        txtTelf.setText("---------------------");

        jLabel6.setText("Teléfono:");

        jLabel9.setText("RUC:");

        txtRuc.setText("--------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmpresa))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumCuenta)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtRuc))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelf))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        txtDesc.setColumns(20);
        txtDesc.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtDesc.setLineWrap(true);
        txtDesc.setRows(5);
        txtDesc.setText("Ingrese información acerca del pago o crédito reallizado...");
        txtDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtDesc);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("¿Que acción desa realizar?"));

        ocp2.setText("Crédito");
        ocp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocp2ActionPerformed(evt);
            }
        });

        opc1.setText("Pago");
        opc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opc1ActionPerformed(evt);
            }
        });

        txtTexto.setText("-------------------");

        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opc1)
                    .addComponent(ocp2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtMonto))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opc1)
                    .addComponent(txtTexto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ocp2)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBusc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(188, 188, 188))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opc1ActionPerformed
        txtTexto.setText("Monto a Pagar:");
    }//GEN-LAST:event_opc1ActionPerformed

    private void ocp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocp2ActionPerformed
        txtTexto.setText("Monto de Crédito:");
    }//GEN-LAST:event_ocp2ActionPerformed

    private void tblProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvMouseClicked
        fila = tblProv.rowAtPoint(evt.getPoint());
        String strValor;
        strValor = (String) tblProv.getValueAt(fila, 0);
        txtEmpresa.setText(strValor);
        strValor = (String) tblProv.getValueAt(fila, 1);
        txtRuc.setText(strValor);
        strValor = (String) tblProv.getValueAt(fila, 4);
        txtNumCuenta.setText(strValor);
        strValor = (String) tblProv.getValueAt(fila, 5).toString();
        txtDeuda.setText(strValor);
        strValor = (String) tblProv.getValueAt(fila, 6);
        txtTelf.setText(strValor);
    }//GEN-LAST:event_tblProvMouseClicked

    private void txtBuscKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscKeyReleased

        String texto = txtBusc.getText();
        if (cmbBusq.getSelectedItem().equals("Nombre de la Empresa")) {
            cargarTablaBusquedaEmpresa(texto);
        }
        if (cmbBusq.getSelectedItem().equals("Nombre de la Cuenta")) {
            cargarTablaBusquedaNombre(texto);
        }
    }//GEN-LAST:event_txtBuscKeyReleased

    public void Credito() {
        double deuda2;
        double monto;

        try {
            String desc = null;
            if ((txtDesc.getText().isEmpty()) || txtDesc.getText().equals("Ingrese información acerca del pago o crédito reallizado...")) {
                desc = "(Vacio)";
            }
            double deuda = Double.parseDouble(txtDeuda.getText());
            monto = Double.parseDouble(txtMonto.getText());
            if (opc1.isSelected()) {
                if (deuda <= 0) {
                    JOptionPane.showMessageDialog(null, "No puede realizar esta accion ya que no existe deuda del proveedor: " + txtEmpresa.getText());
                }
                if (monto > deuda) {
                    JOptionPane.showMessageDialog(null, "El monto pagado excede el monto adeudado");
                }
                if (monto <= deuda) {
                    deuda2 = deuda - monto;
                    txtDeuda.setText(Double.toString(deuda2));
                    String ruc = txtRuc.getText();
                    objProveedor = new Proveedor(ruc, deuda2);

                    manejadorProveedor.updateDeuda(objProveedor);
                    objPagoProv = new PagoProveedorClase(ruc, usuario, monto, ruc, "Pago", desc);
                    manejadorPago.pagoProveedor(objPagoProv);
                }
            } else if (ocp2.isSelected()) {
                deuda2 = deuda + Double.parseDouble(txtMonto.getText());
                txtDeuda.setText(Double.toString(deuda2));
                String ruc = txtRuc.getText();
                objProveedor = new Proveedor(ruc, deuda);
                manejadorProveedor.updateDeuda(objProveedor);
                objPagoProv = new PagoProveedorClase(ruc, usuario, monto, ruc, "Crédio", desc);
                manejadorPago.pagoProveedor(objPagoProv);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            monto = 0;
        }
    }
    
    public void metodoGuardar(){
        if (txtMonto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un monto");
        } else if (!opc1.isSelected() && !ocp2.isSelected()) {
            JOptionPane.showMessageDialog(null, "Tiene que seleccionar una accion");
        } else if (txtEmpresa.getText().equals("---------------------")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor");
        } else {
            if (opc1.isSelected()) {
                int n = JOptionPane.showConfirmDialog(null, "¿Usted ha realizado un pago a:\n"
                        + txtEmpresa.getText() + "?", "Aviso!", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    Credito();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha realizado ninguna acción");
                }
            }
            if (ocp2.isSelected()) {
                int n = JOptionPane.showConfirmDialog(null, "¿Usted ha realizado un credito a:\n"
                        + txtEmpresa.getText() + "?", "Aviso!", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    Credito();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha realizado ninguna acción");
                }
            }
            cargarTabla();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        metodoGuardar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objPrin = new Principal();
        objPrin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objPrin = new Principal();
        objPrin.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') && c != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IngresoProd objIp = new IngresoProd();
        objIp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        BusqProd objBp = new BusqProd();
        objBp.setVisible(true);
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VistaCreditos objVc = new VistaCreditos();
        if (tblProv.getSelectedRows().length > 0) {
            String empresa = txtEmpresa.getText();
            VistaCreditos.txtEmpresa.setText(empresa);
            VistaCreditos.cargarTabla();
            objVc.setVisible(true);
        }

        objVc.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtDescFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescFocusGained
        txtDesc.setText("");
    }//GEN-LAST:event_txtDescFocusGained

    private void txtDescFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescFocusLost
        if (txtDesc.getText().isEmpty()) {
            txtDesc.setText("Ingrese información acerca del pago reallizado...");
        }
    }//GEN-LAST:event_txtDescFocusLost

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            metodoGuardar();
        }
    }//GEN-LAST:event_txtMontoKeyReleased

    public static String getFecha() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagoProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JRadioButton ocp2;
    private javax.swing.JRadioButton opc1;
    private javax.swing.ButtonGroup rbtn;
    private javax.swing.JTable tblProv;
    private javax.swing.JTextField txtBusc;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JTextField txtDeuda;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JLabel txtNumCuenta;
    private javax.swing.JLabel txtRuc;
    private javax.swing.JLabel txtTelf;
    private javax.swing.JLabel txtTexto;
    // End of variables declaration//GEN-END:variables
}
