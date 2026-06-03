/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.obstaculos;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.entidades.ElementoJuego;

public abstract class Obstaculo extends ElementoJuego {
    public abstract boolean TocaDulce(Vector2 PosicionDulce, float RadioDulceActual);
}

