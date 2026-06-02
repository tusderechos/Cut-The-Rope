package com.tusderechos.Juego.niveles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultadoNivelTest {
    @Test
    void rechazaDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(0, 2, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(6, 2, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, -1, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 4, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, -1, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, 1000, -1f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, 1000, Float.NaN));
    }
}
