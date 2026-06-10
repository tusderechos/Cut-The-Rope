/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
public class StatsScreen implements Screen {

    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    public StatsScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label lblTitulo = new Label("ESTADISTICAS GENERALES Y RENDIMIENTO", skin);
        lblTitulo.setFontScale(1.3f);
        root.add(lblTitulo).padBottom(25).row();

        if (usuarioActivo != null) {
            Table tablaStats = new Table();
            tablaStats.defaults().pad(8).left();

            tablaStats.add(new Label("🌟 Estrellas Totales:", skin));
            tablaStats.add(new Label(String.valueOf(usuarioActivo.getEstrellasTotales()), skin)).row();

            tablaStats.add(new Label("🏆 Maximo Nivel Superado:", skin));
            tablaStats.add(new Label("Nivel " + usuarioActivo.getNivelesCompletados(), skin)).row();

            tablaStats.add(new Label("🎮 Partidas Intentadas:", skin));
            tablaStats.add(new Label(String.valueOf(usuarioActivo.getPartidasJugadas()), skin)).row();

            tablaStats.add(new Label("⏱️ Tiempo Total Jugado:", skin));
            float tiempoEnMinutos = usuarioActivo.getTiempoTotalJugado() / 60f;
            tablaStats.add(new Label(String.format("%.2f min", tiempoEnMinutos), skin)).row();

            root.add(tablaStats).padBottom(20).row();

            Label lblHistorial = new Label("Historial Reciente de Partidas:", skin);
            root.add(lblHistorial).padBottom(10).row();

            List<String> listaHistorial = new List<>(skin);

            if (usuarioActivo.getHistorialPartidas().isEmpty()) {
                String[] vacio = {"No hay partidas registradas aun. ¡A jugar!"};
                listaHistorial.setItems(vacio);
            } else {
                String[] items = usuarioActivo.getHistorialPartidas().toArray(new String[0]);
                listaHistorial.setItems(items);
            }

            ScrollPane scrollPane = new ScrollPane(listaHistorial, skin);
            scrollPane.setFadeScrollBars(false);
            root.add(scrollPane).width(500).height(150).padBottom(25).row();

        } else {
            root.add(new Label("No se detecto un perfil activo.", skin)).padBottom(20).row();
        }

        TextButton btnVolver = new TextButton("VOLVER AL MENU", skin);
        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MainMenuScreen(parentGame));
            }
        });
        root.add(btnVolver).width(200).height(40).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.4f, 0.25f, 1); 
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
