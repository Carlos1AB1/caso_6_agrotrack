@echo off
echo ╔════════════════════════════════════════════════════════════════╗
echo ║         AGROTRACK - Ejecutando Sistema IoT v2.0               ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

REM Verificar si existe el directorio bin
if not exist "bin" (
    echo ❌ Error: No se encuentra el directorio bin
    echo Por favor, compile primero usando: compilar.bat
    pause
    exit /b 1
)

REM Ejecutar el programa
java -cp bin Main

echo.
echo ═══════════════════════════════════════════════════════════════
echo Ejecución completada

pause
