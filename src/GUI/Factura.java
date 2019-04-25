package GUI;

import Clases.Clientes;
import Clases.Producto;
import Clases.DetalleVenta;
import Clases.Usuario;
import Clases.Venta;
import Dat.DATClientes;
import Dat.DATMaterial;
import Dat.DATReporte;
import Dat.DATUsuario;
import Dat.DATVenta;
import Utilidades.Utilidades;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public final class Factura extends javax.swing.JFrame {

    double deuda;
    int fila;
    int cont;
    int cantProd2;
    int filasSelec;
    int cantInicial;
    int rol;
    int helper;
    boolean credito;
    boolean ayudaCont;
    boolean flag = true;
    boolean banderaFila = true;
    String cedUsuario;
    String n, vendedor;
    Clientes cliente = new Clientes();
    DetalleVenta detalle = new DetalleVenta();
    Producto producto = new Producto();
    Venta venta = new Venta();
    Usuario usuario = new Usuario();
    DATVenta manejadorVenta;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultListModel<Producto> listaProductos = new DefaultListModel<Producto>();
    DATMaterial manejadorProd;
    DATClientes manejadorCliente;
    DATReporte manejadorDetalle;
    DATUsuario manejadorUsuario;
    double totalVenta;
    String host;
    double cant;
    Utilidades util = new Utilidades();

    public Factura() {
        manejadorProd = new DATMaterial();
        manejadorCliente = new DATClientes();
        manejadorVenta = new DATVenta();
        manejadorDetalle = new DATReporte();
        manejadorUsuario = new DATUsuario();
        initComponents();
        txtCed.setTransferHandler(null);
        host = util.getPcName();
        cargarEncabezado();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        setAnchoColumnas();
        contador();
        permisos();
        txtCed.setTransferHandler(null);
        txtCod.requestFocusInWindow();
        usuario();
        
    }

    @SuppressWarnings("unchecked")
    
    public void generarFactura() {
        try {
            JDialog fact = new JDialog(new javax.swing.JFrame(), "Factura", true);
            fact.setSize(1124, 768);
            fact.setLocationRelativeTo(null);
            JasperReport reporte = (JasperReport) JRLoader.loadObject("factura.jasper");
            Map parametro = new HashMap();

            parametro.put("cedula", txtCed.getText());
            parametro.put("direccion", txtDireccionCl.getText());
            parametro.put("cliente", txtCliente.getText());
            parametro.put("telefono", txtTelf.getText());
            parametro.put("telefono", txtTelf.getText());
            parametro.put("total", txtTotal.getText());
            JRDataSource dataSource = new JRTableModelDataSource(tblVentas.getModel());
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, dataSource);
            JasperViewer viewer = new JasperViewer(j, false);
            fact.getContentPane().add(viewer.getContentPane());
            fact.setVisible(true);
            //jv.setTitle("factura");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar la factura\nError 070", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    public void generarNotaVenta() {
        try {
            JDialog fact = new JDialog(new javax.swing.JFrame(), "Factura", true);
            fact.setSize(1124, 768);
            fact.setLocationRelativeTo(null);
            JasperReport reporte = (JasperReport) JRLoader.loadObject("notaVenta.jasper");
            Map parametro = new HashMap();

            parametro.put("cedula", txtCed.getText());
            parametro.put("direccion", txtDireccionCl.getText());
            parametro.put("cliente", txtCliente.getText());
            parametro.put("telefono", txtTelf.getText());
            parametro.put("total", txtTotal.getText());
            JRDataSource dataSource = new JRTableModelDataSource(tblVentas.getModel());
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, dataSource);
            JasperViewer viewer = new JasperViewer(j, false);
            fact.getContentPane().add(viewer.getContentPane());
            fact.setVisible(true);
            //jv.setTitle("factura");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar la nota de venta\nError 069", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarEncabezado() {
        modelo.addColumn("Cantidad");
        modelo.addColumn("Descripcion");
        modelo.addColumn("P Unit");
        modelo.addColumn("Iva Prod");
        modelo.addColumn("P Total");
        modelo.addColumn("Codigo");
        modelo.addColumn("iva");
        modelo.addColumn("Precio iva");
        modelo.addColumn("Iva 0");
    }

    public void contador() {
        ArrayList<Venta> cantProducto = manejadorVenta.CuentaVentas();
        int cantid = cantProducto.size();
        for (int i = 0; i < cantid; i++) {
            venta = cantProducto.get(i);
            cont = venta.getIntIdVenta();
            if (cont == 0) {
                cont = 1;
            }
        }
    }

    public void usuario() {
        int e = tblVentas.getRowCount();
        System.out.println(e);
        ArrayList<Usuario> cedula = manejadorUsuario.obtenerUserLog(host);
        int cantUser = cedula.size();
        for (int i = 0; i < cantUser; i++) {
            usuario = cedula.get(i);
            vendedor = usuario.getNombre();
            cedUsuario = usuario.getUsuario();
            txtUsuario.setText(vendedor);
            rol = usuario.getRol();
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

    public static String getFecha() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
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
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (15 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void resetFact() {
        int a = tblVentas.getRowCount() - 1;
        for (int j = a; tblVentas.getRowCount() > 0; j--) {
            modelo.removeRow(j);
            fila = fila - 1;
        }
    }

    public void venta() {
        if ((credito == false) && (cmbPago.getSelectedItem().equals("Crédito"))) {
            JOptionPane.showMessageDialog(null, "El cliente no tiene crédito aprobado");
            helper = 0;
        } else {
            GuiCambio cambioDiag = new GuiCambio(this, true);
            cambioDiag.cred = credito;
            GuiCambio.monto = Double.parseDouble(txtTotal.getText());
            GuiCambio.cliente = txtCliente.getText();
            GuiCambio.txtAyudaDeuda.setText(String.valueOf(deuda));
            GuiCambio.txtAyudaCred.setText(String.valueOf(cant));
            cambioDiag.setVisible(true);

            try {
                double num = 0, numTotal;
                if (cambioDiag != null) {
                    num = cambioDiag.getCambio();
                }
                if (num >= 0.00) {
                    numTotal = Double.parseDouble(txtTotal.getText());
                    double cambio = num - numTotal;
                    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                    simbolos.setDecimalSeparator('.');
                    DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                    String decimal = dcmlCambio.format(cambio);
                    double dblDecimal = Double.parseDouble(decimal);
                    dblDecimal = Math.abs(dblDecimal);
                    double EPSILON = 0.0000001;

                    if (cmbPago.getSelectedItem().equals("Contado")) {

                    } else if (cmbPago.getSelectedItem().equals("Crédito")) {

                    }

                    if (dblDecimal < EPSILON) {
                        JOptionPane.showMessageDialog(null, "<html><h1>No hay cambio</h1></html>");
                    } else if ((dblDecimal > 0) && (num > numTotal)) {
                        JOptionPane.showMessageDialog(null, "<html><h1>El cambio es: " + dblDecimal + "</h1></html>");
                        num = num - dblDecimal;
                    } else {
                        JOptionPane.showMessageDialog(null, "<html><h1>El cliente debe: " + dblDecimal + "</h1></html>");
                        double nuevaDeuda = deuda + dblDecimal;
                        cliente = new Clientes(nuevaDeuda, txtCed.getText());
                        manejadorCliente.agregarDeuda(cliente);
                    }
                    for (int i = 0; i < tblVentas.getRowCount(); i++) {

                        int cantVenta = (int) tblVentas.getValueAt(i, 0);
                        String descripcionVenta = (String) tblVentas.getValueAt(i, 1);
                        String strCodigo = (String) tblVentas.getValueAt(i, 5);
                        String strPrecio = tblVentas.getValueAt(i, 2).toString();
                        double precio_Venta = Double.parseDouble(strPrecio);
                        producto = new Producto(cantVenta, descripcionVenta);
                        manejadorProd.UpdateCantFactura(producto);
                        String cedula = txtCed.getText();
                        detalle = new DetalleVenta(cedula, cantVenta, strCodigo, precio_Venta, vendedor, cont);
                    }
                    venta = new Venta(cont, numTotal, num, getFecha(), cedUsuario, cont);//OJO
                    manejadorVenta.crearVenta(venta, detalle);
                    helper = 1;
                    ayudaCont = true;
                    if (cmbTipComp.getSelectedItem().equals("Factura")) {
                        generarFactura();
                    } else {
                        generarNotaVenta();
                    }
                    resetFact();

                } else {
                    ayudaCont = false;
                }

            } catch (NumberFormatException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {

            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        txtEmpresa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        txtImprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmbTipComp = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbPago = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTelf = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblIvaCero = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaBusq = new javax.swing.JList<>();
        txtBusqProd = new javax.swing.JTextField();
        rbtnPrecio2 = new javax.swing.JRadioButton();
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
        setMinimumSize(new java.awt.Dimension(1142, 708));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblVentas = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col)
            {
                //If you didn't want the first column to be editable
                if(col == 0)
                return true;
                else
                return false;
            }
        };
        tblVentas.setModel(modelo);
        tblVentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVentas.getTableHeader().setReorderingAllowed(false);
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        tblVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVentasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVentasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblVentasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblVentas);
        if (tblVentas.getColumnModel().getColumnCount() > 0) {
            tblVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        txtEmpresa.setFont(new java.awt.Font("Roboto Cn", 1, 36)); // NOI18N
        txtEmpresa.setText("Nombre Empresa");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja.png"))); // NOI18N

        txtCod.setFocusCycleRoot(true);
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodKeyPressed(evt);
            }
        });

        txtImprimir.setText("Imprimir");
        txtImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImprimirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Condensed", 1, 14))); // NOI18N
        jPanel1.setToolTipText("");

        cmbTipComp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Nota de Venta" }));
        cmbTipComp.setFocusable(false);
        cmbTipComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipCompActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel4.setText("Tipo de Comprobante:");

        jLabel8.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel8.setText("Tipo de Pago:");

        cmbPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito" }));
        cmbPago.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Condensed", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        txtDireccionCl.setText(" ");
        txtDireccionCl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel15.setText("Direccion:");

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel13.setText("Nombre:");

        jLabel11.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel11.setText("Cedula:");

        txtCed.setText("1111111111111");
        txtCed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedFocusLost(evt);
            }
        });
        txtCed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedKeyTyped(evt);
            }
        });

        jButton2.setText("Buscar por nombre");
        jButton2.setToolTipText("Presione para buscar por nombre");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtCliente.setText("CONSUMIDOR FINAL");
        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel2.setText("Teléfono:");

        txtTelf.setText(" ");
        txtTelf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(txtDireccionCl, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtCliente))
                    .addComponent(jButton2))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccionCl)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelf)
                    .addComponent(jLabel2))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel1.setText("Subtotal:");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel3.setText("IVA 12%:");

        jLabel5.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel5.setText("TOTAL:");

        lblSubtotal.setText("0.00");

        lblIva.setText("0.00");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 204, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0.00");

        txtUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnetVendedor.png"))); // NOI18N
        txtUsuario.setText("Usuario=====");

        jLabel6.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel6.setText("IVA 0%:");

        lblIvaCero.setText("0.00");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/lupa.png"))); // NOI18N
        jLabel7.setToolTipText("Presione para ver la información de un producto");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        listaBusq.setModel(listaProductos);
        listaBusq.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaBusq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaBusqMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaBusq);

        txtBusqProd.setText("Buscar...");
        txtBusqProd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBusqProdFocusGained(evt);
            }
        });
        txtBusqProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusqProdKeyReleased(evt);
            }
        });

        rbtnPrecio2.setText("Precio 2");

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
                .addGap(10, 10, 10)
                .addComponent(txtEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsuario)
                .addGap(83, 83, 83)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtBusqProd, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblIvaCero)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jButton1)
                                .addGap(375, 375, 375)
                                .addComponent(txtImprimir))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbtnPrecio2))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnPrecio2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(txtEmpresa)
                                        .addGap(6, 6, 6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtUsuario)
                                        .addGap(18, 18, 18)))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel7)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblSubtotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIvaCero)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIva)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBusqProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtImprimir))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void getCant() {
        try {
            int numFilas = tblVentas.getSelectedRow();
            if (numFilas >= 0) {
                String filaDesc = (String) tblVentas.getValueAt(numFilas, 1);
                cantInicial = (int) tblVentas.getValueAt(numFilas, 0);
                ArrayList<Producto> datos = manejadorProd.comprobarCant(filaDesc);
                int cantDatos = datos.size();
                for (int i = 0; i < cantDatos; i++) {
                    producto = datos.get(i);
                    int canti = producto.getIntCantidad();
                    cantProd2 = canti;
                }
            }
        } catch (ClassCastException ex) {

        }

    }
    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        getCant();
    }//GEN-LAST:event_tblVentasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void cargaCliente() {
        String nom = txtCed.getText();
        ArrayList<Clientes> busqCliente = manejadorCliente.FacturaCliente(nom);
        int listaCliente = busqCliente.size();
        for (int i = 0; i < listaCliente; i++) {
            cliente = busqCliente.get(i);
            String nombre = cliente.getStrNombre();
            String dir = cliente.getStrDireccion();
            String telf = cliente.getStrTelf();
            txtCliente.setText(nombre);
            txtDireccionCl.setText(dir);
            txtTelf.setText(telf);
            deuda = cliente.getDblDeuda();
            credito = cliente.isCredito();
            cant = cliente.getCant();
        }

        if (txtCliente.getText().equals(" ")) {
            int opc = JOptionPane.showConfirmDialog(null, "Cliente no registrado"
                    + "\n¿Desea ingrear un nuevo Cliente?", "Aviso!", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                NuevoClienteDialog clienteDialogo = new NuevoClienteDialog(this, true);
                clienteDialogo.txtCedula.setText(nom);
                clienteDialogo.txtAyuda.setText(cedUsuario);
                clienteDialogo.txtAyudaRol.setText(String.valueOf(rol));
                clienteDialogo.setVisible(true);
                if (clienteDialogo != null) {
                    if (!clienteDialogo.getInformacion().equals("")) {

                    }
                }
            } else {/*No se hace nada*/
            }
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!txtCed.getText().isEmpty()) {
            cargaCliente();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImprimirActionPerformed
        if ((tblVentas.getRowCount() == 0) || (txtCed.getText().isEmpty())) {
            //Do nothing
        } else {
            if ((txtCliente.getText().equals("CONSUMIDOR FINAL")) && (cmbPago.getSelectedItem().equals("Crédito"))) {
                JOptionPane.showMessageDialog(null, "No se puede agregar deuda a CONSUMIDOR FINAL");
            } else {
                if (cmbTipComp.getSelectedItem().equals("Nota de Venta")) {
                    venta();
                    if (ayudaCont == true) {
                        this.contador();
                    } else {
                        //Do nothing
                    }

                } else {
                    venta();
                    if (ayudaCont == true) {
                        this.contador();
                    } else {
                        //Do nothing
                    }
                }
            }
        }
        if (helper == 1) {
            txtCliente.setText("CONSUMIDOR FINAL");
            txtCed.setText("1111111111111");
            txtDireccionCl.setText(" ");
            txtTelf.setText(" ");
            txtCod.requestFocusInWindow();
            txtTotal.setText("0.00");
            lblIva.setText("0.00");
            lblIvaCero.setText("0.00");
            lblSubtotal.setText("0.00");
        }
    }//GEN-LAST:event_txtImprimirActionPerformed

    public void comparaProducto() {
        if (txtCod.getText().isEmpty() || txtCod.getText().equals("")) {
            //Do nothing
        } else {
            verificar();
            if (flag == true) {
                cargarProducto();
            }
            flag = true;
        }
        total();
    }
    private void txtCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            comparaProducto();
            txtCod.setText("");

        }
        txtCod.requestFocusInWindow();
    }//GEN-LAST:event_txtCodKeyPressed

    private void cmbTipCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipCompActionPerformed
        if (cmbTipComp.getSelectedItem().equals("Nota de Venta")) {
            try {
                txtImprimir.setText("Guardar");
                Image img = ImageIO.read(getClass().getResource(("/Recursos/save.png")));
                txtImprimir.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                txtImprimir.setText("Imprimir");
                Image img = ImageIO.read(getClass().getResource(("/Recursos/impresora.png")));
                txtImprimir.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmbTipCompActionPerformed

    private void tblVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentasKeyPressed
        int filaSeleccionada = tblVentas.getSelectedRow();
        int cantFilas = tblVentas.getRowCount();
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (filaSeleccionada >= 0) {
                int[] filasselec = tblVentas.getSelectedRows();
                for (int i = 0; i < filasselec.length; i++) {
                    modelo.removeRow(filaSeleccionada);
                    fila = fila - 1;
                    if (fila == 0) {
                        txtTotal.setText("0.00");
                        lblSubtotal.setText("0.00");
                        lblIva.setText("0.00");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
        }
        total();
    }//GEN-LAST:event_tblVentasKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BusqClienteDialgo busq = new BusqClienteDialgo(this, true);
        busq.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedFocusGained
        if (txtCed.getText().equals("1111111111111")) {
            txtCed.setText("");
            txtCliente.setText(" ");
        }
    }//GEN-LAST:event_txtCedFocusGained

    private void txtCedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (!txtCed.getText().isEmpty())) {
            cargaCliente();
        }
    }//GEN-LAST:event_txtCedKeyReleased

    private void tblVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentasKeyReleased
        int filaSeleccionada = tblVentas.getSelectedRow();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (filaSeleccionada >= 0) {
                    double precioUnit = (Double) tblVentas.getValueAt(filaSeleccionada, 2);
                    String newCantidad = tblVentas.getValueAt(filaSeleccionada, 0).toString();
                    int newCant = Integer.parseInt(newCantidad);
                    double iva0 = (Double) tblVentas.getValueAt(filaSeleccionada, 8);
                    if (newCant >= cantProd2) {
                        modelo.setValueAt(cantInicial, filaSeleccionada, 0);
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada exede\n"
                                + "la cantidad disponible");
                        total();
                    } else {
                        double soloIva = (Double) modelo.getValueAt(filaSeleccionada, 3);
                        double newPrecioTot = newCant * precioUnit;
                        double nuevoIva = newCant * soloIva;
                        double nuevoPrecioIva = soloIva + precioUnit;
                        double nuevoIva0 = newCant * iva0;
                        modelo.setValueAt(newCant, filaSeleccionada, 0);
                        modelo.setValueAt(newPrecioTot, filaSeleccionada, 4);
                        modelo.setValueAt(nuevoIva, filaSeleccionada, 6);
                        modelo.setValueAt(nuevoPrecioIva, filaSeleccionada, 7);
                        modelo.setValueAt(nuevoIva0, filaSeleccionada, 8);
                        total();
                        txtCod.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleecione un producto");
                }
            } catch (NumberFormatException ex) {
                modelo.setValueAt(cantInicial, filaSeleccionada, 0);
            }

        }

    }//GEN-LAST:event_tblVentasKeyReleased

    private void txtCedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedFocusLost
        if (txtCed.getText().isEmpty()) {
            txtCed.setText("1111111111111");
            txtCliente.setText("CONSUMIDOR FINAL");
        }
    }//GEN-LAST:event_txtCedFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal objP = new Principal();
        this.dispose();
        objP.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void tblVentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentasKeyTyped

    }//GEN-LAST:event_tblVentasKeyTyped

    private void txtBusqProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqProdKeyReleased
        try {
            limpiarLista();
            String busq = txtBusqProd.getText();
            ArrayList<Producto> listaProd = manejadorProd.ConsultarPorNombre(busq);
            for (Producto objProducto : listaProd) {
                listaProductos.addElement(objProducto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBusqProdKeyReleased

    private void txtBusqProdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBusqProdFocusGained
        txtBusqProd.setText("");
    }//GEN-LAST:event_txtBusqProdFocusGained

    private void txtCedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0') || (c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedKeyTyped

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

    private void listaBusqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaBusqMouseClicked
        if ((evt.getClickCount() == 2) && (listaBusq.isSelectionEmpty() == false)) {
            String auxCod = listaBusq.getSelectedValue().getStrCod();
            txtCod.setText(auxCod);
            comparaProducto();
            txtCod.setText("");
        }
        txtCod.requestFocus();
    }//GEN-LAST:event_listaBusqMouseClicked

    private void limpiarLista() {
        listaProductos.clear();
    }

    public void cargarProducto() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
        double totProd;
        double aux;
        double aux2 = 0.00;
        String strAux = "";
        double ivaInit = 0.00;
        cantInicial = 1;
        double dblIva = 0;
        String precioMenos = "";
        String Stringaux = "";
        String dato = txtCod.getText();
        ArrayList<Producto> listadoProducto = manejadorProd.cargaProductoFact(dato);
        int cantLista = listadoProducto.size();
        if (cantLista == 0) {
            JOptionPane.showMessageDialog(null, "No se ha agregado el producto a la venta debido a\n"
                    + "que no existe el producto o no existen mas productos \n"
                    + "en el almacén");
        } else {
            crearFilas();
            if (!rbtnPrecio2.isSelected()) {
                for (int i = 0; i < cantLista; i++) {
                    producto = listadoProducto.get(i);
                    String prod = producto.getStrNombreProd();
                    double precio = producto.getFltPrecio();
                    double precioMayor = producto.getFltPrecioMayor();
                    String codigo = producto.getStrCod();
                    String iva = producto.getIva();
                    if (iva.equals("12%")) {
                        double defecto = 0.00;
                        ivaInit = precio / 1.12;
                        aux = ivaInit;
                        ivaInit = precio - ivaInit;
                        strAux = dcmlCambio.format(ivaInit);
                        dblIva = Double.parseDouble(strAux);
                        modelo.setValueAt(dblIva, fila, 6);
                        modelo.setValueAt(defecto, fila, 8);
                        aux2 = precio - dblIva;
                        precioMenos = dcmlCambio.format(aux2);
                        double dblPrecioMenos = Double.parseDouble(precioMenos);
                        modelo.setValueAt(dblPrecioMenos, fila, 7);
                        aux2 = dblPrecioMenos;
                        modelo.setValueAt(dblIva, fila, 3);
                    } else {
                        double defectoNoIVA = 0.00;
                        modelo.setValueAt(defectoNoIVA, fila, 7);
                        modelo.setValueAt(precio, fila, 8);
                        aux2 = precio;
                    }

                    modelo.setValueAt(cantInicial, fila, 0);
                    modelo.setValueAt(prod, fila, 1);
                    modelo.setValueAt(aux2, fila, 2);

                    modelo.setValueAt(dblIva, fila, 6);
                    totProd = cantInicial * aux2;

                    String strPrec = dcmlCambio.format(totProd);
                    totProd = Double.parseDouble(strPrec);
                    modelo.setValueAt(totProd, fila, 4);
                    modelo.setValueAt(codigo, fila, 5);

                }
            } else {
                for (int i = 0; i < cantLista; i++) {
                    producto = listadoProducto.get(i);
                    String prod = producto.getStrNombreProd();
                    double precio = producto.getFltPrecio();
                    double precioMayor = producto.getFltPrecioMayor();
                    String codigo = producto.getStrCod();
                    String iva = producto.getIva();
                    if (iva.equals("12%")) {
                        double defecto = 0.00;
                        ivaInit = precioMayor / 1.12;
                        aux = ivaInit;
                        ivaInit = precioMayor - ivaInit;
                        strAux = dcmlCambio.format(ivaInit);
                        dblIva = Double.parseDouble(strAux);
                        modelo.setValueAt(dblIva, fila, 6);
                        modelo.setValueAt(defecto, fila, 8);
                        aux2 = precioMayor - dblIva;
                        precioMenos = dcmlCambio.format(aux2);
                        double dblPrecioMenos = Double.parseDouble(precioMenos);
                        modelo.setValueAt(dblPrecioMenos, fila, 7);
                        aux2 = dblPrecioMenos;
                        modelo.setValueAt(dblIva, fila, 3);
                    } else {
                        double defectoNoIVA = 0.00;
                        modelo.setValueAt(defectoNoIVA, fila, 7);
                        modelo.setValueAt(precio, fila, 8);
                        aux2 = precio;
                    }

                    modelo.setValueAt(cantInicial, fila, 0);
                    modelo.setValueAt(prod, fila, 1);
                    modelo.setValueAt(aux2, fila, 2);

                    modelo.setValueAt(dblIva, fila, 6);
                    totProd = cantInicial * aux2;

                    String strPrec = dcmlCambio.format(totProd);
                    totProd = Double.parseDouble(strPrec);
                    modelo.setValueAt(totProd, fila, 4);
                    modelo.setValueAt(codigo, fila, 5);

                }
            }
            fila++;
        }
    }

    public void crearFilas() {

        modelo.addRow(new Object[0]);

    }

    public void total() { //Para sumar el precio total y asignarlo a el textfield de total
        try {
            double total1 = 0.00;
            double totalIva = 0.00;
            double totalProd = 0.00;
            int totalRow = tblVentas.getRowCount();
            totalRow -= 1;
            for (int j = 0; j <= (totalRow); j++) {
                String strIva = tblVentas.getValueAt(j, 6).toString();
                String strProdIva = tblVentas.getValueAt(j, 4).toString();
                String strProdIvaO = tblVentas.getValueAt(j, 8).toString();

                double dblIva = Double.parseDouble(strIva);
                double dblProdIva = Double.parseDouble(strProdIva);
                double dblProdIvaO = Double.parseDouble(strProdIvaO);

                totalIva += dblIva;
                totalProd += dblProdIva;
                total1 += dblProdIvaO;

                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);

                String strTotalIva = dcmlCambio.format(totalIva);
                String strTotalProd = dcmlCambio.format(totalProd);
                String strTotalIvaO = dcmlCambio.format(total1);

                lblIvaCero.setText(strTotalIvaO);
                lblIva.setText(strTotalIva);
                lblSubtotal.setText(strTotalProd);

                double auxSub = Double.parseDouble(strTotalIva);
                double auxProd = Double.parseDouble(strTotalProd);
                double auxIvaCero = Double.parseDouble(strTotalIvaO);
                totalVenta = auxSub + auxProd + auxIvaCero;

                String strTotalLbl = dcmlCambio.format(totalVenta);

                txtTotal.setText(strTotalLbl);
            }
        } catch (NullPointerException ex) {

        }
    }
    boolean verif = true;

    public void verificar() {//Para actualizar la cantidad si se repite el producto
        int f = tblVentas.getRowCount();
        System.out.println("fila v1= " + f);
        try {
            for (int i = 0; i <= tblVentas.getRowCount(); i++) {
                System.out.println("fila v2= "+f);
                int p = tblVentas.getRowCount();
                System.out.println(p);
                String cod = (String) tblVentas.getValueAt(i, 5);
                double precio = (Double) tblVentas.getValueAt(i, 2);
                double dblIva = (Double) tblVentas.getValueAt(i, 3);
                //double dblPrecioIva = (Double) tblVentas.getValueAt(i, 7);
                double dblPrecioIvaO = (Double) tblVentas.getValueAt(i, 8);

                int cantidad = Integer.parseInt( tblVentas.getValueAt(i, 0).toString());
                System.out.println("cantidad "+cantidad);
                if (cod.equals(txtCod.getText())) {
                    int cant2 = cantidad + 1;
                    double precio2 = cant2 * precio;
                    double nuevo_iva = cant2 * dblIva;
                    double nuevoPrecioIva = cant2 * precio;
                    double nuevoIvaO = cant2 * dblPrecioIvaO;

                    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                    simbolos.setDecimalSeparator('.');
                    DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                    String strTotalLbl = dcmlCambio.format(precio2);
                    precio2 = Double.parseDouble(strTotalLbl);
                    String strIva = dcmlCambio.format(nuevo_iva);
                    double strIva2 = Double.parseDouble(strIva);
                    String putoIva = dcmlCambio.format(nuevoPrecioIva);
                    double putoIva2 = Double.parseDouble(putoIva);
                    System.out.println("cant2 "+cant2);
                    modelo.setValueAt(cant2, i, 0);
                    modelo.setValueAt(putoIva2, i, 4);
                    modelo.setValueAt(strIva2, i, 6);
                    //modelo.setValueAt(putoIva2, i, 7);
                    modelo.setValueAt(nuevoIvaO, i, 8);
                    flag = false;
                    verif = false;
                    total();
                    break;
                } else {
                    flag = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbPago;
    private javax.swing.JComboBox cmbTipComp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblIvaCero;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JList<Producto> listaBusq;
    private javax.swing.JRadioButton rbtnPrecio2;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBusqProd;
    public static final javax.swing.JTextField txtCed = new javax.swing.JTextField();
    public static final javax.swing.JLabel txtCliente = new javax.swing.JLabel();
    private javax.swing.JTextField txtCod;
    public static final javax.swing.JLabel txtDireccionCl = new javax.swing.JLabel();
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JButton txtImprimir;
    public static javax.swing.JLabel txtTelf;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
