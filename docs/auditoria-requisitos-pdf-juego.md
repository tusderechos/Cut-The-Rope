# Auditoria de requisitos del PDF - Modulo de juego

## Alcance

Esta auditoria cubre la parte del juego Cut the Rope implementada en LibGDX/Box2D. El registro completo de usuarios, perfiles, contrasenas, ranking general y menu principal se consideran responsabilidad del modulo externo del proyecto.

## Requisitos cubiertos por el juego

- Java y LibGDX: el proyecto usa Java con LibGDX y Box2D.
- Juego Cut the Rope: el jugador corta cuerdas para llevar el dulce al monstruo.
- Fisica: el dulce usa Box2D, cuerdas con joints, burbujas, gravedad, obstaculos y plataforma movil.
- Minimo cinco niveles: existen cinco niveles normales en categoria `Facil`.
- Dificultad progresiva: los cinco niveles normales aumentan mecanicas y complejidad.
- Estrellas: cada nivel tiene tres estrellas opcionales para mejorar puntaje.
- Victoria: el panel final muestra estrellas, puntaje, tiempo, fallos y siguiente accion.
- Rivalidad: existen retos con categoria, numero de nivel, puntaje objetivo y estrellas objetivo.
- POO: entidades, niveles, pantallas, audio, persistencia, estadisticas, utilidades e interfaces estan separadas.
- Diseno abstracto: `Juego` es clase base abstracta y `CutTheRope` es la implementacion concreta.
- Clase abstracta de entidades: `ElementoJuego`.
- Herencia abstracta: `Obstaculo` hereda de `ElementoJuego`.
- Herencia simple separada: `PersonalizacionDulce` y `PersonalizacionMonstruo` heredan de `PersonalizacionVisual`.
- Interfaces: `Dibujable`, `Actualizable`, `Cortable`, `Personalizable`.
- Enums: `CategoriaDificultad`, `DificultadNivel`, `EstadoNivel`, `ColorDulce`, `ColorMonstruo`.
- Clases finales: utilidades, datos inmutables, rutas, persistencia y estadisticas usan `final` donde aplica.
- Funciones finales: `Juego.create`, `Juego.CambiarPantalla`, `Juego.dispose`, `PersonalizacionVisual.ObtenerColor`.
- Recursion: `CalculadoraEstadisticasRecursiva.SumarEstrellas` y `ObtenerMejorPuntaje`.
- Hilos: `GuardadorPartidasBinario.GuardarEnHilo` guarda resultados sin bloquear el juego.
- Archivos binarios: `GuardadorPartidasBinario` usa serializacion Java con `ObjectOutputStream` y `ObjectInputStream`.
- Datos estadisticos base: `RegistroPartida` conserva categoria, nivel, estrellas, puntaje, tiempo, fallos y datos de reto.

## Puntos que pertenecen al modulo de menu/usuarios

- Registro de usuarios.
- Inicio de sesion.
- Carpeta propia por usuario.
- Contrasena segura y validacion visual.
- Avatar o imagen de perfil.
- Preferencias completas del usuario.
- Ranking general entre usuarios.
- Visualizacion completa de estadisticas.
- Desbloqueo persistente por usuario.

## Evidencia tecnica

- `./gradlew core:compileJava lwjgl3:compileJava --warning-mode all`
- `./gradlew core:test --rerun-tasks`
- `./gradlew build`

Las tres verificaciones pasaron despues de esta auditoria.
