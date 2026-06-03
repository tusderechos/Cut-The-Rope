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

public final class DatosEstrella {
    private final Vector2 Posicion;

    public DatosEstrella(Vector2 Posicion) {
        ValidacionDatosNivel.ValidarVector(Posicion, "La posicion de la estrella");
        this.Posicion = new Vector2(Posicion);
    }
    public Vector2 ObtenerPosicion() {
        return new Vector2(Posicion);
    }
}

