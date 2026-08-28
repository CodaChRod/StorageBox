/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import exepciones.StorageBoxException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.Puesto;

/**
 *
 * @author bycha
 */
public class Empleados {
    private List<Empleado> listaEmpleados;
    
    public Empleados() {
        this.listaEmpleados = new ArrayList<>();
    }
    public void agregarEmpleado(String identificacion, String nombre, String telefono, Puesto puesto) throws StorageBoxException {
        if (identificacion == null || identificacion.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            puesto == null) {
            throw new StorageBoxException("Todos los campos del empleado son obligatorios.");
        }
        if (buscarPorIdentificacion(identificacion.trim()) != null) {
            throw new StorageBoxException("Ya existe un empleado con la identificación: " + identificacion);
        }
        Empleado nuevo = new Empleado(identificacion.trim(), nombre.trim(), telefono.trim(), puesto);
        listaEmpleados.add(nuevo);
    }
    public void actualizarEmpleado(String identificacion, String nombre, String telefono, Puesto puesto) throws StorageBoxException {
        Empleado emp = buscarPorIdentificacion(identificacion);
        if (emp == null) {
            throw new StorageBoxException("El empleado no existe.");
        }
        if (nombre == null || nombre.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            puesto == null) {
            throw new StorageBoxException("Los datos no pueden estar vacíos.");
        }
        emp.setNombre(nombre.trim());
        emp.setTelefono(telefono.trim());
        emp.setPuesto(puesto); // Actualiza salario automáticamente
    }
    public void eliminarEmpleado(String identificacion) throws StorageBoxException {
        Empleado emp = buscarPorIdentificacion(identificacion);
        if (emp == null) {
            throw new StorageBoxException("El empleado no existe.");
        }
        listaEmpleados.remove(emp);
    }
    public Empleado buscarPorIdentificacion(String identificacion) {
        if (identificacion == null) return null;
        for (Empleado e : listaEmpleados) {
            if (e.getIdentificacion().equalsIgnoreCase(identificacion.trim())) {
                return e;
            }
        }
        return null;
    }
    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(listaEmpleados);
    }
    public List<Empleado> buscarEmpleados(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return obtenerTodos();
        }
        String crit = criterio.trim().toLowerCase();
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : listaEmpleados) {
            if (e.getIdentificacion().toLowerCase().contains(crit) ||
                e.getNombre().toLowerCase().contains(crit) ||
                e.getPuesto().getNombre().toLowerCase().contains(crit)) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }
}   

