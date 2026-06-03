/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProgresoJugadorDemoTest {
    @Test
    void EncuentraSiguienteNivelDisponibleConRecursion() {
        ProgresoJugadorDemo Progreso = new ProgresoJugadorDemo();

        int Siguiente = Progreso.BuscarSiguienteNivelDisponibleRecursivo(FabricaNiveles.CrearNiveles(), 3);

        assertEquals(4, Siguiente);
    }

    @Test
    void ConservaMejorPuntajeCuandoLlegaUnResultadoPeor() {
        ProgresoJugadorDemo Progreso = new ProgresoJugadorDemo();
        Progreso.RegistrarResultado(new ResultadoNivel(1, 3, 4000, 8f));

        Progreso.RegistrarResultado(new ResultadoNivel(1, 1, 1200, 20f));

        assertEquals(4000, Progreso.ObtenerMejoresResultados().get(0).ObtenerPuntaje());
    }

    @Test
    void EncuentraSiguienteNivelAunqueLaListaEsteDesordenada() {
        ProgresoJugadorDemo Progreso = new ProgresoJugadorDemo();

        int Siguiente = Progreso.BuscarSiguienteNivelDisponibleRecursivo(Arrays.asList(FabricaNiveles.ObtenerNivel(5), FabricaNiveles.ObtenerNivel(4)), 3);

        assertEquals(4, Siguiente);
    }

    @Test
    void RechazaResultadoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ProgresoJugadorDemo().RegistrarResultado(null));
    }
}

