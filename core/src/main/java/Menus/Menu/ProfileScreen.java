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
import com.tusderechos.Juego.graficos.RutasAssetsIdioma;
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
    private Texture btnDesactivarTex;
    private Texture fondoAlertaTex; 
    private Texture btnSeguirTex;
    private Texture btnSiguiendoTex;
    private Texture btnRetarTex;
    private BitmapFont fuenteDatos;

    private Table capaAlertaModal;

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

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        fondoPerfilTexture = new Texture(Gdx.files.internal(ObtenerRutaFondoPerfil(perfilPropio, idm)));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        fotoPerfilTex = CargarFotoPerfil(usuarioPerfil);
        btnDesactivarTex = new Texture(Gdx.files.internal("imgMenus/btn_desactivar.png"));
        btnSeguirTex = new Texture(Gdx.files.internal(RutasAssetsIdioma.ObtenerRutaBoton("seguir")));
        btnSiguiendoTex = new Texture(Gdx.files.internal(RutasAssetsIdioma.ObtenerRutaBoton("siguiendo")));
        btnRetarTex = new Texture(Gdx.files.internal(RutasAssetsIdioma.ObtenerRutaBoton("retar")));
        fondoAlertaTex = new Texture(Gdx.files.internal("imgMenus/fondo_alerta.png")); 

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoPerfilTexture)));
        stage.addActor(rootTable);

        Table capaEsquinaSuperior = new Table();
        capaEsquinaSuperior.setFillParent(true);
        capaEsquinaSuperior.top().right().padTop(110).padRight(35);

        if (perfilPropio && usuarioPerfil != null) {
            ImageButton btnDesactivar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnDesactivarTex)));
            btnDesactivar.getImage().setScaling(Scaling.fill);
            btnDesactivar.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (capaAlertaModal != null) {
                        capaAlertaModal.setVisible(true);
                    }
                }
            });
            capaEsquinaSuperior.add(btnDesactivar).width(75).height(75);
        }
        stage.addActor(capaEsquinaSuperior);

        Table contenedorCentral = new Table();
        contenedorCentral.top();
        rootTable.add(contenedorCentral).expand().fill();

        Label.LabelStyle estiloDatos = CrearEstiloDatos();
        contenedorCentral.add().height(190).row();

        if (usuarioPerfil != null) {
            AgregarDatosPerfil(contenedorCentral, usuarioPerfil, estiloDatos);
            if (!perfilPropio) {
                AgregarAccionesSociales(contenedorCentral, usuarioActivo, usuarioPerfil, estiloDatos);
            }
        } else {
            Label lblError = new Label("PERFIL NO ENCONTRADO / PROFILE NOT FOUND", estiloDatos);
            lblError.setColor(Color.RED);
            contenedorCentral.add(lblError).padTop(150).row();
        }

        contenedorCentral.add().expandY();
        contenedorCentral.row();
        AgregarBotonVolver(contenedorCentral);

        if (perfilPropio && usuarioActivo != null) {
            CrearVentanaAlertaDesactivar(usuarioActivo, estiloDatos);
        }
    }

    private void CrearVentanaAlertaDesactivar(final Usuario usuarioActivo, Label.LabelStyle estiloDatos) {
        capaAlertaModal = new Table();
        capaAlertaModal.setFillParent(true);
        capaAlertaModal.center();
        capaAlertaModal.setVisible(false);

        Table miniVentana = new Table();
        miniVentana.setBackground(new TextureRegionDrawable(new TextureRegion(fondoAlertaTex)));
        miniVentana.pad(40); 

        String textoPregunta;
        String textoSi;
        String textoNo;

        String idioma = ConfiguracionJuego.idiomaActivo.toUpperCase();
        switch (idioma) {
            case "ENG":
                textoPregunta = "Are you sure you want to\ndeactivate your account?";
                textoSi = "Yes";
                textoNo = "No";
                break;
            case "FRA":
                textoPregunta = "ÃŠtes-vous sÃ»r de vouloir\ndÃ©sactiver votre compte?";
                textoSi = "Oui";
                textoNo = "Non";
                break;
            case "HEB":
                textoPregunta = "?×”×× ××ª×” ×‘×˜×•×— ×©×‘×¨×¦×•× ×š\n×œ× ×˜×¨×œ ××ª ×”×—×©×‘×•×Ÿ ×©×œ×š";
                textoSi = "×›×Ÿ";
                textoNo = "×œ×";
                break;
            case "GAR":
                textoPregunta = "Â¿Afurati boun bigira\nadisidagwda humoun bubi?"; 
                textoSi = "Inje";
                textoNo = "Ino";
                break;
            case "ESP":
            default:
                textoPregunta = "Â¿EstÃ¡ seguro de que desea\ndesactivar la cuenta?";
                textoSi = "SÃ­";
                textoNo = "No";
                break;
        }

        Label.LabelStyle estiloAlerta = new Label.LabelStyle();
        estiloAlerta.font = fuenteDatos; 
        estiloAlerta.fontColor = Color.BLACK; 

        Label lblPregunta = new Label(textoPregunta, estiloAlerta);
        lblPregunta.setAlignment(Align.center);
        
        lblPregunta.setFontScale(1.3f); 

        miniVentana.add(lblPregunta).padTop(10).padBottom(25).colspan(2).center().row();

        TextButton btnSi = new TextButton(textoSi, skin);
        btnSi.getLabel().setFontScale(1.15f);
        btnSi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                usuarioActivo.setCuentaActiva(false);
                ManejadorArchivos.guardarUsuario(usuarioActivo);
                SistemaAutenticacion.cerrarSesion();
                parentGame.setScreen(new LoginRegisterScreen(parentGame));
            }
        });

        TextButton btnNo = new TextButton(textoNo, skin);
        btnNo.getLabel().setFontScale(1.15f); 
        btnNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                capaAlertaModal.setVisible(false);
            }
        });

        miniVentana.add(btnSi).width(130).height(48).padRight(15);
        miniVentana.add(btnNo).width(130).height(48).padLeft(15);

        capaAlertaModal.add(miniVentana).width(460).height(320).center();
        stage.addActor(capaAlertaModal);
    }


    private String ObtenerRutaFondoPerfil(boolean perfilPropio, String idm) {
        if (perfilPropio) {
            return "imgMenus/fondo_perfil_" + idm + ".png";
        }

        return RutasAssetsIdioma.ObtenerRutaFondoPerfilAjeno();
    }

    private ImageButton CrearBotonImagen(Texture TexturaBoton) {
        ImageButton Boton = new ImageButton(new TextureRegionDrawable(new TextureRegion(TexturaBoton)));
        Boton.getImage().setScaling(Scaling.fill);
        Boton.getImage().setAlign(Align.center);

        return Boton;
    }

    private String ObtenerTextoRivalidadPendiente() {
        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                return "Rivalry available when both follow each other";
            case "FRA":
                return "Rivalite disponible quand les deux se suivent";
            case "GAR":
                return "Rivalidad disponible cuando ambos se sigan";
            case "HEB":
                return "Rivalidad disponible cuando ambos se sigan";
            default:
                return "Rivalidad disponible cuando ambos se sigan";
        }
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
        contenedorCentral.add(lblUser).height(45).center().row();
        contenedorCentral.add().height(50).row();

        Label lblNombre = new Label(usuarioPerfil.getNombreCompleto(), estiloDatos);
        contenedorCentral.add(lblNombre).height(45).center().row();
        contenedorCentral.add().height(50).row();

        Label lblFecha = new Label(usuarioPerfil.getFechaIngreso(), estiloDatos);
        contenedorCentral.add(lblFecha).height(45).center().row();
    }

    private void AgregarAccionesSociales(Table contenedorCentral, final Usuario usuarioActivo, final Usuario usuarioPerfil, Label.LabelStyle estiloDatos) {
        contenedorCentral.add().height(28).row();

        Texture TexturaSeguir = GestorSeguimiento.YaSigue(usuarioActivo, usuarioPerfil) ? btnSiguiendoTex : btnSeguirTex;
        ImageButton btnSeguir = CrearBotonImagen(TexturaSeguir);
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
        contenedorCentral.add(btnSeguir).width(210).height(50).center().row();
        contenedorCentral.add().height(18).row();

        if (GestorSeguimiento.SonRivalesMutuos(usuarioActivo, usuarioPerfil)) {
            ImageButton btnReto = CrearBotonImagen(btnRetarTex);
            btnReto.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    parentGame.setScreen(new PantallaRivalidad((Juego) parentGame, ColorDulce.Rojo, ColorMonstruo.Verde, usuarioPerfil.getUsername()));
                }
            });
            contenedorCentral.add(btnReto).width(210).height(50).center().row();
        } else {
            Label lblRivalidad = new Label(ObtenerTextoRivalidadPendiente(), estiloDatos);
            lblRivalidad.setFontScale(0.72f);
            lblRivalidad.setAlignment(Align.center);
            contenedorCentral.add(lblRivalidad).width(340).height(36).center().row();
        }
    }

    private String TextoBotonSeguir(Usuario usuarioActivo, Usuario usuarioPerfil) {
        boolean esEng = ConfiguracionJuego.idiomaActivo.equalsIgnoreCase("ENG");
        if (usuarioActivo == null) {
            return esEng ? "Log In" : "Inicia sesion";
        }
        if (GestorSeguimiento.YaSigue(usuarioActivo, usuarioPerfil)) {
            return esEng ? "Following" : "Siguiendo";
        }
        return esEng ? "Follow" : "Seguir";
    }

    private void AgregarBotonVolver(Table contenedorCentral) {
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
        skin.dispose();
        if (fondoPerfilTexture != null) {
            fondoPerfilTexture.dispose();
        }
        if (btnVolverTex != null) {
            btnVolverTex.dispose();
        }
        if (btnDesactivarTex != null) {
            btnDesactivarTex.dispose();
        }
        if (btnSeguirTex != null) {
            btnSeguirTex.dispose();
        }
        if (btnSiguiendoTex != null) {
            btnSiguiendoTex.dispose();
        }
        if (btnRetarTex != null) {
            btnRetarTex.dispose();
        }
        if (fotoPerfilTex != null) {
            fotoPerfilTex.dispose();
        }
        if (fuenteDatos != null) {
            fuenteDatos.dispose();
        }
        if (fondoAlertaTex != null) {
            fondoAlertaTex.dispose(); 
        }
    }
}




