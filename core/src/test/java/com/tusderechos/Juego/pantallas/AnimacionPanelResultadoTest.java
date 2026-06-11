/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AnimacionPanelResultadoTest {
    @Test
    void CuentaPuntajeHastaElResultadoFinal() {
        AnimacionPanelResultado Animacion = new AnimacionPanelResultado(5000);

        Animacion.Actualizar(0.45f);
        assertEquals(1500, Animacion.ObtenerPuntajeVisible());

        Animacion.Actualizar(3f);
        assertEquals(5000, Animacion.ObtenerPuntajeVisible());
    }

    @Test
    void MuestraLineasDelPanelDeFormaProgresiva() {
        AnimacionPanelResultado Animacion = new AnimacionPanelResultado(3000);

        assertEquals(1, Animacion.ObtenerCantidadLineasVisibles(4));

        Animacion.Actualizar(0.40f);
        assertEquals(2, Animacion.ObtenerCantidadLineasVisibles(4));

        Animacion.Actualizar(0.80f);
        assertEquals(4, Animacion.ObtenerCantidadLineasVisibles(4));
    }
}
