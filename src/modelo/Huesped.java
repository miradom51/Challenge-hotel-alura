/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
/**
 *
 * @author CDM-51
 */
public class Huesped {
 
    private Integer idHuesped;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private String idReserva;

    public Huesped(int idHuesped, String nombre, String apellido,
            Date fechaNacimiento, String nacionalidad, String telefono,
            String idReserva) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(String nombre, String apellido, Date fechaNacimiento,
            String nacionalidad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    
    public Integer getIdHuesped() {
        return idHuesped;
    }

    
    public void setIdHuesped(Integer idHuesped) {
        this.idHuesped = idHuesped;
    }

    
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getApellido() {
        return apellido;
    }

    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

   
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

   
    public String getNacionalidad() {
        return nacionalidad;
    }

    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    
    public String getTelefono() {
        return telefono;
    }

    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return String.format("{ID: %s, Nombre: %s, Apellido: %s, FechaNacimiento: %s, Nacionalidad: %s, Teléfono: %s, ID_Reserva: %s}",
                this.idHuesped,
                this.nombre,
                this.apellido,
                this.fechaNacimiento,
                this.nacionalidad,
                this.telefono,
                this.idReserva
        );
    }
}
