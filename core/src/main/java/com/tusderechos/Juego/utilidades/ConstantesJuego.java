/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.utilidades;

/**
 *
 * @author Hp
 */

public final class ConstantesJuego {
    public static final int AnchoVentana = 480;
    public static final int AltoVentana = 800;
    public static final int AnchoVentanaEscritorio = AnchoVentana;
    public static final int AltoVentanaEscritorio = AltoVentana;
    public static final float PixelesPorMetro = 100f;
    public static final float AnchoMundo = AnchoVentana / PixelesPorMetro;
    public static final float AltoMundo = AltoVentana / PixelesPorMetro;
    public static final float Gravedad = -9.8f;
    public static final float RadioDulce = 0.18f;
    public static final float RadioMonstruo = 0.38f;
    public static final float RadioEstrella = 0.13f;
    public static final float MargenCorteCuerda = 0.16f;

    private ConstantesJuego() {
    }
}

