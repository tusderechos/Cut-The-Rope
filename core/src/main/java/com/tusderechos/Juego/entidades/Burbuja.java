package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Dibujable;

public class Burbuja implements Dibujable {
    private final Vector2 posicion;
    private final float radio;
    private boolean activa = true;

    public Burbuja(Vector2 posicion, float radio) {
        this.posicion = new Vector2(posicion);
        this.radio = radio;
    }

    public boolean contienePunto(Vector2 puntoMundo) { return activa && posicion.dst(puntoMundo) <= radio; }
    public boolean estaActiva() { return activa; }
    public void reventar() { activa = false; }
    public void seguirDulce(Vector2 posicionDulce) { if (activa) posicion.set(posicionDulce); }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        if (!activa) return;
        shapeRenderer.setColor(new Color(0.40f, 0.82f, 0.96f, 0.45f));
        shapeRenderer.circle(posicion.x, posicion.y, radio, 32);
    }
}
