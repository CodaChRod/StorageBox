/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Almacenamiento;
import controlador.Clientes;
import controlador.ServiciosAdicionales;
import controlador.Empleados;
import controlador.Contratos;

/**
 *
 * @author UTN
 */
public class Modelo {
    private static Modelo instancia;
    
    private Clientes controladorClientes;
    private Almacenamiento controladorEspacios;
    private ServiciosAdicionales controladorServicios;
    private Empleados controladorEmpleados;
    private Contratos controladorContratos;
    
    public Modelo() {
        this.controladorClientes = new Clientes();
        this.controladorEspacios = new Almacenamiento();
        this.controladorServicios = new ServiciosAdicionales();
        this.controladorEmpleados = new Empleados();
        this.controladorContratos = new Contratos();
    }
    public static Modelo getInstancia() {
        if (instancia == null) {
            instancia = new Modelo();
        }
        return instancia;
    }
    public Clientes getControladorClientes() { 
        return controladorClientes;
    }
    public Almacenamiento getControladorEspacios() {
        return controladorEspacios;
    }
    public ServiciosAdicionales getControladorServicios() {
        return controladorServicios;
    }
    public Empleados getControladorEmpleados() { 
        return controladorEmpleados; 
    }
    public Contratos getControladorContratos() {
        return controladorContratos;
    }
}
