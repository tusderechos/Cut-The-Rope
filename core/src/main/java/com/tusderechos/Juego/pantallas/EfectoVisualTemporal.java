/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public class EfectoVisualTemporal implements Actualizable, Dibujable {
    private final Vector2 Posicion;
    private final float RadioInicial;
    private final float RadioFinal;
    private final Color ColorBase;
    private static final float Duracion = 0.6f;
    private float Edad;

    public EfectoVisualTemporal(Vector2 Posicion, float RadioInicial, float RadioFinal, Color ColorBase) {
        this.Posicion = new Vector2(Posicion);
        this.RadioInicial = RadioInicial;
        this.RadioFinal = RadioFinal;
        this.ColorBase = new Color(ColorBase);
    }

    @Override
    public void Actualizar(float Delta) {
        Edad += Delta;
    }

    public boolean EstaFinalizado() {
        return Edad >= Duracion;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        float Progreso = Math.min(1f, Edad / Duracion);
        float RadioActual = RadioInicial + (RadioFinal - RadioInicial) * Progreso;
        float AlphaActual = 1f - Progreso;
        ShapeRendererActual.setColor(new Color(ColorBase.r, ColorBase.g, ColorBase.b, AlphaActual));
        ShapeRendererActual.circle(Posicion.x, Posicion.y, RadioActual, 24);
    }
}
