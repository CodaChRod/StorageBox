    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import exepciones.StorageBoxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Contrato;
import modelo.Espacio;
import modelo.EstadoEspacio;
import modelo.EstadosCTR;
import modelo.ServicioAdicional;
import modelo.TipoEspacio;

/**
 *
 * @author bycha
 */

public class Contratos {
    private List<Contrato> listaContratos;
    private int contadorContratos;
    public Contratos() {
        this.listaContratos = new ArrayList<>();
        this.contadorContratos = 1001; // Autonumérico inicial
    }
    public Contrato crearContrato(Cliente cliente, Espacio espacio, LocalDate fechaInicio, LocalDate fechaFin, List<ServicioAdicional> servicios) throws StorageBoxException {
        if (cliente == null) {
            throw new StorageBoxException("Debe seleccionar un cliente registrado.");
        }
        if (espacio == null) {
            throw new StorageBoxException("Debe seleccionar un espacio disponible.");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new StorageBoxException("Las fechas son obligatorias.");
        }
        if (!fechaFin.isAfter(fechaInicio)) {
            throw new StorageBoxException("La fecha de finalización debe ser posterior a la fecha de inicio.");
        }
        // Validar conflicto de fechas para el mismo espacio
        for (Contrato c : listaContratos) {
            if (c.getEspacio().getNumeroEspacio().equals(espacio.getNumeroEspacio()) &&
               (c.getEstado() == EstadosCTR.Pendiente || c.getEstado() == EstadosCTR.Activo)) {
                // Si los rangos de fecha se solapan
                if (!(fechaFin.isBefore(c.getFechaInicio()) || fechaInicio.isAfter(c.getFechaFinalizacion()))) {
                    throw new StorageBoxException("El espacio ya tiene un contrato activo/pendiente en ese período.");
                }
            }
        }
        Contrato nuevo = new Contrato(contadorContratos++, cliente, espacio, fechaInicio, fechaFin);
        if (servicios != null) {
            for (ServicioAdicional s : servicios) {
                nuevo.agregarServicio(s);
            }
        }
        listaContratos.add(nuevo);
        return nuevo;
    }
    public boolean clienteTieneContratosActivosOPendientes(String cedulaCliente) {
        for (Contrato c : listaContratos) {
            if (c.getCliente().getIdentificacion().equalsIgnoreCase(cedulaCliente) &&
               (c.getEstado() == EstadosCTR.Pendiente || c.getEstado() == EstadosCTR.Activo)) {
                return true;
            }
        }
        return false;
    }
    public Contrato buscarPorNumero(int numero) {
        for (Contrato c : listaContratos) {
            if (c.getNumeroContrato() == numero) {
                return c;
            }
        }
        return null;
    }
    public List<Contrato> obtenerTodos() {
        return new ArrayList<>(listaContratos);
    } 
}
