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

    public static boolean SegmentosEstanCerca(Vector2 PrimerInicio, Vector2 PrimerFin, Vector2 SegundoInicio, Vector2 SegundoFin, float Margen) {
        if (SegmentosSeCruzan(PrimerInicio, PrimerFin, SegundoInicio, SegundoFin)) {
            return true;
        }
        float DistanciaMinima = Math.min(DistanciaPuntoASegmento(PrimerInicio, SegundoInicio, SegundoFin), DistanciaPuntoASegmento(PrimerFin, SegundoInicio, SegundoFin));
        DistanciaMinima = Math.min(DistanciaMinima, DistanciaPuntoASegmento(SegundoInicio, PrimerInicio, PrimerFin));
        DistanciaMinima = Math.min(DistanciaMinima, DistanciaPuntoASegmento(SegundoFin, PrimerInicio, PrimerFin));

        return DistanciaMinima <= Margen;
    }

    private static boolean SegmentosSeCruzan(Vector2 PrimerInicio, Vector2 PrimerFin, Vector2 SegundoInicio, Vector2 SegundoFin) {
        float OrientacionUno = CalcularOrientacion(PrimerInicio, PrimerFin, SegundoInicio);
        float OrientacionDos = CalcularOrientacion(PrimerInicio, PrimerFin, SegundoFin);
        float OrientacionTres = CalcularOrientacion(SegundoInicio, SegundoFin, PrimerInicio);
        float OrientacionCuatro = CalcularOrientacion(SegundoInicio, SegundoFin, PrimerFin);

        if (OrientacionUno * OrientacionDos < 0f && OrientacionTres * OrientacionCuatro < 0f) {
            return true;
        }
        return PuntoEstaSobreSegmento(PrimerInicio, PrimerFin, SegundoInicio, OrientacionUno)
            || PuntoEstaSobreSegmento(PrimerInicio, PrimerFin, SegundoFin, OrientacionDos)
            || PuntoEstaSobreSegmento(SegundoInicio, SegundoFin, PrimerInicio, OrientacionTres)
            || PuntoEstaSobreSegmento(SegundoInicio, SegundoFin, PrimerFin, OrientacionCuatro);
    }

    private static float CalcularOrientacion(Vector2 Inicio, Vector2 Fin, Vector2 Punto) {
        return (Fin.x - Inicio.x) * (Punto.y - Inicio.y) - (Fin.y - Inicio.y) * (Punto.x - Inicio.x);
    }

    private static boolean PuntoEstaSobreSegmento(Vector2 Inicio, Vector2 Fin, Vector2 Punto, float Orientacion) {
        if (Math.abs(Orientacion) > 0.0001f) {
            return false;
        }
        return Punto.x >= Math.min(Inicio.x, Fin.x) - 0.0001f
            && Punto.x <= Math.max(Inicio.x, Fin.x) + 0.0001f
            && Punto.y >= Math.min(Inicio.y, Fin.y) - 0.0001f
            && Punto.y <= Math.max(Inicio.y, Fin.y) + 0.0001f;
    }
}

