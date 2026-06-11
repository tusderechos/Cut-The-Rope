/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.graficos.GestorFuentes;
import com.tusderechos.Juego.graficos.TexturasInterfaz;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.GestorRetos;

public class PantallaRivalidad extends ScreenAdapter {
    private static final int PuntajeObjetivoMinimo = 0;
    private static final int PuntajeObjetivoMaximo = 4000;
    private static final int PasoPuntajeObjetivo = 250;
    private final Juego JuegoAplicacion;
    private final ColorDulce ColorDulceActual;
    private final ColorMonstruo ColorMonstruoActual;
    private Stage StageActual;
    private BitmapFont FuenteTitulo;
    private BitmapFont FuenteBoton;
    private Texture TexturaBotonPrincipal;
    private Texture TexturaBotonPrincipalPresionado;
    private Texture TexturaBotonSecundario;
    private Texture TexturaBotonSecundarioPresionado;
    private Texture TexturaBotonDificultad;
    private Texture TexturaBotonDificultadSeleccionada;
    private TextButton.TextButtonStyle EstiloPrincipal;
    private TextButton.TextButtonStyle EstiloSecundario;
    private TextButton.TextButtonStyle EstiloDificultad;
    private TextButton.TextButtonStyle EstiloDificultadSeleccionada;
    private Label.LabelStyle EstiloTitulo;
    private Label.LabelStyle EstiloTexto;
    private Table Raiz;
    private CategoriaDificultad CategoriaActual = CategoriaDificultad.Media;
    private int NumeroNivelActual = 1;
    private int EstrellasObjetivo = 2;
    private int PuntajeObjetivo = 3200;

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual) {
        this(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual, CategoriaDificultad.Media);
    }

    public PantallaRivalidad(Juego JuegoAplicacion, ColorDulce ColorDulceActual, ColorMonstruo ColorMonstruoActual, CategoriaDificultad CategoriaInicial) {
        this.JuegoAplicacion = JuegoAplicacion;
        this.ColorDulceActual = ColorDulceActual;
        this.ColorMonstruoActual = ColorMonstruoActual;
        CambiarCategoria(CategoriaInicial == null ? CategoriaDificultad.Media : CategoriaInicial);
    }

    public CategoriaDificultad ObtenerCategoriaActual() {
        return CategoriaActual;
    }

    public int ObtenerNumeroNivelActual() {
        return NumeroNivelActual;
    }

    public int ObtenerEstrellasObjetivo() {
        return EstrellasObjetivo;
    }

    public int ObtenerPuntajeObjetivo() {
        return PuntajeObjetivo;
    }

    public void CambiarCategoria(CategoriaDificultad CategoriaNueva) {
        if (CategoriaNueva == null) {
            throw new IllegalArgumentException("La categoria del reto no puede ser nula");
        }
        CategoriaActual = CategoriaNueva;
        NumeroNivelActual = Math.min(NumeroNivelActual, FabricaNiveles.CantidadNiveles(CategoriaActual));
        NumeroNivelActual = Math.max(1, NumeroNivelActual);
    }

    public void AjustarNivel(int Cambio) {
        int CantidadNiveles = FabricaNiveles.CantidadNiveles(CategoriaActual);
        NumeroNivelActual = LimitarValor(NumeroNivelActual + Cambio, 1, CantidadNiveles);
    }

    public void AjustarEstrellasObjetivo(int Cambio) {
        EstrellasObjetivo = LimitarValor(EstrellasObjetivo + Cambio, 0, 3);
    }

    public void AjustarPuntajeObjetivo(int Cambio) {
        PuntajeObjetivo = LimitarValor(PuntajeObjetivo + Cambio, PuntajeObjetivoMinimo, PuntajeObjetivoMaximo);
    }

    public DatosReto CrearRetoActual() {
        return new DatosReto(CategoriaActual, NumeroNivelActual, "Admin", PuntajeObjetivo, EstrellasObjetivo);
    }

    @Override
    public void show() {
        StageActual = new Stage(new ScreenViewport());
        FuenteTitulo = GestorFuentes.CrearFuenteGoodDog(40);
        FuenteBoton = GestorFuentes.CrearFuenteGoodDog(25);
        EstiloTitulo = new Label.LabelStyle(FuenteTitulo, Color.WHITE);
        EstiloTexto = new Label.LabelStyle(FuenteBoton, Color.WHITE);
        CrearTexturas();
        CrearEstilos();
        Raiz = new Table();
        Raiz.setFillParent(true);
        Raiz.pad(22f);
        StageActual.addActor(Raiz);
        ConstruirContenido();
        Gdx.input.setInputProcessor(StageActual);
    }

    private void CrearTexturas() {
        TexturaBotonPrincipal = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("2fae63"), Color.valueOf("a6f5b8"), Color.valueOf("51d985"));
        TexturaBotonPrincipalPresionado = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("247f4b"), Color.valueOf("d0ffd9"), Color.valueOf("38b96a"));
        TexturaBotonSecundario = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("3b77c4"), Color.valueOf("b9dcff"), Color.valueOf("67a9f0"));
        TexturaBotonSecundarioPresionado = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("285996"), Color.valueOf("d5ecff"), Color.valueOf("4c8bd2"));
        TexturaBotonDificultad = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("7852bf"), Color.valueOf("d5c3ff"), Color.valueOf("9d76e8"));
        TexturaBotonDificultadSeleccionada = TexturasInterfaz.CrearTexturaBoton(Color.valueOf("f0a13a"), Color.valueOf("ffe0a2"), Color.valueOf("ffc65a"));
    }

    private void CrearEstilos() {
        EstiloPrincipal = CrearEstilo(TexturaBotonPrincipal, TexturaBotonPrincipalPresionado);
        EstiloSecundario = CrearEstilo(TexturaBotonSecundario, TexturaBotonSecundarioPresionado);
        EstiloDificultad = CrearEstilo(TexturaBotonDificultad, TexturaBotonDificultadSeleccionada);
        EstiloDificultadSeleccionada = CrearEstilo(TexturaBotonDificultadSeleccionada, TexturaBotonDificultad);
    }

    private TextButton.TextButtonStyle CrearEstilo(Texture TexturaNormal, Texture TexturaPresionada) {
        TextButton.TextButtonStyle Estilo = new TextButton.TextButtonStyle();
        Estilo.font = FuenteBoton;
        Estilo.fontColor = Color.WHITE;
        Estilo.downFontColor = Color.WHITE;
        Estilo.up = new TextureRegionDrawable(TexturaNormal);
        Estilo.down = new TextureRegionDrawable(TexturaPresionada);

        return Estilo;
    }

    private TextButton CrearBoton(String Texto, TextButton.TextButtonStyle Estilo) {
        TextButton Boton = new TextButton(Texto, Estilo);
        Boton.getLabel().setAlignment(Align.center);
        Boton.getLabelCell().padBottom(4f);

        return Boton;
    }

    private void ConstruirContenido() {
        Raiz.clearChildren();
        Raiz.top();
        Table Panel = new Table();
        Raiz.add(Panel).width(540f).padTop(18f);
        Panel.add(new Label("Rivalidad", EstiloTitulo)).padBottom(20f);
        Panel.row();
        Table FilaCategorias = new Table();
        for (final CategoriaDificultad Categoria : CategoriaDificultad.values()) {
            TextButton BotonCategoria = CrearBoton(Categoria.name(), Categoria == CategoriaActual ? EstiloDificultadSeleccionada : EstiloDificultad);
            BotonCategoria.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent Event, Actor ActorActual) {
                    CambiarCategoria(Categoria);
                    ConstruirContenido();
                }
            });
            FilaCategorias.add(BotonCategoria).width(130f).height(54f).pad(7f);
        }
        Panel.add(FilaCategorias).padBottom(14f);
        Panel.row();
        AgregarControlNumerico(Panel, "Nivel", String.valueOf(NumeroNivelActual), 120f, new Runnable() {
            @Override
            public void run() {
                AjustarNivel(-1);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarNivel(1);
            }
        });
        AgregarControlNumerico(Panel, "Estrellas", String.valueOf(EstrellasObjetivo), 120f, new Runnable() {
            @Override
            public void run() {
                AjustarEstrellasObjetivo(-1);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarEstrellasObjetivo(1);
            }
        });
        AgregarControlNumerico(Panel, "Puntaje", String.valueOf(PuntajeObjetivo), 140f, new Runnable() {
            @Override
            public void run() {
                AjustarPuntajeObjetivo(-PasoPuntajeObjetivo);
            }
        }, new Runnable() {
            @Override
            public void run() {
                AjustarPuntajeObjetivo(PasoPuntajeObjetivo);
            }
        });
        Label ResumenReto = new Label(TextoResumenReto(), EstiloTexto);
        ResumenReto.setWrap(true);
        ResumenReto.setAlignment(Align.center);
        Panel.add(ResumenReto).width(500f).padTop(18f).padBottom(16f);
        Panel.row();
        Table FilaAcciones = new Table();
        TextButton BotonVolver = CrearBoton("Volver", EstiloSecundario);
        BotonVolver.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                JuegoAplicacion.CambiarPantalla(new PantallaSeleccionNivel(JuegoAplicacion, ColorDulceActual, ColorMonstruoActual));
            }
        });
        TextButton BotonIniciar = CrearBoton("Iniciar reto", EstiloPrincipal);
        BotonIniciar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                DatosReto Reto = CrearRetoActual();
                JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, GestorRetos.ObtenerNivelReto(Reto), new PersonalizacionDulce(ColorDulceActual), new PersonalizacionMonstruo(ColorMonstruoActual), Reto));
            }
        });
        FilaAcciones.add(BotonVolver).width(150f).height(56f).pad(8f);
        FilaAcciones.add(BotonIniciar).width(190f).height(56f).pad(8f);
        Panel.add(FilaAcciones);
    }

    private void AgregarControlNumerico(Table Panel, String Titulo, String Valor, float AnchoValor, final Runnable AccionMenos, final Runnable AccionMas) {
        Table FilaControl = new Table();
        Label Etiqueta = new Label(Titulo, EstiloTexto);
        Label EtiquetaValor = new Label(Valor, EstiloTexto);
        EtiquetaValor.setAlignment(Align.center);
        TextButton BotonMenos = CrearBoton("-", EstiloSecundario);
        BotonMenos.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                AccionMenos.run();
                ConstruirContenido();
            }
        });
        TextButton BotonMas = CrearBoton("+", EstiloSecundario);
        BotonMas.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent Event, Actor ActorActual) {
                AccionMas.run();
                ConstruirContenido();
            }
        });
        FilaControl.add(Etiqueta).width(150f).left().padRight(10f);
        FilaControl.add(BotonMenos).width(64f).height(44f).pad(5f);
        FilaControl.add(EtiquetaValor).width(AnchoValor).center().padLeft(12f).padRight(12f);
        FilaControl.add(BotonMas).width(64f).height(44f).pad(5f);
        Panel.add(FilaControl).width(500f).padTop(10f).padBottom(2f);
        Panel.row();
    }

    private String TextoResumenReto() {
        return CategoriaActual.name() + " " + NumeroNivelActual + " - " + PuntajeObjetivo + " pts / " + EstrellasObjetivo + " estrellas";
    }

    private int LimitarValor(int Valor, int Minimo, int Maximo) {
        return Math.max(Minimo, Math.min(Maximo, Valor));
    }

    @Override
    public void render(float Delta) {
        ScreenUtils.clear(0.04f, 0.05f, 0.07f, 1f);
        StageActual.act(Delta);
        StageActual.draw();
    }

    @Override
    public void resize(int Width, int Height) {
        if (StageActual != null && Width > 0 && Height > 0) {
            StageActual.getViewport().update(Width, Height, true);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (StageActual != null) {
            StageActual.dispose();
        }
        if (FuenteTitulo != null) {
            FuenteTitulo.dispose();
        }
        if (FuenteBoton != null) {
            FuenteBoton.dispose();
        }
        if (TexturaBotonPrincipal != null) {
            TexturaBotonPrincipal.dispose();
        }
        if (TexturaBotonPrincipalPresionado != null) {
            TexturaBotonPrincipalPresionado.dispose();
        }
        if (TexturaBotonSecundario != null) {
            TexturaBotonSecundario.dispose();
        }
        if (TexturaBotonSecundarioPresionado != null) {
            TexturaBotonSecundarioPresionado.dispose();
        }
        if (TexturaBotonDificultad != null) {
            TexturaBotonDificultad.dispose();
        }
        if (TexturaBotonDificultadSeleccionada != null) {
            TexturaBotonDificultadSeleccionada.dispose();
        }
    }
}
