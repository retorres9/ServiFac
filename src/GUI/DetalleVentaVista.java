package GUI;

import Clases.AbonoCliente;
import Clases.PagoProveedorClase;
import Clases.Venta;
import Dat.DATAbonoCliente;
import Dat.DATPagoProveedor;
import Dat.DATVenta;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class DetalleVentaVista extends javax.swing.JFrame {

    Venta objVenta = new Venta();
    DATVenta manejadorVenta;
    String cedula, nombre, total;
    public static String id_Venta;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel();
    AbonoCliente abono = new AbonoCliente();
    DATAbonoCliente manejadorAbono;
    PagoProveedorClase pago = new PagoProveedorClase();
    DATPagoProveedor manejadorPago;

    public DetalleVentaVista() {
        manejadorVenta = new DATVenta();
        manejadorAbono = new DATAbonoCliente();
        manejadorPago = new DATPagoProveedor();
        this.modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if ((column == 0) || (column == 1) || (column == 2) || (column == 3) || (column == 4) || (column == 5)) {
                    return false;
                } else {
                    return false;
                }
            }
        };
        initComponents();
        txtId.setVisible(false);
        encabezados();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        tablaVentas();
        tablaPagosCL();
        tablaPagoPR();
        sumar();
    }

    public void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblVentas.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblVentas.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblVentas.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (50 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (30 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void sumar() {
        double aux1 = 0, aux2 = 0, aux3 = 0;
        if (tblVentas.getRowCount() > 0) {
            int totalRow = tblVentas.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblVentas.getValueAt(i, 4)));
                sumatoria1 += sumatoria;
                aux1 = sumatoria1;
            }
            txtTotalVentas.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux1 = 0.00;
            txtTotalVentas.setText("Total: $" + String.valueOf(aux1));
        }
        if (tblPagoCl.getRowCount() > 0) {
            int totalRow = tblPagoCl.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblPagoCl.getValueAt(i, 2)));
                sumatoria1 += sumatoria;
                aux2 = sumatoria1;

            }
            txtTotalAbonos.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux2 = 0.00;
            txtTotalAbonos.setText("Total: $" + String.valueOf(aux2));
        }
        if (tblPagoPr.getRowCount() > 0) {
            int totalRow = tblPagoPr.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblPagoPr.getValueAt(i, 2)));
                sumatoria1 += sumatoria;
                aux3 = sumatoria1;
            }
            txtTotalPagoPr.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux3 = 0.00;
            txtTotalPagoPr.setText("Total: $" + String.valueOf(aux3));
        }
        double totalDia = aux1 + aux2 - aux3;
        txtTotal.setText("$ " + String.valueOf(totalDia));
    }

    public void tablaVentas() {
        String fecha = calendario.getText();
        ArrayList<Venta> listadoVentas = manejadorVenta.vistaVenta(fecha);
        int cantFilas = listadoVentas.size();
        modelo.setNumRows(cantFilas);
        for (int i = 0; i < cantFilas; i++) {
            objVenta = listadoVentas.get(i);
            int idVenta = objVenta.getIntIdVenta();
            String cedulaCliente = objVenta.getCedulaUser();
            String nombreCliente = objVenta.getStrFecha();
            double totalVenta = objVenta.getDblTotalVenta();
            double valCancelado = objVenta.getDblValCancelado();
            //String fechaVenta = objVenta.getStrFecha();

            modelo.setValueAt(idVenta, i, 0);
            modelo.setValueAt(cedulaCliente, i, 1);
            modelo.setValueAt(nombreCliente, i, 2);
            modelo.setValueAt(totalVenta, i, 3);
            modelo.setValueAt(valCancelado, i, 4);
            //modelo.setValueAt(fechaVenta, i, 5);
        }
        jTabbedPane1.setTitleAt(0, "Ventas (" + String.valueOf(cantFilas) + ")");
    }

    public void encabezados() {
        modelo.addColumn("Id Venta");
        modelo.addColumn("Cédula de cliente");
        modelo.addColumn("Nombre de cliente");
        modelo.addColumn("Total de venta");
        modelo.addColumn("Valor cancelado");

        modelo2.addColumn("Cliente");
        modelo2.addColumn("Cédula de cliente");
        modelo2.addColumn("Monto de Abono");
        modelo2.addColumn("Deuda");
        modelo2.addColumn("Uusario");

        modelo3.addColumn("Empresa");
        modelo3.addColumn("Deuda");
        modelo3.addColumn("Valor Cancelado");
        modelo3.addColumn("Usuario");
    }

    public void tablaPagosCL() {
        setAnchoColumnas();
        String fecha = calendario.getText();
        ArrayList<AbonoCliente> listadoPagosCL = manejadorAbono.verAbonos(fecha);
        int cantLista = listadoPagosCL.size();
        modelo2.setNumRows(cantLista);

        for (int i = 0; i < cantLista; i++) {
            abono = listadoPagosCL.get(i);
            String cliente = abono.getNombreCL();
            String cedula = abono.getStrCedula();
            double monto = abono.getDblMontoAbono();
            double deuda = abono.getDeuda();
            String usuario = abono.getStrUsuario();

            modelo2.setValueAt(cliente, i, 0);
            modelo2.setValueAt(cedula, i, 1);
            modelo2.setValueAt(monto, i, 2);
            modelo2.setValueAt(deuda, i, 3);
            modelo2.setValueAt(usuario, i, 4);
        }
        jTabbedPane1.setTitleAt(1, "Pago Clientes (" + String.valueOf(cantLista) + ")");
    }

    public void tablaPagoPR() {
        String fecha = calendario.getText();
        setAnchoColumnas();
        ArrayList<PagoProveedorClase> listadoPagosPR = manejadorPago.verPagosPorFecha(fecha);
        int cantLista = listadoPagosPR.size();
        modelo3.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            pago = listadoPagosPR.get(i);
            String empresa = pago.getEmpresa();
            String cliente = pago.getUsuario();
            double deuda = pago.getDeuda();
            double monto = pago.getDblMontoCancelado();

            modelo3.setValueAt(empresa, i, 0);
            modelo3.setValueAt(deuda, i, 1);
            modelo3.setValueAt(monto, i, 2);
            modelo3.setValueAt(cliente, i, 3);
        }
        jTabbedPane1.setTitleAt(2, "Pago Proveedores (" + String.valueOf(cantLista) + ")");
    }

    public void busquedafiltrada() {
        String fecha = calendario.getText();
        String nombreCleinte = txtBusqueda.getText();
        ArrayList<Venta> listadoVentas = manejadorVenta.vistaVentaFiltrada(fecha, nombreCleinte);
        int cantFilas = listadoVentas.size();
        modelo.setNumRows(cantFilas);
        for (int i = 0; i < cantFilas; i++) {
            objVenta = listadoVentas.get(i);
            int idVenta = objVenta.getIntIdVenta();
            String cedulaCliente = objVenta.getCedulaUser();
            String nombreCliente = objVenta.getStrFecha();
            double totalVenta = objVenta.getDblTotalVenta();
            double valCancelado = objVenta.getDblValCancelado();
            //String fechaVenta = objVenta.getStrFecha();

            modelo.setValueAt(idVenta, i, 0);
            modelo.setValueAt(cedulaCliente, i, 1);
            modelo.setValueAt(nombreCliente, i, 2);
            modelo.setValueAt(totalVenta, i, 3);
            modelo.setValueAt(valCancelado, i, 4);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        calendario = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        txtTotalVentas = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPagoCl = new javax.swing.JTable();
        txtTotalAbonos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPagoPr = new javax.swing.JTable();
        txtTotalPagoPr = new javax.swing.JLabel();
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Cn", 0, 36)); // NOI18N
        jLabel1.setText("Reporte de ventas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Monto total de ventas:");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Fecha:");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 255, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        calendario.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    calendario.setNothingAllowed(false);
    calendario.setFormat(2);
    calendario.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
    calendario.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            calendarioOnCommit(evt);
        }
    });

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-reloj-historial.png"))); // NOI18N

    tblVentas.setModel(modelo);
    tblVentas.getTableHeader().setReorderingAllowed(false);
    tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblVentasMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblVentas);

    btnVer.setText("Ver");
    btnVer.setToolTipText("Presione para ver el detalle de la compra");
    btnVer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnVerActionPerformed(evt);
        }
    });

    txtTotalVentas.setText("jLabel5");

    txtId.setText("jLabel5");

    jLabel5.setText("Buscar:");

    txtBusqueda.setText("Ingrese el nombre o cédula del usuario que desee buscar.");
    txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            txtBusquedaFocusGained(evt);
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            txtBusquedaFocusLost(evt);
        }
    });
    txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtBusquedaKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(txtTotalVentas)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addGap(401, 401, 401))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnVer)
                .addComponent(jLabel5)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTotalVentas)
                .addComponent(txtId))
            .addContainerGap(8, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Ventas", jPanel1);

    tblPagoCl.setModel(modelo2);
    tblPagoCl.getTableHeader().setReorderingAllowed(false);
    tblPagoCl.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoClMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tblPagoCl);

    txtTotalAbonos.setText("jLabel5");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(txtTotalAbonos)
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap(25, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtTotalAbonos)
            .addGap(22, 22, 22))
    );

    jTabbedPane1.addTab("Pago Clientes", jPanel2);

    tblPagoPr.setModel(modelo3);
    tblPagoPr.getTableHeader().setReorderingAllowed(false);
    tblPagoPr.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoPrMouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(tblPagoPr);

    txtTotalPagoPr.setText("Total:");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(txtTotalPagoPr)
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(56, 56, 56)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
            .addComponent(txtTotalPagoPr)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Pago Proveedores", jPanel3);

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
                    .addContainerGap()
                    .addComponent(jSeparator1))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(111, 111, 111)
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(337, 337, 337))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel1))
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(4, 4, 4)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel4)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
            .addContainerGap(15, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calendarioOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_calendarioOnCommit
        tablaVentas();
        tablaPagosCL();
        tablaPagoPR();
        sumar();
    }//GEN-LAST:event_calendarioOnCommit

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        int fila = tblVentas.getSelectedRow();
        if (fila >= 0) {
            id_Venta = tblVentas.getValueAt(fila, 0).toString();
            txtId.setText(id_Venta);
            cedula = (String) tblVentas.getValueAt(fila, 1);
            nombre = (String) tblVentas.getValueAt(fila, 2);
            total = tblVentas.getValueAt(fila, 3).toString();
        }
    }//GEN-LAST:event_tblVentasMouseClicked

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        VistaFacturaDialog vf = new VistaFacturaDialog(this, true);

        if (tblVentas.getSelectedRows().length > 0) {
            VistaFacturaDialog.txtCliente.setText(nombre);
            //VistaFactura.txtCedula.setText(cedula);
            vf.cargarTabla();
            VistaFacturaDialog.txtTotal.setText("$" + total);

            vf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna venta");
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void tblPagoClMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagoClMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagoClMouseClicked

    private void tblPagoPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagoPrMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagoPrMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtBusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaFocusGained
        if (txtBusqueda.getText().equals("Ingrese el nombre o cédula del usuario que desee buscar.")) {
            txtBusqueda.setText("");
        } else {
            //Do nothing
        }
    }//GEN-LAST:event_txtBusquedaFocusGained

    private void txtBusquedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusquedaFocusLost
        if (!txtBusqueda.getText().equals("Ingrese el nombre o cédula del usuario que desee buscar.")) {

        } else {
            txtBusqueda.setText("");
        }
    }//GEN-LAST:event_txtBusquedaFocusLost

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if (txtBusqueda.getText().isEmpty()) {
            tablaVentas();
        } else {
            busquedafiltrada();
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

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
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleVentaVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVer;
    private datechooser.beans.DateChooserCombo calendario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JTable tblPagoCl;
    private javax.swing.JTable tblPagoPr;
    public static javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBusqueda;
    public static javax.swing.JLabel txtId;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtTotalAbonos;
    private javax.swing.JLabel txtTotalPagoPr;
    private javax.swing.JLabel txtTotalVentas;
    // End of variables declaration//GEN-END:variables
}
