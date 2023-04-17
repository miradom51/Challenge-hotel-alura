/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
/**
 *
 * @author CDM-51
 */
public class Reserva {
    
     private final String idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private BigDecimal valorReserva;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.idReserva = generarIdReserva();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }

    public Reserva(String idReserva, Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }

    
    public String getId_Reserva() {
        return idReserva;
    }

    
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

   
    public Date getFechaSalida() {
        return fechaSalida;
    }

   
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

   
    public BigDecimal getValorReserva() {
        return valorReserva;
    }

    
    public void setValorReserva(BigDecimal valorReserva) {
        this.valorReserva = valorReserva;
    }

   
    public String getFormaPago() {
        return formaPago;
    }

    
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    
    private String generarIdReserva() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public String toString() {
        return String.format("{ID: %s, FechaEntrada: %s, FechaSalida: %s, Total: %f, FormaPago: %s}",
                this.idReserva,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorReserva,
                this.formaPago
        );
    }
}
