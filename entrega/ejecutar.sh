#!/bin/bash

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║         AGROTRACK - Ejecutando Sistema IoT v2.0               ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Verificar si existe el directorio bin
if [ ! -d "bin" ]; then
    echo "❌ Error: No se encuentra el directorio bin"
    echo "Por favor, compile primero usando: ./compilar.sh"
    exit 1
fi

# Ejecutar el programa
java -cp bin Main

echo ""
echo "═══════════════════════════════════════════════════════════════"
echo "Ejecución completada"
