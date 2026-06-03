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

public enum ColorDulce {
    Rojo(new Color(0.95f, 0.18f, 0.20f, 1f)),
    Azul(new Color(0.20f, 0.45f, 0.95f, 1f)),
    Verde(new Color(0.20f, 0.75f, 0.35f, 1f)),
    Amarillo(new Color(0.98f, 0.82f, 0.22f, 1f));

    private final Color ColorActual;

    ColorDulce(Color ColorActual) {
        this.ColorActual = ColorActual;
    }

    public Color ObtenerColor() {
        return ColorActual.cpy();
    }

    public ColorDulce Siguiente() {
        ColorDulce[] Valores = values();
        return Valores[(ordinal() + 1) % Valores.length];
    }
}

