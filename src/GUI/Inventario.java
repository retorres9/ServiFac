package GUI;

import Clases.Configuracion;
import Clases.ExistenciasBodega;
import Clases.Producto;
import Clases.Usuario;
import Dat.DATExistenciasBodega;
import Dat.DATMaterial;
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

public final class Inventario extends javax.swing.JFrame {

    int fila;
    String n;
    Producto producto;
    Usuario objU = new Usuario();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DATMaterial material;
    ExistenciasBodega existencias;
    DATExistenciasBodega manejadorExistencias;
    Configuracion config = new Configuracion();

    public Inventario() {
        initComponents();
        iconos();
        material = new DATMaterial();
        manejadorExistencias = new DATExistenciasBodega();
        insertarColumnas();
        encabezadoBodega();
        setAnchoColumnas();
        setAnchoColumnas2();
        CargaCombo();
        this.txtPrecio.setEditable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        txtEmpresa.setText(Constantes.Constantes.NOMBRE_EMPRESA);
        permisos();
        updateTabla();
        cargarTablaBodega();
        btnActualizaPrecio.setText("");
    }

    public void insertarColumnas() {
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio de Compra");
        modelo.addColumn("Precio");
        modelo.addColumn("Precio por mayor");
        modelo.addColumn("Ganancia %");
        modelo.addColumn("Ganancia por mayor %");
        modelo.addColumn("Ubicación");
        modelo.addColumn("Cantidad");
    }

    public void encabezadoBodega() {
        modelo2.addColumn("Nombre producto");
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Precio de Compra");
        modelo2.addColumn("Precio");
        modelo2.addColumn("Precio por mayor");
        modelo2.addColumn("Ganancia %");
        modelo2.addColumn("Ganancia por mayor %");
        modelo2.addColumn("Bodega");
        modelo2.addColumn("Cantidad");
    }

    public void permisos() {

        String rol = config.validacion();

        if (rol.equals("0")) {
            jmiElimProd.setEnabled(false);
            jmiElimCliente.setEnabled(false);
            jmiElimProv.setEnabled(false);
            jmConfig.setEnabled(false);
            btnActualizaPrecio.setVisible(false);
            btnActualizaNombre.setVisible(false);
            btnActualizaPrecioMayor.setVisible(false);
            btnActualizaUbicacion.setVisible(false);
            btnActualizaCantidad.setVisible(false);
        }
        if (rol.equals("1")) {
            jmiElimProd.setEnabled(true);
            jmiElimCliente.setEnabled(true);
            jmiElimProv.setEnabled(true);
            jmConfig.setEnabled(true);
            if (txtPrecio.getText().isEmpty()) {
                btnActualizaPrecio.setVisible(false);
                btnActualizaNombre.setVisible(false);
                btnActualizaPrecioMayor.setVisible(false);
                btnActualizaUbicacion.setVisible(false);
                btnActualizaCantidad.setVisible(false);
            } else {
                btnActualizaPrecio.setVisible(true);
                btnActualizaNombre.setVisible(true);
                btnActualizaPrecioMayor.setVisible(true);
                btnActualizaUbicacion.setVisible(true);
                btnActualizaCantidad.setVisible(true);
            }

        }
    }

    public void iconos() {
        btnActualizaPrecio.setVisible(false);
        btnActualizaNombre.setVisible(false);
        btnActualizaPrecioMayor.setVisible(false);
        btnActualizaUbicacion.setVisible(false);
        btnActualizaCantidad.setVisible(false);
        btnActualizaCantidad.setToolTipText("Haga clic para agregar productos");
        btnActualizaPrecio.setToolTipText("Haga clic para actualizar el precio");
        btnActualizaNombre.setToolTipText("Haga clic para actualizar el precio por mayor");
        btnActualizaPrecioMayor.setToolTipText("Haga clic para actualizar el nombre del producto");
        btnActualizaUbicacion.setToolTipText("Haga clic para actualizar la ubicación");
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
                Double precioMayor = producto.getFltPrecioMayor();
                Double ganancia = producto.getGanancia();
                Double gananciaMayor = producto.getGananciaMayor();
                String ubi = producto.getStrUbicacion();
                Integer cant = producto.getIntCantidad();

                modelo.setValueAt(nombreProd, i, 0);
                modelo.setValueAt(cod, i, 1);
                modelo.setValueAt(preciCompra, i, 2);
                modelo.setValueAt(precio, i, 3);
                modelo.setValueAt(precioMayor, i, 4);
                modelo.setValueAt(ganancia, i, 5);
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
            Double precioMayor = producto.getFltPrecioMayor();
            Double ganancia = producto.getGanancia();
            Double gananciaMayor = producto.getGananciaMayor();
            String ubi = producto.getStrUbicacion();
            Integer cant = producto.getIntCantidad();

            modelo2.setValueAt(nombreProd, i, 0);
            modelo2.setValueAt(cod, i, 1);
            modelo2.setValueAt(preciCompra, i, 2);
            modelo2.setValueAt(precio, i, 3);
            modelo2.setValueAt(precioMayor, i, 4);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnActualizaPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        btnActualizaPrecioMayor = new javax.swing.JLabel();
        btnActualizaNombre = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JLabel();
        btnActualizaCantidad = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JLabel();
        btnActualizaUbicacion = new javax.swing.JLabel();
        txtPrecioM = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBodega = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
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

        jLabel6.setText("Buscar por:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles del producto"));
        jPanel1.setMaximumSize(new java.awt.Dimension(743, 145));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setText("$");

        btnActualizaPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar Mediano.png"))); // NOI18N
        btnActualizaPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaPrecioMouseClicked(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 153, 0));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Precio al por mayor:");

        txtNombre.setText("----------");
        txtNombre.setAutoscrolls(true);
        txtNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombre.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtNombre.setMaximumSize(new java.awt.Dimension(150, 16));

        btnActualizaPrecioMayor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaPrecioMayor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaPrecioMayor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaPrecioMayorMouseClicked(evt);
            }
        });

        btnActualizaNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaNombreMouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nombre del Producto:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Cantidad:");

        txtCantidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtCantidad.setText("----------");

        btnActualizaCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/agregar.png"))); // NOI18N
        btnActualizaCantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaCantidadMouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Ubicacion:");

        txtUbicacion.setText("----------");
        txtUbicacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnActualizaUbicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-actualizar.png"))); // NOI18N
        btnActualizaUbicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizaUbicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizaUbicacionMouseClicked(evt);
            }
        });

        txtPrecioM.setText("----------");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Precio de Compra:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnActualizaPrecioMayor))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizaNombre))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizaPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizaUbicacion)))
                .addGap(349, 349, 349))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCantidad)
                                .addComponent(jLabel7))
                            .addComponent(btnActualizaCantidad))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizaUbicacion)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtUbicacion)
                                .addComponent(jLabel8)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnActualizaNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecioM)
                        .addComponent(jLabel4))
                    .addComponent(btnActualizaPrecioMayor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProd.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblProd.setModel(modelo);
        tblProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProd);
        if (tblProd.getColumnModel().getColumnCount() > 0) {
            tblProd.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        jTabbedPane1.addTab("Productos en bodega", jPanel3);

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
                .addComponent(txtEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(410, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(679, 679, 679))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtEmpresa))
                    .addComponent(jLabel9))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdMouseClicked
        fila = tblProd.getSelectedRow();
        String prod = (String) tblProd.getModel().getValueAt(fila, 0);//Seleccionamos el nombre del producto
        txtNombre.setText(prod);
        String precio = tblProd.getModel().getValueAt(fila, 2).toString();//Seleccionamos el precio del producto
        txtPrecio.setText(precio);
        String precioMayor = tblProd.getModel().getValueAt(fila, 3).toString();//Seleccionamos el precio al por mayor del producto
        txtPrecioM.setText(precioMayor);
        String ubicacion = tblProd.getModel().getValueAt(fila, 4).toString();//Seleccionamos la ubicacion del producto
        txtUbicacion.setText(ubicacion);
        String cant = (String) tblProd.getModel().getValueAt(fila, 5).toString();//Seleccionamos la cantidad del producto
        txtCantidad.setText(cant);

        String rol = config.validacion();
        if (rol.equals("0")) {
            jmiElimProd.setEnabled(false);
            jmiElimCliente.setEnabled(false);
            jmiElimProv.setEnabled(false);
            jmConfig.setEnabled(false);
            btnActualizaPrecio.setVisible(false);
            btnActualizaNombre.setVisible(false);
            btnActualizaPrecioMayor.setVisible(false);
            btnActualizaUbicacion.setVisible(false);
            btnActualizaCantidad.setVisible(false);
        }
        if (rol.equals("1")) {
            jmiElimProd.setEnabled(true);
            jmiElimCliente.setEnabled(true);
            jmiElimProv.setEnabled(true);
            jmConfig.setEnabled(true);
            btnActualizaPrecio.setVisible(true);
            btnActualizaNombre.setVisible(true);
            btnActualizaPrecioMayor.setVisible(true);
            btnActualizaUbicacion.setVisible(true);
            btnActualizaCantidad.setVisible(true);
        }

    }//GEN-LAST:event_tblProdMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void CargaCombo() {
        cmbBusq.addItem("Nombre producto");
        cmbBusq.addItem("Codigo");
    }
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
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
                    Double precio = producto.getFltPrecio();
                    Double precioMayor = producto.getFltPrecioMayor();
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();
                    modelo.setValueAt(nombreProd, i, 0);
                    modelo.setValueAt(cod, i, 1);
                    modelo.setValueAt(precio, i, 2);
                    modelo.setValueAt(precioMayor, i, 3);
                    modelo.setValueAt(ubi, i, 4);
                    modelo.setValueAt(cant, i, 5);
                }
            } else {
                ArrayList<Producto> listadoProd = material.ConsultarPorCodigo(dato);
                int cantLista = listadoProd.size();
                modelo.setNumRows(cantLista);
                for (int i = 0; i < cantLista; i++) {
                    producto = listadoProd.get(i);
                    String nombreProd = producto.getStrNombreProd();
                    String cod = producto.getStrCod();
                    Double precio = producto.getFltPrecio();
                    Double precioMayor = producto.getFltPrecioMayor();
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();
                    modelo.setValueAt(nombreProd, i, 0);
                    modelo.setValueAt(cod, i, 1);
                    modelo.setValueAt(precio, i, 2);
                    modelo.setValueAt(precioMayor, i, 3);
                    modelo.setValueAt(ubi, i, 4);
                    modelo.setValueAt(cant, i, 5);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                manejadorExistencias.actualizarCant(existencias);
                updateTabla();
                cargarTablaBodega();
                JOptionPane.showMessageDialog(null, "Se ha movido correcamente el producto al almacén");
                txtBodega.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad a mover supera la cantidad existente");
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!(txtBodega.getText().isEmpty()) && (tblBodega.getSelectedRowCount() == 1)) {
            int n = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea mover " + txtBodega.getText() + "\nunidad/es al almacén?","Aviso",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                moverBodega();
            } else {
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto e ingresar una\n cantidad para mover de bodega");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
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

    private void btnActualizaUbicacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaUbicacionMouseClicked
        String rol = config.validacion();
        if (rol.equals("0")) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevos proveedores", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ActualzarUbicacionDialog dialogUbic = new ActualzarUbicacionDialog(this, true);
            ActualzarUbicacionDialog.lblProd.setText(txtNombre.getText());
            dialogUbic.setVisible(true);

            if (dialogUbic != null) {
                if (!dialogUbic.getInfo().equals("")) {
                    updateTabla();
                    txtUbicacion.setText(dialogUbic.getUbic());
                }
            }

        }
    }//GEN-LAST:event_btnActualizaUbicacionMouseClicked

    private void btnActualizaCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaCantidadMouseClicked
        try {
            String nume = JOptionPane.showInputDialog(
                    "Ingrese la cantidad que desea agregar a:\n" + txtNombre.getText());
            int cant1 = Integer.parseInt(nume);
            int cant2 = Integer.parseInt(txtCantidad.getText());
            int total = cant1 + cant2;
            String cod = (String) tblProd.getModel().getValueAt(fila, 1);
            producto = new Producto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Double.parseDouble(txtPrecioM.getText()), total, cod);
            material.UpdateProducto(producto);
            String nuevaCantTxt = String.valueOf(total);
            txtCantidad.setText(nuevaCantTxt);
            updateTabla();

        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se agregó nada a:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaCantidadMouseClicked

    private void btnActualizaNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaNombreMouseClicked
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de:\n" + txtNombre.getText());
            String cod = (String) tblProd.getModel().getValueAt(fila, 1);
            System.out.println(cod);
            producto = new Producto(nombre, Double.parseDouble(txtPrecio.getText()), Double.parseDouble(txtPrecioM.getText()), Integer.parseInt(txtCantidad.getText()), cod);
            material.UpdateProducto(producto);
            System.out.println(cod);
            updateTabla();
        } catch (NullPointerException | NumberFormatException e) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnActualizaNombreMouseClicked

    private void btnActualizaPrecioMayorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaPrecioMayorMouseClicked
        try {
            n = JOptionPane.showInputDialog(null, "Ingrese el nuevo precio por mayor de:\n" + txtNombre.getText());
            double newPrecio = Double.parseDouble(n);
            int cant = Integer.parseInt(txtCantidad.getText());
            String cod = (String) tblProd.getModel().getValueAt(fila, 1);
            producto = new Producto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), newPrecio, cant, cod);
            material.UpdateProducto(producto);
            String nuevoPrecio = String.valueOf(n);
            txtPrecioM.setText(nuevoPrecio);
            updateTabla();
        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se ha cambiado el precio por mayor de:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaPrecioMayorMouseClicked

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void btnActualizaPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaPrecioMouseClicked
        try {
            n = JOptionPane.showInputDialog(null, "Ingrese el nuevo precio de:\n" + txtNombre.getText());
            double newPrecio = Double.parseDouble(n);
            int cant = Integer.parseInt(txtCantidad.getText());
            String cod = (String) tblProd.getModel().getValueAt(fila, 1);
            producto = new Producto(txtNombre.getText(), newPrecio, Double.parseDouble(txtPrecioM.getText()), cant, cod);
            material.UpdateProducto(producto);
            String nuevoPrecio = String.valueOf(n);
            txtPrecio.setText(nuevoPrecio);
            updateTabla();

        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se ha cambiado el precio de:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaPrecioMouseClicked

    private void txtBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyTyped
        char c = evt.getKeyChar();
        if (c > '9' || c < '0') {
            evt.consume();
        }
    }//GEN-LAST:event_txtBodegaKeyTyped
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
    private javax.swing.JLabel btnActualizaCantidad;
    private javax.swing.JLabel btnActualizaNombre;
    private javax.swing.JLabel btnActualizaPrecio;
    private javax.swing.JLabel btnActualizaPrecioMayor;
    private javax.swing.JLabel btnActualizaUbicacion;
    private javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JTable tblBodega;
    public static final javax.swing.JTable tblProd = new javax.swing.JTable();
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtCantidad;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel txtPrecioM;
    private javax.swing.JLabel txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
