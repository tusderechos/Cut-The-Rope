/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatosNivelTest {
    @Test
    void RechazaMedidasFisicasInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> new DatosCuerda(new Vector2(), 0f));
        assertThrows(IllegalArgumentException.class, () -> new DatosBurbuja(new Vector2(), Float.NaN));
        assertThrows(IllegalArgumentException.class, () -> new DatosObstaculo(new Vector2(), 1f, -1f));
    }

    @Test
    void RechazaContratoDeNivelIncompleto() {
        assertThrows(IllegalArgumentException.class, () -> new DatosNivel(1, CategoriaDificultad.Facil, 1, "", DificultadNivel.Facil, new Vector2(), new Vector2(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false));
        assertThrows(IllegalArgumentException.class, () -> new DatosNivel(1, CategoriaDificultad.Facil, 1, "Prueba", DificultadNivel.Facil, new Vector2(), new Vector2(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false));
        assertThrows(IllegalArgumentException.class, () -> new DatosNivel(1, null, 1, "Prueba", DificultadNivel.Facil, new Vector2(), new Vector2(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false));
        assertThrows(IllegalArgumentException.class, () -> new DatosNivel(1, CategoriaDificultad.Facil, 0, "Prueba", DificultadNivel.Facil, new Vector2(), new Vector2(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false));
    }

    @Test
    void ConservaCategoriaYNumeroInterno() {
        DatosNivel Nivel = new DatosNivel(8, CategoriaDificultad.Media, 3, "Reto", DificultadNivel.Intermedio, new Vector2(), new Vector2(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false);

        assertEquals(CategoriaDificultad.Media, Nivel.ObtenerCategoria());
        assertEquals(3, Nivel.ObtenerNumeroEnCategoria());
    }
}

