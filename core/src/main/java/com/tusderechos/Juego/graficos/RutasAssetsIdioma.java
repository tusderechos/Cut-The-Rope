/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.graficos;

/**
 *
 * @author Hp
 */

import Menus.Menu.ConfiguracionJuego;
import com.badlogic.gdx.Gdx;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import java.util.Locale;

public final class RutasAssetsIdioma {

    private RutasAssetsIdioma() {
    }

    public static String ObtenerRutaBotonDulce(ColorDulce ColorDulceSeleccionado) {
        String NombreColor = ColorDulceSeleccionado.name().toLowerCase(Locale.ROOT);
        return ObtenerRutaLocalizada("boton_dulce_" + NombreColor);
    }

    public static String ObtenerRutaBotonMonstruo(ColorMonstruo ColorMonstruoSeleccionado) {
        String NombreColor = ColorMonstruoSeleccionado.name().toLowerCase(Locale.ROOT);
        String NombreBase = "boton_monstruo_" + NombreColor;
        String Sufijo = ObtenerSufijoIdiomaActivo();
        String Carpeta = ObtenerCarpetaIdiomaActivo();

        if (!Carpeta.isEmpty()) {
            String RutaLocalizada = "imagenes/" + Carpeta + "/" + NombreBase + "_" + Sufijo + ".png";
            if (ExisteRuta(RutaLocalizada)) {
                return RutaLocalizada;
            }

            if ("fra".equals(Sufijo) && "naranja".equals(NombreColor)) {
                String RutaFrancesNaranja = "imagenes/frances/boton_monstruo_naranja_ing.png";
                if (ExisteRuta(RutaFrancesNaranja)) {
                    return RutaFrancesNaranja;
                }
            }

            if ("gar".equals(Sufijo) && "morado".equals(NombreColor)) {
                String RutaGarifunaMorado = "imagenes/garifuna/boton_monstuo_morado_gar.png";
                if (ExisteRuta(RutaGarifunaMorado)) {
                    return RutaGarifunaMorado;
                }
            }
        }

        return "imagenes/" + NombreBase + ".png";
    }

    public static String ObtenerRutaBoton(String NombreBase) {
        return ObtenerRutaLocalizada(NombreBase);
    }

    public static String ObtenerRutaFondoMenuNiveles() {
        String Sufijo = ObtenerSufijoIdiomaActivo();
        String RutaPngMayuscula = "imagenes/fondo_menu_niveles_" + Sufijo + ".PNG";
        if (ExisteRuta(RutaPngMayuscula)) {
            return RutaPngMayuscula;
        }

        String RutaPng = "imagenes/fondo_menu_niveles_" + Sufijo + ".png";
        if (ExisteRuta(RutaPng)) {
            return RutaPng;
        }

        return "imagenes/fondo_menu_niveles.png";
    }

    public static String ObtenerRutaFondoPerfilAjeno() {
        String Sufijo = ObtenerSufijoIdiomaActivo();
        String RutaPngMayuscula = "imagenes/fondo_perfil_ajeno_" + Sufijo + ".PNG";
        if (ExisteRuta(RutaPngMayuscula)) {
            return RutaPngMayuscula;
        }

        String RutaPng = "imagenes/fondo_perfil_ajeno_" + Sufijo + ".png";
        if (ExisteRuta(RutaPng)) {
            return RutaPng;
        }

        return "imagenes/fondo_perfil_ajeno_esp.png";
    }

    public static String ObtenerSufijoIdiomaActivo() {
        return ObtenerSufijoIdioma(ConfiguracionJuego.idiomaActivo);
    }

    public static String ObtenerCarpetaIdiomaActivo() {
        return ObtenerCarpetaIdioma(ConfiguracionJuego.idiomaActivo);
    }

    public static String ObtenerSufijoIdioma(String Idioma) {
        if (Idioma == null) {
            return "esp";
        }

        switch (Idioma.toUpperCase(Locale.ROOT)) {
            case "ENG":
            case "ING":
                return "ing";
            case "FRA":
            case "FR":
                return "fra";
            case "GAR":
                return "gar";
            case "HEB":
                return "heb";
            default:
                return "esp";
        }
    }

    public static String ObtenerCarpetaIdioma(String Idioma) {
        if (Idioma == null) {
            return "";
        }

        switch (Idioma.toUpperCase(Locale.ROOT)) {
            case "ENG":
            case "ING":
                return "ingles";
            case "FRA":
            case "FR":
                return "frances";
            case "GAR":
                return "garifuna";
            case "HEB":
                return "hebreo";
            default:
                return "";
        }
    }

    private static String ObtenerRutaLocalizada(String NombreBase) {
        String Sufijo = ObtenerSufijoIdiomaActivo();
        String Carpeta = ObtenerCarpetaIdiomaActivo();

        if (!Carpeta.isEmpty()) {
            String RutaLocalizada = "imagenes/" + Carpeta + "/" + NombreBase + "_" + Sufijo + ".png";
            if (ExisteRuta(RutaLocalizada)) {
                return RutaLocalizada;
            }

            String RutaLocalizadaMayuscula = "imagenes/" + Carpeta + "/" + NombreBase + "_" + Sufijo + ".PNG";
            if (ExisteRuta(RutaLocalizadaMayuscula)) {
                return RutaLocalizadaMayuscula;
            }
        }

        return "imagenes/" + NombreBase + ".png";
    }

    private static boolean ExisteRuta(String Ruta) {
        return Gdx.files != null && Gdx.files.internal(Ruta).exists();
    }
}
