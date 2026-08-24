/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import exepciones.StorageBoxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Cliente;

/**
 *
 * @author bycha
 */
public class Clientes {
        // Estructura dinámica: HashMap
    private Map<String, Cliente> mapaClientes;
    public Clientes() {
        this.mapaClientes = new HashMap<>();
    }
    /**
     * Registra un nuevo cliente validando campos obligatorios, fecha y duplicados.
     */
    public void agregarCliente(String identificacion, String nombre, String telefono, LocalDate fechaNacimiento, String correo) throws StorageBoxException {
        if (identificacion == null || identificacion.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) {
            throw new StorageBoxException("Todos los campos obligatorios deben ser completados.");
        }
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new StorageBoxException("La fecha de nacimiento no es válida.");
        }
        if (mapaClientes.containsKey(identificacion.trim())) {
            throw new StorageBoxException("Ya existe un cliente registrado con la identificación: " + identificacion);
        }
        Cliente nuevo = new Cliente(identificacion.trim(), nombre.trim(), telefono.trim(), fechaNacimiento, correo.trim());
        mapaClientes.put(nuevo.getIdentificacion(), nuevo);
    }
    /**
     * Modifica nombre, teléfono y correo del cliente.
     */
    public void actualizarCliente(String identificacion, String nombre, String telefono, String correo) throws StorageBoxException {
        Cliente cliente = buscarPorIdentificacion(identificacion);
        if (cliente == null) {
            throw new StorageBoxException("El cliente con identificación " + identificacion + " no existe.");
        }
        if (nombre == null || nombre.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) {
            throw new StorageBoxException("Los datos obligatorios no pueden estar vacíos.");
        }
        cliente.setNombre(nombre.trim());
        cliente.setTelefono(telefono.trim());
        cliente.setCorreoElectronico(correo.trim());
    }
    /**
     * Elimina un cliente. Si tiene contratos activos o pendientes (validado por el sistema), lanza excepción.
     * @param tieneContratosActivosOPendientes booleano proporcionado por la verificación de contratos.
     */
    public void eliminarCliente(String identificacion, boolean tieneContratosActivosOPendientes) throws StorageBoxException {
        if (!mapaClientes.containsKey(identificacion)) {
            throw new StorageBoxException("El cliente no se encuentra registrado.");
        }
        if (tieneContratosActivosOPendientes) {
            throw new StorageBoxException("No se puede eliminar el cliente porque tiene contratos pendientes o activos asociados.");
        }
        mapaClientes.remove(identificacion);
    }
    public Cliente buscarPorIdentificacion(String identificacion) {
        if (identificacion == null) return null;
        return mapaClientes.get(identificacion.trim());
    }
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(mapaClientes.values());
    }
    /**
     * Filtra clientes por coincidencia de texto en identificación, nombre o correo.
     */
    public List<Cliente> buscarClientes(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return obtenerTodos();
        }
        String crit = criterio.trim().toLowerCase();
        List<Cliente> filtrados = new ArrayList<>();
        for (Cliente c : mapaClientes.values()) {
            if (c.getIdentificacion().toLowerCase().contains(crit) ||
                c.getNombre().toLowerCase().contains(crit) ||
                c.getCorreoElectronico().toLowerCase().contains(crit)) {
                filtrados.add(c);
            }
        }
        return filtrados;
    }
}
