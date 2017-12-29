package GUI;

import BL.BLMaterial;
import BL.BLProveedor;
import Clases.Categoria;
import Clases.Configuracion;
import Clases.Producto;
import Clases.Proveedor;
import Clases.Ubicacion;
import Dat.DATCategoria;
import Dat.DATMaterial;
import Dat.DATProveedor;
import Dat.DATUbicacion;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Interleaved25;

public final class IngresoProd extends javax.swing.JFrame {

    Principal objP = new Principal();
    Producto objProd = new Producto();
    int cant;
    File imgArticulo;
    DATCategoria objCat;
    DATProveedor objProveedor;
    DATUbicacion objUbic;
    Categoria categoria;
    Ubicacion ubicacion;

    //Modelos de combobox    
    DefaultComboBoxModel<Categoria> modeloCategorias;
    DefaultComboBoxModel<Proveedor> modeloProv;
    DefaultComboBoxModel<Ubicacion> modeloUbic;

    BLProveedor objProv = new BLProveedor();
    JBarcodeBean barcode = new JBarcodeBean();
    public static BufferedImage imagen = null;
    public String rutadefi;
    DATMaterial conMat;

    public IngresoProd() {
        modeloCategorias = new DefaultComboBoxModel<Categoria>();
        modeloProv = new DefaultComboBoxModel<Proveedor>();
        modeloUbic = new DefaultComboBoxModel<Ubicacion>();

        objCat = new DATCategoria();
        objProveedor = new DATProveedor();
        objUbic = new DATUbicacion();
        conMat = new DATMaterial();

        cargarModeloCat();
        cargarModeloProv();
        cargarModeloUbic();
        initComponents();
        permisos();

        btnGenerador.setEnabled(false);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.nombrePrograma);
    }

    private void cargarModeloCat() {
        try {
            ArrayList<Categoria> listaCategorias;
            listaCategorias = objCat.obtenerCategoria();
            for (Categoria cat : listaCategorias) {
                modeloCategorias.addElement(cat);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarModeloProv() {
        ArrayList<Proveedor> listaProveedores;
        listaProveedores = objProveedor.obtenerEmpresa();
        for (Proveedor prov : listaProveedores) {
            modeloProv.addElement(prov);
        }
    }

    private void cargarModeloUbic() {
        ArrayList<Ubicacion> listaUbic;
        listaUbic = objUbic.obtenerUbicacion();
        for (Ubicacion ubic : listaUbic) {
            modeloUbic.addElement(ubic);
        }
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

    public void guardar() {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat decimal = new DecimalFormat("0.00", simbolo);
        try {
            String strCod = txtCod.getText();
            String nombre = txtNombreProd.getText();
            double precio1 = Double.parseDouble(txtPrecio.getText());
            double precio2 = Double.parseDouble(txtPrecioMayor.getText());
            Ubicacion ubica = (Ubicacion) cmbUbicacion.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            int cantidadMin = Integer.parseInt(txtCantMin.getText());
            Proveedor empresa = (Proveedor) cmbProveedor.getSelectedItem();
            Categoria cat = (Categoria) cmbCategoria.getSelectedItem();
            String strPrecio1 = decimal.format(precio1);
            String strPrecio2 = decimal.format(precio2);
            double dblPrecio1 = Double.parseDouble(strPrecio1);
            double dblPrecio2 = Double.parseDouble(strPrecio2);

            if (!cbxGenerador.isSelected()) {
                System.out.println("=D");
                conMat.IngresarProducto(nombre, strCod, dblPrecio1, dblPrecio2, ubica.getStrUbicacion(), cantidad, cantidadMin, empresa.getStrEmpresa(), null);
                //objProd = new Producto(nombre, strCod, dblPrecio1, dblPrecio2, ubica.getStrUbicacion(), cantidad, cantidadMin, empresa.getStrEmpresa(), null, null);
            } else {
//                int anchoImagen = lblImagen.getWidth();
//                int altoImagen = lblImagen.getHeight();
//
//                imgArticulo = (File) lblImagen.getIcon();
////                ImageIcon icono = new ImageIcon(imgArticulo);
////                Image imagen = icono.getImage();
////                Image imagenescalada = imagen.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH);
//
////                ImageIcon iconoRedimensionado = new ImageIcon(imagenescalada);
////                lblImagProd.setIcon(iconoRedimensionado);
////                FileInputStream fis = null;
//                File fileCodFoto;
//                fileCodFoto = imgArticulo;
////                fis = new FileInputStream(imgArticulo);
//                System.out.println("bander ok");
//                conMat.IngresarProducto(nombre, strCod, dblPrecio2, dblPrecio2, strPrecio2, cantidad, cantidadMin, strPrecio2, imgArticulo);
//objProd = new Producto(nombre, strCod, dblPrecio1, dblPrecio2, ubica.getStrUbicacion(), cantidad, cantidadMin, empresa.getStrEmpresa(), fileCodFoto, null);
            }
            //IngresarProductoBl(objProd);
            JOptionPane.showMessageDialog(null, "Producto creado satisfactoriamente");
            if (cbxAyuda.isSelected()) {
                txtCod.setText("");
                txtCantidad.setText("");
                txtPrecio.setText("");
                txtPrecioMayor.setText("");
            } else {
                txtCantidad.setText("");
                txtCod.setText("");
                txtNombreProd.setText("");
                txtPrecio.setText("");
                txtPrecioMayor.setText("");
                txtCantMin.setText("");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        txtNombreProd = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtPrecioMayor = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCantMin = new javax.swing.JTextField();
        cmbUbicacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        cbxAyuda = new javax.swing.JCheckBox();
        pnlGenerador = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        btnGenerador = new javax.swing.JButton();
        cbxGenerador = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        lblImagProd = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnProveedor = new javax.swing.JButton();
        btnUbicacion = new javax.swing.JButton();
        btnCategoria = new javax.swing.JButton();
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

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(531, 405));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        txtPrecioMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioMayorKeyTyped(evt);
            }
        });

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel1.setText("Nombre de Producto:");

        jLabel3.setText("Codigo:");

        jLabel4.setText("Precio:");

        jLabel5.setText("Precio al por mayor:");

        jLabel6.setText("Ubicacion:");

        jLabel7.setText("Cantidad:");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto Cn", 1, 24)); // NOI18N
        jLabel8.setText("Ingreso de Productos");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/etiqueta-de-nuevo-producto.png"))); // NOI18N

        jLabel11.setText("Cantidad Minima:");

        txtCantMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantMinKeyTyped(evt);
            }
        });

        cmbUbicacion.setMaximumRowCount(5);
        cmbUbicacion.setModel(modeloUbic);
        cmbUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUbicacionActionPerformed(evt);
            }
        });

        jLabel2.setText("Proveedor:");

        cmbProveedor.setModel(modeloProv);
        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        cbxAyuda.setText("Ayuda");

        pnlGenerador.setBorder(javax.swing.BorderFactory.createTitledBorder("Generador de Codigo de Barras"));

        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnGenerador.setText("Generar Codigo");
        btnGenerador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGeneradorLayout = new javax.swing.GroupLayout(pnlGenerador);
        pnlGenerador.setLayout(pnlGeneradorLayout);
        pnlGeneradorLayout.setHorizontalGroup(
            pnlGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeneradorLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(btnGenerador)
                .addContainerGap())
        );
        pnlGeneradorLayout.setVerticalGroup(
            pnlGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeneradorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlGeneradorLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnGenerador)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        lblImagen.getAccessibleContext().setAccessibleName("lblImagen");

        cbxGenerador.setText("Generador");
        cbxGenerador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneradorActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Haga clic para agregar imagen"));

        lblImagProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagProdMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImagProd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImagProd, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel12.setText("Categoria:");

        cmbCategoria.setModel(modeloCategorias);

        btnProveedor.setText("Agregar Proveedor");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnUbicacion.setText("Agregar Ubicación");
        btnUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbicacionActionPerformed(evt);
            }
        });

        btnCategoria.setText("Agregar Categoria");
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 429, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cbxAyuda)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel12)))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioMayor)
                                    .addComponent(txtCantidad)
                                    .addComponent(txtCantMin)
                                    .addComponent(cmbProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbUbicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCod, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(txtPrecio)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUbicacion)
                            .addComponent(btnProveedor)
                            .addComponent(btnCategoria)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnAtras)
                        .addGap(251, 251, 251)
                        .addComponent(btnGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxGenerador)
                        .addGap(18, 18, 18)
                        .addComponent(pnlGenerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel8))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxAyuda)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPrecioMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btnProveedor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(btnUbicacion))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCategoria))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlGenerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAtras)
                            .addComponent(btnGuardar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxGenerador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtCantidad.getText().isEmpty() || txtCod.getText().isEmpty()
                || txtNombreProd.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || txtPrecioMayor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios que no se pueden guardar");
        } else {
            String prueba = cmbProveedor.getSelectedItem().toString();
            System.out.println(prueba);
            if (cmbProveedor.getSelectedItem().toString().equals("Sin Proveedor")) {
                System.out.println("bandera cmb");
                int n = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea guardar el producto sin asignarle un proveedor?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {

                    if (cbxGenerador.isSelected()) {
                        if (new Clases.Guardar().guardarImagen(txtNombreProd.getText())) {
                            imagen = null;
                            this.lblImagen.setIcon(null);
                            //JOptionPane.showMessageDialog(this, "Código guardado con éxito!!!", "Información", JOptionPane.INFORMATION_MESSAGE);
                            //guardar(rutadefi);
                        }
                    } else {
                        guardar();
                    }

                }
            } else {
                System.out.println("bandera cmb mal");
                if (new Clases.Guardar().guardarImagen(txtNombreProd.getText())) {
                    imagen = null;
                    this.lblImagen.setIcon(null);
                    //JOptionPane.showMessageDialog(this, "Código guardado con éxito!!!", "Información", JOptionPane.INFORMATION_MESSAGE);
                    //guardar(rutadefi);
                }
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

        if (!txtCantidad.getText().isEmpty() || !txtCod.getText().isEmpty()
                || !txtNombreProd.getText().isEmpty() || !txtPrecio.getText().isEmpty()
                || !txtPrecioMayor.getText().isEmpty()) {
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Si retrocede se perderan los cambios no guardados?",
                    "Aviso!",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                    objP.setVisible(true);
                    this.dispose();
//                }
            }
        } else {

            objP.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioMayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioMayorKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioMayorKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantMinKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantMinKeyTyped

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

    private void btnGeneradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneradorActionPerformed
        if (this.txtCod.getText().length() < 1) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            generaCodigo(this.txtCod.getText());
        }
    }//GEN-LAST:event_btnGeneradorActionPerformed

    private void cmbUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUbicacionActionPerformed

    private void cbxGeneradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneradorActionPerformed
        if (cbxGenerador.isSelected()) {
            btnGenerador.setEnabled(true);
        } else {
            btnGenerador.setEnabled(false);
        }
    }//GEN-LAST:event_cbxGeneradorActionPerformed

    private void lblImagProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagProdMouseClicked
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Archivos de imagen jpg, gif o png", "jpg", "gif", "png");
        chooser.setFileFilter(extension);

        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            int anchoImagen = lblImagProd.getWidth();
            int altoImagen = lblImagProd.getHeight();

            imgArticulo = chooser.getSelectedFile();
            ImageIcon icono = new ImageIcon(imgArticulo.getAbsolutePath());
            Image imagen = icono.getImage();
            Image imagenescalada = imagen.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH);

            ImageIcon iconoRedimensionado = new ImageIcon(imagenescalada);
            lblImagProd.setIcon(iconoRedimensionado);
        }
    }//GEN-LAST:event_lblImagProdMouseClicked

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void btnUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbicacionActionPerformed
        String rol = Configuracion.validacion();
        if (rol.equals("0")) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevas ubicaciones", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String ubi = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicacion");
            if (ubi == null) {

            } else {
                ubicacion = new Ubicacion(ubi);
                objUbic.crearUbicacion(ubicacion);
            }
        }
    }//GEN-LAST:event_btnUbicacionActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        String rol = Configuracion.validacion();
        if (rol.equals("0")) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevos proveedores", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            NuevoProveedorDialgo dialogProv = new NuevoProveedorDialgo(this, true);
            dialogProv.setVisible(true);
        }
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        String rol = Configuracion.validacion();
        if (rol.equals("0")) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevas categorias", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String cat = JOptionPane.showInputDialog(null, "Ingrese el nombre de la categoria");
            if (cat == null) {

            } else {
                try {
                    categoria = new Categoria(cat);
                    objCat.IngresarCat(categoria);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar \nla nueva categoria");
                }
            }

        }
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void generaCodigo(String codigo) {
        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Interleaved25());
        //barcode.setCodeType(new Code39());

        // nuestro valor a codificar y algunas configuraciones mas
        barcode.setCode(codigo);

        barcode.setCheckDigit(true);

        imagen = barcode.draw(new BufferedImage(150, 100, BufferedImage.TYPE_INT_RGB));

        ImageIcon barras = new ImageIcon(imagen);

        this.lblImagen.setIcon(barras);
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
            java.util.logging.Logger.getLogger(IngresoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoProd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnGenerador;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnUbicacion;
    private javax.swing.JCheckBox cbxAyuda;
    private javax.swing.JCheckBox cbxGenerador;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JComboBox<Proveedor> cmbProveedor;
    private javax.swing.JComboBox<Ubicacion> cmbUbicacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel lblImagProd;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel pnlGenerador;
    private javax.swing.JTextField txtCantMin;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtNombreProd;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioMayor;
    // End of variables declaration//GEN-END:variables
}
