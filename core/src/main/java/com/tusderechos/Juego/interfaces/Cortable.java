package com.tusderechos.Juego.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Cortable {
    boolean contienePuntoDeCorte(Vector2 puntoMundo);
    void cortar(Vector2 puntoMundo);
    boolean estaCortada();
}
