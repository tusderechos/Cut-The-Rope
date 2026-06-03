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

class ConversorUnidadesTest {
    @Test
    void ConviertePixelesYMetrosConEscalaDelJuego() {
        assertEquals(1f, ConversorUnidades.PixelesAMetros(100f), 0.001f);
        assertEquals(250f, ConversorUnidades.MetrosAPixeles(2.5f), 0.001f);
    }
}

