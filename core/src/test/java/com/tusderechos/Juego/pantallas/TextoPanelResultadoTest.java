/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextoPanelResultadoTest {
    @Test
    void CreaTodasLasLineasDelPanelDeVictoria() {
        List<String> Lineas = TextoPanelResultado.CrearLineas(2, 3100, 7200, 12.4f, 1);

        assertEquals(6, Lineas.size());
        assertEquals("Estrellas: 2/3", Lineas.get(0));
        assertEquals("Faltaron: 1", Lineas.get(1));
        assertEquals("Puntaje: 3100", Lineas.get(2));
        assertEquals("Total ranking: 7200", Lineas.get(3));
        assertEquals("Tiempo: 12 s", Lineas.get(4));
        assertEquals("Fallos: 1", Lineas.get(5));
    }

    @Test
    void CambiaTextoDeSiguienteEnElUltimoNivel() {
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(1));
        assertEquals("Final", TextoPanelResultado.CrearTextoSiguiente(5));
    }
}
