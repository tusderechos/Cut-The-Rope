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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeometriaJuegoTest {
    @Test
    void CalculaDistanciaAlCentroDelSegmento() {
        float Distancia = GeometriaJuego.DistanciaPuntoASegmento(new Vector2(1f, 1f), new Vector2(0f, 0f), new Vector2(2f, 0f));

        assertEquals(1f, Distancia, 0.001f);
    }

    @Test
    void CalculaDistanciaAlExtremoMasCercano() {
        float Distancia = GeometriaJuego.DistanciaPuntoASegmento(new Vector2(3f, 0f), new Vector2(0f, 0f), new Vector2(2f, 0f));

        assertEquals(1f, Distancia, 0.001f);
    }

    @Test
    void ProyectaElClicSobreLaCuerdaVisible() {
        Vector2 PuntoProyectado = GeometriaJuego.ProyectarPuntoSobreSegmento(new Vector2(1f, 0.35f), new Vector2(0f, 0f), new Vector2(2f, 0f));

        assertEquals(1f, PuntoProyectado.x, 0.001f);
        assertEquals(0f, PuntoProyectado.y, 0.001f);
    }
}

