# Rediseño de jugabilidad de Cut the Rope

## Objetivo

Corregir la física y rediseñar los cinco niveles para que el prototipo sea predecible, legible y justo. Cada nivel debe permitir alimentar al monstruo mediante una ruta directa y ofrecer una ruta alternativa razonable para recoger las tres estrellas antes de ganar.

## Cuerdas

- El clic detecta una cuerda dentro de un margen cómodo alrededor del segmento visible.
- El punto visual del corte es la proyección del clic sobre la cuerda, nunca la posición cruda del mouse.
- Si dos cuerdas están dentro del margen, se corta la más cercana.
- El `DistanceJoint` se destruye inmediatamente.
- Los dos fragmentos visuales caen y se desvanecen sin alterar la posición física del dulce.

## Burbuja

- La burbuja comienza libre en el mapa.
- No aplica fuerza antes del contacto con el dulce.
- Cuando el dulce la toca, queda asociada al dulce y comienza a elevarlo suavemente.
- El jugador puede reventarla con clic para liberar el dulce.
- El nivel 3 incluye una trayectoria viable hacia la burbuja, una ruta directa al monstruo y una ruta de tres estrellas.

## Física y reglas

- La simulación avanza en pasos fijos.
- En cada paso se comprueba recolección de estrellas, contacto con burbujas, peligros, límites y victoria para evitar saltos durante caídas de FPS.
- La plataforma del monstruo del nivel 5 se mueve horizontalmente de forma constante.
- Las interacciones se bloquean durante transiciones y solamente aceptan clic principal.

## Niveles

1. Tutorial: una cuerda y caída vertical. Enseña el corte.
2. Balanceo: dos cuerdas. Enseña a elegir el orden de corte.
3. Burbuja: contacto con una burbuja libre y decisión del momento de reventarla.
4. Peligro: trayectoria controlable alrededor de un obstáculo.
5. Plataforma: dos cuerdas y monstruo móvil; el reto consiste en medir el momento de soltar.

Cada nivel tendrá una ruta directa sencilla y una ruta de tres estrellas moderadamente exigente, sin depender de precisión milimétrica ni azar.

## Interfaz

- El panel de victoria permanece superpuesto al nivel.
- Muestra texto legible: estrellas, estrellas faltantes, puntaje, tiempo, fallos, salir y siguiente nivel.
- La transición oscura cubre todos los elementos visuales, incluidos los textos.

## Verificación

- Pruebas unitarias para proyección geométrica, activación de burbuja y configuración básica de niveles.
- Pruebas de compilación y suite completa.
- Cuatro pasadas de revisión: física y rutas, lógica y casos límite, calidad Java, y experiencia visual.
- Agentes independientes intentarán encontrar regresiones antes del cierre.
