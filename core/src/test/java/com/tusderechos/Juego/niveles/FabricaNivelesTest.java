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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaNivelesTest {
    @Test
    void RechazaNumeroDeNivelFueraDelRangoDisponible() {
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.ObtenerNivel(0));
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.ObtenerNivel(6));
    }

    @Test
    void ExponeCatalogoInmutableYCantidadDisponible() {
        assertEquals(5, FabricaNiveles.CantidadNiveles());
        assertThrows(UnsupportedOperationException.class, () -> FabricaNiveles.CrearNiveles().clear());
    }
}

