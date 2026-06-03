/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;

public class PantallaSeleccionNivel extends ScreenAdapter {
    private final Juego JuegoAplicacion;
    private Stage StageActual;
    private BitmapFont Fuente;
    private Texture TexturaBoton;
    private Texture TexturaBotonPresionado;
    private ColorDulce ColorDulceActual = ColorDulce.Rojo;
    private ColorMonstruo ColorMonstruoActual = ColorMonstruo.Verde;

    public PantallaSeleccionNivel(Juego JuegoAplicacion) {
        this.JuegoAplicacion = JuegoAplicacion;
    }

    public PantallaSeleccionNivel(Juego JuegoAplicacion, ColorDulce ColorDulceElegido, ColorMonstruo ColorMonstruoElegido) {
        this.JuegoAplicacion = JuegoAplicacion;
        ColorDulceActual = ColorDulceElegido;
        ColorMonstruoActual = ColorMonstruoElegido;
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Fuente = new BitmapFont();
        TexturaBoton = CrearTextura(Color.valueOf("30445b"));
        TexturaBotonPresionado = CrearTextura(Color.valueOf("1e2b3a"));
        TextButton.TextButtonStyle EstiloBoton = new TextButton.TextButtonStyle();
        EstiloBoton.font = Fuente;
        EstiloBoton.up = new TextureRegionDrawable(TexturaBoton);
        EstiloBoton.down = new TextureRegionDrawable(TexturaBotonPresionado);
        Label.LabelStyle EstiloTexto = new Label.LabelStyle(Fuente, Color.WHITE);

        Table Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.pad(28f);
        StageActual.addActor(Raiz);
        Raiz.add(new Label("Seleccion de niveles", EstiloTexto)).colspan(3).padBottom(22f);
        Raiz.row();
        for (int NumeroNivel = 1; NumeroNivel <= FabricaNiveles.CantidadNiveles(); NumeroNivel++) {
            final int NivelElegido = NumeroNivel;
            TextButton BotonNivel = new TextButton(String.valueOf(NumeroNivel), EstiloBoton);
            BotonNivel.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent Event, Actor ActorActual) {
                    JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, FabricaNiveles.ObtenerNivel(NivelElegido), new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual)));
                }
            });
            Raiz.add(BotonNivel).width(92f).height(72f).pad(8f);
            if (NumeroNivel % 3 == 0) {
                Raiz.row();
            }
        }
        Raiz.row();
        Raiz.add(new Label("------------------------------", EstiloTexto)).colspan(3).padTop(28f).padBottom(8f);
        Raiz.row();
        Raiz.add(new Label("Personalizacion", EstiloTexto)).colspan(3).padBottom(16f);
        Raiz.row();
        TextButton BotonDulce = new TextButton(TextoDulce(), EstiloBoton);
        BotonDulce.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorDulceActual = ColorDulceActual.Siguiente();
                BotonDulce.setText(TextoDulce());
            }
        });
        TextButton BotonMonstruo = new TextButton(TextoMonstruo(), EstiloBoton);
        BotonMonstruo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorMonstruoActual = ColorMonstruoActual.Siguiente();
                BotonMonstruo.setText(TextoMonstruo());
            }
        });
        Raiz.add(BotonDulce).colspan(3).width(260f).height(56f).pad(8f);
        Raiz.row();
        Raiz.add(BotonMonstruo).colspan(3).width(260f).height(56f).pad(8f);
        Gdx.input.setInputProcessor(StageActual);
    }

    @Override
    public void render(float Delta) {
        ScreenUtils.clear(0.04f, 0.05f, 0.07f, 1f);
        StageActual.act(Delta);
        StageActual.draw();
    }

    @Override
    public void resize(int Width, int Height) {
        if (StageActual != null && Width > 0 && Height > 0) {
            StageActual.getViewport().update(Width, Height, true);
        }
    }

    private String TextoDulce() {
        return "Dulce: " + ColorDulceActual.name();
    }
    private String TextoMonstruo() {
        return "Monstruo: " + ColorMonstruoActual.name();
    }

    private Texture CrearTextura(Color ColorActual) {
        Pixmap PixmapActual = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorActual);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();
        return Textura;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (StageActual != null) {
            StageActual.dispose();
        }
        if (Fuente != null) {
            Fuente.dispose();
        }
        if (TexturaBoton != null) {
            TexturaBoton.dispose();
        }
        if (TexturaBotonPresionado != null) {
            TexturaBotonPresionado.dispose();
        }
    }
}

