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
import com.badlogic.gdx.graphics.Color;
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

    @Test
    void SeleccionNormalUsaImagenesDeBotonesDisponibles() {
        assertEquals("imagenes/fondo_1.PNG", PantallaSeleccionNivel.ObtenerRutaImagenNivel(1));
        assertEquals("imagenes/fondo_5.png", PantallaSeleccionNivel.ObtenerRutaImagenNivel(5));
        assertEquals("imagenes/boton_dulce_rojo.png", PantallaSeleccionNivel.ObtenerRutaBotonDulce(ColorDulce.Rojo));
        assertEquals("imagenes/boton_monstruo_azul.png", PantallaSeleccionNivel.ObtenerRutaBotonMonstruo(ColorMonstruo.Azul));
        assertEquals("imgMenus/btn_volver.png", PantallaSeleccionNivel.ObtenerRutaBotonMenuPrincipal());
        assertEquals("imagenes/fondo_menu_niveles.png", PantallaSeleccionNivel.ObtenerRutaFondoMenuNiveles());
        assertEquals("imagenes/seleccion_de_niveles.png", PantallaSeleccionNivel.ObtenerRutaTituloSeleccionNiveles());
    }

    @Test
    void SeleccionNormalUsaMarcosBlancos() {
        assertEquals(Color.WHITE, PantallaSeleccionNivel.ObtenerColorMarcoNivel());
        assertEquals(Color.WHITE, PantallaSeleccionNivel.ObtenerColorMarcoPreview());
    }
}
