/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultadoNivelTest {
    @Test
    void RechazaDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(0, 2, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(6, 2, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, -1, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 4, 1000, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, -1, 5f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, 1000, -1f));
        assertThrows(IllegalArgumentException.class, () -> new ResultadoNivel(1, 2, 1000, Float.NaN));
    }
}

