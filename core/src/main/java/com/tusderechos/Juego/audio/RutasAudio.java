/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.audio;

/**
 *
 * @author Hp
 */
import java.util.Arrays;
import java.util.List;

public final class RutasAudio {
    public static final String MusicaFondo = "audio/musica_fondo.wav";
    public static final String CortarCuerda = "audio/cortar_cuerda.wav";
    public static final String Estrella = "audio/estrella.wav";
    public static final String Burbuja = "audio/burbuja.wav";
    public static final String Victoria = "audio/victoria.wav";
    public static final String Fallo = "audio/fallo.wav";

    private RutasAudio() {
    }

    public static List<String> ObtenerRutas() {
        return Arrays.asList(MusicaFondo, CortarCuerda, Estrella, Burbuja, Victoria, Fallo);
    }
}
