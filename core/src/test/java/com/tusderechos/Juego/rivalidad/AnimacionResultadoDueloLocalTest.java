/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimacionResultadoDueloLocalTest {
    @Test
    void CuentaPrimeroRetadorDespuesRetadoYDespuesRevelaGanador() {
        AnimacionResultadoDueloLocal Animacion = new AnimacionResultadoDueloLocal(1f, 0.5f, 0.5f);
        ResultadoTurnoRivalidad Retador = new ResultadoTurnoRivalidad("santos", 4000, 2, 7f);
        ResultadoTurnoRivalidad Retado = new ResultadoTurnoRivalidad("clara", 3000, 3, 5f);

        Animacion.Avanzar(0.5f);
        assertEquals(2000, Animacion.ObtenerPuntajeRetador(Retador));
        assertEquals(0, Animacion.ObtenerPuntajeRetado(Retado));
        assertFalse(Animacion.DebeMostrarGanador());

        Animacion.Avanzar(0.5f);
        assertEquals(4000, Animacion.ObtenerPuntajeRetador(Retador));
        assertEquals(0, Animacion.ObtenerPuntajeRetado(Retado));

        Animacion.Avanzar(0.5f);
        assertEquals(4000, Animacion.ObtenerPuntajeRetador(Retador));
        assertEquals(1500, Animacion.ObtenerPuntajeRetado(Retado));
        assertFalse(Animacion.DebeMostrarGanador());

        Animacion.Avanzar(1f);
        assertEquals(4000, Animacion.ObtenerPuntajeRetador(Retador));
        assertEquals(3000, Animacion.ObtenerPuntajeRetado(Retado));
        assertTrue(Animacion.DebeMostrarGanador());
        assertEquals(0.0f, Animacion.ObtenerProgresoRevealGanador(), 0.001f);

        Animacion.Avanzar(0.25f);
        assertEquals(0.5f, Animacion.ObtenerProgresoRevealGanador(), 0.001f);

        Animacion.Avanzar(0.25f);
        assertTrue(Animacion.FinalizoRevealGanador());
        assertEquals(1.0f, Animacion.ObtenerProgresoRevealGanador(), 0.001f);
    }
}