package models;

import java.time.LocalDateTime;

/**
 * Clase abstracta genérica que representa un sensor IoT
 * @param <T> Tipo de dato que mide el sensor
 */
public abstract class Sensor<T> {
    private String id;
    private String tipoCultivo;
    private String ubicacion;
    private LocalDateTime fechaHora;
    private T valorMedicion;
    
    public Sensor(String id, String tipoCultivo, String ubicacion, LocalDateTime fechaHora, T valorMedicion) {
        this.id = id;
        this.tipoCultivo = tipoCultivo;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.valorMedicion = valorMedicion;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTipoCultivo() {
        return tipoCultivo;
    }
    
    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public T getValorMedicion() {
        return valorMedicion;
    }
    
    public void setValorMedicion(T valorMedicion) {
        this.valorMedicion = valorMedicion;
    }
    
    // Método abstracto para obtener el tipo de medición
    public abstract String getTipoMedicion();
    
    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Cultivo: %s | Ubicación: %s | Fecha: %s | %s: %s",
                getTipoMedicion(), id, tipoCultivo, ubicacion, fechaHora, getTipoMedicion(), valorMedicion);
    }
}
