/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.tusderechos.Juego.audio.VolumenAudio;

/**
 *
 * @author HP
 */
public class ConfiguracionJuego {
    public static float volumenGeneral;
    public static String idiomaActivo;
    public static String modoControl;
    private static Preferences prefs;

    public static void inicializar() {
        if (prefs == null) {
            prefs = Gdx.app.getPreferences("CutTheRope_Config");
        }

        volumenGeneral = prefs.getFloat("volumen", 0.8f);
        idiomaActivo = prefs.getString("idioma", "ESP").toUpperCase();
        modoControl = prefs.getString("control", "MOUSE").toUpperCase();
    }

    private static void asegurarInicializacion() {
        if (prefs == null) {
            inicializar();
        }
    }

    public static void guardarVolumen(float nuevoVolumen) {
        asegurarInicializacion();
        volumenGeneral = VolumenAudio.LimitarVolumen(nuevoVolumen);
        prefs.putFloat("volumen", volumenGeneral);
        prefs.flush();
    }

    public static void guardarIdioma(String nuevoIdioma) {
        asegurarInicializacion();
        if (nuevoIdioma != null) {
            idiomaActivo = nuevoIdioma.toUpperCase();
            prefs.putString("idioma", idiomaActivo);
            prefs.flush();
        }
    }

    public static void guardarControl(String nuevoControl) {
        asegurarInicializacion();
        if (nuevoControl != null) {
            modoControl = nuevoControl.toUpperCase();
            prefs.putString("control", modoControl);
            prefs.flush();
        }
    }
}
