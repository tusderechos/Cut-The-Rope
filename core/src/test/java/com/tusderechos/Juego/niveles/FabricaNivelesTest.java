/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaNivelesTest {
    @Test
    void RechazaNumeroDeNivelFueraDelRangoDisponible() {
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.ObtenerNivel(0));
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.ObtenerNivel(16));
    }

    @Test
    void ExponeCatalogoInmutableYCantidadDisponible() {
        assertEquals(15, FabricaNiveles.CantidadNiveles());
        assertThrows(UnsupportedOperationException.class, () -> FabricaNiveles.CrearNiveles().clear());
    }

    @Test
    void ExponeNivelesPorCategoriaParaRivalidad() {
        assertEquals(5, FabricaNiveles.CrearNivelesPorCategoria(CategoriaDificultad.Facil).size());
        assertEquals(5, FabricaNiveles.CrearNivelesPorCategoria(CategoriaDificultad.Media).size());
        assertEquals(5, FabricaNiveles.CrearNivelesPorCategoria(CategoriaDificultad.Dificil).size());
        assertEquals(5, FabricaNiveles.CantidadNiveles(CategoriaDificultad.Facil));
        assertEquals(5, FabricaNiveles.CantidadNiveles(CategoriaDificultad.Media));
        assertEquals(5, FabricaNiveles.CantidadNiveles(CategoriaDificultad.Dificil));
    }

    @Test
    void ObtieneNivelPorCategoriaYNumeroInterno() {
        DatosNivel NivelTresFacil = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Facil, 3);

        assertEquals(FabricaNiveles.ObtenerNivel(3), NivelTresFacil);
        assertEquals(CategoriaDificultad.Facil, NivelTresFacil.ObtenerCategoria());
        assertEquals(3, NivelTresFacil.ObtenerNumeroEnCategoria());
        assertEquals(8, FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 3).ObtenerNumero());
        assertEquals(13, FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 3).ObtenerNumero());
        assertThrows(IllegalArgumentException.class, () -> FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 6));
    }

    @Test
    void NivelesActualesPertenecenACategoriaFacil() {
        for (int NumeroNivel = 1; NumeroNivel <= 5; NumeroNivel++) {
            DatosNivel Nivel = FabricaNiveles.ObtenerNivel(NumeroNivel);

            assertEquals(CategoriaDificultad.Facil, Nivel.ObtenerCategoria());
            assertEquals(NumeroNivel, Nivel.ObtenerNumeroEnCategoria());
        }
    }

    @Test
    void NivelesMediosPertenecenACategoriaMedia() {
        for (int NumeroNivel = 1; NumeroNivel <= 5; NumeroNivel++) {
            DatosNivel Nivel = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, NumeroNivel);

            assertEquals(CategoriaDificultad.Media, Nivel.ObtenerCategoria());
            assertEquals(NumeroNivel, Nivel.ObtenerNumeroEnCategoria());
            assertEquals(NumeroNivel + 5, Nivel.ObtenerNumero());
        }
    }

    @Test
    void NivelesDificilesPertenecenACategoriaDificil() {
        for (int NumeroNivel = 1; NumeroNivel <= 5; NumeroNivel++) {
            DatosNivel Nivel = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, NumeroNivel);

            assertEquals(CategoriaDificultad.Dificil, Nivel.ObtenerCategoria());
            assertEquals(NumeroNivel, Nivel.ObtenerNumeroEnCategoria());
            assertEquals(NumeroNivel + 10, Nivel.ObtenerNumero());
        }
    }
}

