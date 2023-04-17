/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.util.List;
import dao.ReservaDAO;
import jdbcFactory.ConnectionFactory;
import modelo.Reserva;
/**
 *
 * @author CDM-51
 */
public class ReservaController {
    
     private final ReservaDAO reservaDAO;

    /**
     * Creando conexion para operaciones con MySQL con la tabla de reservas.
     */
    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().realizarConexion());
    }

   /**
     * Obteniendo el listado del DAO de huespedes.
     *
     */
    public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    /**
     * Permite consultar las reservas acorde al idReserva.
     *
     */
    public List<Reserva> listar(String idReserva) {
        return reservaDAO.listar(idReserva);
    }

    /**
     * Almacenando la información obtenida del View, en la capa DAO.
     *
     */
    public void guardar(Reserva reserva) {
        reservaDAO.guardar(reserva);
    }

    /**
     * Recopila los datos de la reserva del modelo View.
     *
     */
    public int actualizar(String idReserva, Date fechaEntrada,
            Date fechaSalida, double valorReserva, String formaPago) {
        return reservaDAO.actualizar(idReserva, fechaEntrada, fechaSalida, valorReserva, formaPago);
    }
}
