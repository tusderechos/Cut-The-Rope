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

    public static Texture CrearTexturaSolida(Color ColorFondo) {
        Pixmap PixmapActual = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorFondo);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();

        return Textura;
    }

    public static Texture CrearTexturaCircular(Color ColorFondo, int Tamano) {
        Pixmap PixmapActual = new Pixmap(Tamano, Tamano, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(0f, 0f, 0f, 0f);
        PixmapActual.fill();
        PixmapActual.setColor(ColorFondo);
        PixmapActual.fillCircle(Tamano / 2, Tamano / 2, Tamano / 2 - 1);
        Texture Textura = new Texture(PixmapActual);
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        PixmapActual.dispose();

        return Textura;
    }

    public static Texture CrearTexturaAnillo(Color ColorFondo, int Tamano, int Grosor) {
        Pixmap PixmapActual = new Pixmap(Tamano, Tamano, Pixmap.Format.RGBA8888);
        int Centro = Tamano / 2;
        int RadioExterior = Tamano / 2 - 1;
        int RadioInterior = Math.max(1, RadioExterior - Grosor);
        PixmapActual.setColor(0f, 0f, 0f, 0f);
        PixmapActual.fill();
        PixmapActual.setColor(ColorFondo);
        PixmapActual.fillCircle(Centro, Centro, RadioExterior);
        PixmapActual.setBlending(Pixmap.Blending.None);
        PixmapActual.setColor(0f, 0f, 0f, 0f);
        PixmapActual.fillCircle(Centro, Centro, RadioInterior);
        PixmapActual.setBlending(Pixmap.Blending.SourceOver);
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
