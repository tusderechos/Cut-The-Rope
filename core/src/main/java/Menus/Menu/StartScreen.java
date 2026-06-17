/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author HP
 */
public class StartScreen implements Screen {

    private final Game parentGame;
    private Stage stage;

    private Texture fondoTexture;
    private Texture botonPlayTexture;

    public StartScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
       stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        fondoTexture = new Texture(Gdx.files.internal("imgMenus/fondo_inicio.png"));
        botonPlayTexture = new Texture(Gdx.files.internal("imgMenus/btn_play.png"));

        TextureRegionDrawable botonDrawable = new TextureRegionDrawable(new TextureRegion(botonPlayTexture));
        ImageButton btnPlay = new ImageButton(botonDrawable);

        Table root = new Table();
        root.setFillParent(true);
        root.bottom(); 
        stage.addActor(root);

        float tamanoBoton = 140; 
        
        root.add(btnPlay).width(tamanoBoton).height(tamanoBoton).padBottom(70).row();

        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("StartScreen", "Abriendo pantalla de autenticación...");
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });
        
        AudioManager.getInstancia().reproducirMusicaMenu();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.28f, 0.18f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(fondoTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

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
        if (fondoTexture != null) {
            fondoTexture.dispose();
        }
        if (botonPlayTexture != null) {
            botonPlayTexture.dispose();
        }
    }
}
