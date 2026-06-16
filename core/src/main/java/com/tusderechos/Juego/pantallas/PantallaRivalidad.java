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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.GestorRivalidades;
import com.tusderechos.Juego.rivalidad.GestorRetos;
import com.tusderechos.Juego.rivalidad.GuardadorRivalidadesBinario;
import com.tusderechos.Juego.rivalidad.SolicitudRivalidad;
import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;
import Menus.Menu.PantallaSolicitudesRivalidad;
import Menus.Menu.ProfileScreen;
import java.nio.file.Path;
import java.util.List;

public class PantallaRivalidad extends ScreenAdapter {
    private static final int PuntajeObjetivoMinimo = 0;
    private static final int PuntajeObjetivoMaximo = 4000;
    private static final int PasoPuntajeObjetivo = 250;
    private static final float AnchoPanelMaximo = 580f;
    private static final float AnchoFilaMaximo = 540f;
    private static final float MargenRaiz = 12f;
    private final Juego JuegoAplicacion;
    private final ColorDulce ColorDulceActual;
    private final ColorMonstruo ColorMonstruoActual;
    private final String UsernameRetado;
    private Stage StageActual;
    private BitmapFont FuenteTitulo;
    private BitmapFont FuenteTexto;
    private Label.LabelStyle EstiloTitulo;
    private Label.LabelStyle EstiloTexto;
    private Texture TexturaFacil;
    private Texture TexturaMedia;
    private Texture TexturaDificil;
    private Texture TexturaSumar;
    private Texture TexturaRestar;
    private Texture TexturaVolver;
    private Texture TexturaIniciarReto;
    private Table Raiz;
    private CategoriaDificultad CategoriaActual = CategoriaDificultad.Media;
    private int NumeroNivelActual = 1;
    private int EstrellasObjetivo = 2;
    private int PuntajeObjetivo = 3200;

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual) {
        this(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual, CategoriaDificultad.Media, null);
    }

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual, CategoriaDificultad CategoriaInicial) {
        this(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual, CategoriaInicial, null);
    }

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual, String UsernameRetado) {
        this(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual, CategoriaDificultad.Media, UsernameRetado);
    }

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual, CategoriaDificultad CategoriaInicial, String UsernameRetado) {
        this.JuegoAplicacion = JuegoAplicacion;
        this.ColorDulceActual = ColorDulceActual;
        this.ColorMonstruoActual = ColorMonstruoActual;
        this.UsernameRetado = NormalizarUsuario(UsernameRetado);
        CambiarCategoria(CategoriaInicial == null ? CategoriaDificultad.Media : CategoriaInicial);
    }

    public CategoriaDificultad ObtenerCategoriaActual() {
        return CategoriaActual;
    }

    public int ObtenerNumeroNivelActual() {
        return NumeroNivelActual;
    }

    public int ObtenerEstrellasObjetivo() {
        return EstrellasObjetivo;
    }

    public int ObtenerPuntajeObjetivo() {
        return PuntajeObjetivo;
    }

    public void CambiarCategoria(CategoriaDificultad CategoriaNueva) {
        if (CategoriaNueva == null) {
            throw new IllegalArgumentException("La categoria del reto no puede ser nula");
        }
        CategoriaActual = CategoriaNueva;
        NumeroNivelActual = Math.min(NumeroNivelActual, FabricaNiveles.CantidadNiveles(CategoriaActual));
        NumeroNivelActual = Math.max(1, NumeroNivelActual);
    }

    public void AjustarNivel(int Cambio) {
        int CantidadNiveles = FabricaNiveles.CantidadNiveles(CategoriaActual);
        NumeroNivelActual = LimitarValor(NumeroNivelActual + Cambio, 1, CantidadNiveles);
    }

    public void AjustarEstrellasObjetivo(int Cambio) {
        EstrellasObjetivo = LimitarValor(EstrellasObjetivo + Cambio, 0, 3);
    }

    public void AjustarPuntajeObjetivo(int Cambio) {
        PuntajeObjetivo = LimitarValor(PuntajeObjetivo + Cambio, PuntajeObjetivoMinimo, PuntajeObjetivoMaximo);
    }

    public DatosReto CrearRetoActual() {
        return new DatosReto(CategoriaActual, NumeroNivelActual, ObtenerUsernameRetador(), PuntajeObjetivo, EstrellasObjetivo);
    }

    public SolicitudRivalidad CrearSolicitudActual(String UsernameRetado) {
        return GestorRivalidades.CrearSolicitud(CrearRetoActual(), ObtenerUsernameRetador(), UsernameRetado);
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        FuenteTitulo = GestorFuentes.CrearFuenteGoodDog(42);
        FuenteTexto = GestorFuentes.CrearFuenteGoodDog(31);
        EstiloTitulo = new Label.LabelStyle(FuenteTitulo, Color.WHITE);
        EstiloTexto = new Label.LabelStyle(FuenteTexto, Color.WHITE);
        CrearTexturas();
        Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.pad(22f);
        StageActual.addActor(Raiz);
        ConstruirContenido();
        Gdx.input.setInputProcessor(StageActual);
    }

    private void CrearTexturas() {
        TexturaFacil = CargarTextura("imagenes/facil-removebg-preview.png");
        TexturaMedia = CargarTextura("imagenes/media.png");
        TexturaDificil = CargarTextura("imagenes/dificil.png");
        TexturaSumar = CargarTextura("imagenes/sumar.png");
        TexturaRestar = CargarTextura("imagenes/restar.png");
        TexturaVolver = CargarTextura("imagenes/volver.png");
        TexturaIniciarReto = CargarTextura("imagenes/iniciar_reto.png");
    }

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return Textura;
    }

    private ImageButton CrearBotonImagen(Texture Textura) {
        ImageButton Boton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textura)));
        Boton.getImage().setScaling(Scaling.fit);
        Boton.getImage().setAlign(Align.center);

        return Boton;
    }

    private void ConstruirContenido() {
        Raiz.clearChildren();
        Raiz.top();
        Raiz.pad(MargenRaiz);

        float AnchoPanel = CalcularAnchoPanel(ObtenerAnchoVentana());
        float AnchoFila = CalcularAnchoFila(AnchoPanel);
        float EscalaLayout = CalcularEscalaLayout(AnchoFila);

        Table Panel = new Table();
        Raiz.add(Panel).width(AnchoPanel).padTop(6f * EscalaLayout);

        Label Titulo = new Label("Rivalidad", EstiloTitulo);
        Titulo.setAlignment(Align.center);
        Panel.add(Titulo).padBottom(10f * EscalaLayout);
        Panel.row();

        AgregarCategorias(Panel, EscalaLayout);
        AgregarControlNumerico(Panel, "Nivel", String.valueOf(NumeroNivelActual), 120f, AnchoFila, EscalaLayout, new Runnable() {
            @Override
            public void run() {
                AjustarNivel(-1);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarNivel(1);
            }
        });
        AgregarControlNumerico(Panel, "Estrellas", String.valueOf(EstrellasObjetivo), 120f, AnchoFila, EscalaLayout, new Runnable() {
            @Override
            public void run() {
                AjustarEstrellasObjetivo(-1);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarEstrellasObjetivo(1);
            }
        });
        AgregarControlNumerico(Panel, "Puntaje", String.valueOf(PuntajeObjetivo), 140f, AnchoFila, EscalaLayout, new Runnable() {
            @Override
            public void run() {
                AjustarPuntajeObjetivo(-PasoPuntajeObjetivo);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarPuntajeObjetivo(PasoPuntajeObjetivo);
            }
        });

        Label ResumenReto = new Label(TextoResumenReto(), EstiloTexto);
        ResumenReto.setWrap(true);
        ResumenReto.setAlignment(Align.center);
        Panel.add(ResumenReto).width(AnchoFila).padTop(10f * EscalaLayout).padBottom(10f * EscalaLayout);
        Panel.row();

        AgregarAcciones(Panel, EscalaLayout);
    }

    private void AgregarCategorias(Table Panel, float EscalaLayout) {
        Table FilaCategorias = new Table();
        AgregarBotonCategoria(FilaCategorias, CategoriaDificultad.Facil, TexturaFacil, EscalaLayout);
        AgregarBotonCategoria(FilaCategorias, CategoriaDificultad.Media, TexturaMedia, EscalaLayout);
        AgregarBotonCategoria(FilaCategorias, CategoriaDificultad.Dificil, TexturaDificil, EscalaLayout);
        Panel.add(FilaCategorias).padBottom(8f * EscalaLayout);
        Panel.row();
    }

    private void AgregarBotonCategoria(Table FilaCategorias, final CategoriaDificultad Categoria, Texture Textura, float EscalaLayout) {
        ImageButton BotonCategoria = CrearBotonImagen(Textura);
        BotonCategoria.setColor(Categoria == CategoriaActual ? Color.WHITE : new Color(0.72f, 0.72f, 0.72f, 1f));
        BotonCategoria.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                CambiarCategoria(Categoria);
                ConstruirContenido();
            }
        });
        FilaCategorias.add(BotonCategoria).width(120f * EscalaLayout).height(52f * EscalaLayout).pad(4f * EscalaLayout);
    }

    private void AgregarControlNumerico(Table Panel, String Titulo, String Valor, float AnchoValor, float AnchoFila, float EscalaLayout, final Runnable AccionMenos, final Runnable AccionMas) {
        Table FilaControl = new Table();
        Label Etiqueta = new Label(Titulo, EstiloTexto);
        Label EtiquetaValor = new Label(Valor, EstiloTexto);
        EtiquetaValor.setAlignment(Align.center);

        ImageButton BotonMenos = CrearBotonImagen(TexturaRestar);
        BotonMenos.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                AccionMenos.run();
                ConstruirContenido();
            }
        });

        ImageButton BotonMas = CrearBotonImagen(TexturaSumar);
        BotonMas.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                AccionMas.run();
                ConstruirContenido();
            }
        });

        FilaControl.add(Etiqueta).width(140f * EscalaLayout).left().padRight(8f * EscalaLayout);
        FilaControl.add(BotonMenos).width(68f * EscalaLayout).height(42f * EscalaLayout).pad(3f * EscalaLayout);
        FilaControl.add(EtiquetaValor).width(AnchoValor * EscalaLayout).center().padLeft(8f * EscalaLayout).padRight(8f * EscalaLayout);
        FilaControl.add(BotonMas).width(68f * EscalaLayout).height(42f * EscalaLayout).pad(3f * EscalaLayout);
        Panel.add(FilaControl).width(AnchoFila).padTop(6f * EscalaLayout).padBottom(2f * EscalaLayout);
        Panel.row();
    }

    private void AgregarAcciones(Table Panel, float EscalaLayout) {
        Table FilaAcciones = new Table();
        ImageButton BotonVolver = CrearBotonImagen(TexturaVolver);
        BotonVolver.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                if (UsernameRetado != null && !UsernameRetado.isEmpty()) {
                    JuegoAplicacion.CambiarPantalla(new ProfileScreen(JuegoAplicacion, UsernameRetado));
                    return;
                }
                JuegoAplicacion.CambiarPantalla(new PantallaSeleccionNivel(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual));
            }
        });

        ImageButton BotonIniciar = CrearBotonImagen(TexturaIniciarReto);
        BotonIniciar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                DatosReto Reto = CrearRetoActual();
                if (UsernameRetado != null && !UsernameRetado.isEmpty()) {
                    GuardarSolicitudRivalidad(Reto);
                    JuegoAplicacion.CambiarPantalla(new PantallaSolicitudesRivalidad(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual));
                    return;
                }
                JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, GestorRetos.ObtenerNivelReto(Reto), new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual), Reto));
            }
        });

        FilaAcciones.add(BotonVolver).width(140f * EscalaLayout).height(50f * EscalaLayout).pad(5f * EscalaLayout);
        FilaAcciones.add(BotonIniciar).width(180f * EscalaLayout).height(50f * EscalaLayout).pad(5f * EscalaLayout);
        Panel.add(FilaAcciones);
    }

    private float ObtenerAnchoVentana() {
        if (StageActual == null) {
            return AnchoPanelMaximo + (MargenRaiz * 2f);
        }
        return StageActual.getViewport().getWorldWidth();
    }

    static float CalcularAnchoPanel(float AnchoVentana) {
        return Math.min(AnchoPanelMaximo, Math.max(0f, AnchoVentana - (MargenRaiz * 2f)));
    }

    private float CalcularAnchoFila(float AnchoPanel) {
        return Math.min(AnchoFilaMaximo, AnchoPanel);
    }

    private float CalcularEscalaLayout(float AnchoFila) {
        return Math.max(0.62f, Math.min(1f, AnchoFila / AnchoFilaMaximo));
    }

    private String TextoResumenReto() {
        return CategoriaActual.name() + " " + NumeroNivelActual + " - " + PuntajeObjetivo + " pts / " + EstrellasObjetivo + " estrellas";
    }

    private String ObtenerUsernameRetador() {
        Usuario UsuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        if (UsuarioActivo == null || UsuarioActivo.getUsername() == null || UsuarioActivo.getUsername().trim().isEmpty()) {
            return "Admin";
        }

        return UsuarioActivo.getUsername().trim().toLowerCase();
    }

    private String NormalizarUsuario(String Username) {
        if (Username == null || Username.trim().isEmpty()) {
            return null;
        }

        return Username.trim().toLowerCase();
    }

    private void GuardarSolicitudRivalidad(DatosReto Reto) {
        Path Ruta = Gdx.files.local("datos/rivalidades_cut_the_rope.bin").file().toPath();
        List<SolicitudRivalidad> Solicitudes = GuardadorRivalidadesBinario.Cargar(Ruta);
        Solicitudes.add(GestorRivalidades.CrearSolicitud(Reto, ObtenerUsernameRetador(), UsernameRetado));
        GuardadorRivalidadesBinario.Guardar(Ruta, Solicitudes);
    }

    private int LimitarValor(int Valor, int Minimo, int Maximo) {
        return Math.max(Minimo, Math.min(Maximo, Valor));
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
            if (Raiz != null) {
                ConstruirContenido();
            }
        }
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
        if (FuenteTitulo != null) {
            FuenteTitulo.dispose();
        }
        if (FuenteTexto != null) {
            FuenteTexto.dispose();
        }
        Disponer(TexturaFacil);
        Disponer(TexturaMedia);
        Disponer(TexturaDificil);
        Disponer(TexturaSumar);
        Disponer(TexturaRestar);
        Disponer(TexturaVolver);
        Disponer(TexturaIniciarReto);
    }

    private void Disponer(Texture Textura) {
        if (Textura != null) {
            Textura.dispose();
        }
    }
}
