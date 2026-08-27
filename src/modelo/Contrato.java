/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import exepciones.StorageBoxException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import modelo.Cliente;
import modelo.EstadosCTR;
import static modelo.EstadosCTR.Pendiente;

/**
 *
 * @author bycha
 */

public class Contrato extends Cliente {
   private int numContrato;
   private LocalDateTime ahora;
   private LocalDateTime FechaInicio;
   private LocalDateTime FechaFinalizacion;

    public Contrato(int numContrato, LocalDateTime ahora, LocalDateTime FechaInicio, LocalDateTime FechaFinalizacion) {
        this.numContrato = numContrato;
        this.ahora = ahora;
        this.FechaInicio = FechaInicio;
        this.FechaFinalizacion = FechaFinalizacion;
    }
   
  EstadosCTR estado = EstadosCTR.Pendiente;


    public void setEstado(EstadosCTR estado) {
        this.estado = estado;
    }
 
  public void MostrarEstado(){
  switch (estado){
      case Pendiente -> System.out.println("Pendiente");
      case Activo -> System.out.println("Activo");
      case Finalizado -> System.out.println("Finalizado");
      case Cancelado -> System.out.println("Cancelado");
  }
}
   public void ActivarContrato() throws StorageBoxException{
       if (estado != EstadosCTR.Pendiente){
           throw new StorageBoxException("El contrato no esta disponible actualmente, intentalo de nuevo.");    
       }
   }
    public EstadosCTR FinalizarContrato() throws StorageBoxException{
       if (estado != EstadosCTR.Activo){
           throw new StorageBoxException("El contrato no pudo ser finalizado");   
}
           estado = EstadosCTR.Finalizado;

return estado;
       }
      
     public EstadosCTR CancelarContrato() throws StorageBoxException{
       if (estado != EstadosCTR.Pendiente){
           throw new StorageBoxException("El contrato no puedo ser cancelado");   
}
           estado = EstadosCTR.Cancelado;

return estado;
       }
public void GenerarCodContrato(){
        numContrato++;
        System.out.println("EL numero de contrato es: "+numContrato);

    }
public void ValidacionFechaInicio() throws StorageBoxException{
        if (FechaInicio.isBefore(ahora)){
            throw new StorageBoxException("No es posible ingresar dicha fecha"); 
        }
        
}
        public void ValidacionFechaFinalizacion() throws StorageBoxException{
        if (FechaFinalizacion.isBefore(FechaInicio)){
            throw new StorageBoxException("No es posible ingresar dicha fecha"); 
        }
       
        }
   }
   

   
    
   

