/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import modelo.Puesto;

/**
 *
 * @author bycha
 */
public class Empleado extends Persona{

    
    
    public Empleado(String identificacion, String nombre, String telefono) {
        super(identificacion, nombre, telefono);
    }
     Puesto Definido = Puesto.Administrador;

    public void setDefinido(Puesto Definido) {
        this.Definido = Definido;
    }
     
    public void MostrarEstado(){
  switch (Definido){
      case Administrador -> System.out.println("Administrador");
      case Recepcionista -> System.out.println("Recepcionista");
      case Encargado_de_bodega -> System.out.println("Encargado_de_bodega");
      case Mantenimiento -> System.out.println("Mantenimiento");
      case Operador_de_carga -> System.out.println("Operario_de_carga");
      
  }
  }
  
  
}
