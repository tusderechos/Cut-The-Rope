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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RutasAudioTest {
    @Test
    public void ObtenerRutasDebeIncluirTodosLosSonidosDelJuego() {
        List<String> Rutas = RutasAudio.ObtenerRutas();
        Set<String> RutasUnicas = new HashSet<>(Rutas);

        assertEquals(6, Rutas.size());
        assertEquals(Rutas.size(), RutasUnicas.size());
        assertTrue(Rutas.contains(RutasAudio.MusicaFondo));
        assertTrue(Rutas.contains(RutasAudio.CortarCuerda));
        assertTrue(Rutas.contains(RutasAudio.Estrella));
        assertTrue(Rutas.contains(RutasAudio.Burbuja));
        assertTrue(Rutas.contains(RutasAudio.Victoria));
        assertTrue(Rutas.contains(RutasAudio.Fallo));
    }
}
