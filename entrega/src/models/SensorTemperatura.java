package models;

import java.time.LocalDateTime;

/**
 * Sensor especializado para medir temperatura
 */
public class SensorTemperatura extends Sensor<Double> {
    
    public SensorTemperatura(String id, String tipoCultivo, String ubicacion, 
                             LocalDateTime fechaHora, Double temperatura) {
        super(id, tipoCultivo, ubicacion, fechaHora, temperatura);
    }
    
    @Override
    public String getTipoMedicion() {
        return "Temperatura (°C)";
    }
}
