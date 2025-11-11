package utils;

import models.LecturaSensor;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase utilitaria para generar datos de prueba simulados
 * Simula lecturas realistas de diferentes tipos de sensores
 */
public class GeneradorDatos {
    private static final String[] CULTIVOS = {"Café", "Flores", "Banano", "Palma Africana"};
    private static final String[] TIPOS_MEDICION = {
        "Temperatura", "Humedad", "pH", "Radiación Solar", "Nutrientes"
    };
    private static final String[] UBICACIONES = {
        "Zona Norte", "Zona Sur", "Zona Este", "Zona Oeste", "Zona Central"
    };
    
    private Random random;
    
    public GeneradorDatos() {
        this.random = new Random();
    }
    
    /**
     * Genera una lectura aleatoria de tipo Double (temperatura, humedad, pH, radiación)
     * @param indice Índice de la lectura para generar ID único
     * @return LecturaSensor con valores aleatorios
     */
    public LecturaSensor<Double> generarLecturaDouble(int indice) {
        String tipoMedicion = TIPOS_MEDICION[random.nextInt(TIPOS_MEDICION.length - 1)]; // Excluye Nutrientes
        String cultivo = CULTIVOS[random.nextInt(CULTIVOS.length)];
        String ubicacion = UBICACIONES[random.nextInt(UBICACIONES.length)];
        
        // Generar valor según el tipo de medición
        Double valor = generarValorSegunTipo(tipoMedicion);
        
        // Generar fecha aleatoria en los últimos 30 días
        LocalDateTime fechaHora = generarFechaAleatoria();
        
        String sensorId = String.format("SENSOR-%s-%04d", 
            tipoMedicion.toUpperCase().replace(" ", ""), indice);
        
        return new LecturaSensor<>(sensorId, cultivo, tipoMedicion, valor, fechaHora, ubicacion);
    }
    
    /**
     * Genera una lectura de nutrientes (Integer)
     * @param indice Índice de la lectura
     * @return LecturaSensor de tipo Integer
     */
    public LecturaSensor<Integer> generarLecturaNutrientes(int indice) {
        String cultivo = CULTIVOS[random.nextInt(CULTIVOS.length)];
        String ubicacion = UBICACIONES[random.nextInt(UBICACIONES.length)];
        
        // Nivel de nutrientes entre 50 y 500 ppm
        Integer valor = 50 + random.nextInt(451);
        
        LocalDateTime fechaHora = generarFechaAleatoria();
        
        String sensorId = String.format("SENSOR-NUTRIENTES-%04d", indice);
        
        return new LecturaSensor<>(sensorId, cultivo, "Nutrientes", valor, fechaHora, ubicacion);
    }
    
    /**
     * Genera un valor realista según el tipo de medición
     * @param tipoMedicion Tipo de sensor
     * @return Valor Double simulado
     */
    private Double generarValorSegunTipo(String tipoMedicion) {
        switch (tipoMedicion) {
            case "Temperatura":
                // Temperatura entre 15°C y 35°C
                return 15.0 + (random.nextDouble() * 20.0);
                
            case "Humedad":
                // Humedad entre 40% y 95%
                return 40.0 + (random.nextDouble() * 55.0);
                
            case "pH":
                // pH entre 4.5 y 8.5
                return 4.5 + (random.nextDouble() * 4.0);
                
            case "Radiación Solar":
                // Radiación entre 0 W/m² y 1200 W/m²
                return random.nextDouble() * 1200.0;
                
            default:
                return random.nextDouble() * 100.0;
        }
    }
    
    /**
     * Genera una fecha aleatoria en los últimos 30 días
     * @return LocalDateTime aleatorio
     */
    private LocalDateTime generarFechaAleatoria() {
        LocalDateTime ahora = LocalDateTime.now();
        long minutosEnUnMes = 30L * 24L * 60L; // 30 días en minutos
        
        long minutosAleatorios = ThreadLocalRandom.current().nextLong(minutosEnUnMes);
        
        return ahora.minusMinutes(minutosAleatorios);
    }
    
    /**
     * Obtiene un cultivo aleatorio
     * @return Nombre del cultivo
     */
    public String getCultivoAleatorio() {
        return CULTIVOS[random.nextInt(CULTIVOS.length)];
    }
    
    /**
     * Obtiene un tipo de medición aleatorio
     * @return Tipo de medición
     */
    public String getTipoMedicionAleatorio() {
        return TIPOS_MEDICION[random.nextInt(TIPOS_MEDICION.length)];
    }
}
