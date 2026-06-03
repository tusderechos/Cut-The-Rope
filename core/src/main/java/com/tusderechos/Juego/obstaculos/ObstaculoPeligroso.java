/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.obstaculos;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ObstaculoPeligroso extends Obstaculo {
    private final Vector2 Posicion;
    private final float Ancho;
    private final float Alto;

    public ObstaculoPeligroso(Vector2 Posicion, float Ancho, float Alto) {
        this.Posicion = new Vector2(Posicion);
        this.Ancho = Ancho;
        this.Alto = Alto;
    }

    @Override
    public boolean TocaDulce(Vector2 PosicionDulce, float RadioDulceActual) {
        float PuntoCercanoX = Math.max(Posicion.x, Math.min(PosicionDulce.x, Posicion.x + Ancho));
        float PuntoCercanoY = Math.max(Posicion.y, Math.min(PosicionDulce.y, Posicion.y + Alto));
        return PosicionDulce.dst2(PuntoCercanoX, PuntoCercanoY) <= RadioDulceActual * RadioDulceActual;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        ShapeRendererActual.setColor(new Color(0.86f, 0.16f, 0.14f, 1f));
        ShapeRendererActual.rect(Posicion.x, Posicion.y, Ancho, Alto);
    }
}

