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
import java.util.Arrays;
import java.util.List;

public final class TextoPanelResultado {
    private TextoPanelResultado() {
    }

    public static List<String> CrearLineas(int EstrellasRecolectadas, int PuntajeFinal, float TiempoNivel, int FallosNivel) {
        return Arrays.asList("Puntaje conseguido: " + PuntajeFinal, "Tiempo usado: " + Math.round(TiempoNivel) + " s", "Fallos del intento: " + FallosNivel, "Estrellas faltantes: " + (3 - EstrellasRecolectadas));
    }

    public static String CrearTextoSiguiente(int NumeroNivelActual) {
        return NumeroNivelActual < FabricaNiveles.CantidadNiveles() ? "Siguiente" : "Final";
    }

    public static String CrearTextoSiguiente(DatosNivel NivelActual) {
        return NivelActual.ObtenerNumeroEnCategoria() < FabricaNiveles.CantidadNiveles(NivelActual.ObtenerCategoria()) ? "Siguiente" : "Final";
    }
}
