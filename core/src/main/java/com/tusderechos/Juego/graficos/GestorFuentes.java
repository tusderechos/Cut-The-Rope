/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.graficos;

/**
 *
 * @author Hp
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public final class GestorFuentes {
    private static final String RutaGoodDog = "fuentes/GOODDC__.TTF";

    private GestorFuentes() {
    }

    public static BitmapFont CrearFuenteGoodDog(int Tamano) {
        FreeTypeFontGenerator Generador = null;
        try {
            Generador = new FreeTypeFontGenerator(Gdx.files.internal(RutaGoodDog));
            FreeTypeFontGenerator.FreeTypeFontParameter Parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();
            Parametros.size = Tamano;
            Parametros.color = Color.WHITE;
            Parametros.borderWidth = 1f;
            Parametros.borderColor = new Color(0f, 0f, 0f, 0.55f);
            return Generador.generateFont(Parametros);
        } catch (RuntimeException Excepcion) {
            if (Gdx.app != null) {
                Gdx.app.error("GestorFuentes", "No se pudo cargar Good Dog", Excepcion);
            }
            return new BitmapFont();
        } finally {
            if (Generador != null) {
                Generador.dispose();
            }
        }
    }
}
