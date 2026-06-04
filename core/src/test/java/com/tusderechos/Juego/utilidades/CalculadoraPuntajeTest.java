/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPuntajeTest {
    @Test
    void RechazaDatosInvalidosAlCalcularPuntaje() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeIntento(-1, 5f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeIntento(4, 5f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeIntento(2, -1f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeIntento(2, Float.NaN, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeIntento(2, 5f, -1));
    }

    @Test
    void EvitaOverflowConMuchosFallos() {
        assertEquals(0, CalculadoraPuntaje.CalcularPuntajeIntento(3, 1f, Integer.MAX_VALUE));
    }

}

