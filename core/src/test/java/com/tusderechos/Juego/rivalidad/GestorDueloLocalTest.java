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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorDueloLocalTest {
    @Test
    void CreaDueloConTurnoInicialDelRetador() {
        DueloLocal Duelo = GestorDueloLocal.CrearDuelo(new DatosReto(CategoriaDificultad.Facil, 1, "santos", 2500, 2), "santos", "clara_09");

        assertEquals("santos", Duelo.ObtenerUsernameConTurno());
        assertFalse(Duelo.EstaFinalizado());
    }

    @Test
    void RegistraTurnosYDeclaraGanador() {
        DueloLocal Duelo = GestorDueloLocal.CrearDuelo(new DatosReto(CategoriaDificultad.Facil, 1, "santos", 2500, 2), "santos", "clara_09");
        int NumeroNivelReal = GestorRetos.ObtenerNivelReto(Duelo.ObtenerReto()).ObtenerNumero();

        GestorDueloLocal.RegistrarResultado(GestorDueloLocal.CrearIdTurno(Duelo, "santos"), new ResultadoNivel(NumeroNivelReal, 2, 3000, 8f));
        assertEquals("clara_09", Duelo.ObtenerUsernameConTurno());

        GestorDueloLocal.RegistrarResultado(GestorDueloLocal.CrearIdTurno(Duelo, "clara_09"), new ResultadoNivel(NumeroNivelReal, 3, 3100, 9f));

        assertTrue(Duelo.EstaFinalizado());
        assertEquals("clara_09", Duelo.ObtenerGanador());
    }

    @Test
    void ReconoceIdsDeDueloLocal() {
        DueloLocal Duelo = GestorDueloLocal.CrearDuelo(new DatosReto(CategoriaDificultad.Facil, 1, "santos", 2500, 2), "santos", "clara_09");
        String IdTurno = GestorDueloLocal.CrearIdTurno(Duelo, "santos");

        assertTrue(GestorDueloLocal.EsIdDueloLocal(IdTurno));
        assertEquals(Duelo, GestorDueloLocal.ObtenerDueloDesdeId(IdTurno));
        assertEquals("santos", GestorDueloLocal.ObtenerUsernameDesdeId(IdTurno));
    }

    @Test
    void IntentarRegistrarResultadoDevuelveFalsoSiElDueloNoExiste() {
        assertFalse(GestorDueloLocal.IntentarRegistrarResultado("duelo-local:no-existe:santos", new ResultadoNivel(1, 3, 3000, 7f)));
    }
}
