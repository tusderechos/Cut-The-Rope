/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

/**
 *
 * @author Hp
 */

import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.graficos.GestorFuentes;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.pantallas.PantallaJuego;
import com.tusderechos.Juego.rivalidad.EstadoRivalidad;
import com.tusderechos.Juego.rivalidad.GestorRetos;
import com.tusderechos.Juego.rivalidad.GestorRivalidades;
import com.tusderechos.Juego.rivalidad.GuardadorRivalidadesBinario;
import com.tusderechos.Juego.rivalidad.SolicitudRivalidad;
import java.nio.file.Path;
import java.util.List;

public class PantallaSolicitudesRivalidad implements Screen {
    private final Game ParentGame;
    private final ColorDulce ColorDulceActual;
    private final ColorMonstruo ColorMonstruoActual;
    private Stage StageActual;
    private Skin SkinActual;
    private BitmapFont FuenteTitulo;
    private BitmapFont FuenteTexto;
    private Texture TexturaRetos;
    private Texture TexturaIniciarReto;
    private Texture TexturaVolver;
    private Texture TexturaSalir;
    private Texture TexturaFondo;
    private Texture TexturaMarcoLista;
    private Texture TexturaInteriorLista;

    public PantallaSolicitudesRivalidad(Game ParentGame) {
        this(ParentGame, ColorDulce.Rojo, ColorMonstruo.Verde);
    }

    public PantallaSolicitudesRivalidad(Game ParentGame, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual) {
        this.ParentGame = ParentGame;
        this.ColorDulceActual = ColorDulceActual == null ? ColorDulce.Rojo : ColorDulceActual;
        this.ColorMonstruoActual = ColorMonstruoActual == null ? ColorMonstruo.Verde : ColorMonstruoActual;
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(StageActual);
        SkinActual = SkinMenu.Crear();
        FuenteTitulo = GestorFuentes.CrearFuenteGoodDog(44);
        FuenteTexto = GestorFuentes.CrearFuenteGoodDog(28);
        CargarTexturas();
        ConstruirContenido();
    }

    private void CargarTexturas() {
        TexturaRetos = CargarTextura("imagenes/retos.png");
        TexturaIniciarReto = CargarTextura("imagenes/iniciar_reto.png");
        TexturaVolver = CargarTextura("imgMenus/btn_volver.png");
        TexturaSalir = CargarTextura("imagenes/salir.png");
        TexturaFondo = CargarTextura("imagenes/fondo_retos.png");
        TexturaMarcoLista = CrearTexturaColor(new Color(0.95f, 0.53f, 0.12f, 0.96f));
        TexturaInteriorLista = CrearTexturaColor(new Color(0.05f, 0.12f, 0.10f, 0.91f));
    }

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return Textura;
    }

    private void ConstruirContenido() {
        Image FondoPantalla = new Image(TexturaFondo);
        FondoPantalla.setFillParent(true);
        FondoPantalla.setScaling(Scaling.fill);
        StageActual.addActor(FondoPantalla);

        Table Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.top();
        Raiz.pad(16f);
        StageActual.addActor(Raiz);

        ImageButton BotonVolver = CrearBotonImagen(TexturaVolver);
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Event, float X, float Y) {
                ParentGame.setScreen(new MainMenuScreen(ParentGame));
            }
        });

        ImageButton Titulo = CrearBotonImagen(TexturaRetos);
        Table Encabezado = new Table();
        Encabezado.add(BotonVolver).width(56).height(56).left();
        Encabezado.add(Titulo).width(230).height(56).center().expandX();
        Encabezado.add().width(56).height(56);
        Raiz.add(Encabezado).width(430).padTop(8).padBottom(18).row();

        Table Lista = new Table();
        Lista.top();
        Lista.pad(14f);
        AgregarSolicitudes(Lista);

        ScrollPane Scroll = new ScrollPane(Lista, SkinActual);
        Scroll.setFadeScrollBars(false);
        Scroll.setScrollingDisabled(true, false);
        Scroll.getStyle().background = new TextureRegionDrawable(new TextureRegion(TexturaInteriorLista));

        Table MarcoLista = new Table();
        MarcoLista.setBackground(new TextureRegionDrawable(new TextureRegion(TexturaMarcoLista)));
        MarcoLista.pad(7f);
        MarcoLista.add(Scroll).width(402).height(520).center();
        Raiz.add(MarcoLista).width(430).height(548).center();
    }

    private void AgregarSolicitudes(Table Lista) {
        Usuario UsuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        if (UsuarioActivo == null) {
            AgregarMensaje(Lista, "Inicia sesion para ver retos");
            return;
        }
        String UsernameActivo = UsuarioActivo.getUsername().trim().toLowerCase();
        List<SolicitudRivalidad> Solicitudes = GuardadorRivalidadesBinario.Cargar(ObtenerRutaRivalidades());
        int RetosMostrados = 0;
        for (final SolicitudRivalidad Solicitud : Solicitudes) {
            if (GestorRivalidades.ParticipaUsuario(Solicitud, UsernameActivo)) {
                AgregarFilaSolicitud(Lista, Solicitud, UsernameActivo);
                RetosMostrados++;
            }
        }
        if (RetosMostrados == 0) {
            AgregarMensaje(Lista, "No hay retos todavia");
        }
    }

    private void AgregarFilaSolicitud(Table Lista, final SolicitudRivalidad Solicitud, final String UsernameActivo) {
        Table Fila = new Table();
        Fila.setBackground(SkinActual.newDrawable("fondoCampo", new Color(0.04f, 0.07f, 0.08f, 0.88f)));
        Fila.pad(12f);
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.Finalizada) {
            Fila.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.72f, 0.45f), Actions.alpha(1f, 0.45f))));
        }

        Label Resumen = CrearLabel(ConstruirResumen(Solicitud, UsernameActivo), 0.9f);
        Resumen.setWrap(true);
        Fila.add(Resumen).width(245).left().padRight(8);

        AgregarAccionSolicitud(Fila, Solicitud, UsernameActivo);
        Lista.add(Fila).width(410).padBottom(10).row();
    }

    private void AgregarAccionSolicitud(Table Fila, final SolicitudRivalidad Solicitud, final String UsernameActivo) {
        Table Acciones = new Table();
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.Pendiente && Solicitud.ObtenerUsernameRetado().equals(UsernameActivo)) {
            ImageButton BotonAceptar = CrearBotonImagen(TexturaIniciarReto);
            BotonAceptar.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent Event, float X, float Y) {
                    GestorRivalidades.AceptarSolicitud(Solicitud, UsernameActivo);
                    GuardarSolicitudActualizada(Solicitud);
                    IniciarTurno(Solicitud);
                }
            });
            ImageButton BotonRechazar = CrearBotonImagen(TexturaSalir);
            BotonRechazar.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent Event, float X, float Y) {
                    GestorRivalidades.RechazarSolicitud(Solicitud, UsernameActivo);
                    GuardarSolicitudActualizada(Solicitud);
                    ParentGame.setScreen(new PantallaSolicitudesRivalidad(ParentGame, ColorDulceActual, ColorMonstruoActual));
                }
            });
            Acciones.add(BotonAceptar).width(118).height(38).row();
            Acciones.add(BotonRechazar).width(118).height(38).padTop(8);
        } else if (GestorRivalidades.TieneTurno(Solicitud, UsernameActivo)) {
            ImageButton BotonJugar = CrearBotonImagen(TexturaIniciarReto);
            BotonJugar.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent Event, float X, float Y) {
                    IniciarTurno(Solicitud);
                }
            });
            Acciones.add(BotonJugar).width(128).height(42);
        } else {
            Acciones.add(CrearLabel(TextoEstadoCorto(Solicitud, UsernameActivo), 0.76f)).width(128).center();
        }
        Fila.add(Acciones).width(132);
    }

    private ImageButton CrearBotonImagen(Texture Textura) {
        ImageButton Boton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textura)));
        Boton.getImage().setScaling(Scaling.fill);
        Boton.getImage().setAlign(Align.center);

        return Boton;
    }

    private Texture CrearTexturaColor(Color ColorActual) {
        Pixmap PixmapActual = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorActual);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();

        return Textura;
    }

    private Label CrearLabel(String Texto, float Escala) {
        Label.LabelStyle Estilo = new Label.LabelStyle(FuenteTexto, Color.WHITE);
        Label LabelActual = new Label(Texto, Estilo);
        LabelActual.setFontScale(Escala);
        LabelActual.setAlignment(Align.center);

        return LabelActual;
    }

    private void AgregarMensaje(Table Lista, String Mensaje) {
        Label LabelMensaje = new Label(Mensaje, new Label.LabelStyle(FuenteTitulo, Color.WHITE));
        LabelMensaje.setAlignment(Align.center);
        Lista.add(LabelMensaje).width(390).height(120).center();
    }

    private String ConstruirResumen(SolicitudRivalidad Solicitud, String UsernameActivo) {
        String Rival = Solicitud.ObtenerUsernameRetador().equals(UsernameActivo) ? Solicitud.ObtenerUsernameRetado() : Solicitud.ObtenerUsernameRetador();
        String Ganador = Solicitud.ObtenerGanador();
        if (!Ganador.isEmpty()) {
            return "Rival: " + Rival + "\n" + TextoReto(Solicitud) + "\nGanador: " + Ganador;
        }

        return "Rival: " + Rival + "\n" + TextoReto(Solicitud) + "\n" + TextoEstadoCorto(Solicitud, UsernameActivo);
    }

    private String TextoReto(SolicitudRivalidad Solicitud) {
        return Solicitud.ObtenerReto().ObtenerCategoria().name() + " " + Solicitud.ObtenerReto().ObtenerNumeroEnCategoria() + " - " + Solicitud.ObtenerReto().ObtenerPuntajeObjetivo() + " pts / " + Solicitud.ObtenerReto().ObtenerEstrellasObjetivo() + " estrellas";
    }

    private String TextoEstadoCorto(SolicitudRivalidad Solicitud, String UsernameActivo) {
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.Pendiente) {
            return Solicitud.ObtenerUsernameRetado().equals(UsernameActivo) ? "Pendiente" : "Esperando";
        }
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.Rechazada) {
            return "Rechazada";
        }
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.Finalizada) {
            return "Final";
        }

        return "Turno de " + Solicitud.ObtenerUsernameConTurno();
    }

    private void IniciarTurno(SolicitudRivalidad Solicitud) {
        DatosNivel Nivel = GestorRetos.ObtenerNivelReto(Solicitud.ObtenerReto());
        ParentGame.setScreen(new PantallaJuego((Juego) ParentGame, Nivel, new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual), Solicitud.ObtenerReto(), Solicitud.ObtenerId()));
    }

    private void GuardarSolicitudActualizada(SolicitudRivalidad SolicitudActualizada) {
        List<SolicitudRivalidad> Solicitudes = GuardadorRivalidadesBinario.Cargar(ObtenerRutaRivalidades());
        for (int Indice = 0; Indice < Solicitudes.size(); Indice++) {
            if (Solicitudes.get(Indice).ObtenerId().equals(SolicitudActualizada.ObtenerId())) {
                Solicitudes.set(Indice, SolicitudActualizada);
                GuardadorRivalidadesBinario.Guardar(ObtenerRutaRivalidades(), Solicitudes);
                return;
            }
        }
        Solicitudes.add(SolicitudActualizada);
        GuardadorRivalidadesBinario.Guardar(ObtenerRutaRivalidades(), Solicitudes);
    }

    private Path ObtenerRutaRivalidades() {
        return Gdx.files.local("datos/rivalidades_cut_the_rope.bin").file().toPath();
    }

    @Override
    public void render(float Delta) {
        Gdx.gl.glClearColor(0.02f, 0.03f, 0.04f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        StageActual.act(Delta);
        StageActual.draw();
    }

    @Override
    public void resize(int Width, int Height) {
        StageActual.getViewport().update(Width, Height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if (StageActual != null) {
            StageActual.dispose();
        }
        if (SkinActual != null) {
            SkinActual.dispose();
        }
        if (FuenteTitulo != null) {
            FuenteTitulo.dispose();
        }
        if (FuenteTexto != null) {
            FuenteTexto.dispose();
        }
        Disponer(TexturaRetos);
        Disponer(TexturaIniciarReto);
        Disponer(TexturaVolver);
        Disponer(TexturaSalir);
        Disponer(TexturaFondo);
        Disponer(TexturaMarcoLista);
        Disponer(TexturaInteriorLista);
    }

    private void Disponer(Texture Textura) {
        if (Textura != null) {
            Textura.dispose();
        }
    }
}
