package monitor;

import models.LecturaSensor;
import java.util.*;
import java.time.LocalDateTime;

/**
 * Clase genérica para el almacenamiento y gestión de lecturas de sensores
 * Utiliza estructuras de datos genéricas para máxima flexibilidad y reutilización
 * @param <T> Tipo de dato comparable de las mediciones
 */
public class AgroTrackMonitor<T extends Comparable<T>> {
    // ArrayList genérico para almacenar todas las lecturas - O(1) inserción al final
    private List<LecturaSensor<T>> lecturas;
    
    // Cola genérica para procesar lecturas en orden FIFO - O(1) inserción y eliminación
    private Queue<LecturaSensor<T>> colaProcesamientos;
    
    // TreeSet para mantener lecturas ordenadas por fecha - O(log n) inserción
    private TreeSet<LecturaSensor<T>> lecturasOrdenadas;
    
    // HashMap para indexación rápida por ID de sensor - O(1) búsqueda
    private Map<String, List<LecturaSensor<T>>> lecturasPorSensor;
    
    // Contador de lecturas procesadas
    private int lecturasProcessadas;
    
    public AgroTrackMonitor() {
        this.lecturas = new ArrayList<>();
        this.colaProcesamientos = new LinkedList<>();
        this.lecturasOrdenadas = new TreeSet<>();
        this.lecturasPorSensor = new HashMap<>();
        this.lecturasProcessadas = 0;
    }
    
    /**
     * Registra una nueva lectura en todas las estructuras de datos
     * Complejidad: O(log n) dominado por TreeSet
     * @param lectura La lectura a registrar
     */
    public void registrarLectura(LecturaSensor<T> lectura) {
        // O(1) - Inserción al final del ArrayList
        lecturas.add(lectura);
        
        // O(1) - Encolar en LinkedList
        colaProcesamientos.offer(lectura);
        
        // O(log n) - Inserción en TreeSet ordenado
        lecturasOrdenadas.add(lectura);
        
        // O(1) amortizado - Inserción en HashMap
        lecturasPorSensor.computeIfAbsent(lectura.getSensorId(), k -> new ArrayList<>()).add(lectura);
    }
    
    /**
     * Procesa la siguiente lectura en la cola
     * Complejidad: O(1)
     * @return La lectura procesada o null si la cola está vacía
     */
    public LecturaSensor<T> procesarSiguienteLectura() {
        LecturaSensor<T> lectura = colaProcesamientos.poll();
        if (lectura != null) {
            lecturasProcessadas++;
            return lectura;
        }
        return null;
    }
    
    /**
     * Procesa todas las lecturas pendientes en la cola
     * Complejidad: O(n) donde n es el número de lecturas en la cola
     */
    public void procesarTodasLasLecturas() {
        System.out.println("\n=== PROCESANDO LECTURAS EN COLA ===");
        int contador = 0;
        while (!colaProcesamientos.isEmpty()) {
            LecturaSensor<T> lectura = procesarSiguienteLectura();
            if (lectura != null) {
                contador++;
                if (contador <= 10 || contador % 1000 == 0) {
                    System.out.println("Procesando lectura #" + contador + ": " + lectura);
                }
            }
        }
        System.out.println("Total de lecturas procesadas: " + contador);
    }
    
    /**
     * Obtiene todas las lecturas ordenadas por fecha/hora
     * Complejidad: O(1) ya que TreeSet mantiene el orden
     * @return Lista ordenada de lecturas
     */
    public List<LecturaSensor<T>> obtenerLecturasOrdenadas() {
        return new ArrayList<>(lecturasOrdenadas);
    }
    
    /**
     * Filtra lecturas por tipo de cultivo
     * Complejidad: O(n) donde n es el número total de lecturas
     * @param tipoCultivo El tipo de cultivo a filtrar
     * @return Lista de lecturas filtradas
     */
    public List<LecturaSensor<T>> filtrarPorCultivo(String tipoCultivo) {
        List<LecturaSensor<T>> resultado = new ArrayList<>();
        for (LecturaSensor<T> lectura : lecturas) {
            if (lectura.getTipoCultivo().equalsIgnoreCase(tipoCultivo)) {
                resultado.add(lectura);
            }
        }
        return resultado;
    }
    
    /**
     * Filtra lecturas por tipo de medición
     * Complejidad: O(n)
     * @param tipoMedicion El tipo de medición a filtrar
     * @return Lista de lecturas filtradas
     */
    public List<LecturaSensor<T>> filtrarPorTipoMedicion(String tipoMedicion) {
        List<LecturaSensor<T>> resultado = new ArrayList<>();
        for (LecturaSensor<T> lectura : lecturas) {
            if (lectura.getTipoMedicion().equalsIgnoreCase(tipoMedicion)) {
                resultado.add(lectura);
            }
        }
        return resultado;
    }
    
    /**
     * Obtiene todas las lecturas de un sensor específico
     * Complejidad: O(1) gracias al HashMap
     * @param sensorId ID del sensor
     * @return Lista de lecturas del sensor
     */
    public List<LecturaSensor<T>> obtenerLecturasPorSensor(String sensorId) {
        return lecturasPorSensor.getOrDefault(sensorId, new ArrayList<>());
    }
    
    /**
     * Filtra lecturas por rango de fechas
     * Complejidad: O(log n + k) donde k es el número de elementos en el rango
     * @param inicio Fecha de inicio
     * @param fin Fecha de fin
     * @return Lista de lecturas en el rango
     */
    public List<LecturaSensor<T>> filtrarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        List<LecturaSensor<T>> resultado = new ArrayList<>();
        for (LecturaSensor<T> lectura : lecturasOrdenadas) {
            LocalDateTime fecha = lectura.getFechaHora();
            if ((fecha.isEqual(inicio) || fecha.isAfter(inicio)) && 
                (fecha.isEqual(fin) || fecha.isBefore(fin))) {
                resultado.add(lectura);
            } else if (fecha.isAfter(fin)) {
                break; // Como está ordenado, no hay más elementos válidos
            }
        }
        return resultado;
    }
    
    /**
     * Ordena las lecturas usando un comparador personalizado
     * Complejidad: O(n log n) - QuickSort/MergeSort de Java
     * @param comparador El comparador a utilizar
     * @return Lista ordenada de lecturas
     */
    public List<LecturaSensor<T>> ordenarPor(Comparator<LecturaSensor<T>> comparador) {
        List<LecturaSensor<T>> copia = new ArrayList<>(lecturas);
        copia.sort(comparador);
        return copia;
    }
    
    /**
     * Genera estadísticas básicas del sistema
     * Complejidad: O(1)
     */
    public void generarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ===");
        System.out.println("Total de lecturas registradas: " + lecturas.size());
        System.out.println("Lecturas en cola de procesamiento: " + colaProcesamientos.size());
        System.out.println("Lecturas ya procesadas: " + lecturasProcessadas);
        System.out.println("Sensores únicos: " + lecturasPorSensor.size());
        
        // Conteo por tipo de cultivo
        Map<String, Integer> cultivoCounts = new HashMap<>();
        for (LecturaSensor<T> lectura : lecturas) {
            cultivoCounts.merge(lectura.getTipoCultivo(), 1, Integer::sum);
        }
        
        System.out.println("\nDistribución por cultivo:");
        cultivoCounts.forEach((cultivo, count) -> 
            System.out.println("  - " + cultivo + ": " + count + " lecturas"));
        
        // Conteo por tipo de medición
        Map<String, Integer> medicionCounts = new HashMap<>();
        for (LecturaSensor<T> lectura : lecturas) {
            medicionCounts.merge(lectura.getTipoMedicion(), 1, Integer::sum);
        }
        
        System.out.println("\nDistribución por tipo de medición:");
        medicionCounts.forEach((medicion, count) -> 
            System.out.println("  - " + medicion + ": " + count + " lecturas"));
    }
    
    /**
     * Muestra las primeras N lecturas ordenadas
     * Complejidad: O(n) donde n es el límite
     * @param limite Número de lecturas a mostrar
     */
    public void mostrarPrimerasLecturas(int limite) {
        System.out.println("\n=== PRIMERAS " + limite + " LECTURAS ORDENADAS POR FECHA ===");
        int contador = 0;
        for (LecturaSensor<T> lectura : lecturasOrdenadas) {
            if (contador >= limite) break;
            System.out.println((contador + 1) + ". " + lectura);
            contador++;
        }
    }
    
    /**
     * Muestra las últimas N lecturas ordenadas
     * Complejidad: O(n) donde n es el límite
     * @param limite Número de lecturas a mostrar
     */
    public void mostrarUltimasLecturas(int limite) {
        System.out.println("\n=== ÚLTIMAS " + limite + " LECTURAS ORDENADAS POR FECHA ===");
        List<LecturaSensor<T>> lista = new ArrayList<>(lecturasOrdenadas);
        int inicio = Math.max(0, lista.size() - limite);
        for (int i = inicio; i < lista.size(); i++) {
            System.out.println((i - inicio + 1) + ". " + lista.get(i));
        }
    }
    
    // Getters
    public int getTotalLecturas() {
        return lecturas.size();
    }
    
    public int getLecturasEnCola() {
        return colaProcesamientos.size();
    }
    
    public int getLecturasProcessadas() {
        return lecturasProcessadas;
    }
    
    public int getTotalSensores() {
        return lecturasPorSensor.size();
    }
}
