package GUI;

import BL.BLClientes;
import BL.BLMaterial;
import BL.BLReporte;
import BL.BLVenta;
import Clases.Clientes;
import Clases.Configuracion;
import Clases.Producto;
import Clases.DetalleVenta;
import Clases.NumeroLetras;
import Clases.Venta;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.swing.Icon;
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
    String n, vendedor;
    BLClientes objC = new BLClientes();
    Clientes manejadorCliente = new Clientes();
    BLReporte objR = new BLReporte();
    DetalleVenta manejadorReporte = new DetalleVenta();
    BLMaterial objM = new BLMaterial();
    Producto manejadorProducto = new Producto();
    Venta manejadorVenta = new Venta();
    BLVenta objV = new BLVenta();

    public Factura() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.nombrePrograma);
        this.setLocationRelativeTo(null);
        setAnchoColumnas();
        contador();
        permisos();
        txtEmpresa.setText(Constantes.Constantes.nombreEmpresa);
    }

    public void contador() {
        try {
            cont = objV.cuentaVentas();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
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
        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + getFechaActual());
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
        System.out.println(vendedor);
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            try {
                int cantExist = 0;
                int cantVenta = (int) tblVentas.getValueAt(i, 0);
                String descripcionVenta = (String) tblVentas.getValueAt(i, 1);
                String strCodigo = (String) tblVentas.getValueAt(i, 4);
                int codigo = Integer.parseInt(strCodigo);
                String strPrecio = (String) tblVentas.getValueAt(i, 2);
                double precio_Venta = Double.parseDouble(strPrecio);
                ArrayList<Object[]> datos = new ArrayList<>();
                datos = objM.comprobarCant(descripcionVenta);
                for (Object[] dato1 : datos) {
                    String cantProdObj = String.valueOf(dato1[0]);
                    cantExist = Integer.parseInt(cantProdObj);
                }
                cantExist = cantExist - cantVenta;
                manejadorProducto = new Producto(descripcionVenta, cantExist);
                objM.updateCantFactura(manejadorProducto);
                int cedula = Integer.parseInt(txtCed.getText());
                System.out.println(cont);
                manejadorReporte = new DetalleVenta(cedula, cantVenta, codigo, precio_Venta, vendedor, cont);
                objR.creaReport(manejadorReporte);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resetFact();
    }

    public void resetFact() {
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
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
            int usuario = Integer.parseInt(txtUsuario.getText());//cedula
            double EPSILON=0.0000001;
            if (Math.abs(dblDecimal)<EPSILON) {
                JOptionPane.showMessageDialog(null, "<html><h1>No hay cambio</h1></html>");
            } else if (dblDecimal > 0 && num > numTotal) {
                JOptionPane.showMessageDialog(null, "<html><h1>El cambio es: " + dblDecimal + "</h1></html>");
            } else {
                JOptionPane.showMessageDialog(null, "<html><h1>El cliente debe: " + dblDecimal + "</h1></html>");
                System.out.println(deuda);
                deuda = deuda + dblDecimal;
                System.out.println(deuda);
                manejadorCliente = new Clientes(deuda, txtCliente.getText());
                objC.InsertarDeuda(manejadorCliente);
            }
            manejadorVenta = new Venta(cont, numTotal, num, getFecha(), usuario);//OJO
            try {
                objV.crearVenta(manejadorVenta);
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
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
        txtImprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmbTipComp = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        txtCod = new javax.swing.JTextField();
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

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cant", "Descripción", "P/ Unit", "P/ Total", "Código"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        tblVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVentasKeyPressed(evt);
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

        txtImprimir.setText("Imprimir");
        txtImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImprimirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));
        jPanel1.setToolTipText("");

        cmbTipComp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Nota de Venta" }));
        cmbTipComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipCompActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo de Comprobante:");

        jLabel8.setText("Tipo de Venta:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Crédito" }));

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

        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodKeyPressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(51, 51, 51))); // NOI18N

        txtDireccionCl.setText(" ");

        jLabel15.setText("Direccion:");

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setText("Nombre:");

        jLabel11.setText("Cedula:");

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
                        .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(txtDireccionCl))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setText("Subtotal:");

        jLabel3.setText("IVA 12%:");

        jLabel5.setText("TOTAL:");

        jLabel6.setText("0.00");

        jLabel7.setText("0.00");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 204, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal.setText("0.00");

        txtTotalLetras.setColumns(20);
        txtTotalLetras.setRows(5);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(404, 404, 404))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(287, 287, 287)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jButton1)
                        .addGap(375, 375, 375)
                        .addComponent(txtImprimir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtImprimir))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void getCant() {
        int numFilas = tblVentas.getSelectedRow();
        if (numFilas >= 0) {
            try {
                String filaDesc = (String) tblVentas.getValueAt(numFilas, 1);
                cantInicial = (int) tblVentas.getValueAt(numFilas, 0);
                ArrayList<Object[]> datos = new ArrayList<>();
                datos = objM.comprobarCant(filaDesc);
                for (Object[] dato1 : datos) {
                    String cantProdObj = String.valueOf(dato1[0]);
                    cantProd2 = Integer.parseInt(cantProdObj);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
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

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    public void cargaCliente() {
        try {
            String nom = txtCed.getText();
            ArrayList<Object[]> datos = new ArrayList<>();
            datos = objC.ClienteFactura(nom);
            for (Object dato[] : datos) {
                String cli = String.valueOf(dato[0]);
                txtCliente.setText(cli);
                String dir = String.valueOf(dato[1]);
                txtDireccionCl.setText(dir);
                String strDeuda = String.valueOf(dato[2]);
                deuda = Double.parseDouble(strDeuda);
            }
            if (txtCliente.getText().equals(" ")) {
                int opc = JOptionPane.showConfirmDialog(null, "Cliente no registrado\n¿Desea ingrear un nuevo Cliente?", "Aviso!", JOptionPane.YES_NO_OPTION);
                if (opc == JOptionPane.YES_OPTION) {
                    NewCliente objCl = new NewCliente();
                    NewCliente.valid = 0;
                    objCl.setVisible(true);
                } else {

                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
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
            imprimirFactura();
            ventaProd();
            this.contador();
        }
    }//GEN-LAST:event_txtImprimirActionPerformed

    public void comparaProducto() {
        try {
            String dato = txtCod.getText();
            ArrayList<Object[]> datos = new ArrayList<>();
            datos = objM.venta(dato);
            String compare = "";
            for (Object[] dato1 : datos) {
                compare = String.valueOf(dato1[2]);
            }
            if (txtCod.getText().isEmpty() || !txtCod.getText().equals(compare)) {
            } else {
                verificar();
                if (flag == true) {
                    crearFilas();
                    cargarProducto();
                }
                total();
                flag = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void txtCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comparaProducto();
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
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
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

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codTabla = (String) tblVentas.getValueAt(filaSeleccionada, 4);
            String comparacion = txtCod.getText();
            if (filaSeleccionada >= 0 && comparacion.equals(codTabla)) {
                String precioUnit = (String) tblVentas.getValueAt(filaSeleccionada, 2);
                double precioUni = Double.parseDouble(precioUnit);
                String newCantidad = (String) tblVentas.getValueAt(filaSeleccionada, 0);
                int newCant = Integer.parseInt(newCantidad);
                if (newCant >= cantProd2) {
                    modelo.setValueAt(cantInicial, filaSeleccionada, 0);
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada exede\n"
                            + "la cantidad disponible");
                    total();
                } else {
                    //System.out.println(cantProd2 + "<2" + newCant);
                    double newPrecioTot = newCant * precioUni;
                    modelo.setValueAt(newCant, filaSeleccionada, 0);
                    modelo.setValueAt(newPrecioTot, filaSeleccionada, 3);
                    total();
                }
            } else {

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
        BusquedaNombre objBusqNom = new BusquedaNombre();
        objBusqNom.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public void cargarProducto() {
        try {
            String dato = txtCod.getText();
            DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
            double totProd;
            int n = 1;
            String compare = null;
            ArrayList<Object[]> datos = new ArrayList<>();
            datos = objM.venta(dato);
            for (Object[] dato1 : datos) {
                compare = String.valueOf(dato1[2]);
            }
            if (!dato.equals(compare)) {

            } else {
                for (Object[] dato1 : datos) {
                    modelo.setValueAt(n, fila, 0);
                    String prod = String.valueOf(dato1[0]);
                    modelo.setValueAt(prod, fila, 1);
                    String prec = String.valueOf(dato1[1]);
                    modelo.setValueAt(prec, fila, 2);
                    double precio = Double.parseDouble(prec);
                    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                    simbolos.setDecimalSeparator('.');
                    DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
                    String strPrec = dcmlCambio.format(precio);
                    totProd = n * precio;
                    modelo.setValueAt(strPrec, fila, 3);
                    String strCod = String.valueOf(dato1[2]);
                    modelo.setValueAt(strCod, fila, 4);
                }
                fila++;
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }

    }

    public void crearFilas() {
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();

        modelo.addRow(new Object[0]);
    }

    public void total() {
        double total1 = 0;
        int totalRow = tblVentas.getRowCount();
        totalRow -= 1;
        for (int j = 0; j <= (totalRow); j++) {
            String strTotal = (String) tblVentas.getValueAt(j, 3).toString();
            double total = Double.parseDouble(strTotal);
            total1 += total;
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat dcmlCambio = new DecimalFormat("0.00", simbolos);
            String strTotalLbl = dcmlCambio.format(total1);
            txtTotal.setText(strTotalLbl);
        }
        System.out.println("Tot " + txtTotal.getText());
        NumeroLetras numero = new NumeroLetras();
        this.txtTotal.getText();

        String number[] = txtTotal.getText().split("\\.");
        if (number.length == 2) {
            String entero = (number[0]);
            System.out.println("int " + entero);
            String decimal = (number[1]);
            System.out.println("dbl " + decimal);
            if (number[0].equals("1")) {
                txtTotalLetras.setText(numero.convertir(Integer.parseInt(entero)) + "DOLAR CON" + numero.convertir(Integer.parseInt(decimal)) + "CENTAVOS");
            } else {

                txtTotalLetras.setText(numero.convertir(Integer.parseInt(entero)) + "DOLARES CON" + numero.convertir(Integer.parseInt(decimal)) + "CENTAVOS");
                System.out.println("dbl " + decimal);
            }

        }
    }

    public void verificar() {
        DefaultTableModel modelo = (DefaultTableModel) tblVentas.getModel();
        for (int i = 0; i < tblVentas.getRowCount(); i++) {
            String cod = (String) tblVentas.getValueAt(i, 4);
            String pre = (String) tblVentas.getValueAt(i, 2);
            double precio = Double.parseDouble(pre);
            int cant = (int) tblVentas.getValueAt(i, 0);

            if (cod.equals(txtCod.getText())) {
                int cant2 = cant + 1;
                double precio2 = cant2 * precio;
                modelo.setValueAt(cant2, i, 0);
                modelo.setValueAt(precio2, i, 3);
                flag = false;
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
