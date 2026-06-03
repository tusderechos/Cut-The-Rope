/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;

public final class GeometriaJuego {
    private GeometriaJuego() {
    }

    public static float DistanciaPuntoASegmento(Vector2 Punto, Vector2 Inicio, Vector2 Fin) {
        return Punto.dst(ProyectarPuntoSobreSegmento(Punto, Inicio, Fin));
    }

    public static Vector2 ProyectarPuntoSobreSegmento(Vector2 Punto, Vector2 Inicio, Vector2 Fin) {
        Vector2 Segmento = new Vector2(Fin).sub(Inicio);
        float LongitudCuadrada = Segmento.len2();
        if (LongitudCuadrada == 0f) {
            return new Vector2(Inicio);
        }
        float Proporcion = new Vector2(Punto).sub(Inicio).dot(Segmento) / LongitudCuadrada;
        Proporcion = Math.max(0f, Math.min(1f, Proporcion));
        return new Vector2(Inicio).mulAdd(Segmento, Proporcion);
    }
}

