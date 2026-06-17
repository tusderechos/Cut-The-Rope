/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.textos.TextosIdioma;

public final class TextoHudNivel {
    private TextoHudNivel() {
    }

    public static String CrearTexto(int NumeroNivel, int EstrellasRecolectadas, float TiempoNivel) {
        return TextosIdioma.Formatear("TextoHudCompleto", NumeroNivel, EstrellasRecolectadas, Math.round(TiempoNivel));
    }
}
