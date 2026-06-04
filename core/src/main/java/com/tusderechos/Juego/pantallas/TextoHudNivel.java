/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
public final class TextoHudNivel {
    private TextoHudNivel() {
    }

    public static String CrearTexto(int NumeroNivel, int EstrellasRecolectadas, float TiempoNivel) {
        return "Nivel " + NumeroNivel + "   Estrellas " + EstrellasRecolectadas + "/3   Tiempo " + Math.round(TiempoNivel) + " s";
    }
}
