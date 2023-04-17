/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Date;
import java.util.List;
import dao.HuespedDAO;
import jdbcFactory.ConnectionFactory;
import modelo.Huesped;
/**
 *
 * @author CDM-51
 */
public class HuespedController {
    
     private final HuespedDAO huespedDAO;
     
     /**
     * Creando conexion para operaciones con MySQL.
     */
    public HuespedController() {
        this.huespedDAO = new HuespedDAO(new ConnectionFactory().realizarConexion());
    }

   /**
     * Obteniendo el listado de la Base de Datos de los húespedes.
     *
     */
    public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    /**
     * Permite consultar los húespedes acorde a su apellido(s).
     *
     */
    public List<Huesped> listar(String apellido) {
        return huespedDAO.listar(apellido);
    }

    /**
     * Recopila los datos del húesped obtenidos del View y agrega el idReserva,
     * para generar relación con la tabla en MySQL de reservas.
     *
     */
    public void guardar(Huesped huesped, String idReserva) {
        huesped.setIdReserva(idReserva);
        huespedDAO.guardar(huesped);
    }

    /**
     * Recopila los datos del húespedo obtenidos del modelo View para ser
     * procesados en el DAO.
     *
     */
    public int actualizar(Integer idHuesped, String nombre, String apellido, Date fechaNacimiento,
            String nacionalidad, String telefono) {
        return huespedDAO.actualizar(idHuesped, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
    }

    /**
     * Recopila las claves asociadas del húesped y la reserva.
     *
     */
    public int eliminar(Integer idHuesped, String idReserva) {
        return huespedDAO.eliminar(idHuesped, idReserva);
    }
}
