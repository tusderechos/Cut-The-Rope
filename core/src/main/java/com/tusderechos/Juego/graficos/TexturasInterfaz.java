/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.graficos;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public final class TexturasInterfaz {
    private TexturasInterfaz() {
    }

    public static Texture CrearTexturaBoton(Color ColorFondo, Color ColorBorde, Color ColorBrillo) {
        Pixmap PixmapActual = new Pixmap(96, 64, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(0f, 0f, 0f, 0f);
        PixmapActual.fill();
        PixmapActual.setColor(ColorBorde);
        DibujarRectanguloRedondeado(PixmapActual, 0, 0, 96, 64, 14);
        PixmapActual.setColor(ColorFondo);
        DibujarRectanguloRedondeado(PixmapActual, 3, 3, 90, 58, 11);
        PixmapActual.setColor(ColorBrillo);
        DibujarRectanguloRedondeado(PixmapActual, 8, 8, 80, 14, 6);
        Texture Textura = new Texture(PixmapActual);
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        PixmapActual.dispose();

        return Textura;
    }

    private static void DibujarRectanguloRedondeado(Pixmap PixmapActual, int X, int Y, int Ancho, int Alto, int Radio) {
        PixmapActual.fillRectangle(X + Radio, Y, Ancho - Radio * 2, Alto);
        PixmapActual.fillRectangle(X, Y + Radio, Ancho, Alto - Radio * 2);
        PixmapActual.fillCircle(X + Radio, Y + Radio, Radio);
        PixmapActual.fillCircle(X + Ancho - Radio - 1, Y + Radio, Radio);
        PixmapActual.fillCircle(X + Radio, Y + Alto - Radio - 1, Radio);
        PixmapActual.fillCircle(X + Ancho - Radio - 1, Y + Alto - Radio - 1, Radio);
    }
}
