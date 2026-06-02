package com.tusderechos.Juego.obstaculos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ObstaculoPeligroso extends Obstaculo {
    private final Vector2 posicion;
    private final float ancho;
    private final float alto;

    public ObstaculoPeligroso(Vector2 posicion, float ancho, float alto) {
        this.posicion = new Vector2(posicion);
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public boolean tocaDulce(Vector2 posicionDulce, float radioDulce) {
        float puntoCercanoX = Math.max(posicion.x, Math.min(posicionDulce.x, posicion.x + ancho));
        float puntoCercanoY = Math.max(posicion.y, Math.min(posicionDulce.y, posicion.y + alto));
        return posicionDulce.dst2(puntoCercanoX, puntoCercanoY) <= radioDulce * radioDulce;
    }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(new Color(0.86f, 0.16f, 0.14f, 1f));
        shapeRenderer.rect(posicion.x, posicion.y, ancho, alto);
    }
}
