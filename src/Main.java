import monitor.AgroTrackMonitor;
import models.LecturaSensor;
import utils.GeneradorDatos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * Clase principal de AgroTrack - Sistema de Monitoreo Agrícola IoT
 * Demuestra el uso de clases y estructuras genéricas en Java
 * 
 * @author AgroTrack Technologies S.A.S.
 * @version 2.0
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     AGROTRACK TECHNOLOGIES S.A.S. - Sistema IoT v2.0         ║");
        System.out.println("║          Sistema de Monitoreo Agrícola Inteligente            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        // Crear instancias del monitor genérico
        AgroTrackMonitor<Double> monitorDouble = new AgroTrackMonitor<>();
        AgroTrackMonitor<Integer> monitorInteger = new AgroTrackMonitor<>();
        
        // Generador de datos
        GeneradorDatos generador = new GeneradorDatos();
        
        // ============================================================
        // REQUERIMIENTO 1: Simular 10,000 lecturas de sensores
        // ============================================================
        System.out.println("\n═══ FASE 1: REGISTRO DE 10,000 LECTURAS ═══");
        System.out.println("Generando lecturas de sensores IoT...\n");
        
        long tiempoInicio = System.currentTimeMillis();
        
        // Generar 8000 lecturas de tipo Double (temperatura, humedad, pH, radiación)
        for (int i = 0; i < 8000; i++) {
            LecturaSensor<Double> lectura = generador.generarLecturaDouble(i);
            monitorDouble.registrarLectura(lectura);
            
            if ((i + 1) % 2000 == 0) {
                System.out.println("✓ " + (i + 1) + " lecturas Double registradas...");
            }
        }
        
        // Generar 2000 lecturas de tipo Integer (nutrientes)
        for (int i = 0; i < 2000; i++) {
            LecturaSensor<Integer> lectura = generador.generarLecturaNutrientes(i);
            monitorInteger.registrarLectura(lectura);
            
            if ((i + 1) % 500 == 0) {
                System.out.println("✓ " + (i + 1) + " lecturas Integer registradas...");
            }
        }
        
        long tiempoRegistro = System.currentTimeMillis() - tiempoInicio;
        
        System.out.println("\n✅ TOTAL: 10,000 lecturas registradas exitosamente");
        System.out.println("⏱️  Tiempo de registro: " + tiempoRegistro + " ms");
        
        // ============================================================
        // REQUERIMIENTO 2: Ordenar lecturas por fecha/hora
        // ============================================================
        System.out.println("\n\n═══ FASE 2: ORDENAMIENTO POR FECHA/HORA ═══");
        System.out.println("Las lecturas se mantienen ordenadas automáticamente usando TreeSet");
        System.out.println("Complejidad: O(log n) para cada inserción\n");
        
        // Mostrar primeras 10 lecturas ordenadas
        monitorDouble.mostrarPrimerasLecturas(10);
        
        // Mostrar últimas 10 lecturas ordenadas
        monitorDouble.mostrarUltimasLecturas(10);
        
        // ============================================================
        // REQUERIMIENTO 3: Cola para procesar lecturas en orden
        // ============================================================
        System.out.println("\n\n═══ FASE 3: PROCESAMIENTO EN COLA (FIFO) ═══");
        System.out.println("Procesando lecturas usando estructura Queue (LinkedList)");
        System.out.println("Complejidad: O(1) para encolar/desencolar\n");
        
        long tiempoProcesamientoInicio = System.currentTimeMillis();
        
        // Procesar todas las lecturas en la cola
        monitorDouble.procesarTodasLasLecturas();
        monitorInteger.procesarTodasLasLecturas();
        
        long tiempoProcesamiento = System.currentTimeMillis() - tiempoProcesamientoInicio;
        System.out.println("⏱️  Tiempo de procesamiento: " + tiempoProcesamiento + " ms");
        
        // ============================================================
        // ESTADÍSTICAS DEL SISTEMA
        // ============================================================
        System.out.println("\n\n═══ ESTADÍSTICAS GENERALES ═══");
        monitorDouble.generarEstadisticas();
        
        System.out.println("\n--- Monitor de Nutrientes (Integer) ---");
        System.out.println("Total de lecturas: " + monitorInteger.getTotalLecturas());
        System.out.println("Lecturas procesadas: " + monitorInteger.getLecturasProcessadas());
        
        // ============================================================
        // DEMOSTRACIÓN DE FILTROS
        // ============================================================
        System.out.println("\n\n═══ FASE 4: FILTRADO DE DATOS ═══");
        
        // Filtrar por cultivo
        System.out.println("\n--- Filtro por Cultivo: Café ---");
        List<LecturaSensor<Double>> lecturasCafe = monitorDouble.filtrarPorCultivo("Café");
        System.out.println("Lecturas de café encontradas: " + lecturasCafe.size());
        System.out.println("Primeras 5 lecturas:");
        for (int i = 0; i < Math.min(5, lecturasCafe.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + lecturasCafe.get(i));
        }
        
        // Filtrar por tipo de medición
        System.out.println("\n--- Filtro por Tipo: Temperatura ---");
        List<LecturaSensor<Double>> temperaturasLecturas = monitorDouble.filtrarPorTipoMedicion("Temperatura");
        System.out.println("Lecturas de temperatura: " + temperaturasLecturas.size());
        System.out.println("Primeras 5 lecturas:");
        for (int i = 0; i < Math.min(5, temperaturasLecturas.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + temperaturasLecturas.get(i));
        }
        
        // Filtrar por rango de fechas
        System.out.println("\n--- Filtro por Rango de Fechas (últimas 24 horas) ---");
        LocalDateTime hace24Horas = LocalDateTime.now().minusHours(24);
        LocalDateTime ahora = LocalDateTime.now();
        List<LecturaSensor<Double>> lecturasRecientes = monitorDouble.filtrarPorRangoFechas(hace24Horas, ahora);
        System.out.println("Lecturas en las últimas 24 horas: " + lecturasRecientes.size());
        
        // ============================================================
        // ORDENAMIENTO PERSONALIZADO
        // ============================================================
        System.out.println("\n\n═══ FASE 5: ORDENAMIENTO PERSONALIZADO ═══");
        
        // Ordenar por valor de medición (descendente)
        System.out.println("\n--- Top 10 valores más altos ---");
        List<LecturaSensor<Double>> ordenadosPorValor = monitorDouble.ordenarPor(
            Comparator.comparing(LecturaSensor<Double>::getValor).reversed()
        );
        for (int i = 0; i < Math.min(10, ordenadosPorValor.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + ordenadosPorValor.get(i));
        }
        
        // Ordenar por tipo de cultivo
        System.out.println("\n--- Ordenado por tipo de cultivo (alfabético) ---");
        List<LecturaSensor<Double>> ordenadosPorCultivo = monitorDouble.ordenarPor(
            Comparator.<LecturaSensor<Double>, String>comparing(LecturaSensor::getTipoCultivo)
                      .thenComparing(LecturaSensor::getFechaHora)
        );
        System.out.println("Primeras 10 lecturas ordenadas por cultivo:");
        for (int i = 0; i < Math.min(10, ordenadosPorCultivo.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + ordenadosPorCultivo.get(i));
        }
        
        // ============================================================
        // ANÁLISIS DE RENDIMIENTO
        // ============================================================
        System.out.println("\n\n═══ ANÁLISIS DE RENDIMIENTO ═══");
        
        System.out.println("\n--- Prueba de búsqueda por ID de sensor ---");
        long busquedaInicio = System.currentTimeMillis();
        List<LecturaSensor<Double>> lecturasSensor = monitorDouble.obtenerLecturasPorSensor("SENSOR-TEMPERATURA-0001");
        long busquedaTiempo = System.currentTimeMillis() - busquedaInicio;
        System.out.println("Lecturas encontradas: " + lecturasSensor.size());
        System.out.println("Tiempo de búsqueda (HashMap): " + busquedaTiempo + " ms - O(1)");
        
        System.out.println("\n--- Prueba de ordenamiento ---");
        long ordenamientoInicio = System.currentTimeMillis();
        List<LecturaSensor<Double>> ordenadas = monitorDouble.obtenerLecturasOrdenadas();
        long ordenamientoTiempo = System.currentTimeMillis() - ordenamientoInicio;
        System.out.println("Lecturas ordenadas: " + ordenadas.size());
        System.out.println("Tiempo de obtención (TreeSet): " + ordenamientoTiempo + " ms - O(1)");
        
        // ============================================================
        // RESPUESTAS A PREGUNTAS DEL CASO
        // ============================================================
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║           RESPUESTAS A PREGUNTAS DEL CLIENTE                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n❓ PREGUNTA 1: ¿Qué pasaría si se agregan nuevos tipos de sensores?");
        System.out.println("   (por ejemplo, humedad del aire o presencia de plagas)");
        System.out.println("\n✅ RESPUESTA:");
        System.out.println("   Gracias al uso de GENÉRICOS, agregar nuevos sensores es trivial:");
        System.out.println("   1. El sistema ya maneja cualquier tipo T que implemente Comparable<T>");
        System.out.println("   2. No requiere modificar AgroTrackMonitor ni estructuras de datos");
        System.out.println("   3. Solo crear nueva clase que extienda Sensor<T>");
        System.out.println("   4. EJEMPLO: SensorPlagas extends Sensor<Boolean> o SensorHumedadAire extends Sensor<Double>");
        System.out.println("   5. El código es ABIERTO para extensión, CERRADO para modificación (SOLID)");
        
        demonstrarExtensibilidad();
        
        System.out.println("\n\n❓ PREGUNTA 2: ¿Qué ventajas ofrece el uso de genéricos?");
        System.out.println("\n✅ RESPUESTA:");
        System.out.println("   1. SEGURIDAD DE TIPOS: Errores detectados en compilación, no en ejecución");
        System.out.println("   2. REUTILIZACIÓN: Una clase funciona con múltiples tipos de datos");
        System.out.println("   3. ELIMINACIÓN DE CASTING: No se necesita conversión explícita de tipos");
        System.out.println("   4. CÓDIGO LIMPIO: Menos duplicación, más mantenible");
        System.out.println("   5. RENDIMIENTO: Sin overhead de boxing/unboxing innecesario");
        System.out.println("   6. FLEXIBILIDAD: Fácil agregar nuevos tipos de sensores sin cambiar código");
        System.out.println("   7. LEGIBILIDAD: El código es más expresivo y autodocumentado");
        
        // ============================================================
        // RESUMEN FINAL
        // ============================================================
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    RESUMEN EJECUTIVO                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n📊 MÉTRICAS DEL SISTEMA:");
        System.out.println("   • Total de lecturas procesadas: 10,000");
        System.out.println("   • Tiempo total de registro: " + tiempoRegistro + " ms");
        System.out.println("   • Tiempo de procesamiento en cola: " + tiempoProcesamiento + " ms");
        System.out.println("   • Velocidad promedio: " + (10000.0 / (tiempoRegistro + tiempoProcesamiento)) * 1000 + " lecturas/segundo");
        
        System.out.println("\n🏗️ ESTRUCTURAS DE DATOS UTILIZADAS:");
        System.out.println("   • ArrayList<T>: O(1) inserción, O(n) búsqueda");
        System.out.println("   • LinkedList<T> (Queue): O(1) encolar/desencolar");
        System.out.println("   • TreeSet<T>: O(log n) inserción, ordenamiento automático");
        System.out.println("   • HashMap<K,V>: O(1) búsqueda por clave");
        
        System.out.println("\n✅ REQUERIMIENTOS CUMPLIDOS:");
        System.out.println("   ✓ Simulación de 10,000 lecturas");
        System.out.println("   ✓ Sistema de ordenamiento por fecha/hora");
        System.out.println("   ✓ Cola de procesamiento FIFO");
        System.out.println("   ✓ Arquitectura genérica y extensible");
        System.out.println("   ✓ Análisis de complejidad algorítmica");
        
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              SISTEMA COMPLETADO EXITOSAMENTE                   ║");
        System.out.println("║                AgroTrack Technologies S.A.S.                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Demuestra la extensibilidad del sistema con nuevos tipos de sensores
     */
    private static void demonstrarExtensibilidad() {
        System.out.println("\n--- DEMOSTRACIÓN DE EXTENSIBILIDAD ---");
        System.out.println("Simulando agregación de nuevos sensores...\n");
        
        // Monitor para Boolean (presencia de plagas)
        AgroTrackMonitor<Boolean> monitorPlagas = new AgroTrackMonitor<>();
        
        System.out.println("✓ Creado: SensorPlagas<Boolean>");
        for (int i = 0; i < 100; i++) {
            LecturaSensor<Boolean> lectura = new LecturaSensor<>(
                "SENSOR-PLAGAS-" + i,
                "Café",
                "Presencia de Plagas",
                Math.random() > 0.7, // 30% probabilidad de plagas
                LocalDateTime.now().minusHours(i),
                "Zona Norte"
            );
            monitorPlagas.registrarLectura(lectura);
        }
        
        System.out.println("✓ Registradas 100 lecturas de tipo Boolean (Plagas)");
        System.out.println("✓ El sistema funciona sin modificaciones!");
        
        // Filtrar plagas detectadas
        List<LecturaSensor<Boolean>> plagasDetectadas = 
            monitorPlagas.obtenerLecturasOrdenadas().stream()
                .filter(l -> l.getValor() == true)
                .toList();
        
        System.out.println("\n📊 Análisis: " + plagasDetectadas.size() + " detecciones de plagas");
        System.out.println("Primeras 5 alertas:");
        for (int i = 0; i < Math.min(5, plagasDetectadas.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + plagasDetectadas.get(i));
        }
    }
}
