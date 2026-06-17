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

    public StatsScreen(Game game) {
        this.parentGame = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = SkinMenu.Crear();
        Usuario usuarioActivo = SistemaAutenticacion.getUsuarioActivo();

        String idm = ConfiguracionJuego.idiomaActivo.toLowerCase();

        fondoStatsTex = new Texture(Gdx.files.internal("imgMenus/fondo_estadisticas_" + idm + ".png"));
        btnVolverTex = new Texture(Gdx.files.internal("imgMenus/btn_volver.png"));

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

            filasStats.add(new Label("Estrellas Totales / Total Stars:", estiloEtiquetas));
            filasStats.add(new Label(String.valueOf(usuarioActivo.getEstrellasTotales()), estiloValores)).row();

            filasStats.add(new Label("Maximo Nivel / Max Level:", estiloEtiquetas));
            filasStats.add(new Label("Nivel / Level " + usuarioActivo.getNivelesCompletados(), estiloValores)).row();

            filasStats.add(new Label("Partidas Jugadas / Matches Played:", estiloEtiquetas));
            filasStats.add(new Label(String.valueOf(usuarioActivo.getPartidasJugadas()), estiloValores)).row();

            filasStats.add(new Label("Tiempo Jugado / Playtime:", estiloEtiquetas));
            float tiempoEnMinutos = usuarioActivo.getTiempoTotalJugado() / 60f;
            filasStats.add(new Label(String.format("%.2f min", tiempoEnMinutos), estiloValores)).row();

            tarjetaStats.add(filasStats).fillX().expandX();

            contenidoScrollable.add(tarjetaStats).width(420).padBottom(25).row();

            Label lblHistorial = new Label("HISTORIAL / HISTORY", estiloTitulo);
            contenidoScrollable.add(lblHistorial).padBottom(15).center().row();

            if (usuarioActivo.getHistorialPartidas().isEmpty()) {
                Table tarjetaVacia = new Table();
                tarjetaVacia.setBackground(skin.newDrawable("white", new Color(0, 0, 0, 0.3f)));
                tarjetaVacia.pad(15);

                Label lblVacio = new Label("No hay partidas registradas aun.\nNo matches recorded yet.", estiloEtiquetas);
                lblVacio.setAlignment(com.badlogic.gdx.utils.Align.center);

                tarjetaVacia.add(lblVacio);
                contenidoScrollable.add(tarjetaVacia).width(420).row();
            } else {
                for (String registroOriginal : usuarioActivo.getHistorialPartidas()) {

                    String registroFormateado = formatearRegistroHistorial(registroOriginal);

                    Table tarjetaPartida = new Table();
                    tarjetaPartida.setBackground(skin.newDrawable("white", new Color(0, 0, 0, 0.3f)));
                    tarjetaPartida.pad(15);

                    Label lblPartida = new Label(registroFormateado, estiloHistorialTexto);
                    lblPartida.setWrap(true);

                    tarjetaPartida.add(lblPartida).left().expandX().fillX();

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

                Label lblIndicadorScroll = new Label("[ SCROLL ]", estiloGuia);
                lblIndicadorScroll.setAlignment(com.badlogic.gdx.utils.Align.center);

                pizarraContenedora.add(lblIndicadorScroll).padTop(8).center().fillX();
            }

            panelCentral.add(pizarraContenedora).width(480).height(510).expandX().center().row();

        } else {
            Label lblError = new Label("INICIA SESION / LOG IN", estiloTitulo);
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

    private String formatearRegistroHistorial(String original) {
        try {
            String[] partes = original.split(" \\| ");

            StringBuilder constructor = new StringBuilder();

            if (partes.length > 0) {
                constructor.append(partes[0]).append("\n");
            }

            for (int i = 1; i < partes.length; i++) {
                String linea = partes[i];

                if (linea.startsWith("Estrellas:")) {
                    constructor.append("  • ").append(linea.toUpperCase());
                } else if (linea.startsWith("Tiempo:")) {
                    constructor.append("  • ").append(linea.toUpperCase());
                } else if (linea.startsWith("Fecha:")) {
                    String fechaCruda = linea.replace("Fecha: ", "");
                    String fechaFormateada = simplificarFecha(fechaCruda);
                    constructor.append("  • FECHA / DATE: ").append(fechaFormateada);
                } else {
                    constructor.append("  • ").append(linea);
                }

                if (i < partes.length - 1) {
                    constructor.append("\n");
                }
            }

            return constructor.toString();
        } catch (Exception e) {
            return original.replace("⭐ ", "").replace("⏱️ ", "").replace("📅 ", "");
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
    }
}
