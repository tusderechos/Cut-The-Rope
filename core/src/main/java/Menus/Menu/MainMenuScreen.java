/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;

/**
 *
 * @author HP
 */
public class MainMenuScreen implements Screen {
    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    public MainMenuScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        String nombreJugador = (usuarioActivo != null) ? usuarioActivo.getNombreCompleto() : "Jugador";

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label lblBienvenida = new Label("Bienvenido, " + nombreJugador, skin);
        lblBienvenida.setFontScale(1.4f);
        lblBienvenida.setColor(Color.GREEN);
        root.add(lblBienvenida).padBottom(35).row();

        TextButton btnJugar = new TextButton("INICIAR JUEGO", skin);
        TextButton btnEstadisticas = new TextButton("VER ESTADISTICAS", skin);
        TextButton btnPerfil = new TextButton("MI PERFIL", skin);
        TextButton btnConfig = new TextButton("CONFIGURACION", skin);
        TextButton btnCerrarSesion = new TextButton("CERRAR SESION", skin);

        float anchoBoton = 240;
        float altoBoton = 45;
        float espaciado = 15;

        btnJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Menu", "Abriendo el mapa del juego...");
            }
        });

        btnEstadisticas.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new StatsScreen(parentGame));
            }
        });

        btnPerfil.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ProfileScreen(parentGame));
            }
        });

        btnConfig.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ConfiguracionScreen(parentGame));
            }
        });

        btnCerrarSesion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SistemaAutenticacion.cerrarSesion();
                parentGame.setScreen(new LoginRegisterScreen(parentGame));
            }
        });

        root.add(btnJugar).width(anchoBoton).height(altoBoton).padBottom(espaciado).row();
        root.add(btnEstadisticas).width(anchoBoton).height(altoBoton).padBottom(espaciado).row();
        root.add(btnPerfil).width(anchoBoton).height(altoBoton).padBottom(espaciado).row();
        root.add(btnConfig).width(anchoBoton).height(altoBoton).padBottom(espaciado).row();
        root.add(btnCerrarSesion).width(anchoBoton).height(altoBoton).padTop(15).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.28f, 0.18f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
