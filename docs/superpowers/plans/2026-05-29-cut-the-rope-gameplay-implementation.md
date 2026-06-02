# Cut The Rope Gameplay Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build the playable Cut The Rope module in libGDX + Box2D with level selection, five custom levels, rope cutting, stars, simple customization, failure transitions, and a victory results panel.

**Architecture:** Keep the game module independent from login/menu systems by starting from `PantallaSeleccionNivel` and passing selected level/customization into `PantallaJuego`. Separate level data, physics entities, rendering contracts, enums, and utility classes so the final menu can plug in later without rewriting gameplay.

**Tech Stack:** Java 11, libGDX, Box2D, Gradle wrapper, LWJGL3 desktop launcher.

---

## File Structure

Existing files to modify:

- `lwjgl3/src/main/java/com/tusderechos/Juego/lwjgl3/Lwjgl3Launcher.java`: change desktop window to vertical `480x800`.
- `core/src/main/java/com/tusderechos/Juego/Juego.java`: start in `PantallaSeleccionNivel`.
- `core/src/main/java/com/tusderechos/Juego/FirstScreen.java`: leave unused or remove after replacement.
- `core/build.gradle`: add test dependency only if pure Java tests are added.

New packages under `core/src/main/java/com/tusderechos/Juego`:

- `pantallas`: `PantallaSeleccionNivel`, `PantallaJuego`.
- `niveles`: level data classes, level factory, demo progress.
- `entidades`: physical and visual game entities.
- `obstaculos`: abstract obstacle branch and dangerous obstacle.
- `interfaces`: common contracts.
- `enums`: gameplay enums.
- `utilidades`: constants, unit conversion, math, scoring.
- `personalizacion`: simple customization objects.

New tests under `core/src/test/java/com/tusderechos/Juego`:

- `utilidades/ConversorUnidadesTest.java`
- `utilidades/CalculadoraPuntajeTest.java`
- `niveles/ProgresoJugadorDemoTest.java`

Manual verification remains required for physics and visuals because Box2D gameplay feel cannot be validated fully with unit tests.

---

### Task 1: Window Format And Startup Screen

**Files:**

- Modify: `lwjgl3/src/main/java/com/tusderechos/Juego/lwjgl3/Lwjgl3Launcher.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/Juego.java`
- Create: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaSeleccionNivel.java`

- [ ] **Step 1: Create a minimal level-selection screen**

Create `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaSeleccionNivel.java`:

```java
package com.tusderechos.Juego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.tusderechos.Juego.Juego;

public class PantallaSeleccionNivel extends ScreenAdapter {
    private final Juego juego;

    public PantallaSeleccionNivel(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.08f, 0.10f, 0.14f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
```

- [ ] **Step 2: Start the game from the new screen**

Replace `core/src/main/java/com/tusderechos/Juego/Juego.java` with:

```java
package com.tusderechos.Juego;

import com.badlogic.gdx.Game;
import com.tusderechos.Juego.pantallas.PantallaSeleccionNivel;

public class Juego extends Game {
    @Override
    public void create() {
        setScreen(new PantallaSeleccionNivel(this));
    }
}
```

- [ ] **Step 3: Change the LWJGL3 window to mobile portrait**

In `lwjgl3/src/main/java/com/tusderechos/Juego/lwjgl3/Lwjgl3Launcher.java`, replace:

```java
configuration.setWindowedMode(640, 480);
```

with:

```java
configuration.setWindowedMode(480, 800);
```

- [ ] **Step 4: Compile**

Run:

```powershell
.\gradlew.bat build
```

Expected: build completes successfully.

- [ ] **Step 5: Run desktop app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected: a `480x800` window opens with a dark blank selection screen.

- [ ] **Step 6: Commit checkpoint**

```bash
git add lwjgl3/src/main/java/com/tusderechos/Juego/lwjgl3/Lwjgl3Launcher.java core/src/main/java/com/tusderechos/Juego/Juego.java core/src/main/java/com/tusderechos/Juego/pantallas/PantallaSeleccionNivel.java
git commit -m "feat: start gameplay module from level selection"
```

---

### Task 2: Core Contracts, Enums, Utilities, And Personalization

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/interfaces/Actualizable.java`
- Create: `core/src/main/java/com/tusderechos/Juego/interfaces/Dibujable.java`
- Create: `core/src/main/java/com/tusderechos/Juego/interfaces/Cortable.java`
- Create: `core/src/main/java/com/tusderechos/Juego/interfaces/Personalizable.java`
- Create: `core/src/main/java/com/tusderechos/Juego/enums/ColorDulce.java`
- Create: `core/src/main/java/com/tusderechos/Juego/enums/ColorMonstruo.java`
- Create: `core/src/main/java/com/tusderechos/Juego/enums/DificultadNivel.java`
- Create: `core/src/main/java/com/tusderechos/Juego/enums/EstadoNivel.java`
- Create: `core/src/main/java/com/tusderechos/Juego/utilidades/ConstantesJuego.java`
- Create: `core/src/main/java/com/tusderechos/Juego/utilidades/ConversorUnidades.java`
- Create: `core/src/main/java/com/tusderechos/Juego/personalizacion/PersonalizacionVisual.java`
- Create: `core/src/main/java/com/tusderechos/Juego/personalizacion/PersonalizacionDulce.java`
- Create: `core/src/main/java/com/tusderechos/Juego/personalizacion/PersonalizacionMonstruo.java`
- Create: `core/src/test/java/com/tusderechos/Juego/utilidades/ConversorUnidadesTest.java`

- [ ] **Step 1: Add shared interfaces**

Create these files:

```java
package com.tusderechos.Juego.interfaces;

public interface Actualizable {
    void actualizar(float delta);
}
```

```java
package com.tusderechos.Juego.interfaces;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Dibujable {
    void dibujar(ShapeRenderer shapeRenderer);
}
```

```java
package com.tusderechos.Juego.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Cortable {
    boolean contienePuntoDeCorte(Vector2 puntoMundo);
    void cortar(Vector2 puntoMundo);
    boolean estaCortada();
}
```

```java
package com.tusderechos.Juego.interfaces;

import com.badlogic.gdx.graphics.Color;

public interface Personalizable {
    Color obtenerColor();
}
```

- [ ] **Step 2: Add enums**

Create:

```java
package com.tusderechos.Juego.enums;

import com.badlogic.gdx.graphics.Color;

public enum ColorDulce {
    ROJO(new Color(0.95f, 0.18f, 0.20f, 1f)),
    AZUL(new Color(0.20f, 0.45f, 0.95f, 1f)),
    VERDE(new Color(0.20f, 0.75f, 0.35f, 1f)),
    AMARILLO(new Color(0.98f, 0.82f, 0.22f, 1f));

    private final Color color;

    ColorDulce(Color color) {
        this.color = color;
    }

    public Color obtenerColor() {
        return color;
    }

    public ColorDulce siguiente() {
        ColorDulce[] valores = values();
        return valores[(ordinal() + 1) % valores.length];
    }
}
```

```java
package com.tusderechos.Juego.enums;

import com.badlogic.gdx.graphics.Color;

public enum ColorMonstruo {
    VERDE(new Color(0.25f, 0.80f, 0.32f, 1f)),
    MORADO(new Color(0.56f, 0.28f, 0.82f, 1f)),
    NARANJA(new Color(0.95f, 0.48f, 0.18f, 1f)),
    CELESTE(new Color(0.25f, 0.75f, 0.95f, 1f));

    private final Color color;

    ColorMonstruo(Color color) {
        this.color = color;
    }

    public Color obtenerColor() {
        return color;
    }

    public ColorMonstruo siguiente() {
        ColorMonstruo[] valores = values();
        return valores[(ordinal() + 1) % valores.length];
    }
}
```

```java
package com.tusderechos.Juego.enums;

public enum DificultadNivel {
    SUPER_FACIL,
    FACIL,
    INTERMEDIO,
    DIFICIL,
    MUY_DIFICIL
}
```

```java
package com.tusderechos.Juego.enums;

public enum EstadoNivel {
    JUGANDO,
    FALLANDO,
    REINICIANDO,
    GANADO
}
```

- [ ] **Step 3: Add final constants and converter classes**

Create:

```java
package com.tusderechos.Juego.utilidades;

public final class ConstantesJuego {
    public static final int ANCHO_VENTANA = 480;
    public static final int ALTO_VENTANA = 800;
    public static final float PIXELES_POR_METRO = 100f;
    public static final float ANCHO_MUNDO = ANCHO_VENTANA / PIXELES_POR_METRO;
    public static final float ALTO_MUNDO = ALTO_VENTANA / PIXELES_POR_METRO;
    public static final float GRAVEDAD = -9.8f;
    public static final float RADIO_DULCE = 0.18f;
    public static final float RADIO_MONSTRUO = 0.38f;
    public static final float RADIO_ESTRELLA = 0.13f;
    public static final float MARGEN_CORTE_CUERDA = 0.16f;

    private ConstantesJuego() {
    }
}
```

```java
package com.tusderechos.Juego.utilidades;

public final class ConversorUnidades {
    private ConversorUnidades() {
    }

    public static final float pixelesAMetros(float pixeles) {
        return pixeles / ConstantesJuego.PIXELES_POR_METRO;
    }

    public static final float metrosAPixeles(float metros) {
        return metros * ConstantesJuego.PIXELES_POR_METRO;
    }
}
```

- [ ] **Step 4: Add simple personalization hierarchy**

Create:

```java
package com.tusderechos.Juego.personalizacion;

import com.badlogic.gdx.graphics.Color;
import com.tusderechos.Juego.interfaces.Personalizable;

public class PersonalizacionVisual implements Personalizable {
    private final Color color;

    public PersonalizacionVisual(Color color) {
        this.color = color;
    }

    @Override
    public Color obtenerColor() {
        return color;
    }
}
```

```java
package com.tusderechos.Juego.personalizacion;

import com.tusderechos.Juego.enums.ColorDulce;

public class PersonalizacionDulce extends PersonalizacionVisual {
    private final ColorDulce colorDulce;

    public PersonalizacionDulce(ColorDulce colorDulce) {
        super(colorDulce.obtenerColor());
        this.colorDulce = colorDulce;
    }

    public ColorDulce obtenerColorDulce() {
        return colorDulce;
    }
}
```

```java
package com.tusderechos.Juego.personalizacion;

import com.tusderechos.Juego.enums.ColorMonstruo;

public class PersonalizacionMonstruo extends PersonalizacionVisual {
    private final ColorMonstruo colorMonstruo;

    public PersonalizacionMonstruo(ColorMonstruo colorMonstruo) {
        super(colorMonstruo.obtenerColor());
        this.colorMonstruo = colorMonstruo;
    }

    public ColorMonstruo obtenerColorMonstruo() {
        return colorMonstruo;
    }
}
```

- [ ] **Step 5: Add converter unit test**

Create `core/src/test/java/com/tusderechos/Juego/utilidades/ConversorUnidadesTest.java`:

```java
package com.tusderechos.Juego.utilidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversorUnidadesTest {
    @Test
    void conviertePixelesYMetrosConEscalaDelJuego() {
        assertEquals(1f, ConversorUnidades.pixelesAMetros(100f), 0.001f);
        assertEquals(250f, ConversorUnidades.metrosAPixeles(2.5f), 0.001f);
    }
}
```

- [ ] **Step 6: Add JUnit dependency if missing**

In `core/build.gradle`, inside `dependencies`, add:

```gradle
  testImplementation "org.junit.jupiter:junit-jupiter-api:5.10.2"
  testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine:5.10.2"
```

At the end of `core/build.gradle`, add:

```gradle
test {
  useJUnitPlatform()
}
```

- [ ] **Step 7: Run pure utility test**

Run:

```powershell
.\gradlew.bat core:test --tests com.tusderechos.Juego.utilidades.ConversorUnidadesTest
```

Expected: test passes.

- [ ] **Step 8: Commit checkpoint**

```bash
git add core/build.gradle core/src/main/java/com/tusderechos/Juego/interfaces core/src/main/java/com/tusderechos/Juego/enums core/src/main/java/com/tusderechos/Juego/utilidades core/src/main/java/com/tusderechos/Juego/personalizacion core/src/test/java/com/tusderechos/Juego/utilidades
git commit -m "feat: add gameplay contracts and shared utilities"
```

---

### Task 3: Level Data, Factory, Demo Progress, And Recursion

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/niveles/DatosCuerda.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/DatosEstrella.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/DatosBurbuja.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/DatosObstaculo.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/DatosNivel.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/ResultadoNivel.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/ProgresoJugadorDemo.java`
- Create: `core/src/main/java/com/tusderechos/Juego/niveles/FabricaNiveles.java`
- Create: `core/src/main/java/com/tusderechos/Juego/utilidades/CalculadoraPuntaje.java`
- Create: `core/src/test/java/com/tusderechos/Juego/utilidades/CalculadoraPuntajeTest.java`
- Create: `core/src/test/java/com/tusderechos/Juego/niveles/ProgresoJugadorDemoTest.java`

- [ ] **Step 1: Create immutable level data classes**

Create simple data classes with final fields and getters. Use `Vector2` for world coordinates:

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosCuerda {
    private final Vector2 ancla;
    private final float longitud;

    public DatosCuerda(Vector2 ancla, float longitud) {
        this.ancla = new Vector2(ancla);
        this.longitud = longitud;
    }

    public Vector2 obtenerAncla() {
        return new Vector2(ancla);
    }

    public float obtenerLongitud() {
        return longitud;
    }
}
```

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosEstrella {
    private final Vector2 posicion;

    public DatosEstrella(Vector2 posicion) {
        this.posicion = new Vector2(posicion);
    }

    public Vector2 obtenerPosicion() {
        return new Vector2(posicion);
    }
}
```

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosBurbuja {
    private final Vector2 posicion;
    private final float radio;

    public DatosBurbuja(Vector2 posicion, float radio) {
        this.posicion = new Vector2(posicion);
        this.radio = radio;
    }

    public Vector2 obtenerPosicion() {
        return new Vector2(posicion);
    }

    public float obtenerRadio() {
        return radio;
    }
}
```

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosObstaculo {
    private final Vector2 posicion;
    private final float ancho;
    private final float alto;

    public DatosObstaculo(Vector2 posicion, float ancho, float alto) {
        this.posicion = new Vector2(posicion);
        this.ancho = ancho;
        this.alto = alto;
    }

    public Vector2 obtenerPosicion() {
        return new Vector2(posicion);
    }

    public float obtenerAncho() {
        return ancho;
    }

    public float obtenerAlto() {
        return alto;
    }
}
```

- [ ] **Step 2: Create `DatosNivel`**

Create `core/src/main/java/com/tusderechos/Juego/niveles/DatosNivel.java`:

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatosNivel {
    private final int numero;
    private final String nombre;
    private final DificultadNivel dificultad;
    private final Vector2 posicionDulce;
    private final Vector2 posicionMonstruo;
    private final List<DatosCuerda> cuerdas;
    private final List<DatosEstrella> estrellas;
    private final List<DatosBurbuja> burbujas;
    private final List<DatosObstaculo> obstaculos;
    private final boolean plataformaMovil;

    public DatosNivel(int numero, String nombre, DificultadNivel dificultad, Vector2 posicionDulce,
                      Vector2 posicionMonstruo, List<DatosCuerda> cuerdas, List<DatosEstrella> estrellas,
                      List<DatosBurbuja> burbujas, List<DatosObstaculo> obstaculos, boolean plataformaMovil) {
        this.numero = numero;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.posicionDulce = new Vector2(posicionDulce);
        this.posicionMonstruo = new Vector2(posicionMonstruo);
        this.cuerdas = new ArrayList<>(cuerdas);
        this.estrellas = new ArrayList<>(estrellas);
        this.burbujas = new ArrayList<>(burbujas);
        this.obstaculos = new ArrayList<>(obstaculos);
        this.plataformaMovil = plataformaMovil;
    }

    public int obtenerNumero() { return numero; }
    public String obtenerNombre() { return nombre; }
    public DificultadNivel obtenerDificultad() { return dificultad; }
    public Vector2 obtenerPosicionDulce() { return new Vector2(posicionDulce); }
    public Vector2 obtenerPosicionMonstruo() { return new Vector2(posicionMonstruo); }
    public List<DatosCuerda> obtenerCuerdas() { return Collections.unmodifiableList(cuerdas); }
    public List<DatosEstrella> obtenerEstrellas() { return Collections.unmodifiableList(estrellas); }
    public List<DatosBurbuja> obtenerBurbujas() { return Collections.unmodifiableList(burbujas); }
    public List<DatosObstaculo> obtenerObstaculos() { return Collections.unmodifiableList(obstaculos); }
    public boolean tienePlataformaMovil() { return plataformaMovil; }
}
```

- [ ] **Step 3: Create result and recursive score calculator**

Create:

```java
package com.tusderechos.Juego.niveles;

public class ResultadoNivel {
    private final int numeroNivel;
    private final int estrellas;
    private final int puntaje;
    private final float tiempo;

    public ResultadoNivel(int numeroNivel, int estrellas, int puntaje, float tiempo) {
        this.numeroNivel = numeroNivel;
        this.estrellas = estrellas;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
    }

    public int obtenerNumeroNivel() { return numeroNivel; }
    public int obtenerEstrellas() { return estrellas; }
    public int obtenerPuntaje() { return puntaje; }
    public float obtenerTiempo() { return tiempo; }
}
```

```java
package com.tusderechos.Juego.utilidades;

import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.util.List;

public final class CalculadoraPuntaje {
    private CalculadoraPuntaje() {
    }

    public static int calcularPuntajeIntento(int estrellas, float tiempo, int fallos) {
        int bonoEstrellas = estrellas * 1000;
        int bonoTiempo = Math.max(0, 2000 - Math.round(tiempo * 50f));
        int penalizacionFallos = fallos * 150;
        return Math.max(0, bonoEstrellas + bonoTiempo - penalizacionFallos);
    }

    public static int calcularPuntajeAcumuladoRecursivo(List<ResultadoNivel> resultados) {
        return sumarDesdeIndice(resultados, 0);
    }

    private static int sumarDesdeIndice(List<ResultadoNivel> resultados, int indice) {
        if (indice >= resultados.size()) {
            return 0;
        }
        return resultados.get(indice).obtenerPuntaje() + sumarDesdeIndice(resultados, indice + 1);
    }
}
```

- [ ] **Step 4: Create demo progress with recursive next-level lookup**

Create `ProgresoJugadorDemo.java`:

```java
package com.tusderechos.Juego.niveles;

import java.util.ArrayList;
import java.util.List;

public class ProgresoJugadorDemo {
    private final List<ResultadoNivel> mejoresResultados = new ArrayList<>();

    public boolean nivelEstaDesbloqueado(int numeroNivel) {
        return numeroNivel >= 1 && numeroNivel <= 5;
    }

    public void registrarResultado(ResultadoNivel resultadoNivel) {
        mejoresResultados.removeIf(resultado -> resultado.obtenerNumeroNivel() == resultadoNivel.obtenerNumeroNivel());
        mejoresResultados.add(resultadoNivel);
    }

    public List<ResultadoNivel> obtenerMejoresResultados() {
        return new ArrayList<>(mejoresResultados);
    }

    public int buscarSiguienteNivelDisponibleRecursivo(List<DatosNivel> niveles, int nivelActual) {
        return buscarDesdeIndice(niveles, 0, nivelActual);
    }

    private int buscarDesdeIndice(List<DatosNivel> niveles, int indice, int nivelActual) {
        if (indice >= niveles.size()) {
            return nivelActual;
        }
        int candidato = niveles.get(indice).obtenerNumero();
        if (candidato > nivelActual && nivelEstaDesbloqueado(candidato)) {
            return candidato;
        }
        return buscarDesdeIndice(niveles, indice + 1, nivelActual);
    }
}
```

- [ ] **Step 5: Create the five-level factory**

Create `FabricaNiveles.java` with concrete coordinates:

```java
package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FabricaNiveles {
    private FabricaNiveles() {
    }

    public static List<DatosNivel> crearNiveles() {
        return Arrays.asList(crearNivelUno(), crearNivelDos(), crearNivelTres(), crearNivelCuatro(), crearNivelCinco());
    }

    public static DatosNivel obtenerNivel(int numeroNivel) {
        return crearNiveles().get(numeroNivel - 1);
    }

    private static DatosNivel crearNivelUno() {
        return new DatosNivel(1, "Basico", DificultadNivel.SUPER_FACIL,
            new Vector2(2.4f, 5.8f), new Vector2(2.4f, 1.1f),
            Collections.singletonList(new DatosCuerda(new Vector2(2.4f, 7.2f), 1.4f)),
            Arrays.asList(new DatosEstrella(new Vector2(2.4f, 4.6f)), new DatosEstrella(new Vector2(2.1f, 3.2f)), new DatosEstrella(new Vector2(2.7f, 2.2f))),
            Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelDos() {
        return new DatosNivel(2, "Balanceo", DificultadNivel.FACIL,
            new Vector2(2.4f, 5.5f), new Vector2(2.4f, 1.1f),
            Arrays.asList(new DatosCuerda(new Vector2(1.4f, 7.1f), 1.9f), new DatosCuerda(new Vector2(3.4f, 7.1f), 1.9f)),
            Arrays.asList(new DatosEstrella(new Vector2(1.8f, 4.8f)), new DatosEstrella(new Vector2(3.0f, 4.1f)), new DatosEstrella(new Vector2(2.4f, 2.4f))),
            Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelTres() {
        return new DatosNivel(3, "Burbuja", DificultadNivel.INTERMEDIO,
            new Vector2(1.4f, 3.2f), new Vector2(3.5f, 1.2f),
            Collections.singletonList(new DatosCuerda(new Vector2(1.2f, 5.2f), 1.9f)),
            Arrays.asList(new DatosEstrella(new Vector2(1.6f, 4.5f)), new DatosEstrella(new Vector2(2.8f, 5.5f)), new DatosEstrella(new Vector2(3.4f, 2.4f))),
            Collections.singletonList(new DatosBurbuja(new Vector2(1.4f, 3.2f), 0.34f)),
            Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelCuatro() {
        return new DatosNivel(4, "Peligro", DificultadNivel.DIFICIL,
            new Vector2(1.2f, 5.8f), new Vector2(3.6f, 1.1f),
            Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.1f), 1.4f), new DatosCuerda(new Vector2(2.3f, 6.9f), 1.8f)),
            Arrays.asList(new DatosEstrella(new Vector2(1.4f, 4.5f)), new DatosEstrella(new Vector2(2.6f, 3.6f)), new DatosEstrella(new Vector2(3.5f, 2.2f))),
            Collections.emptyList(),
            Collections.singletonList(new DatosObstaculo(new Vector2(2.45f, 2.5f), 0.55f, 0.35f)), false);
    }

    private static DatosNivel crearNivelCinco() {
        return new DatosNivel(5, "Plataforma", DificultadNivel.MUY_DIFICIL,
            new Vector2(2.4f, 5.9f), new Vector2(2.4f, 1.2f),
            Arrays.asList(new DatosCuerda(new Vector2(1.3f, 7.1f), 1.7f), new DatosCuerda(new Vector2(3.5f, 7.1f), 1.7f)),
            Arrays.asList(new DatosEstrella(new Vector2(1.3f, 4.5f)), new DatosEstrella(new Vector2(3.5f, 4.5f)), new DatosEstrella(new Vector2(2.4f, 2.6f))),
            Collections.emptyList(), Collections.emptyList(), true);
    }
}
```

- [ ] **Step 6: Add recursion tests**

Create tests:

```java
package com.tusderechos.Juego.utilidades;

import com.tusderechos.Juego.niveles.ResultadoNivel;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraPuntajeTest {
    @Test
    void sumaPuntajeAcumuladoConRecursion() {
        int total = CalculadoraPuntaje.calcularPuntajeAcumuladoRecursivo(Arrays.asList(
            new ResultadoNivel(1, 3, 3000, 12f),
            new ResultadoNivel(2, 2, 2100, 18f),
            new ResultadoNivel(3, 1, 1200, 20f)
        ));

        assertEquals(6300, total);
    }
}
```

```java
package com.tusderechos.Juego.niveles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgresoJugadorDemoTest {
    @Test
    void encuentraSiguienteNivelDisponibleConRecursion() {
        ProgresoJugadorDemo progreso = new ProgresoJugadorDemo();

        int siguiente = progreso.buscarSiguienteNivelDisponibleRecursivo(FabricaNiveles.crearNiveles(), 3);

        assertEquals(4, siguiente);
    }
}
```

- [ ] **Step 7: Run tests**

Run:

```powershell
.\gradlew.bat core:test
```

Expected: all core tests pass.

- [ ] **Step 8: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/niveles core/src/main/java/com/tusderechos/Juego/utilidades/CalculadoraPuntaje.java core/src/test/java/com/tusderechos/Juego
git commit -m "feat: define gameplay levels and demo progress"
```

---

### Task 4: Level Selection UI And Color Customization

**Files:**

- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaSeleccionNivel.java`
- Create: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] **Step 1: Create a placeholder game screen constructor**

Create `PantallaJuego.java`:

```java
package com.tusderechos.Juego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;

public class PantallaJuego extends ScreenAdapter {
    private final Juego juego;
    private final DatosNivel datosNivel;
    private final PersonalizacionDulce personalizacionDulce;
    private final PersonalizacionMonstruo personalizacionMonstruo;

    public PantallaJuego(Juego juego, DatosNivel datosNivel, PersonalizacionDulce personalizacionDulce,
                         PersonalizacionMonstruo personalizacionMonstruo) {
        this.juego = juego;
        this.datosNivel = datosNivel;
        this.personalizacionDulce = personalizacionDulce;
        this.personalizacionMonstruo = personalizacionMonstruo;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.16f, 0.20f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
```

- [ ] **Step 2: Implement selector UI using Stage**

Replace `PantallaSeleccionNivel` with a `Stage`, `Skin`-free `TextButton` style, five level buttons, customization labels, and callbacks. Use default libGDX fonts from `BitmapFont` so no asset is required.

Key fields:

```java
private Stage stage;
private Skin skin;
private ColorDulce colorDulceActual = ColorDulce.ROJO;
private ColorMonstruo colorMonstruoActual = ColorMonstruo.VERDE;
```

Button behavior:

```java
botonDulce.addListener(new ChangeListener() {
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        colorDulceActual = colorDulceActual.siguiente();
        botonDulce.setText("Dulce: " + colorDulceActual.name());
    }
});
```

Level button behavior:

```java
juego.setScreen(new PantallaJuego(
    juego,
    FabricaNiveles.obtenerNivel(numeroNivel),
    new PersonalizacionDulce(colorDulceActual),
    new PersonalizacionMonstruo(colorMonstruoActual)
));
```

- [ ] **Step 3: Dispose UI resources**

In `PantallaSeleccionNivel.dispose()`:

```java
if (stage != null) {
    stage.dispose();
}
if (skin != null) {
    skin.dispose();
}
```

- [ ] **Step 4: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected:

- Selection screen shows five level buttons.
- Personalization section appears below.
- Dulce and Monstruo buttons cycle colors.
- Clicking a level opens the placeholder game screen.

- [ ] **Step 5: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/pantallas
git commit -m "feat: add level selection and customization controls"
```

---

### Task 5: Abstract Entity Model And Box2D Game Skeleton

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/entidades/ElementoJuego.java`
- Create: `core/src/main/java/com/tusderechos/Juego/entidades/Dulce.java`
- Create: `core/src/main/java/com/tusderechos/Juego/entidades/Monstruo.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] **Step 1: Create abstract entity base**

Create:

```java
package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public abstract class ElementoJuego implements Actualizable, Dibujable {
    protected Body cuerpo;

    public Body obtenerCuerpo() {
        return cuerpo;
    }

    public boolean tieneCuerpo() {
        return cuerpo != null;
    }

    @Override
    public void actualizar(float delta) {
    }

    @Override
    public abstract void dibujar(ShapeRenderer shapeRenderer);
}
```

- [ ] **Step 2: Create `Dulce` dynamic body**

Create `Dulce.java` with a circle body, restitution, density, and draw method using `ShapeRenderer.circle`.

Required constructor:

```java
public Dulce(World mundo, Vector2 posicionInicial, PersonalizacionDulce personalizacionDulce)
```

Use `ConstantesJuego.RADIO_DULCE` for fixture radius.

- [ ] **Step 3: Create `Monstruo` goal object**

Create `Monstruo.java` as a drawable entity with position, radius, color, and optional platform tracking later.

Required methods:

```java
public boolean contieneDulce(Vector2 posicionDulce)
public Vector2 obtenerPosicion()
public void establecerPosicion(Vector2 nuevaPosicion)
```

- [ ] **Step 4: Build `PantallaJuego` Box2D skeleton**

In `PantallaJuego`:

- Create `World mundo = new World(new Vector2(0f, ConstantesJuego.GRAVEDAD), true);`
- Create `OrthographicCamera`.
- Create `ShapeRenderer`.
- Instantiate `Dulce` and `Monstruo`.
- In `render`, call `mundo.step(1f / 60f, 6, 2)`.
- Draw dulce and monstruo with projection matrix.
- Dispose world and renderer.

- [ ] **Step 5: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected: selecting a level shows the candy falling with gravity and the monster drawn near the bottom.

- [ ] **Step 6: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/entidades core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java
git commit -m "feat: add Box2D gameplay skeleton"
```

---

### Task 6: Ropes, Cutting Bounds, And Cut Rope Animation

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/entidades/Cuerda.java`
- Create: `core/src/main/java/com/tusderechos/Juego/entidades/CuerdaCortadaVisual.java`
- Create: `core/src/main/java/com/tusderechos/Juego/utilidades/GeometriaJuego.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] **Step 1: Add distance-to-segment utility**

Create:

```java
package com.tusderechos.Juego.utilidades;

import com.badlogic.gdx.math.Vector2;

public final class GeometriaJuego {
    private GeometriaJuego() {
    }

    public static float distanciaPuntoASegmento(Vector2 punto, Vector2 inicio, Vector2 fin) {
        Vector2 segmento = new Vector2(fin).sub(inicio);
        float longitudCuadrada = segmento.len2();
        if (longitudCuadrada == 0f) {
            return punto.dst(inicio);
        }
        float proporcion = new Vector2(punto).sub(inicio).dot(segmento) / longitudCuadrada;
        proporcion = Math.max(0f, Math.min(1f, proporcion));
        Vector2 proyeccion = new Vector2(inicio).mulAdd(segmento, proporcion);
        return punto.dst(proyeccion);
    }
}
```

- [ ] **Step 2: Create `Cuerda` with DistanceJoint**

Create `Cuerda.java` implementing `Cortable`, with:

- Static anchor body.
- `DistanceJoint`.
- `contienePuntoDeCorte` using `GeometriaJuego.distanciaPuntoASegmento`.
- `cortar` destroying the joint through the world.
- Draw active rope as thick line.

- [ ] **Step 3: Create `CuerdaCortadaVisual`**

Create a visual-only class with:

- `Vector2 inicio`
- `Vector2 corte`
- `Vector2 fin`
- `float edad`
- `float duracion = 0.75f`
- `actualizar(delta)` to move endpoints slightly downward and fade.
- `estaFinalizada()`.
- `dibujar(ShapeRenderer)` with alpha based on age.

- [ ] **Step 4: Add rope creation to `PantallaJuego`**

For each `DatosCuerda`, create `Cuerda` connected to the candy body.

Store:

```java
private final Array<Cuerda> cuerdas = new Array<>();
private final Array<CuerdaCortadaVisual> cuerdasCortadas = new Array<>();
```

- [ ] **Step 5: Handle click cutting**

In `PantallaJuego`, add an `InputAdapter`:

```java
public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    Vector3 puntoPantalla = new Vector3(screenX, screenY, 0f);
    camara.unproject(puntoPantalla);
    cortarCuerdaCercana(new Vector2(puntoPantalla.x, puntoPantalla.y));
    return true;
}
```

When cutting, add a `CuerdaCortadaVisual` before marking the active rope removed.

- [ ] **Step 6: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected:

- Level 1 candy hangs from rope.
- Clicking close to rope cuts it.
- Clicking far from rope does not cut it.
- The cut rope fades briefly.
- Candy keeps moving after cut.

- [ ] **Step 7: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/entidades/Cuerda.java core/src/main/java/com/tusderechos/Juego/entidades/CuerdaCortadaVisual.java core/src/main/java/com/tusderechos/Juego/utilidades/GeometriaJuego.java core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java
git commit -m "feat: add rope cutting physics"
```

---

### Task 7: Stars, Victory Detection, Scoring, And Results Panel

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/entidades/Estrella.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] **Step 1: Create `Estrella`**

Create drawable star/circle with:

- position
- collected flag
- `intentarRecolectar(Vector2 posicionDulce)` returning true if candy is close enough.

- [ ] **Step 2: Track level timer and failures**

In `PantallaJuego`, add:

```java
private float tiempoNivel;
private int fallosNivel;
private int estrellasRecolectadas;
private boolean mostrandoResultado;
private int puntajeFinal;
```

Increment `tiempoNivel` only while playing.

- [ ] **Step 3: Detect star collection**

Each frame, compare candy position to every uncollected star. If collected, increment `estrellasRecolectadas`.

- [ ] **Step 4: Detect victory**

If `monstruo.contieneDulce(dulce.obtenerCuerpo().getPosition())`, set result state:

```java
puntajeFinal = CalculadoraPuntaje.calcularPuntajeIntento(estrellasRecolectadas, tiempoNivel, fallosNivel);
mostrandoResultado = true;
```

Stop stepping physics while result panel is visible.

- [ ] **Step 5: Draw result panel**

Use `ShapeRenderer` for panel background and `BitmapFont` for text:

- `Nivel completado`
- `Estrellas: X/3`
- `Faltaron: 3 - X`
- `Puntaje: puntajeFinal`
- `Tiempo: tiempoNivel`
- `Fallos: fallosNivel`
- `Salir`
- `Siguiente`

- [ ] **Step 6: Add result panel button bounds**

Use simple rectangles in world/screen coordinates. Clicking `Salir` returns to `PantallaSeleccionNivel`. Clicking `Siguiente` opens next level from `FabricaNiveles` if current level is less than 5.

- [ ] **Step 7: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected:

- Stars disappear or change color when collected.
- Candy reaching monster opens result panel.
- Result panel buttons work.

- [ ] **Step 8: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/entidades/Estrella.java core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java
git commit -m "feat: add stars and level results"
```

---

### Task 8: Bubble, Failure Rules, And Fade Restart

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/entidades/Burbuja.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] **Step 1: Create `Burbuja`**

Create a visual/interactive entity with:

- position
- radius
- active flag
- `contienePunto(Vector2 puntoMundo)`
- `reventar()`
- transparent blue drawing

- [ ] **Step 2: Apply floating behavior**

In `PantallaJuego`, if the bubble is active and the candy is inside/attached to bubble behavior, apply upward force:

```java
dulce.obtenerCuerpo().applyForceToCenter(0f, 8.5f, true);
```

When player clicks active bubble, call `reventar()`.

- [ ] **Step 3: Add failure state fields**

Add:

```java
private EstadoNivel estadoNivel = EstadoNivel.JUGANDO;
private float tiempoEstadoFallo;
private String mensajeFallo;
private float tiempoDulceDetenido;
```

- [ ] **Step 4: Detect automatic failure**

Rules:

- candy y < `-0.6f`
- candy x < `-0.6f`
- candy x > `ConstantesJuego.ANCHO_MUNDO + 0.6f`
- candy y > `ConstantesJuego.ALTO_MUNDO + 0.6f` while bubble active
- candy speed < `0.05f` for more than `3f` seconds, no active ropes, no active bubble, and far from monster

- [ ] **Step 5: Implement fail transition**

When failure starts:

```java
estadoNivel = EstadoNivel.FALLANDO;
mensajeFallo = "Intento fallido";
tiempoEstadoFallo = 0f;
fallosNivel++;
```

After message/fade duration, rebuild the same level from `datosNivel`.

- [ ] **Step 6: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected:

- Level 3 bubble floats candy upward.
- Clicking bubble pops it.
- Falling out of bounds shows failure message and restarts.
- Restart recreates original ropes/stars/bubble.

- [ ] **Step 7: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/entidades/Burbuja.java core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java
git commit -m "feat: add bubble and automatic restart"
```

---

### Task 9: Abstract Obstacles And Moving Platform

**Files:**

- Create: `core/src/main/java/com/tusderechos/Juego/obstaculos/Obstaculo.java`
- Create: `core/src/main/java/com/tusderechos/Juego/obstaculos/ObstaculoPeligroso.java`
- Create: `core/src/main/java/com/tusderechos/Juego/entidades/PlataformaMovil.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/entidades/Monstruo.java`

- [ ] **Step 1: Create abstract obstacle branch**

Create:

```java
package com.tusderechos.Juego.obstaculos;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.entidades.ElementoJuego;

public abstract class Obstaculo extends ElementoJuego {
    public abstract boolean tocaDulce(Vector2 posicionDulce, float radioDulce);
}
```

- [ ] **Step 2: Create dangerous obstacle**

Create rectangular `ObstaculoPeligroso` with `tocaDulce` checking circle-rectangle overlap. Draw it as red rectangle with warning color.

- [ ] **Step 3: Add obstacles to level 4**

In `PantallaJuego`, build obstacles from `datosNivel.obtenerObstaculos()`. If any obstacle touches the candy, trigger failure with message `El dulce toco un peligro`.

- [ ] **Step 4: Create moving platform**

Create `PlataformaMovil` with:

- center position
- min x
- max x
- speed
- direction
- `actualizar(delta)`
- `obtenerPosicionMonstruo()`
- `dibujar(ShapeRenderer)`

- [ ] **Step 5: Attach monster position to platform in level 5**

If `datosNivel.tienePlataformaMovil()`, instantiate `PlataformaMovil`, update it every frame, and call:

```java
monstruo.establecerPosicion(plataformaMovil.obtenerPosicionMonstruo());
```

- [ ] **Step 6: Run app**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Expected:

- Level 4 red obstacle causes failure on contact.
- Level 5 platform moves horizontally.
- Monster moves with platform.
- Level 5 remains completable with timing.

- [ ] **Step 7: Commit checkpoint**

```bash
git add core/src/main/java/com/tusderechos/Juego/obstaculos core/src/main/java/com/tusderechos/Juego/entidades/PlataformaMovil.java core/src/main/java/com/tusderechos/Juego/entidades/Monstruo.java core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java
git commit -m "feat: add obstacles and moving platform"
```

---

### Task 10: Level Tuning, Cleanup, And Final Verification

**Files:**

- Modify: `core/src/main/java/com/tusderechos/Juego/niveles/FabricaNiveles.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaSeleccionNivel.java`
- Modify: `README.md`

- [ ] **Step 1: Tune each level manually**

For each level, play until a normal successful solution exists:

- Level 1: one cut should solve it.
- Level 2: one or two cuts with timing should solve it.
- Level 3: bubble pop timing should solve it.
- Level 4: obstacle can be avoided.
- Level 5: platform timing can be solved without bubble.

Adjust coordinates in `FabricaNiveles` only.

- [ ] **Step 2: Check class responsibility cleanup**

Confirm:

- `PantallaJuego` handles orchestration, not raw level definitions.
- `FabricaNiveles` owns level coordinates.
- `Dulce`, `Monstruo`, `Cuerda`, `Burbuja`, `Estrella`, `PlataformaMovil` each handle their own behavior.
- `ObstaculoPeligroso` handles dangerous collision.
- Utility classes remain final.

- [ ] **Step 3: Update README with run instructions**

Add:

```markdown
## Gameplay Module

Run the desktop version with:

```powershell
.\gradlew.bat lwjgl3:run
```

The current build starts in level selection for testing. Levels 1-5 are unlocked, and customization buttons cycle temporary colors for the candy and monster.
```
```

- [ ] **Step 4: Run tests**

Run:

```powershell
.\gradlew.bat core:test
```

Expected: unit tests pass.

- [ ] **Step 5: Run full build**

Run:

```powershell
.\gradlew.bat build
```

Expected: all modules build successfully.

- [ ] **Step 6: Run final manual verification**

Run:

```powershell
.\gradlew.bat lwjgl3:run
```

Verify:

- Level selection opens first.
- Color customization changes candy and monster.
- All five levels open.
- All five levels can be completed.
- Each level has three optional stars.
- Rope click bounds match visible rope.
- Cut rope animation appears and fades.
- Failure message and fade restart work.
- Victory panel appears with score, time, stars, failures, exit, and next.
- Exit returns to level selection and re-entering starts fresh.

- [ ] **Step 7: Commit final gameplay module**

```bash
git add README.md core/src/main/java core/src/test/java lwjgl3/src/main/java/com/tusderechos/Juego/lwjgl3/Lwjgl3Launcher.java
git commit -m "feat: complete Cut The Rope gameplay prototype"
```

---

## Self-Review

Spec coverage:

- Level selection, customization, five levels, Box2D physics, rope cutting, cut rope animation, stars, bubble, obstacle, moving platform, failure restart, victory panel, and integration boundary are covered.
- POO requirements are covered by `ElementoJuego`, `Obstaculo`, concrete entities, interfaces, enums, final utility classes, and recursive methods in scoring/progress.
- Future menu/account integration is kept outside gameplay through `ProgresoJugadorDemo` and constructor-based screen inputs.

Placeholder scan:

- The plan uses concrete files, commands, checks, and code snippets instead of empty implementation notes.
- Physics tuning is limited to `FabricaNiveles` and has concrete success criteria.

Type consistency:

- Screen constructors consistently pass `Juego`, `DatosNivel`, `PersonalizacionDulce`, and `PersonalizacionMonstruo`.
- Level data methods use Spanish `obtener...` naming consistently.
- Recursion methods are named exactly as used by tests.
