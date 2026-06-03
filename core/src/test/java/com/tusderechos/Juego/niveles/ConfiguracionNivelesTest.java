/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.utilidades.ConstantesJuego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracionNivelesTest {
    @Test
    void TodosLosNivelesTienenTresEstrellasYPosicionesVisibles() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            assertEquals(3, Nivel.ObtenerEstrellas().size(), Nivel.ObtenerNombre());
            AssertDentroDelMundo(Nivel.ObtenerPosicionDulce().x, Nivel.ObtenerPosicionDulce().y);
            AssertDentroDelMundo(Nivel.ObtenerPosicionMonstruo().x, Nivel.ObtenerPosicionMonstruo().y);
            Nivel.ObtenerEstrellas().forEach(EstrellaActual -> AssertDentroDelMundo(EstrellaActual.ObtenerPosicion().x, EstrellaActual.ObtenerPosicion().y));
            Nivel.ObtenerCuerdas().forEach(CuerdaActual -> {
                AssertDentroDelMundo(CuerdaActual.ObtenerAncla().x, CuerdaActual.ObtenerAncla().y);
                assertEquals(Nivel.ObtenerPosicionDulce().dst(CuerdaActual.ObtenerAncla()), CuerdaActual.ObtenerLongitud(), 0.005f);
            });
            Nivel.ObtenerBurbujas().forEach(BurbujaActual -> AssertDentroDelMundo(BurbujaActual.ObtenerPosicion().x, BurbujaActual.ObtenerPosicion().y));
        }
    }

    @Test
    void NivelTresTieneUnaBurbujaLibreSeparadaDelDulce() {
        DatosNivel NivelTres = FabricaNiveles.ObtenerNivel(3);

        assertEquals(1, NivelTres.ObtenerBurbujas().size());
        DatosBurbuja BurbujaActual = NivelTres.ObtenerBurbujas().get(0);
        assertTrue(NivelTres.ObtenerPosicionDulce().dst(BurbujaActual.ObtenerPosicion()) > BurbujaActual.ObtenerRadio() + ConstantesJuego.RadioDulce);
    }

    @Test
    void NingunNivelRecolectaEstrellasAlIniciar() {
        float DistanciaMinima = ConstantesJuego.RadioDulce + ConstantesJuego.RadioEstrella;

        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            Nivel.ObtenerEstrellas().forEach(EstrellaActual -> assertTrue(Nivel.ObtenerPosicionDulce().dst(EstrellaActual.ObtenerPosicion()) > DistanciaMinima, Nivel.ObtenerNombre()));
        }
    }

    @Test
    void NivelCincoIncluyePlataformaMovil() {
        assertTrue(FabricaNiveles.ObtenerNivel(5).TienePlataformaMovil());
    }

    private void AssertDentroDelMundo(float X, float Y) {
        assertTrue(X >= 0f && X <= ConstantesJuego.AnchoMundo);
        assertTrue(Y >= 0f && Y <= ConstantesJuego.AltoMundo);
    }
}

