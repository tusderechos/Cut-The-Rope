/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.audio;

/**
 *
 * @author Hp
 */
public final class VolumenAudio {
    private VolumenAudio() {
    }

    public static float LimitarVolumen(float VolumenGeneral) {
        return Math.max(0f, Math.min(1f, VolumenGeneral));
    }

    public static float CalcularVolumenMenu(float VolumenGeneral) {
        return LimitarVolumen(VolumenGeneral);
    }

    public static float CalcularVolumenMusicaJuego(float VolumenGeneral) {
        return LimitarVolumen(VolumenGeneral);
    }

    public static float CalcularVolumenSonidosJuego(float VolumenGeneral) {
        return LimitarVolumen(VolumenGeneral);
    }
}
