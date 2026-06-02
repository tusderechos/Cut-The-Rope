package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosObstaculo {
    private final Vector2 posicion;
    private final float ancho;
    private final float alto;

    public DatosObstaculo(Vector2 posicion, float ancho, float alto) {
        this.posicion = new Vector2(posicion);
        this.ancho = ancho;
        this.alto = alto;
    }

    public Vector2 obtenerPosicion() { return new Vector2(posicion); }
    public float obtenerAncho() { return ancho; }
    public float obtenerAlto() { return alto; }
}
