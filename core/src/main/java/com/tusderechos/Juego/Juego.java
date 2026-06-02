package com.tusderechos.Juego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.tusderechos.Juego.niveles.ProgresoJugadorDemo;
import com.tusderechos.Juego.pantallas.PantallaSeleccionNivel;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Juego extends Game {
    private final ProgresoJugadorDemo progresoJugadorDemo = new ProgresoJugadorDemo();

    @Override
    public void create() {
        setScreen(new PantallaSeleccionNivel(this));
    }

    public void cambiarPantalla(Screen nuevaPantalla) {
        Screen pantallaAnterior = getScreen();
        setScreen(nuevaPantalla);
        if (pantallaAnterior != null) pantallaAnterior.dispose();
    }

    public ProgresoJugadorDemo obtenerProgresoJugadorDemo() { return progresoJugadorDemo; }

    @Override
    public void dispose() {
        if (getScreen() != null) getScreen().dispose();
        super.dispose();
    }
}
