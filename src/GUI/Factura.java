package GUI;

import Clases.Clientes;
import Clases.Configuracion;
import Clases.Producto;
import Clases.DetalleVenta;
import Clases.NumeroLetras;
import Clases.Venta;
import Dat.DATClientes;
import Dat.DATMaterial;
import Dat.DATReporte;
import Dat.DATVenta;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class Factura extends javax.swing.JFrame {

    double deuda;
    int fila;
    int cont;
    int cantProd2;
    int filasSelec;
    int cantInicial;
    boolean flag = true;
    boolean banderaFila = true;
    String n, vendedor;
    Clientes cliente = new Clientes();
    DetalleVenta detalle = new DetalleVenta();
    Producto producto = new Producto();
    Venta venta = new Venta();
    DATVenta manejadorVenta;
    DefaultTableModel modelo = new DefaultTableModel();
    DATMaterial manejadorProd;
    DATClientes manejadorCliente;
    DATReporte manejadorDetalle;

    public Factura() {
        initComponents();
        cargarEncabezado();
        manejadorProd = new DATMaterial();
        manejadorCliente = new DATClientes();
        manejadorVenta = new DATVenta();
        manejadorDetalle = new DATReporte();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        setAnchoColumnas();
        contador();
        permisos();
        txtEmpresa.setText(Constantes.Constantes.NOMBRE_EMPRESA);
        txtCod.requestFocus();
    }

    public void cargarEncabezado() {
        modelo.addColumn("Cant.");
        modelo.addColumn("Descripcion");
        modelo.addColumn("P/ Unit");
        modelo.addColumn("P/ Mayor");
        modelo.addColumn("P/ Total");
        modelo.addColumn("Codigo");
    }

    public void contador() {
        ArrayList<Venta> cantProducto = manejadorVenta.CuentaVentas();
        int cant = cantProducto.size();
        for (int i = 0; i < cant; i++) {
            venta = cantProducto.get(i);
            cont = venta.getIntIdVenta();
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
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (25 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    void imprimirFactura() {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(85.85);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 60);

        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        //printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextWrap(1, 2, 30, 60, "FACTURA DE VENTA");
        //printer.printTextWrap(linI, linE, colI, colE, null);
        //printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + txtVentaNumeroFactura.getText());
        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + getFecha());
        printer.printTextWrap(2, 3, 40, 60, "Hora: 12:22:51");
        printer.printTextWrap(3, 3, 1, 60, "Vendedor.  : " + vendedor);
        printer.printTextWrap(4, 4, 1, 60, "CLIENTE: " + txtCliente.getText());
        printer.printTextWrap(5, 5, 1, 60, "RUC/CI.: " + txtCed.getText());
        printer.printTextWrap(6, 6, 1, 60, "DIRECCION: " + txtDireccionCl.getText());
        //printer.printCharAtCol(7, 1, 80, "=");
        //printer.printTextWrap(7, 7, 1, 60, "Cant.      Descripcion                  P  P.Unit.      P.Total");
        //printer.printCharAtCol(9, 1, 80, "-");
        int filas = tblVentas.getRowCount();
        System.out.println(filas);
        for (int i = 0; i < filas; i++) {
            printer.printTextWrap(9 + i, 10, 1, 60, tblVentas.getValueAt(i, 0).toString());
            printer.printTextWrap(9 + i, 10, 10, 60, tblVentas.getValueAt(i, 1).toString());
            printer.printTextWrap(9 + i, 10, 30, 60, tblVentas.getValueAt(i, 2).toString());
            printer.printTextWrap(9 + i, 10, 40, 60, tblVentas.getValueAt(i, 3).toString());
            //printer.printTextWrap(9 + i, 10, 50, 60, tblVentas.getValueAt(i, 4).toString());
            System.out.println(tblVentas.getValueAt(i, 0));
        }

        if (filas > 15) {
            //printer.printCharAtCol(filas + 1, 1, 80, "=");
            printer.printTextWrap(filas + 1, filas + 2, 1, 60, "TOTAL A PAGAR " + txtTotal.getText());
            //printer.printCharAtCol(filas + 2, 1, 80, "=");
            printer.printTextWrap(filas + 2, filas + 3, 1, 60, txtTotalLetras.getText());
        } else {
            //printer.printCharAtCol(25, 1, 80, "=");
            printer.printTextWrap(26, 26, 1, 60, "TOTAL A PAGAR " + txtTotal.getText());
            //printer.printCharAtCol(27, 1, 80, "=");
            printer.printTextWrap(27, 28, 1, 60, txtTotalLetras.getText());

        }
        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (inputStream == null) {
            //System.out.println("bandera");
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            //System.out.println("bandera");
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
                System.out.println("bandera");
            } catch (Exception ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public void ventaProd() {
        vendedor = Configuracion.vendedor_venta();
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            int cantExist = 0;
            int cantVenta = (int) tblVentas.getValueAt(i, 0);
            String descripcionVenta = (String) tblVentas.getValueAt(i, 1);
            String strCodigo = (String) tblVentas.getValueAt(i, 5);
            String strPrecio = tblVentas.getValueAt(i, 2).toString();
            double precio_Venta = Double.parseDouble(strPrecio);
            ArrayList<Producto> cantProd = manejadorProd.comprobarCant(descripcionVenta);
            int intCantProd = cantProd.size();
            for (int j = 0; j < intCantProd; j++) {
                producto = cantProd.get(i);
                cantExist = producto.getIntCantidad();
            }
            cantExist = cantExist - cantVenta;
            producto = new Producto(descripcionVenta, cantExist);
            manejadorProd.UpdateCantFactura(producto);
            String cedula = txtCed.getText();
            detalle = new DetalleVenta(cedula, cantVenta, strCodigo, precio_Venta, vendedor, cont);
            manejadorDetalle.creaReporte(detalle);
        }
        resetFact();
    }

    public void resetFact() {
        int a = tblVentas.getRowCount() - 1;
        for (int j = a; tblVentas.getRowCount() > 0; j--) {
            modelo.removeRow(j);
            fila = fila - 1;
        }
    }

    public void venta() {
        try {
            double num, numTotal;
            String strNum = JOptionPane.showInputDialog(null, "Ingrese el monto recibido");
            num = Double.parseDouble(strNum);
            numTotal = Double.parseDouble(txtTotal.getText());
            double cambio = num - numTotal;
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
            String decimal = dcmlCambio.format(cambio);
            double dblDecimal = Double.parseDouble(decimal);
            dblDecimal = Math.abs(dblDecimal);
            double EPSILON = 0.0000001;
            System.out.println(dblDecimal);
            if (dblDecimal < EPSILON) {
                JOptionPane.showMessageDialog(null, "<html><h1>No hay cambio</h1></html>");
            } else if ((dblDecimal > 0) && (num > numTotal)) {
                JOptionPane.showMessageDialog(null, "<html><h1>El cambio es: " + dblDecimal + "</h1></html>");
            } else {
                JOptionPane.showMessageDialog(null, "<html><h1>El cliente debe: " + dblDecimal + "</h1></html>");
                double nuevaDeuda = deuda + dblDecimal;
                cliente = new Clientes(nuevaDeuda, txtCed.getText());
                manejadorCliente.agregarDeuda(cliente);
            }
            venta = new Venta(cont, numTotal, num, getFecha(), txtCed.getText());//OJO
            manejadorVenta.crearVenta(venta);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
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
        jComboBox2 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtDireccionCl = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCed = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTotalLetras = new javax.swing.JTextArea();
        txtUsuario = new javax.swing.JLabel();
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
        setMinimumSize(new java.awt.Dimension(1120, 410));
        setResizable(false);

        tblVentas.setModel(modelo);
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
        jLabel8.setText("Tipo de Venta:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito" }));
        jComboBox2.setFocusable(false);

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
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto Condensed", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        txtDireccionCl.setText(" ");

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
        });
        txtCed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedKeyReleased(evt);
            }
        });

        jButton2.setText("Buscar por nombre");
        jButton2.setToolTipText("Presione para buscar por nombre");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtCliente.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDireccionCl, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jButton2)
                    .addComponent(txtCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDireccionCl)))
        );

        jLabel1.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel1.setText("Subtotal:");

        jLabel3.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel3.setText("IVA 12%:");

        jLabel5.setFont(new java.awt.Font("Roboto Condensed Light", 1, 13)); // NOI18N
        jLabel5.setText("TOTAL:");

        jLabel6.setText("0.00");

        jLabel7.setText("0.00");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 204, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTotal.setText("0.00");

        txtTotalLetras.setEditable(false);
        txtTotalLetras.setColumns(20);
        txtTotalLetras.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTotalLetras.setRows(5);
        txtTotalLetras.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtTotalLetras);

        txtUsuario.setText("Usuario=====");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsuario)
                .addGap(102, 102, 102)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(80, 80, 80))
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jButton1)
                                .addGap(375, 375, 375)
                                .addComponent(txtImprimir)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(txtImprimir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void getCant() {
        int numFilas = tblVentas.getSelectedRow();
        if (numFilas >= 0) {
            String filaDesc = (String) tblVentas.getValueAt(numFilas, 1);
            cantInicial = (int) tblVentas.getValueAt(numFilas, 0);
            ArrayList<Producto> datos = manejadorProd.comprobarCant(filaDesc);
            int cantDatos = datos.size();
            for (int i = 0; i < cantDatos; i++) {
                producto = datos.get(i);
                int cant = producto.getIntCantidad();
                System.out.println(cant);
                cantProd2 = cant;
                System.out.println(cantProd2);
            }
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
            txtCliente.setText(nombre);
            txtDireccionCl.setText(dir);
            deuda = cliente.getDblDeuda();
        }
        if (txtCliente.getText().equals(" ")) {
            int opc = JOptionPane.showConfirmDialog(null, "Cliente no registrado\n¿Desea ingrear un nuevo Cliente?", "Aviso!", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                NuevoClienteDialog clienteDialogo = new NuevoClienteDialog(this, true);
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
        cargaCliente();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImprimirActionPerformed
        if (cmbTipComp.getSelectedItem().equals("Nota de Venta")) {
            venta();
            ventaProd();
            //imprimirFactura();
            this.contador();
        } else {
            venta();
            //imprimirFactura();
            ventaProd();
            this.contador();
        }
    }//GEN-LAST:event_txtImprimirActionPerformed

    public void comparaProducto() {
        if (txtCod.getText().isEmpty() || txtCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "bandera");
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
            System.out.println(txtCod.getText());
            comparaProducto();
            txtCod.setText("");
        }
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
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            if (filaSeleccionada >= 0) {
                int[] filasselec = tblVentas.getSelectedRows();
                for (int i = 0; i < filasselec.length; i++) {
                    modelo.removeRow(filaSeleccionada);
                    fila = fila - 1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
        }
    }//GEN-LAST:event_tblVentasKeyPressed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BusqClienteDialgo busq = new BusqClienteDialgo(this, true);
        busq.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedFocusGained
        if (txtCed.getText().equals("1111111111111")) {
            txtCed.setText("");
        }
    }//GEN-LAST:event_txtCedFocusGained

    private void txtCedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargaCliente();
        }
    }//GEN-LAST:event_txtCedKeyReleased

    private void tblVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentasKeyReleased
        int filaSeleccionada = tblVentas.getSelectedRow();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (filaSeleccionada >= 0) {
                    System.out.println("band");
                    double precioUnit = (Double) tblVentas.getValueAt(filaSeleccionada, 2);
                    String newCantidad = tblVentas.getValueAt(filaSeleccionada, 0).toString();
                    int newCant = Integer.parseInt(newCantidad);
                    if (newCant >= cantProd2) {
                        modelo.setValueAt(cantInicial, filaSeleccionada, 0);
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada exede\n"
                                + "la cantidad disponible");
                        total();
                    } else {
                        double newPrecioTot = newCant * precioUnit;
                        modelo.setValueAt(newCant, filaSeleccionada, 0);
                        modelo.setValueAt(newPrecioTot, filaSeleccionada, 4);
                        total();
                    }
                } else {
                    
                }
            } catch (NumberFormatException ex) {
                modelo.setValueAt(cantInicial, filaSeleccionada, 0);
            }
            
        }
    }//GEN-LAST:event_tblVentasKeyReleased

    public void cargarProducto() {

        double totProd;
        int n = 1;
        String dato = txtCod.getText();
        ArrayList<Producto> listadoProducto = manejadorProd.cargaProductoFact(dato);
        int cantLista = listadoProducto.size();
        if (cantLista == 0) {

        } else {
            crearFilas();
            for (int i = 0; i < cantLista; i++) {
                producto = listadoProducto.get(i);
                String prod = producto.getStrNombreProd();
                double precio = producto.getFltPrecio();
                double precioMayor = producto.getFltPrecioMayor();
                String codigo = producto.getStrCod();

                modelo.setValueAt(n, fila, 0);
                modelo.setValueAt(prod, fila, 1);
                modelo.setValueAt(precio, fila, 2);
                modelo.setValueAt(precioMayor, fila, 3);
                totProd = n * precio;
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                String strPrec = dcmlCambio.format(totProd);
                modelo.setValueAt(strPrec, fila, 4);
                modelo.setValueAt(codigo, fila, 5);
            }
            fila++;
        }

    }

    public void crearFilas() {

        modelo.addRow(new Object[0]);

    }

    public void total() { //Para sumar el precio total y asignarlo a el textfield de total
        try {
            double total1 = 0;
            int totalRow = tblVentas.getRowCount();
            totalRow -= 1;
            for (int j = 0; j <= (totalRow); j++) {
                String strTotal = (String) tblVentas.getValueAt(j, 4).toString();
                double total = Double.parseDouble(strTotal);
                total1 += total;
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                String strTotalLbl = dcmlCambio.format(total1);
                txtTotal.setText(strTotalLbl);
            }
            NumeroLetras numero = new NumeroLetras();
            this.txtTotal.getText();

            String number[] = txtTotal.getText().split("\\.");
            if (number.length == 2) {
                String entero = (number[0]);
                String decimal = (number[1]);
                if (number[0].equals("1")) {
                    txtTotalLetras.setText(numero.convertir(Integer.parseInt(entero)) + "DOLARES /" + decimal + "/100");
                } else {

                    txtTotalLetras.setText(numero.convertir(Integer.parseInt(entero)) + "DOLARES /" + decimal + "/100");
                }

            }
        } catch (NullPointerException ex) {

        }
    }
    boolean verif = true;

    public void verificar() {//Para actualizar la cantidad si se repite el producto
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            String cod = (String) tblVentas.getValueAt(i, 5);
            double precio = (Double) tblVentas.getValueAt(i, 2);
            int cant = (int) tblVentas.getValueAt(i, 0);
            if (cod.equals(txtCod.getText())) {
                int cant2 = cant + 1;
                double precio2 = cant2 * precio;
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                String strTotalLbl = dcmlCambio.format(precio2);
                precio2 = Double.parseDouble(strTotalLbl);
                modelo.setValueAt(cant2, i, 0);
                modelo.setValueAt(precio2, i, 4);
                flag = false;
                verif = false;
                total();
                break;
            } else {
                flag = true;
            }
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
            java.util.logging.Logger.getLogger(BusqProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox cmbTipComp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProd;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JTable tblVentas;
    public static javax.swing.JTextField txtCed;
    public static javax.swing.JLabel txtCliente;
    private javax.swing.JTextField txtCod;
    public static javax.swing.JLabel txtDireccionCl;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JButton txtImprimir;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextArea txtTotalLetras;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
