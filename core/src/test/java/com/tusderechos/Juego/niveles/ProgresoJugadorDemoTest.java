package com.tusderechos.Juego.niveles;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgresoJugadorDemoTest {
    @Test
    void encuentraSiguienteNivelDisponibleConRecursion() {
        ProgresoJugadorDemo progreso = new ProgresoJugadorDemo();

        int siguiente = progreso.buscarSiguienteNivelDisponibleRecursivo(FabricaNiveles.crearNiveles(), 3);

        assertEquals(4, siguiente);
    }

    @Test
    void conservaMejorPuntajeCuandoLlegaUnResultadoPeor() {
        ProgresoJugadorDemo progreso = new ProgresoJugadorDemo();
        progreso.registrarResultado(new ResultadoNivel(1, 3, 4000, 8f));

        progreso.registrarResultado(new ResultadoNivel(1, 1, 1200, 20f));

        assertEquals(4000, progreso.obtenerMejoresResultados().get(0).obtenerPuntaje());
    }

    @Test
    void encuentraSiguienteNivelAunqueLaListaEsteDesordenada() {
        ProgresoJugadorDemo progreso = new ProgresoJugadorDemo();

        int siguiente = progreso.buscarSiguienteNivelDisponibleRecursivo(
            Arrays.asList(FabricaNiveles.obtenerNivel(5), FabricaNiveles.obtenerNivel(4)), 3);

        assertEquals(4, siguiente);
    }
}
