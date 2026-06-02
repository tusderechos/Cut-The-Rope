package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public class PlataformaMovil implements Actualizable, Dibujable {
    private final Vector2 posicion;
    private final float minimoX;
    private final float maximoX;
    private final float velocidad;
    private float direccion = 1f;

    public PlataformaMovil(Vector2 posicionInicial, float minimoX, float maximoX, float velocidad) {
        posicion = new Vector2(posicionInicial);
        this.minimoX = minimoX;
        this.maximoX = maximoX;
        this.velocidad = velocidad;
    }

    @Override
    public void actualizar(float delta) {
        posicion.x += velocidad * direccion * delta;
        if (posicion.x >= maximoX) {
            posicion.x = maximoX;
            direccion = -1f;
        } else if (posicion.x <= minimoX) {
            posicion.x = minimoX;
            direccion = 1f;
        }
    }

    public Vector2 obtenerPosicionMonstruo() { return new Vector2(posicion.x, posicion.y + 0.28f); }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(new Color(0.44f, 0.48f, 0.55f, 1f));
        shapeRenderer.rect(posicion.x - 0.65f, posicion.y, 1.3f, 0.18f);
    }
}
