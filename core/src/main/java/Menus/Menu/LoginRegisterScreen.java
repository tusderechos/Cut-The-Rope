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
import ManejoArchivos.Archivos.ManejadorArchivos;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author HP
 */
public class LoginRegisterScreen implements Screen {
    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Table contenedorCentral;

    private Texture btnIngresarTex;
    private Texture btnVolverTex;
    private Texture fondoAlertaTex;

    private TextField txtLoginUser;
    private TextField txtLoginPassword;

    private Texture fondoLoginTexture;
    private Table rootTable;

    private Table contenedorFlotanteAlerta;
    private String usuarioPorActivar;

    private BitmapFont fuenteAlerta;

    public LoginRegisterScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        fondoLoginTexture = new Texture(Gdx.files.internal("imgMenus/fondo_login_" + idm + ".png"));
        btnIngresarTex = new Texture(Gdx.files.internal("imgMenus/btn_ingresar_" + idm + ".png"));

        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        fondoAlertaTex = new Texture(Gdx.files.internal("imgMenus/fondo_alerta.png"));

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        contenedorCentral = new Table();
        rootTable.add(contenedorCentral).expand().fill();

        mostrarFormularioLogin();
    }

    private void mostrarFormularioLogin() {
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoLoginTexture)));
        contenedorCentral.clearChildren();
        contenedorCentral.top();

        contenedorCentral.add().height(300).colspan(2).row();

        TextField.TextFieldStyle estiloLimpio = new TextField.TextFieldStyle(skin.get(TextField.TextFieldStyle.class));
        estiloLinter:
        estiloLimpio.background = null;
        estiloLimpio.focusedBackground = null;

        if (estiloLimpio.font != null) {
            estiloLimpio.font.getData().setScale(2.0f);
        }

        txtLoginUser = new TextField("", estiloLimpio);
        txtLoginUser.setAlignment(com.badlogic.gdx.utils.Align.center);

        contenedorCentral.add(txtLoginUser).width(310).height(65).colspan(2)
                .padTop(0).padBottom(95).row();

        txtLoginPassword = new TextField("", estiloLimpio);
        txtLoginPassword.setPasswordMode(true);
        txtLoginPassword.setPasswordCharacter('*');
        txtLoginPassword.setAlignment(com.badlogic.gdx.utils.Align.center);

        Stack contenedorPassword = new Stack();

        Table capaTexto = new Table();
        capaTexto.add(txtLoginPassword).width(310).height(65);
        contenedorPassword.add(capaTexto);

        Table capaBotonOjo = new Table();
        capaBotonOjo.right();

        Texture btnOjoTex = new Texture(Gdx.files.internal("imgMenus/ojo.png"));
        ImageButton btnMostrarPass = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnOjoTex)));
        btnMostrarPass.getImage().setScaling(Scaling.fill);

        capaBotonOjo.add(btnMostrarPass).width(35).height(35).padRight(15);
        contenedorPassword.add(capaBotonOjo);

        btnMostrarPass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                txtLoginPassword.setPasswordMode(!txtLoginPassword.isPasswordMode());
            }
        });

        contenedorCentral.add(contenedorPassword).width(310).height(65).colspan(2)
                .padTop(10).padBottom(60).row(); 

        ImageButton btnIngresar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnIngresarTex)));
        btnIngresar.getImage().setScaling(Scaling.fill);

        btnIngresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String user = txtLoginUser.getText();
                String pass = txtLoginPassword.getText();

                String lang = ConfiguracionJuego.idiomaActivo.toUpperCase();

                if (user.trim().isEmpty() || pass.trim().isEmpty()) {
                    if (lang.equals("ENG")) {
                        mostrarVentanaError("All fields are required!");
                    } else if (lang.equals("FRA")) {
                        mostrarVentanaError("Tous les champs sont obligatoires!");
                    } else if (lang.equals("GAR")) {
                        mostrarVentanaError("Sun lidan sun bikuenta mebeguñou!");
                    } else if (lang.equals("HEB")) {
                        mostrarVentanaError("כל השדות חובה!");
                    } else {
                        mostrarVentanaError("¡Todos los campos son obligatorios!");
                    }
                    return;
                }

                String resultado = SistemaAutenticacion.intentarIniciarSesion(user, pass);

                if (resultado.equals("LOGIN_EXITOSO")) {
                    Gdx.app.log("Login", "¡Acceso concedido!");
                    parentGame.setScreen(new MainMenuScreen(parentGame));
                } else if (resultado.equals("CUENTA_DESACTIVADA")) {
                    usuarioPorActivar = user;
                    crearMiniVentanaAlerta();
                } else {
                    if (lang.equals("ENG")) {
                        mostrarVentanaError("Incorrect user or password!");
                    } else if (lang.equals("FRA")) {
                        mostrarVentanaError("Utilisateur ou mot de passe incorrect!");
                    } else if (lang.equals("GAR")) {
                        mostrarVentanaError("Uá gari o bikuenta bisiama!");
                    } else if (lang.equals("HEB")) {
                        mostrarVentanaError("שם משתמש או סיסמה שגויים!");
                    } else {
                        mostrarVentanaError("¡Usuario o contraseña incorrectos!");
                    }
                }
            }
        });
        contenedorCentral.add(btnIngresar).width(135).height(60).colspan(2).center().padBottom(30).row();

        ImageButton btnVolverMenu = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnVolverTex)));
        btnVolverMenu.getImage().setScaling(Scaling.fill);
        btnVolverMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });

        contenedorCentral.add(btnVolverMenu).width(55).height(55).left().padLeft(25).padBottom(20);
        contenedorCentral.add().expandX();
    }

    private void mostrarVentanaError(String mensaje) {
        if (contenedorFlotanteAlerta != null) {
            contenedorFlotanteAlerta.remove();
        }

        contenedorFlotanteAlerta = new Table();
        contenedorFlotanteAlerta.setFillParent(true);
        contenedorFlotanteAlerta.center();

        Table cuadroInterno = new Table();
        cuadroInterno.setBackground(new TextureRegionDrawable(new TextureRegion(fondoAlertaTex)));
        cuadroInterno.pad(25);

        Label.LabelStyle estiloMsg = new Label.LabelStyle();
        Label.LabelStyle estiloBase = skin.get(Label.LabelStyle.class);

        if (estiloBase != null && estiloBase.font != null) {
            fuenteAlerta = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            for (int i = 0; i < fuenteAlerta.getRegions().size; i++) {
                fuenteAlerta.getRegions().get(i).getTexture().setFilter(
                        Texture.TextureFilter.Linear, Texture.TextureFilter.Linear
                );
            }
            fuenteAlerta.getData().setScale(1.2f);
            estiloMsg.font = fuenteAlerta;
        } else {
            estiloMsg.font = skin.getFont("default-font");
        }
        estiloMsg.fontColor = new Color(0.7f, 0.1f, 0.1f, 1f); 

        Label lblMensaje = new Label(mensaje, estiloMsg);
        lblMensaje.setAlignment(com.badlogic.gdx.utils.Align.center);
        lblMensaje.setWrap(true);
        cuadroInterno.add(lblMensaje).width(400).padBottom(25).center().row();

        String btnOkTexto;
        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                btnOkTexto = "Accept";
                break;
            case "FRA":
                btnOkTexto = "Accepter";
                break;
            case "GAR":
                btnOkTexto = "Lere";
                break;
            case "HEB":
                btnOkTexto = "אישור";
                break;
            default:
                btnOkTexto = "Aceptar";
                break;
        }

        TextButton btnOk = new TextButton(btnOkTexto, skin);
        btnOk.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                contenedorFlotanteAlerta.remove();
            }
        });

        cuadroInterno.add(btnOk).width(130).height(45).center();
        contenedorFlotanteAlerta.add(cuadroInterno).width(460).height(240);
        stage.addActor(contenedorFlotanteAlerta);
    }

    private void crearMiniVentanaAlerta() {
        if (contenedorFlotanteAlerta != null) {
            contenedorFlotanteAlerta.remove();
        }

        contenedorFlotanteAlerta = new Table();
        contenedorFlotanteAlerta.setFillParent(true);
        contenedorFlotanteAlerta.center();

        Table cuadroInterno = new Table();
        cuadroInterno.setBackground(new TextureRegionDrawable(new TextureRegion(fondoAlertaTex)));
        cuadroInterno.pad(30);

        String textoPregunta, btnSiTexto, btnNoTexto, textoExito;

        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                textoPregunta = "Your account is deactivated.\nDo you want to reactivate it?";
                btnSiTexto = "Yes";
                btnNoTexto = "No";
                textoExito = "Your account has been successfully activated!\nYou can now log in normally.";
                break;
            case "GAR":
                textoPregunta = "Desactivadu tumuti lidan bikuenta.\nBusenba bounwagua laguyuguñoun?";
                btnSiTexto = "In";
                btnNoTexto = "Ua";
                textoExito = "¡Lidounwagüili bikuenta buiti!\nHagoun bounwagua beibu luma lere.";
                break;
            case "FRA":
                textoPregunta = "Votre compte est désactivé.\nVoulez-vous le réactiver?";
                btnSiTexto = "Oui";
                btnNoTexto = "Non";
                textoExito = "Votre compte a été activé avec succès!\nVous pouvez maintenant vous connecter normalement.";
                break;
            case "HEB":
                textoPregunta = "החשבון שלך מושבת.\nהאם ברצונך להפעיל אותו מחדש?";
                btnSiTexto = "כן";
                btnNoTexto = "לא";
                textoExito = "החשבון שלך הופעל בהצלחה!\nכעת תוכל להתחבר כרגיל.";
                break;
            default:
                textoPregunta = "Tu cuenta está desactivada.\n¿Deseas activarla de nuevo?";
                btnSiTexto = "Sí";
                btnNoTexto = "No";
                textoExito = "¡Tu cuenta se ha activado con éxito!\nYa puedes iniciar sesión normalmente.";
                break;
        }

        Label.LabelStyle estiloMsg = new Label.LabelStyle();
        Label.LabelStyle estiloBase = skin.get(Label.LabelStyle.class);

        if (estiloBase != null && estiloBase.font != null) {
            fuenteAlerta = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            for (int i = 0; i < fuenteAlerta.getRegions().size; i++) {
                fuenteAlerta.getRegions().get(i).getTexture().setFilter(
                        Texture.TextureFilter.Linear, Texture.TextureFilter.Linear
                );
            }
            fuenteAlerta.getData().setScale(1.25f);
            estiloMsg.font = fuenteAlerta;
        } else {
            estiloMsg.font = skin.getFont("default-font");
        }
        estiloMsg.fontColor = new Color(0.18f, 0.14f, 0.08f, 1f);

        final Label lblMensajePregunta = new Label(textoPregunta, estiloMsg);
        lblMensajePregunta.setAlignment(com.badlogic.gdx.utils.Align.center);
        cuadroInterno.add(lblMensajePregunta).padBottom(30).colspan(2).row();

        TextButton btnSi = new TextButton(btnSiTexto, skin);
        TextButton btnNo = new TextButton(btnNoTexto, skin);

        btnSi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Usuario u = ManejadorArchivos.cargarUsuario(usuarioPorActivar.trim().toLowerCase());
                if (u != null) {
                    u.setCuentaActiva(true);
                    ManejadorArchivos.guardarUsuario(u);
                }

                lblMensajePregunta.setText(textoExito);
                lblMensajePregunta.setColor(new Color(0.05f, 0.38f, 0.12f, 1f));

                cuadroInterno.clearChildren();
                cuadroInterno.add(lblMensajePregunta).padBottom(20).row();

                String btnOkTexto;
                switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
                    case "ENG":
                        btnOkTexto = "Accept";
                        break;
                    case "FRA":
                        btnOkTexto = "Accepter";
                        break;
                    case "GAR":
                        btnOkTexto = "Lere";
                        break;
                    case "HEB":
                        btnOkTexto = "אישור";
                        break;
                    default:
                        btnOkTexto = "Aceptar";
                        break;
                }

                TextButton btnOk = new TextButton(btnOkTexto, skin);
                btnOk.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        contenedorFlotanteAlerta.remove();
                        txtLoginPassword.setText("");
                    }
                });
                cuadroInterno.add(btnOk).width(120).height(45).center();
            }
        });

        btnNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                contenedorFlotanteAlerta.remove();
            }
        });

        cuadroInterno.add(btnSi).width(110).height(45).padRight(20).center();
        cuadroInterno.add(btnNo).width(110).height(45).center();

        contenedorFlotanteAlerta.add(cuadroInterno).width(460).height(240);
        stage.addActor(contenedorFlotanteAlerta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.18f, 0.29f, 1);
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
        if (btnIngresarTex != null) {
            btnIngresarTex.dispose();
        }
        if (btnVolverTex != null) {
            btnVolverTex.dispose();
        }
        if (fondoLoginTexture != null) {
            fondoLoginTexture.dispose();
        }
        if (fondoAlertaTex != null) {
            fondoAlertaTex.dispose();
        }
        if (fuenteAlerta != null) {
            fuenteAlerta.dispose();
        }
    }
}
