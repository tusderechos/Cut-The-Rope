package com.tusderechos.Juego.utilidades;

import com.badlogic.gdx.math.Vector2;

public final class GeometriaJuego {
    private GeometriaJuego() {
    }

    public static float distanciaPuntoASegmento(Vector2 punto, Vector2 inicio, Vector2 fin) {
        Vector2 segmento = new Vector2(fin).sub(inicio);
        float longitudCuadrada = segmento.len2();
        if (longitudCuadrada == 0f) return punto.dst(inicio);
        float proporcion = new Vector2(punto).sub(inicio).dot(segmento) / longitudCuadrada;
        proporcion = Math.max(0f, Math.min(1f, proporcion));
        return punto.dst(new Vector2(inicio).mulAdd(segmento, proporcion));
    }
}
