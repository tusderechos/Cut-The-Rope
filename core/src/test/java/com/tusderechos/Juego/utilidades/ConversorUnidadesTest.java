package com.tusderechos.Juego.utilidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversorUnidadesTest {
    @Test
    void conviertePixelesYMetrosConEscalaDelJuego() {
        assertEquals(1f, ConversorUnidades.pixelesAMetros(100f), 0.001f);
        assertEquals(250f, ConversorUnidades.metrosAPixeles(2.5f), 0.001f);
    }
}
