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

public class PlataformaMovil implements Actualizable, Dibujable {
    private final Vector2 Posicion;
    private final float MinimoX;
    private final float MaximoX;
    private final float Velocidad;
    private float Direccion = 1f;

    public PlataformaMovil(Vector2 PosicionInicial, float MinimoX, float MaximoX, float Velocidad) {
        Posicion = new Vector2(PosicionInicial);
        this.MinimoX = MinimoX;
        this.MaximoX = MaximoX;
        this.Velocidad = Velocidad;
    }

    @Override
    public void Actualizar(float Delta) {
        Posicion.x += Velocidad * Direccion * Delta;
        if (Posicion.x >= MaximoX) {
            Posicion.x = MaximoX;
            Direccion = -1f;
        } else if (Posicion.x <= MinimoX) {
            Posicion.x = MinimoX;
            Direccion = 1f;
        }
    }

    public Vector2 ObtenerPosicionMonstruo() {
        return new Vector2(Posicion.x, Posicion.y + 0.28f);
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        ShapeRendererActual.setColor(new Color(0.44f, 0.48f, 0.55f, 1f));
        ShapeRendererActual.rect(Posicion.x - 0.65f, Posicion.y, 1.3f, 0.18f);
    }
}

