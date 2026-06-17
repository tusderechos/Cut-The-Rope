/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.graficos;

/**
 *
 * @author Hp
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RutasAssetsIdiomaTest {

    @Test
    void ConvierteIdiomasConfiguradosASufijosDeAssets() {
        assertEquals("esp", RutasAssetsIdioma.ObtenerSufijoIdioma("ESP"));
        assertEquals("ing", RutasAssetsIdioma.ObtenerSufijoIdioma("ENG"));
        assertEquals("fra", RutasAssetsIdioma.ObtenerSufijoIdioma("FRA"));
        assertEquals("gar", RutasAssetsIdioma.ObtenerSufijoIdioma("GAR"));
        assertEquals("heb", RutasAssetsIdioma.ObtenerSufijoIdioma("HEB"));
    }

    @Test
    void ConvierteIdiomasConfiguradosACarpetasDeAssets() {
        assertEquals("", RutasAssetsIdioma.ObtenerCarpetaIdioma("ESP"));
        assertEquals("ingles", RutasAssetsIdioma.ObtenerCarpetaIdioma("ENG"));
        assertEquals("frances", RutasAssetsIdioma.ObtenerCarpetaIdioma("FRA"));
        assertEquals("garifuna", RutasAssetsIdioma.ObtenerCarpetaIdioma("GAR"));
        assertEquals("hebreo", RutasAssetsIdioma.ObtenerCarpetaIdioma("HEB"));
    }

    @Test
    void IdiomaDesconocidoCaeAEspanolBase() {
        assertEquals("esp", RutasAssetsIdioma.ObtenerSufijoIdioma("KLINGON"));
        assertEquals("", RutasAssetsIdioma.ObtenerCarpetaIdioma("KLINGON"));
    }
}
