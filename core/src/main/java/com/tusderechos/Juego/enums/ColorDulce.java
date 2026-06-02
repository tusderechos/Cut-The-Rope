package com.tusderechos.Juego.enums;

import com.badlogic.gdx.graphics.Color;

public enum ColorDulce {
    ROJO(new Color(0.95f, 0.18f, 0.20f, 1f)),
    AZUL(new Color(0.20f, 0.45f, 0.95f, 1f)),
    VERDE(new Color(0.20f, 0.75f, 0.35f, 1f)),
    AMARILLO(new Color(0.98f, 0.82f, 0.22f, 1f));

    private final Color color;

    ColorDulce(Color color) {
        this.color = color;
    }

    public Color obtenerColor() {
        return color.cpy();
    }

    public ColorDulce siguiente() {
        ColorDulce[] valores = values();
        return valores[(ordinal() + 1) % valores.length];
    }
}
