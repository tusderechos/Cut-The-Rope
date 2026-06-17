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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.graficos.GestorFuentes;
import com.tusderechos.Juego.graficos.RutasAssetsIdioma;
import com.tusderechos.Juego.ranking.CalculadoraRanking;
import com.tusderechos.Juego.ranking.EntradaRanking;
import com.tusderechos.Juego.textos.TextosIdioma;
import java.util.List;

/**
 *
 * @author Hp
 */
public class RankingScreen implements Screen {
    private final Game ParentGame;
    private Stage StageActual;
    private Skin SkinActual;
    private BitmapFont FuenteTitulo;
    private BitmapFont FuenteTexto;
    private Texture TexturaFondo;
    private Texture TexturaVolver;
    private Texture TexturaGlobal;
    private Texture TexturaAmigos;
    private Texture TexturaMarcoLista;
    private Texture TexturaInteriorLista;
    private boolean MostrandoAmigos;

    public RankingScreen(Game ParentGame) {
        this.ParentGame = ParentGame;
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(StageActual);
        SkinActual = SkinMenu.Crear();
        FuenteTitulo = GestorFuentes.CrearFuenteGoodDog(48);
        FuenteTexto = GestorFuentes.CrearFuenteGoodDog(28);
        CargarTexturas();
        ConstruirContenido();
    }

    private void CargarTexturas() {
        TexturaFondo = CargarTextura("imagenes/fondo_ranking.png");
        TexturaVolver = CargarTextura("imgMenus/btn_volver.png");
        TexturaGlobal = CargarTextura(RutasAssetsIdioma.ObtenerRutaBoton("global"));
        TexturaAmigos = CargarTextura(RutasAssetsIdioma.ObtenerRutaBoton("amigos"));
        TexturaMarcoLista = CrearTexturaColor(new Color(0.95f, 0.54f, 0.11f, 0.95f));
        TexturaInteriorLista = CrearTexturaColor(new Color(0.05f, 0.12f, 0.10f, 0.91f));
    }

    private void ConstruirContenido() {
        StageActual.clear();

        Image FondoPantalla = new Image(TexturaFondo);
        FondoPantalla.setFillParent(true);
        FondoPantalla.setScaling(Scaling.fill);
        StageActual.addActor(FondoPantalla);

        Table Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.top();
        Raiz.pad(18f);
        StageActual.addActor(Raiz);

        ImageButton BotonVolver = new ImageButton(new TextureRegionDrawable(new TextureRegion(TexturaVolver)));
        BotonVolver.getImage().setScaling(Scaling.fill);
        BotonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Evento, float X, float Y) {
                ParentGame.setScreen(new MainMenuScreen(ParentGame));
            }
        });

        Label Titulo = CrearLabel(TextosIdioma.Obtener("Ranking"), FuenteTitulo);
        Titulo.setAlignment(Align.center);

        Table Encabezado = new Table();
        Encabezado.add(BotonVolver).width(58).height(58).left();
        Encabezado.add(Titulo).expandX().center();
        Encabezado.add().width(58).height(58);
        Raiz.add(Encabezado).width(430).padBottom(8).row();

        Table Selector = new Table();
        Selector.add(CrearBotonSelector(TexturaGlobal, false)).width(154).height(52).padRight(14);
        Selector.add(CrearBotonSelector(TexturaAmigos, true)).width(154).height(52);
        Raiz.add(Selector).padBottom(12).row();

        Table Lista = new Table();
        Lista.top();
        Lista.pad(14f);
        AgregarEntradasRanking(Lista);

        ScrollPane Scroll = new ScrollPane(Lista, SkinActual);
        Scroll.setFadeScrollBars(false);
        Scroll.setScrollingDisabled(true, false);
        Scroll.getStyle().background = new TextureRegionDrawable(new TextureRegion(TexturaInteriorLista));

        Table MarcoLista = new Table();
        MarcoLista.setBackground(new TextureRegionDrawable(new TextureRegion(TexturaMarcoLista)));
        MarcoLista.pad(7f);
        MarcoLista.add(Scroll).width(404).height(510).center();
        Raiz.add(MarcoLista).width(432).height(538).center();
    }

    private ImageButton CrearBotonSelector(Texture Textura, final boolean Amigos) {
        ImageButton Boton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Textura)));
        Boton.getImage().setScaling(Scaling.fill);
        Boton.setColor(MostrandoAmigos == Amigos ? Color.WHITE : new Color(0.70f, 0.70f, 0.70f, 1f));
        Boton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent Evento, float X, float Y) {
                MostrandoAmigos = Amigos;
                ConstruirContenido();
            }
        });

        return Boton;
    }

    private void AgregarEntradasRanking(Table Lista) {
        Usuario UsuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        List<Usuario> Usuarios = ManejadorArchivos.listarUsuarios();
        if (MostrandoAmigos && UsuarioActivo == null) {
            AgregarMensaje(Lista, TextosIdioma.Obtener("RankingSinSesion"));
            return;
        }

        List<EntradaRanking> Ranking = MostrandoAmigos ? CalculadoraRanking.CrearRankingAmigos(UsuarioActivo, Usuarios) : CalculadoraRanking.CrearRankingGlobal(Usuarios);
        if (Ranking.isEmpty()) {
            AgregarMensaje(Lista, TextosIdioma.Obtener("RankingVacio"));
            return;
        }

        for (EntradaRanking Entrada : Ranking) {
            AgregarFilaRanking(Lista, Entrada, UsuarioActivo);
        }
    }

    private void AgregarFilaRanking(Table Lista, EntradaRanking Entrada, Usuario UsuarioActivo) {
        boolean EsActivo = UsuarioActivo != null && UsuarioActivo.getUsername() != null && UsuarioActivo.getUsername().equalsIgnoreCase(Entrada.ObtenerUsername());
        Table Fila = new Table();
        Fila.setBackground(SkinActual.newDrawable("fondoCampo", EsActivo ? new Color(0.20f, 0.33f, 0.18f, 0.95f) : new Color(0.04f, 0.07f, 0.08f, 0.90f)));
        Fila.pad(10f);

        Label Posicion = CrearLabel("#" + Entrada.ObtenerPosicion(), FuenteTexto);
        Posicion.setAlignment(Align.center);
        Fila.add(Posicion).width(54).center().padRight(8);

        Table Datos = new Table();
        Datos.left();
        Label Nombre = CrearLabel(Entrada.ObtenerNombreCompleto(), FuenteTexto);
        Nombre.setAlignment(Align.left);
        Datos.add(Nombre).left().row();

        Label Username = CrearLabel("@" + Entrada.ObtenerUsername(), FuenteTexto);
        Username.setFontScale(0.78f);
        Username.setColor(Color.LIGHT_GRAY);
        Datos.add(Username).left();
        Fila.add(Datos).width(170).left().padRight(8);

        Table Numeros = new Table();
        Numeros.right();
        Label Puntaje = CrearLabel(TextosIdioma.Formatear("RankingPuntaje", Entrada.ObtenerPuntajeTotal()), FuenteTexto);
        Puntaje.setAlignment(Align.right);
        Numeros.add(Puntaje).right().row();

        Label Extra = CrearLabel(TextosIdioma.Formatear("RankingEstrellas", Entrada.ObtenerEstrellasTotales()) + " | " + TextosIdioma.Formatear("RankingNivelMax", Entrada.ObtenerNivelesCompletados()), FuenteTexto);
        Extra.setFontScale(0.70f);
        Extra.setColor(Color.valueOf("F8E8B0"));
        Extra.setAlignment(Align.right);
        Numeros.add(Extra).right();
        Fila.add(Numeros).expandX().right();

        Lista.add(Fila).width(390).padBottom(10).row();
    }

    private void AgregarMensaje(Table Lista, String Mensaje) {
        Label Texto = CrearLabel(Mensaje, FuenteTitulo);
        Texto.setWrap(true);
        Texto.setAlignment(Align.center);
        Lista.add(Texto).width(360).padTop(90).center();
    }

    private Label CrearLabel(String Texto, BitmapFont Fuente) {
        Label.LabelStyle Estilo = new Label.LabelStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;

        return new Label(Texto, Estilo);
    }

    private Texture CargarTextura(String Ruta) {
        Texture Textura = new Texture(Gdx.files.internal(Ruta));
        Textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return Textura;
    }

    private Texture CrearTexturaColor(Color ColorActual) {
        Pixmap PixmapActual = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorActual);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();

        return Textura;
    }

    @Override
    public void render(float Delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        StageActual.act(Delta);
        StageActual.draw();
    }

    @Override
    public void resize(int Ancho, int Alto) {
        StageActual.getViewport().update(Ancho, Alto, true);
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
        if (StageActual != null) {
            StageActual.dispose();
        }
        if (SkinActual != null) {
            SkinActual.dispose();
        }
        if (FuenteTitulo != null) {
            FuenteTitulo.dispose();
        }
        if (FuenteTexto != null) {
            FuenteTexto.dispose();
        }
        if (TexturaFondo != null) {
            TexturaFondo.dispose();
        }
        if (TexturaVolver != null) {
            TexturaVolver.dispose();
        }
        if (TexturaGlobal != null) {
            TexturaGlobal.dispose();
        }
        if (TexturaAmigos != null) {
            TexturaAmigos.dispose();
        }
        if (TexturaMarcoLista != null) {
            TexturaMarcoLista.dispose();
        }
        if (TexturaInteriorLista != null) {
            TexturaInteriorLista.dispose();
        }
    }
}
