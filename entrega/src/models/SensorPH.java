package models;

import java.time.LocalDateTime;

/**
 * Sensor especializado para medir pH del suelo
 */
public class SensorPH extends Sensor<Double> {
    
    public SensorPH(String id, String tipoCultivo, String ubicacion, 
                    LocalDateTime fechaHora, Double pH) {
        super(id, tipoCultivo, ubicacion, fechaHora, pH);
    }
    
    @Override
    public String getTipoMedicion() {
        return "pH";
    }
}
