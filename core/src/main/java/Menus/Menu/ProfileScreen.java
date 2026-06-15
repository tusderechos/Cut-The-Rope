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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
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

    private Texture fondoPerfilTexture;
    private Texture btnVolverTex;
    private Texture fotoPerfilTex;
    private BitmapFont fuenteDatos;

    public ProfileScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();
        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();

        fondoPerfilTexture = new Texture(Gdx.files.internal("imgMenus/fondo_perfil.png"));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png")); 

        if (usuarioActivo != null && usuarioActivo.getRutaFotoPerfil() != null) {
            String ruta = usuarioActivo.getRutaFotoPerfil();
            if (ruta.startsWith("imgMenus")) {
                fotoPerfilTex = new Texture(Gdx.files.internal(ruta)); 
            } else {
                fotoPerfilTex = new Texture(Gdx.files.absolute(ruta)); 
            }
        } else {
            fotoPerfilTex = new Texture(Gdx.files.internal("imgMenus/avatar1.png")); 
        }

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoPerfilTexture)));
        stage.addActor(rootTable);

        Table contenedorCentral = new Table();
        contenedorCentral.top();
        rootTable.add(contenedorCentral).expand().fill();

        Label.LabelStyle estiloBase = skin.get(Label.LabelStyle.class);
        Label.LabelStyle estiloDatos = new Label.LabelStyle(estiloBase);
        if (estiloBase.font != null) {
            fuenteDatos = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteDatos.getData().setScale(1.6f); 
            estiloDatos.font = fuenteDatos;
            estiloDatos.fontColor = Color.WHITE;
        }

        contenedorCentral.add().height(190).row();

        if (usuarioActivo != null) {

            Image imgFoto = new Image(fotoPerfilTex);
            imgFoto.setScaling(Scaling.fill);
            contenedorCentral.add(imgFoto).width(120).height(120).center().row();

            contenedorCentral.add().height(55).row();

            Label lblUser = new Label(usuarioActivo.getUsername(), estiloDatos);
            contenedorCentral.add(lblUser).height(45).center().row();

            contenedorCentral.add().height(50).row();

            Label lblNombre = new Label(usuarioActivo.getNombreCompleto(), estiloDatos);
            contenedorCentral.add(lblNombre).height(45).center().row();

            contenedorCentral.add().height(50).row();

            Label lblFecha = new Label(usuarioActivo.getFechaIngreso(), estiloDatos);
            contenedorCentral.add(lblFecha).height(45).center().row();

        } else {
            Label lblError = new Label("SIN SESIÓN ACTIVA", estiloDatos);
            lblError.setColor(Color.RED);
            contenedorCentral.add(lblError).padTop(150).row();
        }

        contenedorCentral.add().expandY();
        contenedorCentral.row();

        ImageButton btnVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnVolverTex)));
        btnVolver.getImage().setScaling(Scaling.fill);
        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MainMenuScreen(parentGame));
            }
        });

        Table filaInferior = new Table();
        filaInferior.left();
        filaInferior.add(btnVolver).width(55).height(55).padLeft(20).padBottom(20);

        contenedorCentral.add(filaInferior).fillX().left();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        fondoPerfilTexture.dispose();
        btnVolverTex.dispose();
        if (fotoPerfilTex != null) {
            fotoPerfilTex.dispose();
        }
        if (fuenteDatos != null) {
            fuenteDatos.dispose();
        }
    }
}
