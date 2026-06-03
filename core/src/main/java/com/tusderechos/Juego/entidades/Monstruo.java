/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        ShapeRendererActual.setColor(Personalizacion.ObtenerColor());
        ShapeRendererActual.circle(Posicion.x, Posicion.y, ConstantesJuego.RadioMonstruo, 32);
    }
}

