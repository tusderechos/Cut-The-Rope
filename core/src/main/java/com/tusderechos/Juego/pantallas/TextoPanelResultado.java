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
import java.util.Arrays;
import java.util.List;

public final class TextoPanelResultado {
    private TextoPanelResultado() {
    }

    public static List<String> CrearLineas(int EstrellasRecolectadas, int PuntajeFinal, float TiempoNivel, int FallosNivel) {
        return Arrays.asList("Puntaje conseguido: " + PuntajeFinal, "Tiempo usado: " + Math.round(TiempoNivel) + " s", "Fallos del intento: " + FallosNivel, "Estrellas faltantes: " + (3 - EstrellasRecolectadas));
    }

    public static List<String> CrearLineasReto(DatosReto Reto, ResultadoReto Resultado, int PuntajeFinalVisible, float TiempoNivel, int FallosNivel) {
        if (Reto == null || Resultado == null) {
            throw new IllegalArgumentException("Los datos del reto no pueden ser nulos");
        }

        return Arrays.asList("Puntaje conseguido: " + PuntajeFinalVisible, "Tiempo usado: " + Math.round(TiempoNivel) + " s", "Fallos del intento: " + FallosNivel, "Objetivo: " + Reto.ObtenerPuntajeObjetivo() + " pts / " + Reto.ObtenerEstrellasObjetivo() + " estrellas", Resultado.RetoFueSuperado() ? "Reto superado" : "Reto fallido");
    }

    public static String CrearTextoSiguiente(int NumeroNivelActual) {
        return NumeroNivelActual < FabricaNiveles.CantidadNiveles() ? "Siguiente" : "Final";
    }

    public static String CrearTextoSiguiente(DatosNivel NivelActual) {
        return NivelActual.ObtenerNumeroEnCategoria() < FabricaNiveles.CantidadNiveles(NivelActual.ObtenerCategoria()) ? "Siguiente" : "Final";
    }
}
