/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatosRetoTest {
    @Test
    void ConservaDatosNecesariosParaResolverNivelDeRivalidad() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 3, "Admin", 3200, 2);

        assertEquals(CategoriaDificultad.Media, Reto.ObtenerCategoria());
        assertEquals(3, Reto.ObtenerNumeroEnCategoria());
        assertEquals("Admin", Reto.ObtenerNombreRetador());
        assertEquals(3200, Reto.ObtenerPuntajeObjetivo());
        assertEquals(2, Reto.ObtenerEstrellasObjetivo());
    }

    @Test
    void RechazaDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(null, 1, "Admin", 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 0, "Admin", 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 6, "Admin", 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 1, "", 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 1, "Admin", -1, 1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 1, "Admin", 1000, -1));
        assertThrows(IllegalArgumentException.class, () -> new DatosReto(CategoriaDificultad.Facil, 1, "Admin", 1000, 4));
    }
}
