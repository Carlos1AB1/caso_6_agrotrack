# AgroTrack Technologies S.A.S. - Sistema IoT v2.0

## 🌱 Sistema de Monitoreo Agrícola Inteligente

Sistema avanzado de recopilación y análisis de datos IoT para cultivos agrícolas, desarrollado con arquitectura genérica en Java.

---

## 📋 Descripción del Proyecto

AgroTrack es un sistema que recopila información en tiempo real desde sensores instalados en cultivos de:
- ☕ Café
- 🌸 Flores
- 🍌 Banano
- 🌴 Palma Africana

### Tipos de Sensores Soportados

1. **Temperatura** (°C) - Double
2. **Humedad** (%) - Double
3. **pH del Suelo** - Double
4. **Radiación Solar** (W/m²) - Double
5. **Nivel de Nutrientes** (ppm) - Integer
6. **Presencia de Plagas** (Boolean) - Extensible

---

## 🏗️ Arquitectura del Sistema

### Estructura de Clases Genéricas

```
src/
├── Main.java                          # Clase principal con demostración completa
├── models/
│   ├── Sensor.java                   # Clase abstracta genérica <T>
│   ├── LecturaSensor.java            # Clase genérica para lecturas <T>
│   ├── SensorTemperatura.java        # Sensor especializado
│   ├── SensorHumedad.java            # Sensor especializado
│   ├── SensorPH.java                 # Sensor especializado
│   ├── SensorRadiacion.java          # Sensor especializado
│   └── SensorNutrientes.java         # Sensor especializado
├── monitor/
│   └── AgroTrackMonitor.java         # Módulo principal genérico <T>
└── utils/
    └── GeneradorDatos.java           # Generador de datos de prueba
```

---

## 🚀 Características Principales

### 1. **Genéricos en Java**
- Clase `Sensor<T>` abstracta y genérica
- `AgroTrackMonitor<T extends Comparable<T>>`
- Seguridad de tipos en tiempo de compilación
- Reutilización máxima de código

### 2. **Estructuras de Datos Eficientes**

| Estructura | Uso | Complejidad |
|------------|-----|-------------|
| `ArrayList<T>` | Almacenamiento principal | O(1) inserción |
| `LinkedList<T>` (Queue) | Procesamiento FIFO | O(1) encolar/desencolar |
| `TreeSet<T>` | Ordenamiento automático | O(log n) inserción |
| `HashMap<K,V>` | Indexación por ID | O(1) búsqueda |

### 3. **Funcionalidades**

✅ Registro de 10,000+ lecturas  
✅ Ordenamiento automático por fecha/hora  
✅ Cola de procesamiento FIFO  
✅ Filtros por cultivo, tipo de medición, fecha  
✅ Búsqueda rápida por ID de sensor  
✅ Ordenamiento personalizado con Comparators  
✅ Generación de estadísticas y reportes  

---

## 📊 Análisis de Complejidad (Big-O)

### Operaciones Principales

| Operación | Complejidad | Justificación |
|-----------|-------------|---------------|
| `registrarLectura()` | **O(log n)** | Dominado por TreeSet.add() |
| `procesarSiguienteLectura()` | **O(1)** | Queue.poll() es constante |
| `obtenerLecturasOrdenadas()` | **O(1)** | TreeSet ya está ordenado |
| `filtrarPorCultivo()` | **O(n)** | Recorrido lineal completo |
| `filtrarPorRangoFechas()` | **O(log n + k)** | Búsqueda binaria + k elementos |
| `obtenerLecturasPorSensor()` | **O(1)** | HashMap.get() es constante |
| `ordenarPor(Comparator)` | **O(n log n)** | Collections.sort() - Timsort |

---

## 💻 Compilación y Ejecución

### Opción 1: Usando Scripts (Recomendado)

#### En Windows:
```bash
compilar.bat
ejecutar.bat
```

#### En macOS/Linux:
```bash
chmod +x compilar.sh ejecutar.sh
./compilar.sh
./ejecutar.sh
```

### Opción 2: Manual

```bash
# Compilar
javac -d bin -sourcepath src src/Main.java

# Ejecutar
java -cp bin Main
```

---

## 🎯 Requerimientos Cumplidos

### ✅ Requerimiento 1: Simulación de 10,000 lecturas
- Sistema genera y registra 10,000 lecturas de sensores diversos
- 8,000 lecturas tipo Double (temperatura, humedad, pH, radiación)
- 2,000 lecturas tipo Integer (nutrientes)

### ✅ Requerimiento 2: Ordenamiento por fecha/hora
- TreeSet mantiene orden automático
- O(log n) por inserción
- Acceso ordenado en O(1)

### ✅ Requerimiento 3: Cola de procesamiento
- Queue (LinkedList) para FIFO
- O(1) para encolar/desencolar
- Procesa 10,000 lecturas eficientemente

---

## ❓ Respuestas a Preguntas del Cliente

### Pregunta 1: ¿Qué pasaría si se agregan nuevos tipos de sensores?

**Respuesta:**

Gracias al uso de **genéricos**, agregar nuevos sensores es trivial:

1. ✅ El sistema ya maneja cualquier tipo `T` que implemente `Comparable<T>`
2. ✅ No requiere modificar `AgroTrackMonitor` ni estructuras de datos
3. ✅ Solo crear nueva clase que extienda `Sensor<T>`
4. ✅ **Ejemplos:**
   ```java
   // Sensor de humedad del aire
   public class SensorHumedadAire extends Sensor<Double> { ... }
   
   // Sensor de presencia de plagas
   public class SensorPlagas extends Sensor<Boolean> { ... }
   
   // Sensor de velocidad del viento
   public class SensorViento extends Sensor<Double> { ... }
   ```
5. ✅ El código es **ABIERTO para extensión, CERRADO para modificación** (Principio SOLID)

**Demostración en el código:** El sistema incluye ejemplo funcional con `SensorPlagas<Boolean>`

---

### Pregunta 2: ¿Qué ventajas ofrece el uso de genéricos?

**Respuesta:**

| # | Ventaja | Descripción |
|---|---------|-------------|
| 1 | **Seguridad de Tipos** | Errores detectados en compilación, no en ejecución |
| 2 | **Reutilización** | Una clase funciona con múltiples tipos de datos |
| 3 | **Eliminación de Casting** | No se necesita conversión explícita de tipos |
| 4 | **Código Limpio** | Menos duplicación, más mantenible |
| 5 | **Rendimiento** | Sin overhead de boxing/unboxing innecesario |
| 6 | **Flexibilidad** | Fácil agregar nuevos tipos sin cambiar código |
| 7 | **Legibilidad** | El código es más expresivo y autodocumentado |

**Ejemplo práctico:**

```java
// ❌ SIN Genéricos (código anterior)
SensorCafe sensorCafe = new SensorCafe();
SensorFlores sensorFlores = new SensorFlores();
SensorBanano sensorBanano = new SensorBanano();
// ... Código duplicado para cada tipo

// ✅ CON Genéricos (nuevo sistema)
AgroTrackMonitor<Double> monitor = new AgroTrackMonitor<>();
// Funciona para TODOS los tipos de sensores
```

---

## 📈 Métricas de Rendimiento

Resultados de pruebas con 10,000 lecturas:

- **Tiempo de registro:** ~100-200 ms
- **Tiempo de procesamiento:** ~50-100 ms
- **Velocidad:** ~40,000-80,000 lecturas/segundo
- **Memoria:** Eficiente con estructuras optimizadas

---

## 🛠️ Tecnologías Utilizadas

- **Java 11+** (Genéricos, Collections Framework)
- **Estructuras de datos:** ArrayList, LinkedList, TreeSet, HashMap
- **Paradigmas:** OOP, Generics, SOLID Principles

---

## 👥 Equipo de Desarrollo

- **Empresa:** AgroTrack Technologies S.A.S.
- **Docente:** David Cano Baquero
- **Curso:** Ingeniería de Software Dual
- **Institución:** Facultad de Ingenierías y Ciencias Básicas

---

## 📝 Licencia

© 2025 AgroTrack Technologies S.A.S. - Sistema de uso académico

---

## 🔗 Entregables

1. ✅ **Código fuente** completo y funcional
2. ✅ **Informe_General.pdf** con análisis completo
3. ✅ **Evidencias.pdf** con pantallazos de ejecución
4. ✅ **README.md** con documentación técnica
5. ✅ Scripts de compilación y ejecución

---

## 📞 Soporte

Para preguntas o problemas técnicos, contactar al equipo de desarrollo.

**¡Sistema completado exitosamente! ✅**
