/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.niveles.FabricaNiveles;
import java.util.Arrays;
import java.util.List;

public final class TextoPanelResultado {
    private TextoPanelResultado() {
    }

    public static List<String> CrearLineas(int EstrellasRecolectadas, int PuntajeFinal, int PuntajeAcumulado, float TiempoNivel, int FallosNivel) {
        return Arrays.asList("Estrellas: " + EstrellasRecolectadas + "/3", "Faltaron: " + (3 - EstrellasRecolectadas), "Puntaje: " + PuntajeFinal, "Total ranking: " + PuntajeAcumulado, "Tiempo: " + Math.round(TiempoNivel) + " s", "Fallos: " + FallosNivel);
    }

    public static String CrearTextoSiguiente(int NumeroNivelActual) {
        return NumeroNivelActual < FabricaNiveles.CantidadNiveles() ? "Siguiente" : "Final";
    }
}
