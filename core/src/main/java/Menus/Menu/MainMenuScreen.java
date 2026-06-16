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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.pantallas.PantallaSeleccionNivel;
import LogicaArchivos.Usuarios.SistemaAutenticacion;

/**
 *
 * @author HP
 */
public class MainMenuScreen implements Screen {

    /*private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Texture fondoMenuTex;
    private Texture btnJugarTex;
    private Texture btnPerfilTex;
    private Texture btnBuscarJugadoresTex;
    private Texture btnRetosTex;
    private Texture btnEstadisticasTex;
    private Texture btnCerrarSesionTex;
    private Texture btnConfigTex;

    public MainMenuScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        fondoMenuTex = new Texture(Gdx.files.internal("imgMenus/fondo_menu_principal.png"));
        btnJugarTex = new Texture(Gdx.files.internal("imgMenus/btn_juego.png"));
        btnPerfilTex = new Texture(Gdx.files.internal("imgMenus/btn_perfil.png"));
        btnBuscarJugadoresTex = new Texture(Gdx.files.internal("imagenes/buscar_jugadores.png"));
        btnRetosTex = new Texture(Gdx.files.internal("imagenes/retos.png"));
        btnEstadisticasTex = new Texture(Gdx.files.internal("imgMenus/btn_estadisticas.png"));
        btnCerrarSesionTex = new Texture(Gdx.files.internal("imgMenus/btn_cerrar_sesion.png"));
        btnConfigTex = new Texture(Gdx.files.internal("imgMenus/btn_config.png"));

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoMenuTex)));
        stage.addActor(rootTable);

        ImageButton btnConfig = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnConfigTex)));
        btnConfig.getImage().setScaling(Scaling.fill);
        btnConfig.setSize(65, 65);

        btnConfig.setPosition(stage.getWidth() - btnConfig.getWidth() - 20, stage.getHeight() - btnConfig.getHeight() - 20);

        btnConfig.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ConfiguracionScreen(parentGame));
            }
        });

        stage.addActor(btnConfig);

        Table contenedorBotones = new Table();
        contenedorBotones.top();

        ImageButton btnJugar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnJugarTex)));
        ImageButton btnPerfil = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnPerfilTex)));
        ImageButton btnBuscarJugadores = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnBuscarJugadoresTex)));
        ImageButton btnRetos = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnRetosTex)));
        ImageButton btnEstadisticas = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnEstadisticasTex)));
        ImageButton btnCerrarSesion = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnCerrarSesionTex)));

        btnJugar.getImage().setScaling(Scaling.fill);
        btnPerfil.getImage().setScaling(Scaling.fill);
        btnBuscarJugadores.getImage().setScaling(Scaling.fill);
        btnRetos.getImage().setScaling(Scaling.fill);
        btnEstadisticas.getImage().setScaling(Scaling.fill);
        btnCerrarSesion.getImage().setScaling(Scaling.fill);

        btnJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new PantallaSeleccionNivel((com.tusderechos.Juego.Juego) parentGame));
            }
        });

        btnPerfil.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ProfileScreen(parentGame));
            }
        });

        btnBuscarJugadores.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new BuscarJugadoresScreen(parentGame));
            }
        });

        btnRetos.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new PantallaSolicitudesRivalidad(parentGame));
            }
        });

        btnEstadisticas.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new StatsScreen(parentGame));
            }
        });

        btnCerrarSesion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SistemaAutenticacion.cerrarSesion();
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });

        contenedorBotones.add(btnJugar).width(245).height(50).padTop(285).padRight(5).row();
        contenedorBotones.add(btnPerfil).width(245).height(50).padTop(18).padRight(5).row();
        contenedorBotones.add(btnBuscarJugadores).width(245).height(50).padTop(18).padRight(5).row();
        contenedorBotones.add(btnRetos).width(245).height(50).padTop(16).padRight(5).row();
        contenedorBotones.add(btnEstadisticas).width(245).height(50).padTop(16).padRight(5).row();
        contenedorBotones.add(btnCerrarSesion).width(245).height(50).padTop(16).padRight(5).row();

        rootTable.add(contenedorBotones).expand().top();
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
        for (com.badlogic.gdx.scenes.scene2d.Actor actor : stage.getActors()) {
            if (actor instanceof ImageButton && actor != null && actor.getWidth() == 65) {
                actor.setPosition(width - actor.getWidth() - 20, height - actor.getHeight() - 20);
            }
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        fondoMenuTex.dispose();
        btnJugarTex.dispose();
        btnPerfilTex.dispose();
        btnBuscarJugadoresTex.dispose();
        btnRetosTex.dispose();
        btnEstadisticasTex.dispose();
        btnCerrarSesionTex.dispose();
        btnConfigTex.dispose();
    }*/

    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Texture fondoMenuTex;
    private Texture btnJugarTex;
    private Texture btnPerfilTex;
    private Texture btnBuscarJugadoresTex;
    private Texture btnEstadisticasTex;
    private Texture btnCerrarSesionTex;
    private Texture btnConfigTex;

    public MainMenuScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        //fondoMenuTex = new Texture(Gdx.files.internal("imgMenus/fondo_menu_principal_" + idm + ".png"));
        fondoMenuTex = new Texture(Gdx.files.internal("imgMenus/fondo_menu_principal.png"));
        btnJugarTex = new Texture(Gdx.files.internal("imgMenus/btn_juego_" + idm + ".png"));
        btnPerfilTex = new Texture(Gdx.files.internal("imgMenus/btn_perfil_" + idm + ".png"));
        btnEstadisticasTex = new Texture(Gdx.files.internal("imgMenus/btn_estadisticas_" + idm + ".png"));
        btnCerrarSesionTex = new Texture(Gdx.files.internal("imgMenus/btn_cerrar_sesion_" + idm + ".png"));

        btnBuscarJugadoresTex = new Texture(Gdx.files.internal("imagenes/buscar_jugadores.png"));
        btnConfigTex = new Texture(Gdx.files.internal("imgMenus/btn_config.png"));

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoMenuTex)));
        stage.addActor(rootTable);

        ImageButton btnConfig = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnConfigTex)));
        btnConfig.getImage().setScaling(Scaling.fill);
        btnConfig.setSize(65, 65);

        btnConfig.setPosition(stage.getWidth() - btnConfig.getWidth() - 20, stage.getHeight() - btnConfig.getHeight() - 20);

        btnConfig.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ConfiguracionScreen(parentGame));
            }
        });

        stage.addActor(btnConfig);

        Table contenedorBotones = new Table();
        contenedorBotones.top();

        ImageButton btnJugar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnJugarTex)));
        ImageButton btnPerfil = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnPerfilTex)));
        ImageButton btnBuscarJugadores = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnBuscarJugadoresTex)));
        ImageButton btnEstadisticas = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnEstadisticasTex)));
        ImageButton btnCerrarSesion = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnCerrarSesionTex)));

        btnJugar.getImage().setScaling(Scaling.fill);
        btnPerfil.getImage().setScaling(Scaling.fill);
        btnBuscarJugadores.getImage().setScaling(Scaling.fill);
        btnEstadisticas.getImage().setScaling(Scaling.fill);
        btnCerrarSesion.getImage().setScaling(Scaling.fill);

        btnJugar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new PantallaSeleccionNivel((com.tusderechos.Juego.Juego) parentGame));
            }
        });

        btnPerfil.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new ProfileScreen(parentGame));
            }
        });

        btnBuscarJugadores.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new BuscarJugadoresScreen(parentGame));
            }
        });

        btnEstadisticas.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new StatsScreen(parentGame));
            }
        });

        btnCerrarSesion.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SistemaAutenticacion.cerrarSesion();
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });

        contenedorBotones.add(btnJugar).width(245).height(50).padTop(315).padRight(5).row();
        contenedorBotones.add(btnPerfil).width(245).height(50).padTop(22).padRight(5).row();
        contenedorBotones.add(btnBuscarJugadores).width(245).height(50).padTop(22).padRight(5).row();
        contenedorBotones.add(btnEstadisticas).width(245).height(50).padTop(22).padRight(5).row();
        contenedorBotones.add(btnCerrarSesion).width(245).height(50).padTop(22).padRight(5).row();

        rootTable.add(contenedorBotones).expand().top();
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
        for (com.badlogic.gdx.scenes.scene2d.Actor actor : stage.getActors()) {
            if (actor instanceof ImageButton && actor != null && actor.getWidth() == 65) {
                actor.setPosition(width - actor.getWidth() - 20, height - actor.getHeight() - 20);
            }
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        if (fondoMenuTex != null) {
            fondoMenuTex.dispose();
        }
        if (btnJugarTex != null) {
            btnJugarTex.dispose();
        }
        if (btnPerfilTex != null) {
            btnPerfilTex.dispose();
        }
        if (btnBuscarJugadoresTex != null) {
            btnBuscarJugadoresTex.dispose();
        }
        if (btnEstadisticasTex != null) {
            btnEstadisticasTex.dispose();
        }
        if (btnCerrarSesionTex != null) {
            btnCerrarSesionTex.dispose();
        }
        if (btnConfigTex != null) {
            btnConfigTex.dispose();
        }
    }
}
