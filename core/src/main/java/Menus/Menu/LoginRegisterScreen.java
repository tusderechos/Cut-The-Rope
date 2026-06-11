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

/**
 *
 * @author HP
 */
public class LoginRegisterScreen implements Screen {

    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Table contenedorCentral;

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

    public LoginRegisterScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Label lblTitulo = new Label("CUT THE ROPE - SISTEMA DE SESION", skin);
        lblTitulo.setFontScale(1.5f);
        root.add(lblTitulo).padBottom(30).row();

        contenedorCentral = new Table();
        root.add(contenedorCentral).padBottom(20).row();

        mostrarFormularioLogin();
    }

    private void mostrarFormularioLogin() {
        contenedorCentral.clearChildren();

        Label lblLogin = new Label("INICIAR SESION", skin);
        lblLogin.setFontScale(1.2f);
        contenedorCentral.add(lblLogin).colspan(2).padBottom(15).row();

        contenedorCentral.add(new Label("Usuario:", skin)).left().padBottom(10);
        txtLoginUser = new TextField("", skin);
        contenedorCentral.add(txtLoginUser).width(200).padBottom(10).row();

        contenedorCentral.add(new Label("Contraseña:", skin)).left().padBottom(15);
        txtLoginPassword = new TextField("", skin);
        txtLoginPassword.setPasswordMode(true);
        txtLoginPassword.setPasswordCharacter('*');
        contenedorCentral.add(txtLoginPassword).width(200).padBottom(15).row();

        final CheckBox chkMostrarPass = new CheckBox(" Mostrar contraseña", skin);
        chkMostrarPass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                txtLoginPassword.setPasswordMode(!chkMostrarPass.isChecked());
            }
        });
        contenedorCentral.add(chkMostrarPass).colspan(2).left().padBottom(15).row();

        TextButton btnIngresar = new TextButton("Ingresar", skin);
        TextButton btnIrRegistro = new TextButton("Crear Cuenta", skin);

        btnIngresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String user = txtLoginUser.getText();
                String pass = txtLoginPassword.getText();

                if (SistemaAutenticacion.iniciarSesion(user, pass)) {
                    Gdx.app.log("Login", "¡Acceso concedido!");
                    parentGame.setScreen(new MainMenuScreen(parentGame));
                } else {
                    lblLogin.setText("¡Credenciales Incorrectas!");
                }
            }
        });

        btnIrRegistro.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mostrarFormularioRegistro();
            }
        });

        contenedorCentral.add(btnIngresar).width(110).padRight(10);
        contenedorCentral.add(btnIrRegistro).width(110).row();
    }

    private void mostrarFormularioRegistro() {
        contenedorCentral.clearChildren();

        final Label lblRegistro = new Label("REGISTRO DE USUARIO", skin);
        lblRegistro.setFontScale(1.2f);
        contenedorCentral.add(lblRegistro).colspan(2).padBottom(15).row();

        contenedorCentral.add(new Label("Nombre Completo:", skin)).left().padBottom(10);
        txtRegNombre = new TextField("", skin);
        contenedorCentral.add(txtRegNombre).width(200).padBottom(10).row();

        contenedorCentral.add(new Label("Nombre de Usuario:", skin)).left().padBottom(10);
        txtRegUser = new TextField("", skin);
        contenedorCentral.add(txtRegUser).width(200).padBottom(10).row();

        contenedorCentral.add(new Label("Contraseña:", skin)).left().padBottom(5);
        txtRegPassword = new TextField("", skin);
        txtRegPassword.setPasswordMode(true);
        txtRegPassword.setPasswordCharacter('*');
        contenedorCentral.add(txtRegPassword).width(200).padBottom(5).row();

        Table cajaRequisitos = new Table();
        cajaRequisitos.defaults().left().pad(2);

        lblReqLongitud = new Label("Minimo 5 caracteres", skin);
        lblReqMayuscula = new Label("Al menos una mayuscula", skin);
        lblReqMinuscula = new Label("Al menos una minuscula", skin);
        lblReqNumero = new Label("Al menos un numero", skin);
        lblReqEspecial = new Label("Un caracter especial", skin);

        lblReqLongitud.setColor(Color.RED);
        lblReqMayuscula.setColor(Color.RED);
        lblReqMinuscula.setColor(Color.RED);
        lblReqNumero.setColor(Color.RED);
        lblReqEspecial.setColor(Color.RED);

        cajaRequisitos.add(lblReqLongitud).row();
        cajaRequisitos.add(lblReqMayuscula).row();
        cajaRequisitos.add(lblReqMinuscula).row();
        cajaRequisitos.add(lblReqNumero).row();
        cajaRequisitos.add(lblReqEspecial).row();

        contenedorCentral.add(cajaRequisitos).colspan(2).left().padBottom(15).row();

        txtRegPassword.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                validarContrasenaEnTiempoReal(textField.getText());
            }
        });

        TextButton btnRegistrar = new TextButton("Crear", skin);
        TextButton btnVolver = new TextButton("Volver", skin);

        btnRegistrar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String nombre = txtRegNombre.getText().trim();
                String user = txtRegUser.getText().trim();
                String pass = txtRegPassword.getText();

                if (nombre.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                    lblRegistro.setText("Campos obligatorios vacios");
                    lblRegistro.setColor(Color.RED);
                    return;
                }

                if (!esContrasenaSegura(pass)) {
                    lblRegistro.setText("Contrasena no cumple requisitos");
                    lblRegistro.setColor(Color.RED);
                    return;
                }

                String resultado = SistemaAutenticacion.registrarNuevoUsuario(user, pass, nombre);
                if (resultado.equals("REGISTRO_EXITOSO")) {
                    mostrarFormularioLogin();
                } else {
                    lblRegistro.setText(resultado);
                    lblRegistro.setColor(Color.RED);
                }
            }
        });

        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mostrarFormularioLogin();
            }
        });

        contenedorCentral.add(btnRegistrar).width(110).padRight(10);
        contenedorCentral.add(btnVolver).width(110).row();
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
        Gdx.gl.glClearColor(0.15f, 0.4f, 0.25f, 1); 
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
