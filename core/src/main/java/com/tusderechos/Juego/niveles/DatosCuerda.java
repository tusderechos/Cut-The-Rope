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

public final class DatosCuerda {
    private final Vector2 Ancla;
    private final float Longitud;

    public DatosCuerda(Vector2 Ancla, float Longitud) {
        ValidacionDatosNivel.ValidarVector(Ancla, "El Ancla");
        ValidacionDatosNivel.ValidarMedidaPositiva(Longitud, "La Longitud");
        this.Ancla = new Vector2(Ancla);
        this.Longitud = Longitud;
    }

    public Vector2 ObtenerAncla() {
        return new Vector2(Ancla);
    }
    public float ObtenerLongitud() {
        return Longitud;
    }
}

