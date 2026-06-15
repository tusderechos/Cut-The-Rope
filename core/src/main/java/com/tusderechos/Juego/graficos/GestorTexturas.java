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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import java.util.HashMap;
import java.util.Map;

public final class GestorTexturas implements Disposable {
    private final Map<String, Texture> Texturas = new HashMap<>();

    public GestorTexturas() {
        for (String Ruta : RutasTexturas.ObtenerRutas()) {
            Texturas.put(Ruta, CargarTextura(Ruta));
        }
    }

    public Texture ObtenerDulce(ColorDulce ColorDulceActual) {
        return Texturas.get(RutasTexturas.ObtenerDulce(ColorDulceActual));
    }

    public Texture ObtenerMonstruo(ColorMonstruo ColorMonstruoActual) {
        return Texturas.get(RutasTexturas.ObtenerMonstruo(ColorMonstruoActual));
    }

    public Texture ObtenerEstrella(boolean Recolectada) {
        if (Recolectada) {
            return Texturas.get(RutasTexturas.EstrellaVacia);
        }
        return Texturas.get(RutasTexturas.Estrella);
    }

    public Texture ObtenerFondoNivel(int NumeroNivel) {
        return Texturas.get(RutasTexturas.ObtenerFondoNivel(NumeroNivel));
    }

    public Texture ObtenerBotonSalir() {
        return Texturas.get(RutasTexturas.BotonSalir);
    }

    public Texture ObtenerBotonSiguiente() {
        return Texturas.get(RutasTexturas.BotonSiguiente);
    }

    public Texture ObtenerBotonVolver() {
        return Texturas.get(RutasTexturas.BotonVolver);
    }

    public Texture ObtenerBotonRetos() {
        return Texturas.get(RutasTexturas.BotonRetos);
    }

    private Texture CargarTextura(String Ruta) {
        try {
            Texture Textura = new Texture(Gdx.files.internal(Ruta));
            Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            return Textura;
        } catch (RuntimeException Excepcion) {
            if (Gdx.app != null) {
                Gdx.app.error("GestorTexturas", "No se pudo cargar la textura: " + Ruta, Excepcion);
            }
            return null;
        }
    }

    @Override
    public void dispose() {
        for (Texture Textura : Texturas.values()) {
            if (Textura != null) {
                Textura.dispose();
            }
        }
        Texturas.clear();
    }
}
