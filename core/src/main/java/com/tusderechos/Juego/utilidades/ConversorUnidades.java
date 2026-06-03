/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

public final class ConversorUnidades {
    private ConversorUnidades() {
    }

    public static float PixelesAMetros(float Pixeles) {
        return Pixeles / ConstantesJuego.PixelesPorMetro;
    }

    public static float MetrosAPixeles(float Metros) {
        return Metros * ConstantesJuego.PixelesPorMetro;
    }
}

