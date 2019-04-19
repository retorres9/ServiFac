package GUI;

import Clases.Bodega;
import Clases.Categoria;
import Clases.Configuracion;
import Clases.ExistenciasBodega;
import Clases.Producto;
import Clases.Proveedor;
import Clases.Ubicacion;
import Clases.Usuario;
import Dat.DATBodega;
import Dat.DATCategoria;
import Dat.DATConfiguracion;
import Dat.DATExistenciasBodega;
import Dat.DATMaterial;
import Dat.DATProveedor;
import Dat.DATUbicacion;
import Dat.DATUsuario;
import Utilidades.Utilidades;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code128;

public final class IngresoProd extends javax.swing.JFrame {

    int cant;
    int rol;
    String host;
    Utilidades util = new Utilidades();
    File imgCodigoArticulo;
    DATCategoria objCat;
    DATBodega objBodega;
    DATProveedor objProveedor;
    DATUbicacion objUbic;
    DATUsuario manejadorUsuario;
    DATExistenciasBodega objExistenciasBodega;
    DATConfiguracion manejadorConf;
    Categoria categoria;
    Ubicacion ubicacion;
    Bodega objetoBodega;
    double precioVenta;
    boolean banderaTextos = true;
    int ivaProd;
    double precioVentaMayor;
    boolean bandera = true;//Se usará para que al momento de hacer clic en la imagen del producto cambie a false
    boolean banderaCodigo = true;//Se usará para que al momento de validar el codigo de barras

    //Modelos de combobox    
    DefaultComboBoxModel<Categoria> modeloCategorias;
    DefaultComboBoxModel<Proveedor> modeloProv;
    DefaultComboBoxModel<Ubicacion> modeloUbic;
    DefaultComboBoxModel<Bodega> modeloBodega;

    JBarcodeBean barcode = new JBarcodeBean();
    public BufferedImage imagen = null;
    DATMaterial conMat;
    Producto producto = new Producto();
    ExistenciasBodega bodegaExistencias = new ExistenciasBodega();
    ButtonGroup iva = new ButtonGroup();
    Configuracion config = new Configuracion();
    Usuario objUsuario = new Usuario();

    public IngresoProd() {
        modeloCategorias = new DefaultComboBoxModel<Categoria>();
        modeloProv = new DefaultComboBoxModel<Proveedor>();
        modeloUbic = new DefaultComboBoxModel<Ubicacion>();
        modeloBodega = new DefaultComboBoxModel<Bodega>();
        manejadorConf = new DATConfiguracion();
        manejadorUsuario = new DATUsuario();
        objCat = new DATCategoria();
        objProveedor = new DATProveedor();
        objUbic = new DATUbicacion();
        conMat = new DATMaterial();
        objBodega = new DATBodega();
        objExistenciasBodega = new DATExistenciasBodega();
        cargarModeloCat();
        cargarModeloProv();
        cargarModeloUbic();
        cargarModeloBodega();
        initComponents();
        obtenerConf();
        txtCantMin.setTransferHandler(null);
        txtCantidad.setTransferHandler(null);
        txtCod.setTransferHandler(null);
        txtGanancia.setEnabled(false);
        txtGananciaMayor.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtExistenciasBodega.setTransferHandler(null);
        txtGanancia.setTransferHandler(null);
        txtGananciaMayor.setTransferHandler(null);
        txtPrecioCompra.setTransferHandler(null);
        host = util.getPcName();
        obtenerCredenciales();
        muestraPrecio();
        muestraPrecioxMayor();
        permisos();
        seleccionIva();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        txtNombreProd.requestFocusInWindow();
    }

    public void seleccionIva() {
        iva.add(iva0);
        iva.add(txtIva);
    }

    public void obtenerConf() {
        ArrayList<Configuracion> conf = manejadorConf.cargaConfig();
        int cantConf = conf.size();
        for (int i = 0; i < cantConf; i++) {
            config = conf.get(i);
            ivaProd = config.getIva();
            txtIva.setText(Integer.toString(ivaProd) + "%");
        }
    }

    public void obtenerCredenciales() {
        ArrayList<Usuario> credencial = manejadorUsuario.obtenerUserLog(host);
        int cantCred = credencial.size();
        for (int i = 0; i < cantCred; i++) {
            objUsuario = credencial.get(i);
            rol = objUsuario.getRol();
        }
    }

    public void muestraPrecioxMayor() {
        if (txtPrecioCompra.getText().isEmpty() || txtGananciaMayor.getText().isEmpty()) {
        } else {
            try {
                double precio = Double.parseDouble(txtPrecioCompra.getText());
                double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
                if (txtIva.isSelected()) {
                    precioVentaMayor = ((precio) + precio * 0.12) + (precio * (gananciaMayor / 100));
                } else {
                    precioVentaMayor = precio + (precio * (gananciaMayor / 100));
                }
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambioMayor = new DecimalFormat("0.00", simbolos);
                String strPrecioMayor = dcmlCambioMayor.format(precioVentaMayor);
                precioVentaMayor = Double.parseDouble(strPrecioMayor);
                lblPrecioMayor.setText("Precio de venta al por mayor: $" + precioVentaMayor);
            } catch (NumberFormatException ex) {
            }

        }
    }

    public void muestraPrecio() {
        if (txtPrecioCompra.getText().isEmpty() || txtGanancia.getText().isEmpty()) {
        } else {
            try {
                double precioInput = Double.parseDouble(txtPrecioCompra.getText());
                double ganancia = Double.parseDouble(txtGanancia.getText());
                Double ivaMasGanancia = ivaProd + ganancia;
                ivaMasGanancia = ivaMasGanancia / 100;
                if (txtIva.isSelected()) {
                    precioVenta = precioInput * ivaMasGanancia;
                    precioInput = precioInput + precioVenta;
                    precioVenta = ((precioInput));
                } else {
                    precioVenta = precioInput + (precioInput * (ganancia / 100));
                }
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                String strPrecio = dcmlCambio.format(precioVenta);
                precioVenta = Double.parseDouble(strPrecio);
                lblPrecioPublico.setText("Precio de venta al público: $" + precioVenta);
            } catch (NumberFormatException ex) {

            }
        }
    }

    public void validacioCod() {
        int cantProd = conMat.validaCodigo(txtCod.getText());
        if (txtCod.getText().isEmpty()) {
            this.txtValid.setIcon(null);
            this.lblBorrar.setIcon(null);
        }
        if (cantProd == 0) {
            try {
                Image img = ImageIO.read(getClass().getResource(("/Recursos/success.png")));
                txtValid.setIcon(new ImageIcon(img));
                bandera = true;
            } catch (IOException ex) {
                Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource(("/Recursos/error.png")));
                txtValid.setIcon(new ImageIcon(img));
                txtValid.setToolTipText("El codigo digitado ya ha sido ingresado en la base de datos"
                        + "haga clic en la X para actualizar el producto");
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                txtValid.setCursor(cursor);
                bandera = false;
            } catch (IOException ex) {
                Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cargarModeloCat() {
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

    private void cargarModeloBodega() {
        ArrayList<Bodega> listaBodegas;
        listaBodegas = objBodega.obtenerBodega();
        for (Bodega bodega : listaBodegas) {
            modeloBodega.addElement(bodega);
        }
    }

    public void cargarModeloProv() {
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

    public void guardarBodega() {
        if (!txtExistenciasBodega.getText().isEmpty()) {
            Bodega bodega = (Bodega) cmbBodega.getSelectedItem();
            String codigo = txtCod.getText();
            int Cantidad = Integer.parseInt(txtExistenciasBodega.getText());
            bodegaExistencias = new ExistenciasBodega(bodega.getIntIdBodega(), codigo, Cantidad);
            objExistenciasBodega.ingresarEnBodega(bodegaExistencias);
        }
    }

    public void reseteoCampos() {
        if (cbxAyuda.isSelected()) {
            txtCantidad.setText("");
            txtCod.setText("");
        } else {
            txtCod.setText("");
            txtGanancia.setText("");
            txtGananciaMayor.setText("");
            txtCantidad.setText("");
            txtNombreProd.setText("");
            txtPrecioCompra.setText("");
            lblPrecioMayor.setText("Precio de venta al por mayor:");
            lblPrecioPublico.setText("Precio de venta al público:");
            txtCantMin.setText("");
        }
    }

    public void guardar() {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat decimal = new DecimalFormat("0.00", simbolo);
        try {
            String strCod = txtCod.getText();
            String nombre = txtNombreProd.getText().toUpperCase();
            double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
            double ganancia = Double.parseDouble(txtGanancia.getText());
            double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            int cantidadMin = Integer.parseInt(txtCantMin.getText());
            Ubicacion ubica = (Ubicacion) cmbUbicacion.getSelectedItem();
            Proveedor empresa = (Proveedor) cmbProveedor.getSelectedItem();
            Categoria cat = (Categoria) cmbCategoria.getSelectedItem();
            Bodega bod = (Bodega) cmbBodega.getSelectedItem();
            String strPrecio1 = decimal.format(precioCompra);
            String strGanancia = decimal.format(ganancia);
            String strGnanciaMayor = decimal.format(gananciaMayor);
            double dblPrecioCompra = Double.parseDouble(strPrecio1);
            double dblGanancia = Double.parseDouble(strGanancia);
            double dblGananciaMayot = Double.parseDouble(strGnanciaMayor);
            boolean stock = true;// revisar ya que en BD esta default
            int idBodega;
            String ivaPrd;
            if (iva0.isSelected()) {
                ivaPrd = "0%";
            } else {
                ivaPrd = "12%";
            }
            if (txtExistenciasBodega.getText().isEmpty()) {
                idBodega = 0;
            } else {
                idBodega = bod.getIntIdBodega();
            }
            producto = new Producto(nombre, strCod, dblPrecioCompra, precioVenta, precioVentaMayor, dblGanancia, dblGananciaMayot, stock, ubica.getIdUbicacion(), cat.getIdCategoria(), cantidad, cantidadMin, empresa.getRuc(), ivaPrd, idBodega);
            try {
                if (conMat.IngresarProducto(producto) == true) {
                    JOptionPane.showMessageDialog(null, "Producto creado satisfactoriamente");
                    reseteoCampos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error");
            }
            if (!txtExistenciasBodega.getText().isEmpty()) {
                guardarBodega();
                txtCod.setText("");
                txtExistenciasBodega.setText("");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "No se puede agregar un numero con dos puntos decimales", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        grupoIVA = new javax.swing.ButtonGroup();
        jLabel15 = new javax.swing.JLabel();
        txtNombreProd = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblPrecioMayor = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnProveedor = new javax.swing.JButton();
        txtGanancia = new javax.swing.JTextField();
        txtGananciaMayor = new javax.swing.JTextField();
        btnUbicacion = new javax.swing.JButton();
        btnCategoria = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtIva = new javax.swing.JRadioButton();
        iva0 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        lblPrecioPublico = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbBodega = new javax.swing.JComboBox<>();
        txtExistenciasBodega = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtValid = new javax.swing.JLabel();
        lblBorrar = new javax.swing.JLabel();
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

        jTextField1.setText("jTextField1");

        jLabel15.setText("jLabel15");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(531, 405));
        setPreferredSize(new java.awt.Dimension(1175, 715));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodKeyTyped(evt);
            }
        });

        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Nombre de Producto:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Codigo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Precio de compra:");

        lblPrecioMayor.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPrecioMayor.setForeground(new java.awt.Color(0, 153, 51));
        lblPrecioMayor.setText("Precio de venta al por mayor:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Ubicacion:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
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

        jLabel8.setFont(new java.awt.Font("Roboto Cn", 1, 36)); // NOI18N
        jLabel8.setText("Ingreso de Productos");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/etiqueta-de-nuevo-producto.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Cantidad Minima:");

        txtCantMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantMinKeyTyped(evt);
            }
        });

        cmbUbicacion.setMaximumRowCount(5);
        cmbUbicacion.setModel(modeloUbic);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Proveedor:");

        cmbProveedor.setModel(modeloProv);

        cbxAyuda.setText("Ayuda");
        cbxAyuda.setToolTipText("Al seleccionar el botón algunos campos no se borrarán para que siga ingresando productos parecidos");
        cbxAyuda.setFocusable(false);

        pnlGenerador.setBorder(javax.swing.BorderFactory.createTitledBorder("Generador de Codigo de Barras"));

        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnGenerador.setText("Generar Codigo");
        btnGenerador.setFocusable(false);
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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Categoria:");

        cmbCategoria.setModel(modeloCategorias);

        btnProveedor.setText("Agregar Proveedor");
        btnProveedor.setFocusable(false);
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        txtGanancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaKeyTyped(evt);
            }
        });

        txtGananciaMayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGananciaMayorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGananciaMayorKeyTyped(evt);
            }
        });

        btnUbicacion.setText("Agregar Ubicación");
        btnUbicacion.setFocusable(false);
        btnUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbicacionActionPerformed(evt);
            }
        });

        btnCategoria.setText("Agregar Categoria");
        btnCategoria.setFocusable(false);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setText("Producto con IVA:");

        txtIva.setFocusable(false);
        txtIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaActionPerformed(evt);
            }
        });

        iva0.setText("0%");
        iva0.setFocusable(false);
        iva0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iva0ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Ganancia %");

        lblPrecioPublico.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPrecioPublico.setForeground(new java.awt.Color(0, 153, 0));
        lblPrecioPublico.setText("Precio de venta al público:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("En bodega"));

        cmbBodega.setModel(modeloBodega);
        cmbBodega.setFocusable(false);

        txtExistenciasBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExistenciasBodegaKeyTyped(evt);
            }
        });

        jButton1.setText("Agregar Bodega");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("*Si deja el campo vacio no se agregará nada a bodega");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbBodega, 0, 153, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(txtExistenciasBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExistenciasBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Ganancia por mayor");

        txtValid.setText(" ");
        txtValid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValidMouseClicked(evt);
            }
        });

        lblBorrar.setMaximumSize(new java.awt.Dimension(3, 16));
        lblBorrar.setMinimumSize(new java.awt.Dimension(3, 16));
        lblBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBorrarMouseClicked(evt);
            }
        });

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxAyuda)
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtCod)
                                                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtValid, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(txtIva)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(iva0)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantMin, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioPublico)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtGananciaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblPrecioMayor)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCategoria))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnUbicacion))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnProveedor)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlGenerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btnAtras)
                        .addGap(239, 239, 239)
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtIva)
                            .addComponent(iva0))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(txtValid)
                            .addComponent(lblBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cbxAyuda)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtGananciaMayor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecioPublico))
                    .addComponent(pnlGenerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lblPrecioMayor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnProveedor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbicacion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCategoria))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnGuardar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if ((txtCantidad.getText().isEmpty() || txtCod.getText().isEmpty()
                || txtNombreProd.getText().isEmpty() || txtPrecioCompra.getText().isEmpty()
                || lblPrecioMayor.getText().isEmpty() || txtGanancia.getText().isEmpty()
                || txtGananciaMayor.getText().isEmpty()) || (!txtIva.isSelected()
                && !iva0.isSelected())) { //Asegura que ningun campo quede nulo
            JOptionPane.showMessageDialog(null, "Hay campos vacios que no se pueden guardar");
        } else if ((cmbCategoria.getItemCount() == 0)) {
            JOptionPane.showMessageDialog(null, "No hay categorias ingresadas, por favor\ningrese una categoria");
        } else if ((cmbUbicacion.getItemCount() == 0)) {
            JOptionPane.showMessageDialog(null, "No hay ubicaciones ingresadas, por favor\ningrese una ubicación");
        } else { //Si todo esta bien procede a guardar proveedor
            if (cmbProveedor.getSelectedItem().toString().equals("(Vacio)")) { //Se pregunta al usuario si desa dejar el producto sin proveedor
                int n = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea guardar el producto sin asignarle un proveedor?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) { //Si acepta se guarda el producto asigandole como proveedor "Sin proveedor"
                    if (imagen != null) { //Si el cmbobox de generador está seleccionado guarda la imagen del codigo de barras

                        imagen = null;
                        this.lblImagen.setIcon(null);
                        guardar();

                    } else { //Si no esta seleccionado el combobox no guarda ninguna imagen
                        guardar();
                    }

                }
            } else { //Si el usuario es uno diferente a "Sin proveedor" no pregunta y procede a guardar el producto
                if (imagen != null) { //Si el cmbobox de generador está seleccionado guarda la imagen del codigo de barras

                    imagen = null;
                    this.lblImagen.setIcon(null);
                    guardar();

                } else {//Si no esta seleccionado el combobox no guarda ninguna imagen
                    guardar();
                }
            }
        }
        txtNombreProd.requestFocusInWindow();
        txtValid.setIcon(null);
        lblBorrar.setIcon(null);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        Principal objP = new Principal();
        if (!(txtCantidad.getText().isEmpty()) || !(txtCod.getText().isEmpty())
                || !(txtNombreProd.getText().isEmpty()) || !(txtPrecioCompra.getText().isEmpty())
                || !(txtGananciaMayor.getText().isEmpty())
                || !(txtGanancia.getText().isEmpty())) {
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

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9') || txtPrecioCompra.getText().length() > 5)
                && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            evt.consume();
        }
        if (!txtGanancia.getText().isEmpty() || !txtGananciaMayor.getText().isEmpty()) {
            try {
                double precio = Double.parseDouble(txtPrecioCompra.getText());
                double ganancia = Double.parseDouble(txtGanancia.getText());
                double gananciaMayor = Double.parseDouble(txtGananciaMayor.getText());
                precioVenta = precio + (precio * (ganancia / 100));
                lblPrecioPublico.setText("Precio de venta al público: $" + precioVenta);
                precioVentaMayor = precio + (precio * (gananciaMayor / 100));
                lblPrecioMayor.setText("Precio de venta al por mayor: $" + precioVentaMayor);
            } catch (NumberFormatException ex) {

            }

        }
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9' || txtCantidad.getText().length() > 6)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantMinKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9' || txtCantMin.getText().length() > 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantMinKeyTyped

    private void btnGeneradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneradorActionPerformed
        if (this.txtCod.getText().length() < 1) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un código para generar el código de barras!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (this.txtNombreProd.getText().length() < 1) {
            JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del producto para\ngenerar el código de barras!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (this.txtCantidad.getText().length() < 1) {
            JOptionPane.showMessageDialog(this, "Debes ingresar la cantidad existente\n"
                    + "del producto.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            generaCodigo(this.txtCod.getText());
        }
    }//GEN-LAST:event_btnGeneradorActionPerformed

    private void btnUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbicacionActionPerformed
        if (rol == 0) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevas ubicaciones", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String ubi = JOptionPane.showInputDialog(null, "Ingrese el nombre de la ubicacion").toUpperCase();
            if (ubi == null) {

            } else {
                ubicacion = new Ubicacion(ubi);
                try {
                    if (objUbic.crearUbicacion(ubicacion)) {
                        cmbUbicacion.removeAllItems();
                        cargarModeloUbic();

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnUbicacionActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        if (rol == 0) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevos proveedores", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            NuevoProveedorDialgo provDialog = new NuevoProveedorDialgo(null, true);
            provDialog.setVisible(true);

            if (provDialog != null) {
                if (!provDialog.getInformacion().equals("")) {
                    modeloProv.removeAllElements();
                    cargarModeloProv();
                }
            }
        }
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        if (rol == 0) {
            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevas categorias", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String cat = JOptionPane.showInputDialog(null, "Ingrese el nombre de la categoria").toUpperCase();
            if (cat == null) {

            } else {
                try {
                    categoria = new Categoria(cat);
                    try {
                        if (objCat.IngresarCat(categoria)) {
                            cmbCategoria.removeAllItems();
                            cargarModeloCat();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error");
                    }

                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al ingresar \nla nueva categoria");
                }
            }

        }
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void txtGananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyReleased
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
        if ((txtGanancia.getText().isEmpty()) || (txtGanancia.getText().equals("")) || (txtPrecioCompra.getText().isEmpty())) {
            lblPrecioPublico.setText("Precio de venta al público:");
        } else {
            muestraPrecio();
        }
    }//GEN-LAST:event_txtGananciaKeyReleased

    private void txtPrecioCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyReleased
        if (txtGanancia.getText().isEmpty() || txtGananciaMayor.getText().isEmpty()) {

        } else {
            muestraPrecio();
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_txtPrecioCompraKeyReleased

    private void txtGananciaMayorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMayorKeyReleased
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
        if ((txtGananciaMayor.getText().isEmpty()) || (txtGananciaMayor.getText().equals("")) || (txtPrecioCompra.getText().isEmpty())) {
            lblPrecioMayor.setText("Precio de venta al por mayor:");
        } else {
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_txtGananciaMayorKeyReleased

    private void txtCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') || (txtCod.getText().length() > 12)) { // Se compara el tamaño de txtCod con 12 porque txtCod.lenght()cuenta desde 0>
            evt.consume();
        }
//        if ((c == KeyEvent.VK_ENTER) || (txtValid.getToolTipText().equals(null)) ) {
//            System.out.println("yes");
//        }
    }//GEN-LAST:event_txtCodKeyTyped

    private void txtGananciaMayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaMayorKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGananciaMayorKeyTyped

    private void txtGananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGananciaKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9') && (c != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGananciaKeyTyped

    private void txtExistenciasBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciasBodegaKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9' || txtExistenciasBodega.getText().length() > 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtExistenciasBodegaKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String newBodega = JOptionPane.showInputDialog(null, "Ingrese la nueva bodega");
        if (newBodega == null) {

        } else {
            objetoBodega = new Bodega(newBodega);
            objBodega.nuevaBodega(objetoBodega);
            cmbBodega.removeAllItems();
            cargarModeloBodega();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaActionPerformed
        if (banderaTextos == true) {
            txtGanancia.setEnabled(true);
            txtGananciaMayor.setEnabled(true);
            txtPrecioCompra.setEnabled(true);
            bandera = false;
        }
        if (!txtPrecioCompra.getText().isEmpty()) {
            muestraPrecio();
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_txtIvaActionPerformed

    private void iva0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iva0ActionPerformed
        if (banderaTextos == true) {
            txtGanancia.setEnabled(true);
            txtGananciaMayor.setEnabled(true);
            txtPrecioCompra.setEnabled(true);
            bandera = false;
        }
        if (!txtPrecioCompra.getText().isEmpty()) {
            muestraPrecio();
            muestraPrecioxMayor();
        }
    }//GEN-LAST:event_iva0ActionPerformed

    private void txtCodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyReleased
        validacioCod();
        if (!(txtCod.getText().isEmpty())) {
            try {
                Image img = ImageIO.read(getClass().getResource(("/Recursos/goma-de-borrar.png")));
                lblBorrar.setIcon(new ImageIcon(img));
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                lblBorrar.setCursor(cursor);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No se ha podido cargar la imagen de la goma de borrar");
            }
        } else {
            this.lblBorrar.setIcon(null);
        }
    }//GEN-LAST:event_txtCodKeyReleased

    private void txtValidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValidMouseClicked
        if (bandera == false) {
            ActualizacionDialog invDialog = new ActualizacionDialog(new javax.swing.JDialog(), true);
            invDialog.lblCod.setText(txtCod.getText());
            ActualizacionDialog.txtIngreso.setText("ingreso");
            invDialog.setVisible(true);

            //InventarioDialog.txtBuscar.setText(txtCod.getText());
        }
    }//GEN-LAST:event_txtValidMouseClicked

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

    private void lblBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseClicked
        txtCod.setText("");
        txtValid.setIcon(null);
        lblBorrar.setIcon(null);
    }//GEN-LAST:event_lblBorrarMouseClicked

    private void generaCodigo(String codigo) {
        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Code128());
        // nuestro valor a codificar y algunas configuraciones mas
        barcode.setCode(codigo);

        barcode.setCheckDigit(true);

        imagen = barcode.draw(new BufferedImage(150, 100, BufferedImage.TYPE_INT_RGB));

        ImageIcon barras = new ImageIcon(imagen);

        lblImagen.setIcon(barras);
        Image image = barras.getImage();

        // cast it to bufferedimage
        BufferedImage buffered = (BufferedImage) image;

        try {
            String workingDirectory = System.getProperty("user.home");
            String absoluteFilePath = "";
            absoluteFilePath = workingDirectory + "\\Pictures" + File.separator + txtNombreProd.getText() + " - " + txtCantidad.getText() + ".png";
            // save to file
            imgCodigoArticulo = new File(absoluteFilePath);
            ImageIO.write(imagen, "png", imgCodigoArticulo);
            System.out.println(imgCodigoArticulo.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(IngresoProd.class.getName()).log(Level.SEVERE, null, e);
        }

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
    private javax.swing.JComboBox<Bodega> cmbBodega;
    private javax.swing.JComboBox<Categoria> cmbCategoria;
    private javax.swing.JComboBox<Proveedor> cmbProveedor;
    private javax.swing.JComboBox<Ubicacion> cmbUbicacion;
    private javax.swing.ButtonGroup grupoIVA;
    private javax.swing.JRadioButton iva0;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel lblBorrar;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblPrecioMayor;
    private javax.swing.JLabel lblPrecioPublico;
    private javax.swing.JPanel pnlGenerador;
    private javax.swing.JTextField txtCantMin;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtExistenciasBodega;
    private javax.swing.JTextField txtGanancia;
    private javax.swing.JTextField txtGananciaMayor;
    private javax.swing.JRadioButton txtIva;
    private javax.swing.JTextField txtNombreProd;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JLabel txtValid;
    // End of variables declaration//GEN-END:variables
}
