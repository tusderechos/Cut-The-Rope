/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.textos;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TextosIdiomaTest {
    @Test
    void ObtieneTextosPrincipalesPorIdioma() {
        assertEquals("Nivel completado", TextosIdioma.ObtenerConIdioma("ESP", "NivelCompletado"));
        assertEquals("Level completed", TextosIdioma.ObtenerConIdioma("ENG", "NivelCompletado"));
        assertEquals("Niveau termine", TextosIdioma.ObtenerConIdioma("FRA", "NivelCompletado"));
        assertEquals("Nivelu furendei", TextosIdioma.ObtenerConIdioma("GAR", "NivelCompletado"));
        assertEquals("Shlav hoshlem", TextosIdioma.ObtenerConIdioma("HEB", "NivelCompletado"));
    }

    @Test
    void FormateaResumenDeRetoSinUsarTextoBaseSiempre() {
        String ResumenIngles = TextosIdioma.FormatearConIdioma("ENG", "RetoResumen", TextosIdioma.ObtenerConIdioma("ENG", "CategoriaMedia"), 3, 3200, 2);
        String ResumenFrances = TextosIdioma.FormatearConIdioma("FRA", "RetoResumen", TextosIdioma.ObtenerConIdioma("FRA", "CategoriaMedia"), 3, 3200, 2);

        assertEquals("Medium 3 - 3200 pts / 2 stars", ResumenIngles);
        assertEquals("Moyen 3 - 3200 pts / 2 etoiles", ResumenFrances);
        assertNotEquals(TextosIdioma.FormatearConIdioma("ESP", "RetoResumen", TextosIdioma.ObtenerConIdioma("ESP", "CategoriaMedia"), 3, 3200, 2), ResumenIngles);
    }

    @Test
    void ObtieneCategoriaTraducidaDesdeEnum() {
        assertEquals("Hard", TextosIdioma.ObtenerConIdioma("ENG", "Categoria" + CategoriaDificultad.Dificil.name()));
        assertEquals("Difficile", TextosIdioma.ObtenerConIdioma("FRA", "Categoria" + CategoriaDificultad.Dificil.name()));
    }
}
