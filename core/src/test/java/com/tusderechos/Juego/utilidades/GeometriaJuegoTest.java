package com.tusderechos.Juego.utilidades;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeometriaJuegoTest {
    @Test
    void calculaDistanciaAlCentroDelSegmento() {
        float distancia = GeometriaJuego.distanciaPuntoASegmento(
            new Vector2(1f, 1f), new Vector2(0f, 0f), new Vector2(2f, 0f));

        assertEquals(1f, distancia, 0.001f);
    }

    @Test
    void calculaDistanciaAlExtremoMasCercano() {
        float distancia = GeometriaJuego.distanciaPuntoASegmento(
            new Vector2(3f, 0f), new Vector2(0f, 0f), new Vector2(2f, 0f));

        assertEquals(1f, distancia, 0.001f);
    }
}
