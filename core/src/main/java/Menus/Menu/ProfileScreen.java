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
public class ProfileScreen implements Screen {
    
    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    public ProfileScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();
        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label lblTitulo = new Label("MI PERFIL", skin);
        lblTitulo.setFontScale(1.4f);
        lblTitulo.setColor(Color.GOLD);
        root.add(lblTitulo).padBottom(25).row();

        if (usuarioActivo != null) {

            Table cajaPrincipal = new Table();
            cajaPrincipal.defaults().pad(10);

            Table infoTable = new Table();
            infoTable.defaults().left().pad(6);

            Label lblDatos = new Label("INFORMACION GENERAL", skin);
            lblDatos.setColor(Color.GOLD);
            infoTable.add(lblDatos).colspan(2).padBottom(15).row();

            infoTable.add(new Label("Nombre de Usuario: ", skin)).left();
            Label lblUser = new Label(usuarioActivo.getUsername(), skin);
            lblUser.setColor(Color.CYAN);
            infoTable.add(lblUser).row();

            infoTable.add(new Label("Nombre Completo: ", skin)).left();
            Label lblNombre = new Label(usuarioActivo.getNombreCompleto(), skin);
            lblNombre.setColor(Color.WHITE);
            infoTable.add(lblNombre).row();

            infoTable.add(new Label("Fecha de Registro: ", skin)).left();
            Label lblFecha = new Label("07/06/2026", skin);
            lblFecha.setColor(Color.LIGHT_GRAY);
            infoTable.add(lblFecha).row();

            cajaPrincipal.add(infoTable).width(360).row();
            root.add(cajaPrincipal).padBottom(35).row();

        } else {
            Label lblError = new Label("No se encontro un perfil de usuario activo.", skin);
            lblError.setColor(Color.RED);
            root.add(lblError).padBottom(35).row();
        }

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
