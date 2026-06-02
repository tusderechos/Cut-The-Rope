package com.tusderechos.Juego.niveles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaNivelesTest {
    @Test
    void rechazaNumeroDeNivelFueraDelRangoDisponible() {
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.obtenerNivel(0));
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.obtenerNivel(6));
    }
}
