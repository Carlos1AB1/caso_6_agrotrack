package models;

import java.time.LocalDateTime;

/**
 * Sensor especializado para medir nivel de nutrientes
 */
public class SensorNutrientes extends Sensor<Integer> {
    
    public SensorNutrientes(String id, String tipoCultivo, String ubicacion, 
                            LocalDateTime fechaHora, Integer nivelNutrientes) {
        super(id, tipoCultivo, ubicacion, fechaHora, nivelNutrientes);
    }
    
    @Override
    public String getTipoMedicion() {
        return "Nivel de Nutrientes (ppm)";
    }
}
