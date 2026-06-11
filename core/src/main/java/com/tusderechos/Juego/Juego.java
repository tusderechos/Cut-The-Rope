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

public abstract class Juego extends Game {
    @Override
    public void create() {
        setScreen(new PantallaSeleccionNivel(this));
        //setScreen(new Menus.Menu.LoginRegisterScreen(this));
    }

    protected abstract Screen CrearPantallaInicial();

    public final void CambiarPantalla(Screen NuevaPantalla) {
        Screen PantallaAnterior = getScreen();
        setScreen(NuevaPantalla);
        if (PantallaAnterior != null) {
            PantallaAnterior.dispose();
        }
    }

    @Override
    public final void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        super.dispose();
    }
}

