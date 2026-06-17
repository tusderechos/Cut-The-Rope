/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 *
 * @author HP
 */
public class AudioManager {
    private static AudioManager instancia;
    private Music musicaMenu;
    private float volumenActual = 0.5f; 

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
        this.volumenActual = Math.max(0.0f, Math.min(1.0f, nuevoVolumen));
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
