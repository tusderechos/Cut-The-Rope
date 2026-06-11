# Verificacion final de balance y sonidos

## Balance

- Los 15 niveles conservan posiciones visibles dentro del mundo.
- Todos los niveles tienen 3 estrellas.
- Los niveles faciles aprobados no se modificaron en esta fase.
- Los niveles medios y dificiles mantienen rutas de estrellas compactas.
- Las rutas de estrellas terminan cerca del monstruo.
- Las estrellas no quedan pegadas injustamente a obstaculos.
- Los niveles con burbuja tienen entrada y salida jugable.
- Los niveles reportados previamente se mantienen cubiertos por tests especificos.

## Sonidos

- El juego conserva los sonidos actuales.
- Las rutas esperadas son:
  - musica de fondo
  - cortar cuerda
  - recolectar estrella
  - burbuja
  - victoria
  - fallo
- Los tests verifican que todos los archivos existan, no esten vacios y tengan cabecera WAV.
- El gestor de audio mantiene carga tolerante para evitar cierres inesperados si un asset falla en runtime.

## Verificacion ejecutada

- `./gradlew core:compileJava lwjgl3:compileJava --warning-mode all`
- `./gradlew core:test --tests com.tusderechos.Juego.audio.RutasAudioTest --rerun-tasks`
- `./gradlew core:test --rerun-tasks`
- `./gradlew build`

Todas las verificaciones pasaron.
