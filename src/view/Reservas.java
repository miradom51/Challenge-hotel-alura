/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import modelo.Reserva;
import util.ConvertirFecha;
import util.ValidarFormulariosUtil;
/**
 *
 * @author CDM-51
 */
public class Reservas extends javax.swing.JPanel {

     private static Reserva reserva;
    /**
     * Creates new form RegistrarUsuario1
     */
    public Reservas() {
        initComponents();
        campoValorReserva.setEnabled(false);
    }
    
    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        if (Reservas.reserva == null) {
            throw new RuntimeException("El método getReserva() debe obtener los "
                    + "valores desde el formulario de la ventana de Reservas.");
        }
        return Reservas.reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        Reservas.reserva = reserva;
    }
    
    
    
     /**
     * Este método no envía el registro en la capa DAO a la Base de Datos, en
     * caso de cancelación del registro, para evitar carga de conexión con la
     * base de datos, los datos se almacenan en un objeto y son extraídos hasta
     * que el botón de guardado del formulario de registro de húespedes haya
     * sido completado y guardado..
     */
    private void guardarReferenciaReserva() {
        if (ValidarFormulariosUtil.esFormularioReservaValido(fechaCheckIn, fechaCheckOut, campoValorReserva.getText(), seleccionFormaPago)) {

            Date dateCheckIn = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckIn.getDate()));
            Date dateCheckOut = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckOut.getDate()));
            String valorReservaString = campoValorReserva.getText();
            BigDecimal valorReservaToBigDecimal = new BigDecimal(valorReservaString);

            setReserva(new Reserva(
                    dateCheckIn,
                    dateCheckOut,
                    valorReservaToBigDecimal,
                    seleccionFormaPago.getSelectedItem().toString()
            ));
            limpiarCampos();
            RegistrarHuesped registrarHuesped = new RegistrarHuesped();
            registrarHuesped.setVisible(true);
            registrarHuesped.setLocationRelativeTo(null);
        }
    }

    public void limpiarCampos() {
        fechaCheckIn.setCalendar(null);
        fechaCheckOut.setCalendar(null);
        campoValorReserva.setText("0.0");
        seleccionFormaPago.setSelectedIndex(0);
    }

    /**
     * Método para cálcular el valor monetario total en Pesos Argentinos con una
     * tasa fija de 500.00 por día, con la diferencia de días obtenida de los
     * campos fechaCheckIn y fechaCheckOut.
     *
     * El valor obtenido se asigna al campo campoValorReserva através del evento
     * PropertyChangeEvent.
     *
     * El valor de diferencia de fechas fue realizando la conversión de las
     * fechas de tipo Date a LocalDate, para luego calcular la diferencia de
     * días con ChronoUnit.DAYS.between.
     *
     * @param fechaEntrada - Fecha de tipo JDateChooser fechaCheckIn.getDate()
     * @param fechaSalida - Fecha de tipo JDateChooser fechaCheckOut.getDate()
     * @return - (BigDecimal) Retorna el resultado de la diferencia de días por
     * el valor de reserva de 550.00.
     */
    public BigDecimal calcularValorReserva(JDateChooser fechaEntrada, JDateChooser fechaSalida) {
        BigDecimal valorTasaReservaPorDia = new BigDecimal("500.00");
        BigDecimal valorReserva = new BigDecimal("0.0");
        long numeroDias;
        if ((fechaEntrada.getDate() != null) && (fechaSalida.getDate() != null)) {
            LocalDate fechaIn = ConvertirFecha.convertirDateALocalDate(fechaCheckIn.getDate());
            LocalDate fechaOut = ConvertirFecha.convertirDateALocalDate(fechaCheckOut.getDate());
            numeroDias = ChronoUnit.DAYS.between(fechaIn, fechaOut);
            if (numeroDias > 0) {
                BigDecimal diasReservados = new BigDecimal(numeroDias);
                valorReserva = diasReservados.multiply(valorTasaReservaPorDia);
                campoValorReserva.setText(String.valueOf(valorReserva));
                return valorReserva;
            } else {
                ValidarFormulariosUtil.desplegarMensajeError(
                        "Error en el cálculo de la Reserva.",
                        "No es posible cálcular reservas si la"
                        + " fecha de Check-Out es menor o igual a la fecha de \n"
                        + " Check-In, ya que el cálculo se realiza por días."
                );
                campoValorReserva.setText(String.valueOf(valorReserva));
                return valorReserva;
            }
        } else {
            campoValorReserva.setText(String.valueOf(valorReserva));
            return valorReserva;
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

        jLabel3 = new javax.swing.JLabel();
        btnContinuarReservas = new LIB.FSButtonMD();
        jPanelRound1 = new LIB.JPanelRound();
        jPanelRound2 = new LIB.JPanelRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTextoCheckIn = new javax.swing.JLabel();
        fechaCheckIn = new com.toedter.calendar.JDateChooser();
        jLabelTextoCheckOut = new javax.swing.JLabel();
        fechaCheckOut = new com.toedter.calendar.JDateChooser();
        jLabelTextoValorReserva = new javax.swing.JLabel();
        campoValorReserva = new javax.swing.JTextField();
        jLabelTextoFormaPago = new javax.swing.JLabel();
        seleccionFormaPago = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnContinuarReservas.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnContinuarReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cintinuar.png"))); // NOI18N
        btnContinuarReservas.setText("   CONTINUAR");
        btnContinuarReservas.setColorNormal(new java.awt.Color(51, 51, 51));
        btnContinuarReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContinuarReservasMouseClicked(evt);
            }
        });
        add(btnContinuarReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 290, 150, -1));

        jPanelRound1.setForeground(new java.awt.Color(255, 255, 255));

        jPanelRound2.setColorPrimario(new java.awt.Color(51, 51, 51));
        jPanelRound2.setColorSecundario(new java.awt.Color(51, 51, 255));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login_icon_176905.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RESERVAS");

        javax.swing.GroupLayout jPanelRound2Layout = new javax.swing.GroupLayout(jPanelRound2);
        jPanelRound2.setLayout(jPanelRound2Layout);
        jPanelRound2Layout.setHorizontalGroup(
            jPanelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRound2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
            .addGroup(jPanelRound2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelRound2Layout.setVerticalGroup(
            jPanelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRound2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
        );

        jLabelTextoCheckIn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelTextoCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek in.png"))); // NOI18N
        jLabelTextoCheckIn.setText("   FECHA DE CHEK IN");

        fechaCheckIn.setBackground(new java.awt.Color(51, 51, 51));
        fechaCheckIn.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        fechaCheckIn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaCheckIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckInPropertyChange(evt);
            }
        });

        jLabelTextoCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelTextoCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek out.png"))); // NOI18N
        jLabelTextoCheckOut.setText("   FECHA DE CHEK OUT");

        fechaCheckOut.setBackground(new java.awt.Color(51, 51, 51));
        fechaCheckOut.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        fechaCheckOut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckOutPropertyChange(evt);
            }
        });

        jLabelTextoValorReserva.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelTextoValorReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoValorReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pago.png"))); // NOI18N
        jLabelTextoValorReserva.setText("   VALOR DE RESERVA");

        campoValorReserva.setBackground(new java.awt.Color(51, 51, 51));
        campoValorReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        campoValorReserva.setForeground(new java.awt.Color(255, 255, 255));
        campoValorReserva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoValorReserva.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        jLabelTextoFormaPago.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelTextoFormaPago.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTextoFormaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volver.png"))); // NOI18N
        jLabelTextoFormaPago.setText("   FORMA DE PAGO");

        seleccionFormaPago.setBackground(new java.awt.Color(51, 51, 51));
        seleccionFormaPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        seleccionFormaPago.setForeground(new java.awt.Color(255, 255, 255));
        seleccionFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija forma de pago", "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en Efectivo" }));
        seleccionFormaPago.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));

        javax.swing.GroupLayout jPanelRound1Layout = new javax.swing.GroupLayout(jPanelRound1);
        jPanelRound1.setLayout(jPanelRound1Layout);
        jPanelRound1Layout.setHorizontalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
            .addGroup(jPanelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTextoCheckOut)
                    .addComponent(jLabelTextoValorReserva)
                    .addComponent(fechaCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoValorReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTextoCheckIn)
                    .addComponent(jLabelTextoFormaPago)
                    .addComponent(seleccionFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanelRound1Layout.setVerticalGroup(
            jPanelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTextoCheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoCheckOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoValorReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoValorReserva)
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoFormaPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        add(jPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 350, 550));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/third.jpg"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 590));
    }// </editor-fold>//GEN-END:initComponents

    private void fechaCheckInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckInPropertyChange
        evt.getPropertyName();
        calcularValorReserva(fechaCheckIn, fechaCheckOut);
    }//GEN-LAST:event_fechaCheckInPropertyChange

    private void fechaCheckOutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckOutPropertyChange
        evt.getPropertyName();
        calcularValorReserva(fechaCheckIn, fechaCheckIn);
    }//GEN-LAST:event_fechaCheckOutPropertyChange

    private void btnContinuarReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuarReservasMouseClicked
        evt.consume();
        guardarReferenciaReserva();
    }//GEN-LAST:event_btnContinuarReservasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD btnContinuarReservas;
    private javax.swing.JTextField campoValorReserva;
    private com.toedter.calendar.JDateChooser fechaCheckIn;
    private com.toedter.calendar.JDateChooser fechaCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTextoCheckIn;
    private javax.swing.JLabel jLabelTextoCheckOut;
    private javax.swing.JLabel jLabelTextoFormaPago;
    private javax.swing.JLabel jLabelTextoValorReserva;
    private LIB.JPanelRound jPanelRound1;
    private LIB.JPanelRound jPanelRound2;
    private javax.swing.JComboBox<String> seleccionFormaPago;
    // End of variables declaration//GEN-END:variables

    

    
}
