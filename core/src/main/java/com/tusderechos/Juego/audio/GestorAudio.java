/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.audio;

/**
 *
 * @author Hp
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public final class GestorAudio implements Disposable {
    private static final float VolumenMusica = 0.22f;
    private static final float VolumenSonidos = 0.62f;
    private final Music MusicaFondo;
    private final Sound SonidoCortarCuerda;
    private final Sound SonidoEstrella;
    private final Sound SonidoBurbuja;
    private final Sound SonidoVictoria;
    private final Sound SonidoFallo;

    public GestorAudio() {
        MusicaFondo = CargarMusica(RutasAudio.MusicaFondo);
        SonidoCortarCuerda = CargarSonido(RutasAudio.CortarCuerda);
        SonidoEstrella = CargarSonido(RutasAudio.Estrella);
        SonidoBurbuja = CargarSonido(RutasAudio.Burbuja);
        SonidoVictoria = CargarSonido(RutasAudio.Victoria);
        SonidoFallo = CargarSonido(RutasAudio.Fallo);
    }

    public void IniciarMusica() {
        if (MusicaFondo == null) {
            return;
        }
        MusicaFondo.setLooping(true);
        MusicaFondo.setVolume(VolumenMusica);
        MusicaFondo.play();
    }

    public void ReproducirCorteCuerda() {
        ReproducirSonido(SonidoCortarCuerda);
    }

    public void ReproducirEstrella() {
        ReproducirSonido(SonidoEstrella);
    }

    public void ReproducirBurbuja() {
        ReproducirSonido(SonidoBurbuja);
    }

    public void ReproducirVictoria() {
        ReproducirSonido(SonidoVictoria);
    }

    public void ReproducirFallo() {
        ReproducirSonido(SonidoFallo);
    }

    private Music CargarMusica(String Ruta) {
        try {
            return Gdx.audio.newMusic(Gdx.files.internal(Ruta));
        } catch (RuntimeException Excepcion) {
            return null;
        }
    }

    private Sound CargarSonido(String Ruta) {
        try {
            return Gdx.audio.newSound(Gdx.files.internal(Ruta));
        } catch (RuntimeException Excepcion) {
            return null;
        }
    }

    private void ReproducirSonido(Sound Sonido) {
        if (Sonido == null) {
            return;
        }
        Sonido.play(VolumenSonidos);
    }

    @Override
    public void dispose() {
        if (MusicaFondo != null) {
            MusicaFondo.dispose();
        }
        if (SonidoCortarCuerda != null) {
            SonidoCortarCuerda.dispose();
        }
        if (SonidoEstrella != null) {
            SonidoEstrella.dispose();
        }
        if (SonidoBurbuja != null) {
            SonidoBurbuja.dispose();
        }
        if (SonidoVictoria != null) {
            SonidoVictoria.dispose();
        }
        if (SonidoFallo != null) {
            SonidoFallo.dispose();
        }
    }
}
