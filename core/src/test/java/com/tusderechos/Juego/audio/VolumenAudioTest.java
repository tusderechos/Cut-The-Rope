/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.audio;

/**
 *
 * @author Hp
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VolumenAudioTest {
    @Test
    void LimitaVolumenGlobalEntreCeroYUno() {
        assertEquals(0f, VolumenAudio.LimitarVolumen(-0.5f), 0.0001f);
        assertEquals(0.45f, VolumenAudio.LimitarVolumen(0.45f), 0.0001f);
        assertEquals(1f, VolumenAudio.LimitarVolumen(1.4f), 0.0001f);
    }

    @Test
    void CalculaVolumenDelMenuDesdeConfiguracionGlobal() {
        assertEquals(0.75f, VolumenAudio.CalcularVolumenMenu(0.75f), 0.0001f);
    }

    @Test
    void CalculaVolumenDelJuegoIgualQueElMenu() {
        assertEquals(0.5f, VolumenAudio.CalcularVolumenMusicaJuego(0.5f), 0.0001f);
        assertEquals(0.5f, VolumenAudio.CalcularVolumenSonidosJuego(0.5f), 0.0001f);
    }

    @Test
    void VolumenCeroSilenciaMusicaYSonidosDelJuego() {
        assertEquals(0f, VolumenAudio.CalcularVolumenMusicaJuego(0f), 0.0001f);
        assertEquals(0f, VolumenAudio.CalcularVolumenSonidosJuego(0f), 0.0001f);
    }
}
