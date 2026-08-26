/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import exepciones.StorageBoxException;
import java.util.LinkedList;
import java.util.List;
import modelo.Espacio;
import modelo.EstadoEspacio;
import modelo.TipoEspacio;
/**
 *
 * @author bycha
 */
public class Almacenamiento {
  // Estructura dinámica: LinkedList, No se le olvide
    private List<Espacio> listaEspacios;
    public Almacenamiento() {
        this.listaEspacios = new LinkedList<>();
    }
    /**
     * Registra un nuevo espacio. Disponible por defecto.
     */
    public void agregarEspacio(String numeroEspacio, TipoEspacio tipo, double metros, double precio) throws StorageBoxException {
        if (numeroEspacio == null || numeroEspacio.trim().isEmpty()) {
            throw new StorageBoxException("El número de espacio es obligatorio.");
        }
        if (buscarPorNumero(numeroEspacio.trim()) != null) {
            throw new StorageBoxException("Ya existe un espacio registrado con el número: " + numeroEspacio);
        }
        if (metros <= 0) {
            throw new StorageBoxException("El tamaño en metros cuadrados debe ser mayor a 0.");
        }
        if (precio <= 0) {
            throw new StorageBoxException("El precio mensual debe ser un valor positivo.");
        }
        Espacio nuevo = new Espacio(numeroEspacio.trim(), tipo, metros, precio);
        listaEspacios.add(nuevo);
    }
    /**
     * Actualiza tipo, tamaño y precio. No modifica la disponibilidad manualmente.
     */
    public void actualizarEspacio(String numeroEspacio, TipoEspacio tipo, double metros, double precio) throws StorageBoxException {
        Espacio espacio = buscarPorNumero(numeroEspacio);
        if (espacio == null) {
            throw new StorageBoxException("El espacio #" + numeroEspacio + " no existe.");
        }
        if (metros <= 0 || precio <= 0) {
            throw new StorageBoxException("El tamaño y el precio deben ser mayores a cero.");
        }
        espacio.setTipo(tipo);
        espacio.setMetrosCuadrados(metros);
        espacio.setPrecioMensual(precio);
    }
    /**
     * Elimina un espacio solo si está DISPONIBLE. Si está alquilado/ocupado lanza excepción, magnific
     */
    public void eliminarEspacio(String numeroEspacio) throws StorageBoxException {
        Espacio espacio = buscarPorNumero(numeroEspacio);
        if (espacio == null) {
            throw new StorageBoxException("El espacio no existe.");
        }
        if (espacio.getEstadoOcupacion() == EstadoEspacio.OCUPADO) {
            throw new StorageBoxException("No se puede eliminar el espacio #" + numeroEspacio + " porque actualmente está alquilado / ocupado.");
        }
        listaEspacios.remove(espacio);
    }
    public Espacio buscarPorNumero(String numero) {
        if (numero == null) return null;
        for (Espacio e : listaEspacios) {
            if (e.getNumeroEspacio().equalsIgnoreCase(numero.trim())) {
                return e;
            }
        }
        return null;
    }
    public List<Espacio> obtenerTodos() {
        return new LinkedList<>(listaEspacios);
    }
    /**
     * Filtro avanzado para la ventana de búsqueda (Número, Tipo, Estado, Rango de precio), para que no se nos complique a futuro (spoiler, lo hara)
     */
    public List<Espacio> filtrarEspacios(String numero, TipoEspacio tipo, EstadoEspacio estado, Double precioMin, Double precioMax) {
        List<Espacio> resultado = new LinkedList<>();
        for (Espacio e : listaEspacios) {
            boolean coincide = true;
            if (numero != null && !numero.trim().isEmpty() && !e.getNumeroEspacio().toLowerCase().contains(numero.trim().toLowerCase())) {
                coincide = false;
            }
            if (tipo != null && e.getTipo() != tipo) {
                coincide = false;
            }
            if (estado != null && e.getEstadoOcupacion() != estado) {
                coincide = false;
            }
            if (precioMin != null && e.getPrecioMensual() < precioMin) {
                coincide = false;
            }
            if (precioMax != null && e.getPrecioMensual() > precioMax) {
                coincide = false;
            }
            if (coincide) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}
