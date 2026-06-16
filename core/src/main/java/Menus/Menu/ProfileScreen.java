/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;
import ManejoArchivos.Archivos.ManejadorArchivos;
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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.pantallas.PantallaRivalidad;
import com.tusderechos.Juego.social.GestorSeguimiento;

/**
 *
 * @author HP
 */
public class ProfileScreen implements Screen {

    private final Game parentGame;
    private final String usernamePerfil;
    private Stage stage;
    private Skin skin;
    private Texture fondoPerfilTexture;
    private Texture btnVolverTex;
    private Texture fotoPerfilTex;
    private Texture RetarTexture;
    private Texture SeguirTexture;
    private Texture SiguiendoTexture;
    private BitmapFont fuenteDatos;

    public ProfileScreen(Game game) {
        this(game, null);
    }

    public ProfileScreen(Game game, String usernamePerfil) {
        this.parentGame = game;
        this.usernamePerfil = usernamePerfil;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();
        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        Usuario usuarioPerfil = ObtenerUsuarioPerfil(usuarioActivo);
        boolean perfilPropio = EsPerfilPropio(usuarioActivo, usuarioPerfil);

        fondoPerfilTexture = new Texture(Gdx.files.internal(ObtenerRutaFondoPerfil(perfilPropio)));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        fotoPerfilTex = CargarFotoPerfil(usuarioPerfil);
        if (!perfilPropio) {
            RetarTexture = new Texture(Gdx.files.internal("imagenes/retar.png"));
            SeguirTexture = new Texture(Gdx.files.internal("imagenes/seguir.png"));
            SiguiendoTexture = new Texture(Gdx.files.internal("imagenes/siguiendo.png"));
        }

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoPerfilTexture)));
        stage.addActor(rootTable);

        Table contenedorCentral = new Table();
        contenedorCentral.top();
        rootTable.add(contenedorCentral).expand().fill();

        Label.LabelStyle estiloDatos = CrearEstiloDatos();
        contenedorCentral.add().height(perfilPropio ? 190 : 208).row();

        if (usuarioPerfil != null) {
            AgregarDatosPerfil(contenedorCentral, usuarioPerfil, estiloDatos);
            if (!perfilPropio) {
                AgregarAccionesSociales(contenedorCentral, usuarioActivo, usuarioPerfil, estiloDatos);
            }
        } else {
            Label lblError = new Label("PERFIL NO ENCONTRADO", estiloDatos);
            lblError.setColor(Color.RED);
            contenedorCentral.add(lblError).padTop(150).row();
        }

        contenedorCentral.add().expandY();
        AgregarBotonVolver();
    }

    private Usuario ObtenerUsuarioPerfil(Usuario usuarioActivo) {
        if (usernamePerfil == null || usernamePerfil.trim().isEmpty()) {
            return usuarioActivo;
        }

        return ManejadorArchivos.cargarUsuario(usernamePerfil.trim().toLowerCase());
    }

    private boolean EsPerfilPropio(Usuario usuarioActivo, Usuario usuarioPerfil) {
        return usuarioActivo != null
                && usuarioPerfil != null
                && usuarioActivo.getUsername().equalsIgnoreCase(usuarioPerfil.getUsername());
    }

    private String ObtenerRutaFondoPerfil(boolean PerfilPropio) {
        return PerfilPropio ? "imgMenus/fondo_perfil.png" : "imagenes/fondo_perfil_ajeno.PNG";
    }

    private Texture CargarFotoPerfil(Usuario usuarioPerfil) {
        if (usuarioPerfil == null || usuarioPerfil.getRutaFotoPerfil() == null || usuarioPerfil.getRutaFotoPerfil().trim().isEmpty()) {
            return new Texture(Gdx.files.internal("imgMenus/avatar1.png"));
        }

        String ruta = usuarioPerfil.getRutaFotoPerfil();
        if (ruta.startsWith("imgMenus")) {
            return new Texture(Gdx.files.internal(ruta));
        }

        return new Texture(Gdx.files.absolute(ruta));
    }

    private Label.LabelStyle CrearEstiloDatos() {
        Label.LabelStyle estiloBase = skin.get(Label.LabelStyle.class);
        Label.LabelStyle estiloDatos = new Label.LabelStyle(estiloBase);
        if (estiloBase.font != null) {
            fuenteDatos = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteDatos.getData().setScale(1.6f);
            estiloDatos.font = fuenteDatos;
            estiloDatos.fontColor = Color.WHITE;
        }

        return estiloDatos;
    }

    private void AgregarDatosPerfil(Table contenedorCentral, Usuario usuarioPerfil, Label.LabelStyle estiloDatos) {
        Image imgFoto = new Image(fotoPerfilTex);
        imgFoto.setScaling(Scaling.fill);
        contenedorCentral.add(imgFoto).width(120).height(120).center().row();
        contenedorCentral.add().height(55).row();

        Label lblUser = new Label(usuarioPerfil.getUsername(), estiloDatos);
        lblUser.setAlignment(Align.center);
        contenedorCentral.add(lblUser).width(340).height(45).center().row();
        contenedorCentral.add().height(50).row();

        Label lblNombre = new Label(usuarioPerfil.getNombreCompleto(), estiloDatos);
        lblNombre.setAlignment(Align.center);
        contenedorCentral.add(lblNombre).width(340).height(45).center().row();
        contenedorCentral.add().height(50).row();

        Label lblFecha = new Label(usuarioPerfil.getFechaIngreso(), estiloDatos);
        lblFecha.setAlignment(Align.center);
        contenedorCentral.add(lblFecha).width(260).height(45).center().row();
    }

    private void AgregarAccionesSociales(Table contenedorCentral, final Usuario usuarioActivo, final Usuario usuarioPerfil, Label.LabelStyle estiloDatos) {
        contenedorCentral.add().height(28).row();

        Button btnSeguir = CrearBotonSeguimiento(usuarioActivo, usuarioPerfil);
        btnSeguir.setDisabled(!GestorSeguimiento.PuedeSeguir(usuarioActivo, usuarioPerfil));
        btnSeguir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GestorSeguimiento.SeguirUsuario(usuarioActivo, usuarioPerfil)) {
                    ManejadorArchivos.guardarUsuario(usuarioActivo);
                    parentGame.setScreen(new ProfileScreen(parentGame, usuarioPerfil.getUsername()));
                }
            }
        });
        contenedorCentral.add(btnSeguir).width(210).height(44).center().row();
        contenedorCentral.add().height(18).row();

        if (GestorSeguimiento.SonRivalesMutuos(usuarioActivo, usuarioPerfil)) {
            ImageButton btnReto = new ImageButton(new TextureRegionDrawable(new TextureRegion(RetarTexture)));
            btnReto.getImage().setScaling(Scaling.fill);
            btnReto.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parentGame.setScreen(new PantallaRivalidad((Juego) parentGame, ColorDulce.Rojo, ColorMonstruo.Verde, usuarioPerfil.getUsername()));
                }
            });
            contenedorCentral.add(btnReto).width(210).height(44).center().row();
        } else {
            Label lblRivalidad = new Label("Deben seguirse para retarse", estiloDatos);
            lblRivalidad.setAlignment(Align.center);
            lblRivalidad.setFontScale(1.0f);
            Table FondoAvisoRivalidad = new Table();
            FondoAvisoRivalidad.setBackground(skin.newDrawable("fondoCampo", new Color(0.04f, 0.09f, 0.07f, 0.88f)));
            FondoAvisoRivalidad.add(lblRivalidad).width(300).height(34).center();
            contenedorCentral.add(FondoAvisoRivalidad).width(330).height(42).center().row();
        }
    }

    private String TextoBotonSeguir(Usuario usuarioActivo, Usuario usuarioPerfil) {
        if (usuarioActivo == null) {
            return "Inicia sesion";
        }

        if (GestorSeguimiento.YaSigue(usuarioActivo, usuarioPerfil)) {
            return "Siguiendo";
        }

        return "Seguir";
    }

    private Button CrearBotonSeguimiento(Usuario usuarioActivo, Usuario usuarioPerfil) {
        if (usuarioActivo == null) {
            TextButton BotonSeguimientoTexto = new TextButton(TextoBotonSeguir(usuarioActivo, usuarioPerfil), skin);
            BotonSeguimientoTexto.getLabel().setAlignment(Align.center);
            return BotonSeguimientoTexto;
        }
        Texture TexturaSeguimiento = GestorSeguimiento.YaSigue(usuarioActivo, usuarioPerfil) ? SiguiendoTexture : SeguirTexture;
        if (TexturaSeguimiento != null) {
            ImageButton BotonSeguimiento = new ImageButton(new TextureRegionDrawable(new TextureRegion(TexturaSeguimiento)));
            BotonSeguimiento.getImage().setScaling(Scaling.fill);
            return BotonSeguimiento;
        }

        TextButton BotonSeguimientoTexto = new TextButton(TextoBotonSeguir(usuarioActivo, usuarioPerfil), skin);
        BotonSeguimientoTexto.getLabel().setAlignment(Align.center);
        return BotonSeguimientoTexto;
    }

    private void AgregarBotonVolver() {
        ImageButton btnVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnVolverTex)));
        btnVolver.getImage().setScaling(Scaling.fill);
        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MainMenuScreen(parentGame));
            }
        });

        Table filaInferior = new Table();
        filaInferior.setFillParent(true);
        filaInferior.bottom().left();
        filaInferior.add(btnVolver).width(55).height(55).padLeft(22).padBottom(22);
        stage.addActor(filaInferior);
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
        skin.dispose();
        fondoPerfilTexture.dispose();
        btnVolverTex.dispose();
        if (fotoPerfilTex != null) {
            fotoPerfilTex.dispose();
        }
        if (RetarTexture != null) {
            RetarTexture.dispose();
        }
        if (SeguirTexture != null) {
            SeguirTexture.dispose();
        }
        if (SiguiendoTexture != null) {
            SiguiendoTexture.dispose();
        }
        if (fuenteDatos != null) {
            fuenteDatos.dispose();
        }
    }
}
