package com.tusderechos.Juego.pantallas;

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
    private final Juego juego;
    private Stage stage;
    private BitmapFont fuente;
    private Texture texturaBoton;
    private Texture texturaBotonPresionado;
    private ColorDulce colorDulceActual = ColorDulce.ROJO;
    private ColorMonstruo colorMonstruoActual = ColorMonstruo.VERDE;

    public PantallaSeleccionNivel(Juego juego) { this.juego = juego; }

    public PantallaSeleccionNivel(Juego juego, ColorDulce colorDulce, ColorMonstruo colorMonstruo) {
        this.juego = juego;
        colorDulceActual = colorDulce;
        colorMonstruoActual = colorMonstruo;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        fuente = new BitmapFont();
        texturaBoton = crearTextura(Color.valueOf("30445b"));
        texturaBotonPresionado = crearTextura(Color.valueOf("1e2b3a"));
        TextButton.TextButtonStyle estiloBoton = new TextButton.TextButtonStyle();
        estiloBoton.font = fuente;
        estiloBoton.up = new TextureRegionDrawable(texturaBoton);
        estiloBoton.down = new TextureRegionDrawable(texturaBotonPresionado);
        Label.LabelStyle estiloTexto = new Label.LabelStyle(fuente, Color.WHITE);

        Table raiz = new Table();
        raiz.setFillParent(true);
        raiz.pad(28f);
        stage.addActor(raiz);
        raiz.add(new Label("Seleccion de niveles", estiloTexto)).colspan(3).padBottom(22f);
        raiz.row();
        for (int numeroNivel = 1; numeroNivel <= 5; numeroNivel++) {
            final int nivelElegido = numeroNivel;
            TextButton botonNivel = new TextButton(String.valueOf(numeroNivel), estiloBoton);
            botonNivel.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    juego.cambiarPantalla(new PantallaJuego(juego, FabricaNiveles.obtenerNivel(nivelElegido),
                        new PersonalizacionDulce(colorDulceActual), new PersonalizacionMonstruo(colorMonstruoActual)));
                }
            });
            raiz.add(botonNivel).width(92f).height(72f).pad(8f);
            if (numeroNivel % 3 == 0) raiz.row();
        }
        raiz.row();
        raiz.add(new Label("------------------------------", estiloTexto)).colspan(3).padTop(28f).padBottom(8f);
        raiz.row();
        raiz.add(new Label("Personalizacion", estiloTexto)).colspan(3).padBottom(16f);
        raiz.row();
        TextButton botonDulce = new TextButton(textoDulce(), estiloBoton);
        botonDulce.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                colorDulceActual = colorDulceActual.siguiente();
                botonDulce.setText(textoDulce());
            }
        });
        TextButton botonMonstruo = new TextButton(textoMonstruo(), estiloBoton);
        botonMonstruo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                colorMonstruoActual = colorMonstruoActual.siguiente();
                botonMonstruo.setText(textoMonstruo());
            }
        });
        raiz.add(botonDulce).colspan(3).width(260f).height(56f).pad(8f);
        raiz.row();
        raiz.add(botonMonstruo).colspan(3).width(260f).height(56f).pad(8f);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.04f, 0.05f, 0.07f, 1f);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null && width > 0 && height > 0) stage.getViewport().update(width, height, true);
    }

    private String textoDulce() { return "Dulce: " + colorDulceActual.name(); }
    private String textoMonstruo() { return "Monstruo: " + colorMonstruoActual.name(); }

    private Texture crearTextura(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture textura = new Texture(pixmap);
        pixmap.dispose();
        return textura;
    }

    @Override
    public void hide() { Gdx.input.setInputProcessor(null); }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
        if (fuente != null) fuente.dispose();
        if (texturaBoton != null) texturaBoton.dispose();
        if (texturaBotonPresionado != null) texturaBotonPresionado.dispose();
    }
}
