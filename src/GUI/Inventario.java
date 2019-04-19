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
import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code128;

public final class Inventario extends javax.swing.JFrame {

    int fila;
    File imgCodigoArticulo;
    String n;
    String cantProd;
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
    String ubicacion;
    String iva;
    Principal objP = new Principal();
    JBarcodeBean barcode = new JBarcodeBean();
    public BufferedImage imagen = null;

    public Inventario() {
        material = new DATMaterial();
        manejadorUsuario = new DATUsuario();
        manejadorExistencias = new DATExistenciasBodega();
        manejadorConf = new DATConfiguracion();
        this.modelo2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
        txtBodega.setTransferHandler(null);
        totalInventario();
    }

    public void limpiarTabla() {
        int numFilas = modelo.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }

    private void generaCodigo(String codigo) {
        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Code128());
        // nuestro valor a codificar y algunas configuraciones mas
        barcode.setCode(codigo);

        barcode.setCheckDigit(true);

        imagen = barcode.draw(new BufferedImage(150, 100, BufferedImage.TYPE_INT_RGB));

        ImageIcon barras = new ImageIcon(imagen);

        //lblImagen.setIcon(barras);
        Image image = barras.getImage();

        // cast it to bufferedimage
        BufferedImage buffered = (BufferedImage) image;

        try {
            String workingDirectory = System.getProperty("user.home");
            String absoluteFilePath = "";
            absoluteFilePath = workingDirectory + "\\Pictures" + File.separator + txtNombreProd.getText() + " - " + cantProd + ".png";
            // save to file
            imgCodigoArticulo = new File(absoluteFilePath);
            ImageIO.write(imagen, "png", imgCodigoArticulo);
            System.out.println(imgCodigoArticulo.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Código de barras generado correctamente, la imagen se guardó en el siguiente directorio\n"
                    + imgCodigoArticulo.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido generar el código de barras");
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
        modelo.addColumn("Cantidad");
        modelo.addColumn("Nombre producto");
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio de Compra");
        modelo.addColumn("Precio");
        modelo.addColumn("Precio por mayor");
        modelo.addColumn("Ubicación");
        modelo.addColumn("IVA %");

    }

    public void totalInventario() {
        int cant1 = modelo.getRowCount();
        int cant2 = modelo2.getRowCount();
        double suma1 = 0.00;
        double suma2 = 0.00;
        for (int i = 0; i < cant1; i++) {
            int cant = (int) modelo.getValueAt(i, 0);
            double prec = (Double) modelo.getValueAt(i, 3);
            suma1 = suma1 + (cant * prec);
        }
        for (int i = 0; i < cant2; i++) {
            int cant = (int) modelo2.getValueAt(i, 0);
            double prec = (Double) modelo2.getValueAt(i, 3);
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
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Nombre producto");
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Precio");
        modelo2.addColumn("Bodega");
    }

    public void permisos() {

        ArrayList<Usuario> credencial = manejadorUsuario.obtenerUserLog(host);
        int cant = credencial.size();
        int rol = 0;
        for (int i = 0; i < cant; i++) {
            objU = credencial.get(i);
            rol = objU.getRol();
            if (rol == 0) {
                jmiElimCliente.setEnabled(false);
                jmiElimProv.setEnabled(false);
                jmConfig.setEnabled(false);
                btnEditar.setEnabled(false);
                btnVer.setEnabled(false);
                btnMover.setEnabled(false);
            }
            if (rol == 1) {
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
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (75 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (35 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (35 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (50 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (20 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public static void setAnchoColumnas2() {
        JViewport scroll = (JViewport) tblBodega.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblBodega.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tblBodega.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (40 * ancho) / 100;
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
                String ubi = producto.getStrUbicacion();
                Integer cant = producto.getIntCantidad();
                String ivaProd = producto.getIva();

                modelo.setValueAt(cant, i, 0);
                modelo.setValueAt(nombreProd, i, 1);
                modelo.setValueAt(cod, i, 2);
                modelo.setValueAt(preciCompra, i, 3);
                modelo.setValueAt(precio, i, 4);
                modelo.setValueAt(precioPorMayor, i, 5);
                modelo.setValueAt(ubi, i, 6);
                modelo.setValueAt(ivaProd, i, 7);

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
            String ubi = producto.getStrUbicacion();
            Integer cant = producto.getIntCantidad();
            double precio = producto.getFltPrecio();

            modelo2.setValueAt(cant, i, 0);
            modelo2.setValueAt(nombreProd, i, 1);
            modelo2.setValueAt(cod, i, 2);
            modelo2.setValueAt(precio, i, 3);
            modelo2.setValueAt(ubi, i, 4);

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
        jButton3 = new javax.swing.JButton();
        lblBorrar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBodega = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtProductoBodega = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
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
        cmbBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBusqActionPerformed(evt);
            }
        });

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
                if(col == 10)
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
        btnEditar.setFocusable(false);
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
        btnVer.setFocusable(false);
        btnVer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnMover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/mozo-de-carga.png"))); // NOI18N
        btnMover.setText("Mover");
        btnMover.setFocusable(false);
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/barcode.png"))); // NOI18N
        jButton3.setText("Generar");
        jButton3.setToolTipText("Presione para generar código de barra del producto seleccionado");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblBorrar.setToolTipText("Presione para limpiar el buscador");
        lblBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBorrarMouseClicked(evt);
            }
        });

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
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnVer)
                    .addComponent(btnMover)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos en almacén", jPanel2);

        tblBodega.setModel(modelo2);
        tblBodega.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBodega.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBodegaMouseClicked(evt);
            }
        });
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

        jLabel1.setText("Producto:");

        txtProductoBodega.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProductoBodega, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProductoBodega))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Productos en bodega", jPanel3);

        lblTotal.setText("Total del inventario:");

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
                .addGap(10, 10, 10)
                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
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
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lblTotal))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdMouseClicked
        fila = tblProd.rowAtPoint(evt.getPoint());
        try {
            String prod = (String) tblProd.getModel().getValueAt(fila, 1);//Seleccionamos el nombre del producto
            txtNombreProd.setText(prod);
            codigoProd = (String) tblProd.getModel().getValueAt(fila, 2);//Seleccionamos el codigo del producto
            ubicacion = tblProd.getModel().getValueAt(fila, 6).toString();//Seleccionamos la ubicacion del producto
            iva = tblProd.getModel().getValueAt(fila, 7).toString();//Seleccionamos el iva del producto

            String cant = tblProd.getModel().getValueAt(fila, 0).toString();//Seleccionamos la cantidad del producto
            cantidad = Integer.parseInt(cant);
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
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();
                    String ivaProd = producto.getIva();

                    modelo.setValueAt(cant, i, 0);
                    modelo.setValueAt(nombreProd, i, 1);
                    modelo.setValueAt(cod, i, 2);
                    modelo.setValueAt(preciCompra, i, 3);
                    modelo.setValueAt(precio, i, 4);
                    modelo.setValueAt(precioPorMayor, i, 5);
                    modelo.setValueAt(ubi, i, 6);
                    modelo.setValueAt(ivaProd, i, 7);

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
                    String ubi = producto.getStrUbicacion();
                    Integer cant = producto.getIntCantidad();
                    String ivaProd = producto.getIva();

                    modelo.setValueAt(cant, i, 0);
                    modelo.setValueAt(nombreProd, i, 1);
                    modelo.setValueAt(cod, i, 2);
                    modelo.setValueAt(preciCompra, i, 3);
                    modelo.setValueAt(precio, i, 4);
                    modelo.setValueAt(precioPorMayor, i, 5);
                    modelo.setValueAt(ubi, i, 6);
                    modelo.setValueAt(ivaProd, i, 7);
                }
            }
            txtNombreProd.setText("----------");
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        busqueda();
        if (txtBuscar.getText().length() > 0) {
            try {
                Image img = ImageIO.read(getClass().getResource(("/Recursos/goma-de-borrar.png")));
                lblBorrar.setIcon(new ImageIcon(img));
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                lblBorrar.setCursor(cursor);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No se ha podido cargar la imagen de la goma de borrar");
            }
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    public void moverBodega() {
        if (tblBodega.getSelectedRowCount() == 1) {
            String cod = modelo2.getValueAt(tblBodega.getSelectedRow(), 2).toString();
            int cantExist = (int) modelo2.getValueAt(tblBodega.getSelectedRow(), 0);
            int cantMovida = Integer.parseInt(txtBodega.getText());
            if (cantMovida <= cantExist) {
                cantExist = cantExist - cantMovida;
                producto = new Producto(cantMovida, cod);
                material.moverBodega(producto);
                existencias = new ExistenciasBodega(cod, cantExist);
                manejadorExistencias.moverATienda(existencias, cantMovida);
                updateTabla();
                cargarTablaBodega();
                modelo2.setValueAt(cantExist, fila, 0);
                updateTabla();
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
            mb.txtAyudaCantIni.setText(tblProd.getValueAt(fila, 0).toString());
            mb.setVisible(true);
        }
    }//GEN-LAST:event_btnMoverActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        JOptionPane.showMessageDialog(null, "Módulo no disponible");
    }//GEN-LAST:event_btnVerActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int index = jTabbedPane1.getSelectedIndex();
        if ((bandera == false) && (index == 1)) {
            cargarTablaBodega();
            bandera = true;
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

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

    private void tblBodegaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBodegaMouseClicked
        int filaBodega = tblBodega.rowAtPoint(evt.getPoint());
        String nombreEnBodega = tblBodega.getModel().getValueAt(filaBodega, 1).toString();
        txtProductoBodega.setText(nombreEnBodega);
    }//GEN-LAST:event_tblBodegaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        fila = tblProd.getSelectedRow();
        if (txtNombreProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String codi = tblProd.getValueAt(fila, 2).toString();
            cantProd = tblProd.getValueAt(fila, 0).toString();
            generaCodigo(codi);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBusqActionPerformed
        txtBuscar.requestFocus();
    }//GEN-LAST:event_cmbBusqActionPerformed

    private void lblBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseClicked
        txtBuscar.setText("");
        this.lblBorrar.setIcon(null);
        txtBuscar.requestFocus();
        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        lblBorrar.setCursor(cursor);
    }//GEN-LAST:event_lblBorrarMouseClicked

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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel lblBorrar;
    private javax.swing.JLabel lblTotal;
    public static javax.swing.JTable tblBodega;
    public static javax.swing.JTable tblProd;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtNombreProd;
    private javax.swing.JLabel txtProductoBodega;
    // End of variables declaration//GEN-END:variables
}
