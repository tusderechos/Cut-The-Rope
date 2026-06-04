/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.enums;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;

public enum ColorMonstruo {
    Verde(new Color(0.25f, 0.80f, 0.32f, 1f)),
    Morado(new Color(0.56f, 0.28f, 0.82f, 1f)),
    Naranja(new Color(0.95f, 0.48f, 0.18f, 1f)),
    Azul(new Color(0.25f, 0.75f, 0.95f, 1f));

    private final Color ColorActual;

    ColorMonstruo(Color ColorActual) {
        this.ColorActual = ColorActual;
    }

    public Color ObtenerColor() {
        return ColorActual.cpy();
    }

    public ColorMonstruo Siguiente() {
        ColorMonstruo[] Valores = values();
        return Valores[(ordinal() + 1) % Valores.length];
    }
}

