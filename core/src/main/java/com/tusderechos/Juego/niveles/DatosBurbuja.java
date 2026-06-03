/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;

public final class DatosBurbuja {
    private final Vector2 Posicion;
    private final float Radio;

    public DatosBurbuja(Vector2 Posicion, float Radio) {
        ValidacionDatosNivel.ValidarVector(Posicion, "La posicion de la burbuja");
        ValidacionDatosNivel.ValidarMedidaPositiva(Radio, "El Radio");
        this.Posicion = new Vector2(Posicion);
        this.Radio = Radio;
    }

    public Vector2 ObtenerPosicion() {
        return new Vector2(Posicion);
    }
    public float ObtenerRadio() {
        return Radio;
    }
}

