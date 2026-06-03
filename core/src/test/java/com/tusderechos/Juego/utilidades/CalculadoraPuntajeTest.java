/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPuntajeTest {
    @Test
    void SumaPuntajeAcumuladoConRecursion() {
        int Total = CalculadoraPuntaje.CalcularPuntajeAcumuladoRecursivo(Arrays.asList(new ResultadoNivel(1, 3, 3000, 12f), new ResultadoNivel(2, 2, 2100, 18f), new ResultadoNivel(3, 1, 1200, 20f)));

        assertEquals(6300, Total);
    }

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

    @Test
    void LimitaPuntajeAcumuladoCuandoSuperaRangoEntero() {
        int Total = CalculadoraPuntaje.CalcularPuntajeAcumuladoRecursivo(Arrays.asList(new ResultadoNivel(1, 3, Integer.MAX_VALUE, 1f), new ResultadoNivel(2, 3, Integer.MAX_VALUE, 1f)));

        assertEquals(Integer.MAX_VALUE, Total);
    }

    @Test
    void RechazaListaNulaAlCalcularPuntajeAcumulado() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.CalcularPuntajeAcumuladoRecursivo(null));
    }
}

