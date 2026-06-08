/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public class CuerdaCortadaVisual implements Actualizable, Dibujable {
    private static final float Duracion = 0.75f;
    private static final float GrosorSombra = 0.060f;
    private static final float GrosorCuerpo = 0.044f;
    private final Vector2 InicioSuperior;
    private final Vector2 FinSuperior;
    private final Vector2 InicioInferior;
    private final Vector2 FinInferior;
    private float Edad;

    public CuerdaCortadaVisual(Vector2 Inicio, Vector2 Corte, Vector2 Fin) {
        InicioSuperior = new Vector2(Inicio);
        FinSuperior = new Vector2(Corte);
        InicioInferior = new Vector2(Corte);
        FinInferior = new Vector2(Fin);
    }

    @Override
    public void Actualizar(float Delta) {
        Edad += Delta;
        FinSuperior.y -= Delta * 0.9f;
        InicioInferior.y -= Delta * 1.2f;
        FinInferior.y -= Delta * 1.5f;
    }

    public boolean EstaFinalizada() {
        return Edad >= Duracion;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        float Alpha = Math.max(0f, 1f - Edad / Duracion);
        DibujarSegmento(ShapeRendererActual, InicioSuperior, FinSuperior, Alpha);
        DibujarSegmento(ShapeRendererActual, InicioInferior, FinInferior, Alpha);
    }

    private void DibujarSegmento(ShapeRenderer ShapeRendererActual, Vector2 Inicio, Vector2 Fin, float Alpha) {
        ShapeRendererActual.setColor(new Color(0.26f, 0.18f, 0.10f, Alpha * 0.75f));
        ShapeRendererActual.rectLine(Inicio, Fin, GrosorSombra);
        ShapeRendererActual.setColor(new Color(0.72f, 0.52f, 0.27f, Alpha));
        ShapeRendererActual.rectLine(Inicio, Fin, GrosorCuerpo);
    }
}

