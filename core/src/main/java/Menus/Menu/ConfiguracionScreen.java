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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 *
 * @author HP
 */
public class ConfiguracionScreen implements Screen {
    private final Game parentGame;
    private Stage stage;
    private Skin skin;
    private Texture fondoConfig;

    private Texture texFlechaIzq, texFlechaDer;
    private Texture texMouseNormal, texMouseActivo;
    private Texture texTecladoNormal, texTecladoActivo;
    private Texture texVolverNormal;

    private TextureRegionDrawable flechaIzq, flechaDer;
    private TextureRegionDrawable mouseNormal, mouseActivo;
    private TextureRegionDrawable tecladoNormal, tecladoActivo;
    private TextureRegionDrawable btnVolverNormal;

    private Label lblIdiomaSeleccionado;
    private ImageButton btnMouse;
    private ImageButton btnTeclado;

    private final String[] idiomas = {"ESP", "ENG", "GAR", "FRA", "HEB"};
    private int indiceIdiomaActual = 0;

    public ConfiguracionScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(480, 800));
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();
        fondoConfig = new Texture(Gdx.files.internal("imgMenus/fondo_configuracion_" + idm + ".png"));

        texFlechaIzq = new Texture(Gdx.files.internal("imgMenus/btn_flecha_izq.png"));
        flechaIzq = new TextureRegionDrawable(new TextureRegion(texFlechaIzq));

        texFlechaDer = new Texture(Gdx.files.internal("imgMenus/btn_flecha_der.png"));
        flechaDer = new TextureRegionDrawable(new TextureRegion(texFlechaDer));

        texMouseNormal = new Texture(Gdx.files.internal("imgMenus/btn_mouse_normal.png"));
        mouseNormal = new TextureRegionDrawable(new TextureRegion(texMouseNormal));

        texMouseActivo = new Texture(Gdx.files.internal("imgMenus/btn_mouse_act.png"));
        mouseActivo = new TextureRegionDrawable(new TextureRegion(texMouseActivo));

        texTecladoNormal = new Texture(Gdx.files.internal("imgMenus/btn_teclado_normal.png"));
        tecladoNormal = new TextureRegionDrawable(new TextureRegion(texTecladoNormal));

        texTecladoActivo = new Texture(Gdx.files.internal("imgMenus/btn_teclado_act.png"));
        tecladoActivo = new TextureRegionDrawable(new TextureRegion(texTecladoActivo));

        texVolverNormal = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        btnVolverNormal = new TextureRegionDrawable(new TextureRegion(texVolverNormal));

        for (int i = 0; i < idiomas.length; i++) {
            if (idiomas[i].equals(ConfiguracionJuego.idiomaActivo)) {
                indiceIdiomaActual = i;
                break;
            }
        }

        construirInterfaz();
    }

    private void construirInterfaz() {
        stage.clear(); 

        Table tablaComponentes = new Table();
        tablaComponentes.setFillParent(true);
        tablaComponentes.top().right();
        tablaComponentes.padTop(270).padRight(35);

        final Slider sliderVolumen = new Slider(0f, 1f, 0.05f, false, skin);
        sliderVolumen.setValue(ConfiguracionJuego.volumenGeneral);
        sliderVolumen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ConfiguracionJuego.guardarVolumen(sliderVolumen.getValue());
            }
        });
        tablaComponentes.add(sliderVolumen).width(210).height(30).center().row();

        tablaComponentes.add().height(65).row();

        Table filaIdioma = new Table();

        ImageButton btnIzq = new ImageButton(flechaIzq);
        ImageButton btnDer = new ImageButton(flechaDer);

        lblIdiomaSeleccionado = new Label(idiomas[indiceIdiomaActual], skin);
        lblIdiomaSeleccionado.setAlignment(com.badlogic.gdx.utils.Align.center);

        btnIzq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceIdiomaActual--;
                if (indiceIdiomaActual < 0) {
                    indiceIdiomaActual = idiomas.length - 1;
                }
                actualizarIdiomaGlobal();
            }
        });

        btnDer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceIdiomaActual++;
                if (indiceIdiomaActual >= idiomas.length) {
                    indiceIdiomaActual = 0;
                }
                actualizarIdiomaGlobal();
            }
        });

        filaIdioma.add(btnIzq).width(30).height(30).padRight(15);
        filaIdioma.add(lblIdiomaSeleccionado).width(110).center();
        filaIdioma.add(btnDer).width(30).height(30).padLeft(15);

        tablaComponentes.add(filaIdioma).width(210).center().row();

        tablaComponentes.add().height(60).row();

        Table filaControles = new Table();

        btnMouse = new ImageButton(mouseNormal);
        btnTeclado = new ImageButton(tecladoNormal);

        actualizarEstadoVisualControles();

        btnMouse.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfiguracionJuego.guardarControl("MOUSE");
                actualizarEstadoVisualControles();
            }
        });

        btnTeclado.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfiguracionJuego.guardarControl("TECLADO");
                actualizarEstadoVisualControles();
            }
        });

        filaControles.add(btnMouse).width(110).height(42).padRight(5);
        filaControles.add(btnTeclado).width(110).height(42);

        tablaComponentes.add(filaControles).width(210).center().row();

        stage.addActor(tablaComponentes);

        ImageButton btnVolver = new ImageButton(btnVolverNormal);
        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MainMenuScreen(parentGame));
            }
        });

        Table tablaVolver = new Table();
        tablaVolver.setFillParent(true);
        tablaVolver.left().bottom().padLeft(25).padBottom(25);
        tablaVolver.add(btnVolver).width(55).height(55);

        stage.addActor(tablaVolver);
    }

    private void actualizarIdiomaGlobal() {
        String seleccion = idiomas[indiceIdiomaActual];
        ConfiguracionJuego.guardarIdioma(seleccion);

        if (fondoConfig != null) {
            fondoConfig.dispose();
        }
        fondoConfig = new Texture(Gdx.files.internal("imgMenus/fondo_configuracion_" + seleccion.toLowerCase() + ".png"));

        construirInterfaz();
    }

    private void actualizarEstadoVisualControles() {
        if (ConfiguracionJuego.modoControl.equals("MOUSE")) {
            btnMouse.getStyle().imageUp = mouseActivo;
            btnTeclado.getStyle().imageUp = tecladoNormal;
        } else {
            btnMouse.getStyle().imageUp = mouseNormal;
            btnTeclado.getStyle().imageUp = tecladoActivo;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        if (fondoConfig != null) {
            stage.getBatch().draw(fondoConfig, 0, 0, 480, 800);
        }
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

        if (fondoConfig != null) {
            fondoConfig.dispose();
        }
        if (texFlechaIzq != null) {
            texFlechaIzq.dispose();
        }
        if (texFlechaDer != null) {
            texFlechaDer.dispose();
        }
        if (texMouseNormal != null) {
            texMouseNormal.dispose();
        }
        if (texMouseActivo != null) {
            texMouseActivo.dispose();
        }
        if (texTecladoNormal != null) {
            texTecladoNormal.dispose();
        }
        if (texTecladoActivo != null) {
            texTecladoActivo.dispose();
        }
        if (texVolverNormal != null) {
            texVolverNormal.dispose();
        }
    }
}
