# Cut the Rope Gameplay Redesign Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Make rope cutting, bubbles, level routes, transitions, and the victory panel predictable and playable across all five prototype levels.

**Architecture:** Keep Box2D responsible for candy motion and joints. Add small geometry and bubble state operations that can be unit-tested independently, then call gameplay rule checks from every fixed physics step. Keep visual rope fragments separate from the physical joint.

**Tech Stack:** Java, libGDX, Box2D, Gradle, JUnit 5

---

### Task 1: Correct rope cut geometry

**Files:**
- Modify: `core/src/main/java/com/tusderechos/Juego/utilidades/GeometriaJuego.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/entidades/Cuerda.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`
- Test: `core/src/test/java/com/tusderechos/Juego/utilidades/GeometriaJuegoTest.java`

- [ ] Add a failing test that projects an off-segment click onto the visible rope.
- [ ] Run `./gradlew core:test --tests com.tusderechos.Juego.utilidades.GeometriaJuegoTest` and confirm failure.
- [ ] Add `GeometriaJuego.proyectarPuntoSobreSegmento`.
- [ ] Select the nearest eligible rope and construct fragments with the projected point.
- [ ] Run the focused test and suite.

### Task 2: Activate bubbles only on candy contact

**Files:**
- Modify: `core/src/main/java/com/tusderechos/Juego/entidades/Burbuja.java`
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`
- Test: `core/src/test/java/com/tusderechos/Juego/entidades/EntidadesInteractivasTest.java`

- [ ] Add failing tests proving a free bubble is inactive, activates on candy contact, follows candy, and can burst.
- [ ] Run the focused test and confirm failure.
- [ ] Represent free, attached, and burst bubble states explicitly.
- [ ] Apply a mass-scaled upward force only while attached.
- [ ] Run the focused test and suite.

### Task 3: Check game rules inside fixed physics steps

**Files:**
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] Move star collection, bubble contact, hazard, bounds, and victory checks into each fixed step.
- [ ] Clamp simulated time consistently after long frames.
- [ ] Block non-primary clicks and interactions during transitions.
- [ ] Render dark transition overlays after text so they cover the full frame.
- [ ] Compile and run the suite.

### Task 4: Redesign level routes

**Files:**
- Modify: `core/src/main/java/com/tusderechos/Juego/niveles/FabricaNiveles.java`
- Test: `core/src/test/java/com/tusderechos/Juego/niveles/ConfiguracionNivelesTest.java`

- [ ] Add tests for one free bubble in level 3 and the moving platform in level 5.
- [ ] Reposition ropes, stars, bubble, obstacle, and monster to support direct and three-star paths.
- [ ] Verify every level through repeated manual play attempts.

### Task 5: Restore readable UI

**Files:**
- Modify: `core/src/main/java/com/tusderechos/Juego/pantallas/PantallaJuego.java`

- [ ] Increase world-space font scale and place overlay text inside its panel.
- [ ] Keep exit and next controls visible and label them clearly.
- [ ] Compile and manually inspect the selector, gameplay HUD, failure fade, and victory panel.

### Task 6: Audit and verification

**Files:**
- Inspect: `core/src/main/java`
- Inspect: `lwjgl3/src/main/java`

- [ ] Run `./gradlew core:test`.
- [ ] Run `./gradlew build`.
- [ ] Dispatch independent reviews for gameplay, Java quality, test abuse, and visual behavior.
- [ ] Fix every confirmed issue and rerun verification.
