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
import com.tusderechos.Juego.niveles.ResultadoNivel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolicitudRivalidadTest {
    @Test
    void IniciaPendienteConRetadorYRetadoSeparados() {
        SolicitudRivalidad Solicitud = CrearSolicitudBase();

        assertNotNull(Solicitud.ObtenerId());
        assertEquals("santos", Solicitud.ObtenerUsernameRetador());
        assertEquals("clara_09", Solicitud.ObtenerUsernameRetado());
        assertEquals(EstadoRivalidad.Pendiente, Solicitud.ObtenerEstado());
    }

    @Test
    void AlAceptarElRetadoJuegaPrimero() {
        SolicitudRivalidad Solicitud = CrearSolicitudBase();

        GestorRivalidades.AceptarSolicitud(Solicitud, "clara_09");

        assertEquals(EstadoRivalidad.TurnoRetado, Solicitud.ObtenerEstado());
        assertEquals("clara_09", Solicitud.ObtenerUsernameConTurno());
    }

    @Test
    void RechazaAceptarSiNoEsElRetado() {
        SolicitudRivalidad Solicitud = CrearSolicitudBase();

        assertThrows(IllegalStateException.class, () -> GestorRivalidades.AceptarSolicitud(Solicitud, "santos"));
    }

    @Test
    void RegistraTurnosEnOrdenYDeclaraGanador() {
        SolicitudRivalidad Solicitud = CrearSolicitudBase();
        GestorRivalidades.AceptarSolicitud(Solicitud, "clara_09");
        int NumeroNivelReal = GestorRetos.ObtenerNivelReto(Solicitud.ObtenerReto()).ObtenerNumero();

        GestorRivalidades.RegistrarResultado(Solicitud, "clara_09", new ResultadoNivel(NumeroNivelReal, 2, 3600, 5f));

        assertEquals(EstadoRivalidad.TurnoRetador, Solicitud.ObtenerEstado());
        assertEquals("santos", Solicitud.ObtenerUsernameConTurno());

        GestorRivalidades.RegistrarResultado(Solicitud, "santos", new ResultadoNivel(NumeroNivelReal, 3, 3900, 8f));

        assertEquals(EstadoRivalidad.Finalizada, Solicitud.ObtenerEstado());
        assertEquals("santos", Solicitud.ObtenerGanador());
    }

    @Test
    void RechazaResultadoFueraDeTurno() {
        SolicitudRivalidad Solicitud = CrearSolicitudBase();
        GestorRivalidades.AceptarSolicitud(Solicitud, "clara_09");
        int NumeroNivelReal = GestorRetos.ObtenerNivelReto(Solicitud.ObtenerReto()).ObtenerNumero();

        assertThrows(IllegalStateException.class, () -> GestorRivalidades.RegistrarResultado(Solicitud, "santos", new ResultadoNivel(NumeroNivelReal, 3, 3900, 8f)));
    }

    private SolicitudRivalidad CrearSolicitudBase() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 3, "santos", 3200, 2);

        return new SolicitudRivalidad(Reto, "santos", "clara_09");
    }
}
