/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelo;

/**
 *
 * @author bycha
 */
public enum Puesto {
   ADMINISTRADOR("Administrador", 950000.0),
    RECEPCIONISTA("Recepcionista", 700000.0),
    ENCARGADO_BODEGA("Encargado de bodega", 650000.0),
    MANTENIMIENTO("Mantenimiento", 600000.0),
    OPERARIO_CARGA("Operario de carga", 575000.0);
    
    private final String nombre;
    private final double salario;
    Puesto(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }
    
    public String getNombre() {
        return nombre;
    }
    public double getSalario() {
        return salario;
    }
    @Override
    public String toString() {
        return nombre + " (₡" + String.format("%,.0f", salario) + ")";
    }
}
