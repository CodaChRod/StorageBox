/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



import exepciones.StorageBoxException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bycha
 */

public class Contrato extends Cliente {
    
    private int numeroContrato;
    private Cliente cliente;
    private Espacio espacio;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private EstadosCTR estado;
    private List<ServicioAdicional> serviciosAdicionales;
    
    public Contrato(int numeroContrato, Cliente cliente, Espacio espacio, LocalDate fechaInicio, LocalDate fechaFinalizacion) {
        this.numeroContrato = numeroContrato;
        this.cliente = cliente;
        this.espacio = espacio;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = EstadosCTR.Pendiente; // Pendiente por defecto
        this.serviciosAdicionales = new ArrayList<>();
    }
    
    public int getNumeroContrato() { return numeroContrato; }
    public Cliente getCliente() { return cliente; }
    public Espacio getEspacio() { return espacio; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFinalizacion() { return fechaFinalizacion; }
    public EstadosCTR getEstado() { return estado; }
    public void setEstado(EstadosCTR estado) { this.estado = estado; }
    public List<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }
    public void agregarServicio(ServicioAdicional servicio) {
        if (servicio != null) {
            this.serviciosAdicionales.add(servicio);
        }
    }
    
    // Cálculos propios del negocio solicitados por la rúbrica:
    public long getCantidadDias() {
        if (fechaInicio == null || fechaFinalizacion == null) return 0;
        return ChronoUnit.DAYS.between(fechaInicio, fechaFinalizacion);
    }
    
    public int getCantidadPeriodos() {
        long dias = getCantidadDias();
        if (dias <= 0) return 1;
        return (int) Math.ceil(dias / 30.0); // Cada período corresponde a 30 días o fracción
    }
    
    public double getTotalServicios() {
        double total = 0.0;
        for (ServicioAdicional s : serviciosAdicionales) {
            total += s.getPrecio();
        }
        return total;
    }
    
    public double getTotal() {
        double costoEspacio = (espacio != null ? espacio.getPrecioMensual() : 0.0) * getCantidadPeriodos();
        return costoEspacio + getTotalServicios();
        
    }
    public double getSubtotal() {
        // Los precios ya incluyen impuestos según el enunciado (13% IVA desglosado)
        return getTotal() / 1.13;
    }
    public double getImpuestos() {
        return getTotal() - getSubtotal();
    }
    
    // Métodos de cambio de estado con excepciones de regla de negocio
    public void activarContrato() throws StorageBoxException {
        if (estado != EstadosCTR.Pendiente) {
            throw new StorageBoxException("Solo se puede activar un contrato en estado Pendiente.");
        }
        this.estado = EstadosCTR.Activo;
        if (espacio != null) {
            espacio.setEstadoOcupacion(EstadoEspacio.OCUPADO);
        }
    }
    public void finalizarContrato() throws StorageBoxException {
        if (estado != EstadosCTR.Activo) {
            throw new StorageBoxException("Solo se puede finalizar un contrato en estado Activo.");
        }
        this.estado = EstadosCTR.Finalizado;
        if (espacio != null) {
            espacio.setEstadoOcupacion(EstadoEspacio.DISPONIBLE);
        }
    }
    public void cancelarContrato() throws StorageBoxException {
        if (estado != EstadosCTR.Pendiente) {
            throw new StorageBoxException("Solo se puede cancelar un contrato en estado Pendiente.");
        }
        this.estado = EstadosCTR.Cancelado;
        if (espacio != null) {
            espacio.setEstadoOcupacion(EstadoEspacio.DISPONIBLE);
        }
    }
   }
   

   
    
   

