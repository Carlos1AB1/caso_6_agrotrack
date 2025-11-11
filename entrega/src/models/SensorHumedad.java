package models;

import java.time.LocalDateTime;

/**
 * Sensor especializado para medir humedad
 */
public class SensorHumedad extends Sensor<Double> {
    
    public SensorHumedad(String id, String tipoCultivo, String ubicacion, 
                         LocalDateTime fechaHora, Double humedad) {
        super(id, tipoCultivo, ubicacion, fechaHora, humedad);
    }
    
    @Override
    public String getTipoMedicion() {
        return "Humedad (%)";
    }
}
