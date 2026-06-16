/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

import LogicaArchivos.Usuarios.SistemaAutenticacion;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 *
 * @author HP
 */
public class RegisterScreen implements Screen {

    /*private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Table contenedorCentral;
    private Texture fondoRegistroTexture;
    private Texture btnCrearTex;
    private Texture btnVolverTex;
    private Texture iconoOjoTex;

    private Texture btnFlechaIzqTex;
    private Texture btnFlechaDerTex;
    private Texture btnSubirTex;
    private Texture avatarActualTex;

    private TextField txtRegNombre;
    private TextField txtRegUser;
    private TextField txtRegPassword;

    private Label lblReqLongitud;
    private Label lblReqMayuscula;
    private Label lblReqMinuscula;
    private Label lblReqNumero;
    private Label lblReqEspecial;

    private BitmapFont fuenteCampos;
    private boolean ocultarContrasena = true;

    private final String[] avataresPredeterminados = {
        "imgMenus/avatar1.png",
        "imgMenus/avatar2.png",
        "imgMenus/avatar3.png",
        "imgMenus/avatar4.png",
        "imgMenus/avatar5.png"
    };
    private int indiceAvatarActual = 0;
    private String rutaImagenSeleccionada = "imgMenus/avatar1.png"; 

    public RegisterScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        fondoRegistroTexture = new Texture(Gdx.files.internal("imgMenus/fondo_registro_completo.png"));
        btnCrearTex = new Texture(Gdx.files.internal("imgMenus/btn_crear.png"));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        iconoOjoTex = new Texture(Gdx.files.internal("imgMenus/ojo.png"));

        btnFlechaIzqTex = new Texture(Gdx.files.internal("imgMenus/btn_flecha_izq.png"));
        btnFlechaDerTex = new Texture(Gdx.files.internal("imgMenus/btn_flecha_der.png"));
        btnSubirTex = new Texture(Gdx.files.internal("imgMenus/btn_subir.png"));
        avatarActualTex = new Texture(Gdx.files.internal("imgMenus/avatar1.png")); 

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoRegistroTexture)));
        stage.addActor(rootTable);

        contenedorCentral = new Table();
        rootTable.add(contenedorCentral).expand().fill();

        mostrarFormularioRegistro();
    }

    private void mostrarFormularioRegistro() {
        contenedorCentral.clearChildren();
        contenedorCentral.top();

        TextField.TextFieldStyle estiloBase = skin.get(TextField.TextFieldStyle.class);
        TextField.TextFieldStyle estiloTransparente = new TextField.TextFieldStyle(estiloBase);
        estiloTransparente.background = null;
        estiloTransparente.focusedBackground = null;
        estiloTransparente.fontColor = Color.WHITE;

        if (estiloBase.font != null) {
            fuenteCampos = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteCampos.getData().setScale(1.8f);
            estiloTransparente.font = fuenteCampos;
        }

        contenedorCentral.add().height(160).row();

        Table seccionAvatar = new Table();
        seccionAvatar.center();

        ImageButton btnFlechaIzq = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnFlechaIzqTex)));
        ImageButton btnFlechaDer = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnFlechaDerTex)));
        Image imgAvatarVisor = new Image(avatarActualTex);
        imgAvatarVisor.setScaling(Scaling.fill);

        btnFlechaIzq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceAvatarActual--;
                if (indiceAvatarActual < 0) {
                    indiceAvatarActual = avataresPredeterminados.length - 1;
                }
                actualizarVisorAvatar(imgAvatarVisor, avataresPredeterminados[indiceAvatarActual], true);
            }
        });

        btnFlechaDer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceAvatarActual++;
                if (indiceAvatarActual >= avataresPredeterminados.length) {
                    indiceAvatarActual = 0;
                }
                actualizarVisorAvatar(imgAvatarVisor, avataresPredeterminados[indiceAvatarActual], true);
            }
        });

        seccionAvatar.add(btnFlechaIzq).width(35).height(35).padRight(15);
        seccionAvatar.add(imgAvatarVisor).width(82).height(82); 
        seccionAvatar.add(btnFlechaDer).width(35).height(35).padLeft(15).row();

        contenedorCentral.add(seccionAvatar).row();

        contenedorCentral.add().height(25).row();

        ImageButton btnSubir = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnSubirTex)));
        btnSubir.getImage().setScaling(Scaling.fill);
        btnSubir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Selecciona tu foto de perfil");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes (*.png, *.jpg)", "png", "jpg", "jpeg"));

                    int seleccion = fileChooser.showOpenDialog(null);
                    if (seleccion == JFileChooser.APPROVE_OPTION) {
                        File archivo = fileChooser.getSelectedFile();
                        String rutaAbsoluta = archivo.getAbsolutePath();

                        Gdx.app.postRunnable(() -> actualizarVisorAvatar(imgAvatarVisor, rutaAbsoluta, false));
                    }
                }).start();
            }
        });
        contenedorCentral.add(btnSubir).width(95).height(32).row();

        contenedorCentral.add().height(26).row();

        txtRegNombre = new TextField("", estiloTransparente);
        txtRegNombre.setAlignment(com.badlogic.gdx.utils.Align.center);
        contenedorCentral.add(txtRegNombre).width(280).height(45).padTop(8).row();

        contenedorCentral.add().height(28).row();

        txtRegUser = new TextField("", estiloTransparente);
        txtRegUser.setAlignment(com.badlogic.gdx.utils.Align.center);
        contenedorCentral.add(txtRegUser).width(280).height(45).padTop(8).row();

        contenedorCentral.add().height(28).row();

        Table contenedorPassword = new Table();

        txtRegPassword = new TextField("", estiloTransparente);
        txtRegPassword.setPasswordMode(ocultarContrasena);
        txtRegPassword.setPasswordCharacter('*');
        txtRegPassword.setAlignment(com.badlogic.gdx.utils.Align.center);

        ImageButton btnOjo = new ImageButton(new TextureRegionDrawable(new TextureRegion(iconoOjoTex)));
        btnOjo.getImage().setScaling(Scaling.fill);
        btnOjo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ocultarContrasena = !ocultarContrasena;
                txtRegPassword.setPasswordMode(ocultarContrasena);
                txtRegPassword.setText(txtRegPassword.getText());
            }
        });

        contenedorPassword.add(txtRegPassword).width(220).height(45).padLeft(35).padTop(8);
        contenedorPassword.add(btnOjo).width(26).height(26).padRight(10);

        contenedorCentral.add(contenedorPassword).width(290).height(45).row();

        contenedorCentral.add().height(30).row();

        Table cajaRequisitos = new Table();
        cajaRequisitos.defaults().left().padBottom(1).padLeft(25).padTop(1);

        Label.LabelStyle estiloReq = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        if (estiloReq.font != null) {
            estiloReq.font.getData().setScale(1.0f);
        }

        lblReqLongitud = new Label("Minimo 5 caracteres", estiloReq);
        lblReqMayuscula = new Label("Al menos una mayuscula", estiloReq);
        lblReqMinuscula = new Label("Al menos una minuscula", estiloReq);
        lblReqNumero = new Label("Al menos un numero", estiloReq);
        lblReqEspecial = new Label("Un caracter especial", estiloReq);

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

        contenedorCentral.add(cajaRequisitos).width(300).height(95).center().row();

        contenedorCentral.add().height(32).row();

        txtRegPassword.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                validarContrasenaEnTiempoReal(textField.getText());
            }
        });

        ImageButton btnRegistrar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnCrearTex)));
        ImageButton btnVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnVolverTex)));

        btnRegistrar.getImage().setScaling(Scaling.fill);
        btnVolver.getImage().setScaling(Scaling.fill);

        btnRegistrar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String nombre = txtRegNombre.getText().trim();
                String user = txtRegUser.getText().trim();
                String pass = txtRegPassword.getText();

                if (nombre.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                    return;
                }
                if (!esContrasenaSegura(pass)) {
                    return;
                }

                String resultado = SistemaAutenticacion.registrarNuevoUsuario(user, pass, nombre, rutaImagenSeleccionada);
                if (resultado.equals("REGISTRO_EXITOSO")) {
                    parentGame.setScreen(new LoginRegisterScreen(parentGame));
                }
            }
        });

        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });

        Table filaBotones = new Table();
        filaBotones.add(btnVolver).width(55).height(55).padRight(40);
        filaBotones.add(btnRegistrar).width(135).height(50);

        contenedorCentral.add(filaBotones).center();
    }

    private void actualizarVisorAvatar(Image visor, String ruta, boolean esInterno) {
        if (avatarActualTex != null) {
            avatarActualTex.dispose();
        }

        if (esInterno) {
            avatarActualTex = new Texture(Gdx.files.internal(ruta));
        } else {
            avatarActualTex = new Texture(Gdx.files.absolute(ruta));
        }

        visor.setDrawable(new TextureRegionDrawable(new TextureRegion(avatarActualTex)));
        rutaImagenSeleccionada = ruta; 
    }

    private void validarContrasenaEnTiempoReal(String password) {
        actualizarEstadoRequisito(lblReqLongitud, "Minimo 5 caracteres", password.length() >= 5);
        actualizarEstadoRequisito(lblReqMayuscula, "Al menos una mayuscula", password.matches(".*[A-Z].*"));
        actualizarEstadoRequisito(lblReqMinuscula, "Al menos una minuscula", password.matches(".*[a-z].*"));
        actualizarEstadoRequisito(lblReqNumero, "Al menos un numero", password.matches(".*[0-9].*"));
        actualizarEstadoRequisito(lblReqEspecial, "Un caracter especial", password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*"));
    }

    private void actualizarEstadoRequisito(Label label, String textoBase, boolean cumplido) {
        if (label == null) {
            return;
        }
        label.setColor(cumplido ? Color.GREEN : Color.RED);
        label.setText(textoBase);
    }

    private boolean esContrasenaSegura(String password) {
        return password.length() >= 5
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*");
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
        fondoRegistroTexture.dispose();
        btnCrearTex.dispose();
        btnVolverTex.dispose();
        if (iconoOjoTex != null) {
            iconoOjoTex.dispose();
        }
        if (btnFlechaIzqTex != null) {
            btnFlechaIzqTex.dispose();
        }
        if (btnFlechaDerTex != null) {
            btnFlechaDerTex.dispose();
        }
        if (btnSubirTex != null) {
            btnSubirTex.dispose();
        }
        if (avatarActualTex != null) {
            avatarActualTex.dispose();
        }
        if (fuenteCampos != null) {
            fuenteCampos.dispose();
        }
    }*/

    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Table contenedorCentral;
    private Texture fondoRegistroTexture;
    private Texture btnCrearTex;
    private Texture btnVolverTex;
    private Texture iconoOjoTex;

    private Texture btnFlechaIzqTex;
    private Texture btnFlechaDerTex;
    private Texture btnSubirTex;
    private Texture avatarActualTex;

    private TextField txtRegNombre;
    private TextField txtRegUser;
    private TextField txtRegPassword;

    private Label lblReqLongitud;
    private Label lblReqMayuscula;
    private Label lblReqMinuscula;
    private Label lblReqNumero;
    private Label lblReqEspecial;

    private BitmapFont fuenteCampos;
    private boolean ocultarContrasena = true;

    private final String[] avataresPredeterminados = {
        "imgMenus/avatar1.png",
        "imgMenus/avatar2.png",
        "imgMenus/avatar3.png",
        "imgMenus/avatar4.png",
        "imgMenus/avatar5.png"
    };
    private int indiceAvatarActual = 0;
    private String rutaImagenSeleccionada = "imgMenus/avatar1.png";

    public RegisterScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        fondoRegistroTexture = new Texture(Gdx.files.internal("imgMenus/fondo_registro_completo_" + idm + ".png"));
        btnCrearTex = new Texture(Gdx.files.internal("imgMenus/btn_crear_" + idm + ".png"));

        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));
        iconoOjoTex = new Texture(Gdx.files.internal("imgMenus/ojo.png"));
        btnFlechaIzqTex = new Texture(Gdx.files.internal("imgMenus/btn_flecha_izq.png"));
        btnFlechaDerTex = new Texture(Gdx.files.internal("imgMenus/btn_flecha_der.png"));
        btnSubirTex = new Texture(Gdx.files.internal("imgMenus/btn_subir.png"));
        avatarActualTex = new Texture(Gdx.files.internal("imgMenus/avatar1.png"));

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoRegistroTexture)));
        stage.addActor(rootTable);

        contenedorCentral = new Table();
        rootTable.add(contenedorCentral).expand().fill();

        mostrarFormularioRegistro();
    }

    private void mostrarFormularioRegistro() {
        contenedorCentral.clearChildren();
        contenedorCentral.top();

        TextField.TextFieldStyle estiloBase = skin.get(TextField.TextFieldStyle.class);
        TextField.TextFieldStyle estiloTransparente = new TextField.TextFieldStyle(estiloBase);
        estiloTransparente.background = null;
        estiloTransparente.focusedBackground = null;
        estiloTransparente.fontColor = Color.WHITE;

        if (estiloBase.font != null) {
            fuenteCampos = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteCampos.getData().setScale(1.8f);
            estiloTransparente.font = fuenteCampos;
        }

        contenedorCentral.add().height(160).row();

        Table seccionAvatar = new Table();
        seccionAvatar.center();

        ImageButton btnFlechaIzq = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnFlechaIzqTex)));
        ImageButton btnFlechaDer = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnFlechaDerTex)));
        Image imgAvatarVisor = new Image(avatarActualTex);
        imgAvatarVisor.setScaling(Scaling.fill);

        btnFlechaIzq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceAvatarActual--;
                if (indiceAvatarActual < 0) {
                    indiceAvatarActual = avataresPredeterminados.length - 1;
                }
                actualizarVisorAvatar(imgAvatarVisor, avataresPredeterminados[indiceAvatarActual], true);
            }
        });

        btnFlechaDer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                indiceAvatarActual++;
                if (indiceAvatarActual >= avataresPredeterminados.length) {
                    indiceAvatarActual = 0;
                }
                actualizarVisorAvatar(imgAvatarVisor, avataresPredeterminados[indiceAvatarActual], true);
            }
        });

        seccionAvatar.add(btnFlechaIzq).width(35).height(35).padRight(15);
        seccionAvatar.add(imgAvatarVisor).width(82).height(82);
        seccionAvatar.add(btnFlechaDer).width(35).height(35).padLeft(15).row();

        contenedorCentral.add(seccionAvatar).row();

        contenedorCentral.add().height(25).row();

        ImageButton btnSubir = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnSubirTex)));
        btnSubir.getImage().setScaling(Scaling.fill);
        btnSubir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(() -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Selecciona tu foto de perfil");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes (*.png, *.jpg)", "png", "jpg", "jpeg"));

                    int seleccion = fileChooser.showOpenDialog(null);
                    if (seleccion == JFileChooser.APPROVE_OPTION) {
                        File archivo = fileChooser.getSelectedFile();
                        String rutaAbsoluta = archivo.getAbsolutePath();

                        Gdx.app.postRunnable(() -> actualizarVisorAvatar(imgAvatarVisor, rutaAbsoluta, false));
                    }
                }).start();
            }
        });
        contenedorCentral.add(btnSubir).width(95).height(32).row();

        contenedorCentral.add().height(26).row();

        txtRegNombre = new TextField("", estiloTransparente);
        txtRegNombre.setAlignment(com.badlogic.gdx.utils.Align.center);
        contenedorCentral.add(txtRegNombre).width(280).height(45).padTop(8).row();

        contenedorCentral.add().height(28).row();

        txtRegUser = new TextField("", estiloTransparente);
        txtRegUser.setAlignment(com.badlogic.gdx.utils.Align.center);
        contenedorCentral.add(txtRegUser).width(280).height(45).padTop(8).row();

        contenedorCentral.add().height(28).row();

        Table contenedorPassword = new Table();

        txtRegPassword = new TextField("", estiloTransparente);
        txtRegPassword.setPasswordMode(ocultarContrasena);
        txtRegPassword.setPasswordCharacter('*');
        txtRegPassword.setAlignment(com.badlogic.gdx.utils.Align.center);

        ImageButton btnOjo = new ImageButton(new TextureRegionDrawable(new TextureRegion(iconoOjoTex)));
        btnOjo.getImage().setScaling(Scaling.fill);
        btnOjo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ocultarContrasena = !ocultarContrasena;
                txtRegPassword.setPasswordMode(ocultarContrasena);
                txtRegPassword.setText(txtRegPassword.getText());
            }
        });

        contenedorPassword.add(txtRegPassword).width(220).height(45).padLeft(35).padTop(8);
        contenedorPassword.add(btnOjo).width(26).height(26).padRight(10);

        contenedorCentral.add(contenedorPassword).width(290).height(45).row();

        contenedorCentral.add().height(30).row();

        Table cajaRequisitos = new Table();
        cajaRequisitos.defaults().left().padBottom(1).padLeft(25).padTop(1);

        Label.LabelStyle estiloReq = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        if (estiloReq.font != null) {
            estiloReq.font.getData().setScale(1.0f);
        }

        lblReqLongitud = new Label("Minimo 5 caracteres", estiloReq);
        lblReqMayuscula = new Label("Al menos una mayuscula", estiloReq);
        lblReqMinuscula = new Label("Al menos una minuscula", estiloReq);
        lblReqNumero = new Label("Al menos un numero", estiloReq);
        lblReqEspecial = new Label("Un caracter especial", estiloReq);

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

        contenedorCentral.add(cajaRequisitos).width(300).height(95).center().row();

        contenedorCentral.add().height(32).row();

        txtRegPassword.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                validarContrasenaEnTiempoReal(textField.getText());
            }
        });

        ImageButton btnRegistrar = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnCrearTex)));
        ImageButton btnVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnVolverTex)));

        btnRegistrar.getImage().setScaling(Scaling.fill);
        btnVolver.getImage().setScaling(Scaling.fill);

        btnRegistrar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String nombre = txtRegNombre.getText().trim();
                String user = txtRegUser.getText().trim();
                String pass = txtRegPassword.getText();

                if (nombre.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                    return;
                }
                if (!esContrasenaSegura(pass)) {
                    return;
                }

                String resultado = SistemaAutenticacion.registrarNuevoUsuario(user, pass, nombre, rutaImagenSeleccionada);
                if (resultado.equals("REGISTRO_EXITOSO")) {
                    parentGame.setScreen(new LoginRegisterScreen(parentGame));
                }
            }
        });

        btnVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parentGame.setScreen(new MenuInicioScreen(parentGame));
            }
        });

        Table filaBotones = new Table();
        filaBotones.add(btnVolver).width(55).height(55).padRight(40);
        filaBotones.add(btnRegistrar).width(135).height(50);

        contenedorCentral.add(filaBotones).center();
    }

    private void actualizarVisorAvatar(Image visor, String ruta, boolean esInterno) {
        if (avatarActualTex != null) {
            avatarActualTex.dispose();
        }

        if (esInterno) {
            avatarActualTex = new Texture(Gdx.files.internal(ruta));
        } else {
            avatarActualTex = new Texture(Gdx.files.absolute(ruta));
        }

        visor.setDrawable(new TextureRegionDrawable(new TextureRegion(avatarActualTex)));
        rutaImagenSeleccionada = ruta;
    }

    private void validarContrasenaEnTiempoReal(String password) {
        actualizarEstadoRequisito(lblReqLongitud, "Minimo 5 caracteres", password.length() >= 5);
        actualizarEstadoRequisito(lblReqMayuscula, "Al menos una mayuscula", password.matches(".*[A-Z].*"));
        actualizarEstadoRequisito(lblReqMinuscula, "Al menos una minuscula", password.matches(".*[a-z].*"));
        actualizarEstadoRequisito(lblReqNumero, "Al menos un numero", password.matches(".*[0-9].*"));
        actualizarEstadoRequisito(lblReqEspecial, "Un caracter especial", password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*"));
    }

    private void actualizarEstadoRequisito(Label label, String textoBase, boolean cumplido) {
        if (label == null) {
            return;
        }
        label.setColor(cumplido ? Color.GREEN : Color.RED);
        label.setText(textoBase);
    }

    private boolean esContrasenaSegura(String password) {
        return password.length() >= 5
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-+=\\[\\]\\\\/].*");
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
        if (fondoRegistroTexture != null) {
            fondoRegistroTexture.dispose();
        }
        if (btnCrearTex != null) {
            btnCrearTex.dispose();
        }
        if (btnVolverTex != null) {
            btnVolverTex.dispose();
        }
        if (iconoOjoTex != null) {
            iconoOjoTex.dispose();
        }
        if (btnFlechaIzqTex != null) {
            btnFlechaIzqTex.dispose();
        }
        if (btnFlechaDerTex != null) {
            btnFlechaDerTex.dispose();
        }
        if (btnSubirTex != null) {
            btnSubirTex.dispose();
        }
        if (avatarActualTex != null) {
            avatarActualTex.dispose();
        }
        if (fuenteCampos != null) {
            fuenteCampos.dispose();
        }
    }
}
