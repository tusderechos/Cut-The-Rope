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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.graficos.GestorTexturas;
import com.tusderechos.Juego.interfaces.Dibujable;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Estrella implements Dibujable {
    private final Vector2 Posicion;
    private boolean Recolectada;

    public Estrella(Vector2 Posicion) {
        this.Posicion = new Vector2(Posicion);
    }

    public boolean IntentarRecolectar(Vector2 PosicionDulce) {
        if (Recolectada || Posicion.dst(PosicionDulce) > ConstantesJuego.RadioEstrella + ConstantesJuego.RadioDulce) {
            return false;
        }
        Recolectada = true;
        return true;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        if (Recolectada) {
            return;
        }
        ShapeRendererActual.setColor(Color.GOLD);
        ShapeRendererActual.circle(Posicion.x, Posicion.y, ConstantesJuego.RadioEstrella, 16);
    }

    public boolean DibujarTextura(SpriteBatch Batch, GestorTexturas GestorTexturasActual) {
        if (Recolectada) {
            return true;
        }
        Texture Textura = GestorTexturasActual.ObtenerEstrella(false);
        if (Textura == null) {
            return false;
        }
        float Tamano = ConstantesJuego.RadioEstrella * 2.45f;
        Batch.draw(Textura, Posicion.x - Tamano / 2f, Posicion.y - Tamano / 2f, Tamano, Tamano);
        return true;
    }

}

