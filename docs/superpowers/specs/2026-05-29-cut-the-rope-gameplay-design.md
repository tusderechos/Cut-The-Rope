# Cut The Rope Gameplay Module Design

## Objetivo

Construir solo el modulo jugable de Cut The Rope en libGDX con Box2D. El menu, login y sistema completo de cuentas seran integrados despues por otra parte del equipo. Esta version debe iniciar desde una seleccion de niveles libre para pruebas, permitir personalizar visualmente el dulce y el monstruo con colores simples, y ejecutar cinco niveles personalizados que puedan completarse.

El codigo debe quedar ordenado, con nombres claros, responsabilidades separadas y sin funciones duplicadas que hagan el mismo trabajo.

## Alcance

Incluido:

- Pantalla de seleccion de niveles con los niveles 1 a 5 disponibles.
- Seccion de personalizacion con colores para dulce y monstruo.
- Pantalla de juego con fisica Box2D.
- Cinco niveles progresivos y completables.
- Tres estrellas opcionales por nivel para puntaje.
- Cuerdas cortables con clic.
- Burbuja en nivel 3.
- Obstaculo peligroso simple en nivel 4.
- Plataforma movil del monstruo en nivel 5.
- Panel de victoria sobre el nivel, estilo Angry Birds.
- Reinicio automatico al fallar.

No incluido en esta fase:

- Login real.
- Persistencia binaria de usuarios.
- Ranking persistente.
- Skins PNG finales.
- Gesto de arrastre para cortar cuerdas.
- Guardado de progreso parcial dentro del nivel.

## Flujo De Pantallas

La clase principal `Juego` inicia en `PantallaSeleccionNivel`.

`PantallaSeleccionNivel` muestra:

- Una cuadricula simple con cinco niveles.
- Todos los niveles desbloqueados por ahora.
- Un bloque inferior con titulo `Personalizacion`.
- Una linea horizontal divisoria.
- Botones para cambiar color de `Dulce` y `Monstruo`.

Al seleccionar un nivel, se abre `PantallaJuego` con:

- Numero de nivel.
- Personalizacion actual del dulce.
- Personalizacion actual del monstruo.

Dentro de `PantallaJuego`, el boton `Salir` vuelve a seleccion de niveles. Salir no guarda estado parcial. Si el jugador vuelve a entrar al mismo nivel, inicia desde cero.

Al ganar, aparece un panel encima del nivel pausado. El panel muestra:

- Estrellas obtenidas de 3.
- Estrellas faltantes.
- Puntaje total.
- Tiempo usado.
- Intentos o fallos acumulados en ese nivel durante la sesion actual.
- Boton para salir.
- Boton para ir al siguiente nivel.

## Mundo Fisico

La ventana desktop se cambiara a formato vertical tipo celular, recomendado `480x800`.

El mundo Box2D usara escala aproximada:

- `100 pixeles = 1 metro`.
- Mundo visible aproximado: `4.8m x 8.0m`.

La fisica y el dibujo se separan:

- Box2D maneja cuerpos, gravedad, colisiones y joints.
- libGDX dibuja circulos, lineas, estrellas, burbujas, obstaculos y textos.

## Dulce, Monstruo Y Cuerdas

El dulce es un cuerpo dinamico con gravedad. Se dibuja como circulo de color configurable.

El monstruo se representa como zona de victoria y dibujo circular simple de color configurable. En nivel 5 se mueve con una plataforma horizontal.

Cada cuerda se modela con:

- Un ancla fija.
- Un joint fisico que conecta el ancla con el dulce.
- Una representacion visual como linea gruesa entre ancla y dulce.

Para cortar una cuerda, el jugador hace clic cerca de ella. El juego calcula la distancia entre el clic y el segmento de cuerda. Si la distancia esta dentro del margen permitido, la cuerda se corta.

Al cortar:

- El joint se destruye inmediatamente.
- El dulce conserva su velocidad.
- La cuerda activa desaparece de la fisica.
- Se crea una animacion visual `CuerdaCortadaVisual`.
- La cuerda cortada se dibuja en dos pedazos, cae o afloja visualmente y se desvanece durante un tiempo corto.

## Reglas De Fallo

El nivel falla automaticamente cuando:

- El dulce cae fuera del mapa.
- El dulce flota fuera de los limites mientras esta en burbuja.
- El dulce toca un obstaculo peligroso.
- El dulce queda casi detenido durante varios segundos, lejos del monstruo, sin cuerda activa ni burbuja que pueda cambiar su trayectoria.

Al fallar:

1. Se muestra un mensaje corto.
2. La pantalla hace fade a negro.
3. El nivel se reconstruye desde sus datos originales.
4. La pantalla vuelve con fade al juego reiniciado.

## Reglas De Victoria Y Puntaje

El jugador gana un nivel cuando el dulce toca la zona del monstruo.

Las tres estrellas no son obligatorias para ganar. Sirven para mejorar puntaje y ranking futuro.

El puntaje del intento considera:

- Estrellas recolectadas.
- Bono por tiempo.
- Penalizacion o reduccion por fallos/intentos.

En esta fase el mejor resultado puede mantenerse en memoria durante la ejecucion. La persistencia real se integrara despues.

## Diseno De Niveles

Cada nivel debe tener una idea principal:

### Nivel 1: Super Facil

Una cuerda, dulce centrado y monstruo debajo. Ensenia cortar y alimentar al monstruo.

### Nivel 2: Facil

Dos cuerdas con balanceo simple. Ensenia timing y orden de corte.

### Nivel 3: Intermedio

Introduce burbuja. El dulce puede flotar y el jugador decide cuando reventar la burbuja para hacerlo caer hacia el monstruo.

### Nivel 4: Mas Dificil

Introduce obstaculo peligroso estatico. El jugador debe cortar en el momento correcto para evitar el obstaculo y recolectar estrellas opcionales.

### Nivel 5: Dificil

Dos cuerdas y monstruo sobre plataforma movil horizontal. El reto principal es sincronizar la llegada del dulce con la posicion del monstruo. La burbuja queda como mejora futura si el nivel resulta demasiado facil.

## Estructura De Paquetes

Paquetes propuestos bajo `com.tusderechos.Juego`:

- `pantallas`
- `niveles`
- `entidades`
- `obstaculos`
- `interfaces`
- `enums`
- `utilidades`
- `personalizacion`

## Clases Principales

`pantallas`:

- `PantallaSeleccionNivel`
- `PantallaJuego`

`niveles`:

- `DatosNivel`
- `DatosCuerda`
- `DatosEstrella`
- `DatosBurbuja`
- `DatosObstaculo`
- `FabricaNiveles`
- `ProgresoJugadorDemo`

`entidades`:

- `ElementoJuego` abstracta
- `Dulce`
- `Monstruo`
- `Estrella`
- `Cuerda`
- `Burbuja`
- `PlataformaMovil`
- `CuerdaCortadaVisual`

`obstaculos`:

- `Obstaculo` abstracta, heredando de `ElementoJuego`
- `ObstaculoPeligroso`

`interfaces`:

- `Actualizable`
- `Dibujable`
- `Cortable`
- `Personalizable`

`enums`:

- `ColorDulce`
- `ColorMonstruo`
- `DificultadNivel`
- `EstadoNivel`

`utilidades`:

- `ConversorUnidades final`
- `ConstantesJuego final`
- `CalculadoraPuntaje`

`personalizacion`:

- `PersonalizacionVisual`
- `PersonalizacionDulce`
- `PersonalizacionMonstruo`

## Requisitos De POO Y Java

El diseno debe demostrar:

- Clase abstracta: `ElementoJuego`.
- Herencia abstracta: `Obstaculo extends ElementoJuego`, con `ObstaculoPeligroso extends Obstaculo`.
- Herencia simple separada de la rama abstracta: `PersonalizacionVisual` con `PersonalizacionDulce` y `PersonalizacionMonstruo`.
- Interfaces: `Actualizable`, `Dibujable`, `Cortable`, `Personalizable`.
- Enums para colores, dificultad y estado.
- Clases finales: `ConversorUnidades` y `ConstantesJuego`.
- Funciones finales donde tenga sentido en utilidades o clases base.
- Dos funciones recursivas originales:
  - Calcular puntaje acumulado recorriendo resultados de niveles.
  - Buscar recursivamente el siguiente nivel disponible desde una lista de niveles.

## Integracion Futura Con Menu Y Cuentas

La seleccion de niveles actual usa `ProgresoJugadorDemo`, que desbloquea todo. En el juego final, esa clase se reemplazara o adaptara para leer el progreso real del usuario.

`PantallaJuego` no debe depender de login ni de archivos. Debe recibir datos claros desde fuera para que el menu final pueda abrir un nivel especifico con la personalizacion elegida.

## Validacion

Antes de considerar completa la implementacion:

- El proyecto debe compilar con Gradle.
- `lwjgl3:run` debe iniciar en formato vertical.
- La seleccion de niveles debe abrir los cinco niveles.
- Cada nivel debe poder ganarse.
- Las tres estrellas deben poder recolectarse de forma opcional.
- Las cuerdas deben cortarse solo cuando el clic este cerca del segmento visual.
- El corte debe conservar el movimiento del dulce.
- La animacion de cuerda cortada debe verse brevemente y desaparecer.
- La burbuja debe flotar y reventarse con clic.
- El obstaculo del nivel 4 debe causar fallo.
- La plataforma del nivel 5 debe moverse.
- Salir de un nivel debe volver a seleccion sin guardar estado parcial.
- Al ganar debe aparecer el panel de resultado.
- Al fallar debe mostrarse mensaje, fade y reinicio automatico.
