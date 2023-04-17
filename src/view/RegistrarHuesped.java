/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;





import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import util.ConvertirFecha;
import util.ListarNacionalidadesUtil;
import util.ValidarFormulariosUtil;




/**
 *
 * @author CDM-51
 */
public class RegistrarHuesped extends javax.swing.JFrame {

    Reservas ventanaReservas = new Reservas();
    private final HuespedController huespedController;
    private final ReservaController reservaController;
   
    //CREAR NUEVO FROMULARIO DE COPIA
    
    public RegistrarHuesped() {
        initComponents();
        this.huespedController = new HuespedController();
        this.reservaController = new ReservaController();
        
       // Agregando el arreglo String[] de 190 nacionalidades al ComboBox.
        seleccionNacionalidad.setModel(new DefaultComboBoxModel<>(ListarNacionalidadesUtil.filtrarNacionalidades()));
        campoNumeroReserva.setEnabled(false);
        campoNumeroReserva.setText(ventanaReservas.getReserva().getId_Reserva());
    }
    
   
   
       /**
     * Al dar click en el botón guardar, iniciará una pila de ejecución
     * personalizada.
     *
     * 1. El primer proceso se encarga de almacenar los datos del objeto Reserva
     * en la base de datos obtenidos en el formulario de Reservas.
     *
     * 2. Entrará a guardarHuesped() que almacenará al huesped con el id de
     * reserva obtenido del formulario Reserva.
     *
     * 3. El método guardarHuesped() entrará a mostrarMensajeGuardado() donde se
     * le mostrará al usuario que el guardado fue exitoso.
     */
    private void guardarReserva() {
        if (ValidarFormulariosUtil.esFormularioHuespedValido(
                campoNombre.getText(), campoApellido.getText(), fechaNacimiento, campoTelefono.getText())) {
            this.reservaController.guardar(ventanaReservas.getReserva());
            guardarHuesped();
        }
    }

   private void guardarHuesped() {
        Date fechaNac = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaNacimiento.getDate()));
        Huesped huesped = new Huesped(
                campoNombre.getText(),
                campoApellido.getText(),
                fechaNac,
                seleccionNacionalidad.getSelectedItem().toString(),
                campoTelefono.getText()
        );
        this.huespedController.guardar(huesped, ventanaReservas.getReserva().getId_Reserva());
        mostrarMensajeGuardado();
    }

    private void mostrarMensajeGuardado() {
        Exito e = new Exito();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
    }

    public void limpiarCampos() {
        campoNombre.setText("");
        campoApellido.setText("");        
        fechaNacimiento.setCalendar(null);
        seleccionNacionalidad.setSelectedIndex(0);
        campoTelefono.setText("");
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        fSGradientPanel1 = new LIB.FSGradientPanel();
        jLabelTituloFormulario = new javax.swing.JLabel();
        jPanelRound1 = new LIB.JPanelRound();
        jLabel1 = new javax.swing.JLabel();
        panelFormularioRegistroHuesped = new LIB.JPanelRound();
        jLabelTextoNombre = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabelTextoApellido = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabelTextoFechaNacimiento = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabelTextoNacionalidad = new javax.swing.JLabel();
        seleccionNacionalidad = new javax.swing.JComboBox<>();
        jLabelTextoTelefono = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabelTextoNumeroReserva = new javax.swing.JLabel();
        campoNumeroReserva = new javax.swing.JTextField();
        btnGuardar = new LIB.FSButtonMD();
        btnCancelar = new LIB.FSButtonMD();
        btnMenuUsuario = new LIB.FSButtonMD();
        bannerRegistroHuesped = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel1.setFSEndColor(new java.awt.Color(102, 0, 102));
        fSGradientPanel1.setFSGradientFocus(700);
        fSGradientPanel1.setFSStartColor(new java.awt.Color(51, 102, 0));

        jLabelTituloFormulario.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTituloFormulario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloFormulario.setText("REGISTRO HUESPED");

        jPanelRound1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 0)));
        jPanelRound1.setColorPrimario(new java.awt.Color(255, 255, 0));
        jPanelRound1.setColorSecundario(new java.awt.Color(51, 51, 51));
        jPanelRound1.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanelRound1.setMinimumSize(new java.awt.Dimension(60, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo hotel chico.png"))); // NOI18N

        javax.swing.GroupLayout jPanelRound1Layout = new javax.swing.GroupLayout(jPanelRound1);
        jPanelRound1.setLayout(jPanelRound1Layout);
        jPanelRound1Layout.setHorizontalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelRound1Layout.setVerticalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout fSGradientPanel1Layout = new javax.swing.GroupLayout(fSGradientPanel1);
        fSGradientPanel1.setLayout(fSGradientPanel1Layout);
        fSGradientPanel1Layout.setHorizontalGroup(
            fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabelTituloFormulario)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        fSGradientPanel1Layout.setVerticalGroup(
            fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTituloFormulario)
                    .addComponent(jPanelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelPrincipal.add(fSGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 90));

        panelFormularioRegistroHuesped.setColorSecundario(new java.awt.Color(0, 0, 51));

        jLabelTextoNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoNombre.setText("NOMBRE");

        campoNombre.setBackground(new java.awt.Color(51, 51, 51));
        campoNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        campoNombre.setForeground(new java.awt.Color(255, 255, 255));
        campoNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoApellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoApellido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoApellido.setText("APELLIDO");

        campoApellido.setBackground(new java.awt.Color(51, 51, 51));
        campoApellido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        campoApellido.setForeground(new java.awt.Color(255, 255, 255));
        campoApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoApellido.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoFechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoFechaNacimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fecha.png"))); // NOI18N
        jLabelTextoFechaNacimiento.setText("   FECHA DE NACIMIENTO");

        fechaNacimiento.setBackground(new java.awt.Color(51, 51, 51));
        fechaNacimiento.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));

        jLabelTextoNacionalidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoNacionalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nacionalidad.png"))); // NOI18N
        jLabelTextoNacionalidad.setText("NACIONALIDAD");

        seleccionNacionalidad.setBackground(new java.awt.Color(51, 51, 51));
        seleccionNacionalidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seleccionNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        seleccionNacionalidad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionNacionalidadActionPerformed(evt);
            }
        });

        jLabelTextoTelefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoTelefono.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/telefono.png"))); // NOI18N
        jLabelTextoTelefono.setText("   TELEFONO");

        campoTelefono.setBackground(new java.awt.Color(51, 51, 51));
        campoTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        campoTelefono.setForeground(new java.awt.Color(255, 255, 255));
        campoTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoTelefono.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoNumeroReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTextoNumeroReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoNumeroReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categoria.png"))); // NOI18N
        jLabelTextoNumeroReserva.setText("NUMERO DE RESERVA");

        campoNumeroReserva.setBackground(new java.awt.Color(51, 51, 51));
        campoNumeroReserva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        campoNumeroReserva.setForeground(new java.awt.Color(255, 255, 255));
        campoNumeroReserva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNumeroReserva.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        javax.swing.GroupLayout panelFormularioRegistroHuespedLayout = new javax.swing.GroupLayout(panelFormularioRegistroHuesped);
        panelFormularioRegistroHuesped.setLayout(panelFormularioRegistroHuespedLayout);
        panelFormularioRegistroHuespedLayout.setHorizontalGroup(
            panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTextoNumeroReserva)
                    .addComponent(jLabelTextoApellido)
                    .addComponent(campoApellido)
                    .addComponent(seleccionNacionalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoNumeroReserva)
                    .addComponent(campoTelefono)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jLabelTextoNombre)
                    .addComponent(jLabelTextoFechaNacimiento)
                    .addComponent(jLabelTextoNacionalidad)
                    .addComponent(jLabelTextoTelefono)
                    .addComponent(campoNombre))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelFormularioRegistroHuespedLayout.setVerticalGroup(
            panelFormularioRegistroHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioRegistroHuespedLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabelTextoNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTextoApellido)
                .addGap(5, 5, 5)
                .addComponent(campoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoFechaNacimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabelTextoNacionalidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabelTextoTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabelTextoNumeroReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNumeroReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        panelPrincipal.add(panelFormularioRegistroHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 320, 520));

        btnGuardar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("   GUARDAR");
        btnGuardar.setColorNormal(new java.awt.Color(51, 51, 51));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 140, -1));

        btnCancelar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar_1.png"))); // NOI18N
        btnCancelar.setText("   CANCELAR");
        btnCancelar.setColorNormal(new java.awt.Color(51, 51, 51));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 140, -1));

        btnMenuUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnMenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inicio.png"))); // NOI18N
        btnMenuUsuario.setText("   INICIO");
        btnMenuUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuUsuarioMouseClicked(evt);
            }
        });
        panelPrincipal.add(btnMenuUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, 140, -1));

        bannerRegistroHuesped.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bannerRegistroHuesped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/third.jpg"))); // NOI18N
        panelPrincipal.add(bannerRegistroHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 570, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        Mensaje.cancelarRegistro(evt, this);
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        evt.consume();
        guardarReserva();
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnMenuUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuUsuarioMouseClicked
        evt.consume();
        this.dispose();
        
    }//GEN-LAST:event_btnMenuUsuarioMouseClicked

    private void seleccionNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionNacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionNacionalidadActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
 /*   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
/*        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /*       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarHuesped().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bannerRegistroHuesped;
    private LIB.FSButtonMD btnCancelar;
    private LIB.FSButtonMD btnGuardar;
    private LIB.FSButtonMD btnMenuUsuario;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoNumeroReserva;
    private javax.swing.JTextField campoTelefono;
    private LIB.FSGradientPanel fSGradientPanel1;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTextoApellido;
    private javax.swing.JLabel jLabelTextoFechaNacimiento;
    private javax.swing.JLabel jLabelTextoNacionalidad;
    private javax.swing.JLabel jLabelTextoNombre;
    private javax.swing.JLabel jLabelTextoNumeroReserva;
    private javax.swing.JLabel jLabelTextoTelefono;
    private javax.swing.JLabel jLabelTituloFormulario;
    private LIB.JPanelRound jPanelRound1;
    private LIB.JPanelRound panelFormularioRegistroHuesped;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JComboBox<String> seleccionNacionalidad;
    // End of variables declaration//GEN-END:variables

}