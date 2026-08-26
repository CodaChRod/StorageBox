/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import exepciones.StorageBoxException;
import java.util.ArrayList;
import java.util.List;
import modelo.ServicioAdicional;

/**
 *
 * @author bycha y J
 */
public class ServiciosAdicionales {
     // Estructura dinámica: ArrayList, es un array...que tiene list
    private List<ServicioAdicional> listaServicios;
    private int contadorCodigo;
    public ServiciosAdicionales() {
        this.listaServicios = new ArrayList<>();
        this.contadorCodigo = 1;
    }
    /**
     * Genera un código automático con formato "SRV-001", Se ve profesional, No?
     */
    private String generarSiguienteCodigo() {
        String cod = String.format("SRV-%03d", contadorCodigo);
        contadorCodigo++;
        return cod;
    }
    /**
     * Agrega un nuevo servicio generando el código automáticamente.
     */
    public ServicioAdicional agregarServicio(String nombre, String descripcion, double precio) throws StorageBoxException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new StorageBoxException("El nombre del servicio es obligatorio.");
        }
        if (precio <= 0) {
            throw new StorageBoxException("El precio del servicio debe ser mayor a 0.");
        }
        String codigoGenerado = generarSiguienteCodigo();
        ServicioAdicional nuevo = new ServicioAdicional(codigoGenerado, nombre.trim(), descripcion != null ? descripcion.trim() : "", precio);
        listaServicios.add(nuevo);
        return nuevo;
    }
    /**
     * Modifica descripción y precio del servicio.
     */
    public void actualizarServicio(String codigo, String descripcion, double precio) throws StorageBoxException {
        ServicioAdicional servicio = buscarPorCodigo(codigo);
        if (servicio == null) {
            throw new StorageBoxException("El servicio con código " + codigo + " no existe.");
        }
        if (precio <= 0) {
            throw new StorageBoxException("El precio debe ser mayor a 0.");
        }
        servicio.setDescripcion(descripcion != null ? descripcion.trim() : "");
        servicio.setPrecio(precio);
    }
    /**
     * Elimina un servicio registrado.
     */
    public void eliminarServicio(String codigo) throws StorageBoxException {
        ServicioAdicional servicio = buscarPorCodigo(codigo);
        if (servicio == null) {
            throw new StorageBoxException("El servicio no existe.");
        }
        listaServicios.remove(servicio);
    }
    public ServicioAdicional buscarPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (ServicioAdicional s : listaServicios) {
            if (s.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return s;
            }
        }
        return null;
    }
    public List<ServicioAdicional> obtenerTodos() {
        return new ArrayList<>(listaServicios);
    }
    /**
     * Filtra servicios por coincidencia en código o nombre.
     */
    public List<ServicioAdicional> buscarServicios(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return obtenerTodos();
        }
        String crit = criterio.trim().toLowerCase();
        List<ServicioAdicional> filtrados = new ArrayList<>();
        for (ServicioAdicional s : listaServicios) {
            if (s.getCodigo().toLowerCase().contains(crit) || s.getNombre().toLowerCase().contains(crit)) {
                filtrados.add(s);
            }
        }
        return filtrados;
    }   
}
