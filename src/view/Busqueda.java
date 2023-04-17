/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;
import util.ConvertirFecha;
import util.ListarNacionalidadesUtil;
import util.ValidarFormulariosUtil;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controller.UsuarioController;
import modelo.Usuario;

/**
 *
 * @author CDM-51
 */
public class Busqueda extends javax.swing.JPanel {

    private long diasTranscurridos;
    private final int margenColumna = 2;
    private DefaultTableModel modeloTablaHuespedes;
    private DefaultTableModel modeloTablaReservas;
    private DefaultTableModel modeloTablaUsuarios;
    private final HuespedController huespedController;
    private final ReservaController reservaController;
    private final UsuarioController usuarioController;  
    /**
     * Creates new form Busqueda1
     */
    public Busqueda() {
        initComponents();
        
        this.huespedController = new HuespedController();
        this.reservaController = new ReservaController();
        this.usuarioController = new UsuarioController();
        configurarEstiloComponentes();
    }
    
    
    private void configurarEstiloComponentes() {
        cargarTablaUsuarios();
        configurarAnchoColumnasTabla(tablaUsuarios, margenColumna);
        cargarTablaHuespedes();
        configurarAnchoColumnasTabla(tablaHuespedes, margenColumna);
        cargarTablaReservas();
        configurarAnchoColumnasTabla(tablaReservas, margenColumna);
        seleccionNacionalidad.setModel(new DefaultComboBoxModel<>(ListarNacionalidadesUtil.filtrarNacionalidades()));
        alternarVisualizacionCamposTablas();
        mostrarElementosHuesped();
        ocultarElementosUsuario();
    }
    
    
     private void mostrarElementosHuesped() {
         jLabelTituloHuesped.setVisible(true);
        jLabelInstrucionesHuesped.setVisible(true);
        jLabelNacionalidad.setVisible(true);
        seleccionNacionalidad.setVisible(true);
        jLabelNacimiento.setVisible(true);
        fechaNacimiento.setVisible(true);
        btnEliminar.setVisible(true);
        jLabelPorApellido.setVisible(true);
        btnAyudaHuespedes.setVisible(true);
        alternarEdicionCamposHuespedes();
    }

    private void ocultarElementosHuesped() {
        jLabelTituloHuesped.setVisible(false);
        jLabelInstrucionesHuesped.setVisible(false);
        jLabelNacionalidad.setVisible(false);
        seleccionNacionalidad.setVisible(false);
        jLabelNacimiento.setVisible(false);
        fechaNacimiento.setVisible(false);
        btnEliminar.setVisible(false);
        jLabelPorApellido.setVisible(false);
        btnAyudaHuespedes.setVisible(false);
    }

    private void mostrarElementosReserva() {
        jLabelTituloReservas.setVisible(true);
        jLabelInstrucionesReserva.setVisible(true);
        jLabelEntrada.setVisible(true);
        fechaCheckIn.setVisible(true);
        jLabelSalida.setVisible(true);
        fechaCheckOut.setVisible(true);
        jLabelFormPago.setVisible(true);
        seleccionFormaPago.setVisible(true);
        jLabelPorIdReserva.setVisible(true);
        btnAyudaReservas.setVisible(true);
        alternarEdicionCamposReservas();
    }

    private void ocultarElementosReserva() {
        jLabelTituloReservas.setVisible(false);
        jLabelInstrucionesReserva.setVisible(false);
        jLabelEntrada.setVisible(false);
        fechaCheckIn.setVisible(false);
        jLabelSalida.setVisible(false);
        fechaCheckOut.setVisible(false);
        jLabelFormPago.setVisible(false);
        seleccionFormaPago.setVisible(false);
        jLabelPorIdReserva.setVisible(false);
        btnAyudaReservas.setVisible(false);
    }

    private void mostrarElementosUsuario() {
        jLabelTituloUsuario.setVisible(true);
        tablaUsuarios.setEnabled(true);
        jLabelPorCategoriaUsuario.setVisible(true);
        jLabelCategoria.setVisible(true);
        seleccionCategoriaUsuario.setVisible(true);
        jLabelPassword.setVisible(true);
        campoPassword.setVisible(true);
        btnEliminar.setVisible(true);
        alternarEdicionCamposUsuario();
    }
    
    private void mostrarOpcionesGerente() {
        jLabelTituloUsuario.setVisible(true);
        tablaUsuarios.setEnabled(true);
        jLabelPorCategoriaUsuario.setVisible(true);
        jLabelCategoria.setVisible(true);
        seleccionCategoriaUsuario.setVisible(true);
        jLabelPassword.setVisible(true);
        campoPassword.setVisible(true);
        btnEliminar.setVisible(true);
        alternarEdicionCamposUsuario();
    }
    
    private void mostrarOpcionesRecepcionista() {
        jLabelTituloUsuario.setVisible(true);
        tablaUsuarios.setEnabled(true);
        jLabelPorCategoriaUsuario.setVisible(true);
        jLabelCategoria.setVisible(false);
        seleccionCategoriaUsuario.setVisible(false);
        jLabelPassword.setVisible(false);
        campoPassword.setVisible(false);
        btnActualizar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        alternarEdicionCamposUsuario();
    }

    private void ocultarElementosUsuario() {    
        tablaUsuarios.setEnabled(true);
        jLabelTituloUsuario.setVisible(false);
        jLabelPorCategoriaUsuario.setVisible(false);
        jLabelCategoria.setVisible(false);
        seleccionCategoriaUsuario.setVisible(false);
        jLabelPassword.setVisible(false);
        campoPassword.setVisible(false);
    }

    /**
     * Si la categoría del usuario es "Gerente" se habilitará la edición de la
     * tabla de Usuarios.
     *
     * Cuando alguna de las tabla es visualizada al dar click en las pestañas,
     * sus campos que permiten alterarlas es visualizado, a fin de evitar que lo
     * campos se solapen o encimen con los de otras tablas.
     */
    private void alternarVisualizacionCamposTablas() {
        if (tablaHuespedes.isShowing()) {
            mostrarElementosHuesped();
            ocultarElementosReserva();
            ocultarElementosUsuario();
        } else if (tablaReservas.isShowing()) {
            ocultarElementosHuesped();
            mostrarElementosReserva();
            ocultarElementosUsuario();
        } else {
            ocultarElementosHuesped();
            ocultarElementosReserva();
            if (MenuPrincipal.esGerente()) {
                mostrarOpcionesGerente();
            } else {
                mostrarOpcionesRecepcionista();
            }
        }
    }

    /**
     * Cuando los campos de fecha y selección en la tabla reservas están vacíos,
     * o en su estado inicial estos se deshabilitan.
     */
    private void alternarEdicionCamposReservas() {
        if (fechaCheckIn.getDate() == null && fechaCheckOut.getDate() == null) {
            fechaCheckIn.setEnabled(false);
            fechaCheckOut.setEnabled(false);
            seleccionFormaPago.setEnabled(false);
        } else {
            fechaCheckIn.setEnabled(true);
            fechaCheckOut.setEnabled(true);
            seleccionFormaPago.setEnabled(true);
        }
    }

    /**
     * Cuando los campos de fecha de nacimiento y seleccion de nacionalidad se
     * encuentren en su estado inicial.
     */
    private void alternarEdicionCamposHuespedes() {
        if (fechaNacimiento.getDate() == null && seleccionNacionalidad.getSelectedIndex() == 0) {
            fechaNacimiento.setEnabled(false);
            seleccionNacionalidad.setEnabled(false);
        } else {
            fechaNacimiento.setEnabled(true);
            seleccionNacionalidad.setEnabled(true);
        }
    }

    /**
     * Cuando los campos de usuario se encuentren en su estado inicial o vacíos.
     */
    private void alternarEdicionCamposUsuario() {
        if (seleccionCategoriaUsuario.getSelectedIndex() == 0) {
            seleccionCategoriaUsuario.setEnabled(false);
            campoPassword.setEnabled(false);
        } else {
            seleccionCategoriaUsuario.setEnabled(true);
            seleccionCategoriaUsuario.setEnabled(true);
            campoPassword.setEnabled(true);
        }
    }

    private void limpiarTablaRegistroHuespedes() {
        modeloTablaHuespedes.getDataVector().clear();
        tablaHuespedes.clearSelection();
    }

    private void limpiarTablaRegistroReservas() {
        modeloTablaReservas.getDataVector().clear();
        tablaReservas.clearSelection();
    }

    private void limpiarTablaRegistroUsuarios() {
        modeloTablaUsuarios.getDataVector().clear();
        tablaUsuarios.clearSelection();
    }

    /**
     * Cada vez que el contenido de los registros se actualice debe llamarse
     * después esta función.
     *
     */
    private void configurarAnchoColumnasTabla(JTable tabla, int margen) {
        for (int indiceColumna = 0; indiceColumna < tabla.getColumnCount(); indiceColumna++) {
            ajustarAnchoColumnas(tabla, indiceColumna, margen);
        }
    }

    /**
     * Permite ajustar el ancho de las columnas y las filas de las tablas,
     * acorde al contenido más largo, a fin de mostrar completamente toda la
     * información de cada fila.
     *
     */
    private void ajustarAnchoColumnas(JTable tabla, int indiceColumna, int margenColumna) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) tabla.getColumnModel();
        TableColumn col = colModel.getColumn(indiceColumna);
        int ancho;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = tabla.getTableHeader().getDefaultRenderer();
        }
        Component component = renderer.getTableCellRendererComponent(tabla, col.getHeaderValue(), false, false, 0, 0);
        ancho = component.getPreferredSize().width;
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            renderer = tabla.getCellRenderer(fila, indiceColumna);
            component = renderer.getTableCellRendererComponent(tabla, tabla.getValueAt(fila, indiceColumna), false, false, fila, indiceColumna);
            ancho = Math.max(ancho, component.getPreferredSize().width);
        }
        ancho += 2 * margenColumna;
        col.setPreferredWidth(ancho);
    }

    /**
     * Obtiene la lista de registros de huespedes obtenidos en la base de datos
     * al modelo de la tabla.
     */
    private void cargarTablaHuespedes() {
        modeloTablaHuespedes = (DefaultTableModel) tablaHuespedes.getModel();
        List<Huesped> listaHuespedes = this.huespedController.listar();
        listaHuespedes.forEach((huesped) -> {
            modeloTablaHuespedes.addRow(
                    new Object[]{
                        huesped.getIdHuesped(),
                        huesped.getNombre(),
                        huesped.getApellido(),
                        huesped.getFechaNacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getIdReserva()
                    }
            );
        });
    }

    /**
     * Muestra los registros en la tabla Huespedes acorde al o los apellidos
     * obtenidos del campo de búsqueda.
     *
     */
    private void cargarTablaHuespedes(JTextField campoBusqueda) {
        modeloTablaHuespedes = (DefaultTableModel) tablaHuespedes.getModel();
        String apellido = campoBusqueda.getText();
        List<Huesped> listaHuespedes = this.huespedController.listar(apellido);
        listaHuespedes.forEach((huesped) -> {
            modeloTablaHuespedes.addRow(
                    new Object[]{
                        huesped.getIdHuesped(),
                        huesped.getNombre(),
                        huesped.getApellido(),                      
                        huesped.getFechaNacimiento(),
                        huesped.getNacionalidad(),
                        huesped.getTelefono(),
                        huesped.getIdReserva()
                    }
            );
        });
    }

    /**
     * Ejecuta la actualización de la información en la base de datos, posee
     * validaciones si se modifican los valores en la tabla.
     */
    private void actualizarRegistroHuesped() {
        int fila = tablaHuespedes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            Integer idHuesped = Integer.valueOf(tablaHuespedes.getValueAt(fila, 0).toString());
            String nombre = String.valueOf(tablaHuespedes.getValueAt(fila, 1));
            String apellido = String.valueOf(tablaHuespedes.getValueAt(fila, 2));
            Date fechaNac = Date.valueOf(tablaHuespedes.getValueAt(fila, 3).toString());
            String nacionalidad = String.valueOf(tablaHuespedes.getValueAt(fila, 4));
            String telefono = String.valueOf(tablaHuespedes.getValueAt(fila, 5));
            if (ValidarFormulariosUtil.esFormularioHuespedValido(nombre, apellido, fechaNacimiento, telefono)) {
                Optional.ofNullable(modeloTablaHuespedes.getValueAt(tablaHuespedes.getSelectedRow(), tablaHuespedes.getSelectedColumn()))
                        .ifPresent(row -> {
                            int lineasActualizadas;
                            lineasActualizadas = this.huespedController.actualizar(idHuesped, nombre, apellido, fechaNac, nacionalidad, telefono);
                            JOptionPane.showMessageDialog(
                                    null,
                                    lineasActualizadas + " " + "registro actualizado éxitosamente.",
                                    "Actualización éxitosa.",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        });
            }
        }
    }

    /**
     * Mensaje de confirmación para la cancelación de la actualización de algún
     * registro en el momento de la edición de la tabla Huespedes antes de
     * efectuar la acción en la base de datos.
     *
     */
    private void cancelarActualizacionRegistroHuespedes(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cancelar la actualización de registro actual?\n"
                + "Los cambios efectuados en la tabla se reestablerecán.",
                "Confirmar cancelación de actualización de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            limpiarTablaRegistroHuespedes();
            cargarTablaHuespedes();
            configurarAnchoColumnasTabla(tablaHuespedes, margenColumna);
        }
    }

    /**
     * Obtenemos los valores idHuesped y idReserva de la tabla, para enviarlos
     * al controller, dónde se eliminará el registro.
     */
    private void eliminarRegistroHuesped() {
        int fila = tablaHuespedes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            Optional.ofNullable(modeloTablaHuespedes.getValueAt(tablaHuespedes.getSelectedRow(), tablaHuespedes.getSelectedColumn()))
                    .ifPresent(row -> {
                        Integer idHuesped = Integer.valueOf(tablaHuespedes.getValueAt(fila, 0).toString());
                        String idReserva = String.valueOf(tablaHuespedes.getValueAt(fila, 6));
                        int cantidadEliminada;
                        cantidadEliminada = this.huespedController.eliminar(idHuesped, idReserva);
                        JOptionPane.showMessageDialog(
                                null,
                                cantidadEliminada + " " + "registro eliminado éxitosamente.",
                                "Eliminación de registro éxitosa.",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    });
        }
    }

    /**
     * Mensaje de confirmación para la eliminación de algún registro en la tabla
     * seleccionado.
     *
     */
    private void confirmarEliminacionRegistroHuesped(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Realmente desea eliminar el registro?\n"
                + "El registro será eliminado definitivamente.",
                "Confirmar eliminación de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            reestablecerCampos();
            eliminarRegistroHuesped();
            limpiarTablaRegistroHuespedes();
            limpiarTablaRegistroReservas();
            cargarTablaHuespedes();
            cargarTablaReservas();
            configurarAnchoColumnasTabla(tablaHuespedes, margenColumna);
        }
    }

    /**
     * Modifica la fecha de nacimiento a ser actualizada en el registro de la
     * fila seleccionada en la tabla.
     */
    private void modificarFechaNacimientoEnTablaHuespedes() {
        if (fechaNacimiento.getDate() != null) {
            Date fechaa = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaNacimiento.getDate()));
            tablaHuespedes.setValueAt(fechaa, tablaHuespedes.getSelectedRow(), 3);
        }
    }

    /**
     * Modifica la seleccion de nacionalidad a ser actualizada en el registro de
     * la fila seleccionada en la tabla.
     */
    private void modificarNacionalidadEnTablaHuespedes() {
        int fila = tablaHuespedes.getSelectedRow();
        String seleccion = seleccionNacionalidad.getSelectedItem().toString();
        if (tablaReservas.isRowSelected(fila)) {
            tablaReservas.setValueAt(seleccion, tablaReservas.getSelectedRow(), 4);
        }
    }

    /**
     * Obtuebe la lista de registros de reservas obtenidos en la base de datos
     * al modelo de la tabla.
     */
    private void cargarTablaReservas() {
        modeloTablaReservas = (DefaultTableModel) tablaReservas.getModel();
        List<Reserva> listaReservas = this.reservaController.listar();
        listaReservas.forEach((reserva) -> {
            modeloTablaReservas.addRow(
                    new Object[]{
                        reserva.getId_Reserva(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        reserva.getValorReserva(),
                        reserva.getFormaPago()
                    }
            );
        });
    }

    /**
     * Muestra los registros en la tabla Reservas acorde al idReserva obtenido
     * del campo de búsqueda.
     */
    private void cargarTablaReservas(JTextField campoBusqueda) {
        modeloTablaReservas = (DefaultTableModel) tablaReservas.getModel();
        String idReserva = campoBusqueda.getText();
        List<Reserva> listaReservas = this.reservaController.listar(idReserva);
        listaReservas.forEach((reserva) -> {
            modeloTablaReservas.addRow(
                    new Object[]{
                        reserva.getId_Reserva(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        reserva.getValorReserva(),
                        reserva.getFormaPago()
                    }
            );
        });
    }

    /**
     * Ejecuta la actualización de la información en la base de datos, posee
     * validaciones si se modifican los valores en la tabla.
     */
    private void actualizarRegistroReserva() {
        int fila = tablaReservas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            String idReserva = String.valueOf(tablaReservas.getValueAt(fila, 0));
            Date fechaEntrada = Date.valueOf(tablaReservas.getValueAt(fila, 1).toString());
            Date fechaSalida = Date.valueOf(tablaReservas.getValueAt(fila, 2).toString());
            String valorReservaStringTabla = String.valueOf(tablaReservas.getValueAt(fila, 3));
            double valorReservaToDouble = Double.parseDouble(valorReservaStringTabla);
            String seleccionPago = String.valueOf(tablaReservas.getValueAt(fila, 4));
            if (ValidarFormulariosUtil.esFormularioReservaValido(
                    fechaCheckIn, fechaCheckOut, valorReservaStringTabla, seleccionFormaPago)) {
                Optional.ofNullable(modeloTablaReservas.getValueAt(tablaReservas.getSelectedRow(), tablaReservas.getSelectedColumn()))
                        .ifPresent(row -> {
                            int lineasActualizadas;
                            lineasActualizadas = this.reservaController.actualizar(idReserva, fechaEntrada, fechaSalida,
                                    valorReservaToDouble, seleccionPago);
                            JOptionPane.showMessageDialog(
                                    null,
                                    lineasActualizadas + " " + "registro actualizado éxitosamente.",
                                    "Actualización éxitosa.",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        });
            }
        }
    }

    /**
     * Mensaje de confirmación para la cancelación de la actualización de algún
     * registro en el momento de la edición de la tabla Reservas antes de
     * efectuar la acción en la base de datos.
     *
     */
    private void cancelarActualizacionRegistroReservas(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cancelar la actualización de registro actual?\n"
                + "Los cambios efectuados en la tabla se reestablerecán.",
                "Confirmar cancelación de actualización de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            limpiarTablaRegistroReservas();
            cargarTablaReservas();
            configurarAnchoColumnasTabla(tablaReservas, margenColumna);
        }
    }

    /**
     * Realiza un Wrapping de las fechas de tipo JDateChooser a LocalDate para
     * cálcular el número de días entre las dos fechas ingresadas.
     *
     */
    private long calcularDiasTranscurridos(JDateChooser fechaEntrada, JDateChooser fechaSalida) {
        LocalDate fechDate = ConvertirFecha.convertirDateALocalDate(fechaEntrada.getDate());
        LocalDate date = ConvertirFecha.convertirDateALocalDate(fechaSalida.getDate());
        return diasTranscurridos = ChronoUnit.DAYS.between(fechDate, date);
    }

    private void calcularValorReservas() {
        BigDecimal valorTasaReservaPorDia = new BigDecimal("550.99");
        BigDecimal valorReserva = new BigDecimal("0.0");
        calcularDiasTranscurridos(fechaCheckIn, fechaCheckOut);
        if (diasTranscurridos > 0) {
            BigDecimal diasReservados = new BigDecimal(diasTranscurridos);
            valorReserva = diasReservados.multiply(valorTasaReservaPorDia);
            tablaReservas.setValueAt(valorReserva, tablaReservas.getSelectedRow(), 3);
        } else {
            ValidarFormulariosUtil.desplegarMensajeError(
                    "Error en el cálculo de la Reserva.",
                    "No es posible cálcular reservas si la"
                    + " fecha de Check-Out es menor o igual a la fecha de \n"
                    + " Check-In, ya que el cálculo se realiza por días."
            );
            tablaReservas.setValueAt(valorReserva, tablaReservas.getSelectedRow(), 3);
        }
    }

    /**
     * Modifica la fecha de entrada a ser actualizada en el registro de la fila
     * seleccionada en la tabla.
     */
    private void modificarFechaEntradaEnTablaReservas() {
        Date fechaEntrada = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckIn.getDate()));
        tablaReservas.setValueAt(fechaEntrada, tablaReservas.getSelectedRow(), 1);
    }

    /**
     * Modifica la fecha de salida a ser actualizada en el registro de la fila
     * seleccionada en la tabla.
     */
    private void modificarFechaSalidaEnTablaReservas() {
        Date fechaSalida = Date.valueOf(ConvertirFecha.convertirDateALocalDate(fechaCheckOut.getDate()));
        tablaReservas.setValueAt(fechaSalida, tablaReservas.getSelectedRow(), 2);
    }

    /**
     * Modifica la selección del tipo de pago a ser actualizado en el registro
     * de la fila seleccionada en la tabla.
     */
    private void modificarSeleccionFormaPagoEnTablaReservas() {
        String seleccion = seleccionFormaPago.getSelectedItem().toString();
        int fila = tablaReservas.getSelectedRow();
        if (tablaReservas.isRowSelected(fila)) {
            tablaReservas.setValueAt(seleccion, tablaReservas.getSelectedRow(), 4);
        }
    }

    /**
     * Obtiene la lista de registros de usuarios obtenidos en la base de datos
     * al modelo de la tabla.
     */
    private void cargarTablaUsuarios() {
        modeloTablaUsuarios = (DefaultTableModel) tablaUsuarios.getModel();
        List<Usuario> listaUsuarios = this.usuarioController.listar();
        listaUsuarios.forEach((usuario) -> {
            modeloTablaUsuarios.addRow(
                    new Object[]{
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getCategoriaUsuario()
                    }
            );
        });
    }

    /**
     * Muestra los registros en la tabla usuarios acorde a la categoría del
     * usuario obtenida del campo de búsqueda.
     *
     */
    private void cargarTablaUsuarios(JTextField campoBusqueda) {
        modeloTablaUsuarios = (DefaultTableModel) tablaUsuarios.getModel();
        String categoriaUsuario = campoBusqueda.getText();
        List<Usuario> listaUsuarios = this.usuarioController.listar(categoriaUsuario);
        listaUsuarios.forEach((usuario) -> {
            modeloTablaUsuarios.addRow(
                    new Object[]{
                        usuario.getIdUsuario(),
                        usuario.getNombreUsuario(),
                        usuario.getCategoriaUsuario()
                    }
            );
        });
    }

    /**
     * Ejecuta la actualización de la información en la base de datos, posee
     * validaciones de los campos antes de realizar la actualización.
     */
    private void actualizarRegistroUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            Integer idUsuario = Integer.valueOf(tablaUsuarios.getValueAt(fila, 0).toString());
            String nombreUsuario = String.valueOf(tablaUsuarios.getValueAt(fila, 1));
            String categoriaUsuario = String.valueOf(tablaUsuarios.getValueAt(fila, 2));
            String password = String.valueOf(campoPassword.getPassword());
            if (ValidarFormulariosUtil.esFormularioUsuarioValido(nombreUsuario, seleccionCategoriaUsuario, campoPassword)) {
                Optional.ofNullable(modeloTablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), tablaUsuarios.getSelectedColumn()))
                        .ifPresent(row -> {
                            int lineasActualizadas;
                            lineasActualizadas = this.usuarioController.actualizar(idUsuario, nombreUsuario, categoriaUsuario, password);
                            JOptionPane.showMessageDialog(
                                    null,
                                    lineasActualizadas + " " + "registro actualizado éxitosamente.",
                                    "Actualización éxitosa.",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        });
            }
        }
    }

    /**
     * Mensaje de confirmación para la cancelación de la actualización de algún
     * registro en el momento de la edición de la tabla usuarios antes de
     * efectuar la acción en la base de datos.
     *
     */
    private void cancelarActualizacionRegistroUsuarios(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Desea cancelar la actualización de registro actual?\n"
                + "Los cambios efectuados en la tabla se reestablerecán.",
                "Confirmar cancelación de actualización de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            limpiarTablaRegistroUsuarios();
            cargarTablaUsuarios();
            configurarAnchoColumnasTabla(tablaUsuarios, margenColumna);
        }
    }

    /**
     * Toma como referencia el id del usuario en la tabla, para ser eliminado.
     */
    private void eliminarRegistroUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
        } else {
            Optional.ofNullable(modeloTablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), tablaUsuarios.getSelectedColumn()))
                    .ifPresent(row -> {
                        Integer idUsuario = Integer.valueOf(tablaUsuarios.getValueAt(fila, 0).toString());
                        int cantidadEliminada;
                        cantidadEliminada = this.usuarioController.eliminar(idUsuario);
                        JOptionPane.showMessageDialog(
                                null,
                                cantidadEliminada + " " + "registro eliminado éxitosamente.",
                                "Eliminación de registro éxitosa.",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    });
        }
    }

    /**
     * Mensaje de confirmación para la eliminación de algún registro en la tabla
     * seleccionado.
     *
     */
    private void confirmarEliminacionRegistroUsuario(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                this,
                "¿Realmente desea eliminar el registro?\n"
                + "El registro será eliminado definitivamente.",
                "Confirmar eliminación de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            reestablecerCampos();
            eliminarRegistroUsuario();
            limpiarTablaRegistroUsuarios();
            cargarTablaUsuarios();
            configurarAnchoColumnasTabla(tablaUsuarios, margenColumna);
        }
    }

    /**
     * Modifica la selección de la categoría del usuario a ser actualizado en el
     * registro de la fila seleccionada en la tabla.
     */
    private void modififcarSeleccionCategoriaUsuarioEnTablaUsuarios() {
        String seleccion = seleccionCategoriaUsuario.getSelectedItem().toString();
        int fila = tablaUsuarios.getSelectedRow();
        if (tablaUsuarios.isRowSelected(fila)) {
            tablaUsuarios.setValueAt(seleccion, tablaUsuarios.getSelectedRow(), 2);
        }
    }

    /**
     * Cuando un registro haya sido eliminado, algunos campos mantienen una
     * dependencia en base al evento propertyChange, al eliminar un registro se
     * piede la fila, generando una ArrayOutOfBounException.
     *
     */
    private void reestablecerCampos() {
        fechaNacimiento.setDate(null);
        seleccionNacionalidad.setSelectedIndex(0);
        fechaCheckIn.setDate(null);
        fechaCheckOut.setDate(null);
        seleccionFormaPago.setSelectedIndex(0);
        seleccionCategoriaUsuario.setSelectedIndex(0);
        campoPassword.setText("");
        alternarEdicionCamposHuespedes();
        alternarEdicionCamposReservas();
        alternarEdicionCamposUsuario();
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
        jLabelTituloUsuario = new javax.swing.JLabel();
        jLabelPorApellido = new javax.swing.JLabel();
        jLabelPorCategoriaUsuario = new javax.swing.JLabel();
        jLabelPorIdReserva = new javax.swing.JLabel();
        campoBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JLabel();
        btnAyudaReservas = new javax.swing.JLabel();
        btnAyudaHuespedes = new javax.swing.JLabel();
        jLabelTituloHuesped = new javax.swing.JLabel();
        jLabelTituloReservas = new javax.swing.JLabel();
        fSGradientPanel2 = new LIB.FSGradientPanel();
        jLabelPassword = new javax.swing.JLabel();
        jLabelCategoria = new javax.swing.JLabel();
        seleccionCategoriaUsuario = new javax.swing.JComboBox<>();
        fechaCheckIn = new com.toedter.calendar.JDateChooser();
        fechaCheckOut = new com.toedter.calendar.JDateChooser();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        seleccionFormaPago = new javax.swing.JComboBox<>();
        seleccionNacionalidad = new javax.swing.JComboBox<>();
        jLabelInstrucionesHuesped = new javax.swing.JLabel();
        jLabelInstrucionesReserva = new javax.swing.JLabel();
        campoPassword = new javax.swing.JPasswordField();
        btnEliminar = new LIB.FSButtonMD();
        btnActualizar = new LIB.FSButtonMD();
        btnCancelar = new LIB.FSButtonMD();
        jLabelNacimiento = new javax.swing.JLabel();
        jLabelSalida = new javax.swing.JLabel();
        jLabelEntrada = new javax.swing.JLabel();
        jLabelFormPago = new javax.swing.JLabel();
        jLabelNacionalidad = new javax.swing.JLabel();
        panelTablas = new javax.swing.JTabbedPane();
        scrollTablaHuespedes = new javax.swing.JScrollPane();
        tablaHuespedes = new javax.swing.JTable();
        scrollTablaReservas = new javax.swing.JScrollPane();
        tablaReservas = new javax.swing.JTable();
        scrollTablaUsuarios = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 590));

        panelPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(1050, 590));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1050, 590));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fSGradientPanel1.setFSEndColor(new java.awt.Color(102, 111, 127));
        fSGradientPanel1.setFSGradientFocus(700);
        fSGradientPanel1.setFSStartColor(new java.awt.Color(38, 45, 61));
        fSGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloUsuario.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTituloUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloUsuario.setText("Búsqueda Usuario");
        fSGradientPanel1.add(jLabelTituloUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        jLabelPorApellido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPorApellido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPorApellido.setText("Buscar por Apellido:");
        fSGradientPanel1.add(jLabelPorApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, 20));

        jLabelPorCategoriaUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPorCategoriaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPorCategoriaUsuario.setText("Buscar por Categoría de Usuario:");
        fSGradientPanel1.add(jLabelPorCategoriaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jLabelPorIdReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPorIdReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPorIdReserva.setText("Buscar por ID Reserva:");
        fSGradientPanel1.add(jLabelPorIdReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        campoBusqueda.setBackground(new java.awt.Color(60, 63, 65));
        campoBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        campoBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        campoBusqueda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoBusqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        campoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBusquedaKeyTyped(evt);
            }
        });
        fSGradientPanel1.add(campoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 320, 40));

        btnBuscar.setBackground(new java.awt.Color(51, 51, 51));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda.png"))); // NOI18N
        btnBuscar.setText("   BUSQUEDA");
        btnBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setOpaque(true);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        fSGradientPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 160, 40));

        btnAyudaReservas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAyudaReservas.setForeground(new java.awt.Color(0, 153, 0));
        btnAyudaReservas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAyudaReservas.setText("Ayuda");
        btnAyudaReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAyudaReservas.setOpaque(true);
        btnAyudaReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAyudaReservasMouseClicked(evt);
            }
        });
        fSGradientPanel1.add(btnAyudaReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 70, 30));

        btnAyudaHuespedes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAyudaHuespedes.setForeground(new java.awt.Color(0, 153, 0));
        btnAyudaHuespedes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAyudaHuespedes.setText("Ayuda");
        btnAyudaHuespedes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAyudaHuespedes.setOpaque(true);
        btnAyudaHuespedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAyudaHuespedesMouseClicked(evt);
            }
        });
        fSGradientPanel1.add(btnAyudaHuespedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 70, 30));

        jLabelTituloHuesped.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTituloHuesped.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloHuesped.setText("Búsqueda Huesped");
        fSGradientPanel1.add(jLabelTituloHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        jLabelTituloReservas.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTituloReservas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloReservas.setText("Búsqueda Reservas");
        fSGradientPanel1.add(jLabelTituloReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        panelPrincipal.add(fSGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 100));

        fSGradientPanel2.setBackground(new java.awt.Color(51, 51, 51));
        fSGradientPanel2.setForeground(new java.awt.Color(255, 255, 255));
        fSGradientPanel2.setFSEndColor(new java.awt.Color(102, 111, 127));
        fSGradientPanel2.setFSStartColor(new java.awt.Color(38, 45, 61));
        fSGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contraseña.png"))); // NOI18N
        jLabelPassword.setText("   Cambiar Contraseña");
        fSGradientPanel2.add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jLabelCategoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        jLabelCategoria.setText("   Cambiar Categoria");
        fSGradientPanel2.add(jLabelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        seleccionCategoriaUsuario.setBackground(new java.awt.Color(51, 51, 51));
        seleccionCategoriaUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        seleccionCategoriaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        seleccionCategoriaUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija la categoría de Usuario", "Gerente", "Recepcionista" }));
        seleccionCategoriaUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionCategoriaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionCategoriaUsuarioActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(seleccionCategoriaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, 30));

        fechaCheckIn.setBackground(new java.awt.Color(51, 51, 51));
        fechaCheckIn.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        fechaCheckIn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaCheckIn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckInPropertyChange(evt);
            }
        });
        fSGradientPanel2.add(fechaCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 207, 30));

        fechaCheckOut.setBackground(new java.awt.Color(51, 51, 51));
        fechaCheckOut.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        fechaCheckOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaCheckOut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaCheckOutPropertyChange(evt);
            }
        });
        fSGradientPanel2.add(fechaCheckOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 207, -1));

        fechaNacimiento.setBackground(new java.awt.Color(51, 51, 51));
        fechaNacimiento.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fechaNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        fechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaNacimientoPropertyChange(evt);
            }
        });
        fSGradientPanel2.add(fechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 207, 28));

        seleccionFormaPago.setBackground(new java.awt.Color(51, 51, 51));
        seleccionFormaPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        seleccionFormaPago.setForeground(new java.awt.Color(255, 255, 255));
        seleccionFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija forma de pago", "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en Efectivo" }));
        seleccionFormaPago.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionFormaPagoActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(seleccionFormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 207, 30));

        seleccionNacionalidad.setBackground(new java.awt.Color(51, 51, 51));
        seleccionNacionalidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        seleccionNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        seleccionNacionalidad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        seleccionNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionNacionalidadActionPerformed(evt);
            }
        });
        fSGradientPanel2.add(seleccionNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 207, 30));

        jLabelInstrucionesHuesped.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelInstrucionesHuesped.setForeground(new java.awt.Color(255, 255, 255));
        jLabelInstrucionesHuesped.setText("Para actualizar los campos Fecha de Nacimiento y Nacionalidad, seleccione la fila y actualice el valor que corresponda.");
        fSGradientPanel2.add(jLabelInstrucionesHuesped, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        jLabelInstrucionesReserva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelInstrucionesReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabelInstrucionesReserva.setText("Para actualizar los campos de la tabla seleccione la fila y edite los registros que desee actualizar.");
        fSGradientPanel2.add(jLabelInstrucionesReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        campoPassword.setBackground(new java.awt.Color(51, 51, 51));
        campoPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        campoPassword.setForeground(new java.awt.Color(255, 255, 255));
        campoPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(12, 138, 199), new java.awt.Color(12, 138, 199)));
        fSGradientPanel2.add(campoPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 210, 30));

        btnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("   ELIMINAR");
        btnEliminar.setColorNormal(new java.awt.Color(51, 51, 51));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        fSGradientPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 140, -1));

        btnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        btnActualizar.setText("   EDITAR");
        btnActualizar.setColorNormal(new java.awt.Color(51, 51, 51));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });
        fSGradientPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 140, -1));

        btnCancelar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 102, 102)));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar_1.png"))); // NOI18N
        btnCancelar.setText("   CANCELAR");
        btnCancelar.setColorNormal(new java.awt.Color(51, 51, 51));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        fSGradientPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 140, -1));

        jLabelNacimiento.setBackground(new java.awt.Color(51, 51, 51));
        jLabelNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNacimiento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNacimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fecha.png"))); // NOI18N
        jLabelNacimiento.setText("   Cambiar Fecha Nac");
        fSGradientPanel2.add(jLabelNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, -1));

        jLabelSalida.setBackground(new java.awt.Color(51, 51, 51));
        jLabelSalida.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSalida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek out.png"))); // NOI18N
        jLabelSalida.setText("   Cambiar Chek Out");
        fSGradientPanel2.add(jLabelSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabelEntrada.setBackground(new java.awt.Color(51, 51, 51));
        jLabelEntrada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/chek in.png"))); // NOI18N
        jLabelEntrada.setText("   Cambiar Check In");
        fSGradientPanel2.add(jLabelEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jLabelFormPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelFormPago.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFormPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pago.png"))); // NOI18N
        jLabelFormPago.setText("   Cambiar  Form Pago");
        fSGradientPanel2.add(jLabelFormPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, -1));

        jLabelNacionalidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNacionalidad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNacionalidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nacionalidad.png"))); // NOI18N
        jLabelNacionalidad.setText("   Cambiar Nacionalidad");
        fSGradientPanel2.add(jLabelNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, -1, -1));

        panelPrincipal.add(fSGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1050, 240));

        panelTablas.setBackground(new java.awt.Color(51, 51, 51));
        panelTablas.setForeground(new java.awt.Color(255, 255, 255));
        panelTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTablasMouseClicked(evt);
            }
        });

        tablaHuespedes.setBackground(new java.awt.Color(51, 51, 51));
        tablaHuespedes.setForeground(new java.awt.Color(255, 255, 255));
        tablaHuespedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "ID Reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHuespedes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaHuespedes.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaHuespedes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaHuespedes.getTableHeader().setResizingAllowed(false);
        tablaHuespedes.getTableHeader().setReorderingAllowed(false);
        tablaHuespedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHuespedesMouseClicked(evt);
            }
        });
        scrollTablaHuespedes.setViewportView(tablaHuespedes);

        panelTablas.addTab("Huéspedes", new javax.swing.ImageIcon(getClass().getResource("/imagenes/persona.png")), scrollTablaHuespedes); // NOI18N

        tablaReservas.setBackground(new java.awt.Color(51, 51, 51));
        tablaReservas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tablaReservas.setForeground(new java.awt.Color(255, 255, 255));
        tablaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Reserva", "Fecha de Entrada", "Fecha de Salida", "Total", "Forma de Pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReservas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaReservas.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaReservas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaReservas.getTableHeader().setResizingAllowed(false);
        tablaReservas.getTableHeader().setReorderingAllowed(false);
        tablaReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReservasMouseClicked(evt);
            }
        });
        scrollTablaReservas.setViewportView(tablaReservas);

        panelTablas.addTab("Reservas", new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario.png")), scrollTablaReservas); // NOI18N

        tablaUsuarios.setBackground(new java.awt.Color(51, 51, 51));
        tablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Usuario", "Usuario", "Categoría Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.setSelectionBackground(new java.awt.Color(12, 138, 199));
        tablaUsuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        scrollTablaUsuarios.setViewportView(tablaUsuarios);

        panelTablas.addTab("Usuarios", new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil-del-usuario.png")), scrollTablaUsuarios); // NOI18N

        panelPrincipal.add(panelTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 978, 270));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/third.jpg"))); // NOI18N
        panelPrincipal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 1050, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaHuespedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHuespedesMouseClicked
        if (evt.getClickCount() == 1) {
            int fila = tablaHuespedes.getSelectedRow();
            String fecha = String.valueOf(tablaHuespedes.getValueAt(fila, 3));
            String nacionalidad = String.valueOf(tablaHuespedes.getValueAt(fila, 4));
            Date dt = Date.valueOf(fecha);
            fechaNacimiento.setDate(dt);
            seleccionNacionalidad.setSelectedItem(nacionalidad);
            alternarEdicionCamposHuespedes();
        }
    }//GEN-LAST:event_tablaHuespedesMouseClicked

    private void tablaReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReservasMouseClicked
        if (evt.getClickCount() == 1) {
            int fila = tablaReservas.getSelectedRow();
            String fechaEntradaOfTablaReservas = String.valueOf(tablaReservas.getValueAt(fila, 1));
            String fechaSalidaOfTablaReservas = String.valueOf(tablaReservas.getValueAt(fila, 2));
            String formaPago = String.valueOf(tablaReservas.getValueAt(fila, 4));
            Date fechaEntrada = Date.valueOf(fechaEntradaOfTablaReservas);
            Date fechaSalida = Date.valueOf(fechaSalidaOfTablaReservas);
            seleccionFormaPago.setSelectedItem(formaPago);
            fechaCheckIn.setDate(fechaEntrada);
            fechaCheckOut.setDate(fechaSalida);
            //Aquí se efectua el cálculo de días al seleccionar cualquier fila.
            if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null) {
                //Deshabilitando fechas para evitar que sean editadas si no es seleccionada una fila.
                alternarEdicionCamposReservas();
//                calcularDiasTranscurridos(fechaCheckIn, fechaCheckOut);
            }
        }
    }//GEN-LAST:event_tablaReservasMouseClicked

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        if (evt.getClickCount() == 1) {
            int fila = tablaUsuarios.getSelectedRow();
            String categoriaUsuario = String.valueOf(tablaUsuarios.getValueAt(fila, 2));
            seleccionCategoriaUsuario.setSelectedItem(categoriaUsuario);
            alternarEdicionCamposUsuario();
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void panelTablasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTablasMouseClicked
        evt.consume();
        alternarVisualizacionCamposTablas();
    }//GEN-LAST:event_panelTablasMouseClicked

    private void campoBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBusquedaKeyTyped
        char comodin = '%';
        if (evt.getKeyChar() == comodin) {
            evt.consume();
        }
    }//GEN-LAST:event_campoBusquedaKeyTyped

    private void seleccionCategoriaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionCategoriaUsuarioActionPerformed
        evt.getActionCommand();
        modififcarSeleccionCategoriaUsuarioEnTablaUsuarios();
    }//GEN-LAST:event_seleccionCategoriaUsuarioActionPerformed

    private void fechaCheckInPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckInPropertyChange
        if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null && evt.getOldValue() != null) {
            calcularValorReservas();
            modificarFechaEntradaEnTablaReservas();
        }
    }//GEN-LAST:event_fechaCheckInPropertyChange

    private void fechaCheckOutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaCheckOutPropertyChange
        if (fechaCheckIn.getDate() != null && fechaCheckOut.getDate() != null && evt.getOldValue() != null) {
            calcularValorReservas();
            modificarFechaSalidaEnTablaReservas();
        }
    }//GEN-LAST:event_fechaCheckOutPropertyChange

    private void fechaNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaNacimientoPropertyChange
        evt.getPropertyName();
        modificarFechaNacimientoEnTablaHuespedes();
    }//GEN-LAST:event_fechaNacimientoPropertyChange

    private void seleccionFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionFormaPagoActionPerformed
        evt.getActionCommand();
        modificarSeleccionFormaPagoEnTablaReservas();
    }//GEN-LAST:event_seleccionFormaPagoActionPerformed

    private void seleccionNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionNacionalidadActionPerformed
        evt.getActionCommand();
        modificarNacionalidadEnTablaHuespedes();
    }//GEN-LAST:event_seleccionNacionalidadActionPerformed

    private void btnAyudaHuespedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaHuespedesMouseClicked
        evt.consume();
        JOptionPane.showMessageDialog(
            null,
            "Puede actualizar los registros Nombre, Apellido y Fecha de Nacimiento\n"
            + "directamente en la tabla.\n"
            + "Si desea actualizar la Nacionalidad, seleccione la fila que desee "
            + "y cambie el valor en el campo de selección."
        );
    }//GEN-LAST:event_btnAyudaHuespedesMouseClicked

    private void btnAyudaReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaReservasMouseClicked
        evt.consume();
        JOptionPane.showMessageDialog(
            null,
            "Si desea actualizar los registros sólo será posible editar los campos:\n"
            + "Fecha de Entrada.\n"
            + "Fecha de Salida.\n"
            + "Forma de Pago.\n"
            + "El total se cálcula automáticamente al índicar las fechas."
        );
    }//GEN-LAST:event_btnAyudaReservasMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
         evt.consume();
        if (tablaHuespedes.isShowing()) {
            limpiarTablaRegistroHuespedes();
            cargarTablaHuespedes(campoBusqueda);
            configurarAnchoColumnasTabla(tablaHuespedes, margenColumna);
        } else if (tablaReservas.isShowing()) {
            limpiarTablaRegistroReservas();
            cargarTablaReservas(campoBusqueda);
            configurarAnchoColumnasTabla(tablaReservas, margenColumna);
        } else {
            limpiarTablaRegistroUsuarios();
            cargarTablaUsuarios(campoBusqueda);
            configurarAnchoColumnasTabla(tablaUsuarios, margenColumna);
        }
        reestablecerCampos();
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        evt.consume();
        if (tablaHuespedes.isShowing()) {
            actualizarRegistroHuesped();
            limpiarTablaRegistroHuespedes();
            reestablecerCampos();
            cargarTablaHuespedes();
            configurarAnchoColumnasTabla(tablaHuespedes, margenColumna);
        } else if (tablaReservas.isShowing()) {
            actualizarRegistroReserva();
            limpiarTablaRegistroReservas();
            reestablecerCampos();
            cargarTablaReservas();
            configurarAnchoColumnasTabla(tablaReservas, margenColumna);
        } else {
            actualizarRegistroUsuario();
            limpiarTablaRegistroUsuarios();
            reestablecerCampos();
            cargarTablaUsuarios();
            configurarAnchoColumnasTabla(tablaUsuarios, margenColumna);
        }
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        if (tablaHuespedes.isShowing()) {
            confirmarEliminacionRegistroHuesped(evt);
        } else {
            confirmarEliminacionRegistroUsuario(evt);
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        evt.consume();
        if (tablaHuespedes.isShowing()) {
            cancelarActualizacionRegistroHuespedes(evt);
        } else if (tablaReservas.isShowing()) {
            cancelarActualizacionRegistroReservas(evt);
        } else {
            cancelarActualizacionRegistroUsuarios(evt);
        }
    }//GEN-LAST:event_btnCancelarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD btnActualizar;
    private javax.swing.JLabel btnAyudaHuespedes;
    private javax.swing.JLabel btnAyudaReservas;
    private javax.swing.JLabel btnBuscar;
    private LIB.FSButtonMD btnCancelar;
    private LIB.FSButtonMD btnEliminar;
    private javax.swing.JTextField campoBusqueda;
    private javax.swing.JPasswordField campoPassword;
    private LIB.FSGradientPanel fSGradientPanel1;
    private LIB.FSGradientPanel fSGradientPanel2;
    private com.toedter.calendar.JDateChooser fechaCheckIn;
    private com.toedter.calendar.JDateChooser fechaCheckOut;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelEntrada;
    private javax.swing.JLabel jLabelFormPago;
    private javax.swing.JLabel jLabelInstrucionesHuesped;
    private javax.swing.JLabel jLabelInstrucionesReserva;
    private javax.swing.JLabel jLabelNacimiento;
    private javax.swing.JLabel jLabelNacionalidad;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelPorApellido;
    private javax.swing.JLabel jLabelPorCategoriaUsuario;
    private javax.swing.JLabel jLabelPorIdReserva;
    private javax.swing.JLabel jLabelSalida;
    private javax.swing.JLabel jLabelTituloHuesped;
    private javax.swing.JLabel jLabelTituloReservas;
    private javax.swing.JLabel jLabelTituloUsuario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTabbedPane panelTablas;
    private javax.swing.JScrollPane scrollTablaHuespedes;
    private javax.swing.JScrollPane scrollTablaReservas;
    private javax.swing.JScrollPane scrollTablaUsuarios;
    private javax.swing.JComboBox<String> seleccionCategoriaUsuario;
    private javax.swing.JComboBox<String> seleccionFormaPago;
    private javax.swing.JComboBox<String> seleccionNacionalidad;
    private javax.swing.JTable tablaHuespedes;
    private javax.swing.JTable tablaReservas;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
