package gui;

import clases.PagoProveedorClase;
import clases.Proveedor;
import clases.Usuario;
import dat.DATPagoProveedor;
import dat.DATProveedor;
import dat.DATUsuario;
import utilidades.Utilidades;
import com.sun.glass.events.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    String host;
    private String rucEmpresa;
    String nombreCta;
    String numeroCta;
    String telefono;
    String tipoCta;
    String cedula_usuario;
    DATUsuario manejadorUsuario;
    Usuario user = new Usuario();
    Utilidades util = new Utilidades();

    public PagoProveedor() {
        manejadorProveedor = new DATProveedor();
        manejadorUsuario = new DATUsuario();
        manejadorPago = new DATPagoProveedor();
        this.modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        initComponents();
        host = util.getPcName();
        cargarColumnas();
        cargarTabla();
        txtMonto.setTransferHandler(null);
        combo();
        seleccion();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(constantes.Constantes.NOMBRE_PROGRAMA);
        cedula();
        jPanel2.setVisible(false);
    }
    
    public String getRuc(){
        return rucEmpresa;
    }
    
    public void setRuc(String rucEmpresa){
        this.rucEmpresa = rucEmpresa;
    }

    public void cedula() {
        ArrayList<Usuario> cedula = manejadorUsuario.obtenerUserLog(host);
        int cant = cedula.size();
        for (int i = 0; i < cant; i++) {
            user = cedula.get(i);
            usuario = user.getNombre();
            cedula_usuario = user.getUsuario();//se toma usuario por que datusuario toma usuario para devolver el numero de cedula
            txtUsuario.setText(usuario);
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
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbBusq = new javax.swing.JComboBox<>();
        txtBusc = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ocp2 = new javax.swing.JRadioButton();
        opc1 = new javax.swing.JRadioButton();
        txtTexto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        txtUsuario = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        txtDeuda = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 709));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblProv.setModel(modelo);
        tblProv.getTableHeader().setReorderingAllowed(false);
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

        jLabel2.setText("Buscar por:");

        txtBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscKeyReleased(evt);
            }
        });

        jButton3.setText("Ver detalle");
        jButton3.setToolTipText("Haga clic aquí para ver las transacciones con el proveedor seleccionado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        txtDesc.setColumns(20);
        txtDesc.setFont(new java.awt.Font("Roboto Condensed Light", 0, 14)); // NOI18N
        txtDesc.setLineWrap(true);
        txtDesc.setRows(5);
        txtDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtDesc);

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opc1)
                    .addComponent(ocp2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTexto)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(opc1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ocp2)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        txtUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnetVendedor.png"))); // NOI18N

        txtEmpresa.setText("---------------------");

        jLabel4.setText("Empresa:");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/binoculares.png"))); // NOI18N
        btnVer.setText("Ver");
        btnVer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        txtDeuda.setText(" ");

        jLabel1.setText("Deuda:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setText("Operaciones con proveedores");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBusc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVer)))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnVer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresa)
                    .addComponent(jLabel4)
                    .addComponent(txtDeuda)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2)
                    .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opc1ActionPerformed
        txtTexto.setText("Monto a Pagar:");
        txtDesc.setText("Ingrese todo tipo de información sobre el pago realizado...");
    }//GEN-LAST:event_opc1ActionPerformed

    private void ocp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocp2ActionPerformed
        txtTexto.setText("Monto de Crédito:");
        txtDesc.setText("Ingrese todo tipo de información sobre el crédito realizado...");
    }//GEN-LAST:event_ocp2ActionPerformed

    private void tblProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvMouseClicked
        fila = tblProv.rowAtPoint(evt.getPoint());
        String strValor;
        strValor = (String) tblProv.getValueAt(fila, 0);
        txtEmpresa.setText(strValor);
        String strRuc = (String) tblProv.getValueAt(fila, 1);
        rucEmpresa = strRuc;
        nombreCta = (String) tblProv.getValueAt(fila, 2);
        String deuda = tblProv.getValueAt(fila, 5).toString();
        txtDeuda.setText(deuda);
        numeroCta = (String) tblProv.getValueAt(fila, 4);
        telefono = (String) tblProv.getValueAt(fila, 6);
        tipoCta = (String) tblProv.getValueAt(fila, 3);
        jPanel2.setVisible(true);
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
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("0.00", simbolo);

        double deuda2;
        double monto;

        try {
            String desc;
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
                    String strDeuda = formato.format(deuda2);
                    txtDeuda.setText(strDeuda);
                    objProveedor = new Proveedor(rucEmpresa, deuda2);
                    desc = txtDesc.getText();
                    manejadorProveedor.updateDeuda(objProveedor);
                    objPagoProv = new PagoProveedorClase(rucEmpresa, cedula_usuario, monto, getFecha(), "Pago", desc);
                    manejadorPago.pagoProveedor(objPagoProv);
                    JOptionPane.showMessageDialog(null, "Pago registrado exitosamente");
                    txtMonto.setText("");
                }
            } else if (ocp2.isSelected()) {
                deuda2 = deuda + Double.parseDouble(txtMonto.getText());
                String strDeuda = formato.format(deuda2);
                txtDeuda.setText(strDeuda);
                desc = txtDesc.getText();
                objProveedor = new Proveedor(rucEmpresa, deuda2);
                manejadorProveedor.updateDeuda(objProveedor);
                objPagoProv = new PagoProveedorClase(rucEmpresa, cedula_usuario, monto, getFecha(), "Crédio", desc);
                manejadorPago.pagoProveedor(objPagoProv);
                JOptionPane.showMessageDialog(null, "Crédito registrado exitosamente");
                txtMonto.setText("");
            }
        } catch (NumberFormatException e) {
            monto = 0;
        }
    }

    public void metodoGuardar() {
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
        if ((c < '0' || c > '9') || (txtMonto.getText().length()>6) && c != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VistaCreditos objVc = new VistaCreditos();
        if (tblProv.getSelectedRows().length > 0) {
            String empresa = txtEmpresa.getText();
            VistaCreditos.txtEmpresa.setText(empresa);
            objVc.setVisible(true);
            this.setVisible(false);
        }
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            metodoGuardar();
        }
    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (txtEmpresa.getText().equals("---------------------")) {

        } else {
            ActualizaProvDialog actDialg = new ActualizaProvDialog(this, true);
            actDialg.txtFila.setText(String.valueOf(fila));
            actDialg.txtEmpresa.setText(this.txtEmpresa.getText());
            actDialg.txtRUC.setText(rucEmpresa);
            actDialg.txtNombreCuenta.setText(nombreCta);
            actDialg.txtNumCuenta.setText(numeroCta);
            actDialg.txtTelefono.setText(telefono);
            actDialg.jComboBox1.setSelectedItem(tipoCta);
//            actDialg.lblIVA.setText(iva);
//            actDialg.txtCantidad.setText(String.valueOf(cantidad));
//            actDialg.txtFila.setText(String.valueOf(fila));
            actDialg.setVisible(true);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        if (txtEmpresa.getText().equals("---------------------")) {

        } else {
            ActualizaProvDialog actDialg = new ActualizaProvDialog(this, true);
            actDialg.txtEmpresa.setText(this.txtEmpresa.getText());
            actDialg.txtRUC.setText(rucEmpresa);
            actDialg.txtNombreCuenta.setText(nombreCta);
            actDialg.txtNombreCuenta.setEditable(false);
            actDialg.txtNumCuenta.setText(numeroCta);
            actDialg.txtNumCuenta.setEditable(false);
            actDialg.txtTelefono.setText(telefono);
            actDialg.txtTelefono.setEditable(false);
            actDialg.jComboBox1.setSelectedItem(tipoCta);
            actDialg.jComboBox1.setEnabled(false);
            actDialg.jComboBox1.setEditable(false);
            actDialg.btnGuardar.setVisible(false);
            actDialg.btbCerrar.setText("Cerrar");
//            actDialg.lblIVA.setText(iva);
//            actDialg.txtCantidad.setText(String.valueOf(cantidad));
//            actDialg.txtFila.setText(String.valueOf(fila));
            actDialg.setVisible(true);
        }
    }//GEN-LAST:event_btnVerActionPerformed

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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JRadioButton ocp2;
    private javax.swing.JRadioButton opc1;
    private javax.swing.ButtonGroup rbtn;
    public static final javax.swing.JTable tblProv = new javax.swing.JTable();
    private javax.swing.JTextField txtBusc;
    private javax.swing.JTextArea txtDesc;
    private javax.swing.JLabel txtDeuda;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JLabel txtTexto;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
