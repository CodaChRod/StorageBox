/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelo;

/**
 *
 * @author UTN
 */
public enum TipoEspacio {
    PEQUENO("Pequeño", 5.0, 25000.0),
    MEDIANO("Mediano", 10.0, 45000.0),
    GRANDE("Grande", 20.0, 70000.0);
    
    private final String descripcion;
    private final double metrosCuadrados;
    private final double precioPorDefecto;
    
    TipoEspacio(String descripcion, double metrosCuadrados, double precioPorDefecto) {
        this.descripcion = descripcion;
        this.metrosCuadrados = metrosCuadrados;
        this.precioPorDefecto = precioPorDefecto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }
    public double getPrecioPorDefecto() {
        return precioPorDefecto;
    }
    //tumbalacasamami
    @Override
    public String toString() {
        return descripcion + " (" + metrosCuadrados + " m²)";
    }
}
