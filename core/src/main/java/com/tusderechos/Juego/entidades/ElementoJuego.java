/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tusderechos.Juego.interfaces.Dibujable;

public abstract class ElementoJuego implements Dibujable {
    @Override
    public abstract void Dibujar(ShapeRenderer ShapeRendererActual);
}

