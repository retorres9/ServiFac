package gui;

import clases.Configuracion;
import clases.Usuario;
import dat.DATConfiguracion;
import dat.DATMaterial;
import dat.DATUsuario;
import utilidades.Utilidades;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Principal extends javax.swing.JFrame {

    Configuracion config = new Configuracion();
    Utilidades util = new Utilidades();
    static Process p;
    String ruta = null;
    String nombreEmp;
    String usuario;
    int cant;
    String cedUser;
    String host;//Nombre de la maquina
    int rol;
    private static ServerSocket socket;

    Usuario objUser = new Usuario();
    DATMaterial objProd;
    DATConfiguracion manejadorConf;
    DATUsuario manejadorUsuario;
    FileOutputStream out;

    public Principal() {
        objProd = new DATMaterial();
        manejadorUsuario = new DATUsuario();
        manejadorConf = new DATConfiguracion();
        initComponents();
        host = util.getPcName();
        obtenerUsuario();
        obtenerConf();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Recursos/ServiFac.png")).getImage());
        this.setTitle(constantes.Constantes.NOMBRE_PROGRAMA);
        alerta();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtAlarma = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtConfig = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        txtVendedor = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1034, 675));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setText("Ingresar Producto");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(381, 252, 110, 16);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(381, 272, 90, 32);

        jLabel2.setText("Inventario");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(541, 252, 87, 16);

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(541, 272, 90, 32);

        jLabel3.setText("Ingresar Cliente");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(730, 250, 100, 16);

        jButton3.setText("Aceptar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(730, 270, 80, 32);

        jLabel4.setText("Pagos");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(895, 251, 40, 16);

        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(895, 271, 90, 32);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/codigo-de-barras.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(444, 113, 128, 111);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cliente.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(775, 105, 128, 128);

        txtEmpresa.setFont(new java.awt.Font("Roboto Cn", 1, 24)); // NOI18N
        txtEmpresa.setText("Nombre Empresa");
        getContentPane().add(txtEmpresa);
        txtEmpresa.setBounds(50, 11, 840, 32);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(0, 47, 1031, 10);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(342, 71, 10, 250);

        txtAlarma.setToolTipText("");
        txtAlarma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtAlarma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAlarmaMouseClicked(evt);
            }
        });
        getContentPane().add(txtAlarma);
        txtAlarma.setBounds(520, 60, 27, 27);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(501, 252, 10, 54);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(856, 251, 10, 54);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/trato.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(88, 403, 128, 128);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(685, 71, 10, 250);

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(150, 552, 10, 54);

        jLabel9.setText("Ingresar Proveedor");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 552, 120, 16);

        jButton5.setText("Aceptar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(40, 572, 90, 32);

        jLabel10.setText("Operaciones de Proveedor");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(168, 552, 160, 16);

        jButton6.setText("Aceptar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(190, 572, 100, 32);

        jLabel11.setText("Productos");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(461, 71, 59, 16);

        jLabel12.setText("Clientes");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(822, 73, 46, 16);

        jLabel13.setText("Proveedores");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(148, 375, 73, 16);

        jButton7.setText("Aceptar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(56, 269, 90, 32);

        jLabel15.setText("Ventas");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(145, 71, 40, 16);

        txtConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ajustes_principal.png"))); // NOI18N
        txtConfig.setToolTipText("Presione para ir a Configuracion");
        txtConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtConfigMouseClicked(evt);
            }
        });
        getContentPane().add(txtConfig);
        txtConfig.setBounds(449, 404, 128, 128);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(0, 347, 1031, 10);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja-registradora.png"))); // NOI18N
        getContentPane().add(jLabel16);
        jLabel16.setBounds(94, 103, 128, 128);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Salir.png"))); // NOI18N
        jLabel14.setToolTipText("Presione para Cerrar Seción");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14);
        jLabel14.setBounds(782, 403, 129, 129);

        jLabel17.setText("Configuración");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(479, 550, 79, 16);

        jLabel18.setText("Salir");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(840, 550, 100, 16);

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(344, 375, 10, 250);

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator9);
        jSeparator9.setBounds(686, 375, 10, 250);

        jLabel19.setText("Factura");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(56, 249, 43, 16);

        jLabel20.setText("Reporte de Ventas");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(186, 249, 120, 16);

        jButton8.setText("Aceptar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(186, 269, 90, 32);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator10);
        jSeparator10.setBounds(158, 249, 10, 54);

        txtVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/carnetVendedor.png"))); // NOI18N
        txtVendedor.setText("jLabel8");
        txtVendedor.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(txtVendedor);
        txtVendedor.setBounds(902, 10, 100, 24);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        IngresoProd objI = new IngresoProd();
        objI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void obtenerUsuario() {
        ArrayList<Usuario> listaUsuario = manejadorUsuario.obtenerUserLog(host);
        int cantUsuario = listaUsuario.size();
        for (int i = 0; i < cantUsuario; i++) {
            objUser = listaUsuario.get(i);
            String usuar = objUser.getNombre();
            cedUser = objUser.getUsuario();
            txtVendedor.setText(usuar);
            rol = objUser.getRol();
            if (rol == 0) {
                //jmiElimProd.setEnabled(false);
                jmiElimCliente.setEnabled(false);
                jmiElimProv.setEnabled(false);
                jmConfig.setEnabled(false);
                txtConfig.setEnabled(false);
            }
            if (rol == 1) {
                //jmiElimProd.setEnabled(true);
                jmiElimCliente.setEnabled(true);
                jmiElimProv.setEnabled(true);
                jmConfig.setEnabled(true);
                txtConfig.setEnabled(true);
            }

        }
    }

    public void obtenerConf() {
        ArrayList<Configuracion> conf = manejadorConf.cargaConfig();
        int cantConf = conf.size();
        for (int j = 0; j < cantConf; j++) {
            config = conf.get(j);
            String empresa = config.getEmpresa();
            txtEmpresa.setText(empresa);
        }
    }

    public void respaldo() {
        String fecha = new SimpleDateFormat("dd-MM-yyyy__HH_mm").format(new Date());
        String workingDirectory = System.getProperty("user.home");
        ruta = workingDirectory + "\\" + "OneDrive\\Backups\\Libreria\\" + host + "_" + fecha + ".sql";
        Process proceso = null;
        try {
            Runtime run = Runtime.getRuntime();
            proceso = run.exec("mysqldump -u root -pticowrc2017"
                    + " empresa");
            InputStream in = proceso.getInputStream();
            out = new FileOutputStream(ruta);
            byte[] buffer = new byte[1000];
            int leido = in.read(buffer);
            while (leido > 0) {
                out.write(buffer, 0, leido);
                leido = in.read(buffer);
            }
            
            JOptionPane.showMessageDialog(null, "Backup creado satisfactoriamente");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al crear el Backup");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cerrar() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "¿En realidad desea realizar cerrar la aplicacion?", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            int n = JOptionPane.showConfirmDialog(null, "Antes de cerrar ¿Desea crear un respaldo de la base de datos?\n"
                    + "Esto solo nos tomará unos segundos", "Backup", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                respaldo();
                System.exit(0);
            } else {
                System.exit(0);
            }
        } else {
        }
    }

    public void alerta() {
        cant = objProd.CuentaRegistros();
        String path = "/Recursos/alerta.png";
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        String path2 = "/Recursos/success.png";
        URL url2 = this.getClass().getResource(path2);
        ImageIcon icon2 = new ImageIcon(url2);
        if (cant >= 1) {
            txtAlarma.setIcon(icon);
            txtAlarma.setToolTipText("Hay productos por debajo de su cantidad mínima");
        } else {
            txtAlarma.setIcon(icon2);
            txtAlarma.setToolTipText("No hay elementos faltantes");
        }
    }

    public void cerrarSocket() {
        try {
            socket = new ServerSocket(6669);
        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Inventario objB = new Inventario();
        objB.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        NewCliente objC = new NewCliente();
        NewCliente.txtAyuda.setText(cedUser);
        objC.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Pagos objP = new Pagos();
        objP.lblRol.setText(String.valueOf(rol));
        objP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtAlarmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAlarmaMouseClicked
        Minimos objMin = new Minimos();
        objMin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtAlarmaMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        NuevoProveedor objNP = new NuevoProveedor();
        objNP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        PagoProveedor objPP = new PagoProveedor();
        objPP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Factura fact = new Factura();
        fact.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtConfigMouseClicked
        ConfiguracionGUI configGui = new ConfiguracionGUI();
        if (rol == 1) {
            this.setVisible(false);
            configGui.setVisible(true);
        }
    }//GEN-LAST:event_txtConfigMouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        cerrar();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DetalleVentaVista rv = new DetalleVentaVista();
        rv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JMenu jmConfig;
    private javax.swing.JMenuItem jmiElimCliente;
    private javax.swing.JMenuItem jmiElimProv;
    private javax.swing.JLabel txtAlarma;
    private javax.swing.JLabel txtConfig;
    public static final javax.swing.JLabel txtEmpresa = new javax.swing.JLabel();
    private javax.swing.JLabel txtVendedor;
    // End of variables declaration//GEN-END:variables
}
