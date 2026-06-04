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
        List<String> Lineas = TextoPanelResultado.CrearLineas(2, 3100, 12.4f, 1);

        assertEquals(4, Lineas.size());
        assertEquals("Puntaje conseguido: 3100", Lineas.get(0));
        assertEquals("Tiempo usado: 12 s", Lineas.get(1));
        assertEquals("Fallos del intento: 1", Lineas.get(2));
        assertEquals("Estrellas faltantes: 1", Lineas.get(3));
    }

    @Test
    void CambiaTextoDeSiguienteEnElUltimoNivel() {
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(1));
        assertEquals("Final", TextoPanelResultado.CrearTextoSiguiente(5));
    }
}
