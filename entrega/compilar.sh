#!/bin/bash

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║         AGROTRACK - Compilando Sistema IoT v2.0               ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Crear directorio bin si no existe
if [ ! -d "bin" ]; then
    echo "📁 Creando directorio bin..."
    mkdir bin
fi

# Compilar todos los archivos Java
echo "🔨 Compilando archivos Java..."
javac -d bin -sourcepath src src/Main.java

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ ¡Compilación exitosa!"
    echo ""
    echo "Para ejecutar el programa, use: ./ejecutar.sh"
    echo "O manualmente: java -cp bin Main"
else
    echo ""
    echo "❌ Error en la compilación"
    exit 1
fi
