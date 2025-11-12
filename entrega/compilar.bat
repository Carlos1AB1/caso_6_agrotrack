@echo off
echo ╔════════════════════════════════════════════════════════════════╗
echo ║         AGROTRACK - Compilando Sistema IoT v2.0               ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

REM Crear directorio bin si no existe
if not exist "bin" (
    echo 📁 Creando directorio bin...
    mkdir bin
)

REM Compilar todos los archivos Java
echo 🔨 Compilando archivos Java...
javac -encoding UTF-8 -d bin -sourcepath src src\Main.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ ¡Compilación exitosa!
    echo.
    echo Para ejecutar el programa, use: ejecutar.bat
    echo O manualmente: java -cp bin Main
) else (
    echo.
    echo ❌ Error en la compilación
    exit /b 1
)

pause
