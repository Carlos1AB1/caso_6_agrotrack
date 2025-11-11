package models;

import java.time.LocalDateTime;

/**
 * Sensor especializado para medir radiación solar
 */
public class SensorRadiacion extends Sensor<Double> {
    
    public SensorRadiacion(String id, String tipoCultivo, String ubicacion, 
                           LocalDateTime fechaHora, Double radiacion) {
        super(id, tipoCultivo, ubicacion, fechaHora, radiacion);
    }
    
    @Override
    public String getTipoMedicion() {
        return "Radiación Solar (W/m²)";
    }
}
