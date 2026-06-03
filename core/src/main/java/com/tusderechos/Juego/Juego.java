/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.tusderechos.Juego.niveles.ProgresoJugadorDemo;
import com.tusderechos.Juego.pantallas.PantallaSeleccionNivel;

public class Juego extends Game {
    private final ProgresoJugadorDemo ProgresoJugadorDemoActual = new ProgresoJugadorDemo();

    @Override
    public void create() {
        setScreen(new PantallaSeleccionNivel(this));
    }

    public void CambiarPantalla(Screen NuevaPantalla) {
        Screen PantallaAnterior = getScreen();
        setScreen(NuevaPantalla);
        if (PantallaAnterior != null) {
            PantallaAnterior.dispose();
        }
    }

    public ProgresoJugadorDemo ObtenerProgresoJugadorDemo() {
        return ProgresoJugadorDemoActual;
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        super.dispose();
    }
}

