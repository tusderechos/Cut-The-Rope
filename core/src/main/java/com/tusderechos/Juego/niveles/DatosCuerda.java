package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;

public class DatosCuerda {
    private final Vector2 ancla;
    private final float longitud;

    public DatosCuerda(Vector2 ancla, float longitud) {
        this.ancla = new Vector2(ancla);
        this.longitud = longitud;
    }

    public Vector2 obtenerAncla() { return new Vector2(ancla); }
    public float obtenerLongitud() { return longitud; }
}
