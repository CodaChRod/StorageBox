/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author bycha
 */
public class Empleado extends Persona{
     private Puesto puesto;
    private double salario;
    
    public Empleado(String identificacion, String nombre, String telefono, Puesto puesto) {
        super(identificacion, nombre, telefono);
        this.puesto = puesto;
        this.salario = (puesto != null) ? puesto.getSalario() : 0.0;
    }
    
    public Empleado() {
    }
    public Puesto getPuesto() {
        return puesto;
    }
    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
        if (puesto != null) {
            this.salario = puesto.getSalario(); // Carga automática del salario
        }
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override
    public String toString() {
        return super.toString() + " - " + (puesto != null ? puesto.getNombre() : "Sin puesto");
    }
}
