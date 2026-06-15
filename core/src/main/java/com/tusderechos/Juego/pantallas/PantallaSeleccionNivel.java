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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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
import java.util.Locale;

public class PantallaSeleccionNivel extends ScreenAdapter {
    private final Juego JuegoAplicacion;
    private Stage StageActual;
    private BitmapFont Fuente;
    private Texture TexturaBotonPresionado;
    private Texture TexturaBloqueoNivel;
    private Texture TexturaOverlayBloqueado;
    private Texture TexturaMarcoNivel;
    private Texture TexturaBotonDulce;
    private Texture TexturaBotonMonstruo;
    private Texture TexturaBotonMenuPrincipal;
    private Texture TexturaTituloSeleccionNiveles;
    private Texture TexturaAvatarPerfil;
    private final List<Texture> TexturasFondosNivel = new ArrayList<>();
    private Texture TexturaDulcePreview;
    private Texture TexturaMonstruoPreview;
    private Image ImagenDulcePreview;
    private Image ImagenMonstruoPreview;
    private Table Raiz;
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
        TexturaBotonPresionado = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("21766d"), Color.valueOf("c6fff0"), Color.valueOf("2fa394"));
        TexturaBloqueoNivel = CargarTextura(RutasTexturas.BloqueoNivel);
        TexturaOverlayBloqueado = TexturasInterfaz.CrearTexturaSolida(new Color(0.18f, 0.18f, 0.18f, 0.64f));
        TexturaMarcoNivel = TexturasInterfaz.CrearTexturaSolida(new Color(1f, 0.78f, 0.38f, 1f));
        TexturaBotonDulce = CargarTextura(ObtenerRutaBotonDulce(ColorDulceActual));
        TexturaBotonMonstruo = CargarTextura(ObtenerRutaBotonMonstruo(ColorMonstruoActual));
        TexturaBotonMenuPrincipal = CargarTextura(ObtenerRutaBotonMenuPrincipal());
        TexturaTituloSeleccionNiveles = CargarTextura(ObtenerRutaTituloSeleccionNiveles());
        EstiloTexto = new Label.LabelStyle(Fuente, Color.WHITE);

        Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.center();
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
            TablaNiveles.add(CrearTarjetaNivel(NumeroNivel)).width(124f).height(128f).pad(10f);
            if (NumeroNivel % 3 == 0) {
                TablaNiveles.row();
            }
        }
        Raiz.add(TablaNiveles).width(440f).height(300f).padTop(18f).padBottom(10f);
        Raiz.row();
        Raiz.add(new Image(TexturaBotonPresionado)).width(360f).height(2f).padTop(4f).padBottom(6f);
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
        TablaPersonalizacion.add(ImagenDulcePreview).width(72f).height(72f).pad(4f);
        TablaPersonalizacion.add().width(100f);
        TablaPersonalizacion.add(ImagenMonstruoPreview).width(82f).height(82f).pad(4f);
        Raiz.add(TablaPersonalizacion).padTop(2f).padBottom(8f);
        Raiz.row();
        ImageButton BotonDulce = new ImageButton(CrearEstiloBotonImagen(TexturaBotonDulce));
        BotonDulce.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorDulceActual = ColorDulceActual.Siguiente();
                ActualizarBotonDulce(BotonDulce);
                ActualizarPreviewDulce();
            }
        });
        ImageButton BotonMonstruo = new ImageButton(CrearEstiloBotonImagen(TexturaBotonMonstruo));
        BotonMonstruo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                ColorMonstruoActual = ColorMonstruoActual.Siguiente();
                ActualizarBotonMonstruo(BotonMonstruo);
                ActualizarPreviewMonstruo();
            }
        });
        Raiz.add(BotonMonstruo).width(260f).height(62f).padTop(2f).padBottom(6f);
        Raiz.row();
        Raiz.add(BotonDulce).width(260f).height(62f).padBottom(8f);
        Raiz.row();
        ImageButton BotonMenuPrincipal = new ImageButton(CrearEstiloBotonImagen(TexturaBotonMenuPrincipal));
        BotonMenuPrincipal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                JuegoAplicacion.CambiarPantalla(new MainMenuScreen(JuegoAplicacion));
            }
        });
        Raiz.add(BotonMenuPrincipal).right().width(190f).height(54f).padTop(2f);
    }

    private void AgregarBarraSuperior() {
        Table BarraSuperior = new Table();
        Image Titulo = new Image(TexturaTituloSeleccionNiveles);
        BarraSuperior.add(Titulo).width(230f).height(42f).left();
        BarraSuperior.add().expandX();
        BarraSuperior.add(CrearPerfilPlaceholder()).width(166f).height(44f).right();
        Raiz.add(BarraSuperior).width(440f).height(48f);
    }

    private Table CrearPerfilPlaceholder() {
        Table Perfil = new Table();
        Usuario UsuarioActual = SistemaAutenticacion.getUsuarioActivo();
        String NombreUsuario = UsuarioActual == null ? "Invitado" : UsuarioActual.getUsername();
        if (TexturaAvatarPerfil == null) {
            TexturaAvatarPerfil = CargarAvatarPerfil(UsuarioActual);
        }
        Image Avatar = new Image(TexturaAvatarPerfil);
        Label Nombre = new Label(NombreUsuario, EstiloTexto);
        Nombre.setAlignment(Align.left);
        Perfil.add(Avatar).width(28f).height(28f).padRight(8f);
        Perfil.add(Nombre).expandX().left();

        return Perfil;
    }

    private Actor CrearTarjetaNivel(final int NumeroNivel) {
        boolean NivelDesbloqueado = ProgresoNiveles.NivelEstaDesbloqueado(SistemaAutenticacion.getUsuarioActivo(), NumeroNivel);
        NivelTarjetaActor Tarjeta = new NivelTarjetaActor(CargarFondoNivel(NumeroNivel), NivelDesbloqueado);
        if (!NivelDesbloqueado) {
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

    private final class NivelTarjetaActor extends Actor {
        private final Texture FondoNivel;
        private final boolean NivelDesbloqueado;

        private NivelTarjetaActor(Texture FondoNivel, boolean NivelDesbloqueado) {
            this.FondoNivel = FondoNivel;
            this.NivelDesbloqueado = NivelDesbloqueado;
        }

        @Override
        public void draw(Batch BatchActual, float ParentAlpha) {
            float X = getX();
            float Y = getY();
            float Ancho = getWidth();
            float Alto = getHeight();
            float Margen = 7f;
            float XInterno = X + Margen;
            float YInterno = Y + Margen;
            float AnchoInterno = Ancho - Margen * 2f;
            float AltoInterno = Alto - Margen * 2f;
            BatchActual.setColor(1f, 1f, 1f, ParentAlpha);
            BatchActual.draw(TexturaMarcoNivel, X, Y, Ancho, Alto);
            BatchActual.draw(FondoNivel, XInterno, YInterno, AnchoInterno, AltoInterno);
            if (!NivelDesbloqueado) {
                BatchActual.draw(TexturaOverlayBloqueado, XInterno, YInterno, AnchoInterno, AltoInterno);
                float CandadoTamano = Math.min(AnchoInterno, AltoInterno) - 18f;
                BatchActual.draw(TexturaBloqueoNivel, XInterno + (AnchoInterno - CandadoTamano) / 2f, YInterno + (AltoInterno - CandadoTamano) / 2f, CandadoTamano, CandadoTamano);
            }
        }
    }

    private Texture CargarFondoNivel(int NumeroNivel) {
        Texture Textura = CargarTextura(ObtenerRutaImagenNivel(NumeroNivel));
        TexturasFondosNivel.add(Textura);

        return Textura;
    }

    static String ObtenerRutaImagenNivel(int NumeroNivel) {
        return RutasTexturas.ObtenerFondoNivel(NumeroNivel);
    }

    static String ObtenerRutaBotonDulce(ColorDulce ColorDulceSeleccionado) {
        return "imagenes/boton_dulce_" + ColorDulceSeleccionado.name().toLowerCase(Locale.ROOT) + ".png";
    }

    static String ObtenerRutaBotonMonstruo(ColorMonstruo ColorMonstruoSeleccionado) {
        return "imagenes/boton_monstruo_" + ColorMonstruoSeleccionado.name().toLowerCase(Locale.ROOT) + ".png";
    }

    static String ObtenerRutaBotonMenuPrincipal() {
        return "imagenes/menu_principal.png";
    }

    static String ObtenerRutaTituloSeleccionNiveles() {
        return "imagenes/seleccion_de_niveles.png";
    }

    private ImageButton.ImageButtonStyle CrearEstiloBotonImagen(Texture TexturaBotonImagen) {
        ImageButton.ImageButtonStyle Estilo = new ImageButton.ImageButtonStyle();
        TextureRegionDrawable Drawable = new TextureRegionDrawable(TexturaBotonImagen);
        Estilo.imageUp = Drawable;
        Estilo.imageDown = Drawable;

        return Estilo;
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

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return Textura;
    }

    private Texture CargarAvatarPerfil(Usuario UsuarioActual) {
        String RutaAvatar = ObtenerRutaAvatarPerfil(UsuarioActual);
        try {
            if (RutaAvatar.startsWith("imgMenus") && Gdx.files.internal(RutaAvatar).exists()) {
                return CargarTextura(RutaAvatar);
            }
            if (RutaAvatar.startsWith("avatars") && Gdx.files.internal(RutaAvatar).exists()) {
                return CargarTextura(RutaAvatar);
            }
            if (Gdx.files.absolute(RutaAvatar).exists()) {
                Texture Textura = new Texture(Gdx.files.absolute(RutaAvatar));
                Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                return Textura;
            }
        } catch (RuntimeException Excepcion) {
            if (Gdx.app != null) {
                Gdx.app.error("PantallaSeleccionNivel", "No se pudo cargar avatar: " + RutaAvatar, Excepcion);
            }
        }

        return CargarTextura("imgMenus/avatar1.png");
    }

    private String ObtenerRutaAvatarPerfil(Usuario UsuarioActual) {
        if (UsuarioActual == null) {
            return "imgMenus/avatar1.png";
        }
        if (UsuarioActual.getRutaFotoPerfil() != null && !UsuarioActual.getRutaFotoPerfil().trim().isEmpty()) {
            return UsuarioActual.getRutaFotoPerfil().trim();
        }
        if (UsuarioActual.getAvatarPath() != null && !UsuarioActual.getAvatarPath().trim().isEmpty()) {
            return UsuarioActual.getAvatarPath().trim();
        }

        return "imgMenus/avatar1.png";
    }

    private void ActualizarPreviewDulce() {
        if (TexturaDulcePreview != null) {
            TexturaDulcePreview.dispose();
        }
        TexturaDulcePreview = CargarTextura(RutasTexturas.ObtenerDulce(ColorDulceActual));
        ImagenDulcePreview.setDrawable(new TextureRegionDrawable(TexturaDulcePreview));
    }

    private void ActualizarBotonDulce(ImageButton BotonDulce) {
        if (TexturaBotonDulce != null) {
            TexturaBotonDulce.dispose();
        }
        TexturaBotonDulce = CargarTextura(ObtenerRutaBotonDulce(ColorDulceActual));
        BotonDulce.setStyle(CrearEstiloBotonImagen(TexturaBotonDulce));
    }

    private void ActualizarPreviewMonstruo() {
        if (TexturaMonstruoPreview != null) {
            TexturaMonstruoPreview.dispose();
        }
        TexturaMonstruoPreview = CargarTextura(RutasTexturas.ObtenerMonstruo(ColorMonstruoActual));
        ImagenMonstruoPreview.setDrawable(new TextureRegionDrawable(TexturaMonstruoPreview));
    }

    private void ActualizarBotonMonstruo(ImageButton BotonMonstruo) {
        if (TexturaBotonMonstruo != null) {
            TexturaBotonMonstruo.dispose();
        }
        TexturaBotonMonstruo = CargarTextura(ObtenerRutaBotonMonstruo(ColorMonstruoActual));
        BotonMonstruo.setStyle(CrearEstiloBotonImagen(TexturaBotonMonstruo));
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
        if (TexturaBotonPresionado != null) {
            TexturaBotonPresionado.dispose();
        }
        if (TexturaBloqueoNivel != null) {
            TexturaBloqueoNivel.dispose();
        }
        if (TexturaOverlayBloqueado != null) {
            TexturaOverlayBloqueado.dispose();
        }
        if (TexturaMarcoNivel != null) {
            TexturaMarcoNivel.dispose();
        }
        if (TexturaBotonDulce != null) {
            TexturaBotonDulce.dispose();
        }
        if (TexturaBotonMonstruo != null) {
            TexturaBotonMonstruo.dispose();
        }
        if (TexturaBotonMenuPrincipal != null) {
            TexturaBotonMenuPrincipal.dispose();
        }
        if (TexturaTituloSeleccionNiveles != null) {
            TexturaTituloSeleccionNiveles.dispose();
        }
        if (TexturaAvatarPerfil != null) {
            TexturaAvatarPerfil.dispose();
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

