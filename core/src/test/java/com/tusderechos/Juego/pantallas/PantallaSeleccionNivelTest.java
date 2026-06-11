/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PantallaSeleccionNivelTest {
    @Test
    void SeleccionNormalSiempreUsaNivelesBase() {
        PantallaSeleccionNivel Pantalla = new PantallaSeleccionNivel(null, ColorDulce.Azul, ColorMonstruo.Morado, CategoriaDificultad.Dificil);

        assertEquals(CategoriaDificultad.Facil, Pantalla.ObtenerCategoriaActual());
    }

    @Test
    void SeleccionNormalMuestraSoloCincoNivelesOriginales() {
        PantallaSeleccionNivel Pantalla = new PantallaSeleccionNivel(null, ColorDulce.Azul, ColorMonstruo.Morado, CategoriaDificultad.Media);

        assertEquals(5, Pantalla.ObtenerCantidadNivelesMostrados());
    }
}
