package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosEstrella {
    private final Vector2 posicion;

    public DatosEstrella(Vector2 posicion) { this.posicion = new Vector2(posicion); }
    public Vector2 obtenerPosicion() { return new Vector2(posicion); }
}
