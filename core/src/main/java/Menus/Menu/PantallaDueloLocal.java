/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
import com.tusderechos.Juego.graficos.RutasTexturas;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.pantallas.PantallaJuego;
import com.tusderechos.Juego.rivalidad.AnimacionResultadoDueloLocal;
import com.tusderechos.Juego.rivalidad.DueloLocal;
import com.tusderechos.Juego.rivalidad.GestorDueloLocal;
import com.tusderechos.Juego.rivalidad.GestorRetos;
import com.tusderechos.Juego.rivalidad.ResultadoTurnoRivalidad;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class PantallaDueloLocal implements Screen {
    private static final int AnchoVentanaDuelo = ConstantesJuego.AnchoVentana * 2 + 140;
    private static final int AltoVentanaDuelo = ConstantesJuego.AltoVentanaEscritorio;
    private static final float EscalaGanadorMaxima = 1.09f;
    private final Game ParentGame;
    private final DueloLocal Duelo;
    private final ColorDulce ColorDulceActual;
    private final ColorMonstruo ColorMonstruoActual;
    private Stage StageActual;
    private Skin SkinActual;
    private BitmapFont FuenteTitulo;
    private BitmapFont FuenteTexto;
    private Texture TexturaFondoDuelo;
    private Texture TexturaFondoNivel;
    private Texture TexturaIniciarReto;
    private Texture TexturaVolver;
    private Texture TexturaEstrella;
    private Texture TexturaEstrellaPlaceholder;
    private AnimacionResultadoDueloLocal AnimacionResultadoActual;
    private Table PanelRetador;
    private Table PanelRetado;
    private Label PuntajeRetadorLabel;
    private Label PuntajeRetadoLabel;
    private Label GanadorLabel;

    public PantallaDueloLocal(Game ParentGame, DueloLocal Duelo, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual) {
        this.ParentGame = ParentGame;
        this.Duelo = Duelo;
        this.ColorDulceActual = ColorDulceActual == null ? ColorDulce.Rojo : ColorDulceActual;
        this.ColorMonstruoActual = ColorMonstruoActual == null ? ColorMonstruo.Verde : ColorMonstruoActual;
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(StageActual);
        Gdx.graphics.setWindowedMode(AnchoVentanaDuelo, AltoVentanaDuelo);
        SkinActual = SkinMenu.Crear();
        FuenteTitulo = GestorFuentes.CrearFuenteGoodDog(42);
        FuenteTexto = GestorFuentes.CrearFuenteGoodDog(28);
        CargarTexturas();
        if (Duelo != null && Duelo.EstaFinalizado()) {
            AnimacionResultadoActual = new AnimacionResultadoDueloLocal(1.15f, 1.25f, 0.65f);
        }
        ConstruirContenido();
    }

    private void CargarTexturas() {
        int NumeroFondo = Duelo == null ? 1 : Duelo.ObtenerReto().ObtenerNumeroEnCategoria();
        TexturaFondoDuelo = CargarTextura("imagenes/fondo_duelo_local.PNG");
        TexturaFondoNivel = CargarTextura(RutasTexturas.ObtenerFondoNivel(NumeroFondo));
        TexturaIniciarReto = CargarTextura("imagenes/iniciar_reto.png");
        TexturaVolver = CargarTextura("imgMenus/btn_volver.png");
        TexturaEstrella = CargarTextura("imagenes/estrella.png");
        TexturaEstrellaPlaceholder = CargarTextura("imagenes/estrella_placeholder.png");
    }

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return Textura;
    }

    private void ConstruirContenido() {
        Image Fondo = new Image(TexturaFondoDuelo);
        Fondo.setBounds(0f, 0f, AnchoVentanaDuelo, AltoVentanaDuelo);
        Fondo.setScaling(Scaling.fill);
        StageActual.addActor(Fondo);

        ImageButton BotonVolver = CrearBotonImagen(TexturaVolver);
        BotonVolver.setBounds(34f, 684f, 58f, 58f);
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Event, float X, float Y) {
                RestaurarVentanaNormal();
                ParentGame.setScreen(new MainMenuScreen(ParentGame));
            }
        });
        StageActual.addActor(BotonVolver);

        Label Titulo = CrearLabel("Duelo local", FuenteTitulo, 1f);
        Titulo.setBounds(420f, 694f, 260f, 52f);
        StageActual.addActor(Titulo);

        if (Duelo == null) {
            Label Mensaje = CrearLabel("No se encontro el duelo local", FuenteTitulo, 0.85f);
            Mensaje.setBounds(300f, 360f, 500f, 80f);
            StageActual.addActor(Mensaje);
            return;
        }

        CrearAreaJugador(Duelo.ObtenerUsernameRetador(), Duelo.ObtenerResultadoRetador(), EsTurnoDe(Duelo.ObtenerUsernameRetador()), true, 92f, 122f, 392f, 536f);
        CrearDivisor();
        CrearAreaJugador(Duelo.ObtenerUsernameRetado(), Duelo.ObtenerResultadoRetado(), EsTurnoDe(Duelo.ObtenerUsernameRetado()), false, 618f, 122f, 392f, 536f);
        AgregarAccionPrincipal();
        ActualizarAnimacionResultado(0f);
    }

    private void CrearAreaJugador(String Username, ResultadoTurnoRivalidad Resultado, boolean Activo, boolean EsRetador, float X, float Y, float Ancho, float Alto) {
        Label TituloJugador = CrearLabel(Username, FuenteTitulo, 0.86f);
        TituloJugador.setBounds(X, Y + Alto + 10f, Ancho, 48f);
        StageActual.addActor(TituloJugador);

        if (Resultado != null) {
            Table PanelResultado = CrearPanelVictoria(Username, Resultado, EsRetador);
            PanelResultado.setBounds(X + 36f, Y + 76f, Ancho - 72f, Alto - 132f);
            PanelResultado.setTransform(true);
            PanelResultado.setOrigin(Align.center);
            StageActual.addActor(PanelResultado);
            if (EsRetador) {
                PanelRetador = PanelResultado;
            } else {
                PanelRetado = PanelResultado;
            }
            return;
        }

        Image Preview = new Image(TexturaFondoNivel);
        Preview.setScaling(Scaling.fill);
        Preview.setBounds(X + 18f, Y + 72f, Ancho - 36f, Alto - 116f);
        StageActual.addActor(Preview);

        Label Estado = CrearLabel(Activo ? "Listo para jugar" : "Esperando turno", FuenteTexto, 0.86f);
        Estado.setBounds(X, Y + 18f, Ancho, 40f);
        StageActual.addActor(Estado);
    }

    private Table CrearPanelVictoria(String Username, ResultadoTurnoRivalidad Resultado, boolean EsRetador) {
        Table Panel = new Table();
        Panel.setBackground(SkinActual.newDrawable("fondoCampo", new Color(0.02f, 0.04f, 0.05f, 0.88f)));
        Panel.pad(16f);

        Label TituloResultado = CrearLabel("Resultado", FuenteTitulo, 0.78f);
        Panel.add(TituloResultado).height(42f).row();
        Panel.add(CrearEstrellas(Resultado.ObtenerEstrellas())).height(54f).padTop(2f).padBottom(10f).row();

        Label Puntaje = CrearLabel("Puntaje: 0", FuenteTexto, 0.82f);
        Panel.add(Puntaje).height(42f).row();
        Panel.add(CrearLabel("Estrellas: " + Resultado.ObtenerEstrellas() + "/3", FuenteTexto, 0.76f)).height(36f).row();
        Panel.add(CrearLabel("Tiempo: " + Math.round(Resultado.ObtenerTiempo()) + " s", FuenteTexto, 0.76f)).height(36f).row();
        Panel.add(CrearLabel(Username, FuenteTexto, 0.72f)).height(36f).padTop(8f);

        if (EsRetador) {
            PuntajeRetadorLabel = Puntaje;
        } else {
            PuntajeRetadoLabel = Puntaje;
        }

        return Panel;
    }

    private Table CrearEstrellas(int EstrellasGanadas) {
        Table Fila = new Table();
        for (int Indice = 1; Indice <= 3; Indice++) {
            Texture Textura = Indice <= EstrellasGanadas ? TexturaEstrella : TexturaEstrellaPlaceholder;
            Image Estrella = new Image(Textura);
            Estrella.setScaling(Scaling.fit);
            Fila.add(Estrella).width(48f).height(48f).padLeft(4f).padRight(4f);
        }

        return Fila;
    }

    private void CrearDivisor() {
        Label Vs = CrearLabel("VS", FuenteTitulo, 1.25f);
        Vs.setBounds(505f, 362f, 90f, 72f);
        StageActual.addActor(Vs);
        Label Turno = CrearLabel(Duelo.EstaFinalizado() ? "Final" : "Turno de\n" + Duelo.ObtenerUsernameConTurno(), FuenteTexto, 0.78f);
        Turno.setBounds(505f, 322f, 90f, 54f);
        StageActual.addActor(Turno);
    }

    private void AgregarAccionPrincipal() {
        if (Duelo.EstaFinalizado()) {
            GanadorLabel = CrearLabel("", FuenteTitulo, 0.95f);
            GanadorLabel.setVisible(false);
            GanadorLabel.setBounds(350f, 56f, 400f, 56f);
            StageActual.addActor(GanadorLabel);
            return;
        }
        ImageButton BotonJugar = CrearBotonImagen(TexturaIniciarReto);
        BotonJugar.setBounds(440f, 58f, 220f, 58f);
        BotonJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Event, float X, float Y) {
                IniciarTurnoActual();
            }
        });
        StageActual.addActor(BotonJugar);
    }

    private boolean EsTurnoDe(String Username) {
        return !Duelo.EstaFinalizado() && Duelo.ObtenerUsernameConTurno().equals(Username);
    }

    private void IniciarTurnoActual() {
        String UsernameTurno = Duelo.ObtenerUsernameConTurno();
        DatosNivel Nivel = GestorRetos.ObtenerNivelReto(Duelo.ObtenerReto());
        RestaurarVentanaNormal();
        ParentGame.setScreen(new PantallaJuego((Juego) ParentGame, Nivel, new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual), Duelo.ObtenerReto(), GestorDueloLocal.CrearIdTurno(Duelo, UsernameTurno)));
    }

    private void ActualizarAnimacionResultado(float Delta) {
        if (Duelo == null || !Duelo.EstaFinalizado() || AnimacionResultadoActual == null) {
            return;
        }
        AnimacionResultadoActual.Avanzar(Delta);
        ResultadoTurnoRivalidad ResultadoRetador = Duelo.ObtenerResultadoRetador();
        ResultadoTurnoRivalidad ResultadoRetado = Duelo.ObtenerResultadoRetado();
        PuntajeRetadorLabel.setText("Puntaje: " + AnimacionResultadoActual.ObtenerPuntajeRetador(ResultadoRetador));
        PuntajeRetadoLabel.setText("Puntaje: " + AnimacionResultadoActual.ObtenerPuntajeRetado(ResultadoRetado));

        if (AnimacionResultadoActual.DebeMostrarGanador()) {
            float Progreso = AnimacionResultadoActual.ObtenerProgresoRevealGanador();
            GanadorLabel.setText("Ganador: " + Duelo.ObtenerGanador());
            GanadorLabel.setVisible(true);
            AplicarHighlightGanador(Progreso);
        }
    }

    private void AplicarHighlightGanador(float Progreso) {
        String Ganador = Duelo.ObtenerGanador();
        boolean GanoRetador = Duelo.ObtenerUsernameRetador().equals(Ganador);
        boolean GanoRetado = Duelo.ObtenerUsernameRetado().equals(Ganador);
        if ("Empate".equals(Ganador)) {
            AplicarEstadoPanelFinal(PanelRetador, true, Progreso * 0.55f);
            AplicarEstadoPanelFinal(PanelRetado, true, Progreso * 0.55f);
            return;
        }
        AplicarEstadoPanelFinal(PanelRetador, GanoRetador, Progreso);
        AplicarEstadoPanelFinal(PanelRetado, GanoRetado, Progreso);
    }

    private void AplicarEstadoPanelFinal(Table Panel, boolean Ganador, float Progreso) {
        float Escala = Ganador ? 1f + ((EscalaGanadorMaxima - 1f) * Progreso) : 1f - (0.04f * Progreso);
        float Opacidad = Ganador ? 1f : 1f - (0.24f * Progreso);
        Panel.setScale(Escala);
        Panel.setColor(1f, 1f, 1f, Opacidad);
        if (Ganador) {
            Panel.setBackground(SkinActual.newDrawable("fondoCampo", new Color(0.13f + (0.20f * Progreso), 0.22f + (0.18f * Progreso), 0.08f, 0.92f)));
        }
    }

    private ImageButton CrearBotonImagen(Texture Textura) {
        ImageButton Boton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textura)));
        Boton.getImage().setScaling(Scaling.fill);
        Boton.getImage().setAlign(Align.center);

        return Boton;
    }

    private Label CrearLabel(String Texto, BitmapFont Fuente, float Escala) {
        Label LabelActual = new Label(Texto, new Label.LabelStyle(Fuente, Color.WHITE));
        LabelActual.setAlignment(Align.center);
        LabelActual.setFontScale(Escala);

        return LabelActual;
    }

    @Override
    public void render(float Delta) {
        Gdx.gl.glClearColor(0.02f, 0.03f, 0.04f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (StageActual != null) {
            ActualizarAnimacionResultado(Delta);
            StageActual.act(Delta);
            StageActual.draw();
        }
    }

    @Override
    public void resize(int Width, int Height) {
        if (StageActual != null) {
            StageActual.getViewport().update(Width, Height, true);
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        RestaurarVentanaNormal();
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
        Disponer(TexturaFondoDuelo);
        Disponer(TexturaFondoNivel);
        Disponer(TexturaIniciarReto);
        Disponer(TexturaVolver);
        Disponer(TexturaEstrella);
        Disponer(TexturaEstrellaPlaceholder);
    }

    private void RestaurarVentanaNormal() {
        Gdx.graphics.setWindowedMode(ConstantesJuego.AnchoVentanaEscritorio, ConstantesJuego.AltoVentanaEscritorio);
    }

    private void Disponer(Texture Textura) {
        if (Textura != null) {
            Textura.dispose();
        }
    }
}