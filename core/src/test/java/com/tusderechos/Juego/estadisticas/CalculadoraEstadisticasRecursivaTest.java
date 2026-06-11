/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.estadisticas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import com.tusderechos.Juego.persistencia.RegistroPartida;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraEstadisticasRecursivaTest {
    @Test
    void SumaEstrellasUsandoRecursion() {
        List<RegistroPartida> Registros = Arrays.asList(CrearRegistro(1, 2, 3000), CrearRegistro(2, 3, 4200), CrearRegistro(3, 1, 2500));

        assertEquals(6, CalculadoraEstadisticasRecursiva.SumarEstrellas(Registros));
    }

    @Test
    void ObtieneMejorPuntajeUsandoRecursion() {
        List<RegistroPartida> Registros = Arrays.asList(CrearRegistro(1, 2, 3000), CrearRegistro(2, 3, 4200), CrearRegistro(3, 1, 2500));

        assertEquals(4200, CalculadoraEstadisticasRecursiva.ObtenerMejorPuntaje(Registros));
    }

    @Test
    void ManejaListaVaciaYRechazaListaNula() {
        assertEquals(0, CalculadoraEstadisticasRecursiva.SumarEstrellas(Collections.<RegistroPartida>emptyList()));
        assertEquals(0, CalculadoraEstadisticasRecursiva.ObtenerMejorPuntaje(Collections.<RegistroPartida>emptyList()));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraEstadisticasRecursiva.SumarEstrellas(null));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraEstadisticasRecursiva.ObtenerMejorPuntaje(null));
    }

    private RegistroPartida CrearRegistro(int NumeroNivel, int Estrellas, int Puntaje) {
        DatosNivel Nivel = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Facil, NumeroNivel);
        ResultadoNivel Resultado = new ResultadoNivel(Nivel.ObtenerNumero(), Estrellas, Puntaje, 5f);

        return RegistroPartida.CrearDesdeResultado(Nivel, Resultado, 0, null);
    }
}
