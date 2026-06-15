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
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RutasTexturasTest {
    @Test
    void ObtieneRutasDeSpritesParaCadaPersonalizacion() {
        assertEquals("imagenes/dulce_rojo.png", RutasTexturas.ObtenerDulce(ColorDulce.Rojo));
        assertEquals("imagenes/dulce_azul.png", RutasTexturas.ObtenerDulce(ColorDulce.Azul));
        assertEquals("imagenes/dulce_verde.png", RutasTexturas.ObtenerDulce(ColorDulce.Verde));
        assertEquals("imagenes/dulce_morado.png", RutasTexturas.ObtenerDulce(ColorDulce.Morado));
        assertEquals("imagenes/monstruo_verde.png", RutasTexturas.ObtenerMonstruo(ColorMonstruo.Verde));
        assertEquals("imagenes/monstruo_morado.png", RutasTexturas.ObtenerMonstruo(ColorMonstruo.Morado));
        assertEquals("imagenes/monstruo_naranja.png", RutasTexturas.ObtenerMonstruo(ColorMonstruo.Naranja));
        assertEquals("imagenes/monstruo_azul.png", RutasTexturas.ObtenerMonstruo(ColorMonstruo.Azul));
    }

    @Test
    void ListaTodasLasTexturasSinRepetirRutas() {
        List<String> Rutas = RutasTexturas.ObtenerRutas();
        Set<String> RutasUnicas = new HashSet<>(Rutas);

        assertEquals(16, Rutas.size());
        assertEquals(Rutas.size(), RutasUnicas.size());
        assertTrue(Rutas.contains(RutasTexturas.Estrella));
        assertTrue(Rutas.contains(RutasTexturas.EstrellaVacia));
        assertTrue(Rutas.contains(RutasTexturas.BloqueoNivel));
    }

    @Test
    void ObtieneUnFondoParaCadaNivel() {
        assertEquals("imagenes/fondo_1.PNG", RutasTexturas.ObtenerFondoNivel(1));
        assertEquals("imagenes/fondo_2.PNG", RutasTexturas.ObtenerFondoNivel(2));
        assertEquals("imagenes/fondo_3.png", RutasTexturas.ObtenerFondoNivel(3));
        assertEquals("imagenes/fondo_4.png", RutasTexturas.ObtenerFondoNivel(4));
        assertEquals("imagenes/fondo_5.png", RutasTexturas.ObtenerFondoNivel(5));
    }
}
