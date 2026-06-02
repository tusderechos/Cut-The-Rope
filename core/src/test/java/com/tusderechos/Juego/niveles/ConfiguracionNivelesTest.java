package com.tusderechos.Juego.niveles;

import com.tusderechos.Juego.utilidades.ConstantesJuego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracionNivelesTest {
    @Test
    void todosLosNivelesTienenTresEstrellasYPosicionesVisibles() {
        for (DatosNivel nivel : FabricaNiveles.crearNiveles()) {
            assertEquals(3, nivel.obtenerEstrellas().size(), nivel.obtenerNombre());
            assertDentroDelMundo(nivel.obtenerPosicionDulce().x, nivel.obtenerPosicionDulce().y);
            assertDentroDelMundo(nivel.obtenerPosicionMonstruo().x, nivel.obtenerPosicionMonstruo().y);
            nivel.obtenerEstrellas().forEach(estrella ->
                assertDentroDelMundo(estrella.obtenerPosicion().x, estrella.obtenerPosicion().y));
        }
    }

    private void assertDentroDelMundo(float x, float y) {
        assertTrue(x >= 0f && x <= ConstantesJuego.ANCHO_MUNDO);
        assertTrue(y >= 0f && y <= ConstantesJuego.ALTO_MUNDO);
    }
}
