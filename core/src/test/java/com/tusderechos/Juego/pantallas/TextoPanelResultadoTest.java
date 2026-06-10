/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import java.util.List;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.ResultadoReto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextoPanelResultadoTest {
    @Test
    void CreaTodasLasLineasDelPanelDeVictoria() {
        List<String> Lineas = TextoPanelResultado.CrearLineas(2, 3100, 12.4f, 1);

        assertEquals(4, Lineas.size());
        assertEquals("Puntaje conseguido: 3100", Lineas.get(0));
        assertEquals("Tiempo usado: 12 s", Lineas.get(1));
        assertEquals("Fallos del intento: 1", Lineas.get(2));
        assertEquals("Estrellas faltantes: 1", Lineas.get(3));
    }

    @Test
    void CreaLineasDelPanelDeReto() {
        DatosReto Reto = new DatosReto(CategoriaDificultad.Media, 2, "Admin", 3000, 2);
        ResultadoReto Resultado = new ResultadoReto(Reto, 3400, 3, true);
        List<String> Lineas = TextoPanelResultado.CrearLineasReto(Reto, Resultado, 3400, 7.2f, 1);

        assertEquals(5, Lineas.size());
        assertEquals("Puntaje conseguido: 3400", Lineas.get(0));
        assertEquals("Tiempo usado: 7 s", Lineas.get(1));
        assertEquals("Fallos del intento: 1", Lineas.get(2));
        assertEquals("Objetivo: 3000 pts / 2 estrellas", Lineas.get(3));
        assertEquals("Reto superado", Lineas.get(4));
    }

    @Test
    void CambiaTextoDeSiguienteEnElUltimoNivel() {
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(1));
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(5));
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(10));
        assertEquals("Final", TextoPanelResultado.CrearTextoSiguiente(15));
    }

    @Test
    void CambiaTextoDeSiguienteSegunCategoriaDelNivel() {
        assertEquals("Siguiente", TextoPanelResultado.CrearTextoSiguiente(FabricaNiveles.ObtenerNivel(1)));
        assertEquals("Final", TextoPanelResultado.CrearTextoSiguiente(FabricaNiveles.ObtenerNivel(5)));
        assertEquals("Final", TextoPanelResultado.CrearTextoSiguiente(FabricaNiveles.ObtenerNivel(10)));
    }
}
