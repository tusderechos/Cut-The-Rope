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
        return color.cpy();
    }

    public ColorMonstruo siguiente() {
        ColorMonstruo[] valores = values();
        return valores[(ordinal() + 1) % valores.length];
    }
}
