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
    private static final float EscalaGanadorMaxima = 1.07f;
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
    private AnimacionResultadoDueloLocal AnimacionResultadoActual;
    private Table PanelRetador;
    private Table PanelRetado;
    private Label EstadoRetador;
    private Label EstadoRetado;
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
        Gdx.graphics.setWindowedMode(AnchoVentanaDuelo, ConstantesJuego.AltoVentanaEscritorio);
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
    }

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return Textura;
    }

    private void ConstruirContenido() {
        Image Fondo = new Image(TexturaFondoDuelo);
        Fondo.setFillParent(true);
        Fondo.setScaling(Scaling.fill);
        StageActual.addActor(Fondo);

        Table Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.pad(16f);
        StageActual.addActor(Raiz);

        ImageButton BotonVolver = CrearBotonImagen(TexturaVolver);
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Event, float X, float Y) {
                RestaurarVentanaNormal();
                ParentGame.setScreen(new MainMenuScreen(ParentGame));
            }
        });

        Table Encabezado = new Table();
        Encabezado.add(BotonVolver).width(58).height(58).left();
        Encabezado.add(CrearLabel("Duelo local", FuenteTitulo, 1f)).expandX().center();
        Encabezado.add().width(58);
        Raiz.add(Encabezado).width(AnchoVentanaDuelo - 40).padBottom(12).row();

        if (Duelo == null) {
            Raiz.add(CrearLabel("No se encontro el duelo local", FuenteTitulo, 0.85f)).padTop(120).row();
            return;
        }

        Table Tableros = new Table();
        PanelRetador = CrearPanelJugador(Duelo.ObtenerUsernameRetador(), Duelo.ObtenerResultadoRetador(), EsTurnoDe(Duelo.ObtenerUsernameRetador()), true);
        PanelRetado = CrearPanelJugador(Duelo.ObtenerUsernameRetado(), Duelo.ObtenerResultadoRetado(), EsTurnoDe(Duelo.ObtenerUsernameRetado()), false);
        Tableros.add(PanelRetador).width(ConstantesJuego.AnchoVentana).height(630);
        Tableros.add(CrearDivisor()).width(100).height(630).padLeft(12).padRight(12);
        Tableros.add(PanelRetado).width(ConstantesJuego.AnchoVentana).height(630);
        Raiz.add(Tableros).row();

        AgregarAccionPrincipal(Raiz);
        ActualizarAnimacionResultado(0f);
    }

    private Table CrearPanelJugador(String Username, ResultadoTurnoRivalidad Resultado, boolean Activo, boolean EsRetador) {
        Table Panel = new Table();
        Panel.setTransform(true);
        Panel.setOrigin(Align.center);
        Panel.setBackground(SkinActual.newDrawable("fondoCampo", Activo ? new Color(0.08f, 0.18f, 0.15f, 0.96f) : new Color(0.03f, 0.05f, 0.06f, 0.94f)));
        Panel.pad(12f);

        Label TituloJugador = CrearLabel(Username, FuenteTitulo, 0.9f);
        Panel.add(TituloJugador).height(50).row();

        Image Preview = new Image(TexturaFondoNivel);
        Preview.setScaling(Scaling.fill);
        Panel.add(Preview).width(360).height(500).padTop(8).row();

        Label Estado = CrearLabel(CrearTextoEstadoJugador(Resultado, Activo), FuenteTexto, 0.86f);
        Panel.add(Estado).height(52).padTop(8);
        if (EsRetador) {
            EstadoRetador = Estado;
        } else {
            EstadoRetado = Estado;
        }

        return Panel;
    }

    private Table CrearDivisor() {
        Table Divisor = new Table();
        Divisor.add(CrearLabel("VS", FuenteTitulo, 1.25f)).center().row();
        String TextoTurno = Duelo.EstaFinalizado() ? "Final" : "Turno de\n" + Duelo.ObtenerUsernameConTurno();
        Divisor.add(CrearLabel(TextoTurno, FuenteTexto, 0.78f)).width(90).center().padTop(18);

        return Divisor;
    }

    private void AgregarAccionPrincipal(Table Raiz) {
        if (Duelo.EstaFinalizado()) {
            GanadorLabel = CrearLabel("", FuenteTitulo, 0.95f);
            GanadorLabel.setVisible(false);
            Raiz.add(GanadorLabel).padTop(12).row();
            return;
        }
        ImageButton BotonJugar = CrearBotonImagen(TexturaIniciarReto);
        BotonJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Event, float X, float Y) {
                IniciarTurnoActual();
            }
        });
        Raiz.add(BotonJugar).width(220).height(58).padTop(12);
    }

    private String CrearTextoEstadoJugador(ResultadoTurnoRivalidad Resultado, boolean Activo) {
        if (Resultado != null) {
            return Resultado.ObtenerPuntaje() + " pts / " + Resultado.ObtenerEstrellas() + " estrellas";
        }
        if (Activo) {
            return "Listo para jugar";
        }

        return "Esperando turno";
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
        EstadoRetador.setText(CrearTextoResultadoAnimado(ResultadoRetador, AnimacionResultadoActual.ObtenerPuntajeRetador(ResultadoRetador)));
        EstadoRetado.setText(CrearTextoResultadoAnimado(ResultadoRetado, AnimacionResultadoActual.ObtenerPuntajeRetado(ResultadoRetado)));

        if (AnimacionResultadoActual.DebeMostrarGanador()) {
            float Progreso = AnimacionResultadoActual.ObtenerProgresoRevealGanador();
            GanadorLabel.setText("Ganador: " + Duelo.ObtenerGanador());
            GanadorLabel.setVisible(true);
            AplicarHighlightGanador(Progreso);
        }
    }

    private String CrearTextoResultadoAnimado(ResultadoTurnoRivalidad Resultado, int PuntajeMostrado) {
        if (Resultado == null) {
            return "0 pts / 0 estrellas";
        }

        return PuntajeMostrado + " pts / " + Resultado.ObtenerEstrellas() + " estrellas";
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
        float Escala = Ganador ? 1f + ((EscalaGanadorMaxima - 1f) * Progreso) : 1f - (0.05f * Progreso);
        float Opacidad = Ganador ? 1f : 1f - (0.28f * Progreso);
        Panel.setScale(Escala);
        Panel.setColor(1f, 1f, 1f, Opacidad);
        if (Ganador) {
            Panel.setBackground(SkinActual.newDrawable("fondoCampo", new Color(0.12f + (0.18f * Progreso), 0.22f + (0.18f * Progreso), 0.12f, 0.96f)));
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