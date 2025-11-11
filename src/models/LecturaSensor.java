package models;

import java.time.LocalDateTime;

/**
 * Clase genérica que representa una lectura de cualquier tipo de sensor
 * @param <T> Tipo de dato de la medición
 */
public class LecturaSensor<T extends Comparable<T>> implements Comparable<LecturaSensor<T>> {
    private String sensorId;
    private String tipoCultivo;
    private String tipoMedicion;
    private T valor;
    private LocalDateTime fechaHora;
    private String ubicacion;
    
    public LecturaSensor(String sensorId, String tipoCultivo, String tipoMedicion, 
                         T valor, LocalDateTime fechaHora, String ubicacion) {
        this.sensorId = sensorId;
        this.tipoCultivo = tipoCultivo;
        this.tipoMedicion = tipoMedicion;
        this.valor = valor;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
    }
    
    // Getters
    public String getSensorId() {
        return sensorId;
    }
    
    public String getTipoCultivo() {
        return tipoCultivo;
    }
    
    public String getTipoMedicion() {
        return tipoMedicion;
    }
    
    public T getValor() {
        return valor;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    @Override
    public int compareTo(LecturaSensor<T> otra) {
        return this.fechaHora.compareTo(otra.fechaHora);
    }
    
    @Override
    public String toString() {
        return String.format("[%s] Sensor: %s | Cultivo: %s | Ubicación: %s | Fecha: %s | Valor: %s",
                tipoMedicion, sensorId, tipoCultivo, ubicacion, fechaHora, valor);
    }
}
