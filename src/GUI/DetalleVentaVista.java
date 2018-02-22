package GUI;

import Clases.AbonoCliente;
import Clases.PagoProveedorClase;
import Clases.Venta;
import Dat.DATAbonoCliente;
import Dat.DATPagoProveedor;
import Dat.DATVenta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class DetalleVentaVista extends javax.swing.JFrame {

    
    Venta objVenta = new Venta();
    DATVenta manejadorVenta;
    String id_Venta, cedula, nombre, total;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel();
    AbonoCliente abono = new AbonoCliente();
    DATAbonoCliente manejadorAbono;
    PagoProveedorClase pago = new PagoProveedorClase();
    DATPagoProveedor manejadorPago;

    public DetalleVentaVista() {
        manejadorVenta = new DATVenta();
        manejadorAbono = new DATAbonoCliente();
        manejadorPago = new DATPagoProveedor();
        initComponents();
        encabezados();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        tablaVentas();
        tablaPagosCL();
        tablaPagoPR();
        sumar();
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
                    anchoColumna = (60 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (50 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (30 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void sumar() {
        double aux1 = 0, aux2 = 0, aux3 = 0;
        if (tblVentas.getRowCount() > 0) {
            int totalRow = tblVentas.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblVentas.getValueAt(i, 4)));
                sumatoria1 += sumatoria;
                aux1 = sumatoria1;
            }
            txtTotalVentas.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux1 = 0.00;
            txtTotalVentas.setText("Total: $" + String.valueOf(aux1));
        }
        if (tblPagoCl.getRowCount() > 0) {
            int totalRow = tblPagoCl.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblPagoCl.getValueAt(i, 2)));
                sumatoria1 += sumatoria;
                aux2 = sumatoria1;

            }
            txtTotalAbonos.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux2 = 0.00;
            txtTotalAbonos.setText("Total: $" + String.valueOf(aux2));
        }
        if (tblPagoPr.getRowCount() > 0) {
            int totalRow = tblPagoPr.getRowCount();
            totalRow -= 1;
            double sumatoria1 = 0.0;
            for (int i = 0; i <= (totalRow); i++) {
                double sumatoria = Double.parseDouble(String.valueOf(tblPagoPr.getValueAt(i, 2)));
                sumatoria1 += sumatoria;
                aux3 = sumatoria1;
            }
            txtTotalPagoPr.setText("Total: $" + String.valueOf(sumatoria1));
        } else {
            aux3 = 0.00;
            txtTotalPagoPr.setText("Total: $" + String.valueOf(aux3));
        }
        double totalDia = aux1 + aux2 + aux3;
        txtTotal.setText("$ " + String.valueOf(totalDia));
    }

    public void tablaVentas() {
        String fecha = calendario.getText();
        ArrayList<Venta> listadoVentas = manejadorVenta.vistaVenta(fecha);
        int cantFilas = listadoVentas.size();
        modelo.setNumRows(cantFilas);
        for (int i = 0; i < cantFilas; i++) {
            objVenta = listadoVentas.get(i);
            int idVenta = objVenta.getIntIdVenta();
            String cedulaCliente = objVenta.getCedulaUser();
            String nombreCliente = objVenta.getStrCliente();
            double totalVenta = objVenta.getDblTotalVenta();
            double valCancelado = objVenta.getDblValCancelado();
            String fechaVenta = objVenta.getStrFecha();

            modelo.setValueAt(idVenta, i, 0);
            modelo.setValueAt(cedulaCliente, i, 1);
            modelo.setValueAt(nombreCliente, i, 2);
            modelo.setValueAt(totalVenta, i, 3);
            modelo.setValueAt(valCancelado, i, 4);
            modelo.setValueAt(fechaVenta, i, 5);
        }
    }

    public void encabezados() {
        modelo.addColumn("Id Venta");
        modelo.addColumn("Cédula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Total de venta");
        modelo.addColumn("Valor cancelado");

        modelo2.addColumn("Cliente");
        modelo2.addColumn("Cédula");
        modelo2.addColumn("Monto de Abono");
        modelo2.addColumn("Deuda");
        modelo2.addColumn("Uusario");

        modelo3.addColumn("Empresa");
        modelo3.addColumn("Deuda");
        modelo3.addColumn("Valor Cancelado");
        modelo3.addColumn("Usuario");
    }

    public void tablaPagosCL() {
        setAnchoColumnas();
        String fecha = calendario.getText();
        ArrayList<AbonoCliente> listadoPagosCL = manejadorAbono.verAbonos(fecha);
        int cantLista = listadoPagosCL.size();
        modelo2.setNumRows(cantLista);

        for (int i = 0; i < cantLista; i++) {
            abono = listadoPagosCL.get(i);
            String cliente = abono.getNombreCL();
            String cedula = abono.getStrCedula();
            double monto = abono.getDblMontoAbono();
            double deuda = abono.getDeuda();
            String usuario = abono.getStrUsuario();

            modelo2.setValueAt(cliente, i, 1);
            modelo2.setValueAt(cedula, i, 2);
            modelo2.setValueAt(monto, i, 3);
            modelo2.setValueAt(deuda, i, 4);
            modelo2.setValueAt(usuario, i, 5);
        }
    }

    public void tablaPagoPR() {
        String fecha = calendario.getText();
        setAnchoColumnas();
        ArrayList<PagoProveedorClase> listadoPagosPR = manejadorPago.verPagosPorFecha(fecha);
        int cantLista = listadoPagosPR.size();
        modelo3.setNumRows(cantLista);
        for (int i = 0; i < cantLista; i++) {
            pago = listadoPagosPR.get(i);
            String empresa = pago.getEmpresa();
            String cliente = pago.getUsuario();
            double deuda = pago.getDeuda();
            double monto = pago.getDblMontoCancelado();
            
            modelo3.setValueAt(empresa, i, 1);
            modelo3.setValueAt(deuda, i, 2);
            modelo3.setValueAt(monto, i, 3);
            modelo3.setValueAt(cliente, i, 4);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        calendario = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        txtTotalVentas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPagoCl = new javax.swing.JTable();
        txtTotalAbonos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPagoPr = new javax.swing.JTable();
        txtTotalPagoPr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto Cn", 0, 36)); // NOI18N
        jLabel1.setText("Reporte de ventas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Monto total de ventas:");

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Fecha:");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 255, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        calendario.setCurrentView(new datechooser.view.appearance.AppearancesList("custom",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    calendario.setFormat(2);
    calendario.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            calendarioOnCommit(evt);
        }
    });

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-reloj-historial.png"))); // NOI18N

    tblVentas.setModel(modelo);
    tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblVentasMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblVentas);

    btnVer.setText("Ver");
    btnVer.setToolTipText("Presione para ver el detalle de la compra");
    btnVer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnVerActionPerformed(evt);
        }
    });

    txtTotalVentas.setText("jLabel5");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(917, Short.MAX_VALUE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(txtTotalVentas)
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(btnVer)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(txtTotalVentas)
            .addContainerGap(43, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Ventas", jPanel1);

    tblPagoCl.setModel(modelo2);
    tblPagoCl.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoClMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tblPagoCl);

    txtTotalAbonos.setText("jLabel5");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(txtTotalAbonos)
                    .addContainerGap(945, Short.MAX_VALUE))))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap(60, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(txtTotalAbonos)
            .addGap(38, 38, 38))
    );

    jTabbedPane1.addTab("Pago Clientes", jPanel2);

    tblPagoPr.setModel(modelo3);
    tblPagoPr.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoPrMouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(tblPagoPr);

    txtTotalPagoPr.setText("Total:");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(txtTotalPagoPr)
                    .addContainerGap(955, Short.MAX_VALUE))))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(56, 56, 56)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(txtTotalPagoPr)
            .addContainerGap(46, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Pago Proveedores", jPanel3);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(18, 18, 18)
                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(162, 162, 162)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(279, 279, 279)
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jLabel1))
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(4, 4, 4)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel4)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTabbedPane1)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(22, 22, 22)
            .addComponent(jButton1)
            .addGap(21, 21, 21))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calendarioOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_calendarioOnCommit
        tablaVentas();
        tablaPagosCL();
        tablaPagoPR();
        sumar();
    }//GEN-LAST:event_calendarioOnCommit

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        int fila = tblVentas.getSelectedRow();
        if (fila >= 0) {
            id_Venta = (String) tblVentas.getValueAt(fila, 0).toString();
            cedula = (String) tblVentas.getValueAt(fila, 1).toString();
            nombre = (String) tblVentas.getValueAt(fila, 2);
            total = (String) tblVentas.getValueAt(fila, 3).toString();
        }
    }//GEN-LAST:event_tblVentasMouseClicked

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        VistaFactura vf = new VistaFactura();

//        if (tblVentas.getSelectedRows().length > 0) {
//            VistaFactura.txtCliente.setText(nombre);
//            VistaFactura.txtCedula.setText(cedula);
//            VistaFactura.txtVenta.setText(id_Venta);
//            VistaFactura.txtTotal.setText("$" + total);
//            VistaFactura.cargarTabla();
//            vf.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna venta");
//        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void tblPagoClMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagoClMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagoClMouseClicked

    private void tblPagoPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagoPrMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPagoPrMouseClicked

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
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleVentaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleVentaVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVer;
    private datechooser.beans.DateChooserCombo calendario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblPagoCl;
    private javax.swing.JTable tblPagoPr;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtTotalAbonos;
    private javax.swing.JLabel txtTotalPagoPr;
    private javax.swing.JLabel txtTotalVentas;
    // End of variables declaration//GEN-END:variables
}
