/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UTN
 * esto es para representar un espacio de almacenamiento, OJO
 */
public class Espacio {
    
    private String numeroEspacio;
    private TipoEspacio tipo;
    private double metrosCuadrados;
    private double precioMensual;
    private EstadoEspacio estadoOcupacion;
    
    public Espacio(String numeroEspacio, TipoEspacio tipo, double metrosCuadrados, double precioMensual) {
        this.numeroEspacio = numeroEspacio;
        this.tipo = tipo;
        this.metrosCuadrados = metrosCuadrados;
        this.precioMensual = precioMensual;
        this.estadoOcupacion = EstadoEspacio.DISPONIBLE; // Disponible por defecto
    }
    public Espacio() {
        this.estadoOcupacion = EstadoEspacio.DISPONIBLE;
    }
    public String getNumeroEspacio() {
        return numeroEspacio;
    }
    public void setNumeroEspacio(String numeroEspacio) {
        this.numeroEspacio = numeroEspacio;
    }
    public TipoEspacio getTipo() {
        return tipo;
    }
    public void setTipo(TipoEspacio tipo) {
        this.tipo = tipo;
    }
    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }
    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }
    public double getPrecioMensual() {
        return precioMensual;
    }
    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }
    public EstadoEspacio getEstadoOcupacion() {
        return estadoOcupacion;
    }
    public void setEstadoOcupacion(EstadoEspacio estadoOcupacion) {
        this.estadoOcupacion = estadoOcupacion;
    }
    public boolean isDisponible() {
        return this.estadoOcupacion == EstadoEspacio.DISPONIBLE;
    }
    @Override
    public String toString() {
        return "Espacio " + numeroEspacio + " [" + tipo.getDescripcion() + "] - ₡" + precioMensual;
    }
}
