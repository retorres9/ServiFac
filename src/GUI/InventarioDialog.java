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
import Utilidades.Utilidades;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author rober
 */
public class InventarioDialog extends javax.swing.JDialog {

    int fila;
    String n;
    Producto producto;
    Usuario objU = new Usuario();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DATMaterial material;
    DATUsuario manejadorUsuario;
    DATConfiguracion manejadorConf;
    Configuracion objConfig = new Configuracion();
    ExistenciasBodega existencias;
    DATExistenciasBodega manejadorExistencias;
    Configuracion config = new Configuracion();
    String nombreEmp;
    Utilidades util = new Utilidades();
    String host;

    public InventarioDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        host = util.getPcName();
        manejadorConf = new DATConfiguracion();
        manejadorUsuario = new DATUsuario();
        JOptionPane.showMessageDialog(null, "En la siguiente ventana solo podrá actualizar\n"
                + "el producto que ya se encuentra agregado");
        this.txtBuscar.setEditable(false);
        material = new DATMaterial();
        manejadorExistencias = new DATExistenciasBodega();
        CargaCombo();
        cargConfig();
        insertarColumnas();
        setAnchoColumnas();
        this.setLocationRelativeTo(null);
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);

    }

    public void CargaCombo() {
        cmbBusq.addItem("Codigo");
    }

    public void cargConfig() {
        ArrayList<Configuracion> configuracion = manejadorConf.cargaConfig();
        int cantConf = configuracion.size();
        for (int i = 0; i < cantConf; i++) {
            config = configuracion.get(i);
            nombreEmp = config.getEmpresa();
            txtEmpresa.setText(nombreEmp);
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

    public void permisos() {

        ArrayList<Usuario> credencial = manejadorUsuario.obtenerUserLog(host);
        int cantCred = credencial.size();
        int rol = 0;
        for (int i = 0; i < cantCred; i++) {
            objU = credencial.get(i);
            if (rol == 0) {
                btnActualizaPrecio.setVisible(false);
                btnActualizaNombre.setVisible(false);
                btnActualizaPrecioMayor.setVisible(false);
                btnActualizaUbicacion.setVisible(false);
                btnActualizaCantidad.setVisible(false);
                lblMenos.setVisible(false);
            }
            if (rol == 1) {
                btnActualizaPrecio.setVisible(true);
                btnActualizaNombre.setVisible(true);
                btnActualizaPrecioMayor.setVisible(true);
                btnActualizaUbicacion.setVisible(true);
                btnActualizaCantidad.setVisible(true);
                lblMenos.setVisible(true);
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

    public void busqueda() {
        String dato = txtBuscar.getText();
        setAnchoColumnas();
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
        lblMenos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        cmbBusq.setFocusable(false);

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
        txtPrecio.setFocusable(false);

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

        lblMenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/menos.png"))); // NOI18N
        lblMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenosMouseClicked(evt);
            }
        });

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
                        .addComponent(btnActualizaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMenos))
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
                            .addComponent(btnActualizaCantidad)
                            .addComponent(lblMenos))
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
        tblProd.setFocusable(false);
        tblProd.getTableHeader().setReorderingAllowed(false);
        tblProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProd);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos en almacén", jPanel2);

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
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jButton1)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

    }//GEN-LAST:event_txtBuscarKeyReleased

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
            //updateTabla();

        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se ha cambiado el precio de:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaPrecioMouseClicked

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
            //updateTabla();
        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se ha cambiado el precio por mayor de:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaPrecioMayorMouseClicked

    private void btnActualizaNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaNombreMouseClicked
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de:\n" + txtNombre.getText()).toUpperCase();
            String cod = (String) tblProd.getModel().getValueAt(fila, 1);
            producto = new Producto(nombre, Double.parseDouble(txtPrecio.getText()), Double.parseDouble(txtPrecioM.getText()), Integer.parseInt(txtCantidad.getText()), cod);
            material.UpdateProducto(producto);
            txtNombre.setText(nombre);
            tblProd.setValueAt(nombre, fila, 0);
            //updateTabla();
        } catch (NullPointerException | NumberFormatException e) {

        }
    }//GEN-LAST:event_btnActualizaNombreMouseClicked

    private void btnActualizaCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaCantidadMouseClicked
        int cant = Integer.parseInt(txtCantidad.getText());
        try {
            String nume = JOptionPane.showInputDialog(
                    "Ingrese la cantidad que desea agregar a:\n" + txtNombre.getText());
            int cant1 = Integer.parseInt(nume);
            int cantAux = cant + cant1;
            producto = new Producto(cant1, txtNombre.getText());
            material.AumentaCant(producto);
            txtCantidad.setText(String.valueOf(cantAux));
            busqueda();
            //updateTabla();

        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se agregó nada a:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_btnActualizaCantidadMouseClicked

    private void btnActualizaUbicacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizaUbicacionMouseClicked
//        String rol = config.validacion();
//        if (rol.equals("0")) {
//            JOptionPane.showMessageDialog(null, "No tiene el permiso para agregar nuevos proveedores", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            ActualzarUbicacionDialog dialogUbic = new ActualzarUbicacionDialog(this, true);
//            ActualzarUbicacionDialog.lblProd.setText(txtNombre.getText());
//            dialogUbic.setVisible(true);
//
//            if (dialogUbic != null) {
//                if (!dialogUbic.getInfo().equals("")) {
//                    updateTabla();
//                    txtUbicacion.setText(dialogUbic.getUbic());
//                }
//            }
//
//        }
    }//GEN-LAST:event_btnActualizaUbicacionMouseClicked

    private void tblProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdMouseClicked
        fila = tblProd.getSelectedRow();
        String prod = (String) tblProd.getModel().getValueAt(fila, 0);//Seleccionamos el nombre del producto
        txtNombre.setText(prod);
        String precio = tblProd.getModel().getValueAt(fila, 2).toString();//Seleccionamos el precio del producto
        txtPrecio.setText(precio);
        String precioMayor = tblProd.getModel().getValueAt(fila, 3).toString();//Seleccionamos el precio al por mayor del producto
        txtPrecioM.setText(precioMayor);
        String ubicacion = tblProd.getModel().getValueAt(fila, 7).toString();//Seleccionamos la ubicacion del producto
        txtUbicacion.setText(ubicacion);
        String cant = tblProd.getModel().getValueAt(fila, 8).toString();//Seleccionamos la cantidad del producto
        txtCantidad.setText(cant);
    }//GEN-LAST:event_tblProdMouseClicked

    private void lblMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenosMouseClicked
        int cant2 = Integer.parseInt(txtCantidad.getText());
        try {
            String nume = JOptionPane.showInputDialog(
                    "Ingrese la cantidad que desea restar a:\n" + txtNombre.getText());
            int cant1 = Integer.parseInt(nume);
            int nuevaCant = cant2 - cant1;
            producto = new Producto(cant1, txtNombre.getText());
            material.UpdateCantFactura(producto);
            String nuevaCantTxt = String.valueOf(nuevaCant);
            txtCantidad.setText(nuevaCantTxt);
            //updateTabla();

        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No se agregó nada a:\n" + txtNombre.getText());
        }
    }//GEN-LAST:event_lblMenosMouseClicked

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
            java.util.logging.Logger.getLogger(InventarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InventarioDialog dialog = new InventarioDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizaCantidad;
    private javax.swing.JLabel btnActualizaNombre;
    private javax.swing.JLabel btnActualizaPrecio;
    private javax.swing.JLabel btnActualizaPrecioMayor;
    private javax.swing.JLabel btnActualizaUbicacion;
    public static javax.swing.JComboBox<String> cmbBusq;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMenos;
    public static final javax.swing.JTable tblProd = new javax.swing.JTable();
    public static final javax.swing.JTextField txtBuscar = new javax.swing.JTextField();
    private javax.swing.JLabel txtCantidad;
    private javax.swing.JLabel txtEmpresa;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JLabel txtPrecioM;
    private javax.swing.JLabel txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
