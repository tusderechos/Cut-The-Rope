/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.Screen;
import Menus.Menu.LoginRegisterScreen;
import Menus.Menu.StartScreen;

public final class CutTheRope extends Juego {
    @Override
    protected Screen CrearPantallaInicial() {
        return new StartScreen(this);
    }
}
