package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosBurbuja {
    private final Vector2 posicion;
    private final float radio;

    public DatosBurbuja(Vector2 posicion, float radio) {
        this.posicion = new Vector2(posicion);
        this.radio = radio;
    }

    public Vector2 obtenerPosicion() { return new Vector2(posicion); }
    public float obtenerRadio() { return radio; }
}
