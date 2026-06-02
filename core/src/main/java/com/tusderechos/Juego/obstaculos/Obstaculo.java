package com.tusderechos.Juego.obstaculos;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.entidades.ElementoJuego;

public abstract class Obstaculo extends ElementoJuego {
    public abstract boolean tocaDulce(Vector2 posicionDulce, float radioDulce);
}
