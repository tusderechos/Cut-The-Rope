package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public class CuerdaCortadaVisual implements Actualizable, Dibujable {
    private final Vector2 inicio;
    private final Vector2 corte;
    private final Vector2 fin;
    private float edad;
    private static final float DURACION = 0.75f;

    public CuerdaCortadaVisual(Vector2 inicio, Vector2 corte, Vector2 fin) {
        this.inicio = new Vector2(inicio);
        this.corte = new Vector2(corte);
        this.fin = new Vector2(fin);
    }

    @Override
    public void actualizar(float delta) {
        edad += delta;
        corte.y -= delta * 0.9f;
        fin.y -= delta * 1.5f;
    }

    public boolean estaFinalizada() { return edad >= DURACION; }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        float alpha = Math.max(0f, 1f - edad / DURACION);
        shapeRenderer.setColor(new Color(0.82f, 0.70f, 0.46f, alpha));
        shapeRenderer.line(inicio, corte);
        shapeRenderer.line(corte, fin);
    }
}
