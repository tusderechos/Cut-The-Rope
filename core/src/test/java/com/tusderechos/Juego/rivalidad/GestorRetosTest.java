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
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestorRetosTest {
    @Test
    void ResuelveNivelDesdeCategoriaYNumeroInterno() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Dificil, 3, "Admin", 3000, 2);
        DatosNivel Nivel = GestorRetos.ObtenerNivelReto(Reto);

        assertEquals(FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 3), Nivel);
    }

    @Test
    void EvaluaRetoSuperadoPorPuntajeYEstrellas() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 2, "Admin", 3000, 2);
        ResultadoNivel ResultadoJugador = new ResultadoNivel(FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 2).ObtenerNumero(), 2, 3400, 6f);
        ResultadoReto Resultado = GestorRetos.EvaluarResultado(Reto, ResultadoJugador);

        assertTrue(Resultado.RetoFueSuperado());
        assertEquals(400, Resultado.ObtenerDiferenciaPuntaje());
        assertEquals(2, Resultado.ObtenerEstrellasJugador());
    }

    @Test
    void EvaluaRetoFallidoSiNoAlcanzaObjetivo() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 2, "Admin", 3000, 3);
        ResultadoNivel ResultadoJugador = new ResultadoNivel(FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 2).ObtenerNumero(), 2, 3400, 6f);
        ResultadoReto Resultado = GestorRetos.EvaluarResultado(Reto, ResultadoJugador);

        assertFalse(Resultado.RetoFueSuperado());
        assertEquals(400, Resultado.ObtenerDiferenciaPuntaje());
    }

    @Test
    void RechazaCompararResultadoDeOtroNivel() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 2, "Admin", 3000, 2);
        ResultadoNivel ResultadoJugador = new ResultadoNivel(FabricaNiveles.ObtenerNivel(CategoriaDificultad.Facil, 2).ObtenerNumero(), 3, 5000, 4f);

        assertThrows(IllegalArgumentException.class, () -> GestorRetos.EvaluarResultado(Reto, ResultadoJugador));
    }

    @Test
    void CreaRetosDePruebaParaMenuOAdmin() {
        assertEquals(3, GestorRetos.CrearRetosDePrueba().size());
        assertThrows(UnsupportedOperationException.class, () -> GestorRetos.CrearRetosDePrueba().clear());
    }
}
