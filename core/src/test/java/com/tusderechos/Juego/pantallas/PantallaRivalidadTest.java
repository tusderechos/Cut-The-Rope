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
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.SolicitudRivalidad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PantallaRivalidadTest {
    @Test
    void IniciaConCategoriaMediaParaRetos() {
        PantallaRivalidad Pantalla = new PantallaRivalidad(null, ColorDulce.Rojo, ColorMonstruo.Verde);

        assertEquals(CategoriaDificultad.Media, Pantalla.ObtenerCategoriaActual());
        assertEquals(1, Pantalla.ObtenerNumeroNivelActual());
    }

    @Test
    void LimitaNivelEstrellasYPuntajeDelReto() {
        PantallaRivalidad Pantalla = new PantallaRivalidad(null, ColorDulce.Azul, ColorMonstruo.Morado, CategoriaDificultad.Dificil);

        Pantalla.AjustarNivel(99);
        Pantalla.AjustarEstrellasObjetivo(99);
        Pantalla.AjustarPuntajeObjetivo(99999);

        assertEquals(5, Pantalla.ObtenerNumeroNivelActual());
        assertEquals(3, Pantalla.ObtenerEstrellasObjetivo());
        assertEquals(4000, Pantalla.ObtenerPuntajeObjetivo());

        Pantalla.AjustarNivel(-99);
        Pantalla.AjustarEstrellasObjetivo(-99);
        Pantalla.AjustarPuntajeObjetivo(-99999);

        assertEquals(1, Pantalla.ObtenerNumeroNivelActual());
        assertEquals(0, Pantalla.ObtenerEstrellasObjetivo());
        assertEquals(0, Pantalla.ObtenerPuntajeObjetivo());
    }

    @Test
    void CreaRetoConLaConfiguracionActual() {
        PantallaRivalidad Pantalla = new PantallaRivalidad(null, ColorDulce.Verde, ColorMonstruo.Naranja, CategoriaDificultad.Media);
        Pantalla.AjustarNivel(2);
        Pantalla.AjustarEstrellasObjetivo(1);
        Pantalla.AjustarPuntajeObjetivo(500);

        DatosReto Reto = Pantalla.CrearRetoActual();

        assertEquals(CategoriaDificultad.Media, Reto.ObtenerCategoria());
        assertEquals(3, Reto.ObtenerNumeroEnCategoria());
        assertEquals(3, Reto.ObtenerEstrellasObjetivo());
        assertEquals(3700, Reto.ObtenerPuntajeObjetivo());
    }

    @Test
    void CreaSolicitudConUsuarioRetadoParaFlujoPorTurnos() {
        PantallaRivalidad Pantalla = new PantallaRivalidad(null, ColorDulce.Verde, ColorMonstruo.Naranja, CategoriaDificultad.Media, "Clara_09");

        SolicitudRivalidad Solicitud = Pantalla.CrearSolicitudActual("Clara_09");

        assertEquals("admin", Solicitud.ObtenerUsernameRetador());
        assertEquals("clara_09", Solicitud.ObtenerUsernameRetado());
        assertEquals(CategoriaDificultad.Media, Solicitud.ObtenerReto().ObtenerCategoria());
    }

    @Test
    void RechazaCategoriaNula() {
        PantallaRivalidad Pantalla = new PantallaRivalidad(null, ColorDulce.Rojo, ColorMonstruo.Verde);

        assertThrows(IllegalArgumentException.class, () -> Pantalla.CambiarCategoria(null));
    }

    @Test
    void AjustaAnchoDelPanelAlTamanoDeLaVentana() {
        assertEquals(580f, PantallaRivalidad.CalcularAnchoPanel(900f));
        assertEquals(336f, PantallaRivalidad.CalcularAnchoPanel(360f));
        assertEquals(0f, PantallaRivalidad.CalcularAnchoPanel(20f));
    }
}
