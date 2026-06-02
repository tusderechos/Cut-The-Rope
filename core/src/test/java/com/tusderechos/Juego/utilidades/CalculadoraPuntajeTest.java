package com.tusderechos.Juego.utilidades;

import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPuntajeTest {
    @Test
    void sumaPuntajeAcumuladoConRecursion() {
        int total = CalculadoraPuntaje.calcularPuntajeAcumuladoRecursivo(Arrays.asList(
            new ResultadoNivel(1, 3, 3000, 12f),
            new ResultadoNivel(2, 2, 2100, 18f),
            new ResultadoNivel(3, 1, 1200, 20f)
        ));

        assertEquals(6300, total);
    }

    @Test
    void rechazaDatosInvalidosAlCalcularPuntaje() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.calcularPuntajeIntento(-1, 5f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.calcularPuntajeIntento(4, 5f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.calcularPuntajeIntento(2, -1f, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.calcularPuntajeIntento(2, Float.NaN, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraPuntaje.calcularPuntajeIntento(2, 5f, -1));
    }

    @Test
    void evitaOverflowConMuchosFallos() {
        assertEquals(0, CalculadoraPuntaje.calcularPuntajeIntento(3, 1f, Integer.MAX_VALUE));
    }

    @Test
    void limitaPuntajeAcumuladoCuandoSuperaRangoEntero() {
        int total = CalculadoraPuntaje.calcularPuntajeAcumuladoRecursivo(Arrays.asList(
            new ResultadoNivel(1, 3, Integer.MAX_VALUE, 1f),
            new ResultadoNivel(2, 3, Integer.MAX_VALUE, 1f)
        ));

        assertEquals(Integer.MAX_VALUE, total);
    }
}
