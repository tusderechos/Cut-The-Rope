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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.graficos.RutasAssetsIdioma;
import com.tusderechos.Juego.social.BusquedaJugadores;
import com.tusderechos.Juego.social.GestorSeguimiento;
import java.util.List;

/**
 *
 * @author Hp
 */
public class BuscarJugadoresScreen implements Screen {

    private final Game ParentGame;
    private Stage StageActual;
    private Skin SkinActual;
    private Texture FondoMenuTextura;
    private Texture TituloBuscarJugadoresTexture;
    private Texture BotonPerfilTexture;
    private Texture BotonSeguirTexture;
    private Texture BotonSiguiendoTexture;
    private Texture BotonVolverTexture;
    private TextField CampoBusqueda;
    private Table TablaResultados;
    private Label LabelEstado;

    public BuscarJugadoresScreen(Game GameActual) {
        this.ParentGame = GameActual;
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(StageActual);

        SkinActual = SkinMenu.Crear();
        FondoMenuTextura = new Texture(Gdx.files.internal("imgMenus/fondo_menu_principal.png"));
        TituloBuscarJugadoresTexture = CargarTexturaSiExiste(RutasAssetsIdioma.ObtenerRutaBoton("buscar_jugadores"));
        BotonPerfilTexture = CargarTexturaSiExiste(RutasAssetsIdioma.ObtenerRutaBoton("perfil"));
        BotonSeguirTexture = CargarTexturaSiExiste(RutasAssetsIdioma.ObtenerRutaBoton("seguir"));
        BotonSiguiendoTexture = CargarTexturaSiExiste(RutasAssetsIdioma.ObtenerRutaBoton("siguiendo"));
        BotonVolverTexture = CargarTexturaSiExiste(RutasAssetsIdioma.ObtenerRutaBoton("volver"));

        Table TablaRaiz = new Table();
        TablaRaiz.setFillParent(true);
        TablaRaiz.setBackground(new TextureRegionDrawable(new TextureRegion(FondoMenuTextura)));
        TablaRaiz.pad(42, 54, 36, 54);
        StageActual.addActor(TablaRaiz);

        AgregarTitulo(TablaRaiz);

        CampoBusqueda = new TextField("", SkinActual);
        CampoBusqueda.setMessageText("Username o nombre");
        CampoBusqueda.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent EventActual, com.badlogic.gdx.scenes.scene2d.Actor ActorActual) {
                ActualizarResultados();
            }
        });
        TablaRaiz.add(CampoBusqueda).growX().height(44).padBottom(12).row();

        LabelEstado = new Label("", SkinActual);
        LabelEstado.setAlignment(Align.center);
        LabelEstado.setColor(Color.LIGHT_GRAY);
        TablaRaiz.add(LabelEstado).growX().height(28).padBottom(8).row();

        TablaResultados = new Table();
        TablaResultados.top();

        ScrollPane ScrollResultados = new ScrollPane(TablaResultados, SkinActual);
        ScrollResultados.setFadeScrollBars(false);
        TablaRaiz.add(ScrollResultados).grow().padBottom(18).row();

        Button BotonVolver = CrearBotonVolver();
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent EventActual, float PosicionX, float PosicionY) {
                ParentGame.setScreen(new MainMenuScreen(ParentGame));
            }
        });

        TablaRaiz.add(BotonVolver).width(190).height(48).center();
        ActualizarResultados();
    }

    private void ActualizarResultados() {
        TablaResultados.clearChildren();

        Usuario UsuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        String UsernameActivo = UsuarioActivo == null ? "" : UsuarioActivo.getUsername();
        List<Usuario> UsuariosEncontrados = BusquedaJugadores.FiltrarUsuarios(ManejadorArchivos.listarUsuarios(), CampoBusqueda.getText(), UsernameActivo);

        if (UsuarioActivo == null) {
            LabelEstado.setText(ObtenerTextoEstadoSinSesion());
        } else if (UsuariosEncontrados.isEmpty()) {
            LabelEstado.setText(ObtenerTextoSinResultados());
        } else {
            LabelEstado.setText(ObtenerTextoResultados());
        }

        for (Usuario UsuarioEncontrado : UsuariosEncontrados) {
            AgregarFilaUsuario(UsuarioActivo, UsuarioEncontrado);
        }
    }

    private void AgregarFilaUsuario(Usuario UsuarioActivo, Usuario UsuarioEncontrado) {
        Table FilaUsuario = new Table();
        FilaUsuario.defaults().pad(4);
        FilaUsuario.setBackground(SkinActual.newDrawable("fondoCampo", new Color(0.08f, 0.12f, 0.14f, 0.92f)));

        Label LabelNombre = new Label(ObtenerNombreVisible(UsuarioEncontrado), SkinActual);
        LabelNombre.setFontScale(1.12f);

        Label LabelUsername = new Label("@" + UsuarioEncontrado.getUsername(), SkinActual);
        LabelUsername.setColor(Color.LIGHT_GRAY);

        Table DatosUsuario = new Table();
        DatosUsuario.left();
        DatosUsuario.add(LabelNombre).left().row();
        DatosUsuario.add(LabelUsername).left();

        Button BotonSeguir = CrearBotonSeguimiento(UsuarioActivo, UsuarioEncontrado);
        Actor BotonPerfil = CrearBotonPerfil(UsuarioEncontrado);

        FilaUsuario.add(DatosUsuario).growX().left().padLeft(12);
        FilaUsuario.add(BotonPerfil).width(110).height(42).padRight(8);
        FilaUsuario.add(BotonSeguir).width(128).height(42).padRight(12);

        TablaResultados.add(FilaUsuario).growX().height(76).padBottom(10).row();
    }

    private Actor CrearBotonPerfil(Usuario UsuarioEncontrado) {
        Actor BotonPerfil;
        if (BotonPerfilTexture != null) {
            ImageButton BotonPerfilImagen = new ImageButton(new TextureRegionDrawable(new TextureRegion(BotonPerfilTexture)));
            BotonPerfilImagen.getImage().setScaling(Scaling.fill);
            BotonPerfil = BotonPerfilImagen;
        } else {
            TextButton BotonPerfilTexto = new TextButton("Perfil", SkinActual);
            BotonPerfilTexto.getLabel().setAlignment(Align.center);
            BotonPerfil = BotonPerfilTexto;
        }

        BotonPerfil.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent EventActual, float PosicionX, float PosicionY) {
                ParentGame.setScreen(new ProfileScreen(ParentGame, UsuarioEncontrado.getUsername()));
            }
        });

        return BotonPerfil;
    }

    private void AgregarTitulo(Table TablaRaiz) {
        if (TituloBuscarJugadoresTexture != null) {
            Image TituloBuscarJugadores = new Image(TituloBuscarJugadoresTexture);
            TituloBuscarJugadores.setScaling(Scaling.fill);
            TablaRaiz.add(TituloBuscarJugadores).width(260).height(65).center().padBottom(18).row();
            return;
        }

        Label LabelTitulo = new Label("Buscar jugadores", SkinActual);
        LabelTitulo.setAlignment(Align.center);
        LabelTitulo.setFontScale(1.65f);
        TablaRaiz.add(LabelTitulo).growX().padBottom(18).row();
    }

    private Button CrearBotonSeguimiento(Usuario UsuarioActivo, Usuario UsuarioEncontrado) {
        String TextoBoton = ObtenerTextoBotonSeguimiento(UsuarioActivo, UsuarioEncontrado);
        Button BotonSeguir;
        Texture TexturaBoton = GestorSeguimiento.YaSigue(UsuarioActivo, UsuarioEncontrado) ? BotonSiguiendoTexture : BotonSeguirTexture;
        if (UsuarioActivo != null && TexturaBoton != null) {
            ImageButton BotonImagen = new ImageButton(new TextureRegionDrawable(new TextureRegion(TexturaBoton)));
            BotonImagen.getImage().setScaling(Scaling.fill);
            BotonSeguir = BotonImagen;
        } else {
            TextButton BotonTexto = new TextButton(TextoBoton, SkinActual);
            BotonTexto.getLabel().setAlignment(Align.center);
            BotonSeguir = BotonTexto;
        }
        BotonSeguir.setDisabled(!GestorSeguimiento.PuedeSeguir(UsuarioActivo, UsuarioEncontrado));

        BotonSeguir.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent EventActual, float PosicionX, float PosicionY) {
                if (GestorSeguimiento.SeguirUsuario(UsuarioActivo, UsuarioEncontrado)) {
                    ManejadorArchivos.guardarUsuario(UsuarioActivo);
                    ActualizarResultados();
                }
            }
        });

        return BotonSeguir;
    }

    private Button CrearBotonVolver() {
        if (BotonVolverTexture != null) {
            ImageButton BotonVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(BotonVolverTexture)));
            BotonVolver.getImage().setScaling(Scaling.fill);
            return BotonVolver;
        }

        TextButton BotonVolver = new TextButton("Volver", SkinActual);
        BotonVolver.getLabel().setAlignment(Align.center);
        return BotonVolver;
    }

    private Texture CargarTexturaSiExiste(String Ruta) {
        if (Ruta != null && Gdx.files.internal(Ruta).exists()) {
            Texture Textura = new Texture(Gdx.files.internal(Ruta));
            Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            return Textura;
        }

        return null;
    }

    private String ObtenerTextoEstadoSinSesion() {
        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                return "Log in to follow players.";
            case "FRA":
                return "Connecte-toi pour suivre des joueurs.";
            case "GAR":
                return "Inicia sesion para seguir jugadores.";
            case "HEB":
                return "Inicia sesion para seguir jugadores.";
            default:
                return "Inicia sesion para seguir jugadores.";
        }
    }

    private String ObtenerTextoSinResultados() {
        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                return "No players found.";
            case "FRA":
                return "Aucun joueur trouve.";
            case "GAR":
                return "No se encontraron jugadores.";
            case "HEB":
                return "No se encontraron jugadores.";
            default:
                return "No se encontraron jugadores.";
        }
    }

    private String ObtenerTextoResultados() {
        switch (ConfiguracionJuego.idiomaActivo.toUpperCase()) {
            case "ENG":
                return "Follow players to prepare future rivalries.";
            case "FRA":
                return "Suis des joueurs pour preparer des rivalites.";
            case "GAR":
                return "Toca seguir para preparar futuras rivalidades.";
            case "HEB":
                return "Toca seguir para preparar futuras rivalidades.";
            default:
                return "Toca seguir para preparar futuras rivalidades.";
        }
    }

    private String ObtenerTextoBloqueado() {
        return ConfiguracionJuego.idiomaActivo.equalsIgnoreCase("ENG") ? "Locked" : "Bloqueado";
    }

    private String ObtenerTextoSiguiendo() {
        return ConfiguracionJuego.idiomaActivo.equalsIgnoreCase("ENG") ? "Following" : "Siguiendo";
    }

    private String ObtenerTextoSeguir() {
        return ConfiguracionJuego.idiomaActivo.equalsIgnoreCase("ENG") ? "Follow" : "Seguir";
    }
    private String ObtenerTextoBotonSeguimiento(Usuario UsuarioActivo, Usuario UsuarioEncontrado) {
        if (UsuarioActivo == null) {
            return ObtenerTextoBloqueado();
        }

        if (GestorSeguimiento.YaSigue(UsuarioActivo, UsuarioEncontrado)) {
            return ObtenerTextoSiguiendo();
        }

        return ObtenerTextoSeguir();
    }

    private String ObtenerNombreVisible(Usuario UsuarioEncontrado) {
        if (UsuarioEncontrado.getNombreCompleto() == null || UsuarioEncontrado.getNombreCompleto().trim().isEmpty()) {
            return UsuarioEncontrado.getUsername();
        }

        return UsuarioEncontrado.getNombreCompleto();
    }

    @Override
    public void render(float Delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        StageActual.act(Delta);
        StageActual.draw();
    }

    @Override
    public void resize(int Width, int Height) {
        StageActual.getViewport().update(Width, Height, true);
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
        StageActual.dispose();
        SkinActual.dispose();
        FondoMenuTextura.dispose();
        if (TituloBuscarJugadoresTexture != null) {
            TituloBuscarJugadoresTexture.dispose();
        }
        if (BotonPerfilTexture != null) {
            BotonPerfilTexture.dispose();
        }
        if (BotonSeguirTexture != null) {
            BotonSeguirTexture.dispose();
        }
        if (BotonSiguiendoTexture != null) {
            BotonSiguiendoTexture.dispose();
        }
        if (BotonVolverTexture != null) {
            BotonVolverTexture.dispose();
        }
    }
}

