/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.graficos.GestorTexturas;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Monstruo extends ElementoJuego {
    private final Vector2 Posicion;
    private final PersonalizacionMonstruo Personalizacion;

    public Monstruo(Vector2 Posicion, PersonalizacionMonstruo Personalizacion) {
        this.Posicion = new Vector2(Posicion);
        this.Personalizacion = Personalizacion;
    }

    public boolean ContieneDulce(Vector2 PosicionDulce) {
        return Posicion.dst(PosicionDulce) <= ConstantesJuego.RadioMonstruo + ConstantesJuego.RadioDulce;
    }

    public void EstablecerPosicion(Vector2 NuevaPosicion) {
        Posicion.set(NuevaPosicion);
    }

    public Vector2 ObtenerPosicion() {
        return new Vector2(Posicion);
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        ShapeRendererActual.setColor(Personalizacion.ObtenerColor());
        ShapeRendererActual.circle(Posicion.x, Posicion.y, ConstantesJuego.RadioMonstruo, 32);
    }

    public boolean DibujarTextura(SpriteBatch Batch, GestorTexturas GestorTexturasActual) {
        Texture Textura = GestorTexturasActual.ObtenerMonstruo(Personalizacion.ObtenerColorMonstruo());
        if (Textura == null) {
            return false;
        }
        float Tamano = ConstantesJuego.RadioMonstruo * 2.05f;
        Batch.draw(Textura, Posicion.x - Tamano / 2f, Posicion.y - Tamano / 2f, Tamano, Tamano);
        return true;
    }
}

