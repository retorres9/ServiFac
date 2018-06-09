/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Configuracion;
import Clases.ExistenciasBodega;
import Clases.Producto;
import Clases.Usuario;
import Dat.DATConfiguracion;
import Dat.DATExistenciasBodega;
import Dat.DATMaterial;
import Dat.DATUsuario;
import static GUI.Inventario.setAnchoColumnas;
import static GUI.Inventario.setAnchoColumnas2;
import static GUI.Inventario.tblProd;
import Utilidades.Utilidades;
import java.awt.event.KeyEvent;
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

/**
 *
 * @author rober
 */
public class prueba extends javax.swing.JFrame {

    int fila = 0;
    String n;
    Producto producto;
    Usuario objU = new Usuario();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DATMaterial material;
    ExistenciasBodega existencias;
    DATExistenciasBodega manejadorExistencias;
    DATUsuario manejadorUsuario;
    Configuracion objConfig = new Configuracion();
    DATConfiguracion manejadorConf;
    String host;
    Utilidades util = new Utilidades();
    public prueba() {
        initComponents();
        iconos();
        material = new DATMaterial();
        manejadorUsuario = new DATUsuario();
        manejadorExistencias = new DATExistenciasBodega();
        manejadorConf = new DATConfiguracion();
        host = util.getPcName();
        insertarColumnas();
        encabezadoBodega();
        setAnchoColumnas();
        setAnchoColumnas2();
        CargaCombo();
//        this.txtPrecio.setEditable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        permisos();
        updateTabla();
        cargarTablaBodega();
//        btnActualizaPrecio.setText("");
        totalInventario();
    }
    
    public void iconos() {
//        btnActualizaPrecio.setVisible(false);
//        btnActualizaNombre.setVisible(false);
//        btnActualizaPrecioMayor.setVisible(false);
//        btnActualizaUbicacion.setVisible(false);
//        btnActualizaCantidad.setVisible(false);
//        btnActualizaCantidad.setToolTipText("Haga clic para agregar productos");
//        btnActualizaPrecio.setToolTipText("Haga clic para actualizar el precio");
//        btnActualizaNombre.setToolTipText("Haga clic para actualizar el precio por mayor");
//        btnActualizaPrecioMayor.setToolTipText("Haga clic para actualizar el nombre del producto");
//        btnActualizaUbicacion.setToolTipText("Haga clic para actualizar la ubicación");
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
        modelo.addColumn("Ganancia %");
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
        modelo2.addColumn("Ganancia %");
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
//                jmiElimProd.setEnabled(false);
//                jmiElimCliente.setEnabled(false);
//                jmiElimProv.setEnabled(false);
//                jmConfig.setEnabled(false);
//                btnActualizaPrecio.setVisible(false);
//                btnActualizaNombre.setVisible(false);
//                btnActualizaPrecioMayor.setVisible(false);
//                btnActualizaUbicacion.setVisible(false);
//                btnActualizaCantidad.setVisible(false);
//                lblMenos.setVisible(false);
            }
            if (rol == 1) {
//                jmiElimProd.setEnabled(true);
//                jmiElimCliente.setEnabled(true);
//                jmiElimProv.setEnabled(true);
//                jmConfig.setEnabled(true);
//                if (txtPrecio.getText().isEmpty()) {
//                    btnActualizaPrecio.setVisible(false);
//                    btnActualizaNombre.setVisible(false);
//                    btnActualizaPrecioMayor.setVisible(false);
//                    btnActualizaUbicacion.setVisible(false);
//                    btnActualizaCantidad.setVisible(false);
//                    lblMenos.setVisible(false);
//                } else {
//                    btnActualizaPrecio.setVisible(true);
//                    btnActualizaNombre.setVisible(true);
//                    btnActualizaPrecioMayor.setVisible(true);
//                    btnActualizaUbicacion.setVisible(true);
//                    btnActualizaCantidad.setVisible(true);
//                    lblMenos.setVisible(true);
//                }
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
            }
//            txtCantidad.setText("----------");
//            txtUbicacion.setText("----------");
//            txtPrecio.setText("");
            txtNombreProd.setText("----------");
//            txtPrecioM.setText("----------");
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmpresa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtNombreProd = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBodega = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cmbBusq = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEmpresa.setFont(new java.awt.Font("Roboto Cn", 1, 36)); // NOI18N
        txtEmpresa.setText("Nombre Empresa");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/lupa.png"))); // NOI18N

        lblTotal.setText("Total del inventario:");

        jTabbedPane1.setFocusable(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Editar.png"))); // NOI18N
        jButton3.setText("Editar");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/binoculares.png"))); // NOI18N
        jButton4.setText("Ver");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/mozo-de-carga.png"))); // NOI18N
        jButton5.setText("Mover");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        txtNombreProd.setText("----------");
        txtNombreProd.setAutoscrolls(true);
        txtNombreProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreProd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        txtNombreProd.setMaximumSize(new java.awt.Dimension(150, 16));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nombre del Producto:");

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
        tblProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblProdKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblProd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Productos en almacén", jPanel2);

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
                .addContainerGap(747, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(489, 489, 489))
        );

        jTabbedPane1.addTab("Productos en bodega", jPanel3);

        cmbBusq.setFocusable(false);

        jLabel6.setText("Buscar por:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(100, 100, 100)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(lblTotal))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel6))
                        .addComponent(cmbBusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBodegaKeyTyped
        char c = evt.getKeyChar();
        if (c > '9' || c < '0') {
            evt.consume();
        }
    }//GEN-LAST:event_txtBodegaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        if (!(txtBodega.getText().isEmpty()) && (tblBodega.getSelectedRowCount() == 1)) {
//            int n = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea mover " + txtBodega.getText() + "\nunidad/es al almacén?", "Aviso", JOptionPane.YES_NO_OPTION);
//            if (n == JOptionPane.YES_OPTION) {
////                moverBodega();
//            } else {
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto e ingresar una\n cantidad para mover de bodega");
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pruebactual prueb = new pruebactual(this, true);
        prueb.txtNombreProd.setText(this.txtNombreProd.getText());
        prueb.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdMouseClicked
        int fila1 = tblProd.rowAtPoint(evt.getPoint());
        try {
            String prod = (String) tblProd.getModel().getValueAt(fila1, 0);//Seleccionamos el nombre del producto
            txtNombreProd.setText(prod);
            String precio = tblProd.getModel().getValueAt(fila1, 3).toString();//Seleccionamos el precio del producto
//            txtPrecio.setText(precio);
            String precioMayor = tblProd.getModel().getValueAt(fila1, 4).toString();//Seleccionamos el precio al por mayor del producto
//            txtPrecioM.setText(precioMayor);
            String ubicacion = tblProd.getModel().getValueAt(fila1, 7).toString();//Seleccionamos la ubicacion del producto
//            txtUbicacion.setText(ubicacion);
            String cant = tblProd.getModel().getValueAt(fila1, 8).toString();//Seleccionamos la cantidad del producto
//            txtCantidad.setText(cant);
            permisos();

        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }//GEN-LAST:event_tblProdMouseClicked

    private void tblProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdKeyPressed
        char c = evt.getKeyChar();
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            System.out.println("hola");
            fila = tblProd.getSelectedRow();
            String prod = (String) tblProd.getModel().getValueAt(fila, 0);//Seleccionamos el nombre del producto
            txtNombreProd.setText(prod);
            String precio = tblProd.getModel().getValueAt(fila, 2).toString();//Seleccionamos el precio del producto
//            txtPrecio.setText(precio);
            String precioMayor = tblProd.getModel().getValueAt(fila, 3).toString();//Seleccionamos el precio al por mayor del producto
//            txtPrecioM.setText(precioMayor);
            String ubicacion = tblProd.getModel().getValueAt(fila, 7).toString();//Seleccionamos la ubicacion del producto
//            txtUbicacion.setText(ubicacion);
            String cant = (String) tblProd.getModel().getValueAt(fila, 8).toString();//Seleccionamos la cantidad del producto
//            txtCantidad.setText(cant);
        }
    }//GEN-LAST:event_tblProdKeyPressed

    private void tblProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProdKeyTyped
        //        char c = evt.getKeyChar();
        //        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            //            System.out.println("hola");
            //            fila = tblProd.getSelectedRow();
            //            String prod = (String) tblProd.getModel().getValueAt(fila, 0);//Seleccionamos el nombre del producto
            //            txtNombreProd.setText(prod);
            //            String precio = tblProd.getModel().getValueAt(fila, 2).toString();//Seleccionamos el precio del producto
            //            txtPrecio.setText(precio);
            //            String precioMayor = tblProd.getModel().getValueAt(fila, 3).toString();//Seleccionamos el precio al por mayor del producto
            //            txtPrecioM.setText(precioMayor);
            //            String ubicacion = tblProd.getModel().getValueAt(fila, 7).toString();//Seleccionamos la ubicacion del producto
            //            txtUbicacion.setText(ubicacion);
            //            String cant = (String) tblProd.getModel().getValueAt(fila, 8).toString();//Seleccionamos la cantidad del producto
            //            txtCantidad.setText(cant);
            //        }
    }//GEN-LAST:event_tblProdKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTotal;
    public static javax.swing.JTable tblProd;
    private javax.swing.JTextField txtBodega;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtNombreProd;
    // End of variables declaration//GEN-END:variables
}
