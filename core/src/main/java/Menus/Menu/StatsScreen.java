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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

/**
 *
 * @author HP
 */
public class StatsScreen implements Screen {
    private final Game parentGame;
    private Stage stage;
    private Skin skin;

    private Texture fondoStatsTex;
    private Texture btnVolverTex;
    private Texture texturaFondoContenedor;
    private BitmapFont fuenteTitulo;
    private BitmapFont fuenteContenido;

    private Texture icoEstrellaTex;
    private Texture icoTiempoTex;
    private Texture icoFechaTex;

    private String txtEstrellas;
    private String txtNivelMax;
    private String txtNivel;
    private String txtPartidasJugadas;
    private String txtTiempoJugado;
    private String txtHistorial;
    private String txtNoPartidas;
    private String txtErrorSesion;
    private String txtScroll;
    private String txtFecha;

    private String txtHistEstrellas;
    private String txtHistTiempo;
    private String txtHistCompletado;

    public StatsScreen(Game game) {
        this.parentGame = game;
    }

    private void inicializarIdioma(String idioma) {
        switch (idioma.toLowerCase().trim()) {
            case "eng":
                txtEstrellas = "Total Stars:";
                txtNivelMax = "Max Level:";
                txtNivel = "Level ";
                txtPartidasJugadas = "Matches Played:";
                txtTiempoJugado = "Playtime:";
                txtHistorial = "HISTORY";
                txtNoPartidas = "No matches recorded yet.";
                txtErrorSesion = "LOG IN";
                txtScroll = "SCROLL";
                txtFecha = "DATE: ";
                txtHistEstrellas = "STARS: ";
                txtHistTiempo = "TIME: ";
                txtHistCompletado = "Completed";
                break;
            case "gar":
                txtEstrellas = "Sunwiti luma Chulugati:";
                txtNivelMax = "Ibunidun Lida Sani:";
                txtNivel = "Sani ";
                txtPartidasJugadas = "Uruba Agachagua:";
                txtTiempoJugado = "Dan lida Agachagua:";
                txtHistorial = "SENSEI";
                txtNoPartidas = "Weiriti siyei lida agachagua jenia.";
                txtErrorSesion = "AGUYUGA ME";
                txtScroll = "ALUJA";
                txtFecha = "DAN: ";
                txtHistEstrellas = "CHULUGATI: ";
                txtHistTiempo = "DAN: ";
                txtHistCompletado = "Lidoun";
                break;
            case "fra":
                txtEstrellas = "Étoiles Totales:";
                txtNivelMax = "Niveau Maximum:";
                txtNivel = "Niveau ";
                txtPartidasJugadas = "Parties Jouées:";
                txtTiempoJugado = "Temps de Jeu:";
                txtHistorial = "HISTORIQUE";
                txtNoPartidas = "Aucune partie enregistrée pour le moment.";
                txtErrorSesion = "CONNECTEZ-VOUS";
                txtScroll = "DÉFILER";
                txtFecha = "DATE: ";
                txtHistEstrellas = "ÉTOILES: ";
                txtHistTiempo = "TEMPS: ";
                txtHistCompletado = "Terminé";
                break;
            case "heb":
                txtEstrellas = "כוכבים סך הכל:";
                txtNivelMax = "רמה מקסימלית:";
                txtNivel = "רמה ";
                txtPartidasJugadas = "משחקים ששוחקו:";
                txtTiempoJugado = "זמן משחק:";
                txtHistorial = "היסטוריה";
                txtNoPartidas = "אין משחקים מוקלטים עדיין.";
                txtErrorSesion = "התחבר";
                txtScroll = "גלול";
                txtFecha = "תאריך: ";
                txtHistEstrellas = "כוכבים: ";
                txtHistTiempo = "זמן: ";
                txtHistCompletado = "הושלם";
                break;
            case "esp":
            case "spa":
            default:
                txtEstrellas = "Estrellas Totales:";
                txtNivelMax = "Máximo Nivel:";
                txtNivel = "Nivel ";
                txtPartidasJugadas = "Partidas Jugadas:";
                txtTiempoJugado = "Tiempo Jugado:";
                txtHistorial = "HISTORIAL";
                txtNoPartidas = "No hay partidas registradas aún.";
                txtErrorSesion = "INICIA SESIÓN";
                txtScroll = "DESLIZAR";
                txtFecha = "FECHA: ";
                txtHistEstrellas = "ESTRELLAS: ";
                txtHistTiempo = "TIEMPO: ";
                txtHistCompletado = "Completado";
                break;
        }
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();
        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        inicializarIdioma(idm);

        fondoStatsTex = new Texture(Gdx.files.internal("imgMenus/fondo_estadisticas_" + idm + ".png"));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));

        icoEstrellaTex = new Texture(Gdx.files.internal("imgMenus/ico_estrella.png"));
        icoTiempoTex = new Texture(Gdx.files.internal("imgMenus/ico_tiempo.png"));
        icoFechaTex = new Texture(Gdx.files.internal("imgMenus/ico_fecha.png"));

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, 0.6f));
        pixmap.fill();
        texturaFondoContenedor = new Texture(pixmap);
        pixmap.dispose();

        Label.LabelStyle estiloBase = skin.get(Label.LabelStyle.class);

        Label.LabelStyle estiloTitulo = new Label.LabelStyle(estiloBase);
        if (estiloBase.font != null) {
            fuenteTitulo = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteTitulo.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            fuenteTitulo.getData().setScale(1.3f);
            estiloTitulo.font = fuenteTitulo;
            estiloTitulo.fontColor = Color.valueOf("FF941A");
        }

        Label.LabelStyle estiloEtiquetas = new Label.LabelStyle(estiloBase);
        Label.LabelStyle estiloValores = new Label.LabelStyle(estiloBase);
        Label.LabelStyle estiloHistorialTexto = new Label.LabelStyle(estiloBase);

        if (estiloBase.font != null) {
            fuenteContenido = new BitmapFont(estiloBase.font.getData().getFontFile(), false);
            fuenteContenido.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            fuenteContenido.getData().setScale(1.1f);

            estiloEtiquetas.font = fuenteContenido;
            estiloEtiquetas.fontColor = Color.valueOf("EAEAEA");

            estiloValores.font = fuenteContenido;
            estiloValores.fontColor = Color.GOLD;

            estiloHistorialTexto.font = fuenteContenido;
            estiloHistorialTexto.fontColor = Color.WHITE;
        }

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(new TextureRegion(fondoStatsTex)));
        stage.addActor(rootTable);

        Table panelCentral = new Table();
        panelCentral.top();
        rootTable.add(panelCentral).expand().fill().padTop(170);

        if (usuarioActivo != null) {

            Table pizarraContenedora = new Table();
            pizarraContenedora.setBackground(new TextureRegionDrawable(new TextureRegion(texturaFondoContenedor)));
            pizarraContenedora.pad(20);

            Table contenidoScrollable = new Table();
            contenidoScrollable.top().center();

            Table tarjetaStats = new Table();
            tarjetaStats.setBackground(skin.newDrawable("white", new Color(0, 0, 0, 0.3f)));
            tarjetaStats.pad(15);

            Table filasStats = new Table();
            filasStats.defaults().padTop(6).padBottom(6).padLeft(10).padRight(10).left();

            filasStats.add(new Label(txtEstrellas, estiloEtiquetas));
            filasStats.add(new Label(String.valueOf(usuarioActivo.getEstrellasTotales()), estiloValores)).row();

            filasStats.add(new Label(txtNivelMax, estiloEtiquetas));
            filasStats.add(new Label(txtNivel + usuarioActivo.getNivelesCompletados(), estiloValores)).row();

            filasStats.add(new Label(txtPartidasJugadas, estiloEtiquetas));
            filasStats.add(new Label(String.valueOf(usuarioActivo.getPartidasJugadas()), estiloValores)).row();

            filasStats.add(new Label(txtTiempoJugado, estiloEtiquetas));
            float tiempoEnMinutos = usuarioActivo.getTiempoTotalJugado() / 60f;
            filasStats.add(new Label(String.format("%.2f min", tiempoEnMinutos), estiloValores)).row();

            tarjetaStats.add(filasStats).fillX().expandX();
            contenidoScrollable.add(tarjetaStats).width(420).padBottom(25).row();

            Label lblHistorial = new Label(txtHistorial, estiloTitulo);
            contenidoScrollable.add(lblHistorial).padBottom(15).center().row();

            if (usuarioActivo.getHistorialPartidas().isEmpty()) {
                Table tarjetaVacia = new Table();
                tarjetaVacia.setBackground(skin.newDrawable("white", new Color(0, 0, 0, 0.3f)));
                tarjetaVacia.pad(15);

                Label lblVacio = new Label(txtNoPartidas, estiloEtiquetas);
                lblVacio.setAlignment(com.badlogic.gdx.utils.Align.center);

                tarjetaVacia.add(lblVacio);
                contenidoScrollable.add(tarjetaVacia).width(420).row();
            } else {
                for (String registroOriginal : usuarioActivo.getHistorialPartidas()) {

                    Table tarjetaPartida = new Table();
                    tarjetaPartida.setBackground(skin.newDrawable("white", new Color(0, 0, 0, 0.3f)));
                    tarjetaPartida.pad(12);
                    tarjetaPartida.left();

                    armarTarjetaConFotos(tarjetaPartida, registroOriginal, estiloHistorialTexto);

                    contenidoScrollable.add(tarjetaPartida).width(420).padBottom(12).row();
                }
            }

            ScrollPane scrollGeneral = new ScrollPane(contenidoScrollable, skin);
            scrollGeneral.setFadeScrollBars(false);
            scrollGeneral.setScrollingDisabled(true, false);

            pizarraContenedora.add(scrollGeneral).expand().fill().row();

            if (!usuarioActivo.getHistorialPartidas().isEmpty()) {
                Label.LabelStyle estiloGuia = new Label.LabelStyle(estiloEtiquetas);
                estiloGuia.fontColor = Color.WHITE;

                Label lblIndicadorScroll = new Label("[ " + txtScroll + " ]", estiloGuia);
                lblIndicadorScroll.setAlignment(com.badlogic.gdx.utils.Align.center);

                pizarraContenedora.add(lblIndicadorScroll).padTop(8).center().fillX();
            }

            panelCentral.add(pizarraContenedora).width(480).height(510).expandX().center().row();

        } else {
            Label lblError = new Label(txtErrorSesion, estiloTitulo);
            panelCentral.add(lblError).padTop(150).row();
        }

        panelCentral.add().expandY().row();

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
        filaInferior.add(btnVolver).width(55).height(55).padLeft(25).padBottom(25);

        panelCentral.add(filaInferior).fillX().left();
    }

    private void armarTarjetaConFotos(Table tarjeta, String original, Label.LabelStyle estiloTexto) {
        try {
            String limpia = original.replace("⭐", "")
                    .replace("⏱️", "")
                    .replace("📅", "")
                    .replaceAll("[^\\x00-\\x7F\\p{InHebrew}]", "")
                    .trim();

            String[] partes = limpia.split(" \\| ");

            if (partes.length > 0) {
                String encabezado = partes[0].trim();
                if (encabezado.startsWith("Nivel")) {
                    encabezado = encabezado.replace("Nivel", txtNivel)
                            .replace("- Completado", "- " + txtHistCompletado)
                            .replace("Completado", txtHistCompletado);
                }
                Label lblTituloPartida = new Label(encabezado, estiloTexto);
                lblTituloPartida.setColor(Color.valueOf("FFB84D")); 
                tarjeta.add(lblTituloPartida).left().colspan(2).padBottom(6).row();
            }

            for (int i = 1; i < partes.length; i++) {
                String linea = partes[i].trim();
                Image imgIcono = null;
                String textoFinal = "";

                if (linea.toLowerCase().startsWith("estrellas:")) {
                    String valor = linea.substring(linea.indexOf(":") + 1).trim();
                    imgIcono = new Image(icoEstrellaTex);
                    textoFinal = txtHistEstrellas + valor;
                } else if (linea.toLowerCase().startsWith("tiempo:")) {
                    String valor = linea.substring(linea.indexOf(":") + 1).trim();
                    imgIcono = new Image(icoTiempoTex);
                    textoFinal = txtHistTiempo + valor;
                } else if (linea.toLowerCase().startsWith("fecha:")) {
                    String fechaCruda = linea.substring(linea.indexOf(":") + 1).trim();
                    imgIcono = new Image(icoFechaTex);
                    textoFinal = txtFecha + simplificarFecha(fechaCruda);
                } else if (!linea.isEmpty()) {
                    textoFinal = linea;
                }

                if (!textoFinal.isEmpty()) {
                    if (imgIcono != null) {
                        tarjeta.add(imgIcono).size(18, 18).left().padLeft(10).padRight(8).padBottom(3);
                    } else {
                        tarjeta.add().width(18); 
                    }

                    Label lblLinea = new Label(textoFinal, estiloTexto);
                    tarjeta.add(lblLinea).left().padBottom(3).row();
                }
            }
        } catch (Exception e) {
            tarjeta.add(new Label(original.replaceAll("[^\\x00-\\x7F]", ""), estiloTexto)).left().row();
        }
    }

    private String simplificarFecha(String fechaCruda) {
        try {
            String[] segmentos = fechaCruda.split(" ");
            if (segmentos.length >= 4) {
                String mes = segmentos[1];
                String dia = segmentos[2];
                String tiempo = segmentos[3];

                if (tiempo.contains(":")) {
                    String[] hms = tiempo.split(":");
                    if (hms.length >= 2) {
                        tiempo = hms[0] + ":" + hms[1];
                    }
                }
                return mes + " " + dia + " - " + tiempo;
            }
        } catch (Exception e) {
        }
        return fechaCruda;
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
        if (fondoStatsTex != null) {
            fondoStatsTex.dispose();
        }
        if (btnVolverTex != null) {
            btnVolverTex.dispose();
        }
        if (texturaFondoContenedor != null) {
            texturaFondoContenedor.dispose();
        }
        if (fuenteTitulo != null) {
            fuenteTitulo.dispose();
        }
        if (fuenteContenido != null) {
            fuenteContenido.dispose();
        }

        if (icoEstrellaTex != null) {
            icoEstrellaTex.dispose();
        }
        if (icoTiempoTex != null) {
            icoTiempoTex.dispose();
        }
        if (icoFechaTex != null) {
            icoFechaTex.dispose();
        }
    }
}
