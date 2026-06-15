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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
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
import com.tusderechos.Juego.progreso.ProgresoNiveles;
import Menus.Menu.MainMenuScreen;
import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;

public class PantallaSeleccionNivel extends ScreenAdapter {
    private final Juego JuegoAplicacion;
    private Stage StageActual;
    private BitmapFont Fuente;
    private Texture TexturaBoton;
    private Texture TexturaBotonPresionado;
    private Texture TexturaBotonNivel;
    private Texture TexturaBotonNivelPresionado;
    private Texture TexturaBloqueoNivel;
    private Texture TexturaOverlayBloqueado;
    private final List<Texture> TexturasFondosNivel = new ArrayList<>();
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
        TexturaBloqueoNivel = CargarTextura(RutasTexturas.BloqueoNivel);
        TexturaOverlayBloqueado = TexturasInterfaz.CrearTexturaSolida(new Color(0.18f, 0.18f, 0.18f, 0.64f));
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
        Raiz.top();
        Raiz.pad(12f);
        StageActual.addActor(Raiz);
        ConstruirContenido();
        Gdx.input.setInputProcessor(StageActual);
    }

    private void ConstruirContenido() {
        Raiz.clearChildren();
        AgregarBarraSuperior();
        Raiz.row();
        Table TablaNiveles = new Table();
        for (int NumeroNivel = 1; NumeroNivel <= ObtenerCantidadNivelesMostrados(); NumeroNivel++) {
            TablaNiveles.add(CrearTarjetaNivel(NumeroNivel)).width(124f).height(128f).pad(6f);
            if (NumeroNivel % 3 == 0) {
                TablaNiveles.row();
            }
        }
        Raiz.add(TablaNiveles).width(420f).padTop(12f);
        Raiz.row();
        Raiz.add(new Image(TexturaBotonPresionado)).width(360f).height(2f).padTop(18f).padBottom(6f);
        Raiz.row();
        Raiz.add(new Label("Personalizacion", EstiloTexto)).padBottom(6f);
        Raiz.row();
        Table TablaPersonalizacion = new Table();
        if (TexturaDulcePreview == null) {
            TexturaDulcePreview = CargarTextura(RutasTexturas.ObtenerDulce(ColorDulceActual));
        }
        if (TexturaMonstruoPreview == null) {
            TexturaMonstruoPreview = CargarTextura(RutasTexturas.ObtenerMonstruo(ColorMonstruoActual));
        }
        ImagenDulcePreview = new Image(TexturaDulcePreview);
        ImagenMonstruoPreview = new Image(TexturaMonstruoPreview);
        TablaPersonalizacion.add(ImagenDulcePreview).width(82f).height(82f).pad(4f);
        TablaPersonalizacion.add().width(88f);
        TablaPersonalizacion.add(ImagenMonstruoPreview).width(92f).height(92f).pad(4f);
        Raiz.add(TablaPersonalizacion).padBottom(4f);
        Raiz.row();
        TextButton BotonDulce = new TextButton(TextoDulce(), EstiloBoton);
        CentrarTextoBoton(BotonDulce);
        BotonDulce.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorDulceActual = ColorDulceActual.Siguiente();
                BotonDulce.setText(TextoDulce());
                ActualizarPreviewDulce();
            }
        });
        TextButton BotonMonstruo = new TextButton(TextoMonstruo(), EstiloBoton);
        CentrarTextoBoton(BotonMonstruo);
        BotonMonstruo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorMonstruoActual = ColorMonstruoActual.Siguiente();
                BotonMonstruo.setText(TextoMonstruo());
                ActualizarPreviewMonstruo();
            }
        });
        Raiz.add(BotonMonstruo).width(260f).height(50f).padTop(2f).padBottom(6f);
        Raiz.row();
        Raiz.add(BotonDulce).width(260f).height(50f).padBottom(6f);
        Raiz.row();
        TextButton BotonMenuPrincipal = new TextButton("Menu principal", EstiloBoton);
        CentrarTextoBoton(BotonMenuPrincipal);
        BotonMenuPrincipal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                JuegoAplicacion.CambiarPantalla(new MainMenuScreen(JuegoAplicacion));
            }
        });
        Raiz.add(BotonMenuPrincipal).right().width(170f).height(48f).padTop(2f);
    }

    private void AgregarBarraSuperior() {
        Table BarraSuperior = new Table();
        BarraSuperior.setBackground(new TextureRegionDrawable(TexturaBotonPresionado));
        Label Titulo = new Label("Seleccion de niveles", EstiloTexto);
        Titulo.setAlignment(Align.left);
        BarraSuperior.add(Titulo).expandX().left().padLeft(8f);
        BarraSuperior.add(CrearPerfilPlaceholder()).width(166f).height(44f).right().padRight(4f);
        Raiz.add(BarraSuperior).width(440f).height(48f);
    }

    private Table CrearPerfilPlaceholder() {
        Table Perfil = new Table();
        Usuario UsuarioActual = SistemaAutenticacion.getUsuarioActivo();
        String NombreUsuario = UsuarioActual == null ? "Invitado" : UsuarioActual.getUsername();
        Image Avatar = new Image(TexturaBotonNivelPresionado);
        Label Nombre = new Label(NombreUsuario, EstiloTexto);
        Nombre.setAlignment(Align.left);
        Perfil.add(Avatar).width(28f).height(28f).padRight(8f);
        Perfil.add(Nombre).expandX().left();

        return Perfil;
    }

    private Stack CrearTarjetaNivel(final int NumeroNivel) {
        boolean NivelDesbloqueado = ProgresoNiveles.NivelEstaDesbloqueado(SistemaAutenticacion.getUsuarioActivo(), NumeroNivel);
        Stack Tarjeta = new Stack();
        Image FondoNivel = new Image(CargarFondoNivel(NumeroNivel));
        FondoNivel.setScaling(Scaling.fill);
        Tarjeta.add(FondoNivel);
        Label Numero = new Label(String.valueOf(NumeroNivel), EstiloTexto);
        Numero.setAlignment(Align.center);
        Tarjeta.add(Numero);
        if (!NivelDesbloqueado) {
            Image Overlay = new Image(TexturaOverlayBloqueado);
            Overlay.setScaling(Scaling.fill);
            Image Candado = new Image(TexturaBloqueoNivel);
            Candado.setScaling(Scaling.fit);
            Table ContenedorCandado = new Table();
            ContenedorCandado.add(Candado).width(46f).height(46f);
            Tarjeta.add(Overlay);
            Tarjeta.add(ContenedorCandado);
            return Tarjeta;
        }
        Tarjeta.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent Event, float X, float Y) {
                JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, FabricaNiveles.ObtenerNivel(CategoriaActual, NumeroNivel), new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual)));
            }
        });

        return Tarjeta;
    }

    private Texture CargarFondoNivel(int NumeroNivel) {
        Texture Textura = CargarTextura(RutasTexturas.ObtenerFondoNivel(NumeroNivel));
        TexturasFondosNivel.add(Textura);

        return Textura;
    }

    private void CentrarTextoBoton(TextButton Boton) {
        Boton.getLabel().setAlignment(Align.center);
        Boton.getLabelCell().center();
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
        if (TexturaBloqueoNivel != null) {
            TexturaBloqueoNivel.dispose();
        }
        if (TexturaOverlayBloqueado != null) {
            TexturaOverlayBloqueado.dispose();
        }
        for (Texture TexturaFondoNivel : TexturasFondosNivel) {
            if (TexturaFondoNivel != null) {
                TexturaFondoNivel.dispose();
            }
        }
        TexturasFondosNivel.clear();
        if (TexturaDulcePreview != null) {
            TexturaDulcePreview.dispose();
        }
        if (TexturaMonstruoPreview != null) {
            TexturaMonstruoPreview.dispose();
        }
    }
}

