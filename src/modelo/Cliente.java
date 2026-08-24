/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.time.Period;


/**
 *
 * @author UTN
 */
public class Cliente extends Persona {
       private LocalDate fechaNacimiento;
    private String correoElectronico;
    public Cliente(String identificacion, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico) {
        super(identificacion, nombre, telefono);
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }
    public Cliente() {
    }
   
    public int getEdad() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    @Override
    public String toString() {
        return super.toString() + " (" + getEdad() + " años)";
    }
}
