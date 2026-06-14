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

/**
 *
 * @author HP
 */
public class LoginRegisterScreen implements Screen {

    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Table contenedorCentral;

    private Texture fondoTexture;
    private Texture btnIngresarTex;
    private Texture btnCrearTex;
    private Texture btnVolverTex;

    private TextField txtLoginUser;
    private TextField txtLoginPassword;

    private TextField txtRegNombre;
    private TextField txtRegUser;
    private TextField txtRegPassword;

    private Label lblReqLongitud;
    private Label lblReqMayuscula;
    private Label lblReqMinuscula;
    private Label lblReqNumero;
    private Label lblReqEspecial;
    
    private Texture fondoLoginTexture;
    private Texture fondoRegistroTexture;
    private Table rootTable;

    public LoginRegisterScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        fondoLoginTexture = new Texture(Gdx.files.internal("imgMenus/fondo_login.png"));
        
        btnIngresarTex = new Texture(Gdx.files.internal("imgMenus/btn_ingresar.png"));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png")); 
        btnCrearTex = new Texture(Gdx.files.internal("imgMenus/btn_crear.png")); 

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
        
        contenedorCentral.add(txtLoginPassword).width(310).height(65).colspan(2)
                .padTop(10).padBottom(20).row();

        final CheckBox chkMostrarPass = new CheckBox(" Ver", skin);
        chkMostrarPass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                txtLoginPassword.setPasswordMode(!chkMostrarPass.isChecked());
            }
        });
        contenedorCentral.add(chkMostrarPass).colspan(2).center().padBottom(35).row();

        ImageButton btnIngresar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnIngresarTex)));
        btnIngresar.getImage().setScaling(Scaling.fill);

        btnIngresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String user = txtLoginUser.getText();
                String pass = txtLoginPassword.getText();

                if (SistemaAutenticacion.iniciarSesion(user, pass)) {
                    Gdx.app.log("Login", "¡Acceso concedido!");
                    parentGame.setScreen(new MainMenuScreen(parentGame)); 
                } else {
                    Gdx.app.log("Login", "Credenciales Incorrectas");
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


    private void validarContrasenaEnTiempoReal(String pass) {
        actualizarEstadoRequisito(lblReqLongitud, "Minimo 5 caracteres", pass.length() >= 5);
        actualizarEstadoRequisito(lblReqMayuscula, "Al menos una mayuscula", pass.matches(".*[A-Z].*"));
        actualizarEstadoRequisito(lblReqMinuscula, "Al menos una minuscula", pass.matches(".*[a-z].*"));
        actualizarEstadoRequisito(lblReqNumero, "Al menos un numero", pass.matches(".*[0-9].*"));
        actualizarEstadoRequisito(lblReqEspecial, "Un caracter especial", pass.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*"));
    }

    private void actualizarEstadoRequisito(Label label, String textoBase, boolean cumplido) {
        if (label == null) {
            return;
        }

        if (cumplido) {
            label.setColor(Color.GREEN);
            label.setText("✔ " + textoBase);
        } else {
            label.setColor(Color.RED);
            label.setText("[ ] " + textoBase);
        }
    }

    private boolean esContrasenaSegura(String pass) {
        return pass.length() >= 5
                && pass.matches(".*[A-Z].*")
                && pass.matches(".*[a-z].*")
                && pass.matches(".*[0-9].*")
                && pass.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*");
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
        fondoTexture.dispose();
        btnIngresarTex.dispose();
        btnCrearTex.dispose();
        btnVolverTex.dispose();
    }
}
