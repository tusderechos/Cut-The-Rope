/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tusderechos.Juego.audio.VolumenAudio;

/**
 *
 * @author HP
 */
public class AudioManager {
    private static AudioManager instancia;
    private Music musicaMenu;
    private float volumenActual = VolumenAudio.CalcularVolumenMenu(ConfiguracionJuego.volumenGeneral);

    private AudioManager() {
        try {
            musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("audioMenu/musica_menu.mp3"));
            musicaMenu.setLooping(true); 
        } catch (Exception e) {
            Gdx.app.log("AudioManager", "Error cargando la música desde audioMenu: " + e.getMessage());
        }
    }

    public static AudioManager getInstancia() {
        if (instancia == null) {
            instancia = new AudioManager();
        }
        return instancia;
    }

    public void reproducirMusicaMenu() {
        if (musicaMenu != null && !musicaMenu.isPlaying()) {
            volumenActual = VolumenAudio.CalcularVolumenMenu(ConfiguracionJuego.volumenGeneral);
            musicaMenu.setVolume(volumenActual);
            musicaMenu.play();
        }
    }

    public void detenerMusicaMenu() {
        if (musicaMenu != null && musicaMenu.isPlaying()) {
            musicaMenu.stop();
        }
    }

    public float getVolumen() {
        return volumenActual;
    }

    public void actualizarVolumen(float nuevoVolumen) {
        ConfiguracionJuego.guardarVolumen(nuevoVolumen);
        this.volumenActual = VolumenAudio.CalcularVolumenMenu(ConfiguracionJuego.volumenGeneral);
        if (musicaMenu != null) {
            musicaMenu.setVolume(volumenActual);
        }
    }

    public void dispose() {
        if (musicaMenu != null) {
            musicaMenu.dispose();
        }
    }
}
