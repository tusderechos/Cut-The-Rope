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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.graficos.GestorFuentes;
import com.tusderechos.Juego.graficos.RutasTexturas;
import com.tusderechos.Juego.graficos.TexturasInterfaz;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;

public class PantallaSeleccionNivel extends ScreenAdapter {
    private final Juego JuegoAplicacion;
    private Stage StageActual;
    private BitmapFont Fuente;
    private Texture TexturaBoton;
    private Texture TexturaBotonPresionado;
    private Texture TexturaBotonNivel;
    private Texture TexturaBotonNivelPresionado;
    private Texture TexturaDulcePreview;
    private Texture TexturaMonstruoPreview;
    private Image ImagenDulcePreview;
    private Image ImagenMonstruoPreview;
    private Table Raiz;
    private TextButton.TextButtonStyle EstiloBoton;
    private TextButton.TextButtonStyle EstiloBotonNivel;
    private Label.LabelStyle EstiloTexto;
    private ColorDulce ColorDulceActual = ColorDulce.Rojo;
    private ColorMonstruo ColorMonstruoActual = ColorMonstruo.Verde;
    private final CategoriaDificultad CategoriaActual = CategoriaDificultad.Facil;

    public PantallaSeleccionNivel(Juego JuegoAplicacion) {
        this.JuegoAplicacion = JuegoAplicacion;
    }

    public PantallaSeleccionNivel(Juego JuegoAplicacion, ColorDulce ColorDulceElegido, ColorMonstruo ColorMonstruoElegido) {
        this(JuegoAplicacion, ColorDulceElegido, ColorMonstruoElegido, CategoriaDificultad.Facil);
    }

    public PantallaSeleccionNivel(Juego JuegoAplicacion, ColorDulce ColorDulceElegido, ColorMonstruo ColorMonstruoElegido, CategoriaDificultad CategoriaElegida) {
        this.JuegoAplicacion = JuegoAplicacion;
        ColorDulceActual = ColorDulceElegido;
        ColorMonstruoActual = ColorMonstruoElegido;
    }

    public CategoriaDificultad ObtenerCategoriaActual() {
        return CategoriaActual;
    }

    public int ObtenerCantidadNivelesMostrados() {
        return FabricaNiveles.CantidadNiveles(CategoriaActual);
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Fuente = GestorFuentes.CrearFuenteGoodDog(36);
        TexturaBoton = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("2f9f91"), Color.valueOf("9ff5df"), Color.valueOf("43c7b6"));
        TexturaBotonPresionado = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("21766d"), Color.valueOf("c6fff0"), Color.valueOf("2fa394"));
        TexturaBotonNivel = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("f0a13a"), Color.valueOf("ffe0a2"), Color.valueOf("ffc65a"));
        TexturaBotonNivelPresionado = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("c87425"), Color.valueOf("fff0c9"), Color.valueOf("e99a36"));
        EstiloBoton = new TextButton.TextButtonStyle();
        EstiloBoton.font = Fuente;
        EstiloBoton.fontColor = Color.WHITE;
        EstiloBoton.downFontColor = Color.WHITE;
        EstiloBoton.up = new TextureRegionDrawable(TexturaBoton);
        EstiloBoton.down = new TextureRegionDrawable(TexturaBotonPresionado);
        EstiloBotonNivel = new TextButton.TextButtonStyle();
        EstiloBotonNivel.font = Fuente;
        EstiloBotonNivel.fontColor = Color.WHITE;
        EstiloBotonNivel.downFontColor = Color.WHITE;
        EstiloBotonNivel.up = new TextureRegionDrawable(TexturaBotonNivel);
        EstiloBotonNivel.down = new TextureRegionDrawable(TexturaBotonNivelPresionado);
        EstiloTexto = new Label.LabelStyle(Fuente, Color.WHITE);

        Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.pad(28f);
        StageActual.addActor(Raiz);
        ConstruirContenido();
        Gdx.input.setInputProcessor(StageActual);
    }

    private void ConstruirContenido() {
        Raiz.clearChildren();
        Raiz.add(new Label("Seleccion de niveles", EstiloTexto)).colspan(3).padBottom(22f);
        Raiz.row();
        for (int NumeroNivel = 1; NumeroNivel <= ObtenerCantidadNivelesMostrados(); NumeroNivel++) {
            final int NivelElegido = NumeroNivel;
            TextButton BotonNivel = new TextButton(String.valueOf(NumeroNivel), EstiloBotonNivel);
            BotonNivel.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent Event, Actor ActorActual) {
                    JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, FabricaNiveles.ObtenerNivel(CategoriaActual, NivelElegido), new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual)));
                }
            });
            Raiz.add(BotonNivel).width(112f).height(84f).pad(9f);
            if (NumeroNivel % 3 == 0) {
                Raiz.row();
            }
        }
        Raiz.row();
        TextButton BotonRivalidad = new TextButton("Rivalidad", EstiloBoton);
        BotonRivalidad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                JuegoAplicacion.CambiarPantalla(new PantallaRivalidad(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual));
            }
        });
        Raiz.add(BotonRivalidad).colspan(3).width(260f).height(58f).padTop(16f).padBottom(10f);
        Raiz.row();
        Raiz.add(new Image(TexturaBotonPresionado)).colspan(3).width(320f).height(2f).padTop(28f).padBottom(8f);
        Raiz.row();
        Raiz.add(new Label("Personalizacion", EstiloTexto)).colspan(3).padBottom(10f);
        Raiz.row();
        if (TexturaDulcePreview == null) {
            TexturaDulcePreview = CargarTextura(RutasTexturas.ObtenerDulce(ColorDulceActual));
        }
        if (TexturaMonstruoPreview == null) {
            TexturaMonstruoPreview = CargarTextura(RutasTexturas.ObtenerMonstruo(ColorMonstruoActual));
        }
        ImagenDulcePreview = new Image(TexturaDulcePreview);
        ImagenMonstruoPreview = new Image(TexturaMonstruoPreview);
        Raiz.add(ImagenDulcePreview).width(112f).height(112f).pad(6f);
        Raiz.add().width(36f);
        Raiz.add(ImagenMonstruoPreview).width(128f).height(128f).pad(6f);
        Raiz.row();
        TextButton BotonDulce = new TextButton(TextoDulce(), EstiloBoton);
        BotonDulce.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorDulceActual = ColorDulceActual.Siguiente();
                BotonDulce.setText(TextoDulce());
                ActualizarPreviewDulce();
            }
        });
        TextButton BotonMonstruo = new TextButton(TextoMonstruo(), EstiloBoton);
        BotonMonstruo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorMonstruoActual = ColorMonstruoActual.Siguiente();
                BotonMonstruo.setText(TextoMonstruo());
                ActualizarPreviewMonstruo();
            }
        });
        Raiz.add(BotonDulce).colspan(3).width(330f).height(62f).padTop(8f).padBottom(8f);
        Raiz.row();
        Raiz.add(BotonMonstruo).colspan(3).width(330f).height(62f).pad(8f);
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

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return Textura;
    }

    private void ActualizarPreviewDulce() {
        if (TexturaDulcePreview != null) {
            TexturaDulcePreview.dispose();
        }
        TexturaDulcePreview = CargarTextura(RutasTexturas.ObtenerDulce(ColorDulceActual));
        ImagenDulcePreview.setDrawable(new TextureRegionDrawable(TexturaDulcePreview));
    }

    private void ActualizarPreviewMonstruo() {
        if (TexturaMonstruoPreview != null) {
            TexturaMonstruoPreview.dispose();
        }
        TexturaMonstruoPreview = CargarTextura(RutasTexturas.ObtenerMonstruo(ColorMonstruoActual));
        ImagenMonstruoPreview.setDrawable(new TextureRegionDrawable(TexturaMonstruoPreview));
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
        if (TexturaBotonNivel != null) {
            TexturaBotonNivel.dispose();
        }
        if (TexturaBotonNivelPresionado != null) {
            TexturaBotonNivelPresionado.dispose();
        }
        if (TexturaDulcePreview != null) {
            TexturaDulcePreview.dispose();
        }
        if (TexturaMonstruoPreview != null) {
            TexturaMonstruoPreview.dispose();
        }
    }
}

