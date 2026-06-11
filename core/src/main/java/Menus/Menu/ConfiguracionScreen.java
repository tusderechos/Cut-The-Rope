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
/**
 *
 * @author HP
 */
public class ConfiguracionScreen implements Screen{
    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    public ConfiguracionScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label lblTitulo = new Label("CONFIGURACION DEL JUEGO", skin);
        lblTitulo.setFontScale(1.4f);
        lblTitulo.setColor(Color.GOLD);
        root.add(lblTitulo).padBottom(25).row();

        Table cajaPrincipal = new Table();
        cajaPrincipal.defaults().pad(10);

        Table configTable = new Table();
        configTable.defaults().pad(8);

        Label lblAjustes = new Label("PREFERENCIAS DE CONFIGURACION", skin);
        lblAjustes.setColor(Color.GREEN);
        configTable.add(lblAjustes).colspan(2).padBottom(15).row();

        configTable.add(new Label("Volumen Musica:", skin)).left();
        Slider sliderVolumen = new Slider(0, 100, 1, false, skin);
        sliderVolumen.setValue(80);
        configTable.add(sliderVolumen).width(160).row();

        configTable.add(new Label("Idioma del Juego:", skin)).left();
        SelectBox<String> selectIdioma = new SelectBox<>(skin);
        selectIdioma.setItems("Espanol", "English");
        configTable.add(selectIdioma).width(160).row();

        configTable.add(new Label("Controles:", skin)).left();
        SelectBox<String> selectControles = new SelectBox<>(skin);
        selectControles.setItems("Mouse / Touch", "Teclado");
        configTable.add(selectControles).width(160).row();

        cajaPrincipal.add(configTable).width(360).row();
        root.add(cajaPrincipal).padBottom(35).row();

        TextButton btnVolver = new TextButton("VOLVER AL MENU", skin);
        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MainMenuScreen(parentGame));
            }
        });
        root.add(btnVolver).width(180).height(40).row();
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
