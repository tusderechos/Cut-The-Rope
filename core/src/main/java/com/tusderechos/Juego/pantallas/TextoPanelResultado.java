/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.ResultadoReto;
import com.tusderechos.Juego.textos.TextosIdioma;
import java.util.Arrays;
import java.util.List;

public final class TextoPanelResultado {
    private TextoPanelResultado() {
    }

    public static List<String> CrearLineas(int EstrellasRecolectadas, int PuntajeFinal, float TiempoNivel, int FallosNivel) {
        return Arrays.asList(TextosIdioma.Formatear("PuntajeConseguido", PuntajeFinal), TextosIdioma.Formatear("TiempoUsado", Math.round(TiempoNivel)), TextosIdioma.Formatear("FallosIntento", FallosNivel), TextosIdioma.Formatear("EstrellasFaltantes", (3 - EstrellasRecolectadas)));
    }

    public static List<String> CrearLineasFinal(int EstrellasRecolectadas, int PuntajeFinal, float TiempoNivel, int FallosNivel) {
        return Arrays.asList(TextosIdioma.Obtener("CompletasteTodos"), TextosIdioma.Formatear("PuntajeConseguido", PuntajeFinal), TextosIdioma.Formatear("TiempoUsado", Math.round(TiempoNivel)), TextosIdioma.Formatear("FallosIntento", FallosNivel), TextosIdioma.Formatear("EstrellasFaltantes", (3 - EstrellasRecolectadas)));
    }

    public static List<String> CrearLineasReto(DatosReto Reto, ResultadoReto Resultado, int PuntajeFinalVisible, float TiempoNivel, int FallosNivel) {
        if (Reto == null || Resultado == null) {
            throw new IllegalArgumentException("Los datos del reto no pueden ser nulos");
        }

        return Arrays.asList(TextosIdioma.Formatear("PuntajeConseguido", PuntajeFinalVisible), TextosIdioma.Formatear("TiempoUsado", Math.round(TiempoNivel)), TextosIdioma.Formatear("FallosIntento", FallosNivel), TextosIdioma.Formatear("ObjetivoReto", Reto.ObtenerPuntajeObjetivo(), Reto.ObtenerEstrellasObjetivo()), Resultado.RetoFueSuperado() ? TextosIdioma.Obtener("RetoSuperado") : TextosIdioma.Obtener("RetoFallido"));
    }

    public static String CrearTextoSiguiente(int NumeroNivelActual) {
        return NumeroNivelActual < FabricaNiveles.CantidadNiveles() ? TextosIdioma.Obtener("Siguiente") : TextosIdioma.Obtener("Final");
    }

    public static String CrearTextoSiguiente(DatosNivel NivelActual) {
        return NivelActual.ObtenerNumeroEnCategoria() < FabricaNiveles.CantidadNiveles(NivelActual.ObtenerCategoria()) ? TextosIdioma.Obtener("Siguiente") : TextosIdioma.Obtener("Final");
    }
}
