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

public final class DatosObstaculo {
    private final Vector2 Posicion;
    private final float Ancho;
    private final float Alto;

    public DatosObstaculo(Vector2 Posicion, float Ancho, float Alto) {
        ValidacionDatosNivel.ValidarVector(Posicion, "La posicion del obstaculo");
        ValidacionDatosNivel.ValidarMedidaPositiva(Ancho, "El Ancho");
        ValidacionDatosNivel.ValidarMedidaPositiva(Alto, "El Alto");
        this.Posicion = new Vector2(Posicion);
        this.Ancho = Ancho;
        this.Alto = Alto;
    }

    public Vector2 ObtenerPosicion() {
        return new Vector2(Posicion);
    }
    public float ObtenerAncho() {
        return Ancho;
    }
    public float ObtenerAlto() {
        return Alto;
    }
}

