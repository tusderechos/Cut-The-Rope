package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Dibujable;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Estrella implements Dibujable {
    private final Vector2 posicion;
    private boolean recolectada;

    public Estrella(Vector2 posicion) { this.posicion = new Vector2(posicion); }

    public boolean intentarRecolectar(Vector2 posicionDulce) {
        if (recolectada || posicion.dst(posicionDulce) > ConstantesJuego.RADIO_ESTRELLA + ConstantesJuego.RADIO_DULCE) {
            return false;
        }
        recolectada = true;
        return true;
    }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        if (recolectada) return;
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.circle(posicion.x, posicion.y, ConstantesJuego.RADIO_ESTRELLA, 16);
    }
}
