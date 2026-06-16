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
public class MenuInicioScreen implements Screen {

    private final Game parentGame;
    private Stage stage;

    private Texture fondoTexture;
    private Texture btnLoginTexture;
    private Texture btnRegisterTexture;
    private Texture btnSalirTexture;

    public MenuInicioScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        fondoTexture = new Texture(Gdx.files.internal("imgMenus/fondo_auth.png"));
        btnLoginTexture = new Texture(Gdx.files.internal("imgMenus/btn_iniciar_sesion_" + idm + ".png"));
        btnRegisterTexture = new Texture(Gdx.files.internal("imgMenus/btn_crear_cuenta_" + idm + ".png"));
        btnSalirTexture = new Texture(Gdx.files.internal("imgMenus/btn_salir_" + idm + ".png"));

        TextureRegionDrawable drawableLogin = new TextureRegionDrawable(new TextureRegion(btnLoginTexture));
        TextureRegionDrawable drawableRegister = new TextureRegionDrawable(new TextureRegion(btnRegisterTexture));
        TextureRegionDrawable drawableSalir = new TextureRegionDrawable(new TextureRegion(btnSalirTexture));

        ImageButton btnIniciarSesion = new ImageButton(drawableLogin);
        ImageButton btnCrearCuenta = new ImageButton(drawableRegister);
        ImageButton btnSalir = new ImageButton(drawableSalir);

        Table root = new Table();
        root.setFillParent(true);
        root.center(); 
        root.padTop(150); 
        stage.addActor(root);

        float anchoGrande = 310; 
        float altoGrande = 76;   
        float anchoChico = 155;  
        float altoChico = 58;    
        float espaciado = 14;    

        btnIniciarSesion.getStyle().imageUp.setMinWidth(anchoGrande);
        btnIniciarSesion.getStyle().imageUp.setMinHeight(altoGrande);
        
        btnCrearCuenta.getStyle().imageUp.setMinWidth(anchoGrande);
        btnCrearCuenta.getStyle().imageUp.setMinHeight(altoGrande);
        
        btnSalir.getStyle().imageUp.setMinWidth(anchoChico);
        btnSalir.getStyle().imageUp.setMinHeight(altoChico);

        btnIniciarSesion.getImage().setScaling(com.badlogic.gdx.utils.Scaling.fill);
        btnCrearCuenta.getImage().setScaling(com.badlogic.gdx.utils.Scaling.fill);
        btnSalir.getImage().setScaling(com.badlogic.gdx.utils.Scaling.fill);

        root.add(btnIniciarSesion).width(anchoGrande).height(altoGrande).padBottom(espaciado).row();
        root.add(btnCrearCuenta).width(anchoGrande).height(altoGrande).padBottom(espaciado).row();
        root.add(btnSalir).width(anchoChico).height(altoChico).row();

        btnIniciarSesion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new LoginRegisterScreen(parentGame));
            }
        });

        btnCrearCuenta.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new RegisterScreen(parentGame));
            }
        });

        btnSalir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new StartScreen(parentGame));
            }
        });
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
        if (btnLoginTexture != null) {
            btnLoginTexture.dispose();
        }
        if (btnRegisterTexture != null) {
            btnRegisterTexture.dispose();
        }
        if (btnSalirTexture != null) {
            btnSalirTexture.dispose();
        }
    }
}
