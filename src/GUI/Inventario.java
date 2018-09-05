package GUI;

import Clases.Configuracion;
import Clases.ExistenciasBodega;
import Clases.Producto;
import Clases.Renderer;
import Clases.Usuario;
import Dat.DATConfiguracion;
import Dat.DATExistenciasBodega;
import Dat.DATMaterial;
import Dat.DATUsuario;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class Inventario extends javax.swing.JFrame {

    int fila;
    String n;
    int cantProd;
    String codigoProd;
    Producto producto;
    Usuario objU = new Usuario();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DATMaterial material;
    ExistenciasBodega existencias;
    DATExistenciasBodega manejadorExistencias;
    DATUsuario manejadorUsuario;
    Configuracion objConfig = new Configuracion();
    public static boolean bandera;
    DATConfiguracion manejadorConf;
    String host;
    Utilidades util = new Utilidades();
    Renderer render = new Renderer();
    int cantidad;
    String precioProd;
    String ubicacion;
    String categoria;
    String proveedor;
    String iva;
    String precioMayor;
    Principal objP = new Principal();

    public Inventario() {
        material = new DATMaterial();
        manejadorUsuario = new DATUsuario();
        manejadorExistencias = new DATExistenciasBodega();
        manejadorConf = new DATConfiguracion();
        //mb = new MoverBodega(this, true);
        initComponents();
        bandera = true;
        host = util.getPcName();
        insertarColumnas();
        encabezadoBodega();
        setAnchoColumnas();
        setAnchoColumnas2();
        CargaCombo();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        permisos();
        updateTabla();
        cargarTablaBodega();
        totalInventario();
        tblBodega.setDefaultRenderer(Object.class, render);
    }

    public void limpiarTabla() {
        int numFilas = modelo.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public void cargarConfig() {
        ArrayList<Configuracion> config = manejadorConf.cargaConfig();
        int cantConf = config.size();
        for (int i = 0; i < cantConf; i++) {
            objConfig = config.get(i);
            String empr = objConfig.getEmpresa();
            txtEmpresa.setText(empr);
        }
    }

    public void insertarColumnas() {
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio de Compra");
        modelo.addColumn("Precio");
        modelo.addColumn("Precio por mayor");
        modelo.addColumn("IVA %");
        modelo.addColumn("Ganancia por mayor %");
        modelo.addColumn("Ubicación");
        modelo.addColumn("Cantidad");
    }

    public void totalInventario() {
        int cant1 = modelo.getRowCount();
        int cant2 = modelo2.getRowCount();
        double suma1 = 0.00;
        double suma2 = 0.00;
        for (int i = 0; i < cant1; i++) {
            int cant = (int) modelo.getValueAt(i, 8);
            double prec = (Double) modelo.getValueAt(i, 2);
            suma1 = suma1 + (cant * prec);
        }
        for (int i = 0; i < cant2; i++) {
            int cant = (int) modelo2.getValueAt(i, 8);
            double prec = (Double) modelo2.getValueAt(i, 2);
            suma2 = suma2 + (cant * prec);
        }
        double global = suma1 + suma2;
        DecimalFormatSymbols separador = new DecimalFormatSymbols();
        separador.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", separador);
        String strGlobal = df.format(global);
        lblTotal.setText("Total del inventario: $" + strGlobal);
    }

    public void encabezadoBodega() {
        modelo2.addColumn("Nombre producto");
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Precio de Compra");
        modelo2.addColumn("Precio");
        modelo2.addColumn("Precio por mayor");
        modelo2.addColumn("Iva");
        modelo2.addColumn("Ganancia por mayor %");
        modelo2.addColumn("Bodega");
        modelo2.addColumn("Cantidad");
    }

    public void permisos() {

        ArrayList<Usuario> credencial = manejadorUsuario.obtenerUserLog(host);
        int cant = credencial.size();
        int rol = 0;
        for (int i = 0; i < cant; i++) {
            objU = credencial.get(i);
            rol = objU.getRol();
            if (rol == 0) {
                jmiElimProd.setEnabled(false);
                jmiElimCliente.setEnabled(false);
                jmiElimProv.setEnabled(false);
                jmConfig.setEnabled(false);
                btnEditar.setEnabled(false);
                btnVer.setEnabled(false);
                btnMover.setEnabled(false);
            }
            if (rol == 1) {
                jmiElimProd.setEnabled(true);
                jmiElimCliente.setEnabled(true);
                jmiElimProv.setEnabled(true);
                jmConfig.setEnabled(true);
                btnEditar.setEnabled(true);
                btnVer.setEnabled(true);
                btnMover.setEnabled(true);
            }
        }
    }

    public static void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblProd.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblProd.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblProd.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (65 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (50 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (35 * ancho) / 100;
                    break;
                case 8:
                    anchoColumna = (25 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public static void setAnchoColumnas2() {
        JViewport scroll = (JViewport) tblProd.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblProd.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblProd.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (65 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (50 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (35 * ancho) / 100;
                    break;
                case 8:
                    anchoColumna = (25 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void updateTabla() {
        try {
            setAnchoColumnas();
            ArrayList<Producto> listadoProd = material.Consultar();
            int cantLista = listadoProd.size();
            modelo.setNumRows(cantLista);
            for (int i = 0; i < cantLista; i++) {
                producto = listadoProd.get(i);
                String nombreProd = producto.getStrNombreProd();
                String cod = producto.getStrCod();
                Double preciCompra = producto.getPrecioCompra();
                Double precio = producto.getFltPrecio();
                Double precioPorMayor = producto.getFltPrecioMayor();
                String iva = producto.getIva();
                Double gananciaMayor = producto.getGananciaMayor();
                String ubi = producto.getStrUbicacion();
                Integer cant = producto.getIntCantidad();

                modelo.setValueAt(nombreProd, i, 0);
                modelo.setValueAt(cod, i, 1);
                modelo.setValueAt(preciCompra, i, 2);
                modelo.setValueAt(precio, i, 3);
                modelo.setValueAt(precioPorMayor, i, 4);
                modelo.setValueAt(iva, i, 5);
                modelo.setValueAt(gananciaMayor, i, 6);
                modelo.setValueAt(ubi, i, 7);
                modelo.setValueAt(cant, i, 8);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarTablaBodega() {
        setAnchoColumnas2();
        ArrayList<Producto> listadoBodega = material.existenciasBodega();
        int cantLista = listadoBodega.size();
        modelo2.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            producto = listadoBodega.get(i);
            String nombreProd = producto.getStrNombreProd();
            String cod = producto.getStrCod();
            Double preciCompra = producto.getPrecioCompra();
            Double precio = producto.getFltPrecio();
            Double precioPorMayor = producto.getFltPrecioMayor();
            Double ganancia = producto.getGanancia();
            Double gananciaMayor = producto.getGananciaMayor();
            String ubi = producto.getStrUbicacion();
            Integer cant = producto.getIntCantidad();

            modelo2.setValueAt(nombreProd, i, 0);
            modelo2.setValueAt(cod, i, 1);
            modelo2.setValueAt(preciCompra, i, 2);
            modelo2.setValueAt(precio, i, 3);
            modelo2.setValueAt(precioPorMayor, i, 4);
            modelo2.setValueAt(ganancia, i, 5);
            modelo2.setValueAt(gananciaMayor, i, 6);
            modelo2.setValueAt(ubi, i, 7);
            modelo2.setValueAt(cant, i, 8);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmpresa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        cmbBusq = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnMover = new javax.swing.JButton();
        txtNombreProd = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBodega = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
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
        setMinimumSize(new java.awt.Dimension(1150, 761));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtEmpresa.setFont(new java.awt.Font("Roboto Cn", 1, 36)); // NOI18N
        txtEmpresa.setText("Nombre Empresa");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/lupa.png"))); // NOI18N

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        cmbBusq.setFocusable(false);

        jLabel6.setText("Buscar por:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tblProd = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col)
            {
                //If you didn't want the first column to be editable
                if(col == 0)
                return true;
                if(col == 7)
                return true;
                else
                return false;
            }
        };
        tblProd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblProd.setModel(modelo);
        tblProd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProd.getTableHeader().setReorderingAllowed(false);
        tblProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProd);
        if (tblProd.getColumnModel().getColumnCount() > 0) {
            tblProd.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

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

        btnMover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/mozo-de-carga.png"))); // NOI18N
        btnMover.setText("Mover");
        btnMover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverActionPerformed(evt);
            }
        });

        txtNombreProd.setText("----------");
        txtNombreProd.setAutoscrolls(true);
        txtNombreProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreProd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtNombreProd.setMaximumSize(new java.awt.Dimension(150, 16));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nombre del Producto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMover))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnVer)
                    .addComponent(btnMover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos en almacén", jPanel2);

        tblBodega.setModel(modelo2);
        jScrollPane2.setViewportView(tblBodega);

        jLabel3.setText("Cantidad a mover:");

        txtBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBodegaKeyTyped(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        jTabbedPane1.addTab("Productos en bodega", jPanel3);

        lblTotal.setText("Total del inventario:");

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
                .addGap(10, 10, 10)
                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtEmpresa)))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lblTotal))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdMouseClicked
        fila = tblProd.rowAtPoint(evt.getPoint());
        try {
            String prod = (String) tblProd.getModel().getValueAt(fila, 0);//Seleccionamos el nombre del producto
            txtNombreProd.setText(prod);
            codigoProd = (String) tblProd.getModel().getValueAt(fila, 1);//Seleccionamos el codigo del producto
            precioProd = tblProd.getModel().getValueAt(fila, 3).toString();//Seleccionamos el precio del producto

            precioMayor = tblProd.getModel().getValueAt(fila, 4).toString();//Seleccionamos el precio al por mayor del producto
            iva = tblProd.getModel().getValueAt(fila, 5).toString();//Seleccionamos el iva del producto
            ubicacion = tblProd.getModel().getValueAt(fila, 7).toString();//Seleccionamos la ubicacion del producto

            String cant = tblProd.getModel().getValueAt(fila, 8).toString();//Seleccionamos la cantidad del producto
            cantidad = Integer.parseInt(cant);

            cantProd = Integer.parseInt(cant);
            permisos();

        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }//GEN-LAST:event_tblProdMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void CargaCombo() {
        cmbBusq.addItem("Nombre producto");
        cmbBusq.addItem("Codigo");
    }

    public void busqueda() {
        String dato = txtBuscar.getText();
        try {
            setAnchoColumnas();
            if (cmbBusq.getSelectedItem().equals("Nombre producto")) {
                ArrayList<Producto> listadoProd = material.ConsultarPorNombre(dato);
                int cantLista = listadoProd.size();
                modelo.setNumRows(cantLista);
                for (int i = 0; i < cantLista; i++) {
                    producto = listadoProd.get(i);

                    String nombreProd = producto.getStrNombreProd();
                    String cod = producto.getStrCod();
                    Double preciCompra = producto.getPrecioCompra();
                    Double precio = producto.getFltPrecio();
                    Double precioPorMayor = producto.getFltPrecioMayor();
                    Double ganancia = producto.getGanancia();
                    Double gananciaMayor = producto.getGananciaMayor();
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();

                    modelo.setValueAt(nombreProd, i, 0);
                    modelo.setValueAt(cod, i, 1);
                    modelo.setValueAt(preciCompra, i, 2);
                    modelo.setValueAt(precio, i, 3);
                    modelo.setValueAt(precioPorMayor, i, 4);
                    modelo.setValueAt(ganancia, i, 5);
                    modelo.setValueAt(gananciaMayor, i, 6);
                    modelo.setValueAt(ubi, i, 7);
                    modelo.setValueAt(cant, i, 8);
                }
            } else {
                ArrayList<Producto> listadoProd = material.ConsultarPorCodigo(dato);
                int cantLista = listadoProd.size();
                modelo.setNumRows(cantLista);
                for (int i = 0; i < cantLista; i++) {
                    producto = listadoProd.get(i);

                    String nombreProd = producto.getStrNombreProd();
                    String cod = producto.getStrCod();
                    Double preciCompra = producto.getPrecioCompra();
                    Double precio = producto.getFltPrecio();
                    Double precioPorMayor = producto.getFltPrecioMayor();
                    Double ganancia = producto.getGanancia();
                    Double gananciaMayor = producto.getGananciaMayor();
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();

                    modelo.setValueAt(nombreProd, i, 0);
                    modelo.setValueAt(cod, i, 1);
                    modelo.setValueAt(preciCompra, i, 2);
                    modelo.setValueAt(precio, i, 3);
                    modelo.setValueAt(precioPorMayor, i, 4);
                    modelo.setValueAt(ganancia, i, 5);
                    modelo.setValueAt(gananciaMayor, i, 6);
                    modelo.setValueAt(ubi, i, 7);
                    modelo.setValueAt(cant, i, 8);
                }
            }
            txtNombreProd.setText("----------");
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        busqueda();
    }//GEN-LAST:event_txtBuscarKeyReleased

    public void moverBodega() {
        if (tblBodega.getSelectedRowCount() == 1) {
            String cod = modelo2.getValueAt(tblBodega.getSelectedRow(), 0).toString();
            int cantExist = (int) modelo2.getValueAt(tblBodega.getSelectedRow(), 8);
            int cantMovida = Integer.parseInt(txtBodega.getText());
            if (cantMovida <= cantExist) {
                cantExist = cantExist - cantMovida;
                producto = new Producto(cantMovida, cod);
                material.moverBodega(producto);
                existencias = new ExistenciasBodega(cod, cantExist);
                //manejadorExistencias.actualizarCant(existencias);
                updateTabla();
                cargarTablaBodega();
                JOptionPane.showMessageDialog(null, "Se ha movido correcamente el producto al almacén");
                txtBodega.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad a mover supera la cantidad existente");
            }
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IngresoProd objIp = new IngresoProd();
        objIp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Inventario objBp = new Inventario();
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!(txtBodega.getText().isEmpty()) && (tblBodega.getSelectedRowCount() == 1)) {
            int opc = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea mover " + txtBodega.getText() + "\nunidad/es al almacén?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                moverBodega();
            } else {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto e ingresar una\n cantidad para mover de bodega");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyTyped
        char c = evt.getKeyChar();
        if (c > '9' || c < '0') {
            evt.consume();
        }
    }//GEN-LAST:event_txtBodegaKeyTyped

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (txtNombreProd.getText().equals("----------")) {

        } else {
            ActualizacionDialog actDialg = new ActualizacionDialog(new javax.swing.JDialog(), true);
            actDialg.lblIVA.setText(iva);
            actDialg.lblCod.setText(codigoProd);
            actDialg.txtFila.setText(String.valueOf(fila));
            actDialg.setVisible(true);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverActionPerformed
        if (txtNombreProd.getText().equals("----------")) {

        } else {
            MoverBodega mb = new MoverBodega(this, true);
            mb.lblProd.setText(txtNombreProd.getText());
            mb.txtAyudaIndice.setText(String.valueOf(fila));
            mb.txtAyudaCant.setText(String.valueOf(cantidad));
            mb.txtAyudaCod.setText(codigoProd);
            mb.txtAyudaCantIni.setText(tblProd.getValueAt(fila, 8).toString());
            mb.setVisible(true);
        }
    }//GEN-LAST:event_btnMoverActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        JOptionPane.showMessageDialog(null, "Módulo no disponible");
    }//GEN-LAST:event_btnVerActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        System.out.println(bandera);
        int index = jTabbedPane1.getSelectedIndex();
        if ((bandera == false) && (index == 1)) {
            cargarTablaBodega();
            bandera = true;
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked
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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnMover;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel lblTotal;
    public static javax.swing.JTable tblBodega;
    public static javax.swing.JTable tblProd;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtNombreProd;
    // End of variables declaration//GEN-END:variables
}
