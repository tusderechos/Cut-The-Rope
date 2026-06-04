/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

public final class CalculadoraPuntaje {
    private CalculadoraPuntaje() {
    }

    public static int CalcularPuntajeIntento(int Estrellas, float Tiempo, int Fallos) {
        if (Estrellas < 0 || Estrellas > 3 || !Float.isFinite(Tiempo) || Tiempo < 0f || Fallos < 0) {
            throw new IllegalArgumentException("Los datos del intento no son validos");
        }
        long BonoEstrellas = Estrellas * 1000L;
        long BonoTiempo = Math.max(0L, 2000L - Math.round(Tiempo * 50f));
        long Puntaje = Math.max(0L, BonoEstrellas + BonoTiempo - Fallos * 150L);
        return (int) Math.min(Integer.MAX_VALUE, Puntaje);
    }
}

