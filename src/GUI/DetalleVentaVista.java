package GUI;


import Clases.DetalleVenta;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class DetalleVentaVista extends javax.swing.JFrame {

    DetalleVenta objRep = new DetalleVenta();
    String id_Venta, cedula, nombre, total;
    String pagosCl = "<html><body>Pago<br>Clientes</body></html>";
    String pagosPr = "<html><body>Pago<br>Proveedores</body></html>";

    public DetalleVentaVista() {
        initComponents();
        sumar();
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(Constantes.Constantes.NOMBRE_PROGRAMA);
        this.setLocationRelativeTo(null);
        updateTablaVentas();
        updateTablaPagosCL();
        updateTablaPagoPR();
        txtPagosCl.setText(pagosCl);
        txtPagoProv.setText(pagosPr);
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
                System.out.println("aux2 for:"+aux1);
            }
            txtTotalVentas.setText("Total: $" + String.valueOf(sumatoria1));
        } else{
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
        } else{
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
        System.out.println(aux1+" "+aux2+" "+aux3);
        double totalDia = aux1 + aux2 + aux3;
        txtTotal.setText("$ "+String.valueOf(totalDia));
    }

    private void updateTablaVentas() {
//        try {
//            BLVenta dl = new BLVenta();
//            DefaultTableModel dtm = new DefaultTableModel();
//            dtm.addColumn("Nro Venta");
//            dtm.addColumn("Cédula");
//            dtm.addColumn("Cliente");
//            dtm.addColumn("Costo Total");
//            dtm.addColumn("Valor Cancelado");
//            dtm.addColumn("Fecha");
//            setAnchoColumnas();
//            String fecha = calendario.getText();
//            for (int i = 0; i < dl.vistaVenta(fecha).size(); i++) {
//                dtm.addRow(dl.vistaVenta(fecha).get(i));
//            }
//            tblVentas.setModel(dtm);
//        } catch (SQLException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void updateTablaPagosCL() {
//        try {
//            BLAbonoCliente blc = new BLAbonoCliente();
//            DefaultTableModel dtm = new DefaultTableModel();
//            dtm.addColumn("Cliente");
//            dtm.addColumn("Cédula");
//            dtm.addColumn("Monto de Abono");
//            dtm.addColumn("Deuda");
//            dtm.addColumn("Fecha");
//            setAnchoColumnas();
//            String fecha = calendario.getText();
//            for (int i = 0; i < blc.verAbono(fecha).size(); i++) {
//                dtm.addRow(blc.verAbono(fecha).get(i));
//            }
//            tblPagoCl.setModel(dtm);
//        } catch (SQLException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void updateTablaPagoPR() {
//        try {
//            BLPagoProveedor blpp = new BLPagoProveedor();
//            DefaultTableModel dtm = new DefaultTableModel();
//            dtm.addColumn("Nro Venta");
//            dtm.addColumn("Cédula");
//            dtm.addColumn("Cliente");
//            dtm.addColumn("Costo Total");
//            dtm.addColumn("Valor Cancelado");
//            dtm.addColumn("Fecha");
//            setAnchoColumnas();
//            String fecha = calendario.getText();
//            for (int i = 0; i < blpp.verPagoPorFecha(fecha).size(); i++) {
//                dtm.addRow(blpp.verPagoPorFecha(fecha).get(i));
//            }
//            tblPagoPr.setModel(dtm);
//        } catch (SQLException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DetalleVentaVista.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        calendario = new datechooser.beans.DateChooserCombo();
        btnVer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPagoCl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtPagosCl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPagoPr = new javax.swing.JTable();
        txtPagoProv = new javax.swing.JLabel();
        txtTotalVentas = new javax.swing.JLabel();
        txtTotalAbonos = new javax.swing.JLabel();
        txtTotalPagoPr = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVentas);

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
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(187, 187, 187),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
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

    btnVer.setText("Ver");
    btnVer.setToolTipText("Presione para ver el detalle de la compra");
    btnVer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnVerActionPerformed(evt);
        }
    });

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/boton-reloj-historial.png"))); // NOI18N

    tblPagoCl.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    tblPagoCl.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoClMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tblPagoCl);

    jLabel5.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
    jLabel5.setText("Ventas");

    txtPagosCl.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
    txtPagosCl.setText("jLabel6");
    txtPagosCl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

    tblPagoPr.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    tblPagoPr.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblPagoPrMouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(tblPagoPr);

    txtPagoProv.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
    txtPagoProv.setText("jLabel6");
    txtPagoProv.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

    txtTotalVentas.setText("Total:");

    txtTotalAbonos.setText("Total:");

    txtTotalPagoPr.setText("Total:");

    jToggleButton1.setText("jToggleButton1");

    jToggleButton2.setText("jToggleButton2");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jToggleButton1)
                .addComponent(jToggleButton2))
            .addGap(27, 27, 27)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTotalVentas)
                        .addComponent(txtTotalAbonos)
                        .addComponent(txtTotalPagoPr)))
                .addComponent(jButton1))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtPagoProv)
                                            .addComponent(txtPagosCl))
                                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(711, 711, 711)))))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1)))
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(51, 51, 51))
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel4)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(txtTotalVentas))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnVer)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtPagosCl)
                    .addGap(28, 28, 28)
                    .addComponent(txtTotalAbonos)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(21, 21, 21))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtPagoProv)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(txtTotalPagoPr))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(jToggleButton1)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jToggleButton2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal objP = new Principal();
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calendarioOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_calendarioOnCommit
        updateTablaVentas();
        updateTablaPagosCL();
//        updateTablaPagoPR();
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

        if (tblVentas.getSelectedRows().length > 0) {
            VistaFactura.txtCliente.setText(nombre);
            VistaFactura.txtCedula.setText(cedula);
            VistaFactura.txtVenta.setText(id_Venta);
            VistaFactura.txtTotal.setText("$" + total);
            VistaFactura.cargarTabla();
            vf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna venta");
        }
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTable tblPagoCl;
    private javax.swing.JTable tblPagoPr;
    private javax.swing.JTable tblVentas;
    private javax.swing.JLabel txtPagoProv;
    private javax.swing.JLabel txtPagosCl;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtTotalAbonos;
    private javax.swing.JLabel txtTotalPagoPr;
    private javax.swing.JLabel txtTotalVentas;
    // End of variables declaration//GEN-END:variables
}
