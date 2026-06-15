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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.audio.GestorAudio;
import com.tusderechos.Juego.entidades.Dulce;
import com.tusderechos.Juego.entidades.Burbuja;
import com.tusderechos.Juego.entidades.Cuerda;
import com.tusderechos.Juego.entidades.CuerdaCortadaVisual;
import com.tusderechos.Juego.entidades.Monstruo;
import com.tusderechos.Juego.entidades.Estrella;
import com.tusderechos.Juego.entidades.PlataformaMovil;
import com.tusderechos.Juego.graficos.GestorFuentes;
import com.tusderechos.Juego.graficos.GestorTexturas;
import com.tusderechos.Juego.niveles.DatosCuerda;
import com.tusderechos.Juego.niveles.DatosBurbuja;
import com.tusderechos.Juego.niveles.DatosEstrella;
import com.tusderechos.Juego.niveles.DatosObstaculo;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.persistencia.GuardadorPartidasBinario;
import com.tusderechos.Juego.persistencia.RegistroPartida;
import com.tusderechos.Juego.rivalidad.DatosReto;
import com.tusderechos.Juego.rivalidad.GestorRetos;
import com.tusderechos.Juego.rivalidad.ResultadoReto;
import com.tusderechos.Juego.utilidades.ConstantesJuego;
import com.tusderechos.Juego.utilidades.CalculadoraPuntaje;
import com.tusderechos.Juego.enums.EstadoNivel;
import com.tusderechos.Juego.obstaculos.Obstaculo;
import com.tusderechos.Juego.obstaculos.ObstaculoPeligroso;
import LogicaArchivos.Usuarios.SistemaAutenticacion;
import LogicaArchivos.Usuarios.Usuario;
import ManejoArchivos.Archivos.ManejadorArchivos;
import java.util.List;

public class PantallaJuego extends ScreenAdapter {
    private static final float PasoFisica = 1f / 60f;
    private final Juego JuegoAplicacion;
    private final DatosNivel DatosNivelActual;
    private final PersonalizacionDulce PersonalizacionDulceActual;
    private final PersonalizacionMonstruo PersonalizacionMonstruoActual;
    private final DatosReto RetoActual;
    private World Mundo;
    private OrthographicCamera Camara;
    private FitViewport Viewport;
    private ShapeRenderer ShapeRendererActual;
    private SpriteBatch Batch;
    private BitmapFont Fuente;
    private BitmapFont FuenteInterfaz;
    private GlyphLayout MedidorTexto;
    private GlyphLayout MedidorTextoInterfaz;
    private GestorTexturas GestorTexturasActual;
    private Dulce DulceActual;
    private Monstruo MonstruoActual;
    private final Array<Cuerda> Cuerdas = new Array<>();
    private final Array<CuerdaCortadaVisual> CuerdasCortadas = new Array<>();
    private final Array<Estrella> Estrellas = new Array<>();
    private final Array<Burbuja> Burbujas = new Array<>();
    private final Array<Obstaculo> Obstaculos = new Array<>();
    private final Array<EfectoVisualTemporal> EfectosVisuales = new Array<>();
    private final Rectangle BotonSalir = new Rectangle(0.23f, 7.38f, 1.00f, 0.46f);
    private final Rectangle BotonResultadoSalir = new Rectangle(0.65f, 1.55f, 1.35f, 0.52f);
    private final Rectangle BotonSiguiente = new Rectangle(2.75f, 1.55f, 1.35f, 0.52f);
    private final Rectangle PanelConfirmacionSalir = new Rectangle(0.55f, 2.65f, 3.7f, 1.85f);
    private final Rectangle BotonCancelarSalida = new Rectangle(0.85f, 2.94f, 1.25f, 0.50f);
    private final Rectangle BotonConfirmarSalida = new Rectangle(2.75f, 2.94f, 1.05f, 0.50f);
    private final Rectangle FondoHud = new Rectangle(0f, 7.12f, ConstantesJuego.AnchoMundo, 0.88f);
    private float TiempoNivel;
    private int FallosNivel;
    private int EstrellasRecolectadas;
    private int PuntajeFinal;
    private ResultadoNivel ResultadoNivelActual;
    private ResultadoReto ResultadoRetoActual;
    private AnimacionPanelResultado AnimacionResultadoActual;
    private EstadoNivel EstadoNivelActual = EstadoNivel.Jugando;
    private boolean ConfirmacionSalidaActiva;
    private float TiempoEstadoFallo;
    private float TiempoDulceDetenido;
    private String MensajeFallo = "";
    private PlataformaMovil PlataformaMovilActual;
    private GestorAudio GestorAudioActual;
    private float AcumuladorFisica;
    private float AlphaEntrada = 1f;
    private boolean ArrastreCorteActivo;
    private Vector2 PuntoArrastreAnterior;

    public PantallaJuego(Juego JuegoAplicacion, DatosNivel DatosNivelActual, PersonalizacionDulce PersonalizacionDulceActual, PersonalizacionMonstruo PersonalizacionMonstruoActual) {
        this(JuegoAplicacion, DatosNivelActual, PersonalizacionDulceActual, PersonalizacionMonstruoActual, null);
    }

    public PantallaJuego(Juego JuegoAplicacion, DatosNivel DatosNivelActual, PersonalizacionDulce PersonalizacionDulceActual, PersonalizacionMonstruo PersonalizacionMonstruoActual, DatosReto RetoActual) {
        this.JuegoAplicacion = JuegoAplicacion;
        this.DatosNivelActual = DatosNivelActual;
        this.PersonalizacionDulceActual = PersonalizacionDulceActual;
        this.PersonalizacionMonstruoActual = PersonalizacionMonstruoActual;
        this.RetoActual = RetoActual;
    }

    @Override
    public void show() {
        Mundo = new World(new Vector2(0f, ConstantesJuego.Gravedad), true);
        Camara = new OrthographicCamera(ConstantesJuego.AnchoMundo, ConstantesJuego.AltoMundo);
        Viewport = new FitViewport(ConstantesJuego.AnchoMundo, ConstantesJuego.AltoMundo, Camara);
        Viewport.apply(true);
        ShapeRendererActual = new ShapeRenderer();
        Batch = new SpriteBatch();
        MedidorTexto = new GlyphLayout();
        MedidorTextoInterfaz = new GlyphLayout();
        Fuente = GestorFuentes.CrearFuenteGoodDog(34);
        FuenteInterfaz = new BitmapFont();
        FuenteInterfaz.getData().setScale(1.05f);
        GestorTexturasActual = new GestorTexturas();
        GestorAudioActual = new GestorAudio();
        GestorAudioActual.IniciarMusica();
        DulceActual = new Dulce(Mundo, DatosNivelActual.ObtenerPosicionDulce(), PersonalizacionDulceActual);
        MonstruoActual = new Monstruo(DatosNivelActual.ObtenerPosicionMonstruo(), PersonalizacionMonstruoActual);
        for (DatosCuerda DatosCuerdaActual : DatosNivelActual.ObtenerCuerdas()) {
            Cuerdas.add(new Cuerda(Mundo, DatosCuerdaActual.ObtenerAncla(), DatosCuerdaActual.ObtenerLongitud(), DulceActual.ObtenerCuerpo()));
        }
        for (DatosEstrella DatosEstrellaActual : DatosNivelActual.ObtenerEstrellas()) {
            Estrellas.add(new Estrella(DatosEstrellaActual.ObtenerPosicion()));
        }
        for (DatosBurbuja DatosBurbujaActual : DatosNivelActual.ObtenerBurbujas()) {
            Burbujas.add(new Burbuja(DatosBurbujaActual.ObtenerPosicion(), DatosBurbujaActual.ObtenerRadio()));
        }
        for (DatosObstaculo DatosObstaculoActual : DatosNivelActual.ObtenerObstaculos()) {
            Obstaculos.add(new ObstaculoPeligroso(DatosObstaculoActual.ObtenerPosicion(), DatosObstaculoActual.ObtenerAncho(), DatosObstaculoActual.ObtenerAlto()));
        }
        if (DatosNivelActual.TienePlataformaMovil()) {
            PlataformaMovilActual = new PlataformaMovil(new Vector2(2.4f, 0.82f), 1.0f, 3.8f, 0.45f);
            MonstruoActual.EstablecerPosicion(PlataformaMovilActual.ObtenerPosicionMonstruo());
        }
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int Keycode) {
                if (Keycode == Input.Keys.ESCAPE) {
                    if (!NivelCompletado()) {
                        ConfirmacionSalidaActiva = !ConfirmacionSalidaActiva;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchDown(int ScreenX, int ScreenY, int Pointer, int Button) {
                if (Button != Input.Buttons.LEFT || AlphaEntrada > 0f || EstadoNivelActual == EstadoNivel.Fallando) {
                    return false;
                }
                Vector2 PuntoMundo = ConvertirPantallaAMundo(ScreenX, ScreenY);
                if (ConfirmacionSalidaActiva) {
                    ManejarClicConfirmacionSalida(PuntoMundo);
                } else if (NivelCompletado()) {
                    ManejarClicResultado(PuntoMundo);
                } else if (BotonSalir.contains(PuntoMundo)) {
                    ConfirmacionSalidaActiva = true;
                } else {
                    if (ReventarBurbujaCercana(PuntoMundo)) {
                        ArrastreCorteActivo = false;
                        PuntoArrastreAnterior = null;
                    } else {
                        ArrastreCorteActivo = true;
                        PuntoArrastreAnterior = PuntoMundo;
                    }
                }
                return true;
            }

            @Override
            public boolean touchDragged(int ScreenX, int ScreenY, int Pointer) {
                if (!ArrastreCorteActivo || AlphaEntrada > 0f || EstadoNivelActual != EstadoNivel.Jugando || ConfirmacionSalidaActiva) {
                    return false;
                }
                Vector2 PuntoMundo = ConvertirPantallaAMundo(ScreenX, ScreenY);
                CortarCuerdaPorArrastre(PuntoArrastreAnterior, PuntoMundo);
                PuntoArrastreAnterior = PuntoMundo;

                return true;
            }

            @Override
            public boolean touchUp(int ScreenX, int ScreenY, int Pointer, int Button) {
                ArrastreCorteActivo = false;
                PuntoArrastreAnterior = null;

                return true;
            }
        });
    }

    @Override
    public void render(float Delta) {
        ScreenUtils.clear(0.12f, 0.16f, 0.20f, 1f);
        AlphaEntrada = Math.max(0f, AlphaEntrada - Delta * 1.6f);
        if (EstadoNivelActual == EstadoNivel.Jugando && AlphaEntrada <= 0f) {
            float DeltaSimulado = Math.min(Delta, 0.25f);
            ActualizarFisica(DeltaSimulado);
            if (EstadoNivelActual == EstadoNivel.Jugando) {
                DetectarDulceDetenido(DeltaSimulado);
            }
        } else if (EstadoNivelActual == EstadoNivel.Fallando) {
            ActualizarTransicionFallo(Delta);
        }
        if (EstadoNivelActual == EstadoNivel.Reiniciando) {
            PantallaJuego Reinicio = new PantallaJuego(JuegoAplicacion, DatosNivelActual, PersonalizacionDulceActual, PersonalizacionMonstruoActual, RetoActual);
            Reinicio.FallosNivel = FallosNivel;
            JuegoAplicacion.CambiarPantalla(Reinicio);
            return;
        }
        for (CuerdaCortadaVisual CuerdaCortada : CuerdasCortadas) {
            CuerdaCortada.Actualizar(Delta);
        }
        for (int Indice = CuerdasCortadas.size - 1; Indice >= 0; Indice--) {
            if (CuerdasCortadas.get(Indice).EstaFinalizada()) {
                CuerdasCortadas.removeIndex(Indice);
            }
        }
        ActualizarAnimacionResultado(Delta);
        ActualizarEfectosVisuales(Delta);
        ShapeRendererActual.setProjectionMatrix(Camara.combined);
        DibujarFondoTextura();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ShapeRendererActual.begin(ShapeRenderer.ShapeType.Filled);
        DibujarFondoNivel(ShapeRendererActual);
        ShapeRendererActual.end();
        ShapeRendererActual.begin(ShapeRenderer.ShapeType.Filled);
        for (Cuerda CuerdaActual : Cuerdas) {
            CuerdaActual.Dibujar(ShapeRendererActual);
        }
        for (CuerdaCortadaVisual CuerdaCortada : CuerdasCortadas) {
            CuerdaCortada.Dibujar(ShapeRendererActual);
        }
        ShapeRendererActual.end();
        ShapeRendererActual.begin(ShapeRenderer.ShapeType.Filled);
        for (Estrella EstrellaActual : Estrellas) {
            if (GestorTexturasActual.ObtenerEstrella(false) == null) {
                EstrellaActual.Dibujar(ShapeRendererActual);
            }
        }
        for (Burbuja BurbujaActual : Burbujas) {
            BurbujaActual.Dibujar(ShapeRendererActual);
        }
        for (EfectoVisualTemporal EfectoVisual : EfectosVisuales) {
            EfectoVisual.Dibujar(ShapeRendererActual);
        }
        for (Obstaculo ObstaculoActual : Obstaculos) {
            ObstaculoActual.Dibujar(ShapeRendererActual);
        }
        if (PlataformaMovilActual != null) {
            PlataformaMovilActual.Dibujar(ShapeRendererActual);
        }
        if (GestorTexturasActual.ObtenerMonstruo(PersonalizacionMonstruoActual.ObtenerColorMonstruo()) == null) {
            MonstruoActual.Dibujar(ShapeRendererActual);
        }
        if (GestorTexturasActual.ObtenerDulce(PersonalizacionDulceActual.ObtenerColorDulce()) == null) {
            DulceActual.Dibujar(ShapeRendererActual);
        }
        ShapeRendererActual.end();
        DibujarSpritesMundo();
        ShapeRendererActual.begin(ShapeRenderer.ShapeType.Filled);
        if (!NivelCompletado()) {
            DibujarHud(ShapeRendererActual);
            DibujarBoton(ShapeRendererActual, BotonSalir, Color.valueOf("c74343"), Color.valueOf("ff9a7a"), Color.valueOf("ef6868"));
        }
        if (NivelCompletado() && !ConfirmacionSalidaActiva) {
            DibujarPanelResultado(ShapeRendererActual);
        }
        if (ConfirmacionSalidaActiva) {
            DibujarPanelConfirmacionSalida(ShapeRendererActual);
        }
        ShapeRendererActual.end();
        if (NivelCompletado() && !ConfirmacionSalidaActiva) {
            DibujarSpritesResultado();
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
        DibujarTextos();
        DibujarTransicion();
    }

    private void CortarCuerdaPorArrastre(Vector2 InicioTrazo, Vector2 FinTrazo) {
        if (InicioTrazo == null || FinTrazo == null || InicioTrazo.dst2(FinTrazo) < 0.0001f) {
            return;
        }
        Cuerda CuerdaCortada = null;
        for (Cuerda CuerdaActual : Cuerdas) {
            if (CuerdaActual.IntersectaTrazoDeCorte(InicioTrazo, FinTrazo)) {
                CuerdaCortada = CuerdaActual;
                break;
            }
        }
        if (CuerdaCortada == null) {
            return;
        }
        Vector2 Ancla = CuerdaCortada.ObtenerAncla();
        Vector2 Fin = CuerdaCortada.ObtenerFin();
        Vector2 PuntoDeCorte = CuerdaCortada.ProyectarTrazoDeCorte(InicioTrazo, FinTrazo);
        CuerdaCortada.Cortar();
        CuerdasCortadas.add(new CuerdaCortadaVisual(Ancla, PuntoDeCorte, Fin));
        CrearEfecto(PuntoDeCorte, 0.08f, 0.32f, new Color(0.82f, 0.70f, 0.46f, 1f));
        GestorAudioActual.ReproducirCorteCuerda();
    }

    private void RecolectarEstrellas() {
        for (Estrella EstrellaActual : Estrellas) {
            if (EstrellaActual.IntentarRecolectar(DulceActual.ObtenerCuerpo().getPosition())) {
                EstrellasRecolectadas++;
                CrearEfecto(DulceActual.ObtenerCuerpo().getPosition(), 0.12f, 0.48f, Color.GOLD);
                GestorAudioActual.ReproducirEstrella();
            }
        }
    }

    private boolean ReventarBurbujaCercana(Vector2 PuntoMundo) {
        for (Burbuja BurbujaActual : Burbujas) {
            if (!BurbujaActual.EstaAdherida() || !BurbujaActual.ContienePunto(PuntoMundo)) {
                continue;
            }
            BurbujaActual.Reventar();
            CrearEfecto(PuntoMundo, 0.18f, 0.62f, new Color(0.35f, 0.80f, 1f, 1f));
            GestorAudioActual.ReproducirBurbuja();
            return true;
        }
        return false;
    }

    private boolean HayBurbujaAdherida() {
        for (Burbuja BurbujaActual : Burbujas) {
            if (BurbujaActual.EstaAdherida()) {
                return true;
            }
        }
        return false;
    }

    private boolean HayCuerdaActiva() {
        for (Cuerda CuerdaActual : Cuerdas) {
            if (!CuerdaActual.EstaCortada()) {
                return true;
            }
        }
        return false;
    }

    private void AplicarFlotacion() {
        for (Burbuja BurbujaActual : Burbujas) {
            if (!BurbujaActual.EstaAdherida()) {
                continue;
            }
            DulceActual.ObtenerCuerpo().applyForceToCenter(0f, DulceActual.ObtenerCuerpo().getMass() * 14f, true);
            return;
        }
    }

    private void ActualizarFisica(float Delta) {
        AcumuladorFisica += Delta;
        while (AcumuladorFisica >= PasoFisica) {
            TiempoNivel += PasoFisica;
            ActualizarPlataforma(PasoFisica);
            AplicarFlotacion();
            Mundo.step(PasoFisica, 6, 2);
            LimitarVelocidadBurbuja();
            ActualizarBurbujas();
            ComprobarReglasPasoFisica();
            AcumuladorFisica -= PasoFisica;
            if (EstadoNivelActual != EstadoNivel.Jugando) {
                break;
            }
        }
    }

    private void LimitarVelocidadBurbuja() {
        if (!HayBurbujaAdherida()) {
            return;
        }
        Vector2 Velocidad = DulceActual.ObtenerCuerpo().getLinearVelocity();
        if (Velocidad.y > 1.8f) {
            DulceActual.ObtenerCuerpo().setLinearVelocity(Velocidad.x, 1.8f);
        }
    }

    private void ActualizarBurbujas() {
        Vector2 PosicionDulce = DulceActual.ObtenerCuerpo().getPosition();
        for (Burbuja BurbujaActual : Burbujas) {
            if (!BurbujaActual.EstaAdherida()) {
                continue;
            }
            BurbujaActual.SeguirDulce(PosicionDulce);
            return;
        }
        for (Burbuja BurbujaActual : Burbujas) {
            if (!BurbujaActual.IntentarAdherir(PosicionDulce)) {
                continue;
            }
            BurbujaActual.SeguirDulce(PosicionDulce);
            return;
        }
    }

    private void ComprobarReglasPasoFisica() {
        RecolectarEstrellas();
        Vector2 PosicionDulce = DulceActual.ObtenerCuerpo().getPosition();
        if (MonstruoActual.ContieneDulce(PosicionDulce)) {
            MostrarResultado();
            return;
        }
        DetectarFalloInstantaneo(PosicionDulce);
    }

    private void DetectarFalloInstantaneo(Vector2 Posicion) {
        if (Posicion.y < -0.6f || Posicion.x < -0.6f || Posicion.x > ConstantesJuego.AnchoMundo + 0.6f
            || (Posicion.y > ConstantesJuego.AltoMundo + 0.6f && HayBurbujaAdherida())) {
            IniciarFallo("El dulce se perdio");
            return;
        }
        for (Obstaculo ObstaculoActual : Obstaculos) {
            if (ObstaculoActual.TocaDulce(Posicion, ConstantesJuego.RadioDulce)) {
                IniciarFallo("El dulce toco un peligro");
                return;
            }
        }
    }

    private void DetectarDulceDetenido(float Delta) {
        Vector2 Posicion = DulceActual.ObtenerCuerpo().getPosition();
        boolean Detenido = DulceActual.ObtenerCuerpo().getLinearVelocity().len() < 0.05f;
        TiempoDulceDetenido = Detenido && !HayCuerdaActiva() && !HayBurbujaAdherida() ? TiempoDulceDetenido + Delta : 0f;
        if (TiempoDulceDetenido > 3f && !MonstruoActual.ContieneDulce(Posicion)) {
            IniciarFallo("Intento fallido");
        }
    }

    private void ActualizarPlataforma(float Delta) {
        if (PlataformaMovilActual == null) {
            return;
        }
        PlataformaMovilActual.Actualizar(Delta);
        MonstruoActual.EstablecerPosicion(PlataformaMovilActual.ObtenerPosicionMonstruo());
    }

    private void IniciarFallo(String Mensaje) {
        if (EstadoNivelActual != EstadoNivel.Jugando) {
            return;
        }
        EstadoNivelActual = EstadoNivel.Fallando;
        MensajeFallo = Mensaje;
        TiempoEstadoFallo = 0f;
        FallosNivel++;
        CrearEfecto(DulceActual.ObtenerCuerpo().getPosition(), 0.18f, 0.75f, new Color(0.85f, 0.16f, 0.16f, 1f));
        GestorAudioActual.ReproducirFallo();
    }

    private void ActualizarTransicionFallo(float Delta) {
        TiempoEstadoFallo += Delta;
        if (TiempoEstadoFallo >= 1.5f) {
            EstadoNivelActual = EstadoNivel.Reiniciando;
        }
    }

    private void MostrarResultado() {
        if (EstadoNivelActual == EstadoNivel.Ganado) {
            return;
        }
        EstadoNivelActual = EstadoNivel.Ganado;
        ConfirmacionSalidaActiva = false;
        PuntajeFinal = CalculadoraPuntaje.CalcularPuntajeIntento(EstrellasRecolectadas, TiempoNivel, FallosNivel);
        ResultadoNivelActual = new ResultadoNivel(DatosNivelActual.ObtenerNumero(), EstrellasRecolectadas, PuntajeFinal, TiempoNivel);
        if (RetoActual != null) {
            ResultadoRetoActual = GestorRetos.EvaluarResultado(RetoActual, ResultadoNivelActual);
        }
        GuardarResultadoPartida();
        AnimacionResultadoActual = new AnimacionPanelResultado(PuntajeFinal);
        CrearEfecto(MonstruoActual.ObtenerPosicion(), 0.24f, 0.95f, new Color(0.25f, 0.90f, 0.35f, 1f));
        GestorAudioActual.ReproducirVictoria();
    }

    private void CrearEfecto(Vector2 Posicion, float RadioInicial, float RadioFinal, Color ColorActual) {
        EfectosVisuales.add(new EfectoVisualTemporal(Posicion, RadioInicial, RadioFinal, ColorActual));
    }

    private void GuardarResultadoPartida() {
        RegistroPartida Registro = RegistroPartida.CrearDesdeResultado(DatosNivelActual, ResultadoNivelActual, FallosNivel, RetoActual);
        GuardadorPartidasBinario.GuardarEnHilo(Gdx.files.local("datos/partidas_cut_the_rope.bin").file().toPath(), Registro);
        Usuario UsuarioActivo = SistemaAutenticacion.getUsuarioActivo();
        if (UsuarioActivo != null) {
            UsuarioActivo.registrarPartida(DatosNivelActual.ObtenerNumero(), true, EstrellasRecolectadas, TiempoNivel);
            ManejadorArchivos.guardarUsuario(UsuarioActivo);
        }
    }

    private void ActualizarEfectosVisuales(float Delta) {
        for (EfectoVisualTemporal EfectoVisual : EfectosVisuales) {
            EfectoVisual.Actualizar(Delta);
        }
        for (int Indice = EfectosVisuales.size - 1; Indice >= 0; Indice--) {
            if (EfectosVisuales.get(Indice).EstaFinalizado()) {
                EfectosVisuales.removeIndex(Indice);
            }
        }
    }

    private void ActualizarAnimacionResultado(float Delta) {
        if (AnimacionResultadoActual != null) {
            AnimacionResultadoActual.Actualizar(Delta);
        }
    }

    private void ManejarClicResultado(Vector2 PuntoMundo) {
        if (BotonResultadoSalir.contains(PuntoMundo)) {
            VolverASeleccion();
        } else if (BotonSiguiente.contains(PuntoMundo)) {
            if (RetoActual != null) {
                VolverASeleccion();
                return;
            }
            int SiguienteNivel = DatosNivelActual.ObtenerNumeroEnCategoria() + 1;
            if (SiguienteNivel > FabricaNiveles.CantidadNiveles(DatosNivelActual.ObtenerCategoria())) {
                VolverASeleccion();
            } else {
                JuegoAplicacion.CambiarPantalla(new PantallaJuego(JuegoAplicacion, FabricaNiveles.ObtenerNivel(DatosNivelActual.ObtenerCategoria(), SiguienteNivel), PersonalizacionDulceActual, PersonalizacionMonstruoActual));
            }
        }
    }

    private void ManejarClicConfirmacionSalida(Vector2 PuntoMundo) {
        if (BotonCancelarSalida.contains(PuntoMundo)) {
            ConfirmacionSalidaActiva = false;
        } else if (BotonConfirmarSalida.contains(PuntoMundo)) {
            VolverASeleccion();
        }
    }

    private void VolverASeleccion() {
        if (RetoActual != null) {
            JuegoAplicacion.CambiarPantalla(new PantallaRivalidad(JuegoAplicacion, PersonalizacionDulceActual.ObtenerColorDulce(), PersonalizacionMonstruoActual.ObtenerColorMonstruo(), RetoActual.ObtenerCategoria()));
            return;
        }
        JuegoAplicacion.CambiarPantalla(new PantallaSeleccionNivel(JuegoAplicacion, PersonalizacionDulceActual.ObtenerColorDulce(), PersonalizacionMonstruoActual.ObtenerColorMonstruo(), DatosNivelActual.ObtenerCategoria()));
    }

    private void DibujarPanelResultado(ShapeRenderer Renderer) {
        DibujarPanelRedondeado(Renderer, 0.35f, 1.0f, 4.1f, 5.15f, 0.18f, new Color(0.05f, 0.07f, 0.10f, 0.98f), new Color(0.20f, 0.28f, 0.35f, 0.92f));
        DibujarPanelRedondeado(Renderer, 0.56f, 2.55f, 3.68f, 2.25f, 0.12f, new Color(0.10f, 0.14f, 0.19f, 1f), new Color(0.25f, 0.34f, 0.42f, 0.85f));
        if (GestorTexturasActual.ObtenerEstrella(false) == null || GestorTexturasActual.ObtenerEstrella(true) == null) {
            DibujarEstrellasResultado(Renderer);
        }
        DibujarBoton(Renderer, BotonResultadoSalir, Color.valueOf("c74343"), Color.valueOf("ff9a7a"), Color.valueOf("ef6868"));
        DibujarBoton(Renderer, BotonSiguiente, Color.valueOf("2fae63"), Color.valueOf("a6f5b8"), Color.valueOf("51d985"));
    }

    private void DibujarFondoNivel(ShapeRenderer Renderer) {
        if (GestorTexturasActual.ObtenerFondoNivel(DatosNivelActual.ObtenerNumeroEnCategoria()) == null) {
            Renderer.setColor(new Color(0.13f, 0.18f, 0.23f, 1f));
        } else {
            Renderer.setColor(new Color(0.04f, 0.06f, 0.08f, 0.18f));
        }
        Renderer.rect(0f, 0f, ConstantesJuego.AnchoMundo, ConstantesJuego.AltoMundo);
        Renderer.setColor(new Color(0.10f, 0.14f, 0.18f, 1f));
        Renderer.rect(0f, 0f, ConstantesJuego.AnchoMundo, 1.0f);
        Renderer.setColor(new Color(0.17f, 0.23f, 0.28f, 0.55f));
        Renderer.rect(0f, 6.55f, ConstantesJuego.AnchoMundo, 0.14f);
        Renderer.rect(0f, 1.0f, ConstantesJuego.AnchoMundo, 0.08f);
    }

    private void DibujarFondoTextura() {
        Texture Fondo = GestorTexturasActual.ObtenerFondoNivel(DatosNivelActual.ObtenerNumeroEnCategoria());
        if (Fondo == null) {
            return;
        }
        float AspectoFondo = (float) Fondo.getWidth() / Fondo.getHeight();
        float AspectoMundo = ConstantesJuego.AnchoMundo / ConstantesJuego.AltoMundo;
        float AnchoDibujo = ConstantesJuego.AnchoMundo;
        float AltoDibujo = ConstantesJuego.AltoMundo;
        float PosicionX = 0f;
        float PosicionY = 0f;
        if (AspectoFondo > AspectoMundo) {
            AnchoDibujo = ConstantesJuego.AltoMundo * AspectoFondo;
            PosicionX = (ConstantesJuego.AnchoMundo - AnchoDibujo) / 2f;
        } else {
            AltoDibujo = ConstantesJuego.AnchoMundo / AspectoFondo;
            PosicionY = (ConstantesJuego.AltoMundo - AltoDibujo) / 2f;
        }
        Batch.setProjectionMatrix(Camara.combined);
        Batch.setColor(Color.WHITE);
        Batch.begin();
        Batch.draw(Fondo, PosicionX, PosicionY, AnchoDibujo, AltoDibujo);
        Batch.end();
    }

    private void DibujarPanelConfirmacionSalida(ShapeRenderer Renderer) {
        Renderer.setColor(new Color(0f, 0f, 0f, 0.58f));
        Renderer.rect(0f, 0f, ConstantesJuego.AnchoMundo, ConstantesJuego.AltoMundo);
        DibujarPanelRedondeado(Renderer, PanelConfirmacionSalir.x, PanelConfirmacionSalir.y, PanelConfirmacionSalir.width, PanelConfirmacionSalir.height, 0.16f, new Color(0.05f, 0.07f, 0.10f, 0.98f), new Color(0.22f, 0.30f, 0.38f, 0.94f));
        DibujarBoton(Renderer, BotonCancelarSalida, Color.valueOf("3b77c4"), Color.valueOf("b9dcff"), Color.valueOf("67a9f0"));
        DibujarBoton(Renderer, BotonConfirmarSalida, Color.valueOf("c74343"), Color.valueOf("ff9a7a"), Color.valueOf("ef6868"));
    }

    private void DibujarSpritesMundo() {
        Batch.setProjectionMatrix(Camara.combined);
        Batch.begin();
        for (Estrella EstrellaActual : Estrellas) {
            EstrellaActual.DibujarTextura(Batch, GestorTexturasActual);
        }
        MonstruoActual.DibujarTextura(Batch, GestorTexturasActual);
        DulceActual.DibujarTextura(Batch, GestorTexturasActual);
        Batch.end();
    }

    private void DibujarSpritesResultado() {
        Batch.setProjectionMatrix(Camara.combined);
        Batch.begin();
        DibujarEstrellasResultadoTextura();
        Batch.end();
    }

    private void DibujarEstrellasResultadoTextura() {
        float Espaciado = 0.86f;
        float InicioX = 2.4f - Espaciado;
        for (int Indice = 0; Indice < 3; Indice++) {
            Texture Textura = GestorTexturasActual.ObtenerEstrella(Indice >= EstrellasRecolectadas);
            if (Textura == null) {
                continue;
            }
            float Tamano = 0.56f;
            float PosicionX = InicioX + Indice * Espaciado;
            Batch.draw(Textura, PosicionX - Tamano / 2f, 5.20f - Tamano / 2f, Tamano, Tamano);
        }
    }

    private void DibujarHud(ShapeRenderer Renderer) {
        Renderer.setColor(new Color(0f, 0f, 0f, 0.22f));
        Renderer.rect(FondoHud.x, FondoHud.y - 0.03f, FondoHud.width, FondoHud.height);
        Renderer.setColor(new Color(0.05f, 0.07f, 0.10f, 0.76f));
        Renderer.rect(FondoHud.x, FondoHud.y, FondoHud.width, FondoHud.height);
        Renderer.setColor(new Color(0.30f, 0.43f, 0.52f, 0.35f));
        Renderer.rect(FondoHud.x, FondoHud.y, FondoHud.width, 0.04f);
    }

    private void DibujarEstrellasResultado(ShapeRenderer Renderer) {
        float Espaciado = 0.86f;
        float InicioX = 2.4f - Espaciado;
        for (int Indice = 0; Indice < 3; Indice++) {
            if (Indice < EstrellasRecolectadas) {
                Renderer.setColor(Color.GOLD);
            } else {
                Renderer.setColor(new Color(0.28f, 0.30f, 0.34f, 1f));
            }
            Renderer.circle(InicioX + Indice * Espaciado, 5.20f, 0.30f, 24);
        }
    }

    private void DibujarBoton(ShapeRenderer Renderer, Rectangle Rectangulo, Color ColorFondo, Color ColorBorde, Color ColorBrillo) {
        Renderer.setColor(new Color(0f, 0f, 0f, 0.28f));
        DibujarRectanguloRedondeado(Renderer, Rectangulo.x + 0.035f, Rectangulo.y - 0.035f, Rectangulo.width, Rectangulo.height, 0.10f);
        Renderer.setColor(ColorBorde);
        DibujarRectanguloRedondeado(Renderer, Rectangulo.x, Rectangulo.y, Rectangulo.width, Rectangulo.height, 0.10f);
        Renderer.setColor(ColorFondo);
        DibujarRectanguloRedondeado(Renderer, Rectangulo.x + 0.035f, Rectangulo.y + 0.035f, Rectangulo.width - 0.07f, Rectangulo.height - 0.07f, 0.08f);
        Renderer.setColor(ColorBrillo);
        DibujarRectanguloRedondeado(Renderer, Rectangulo.x + 0.13f, Rectangulo.y + Rectangulo.height - 0.16f, Rectangulo.width - 0.26f, 0.07f, 0.035f);
    }

    private void DibujarPanelRedondeado(ShapeRenderer Renderer, float X, float Y, float Ancho, float Alto, float Radio, Color ColorFondo, Color ColorBorde) {
        Renderer.setColor(new Color(0f, 0f, 0f, 0.28f));
        DibujarRectanguloRedondeado(Renderer, X + 0.045f, Y - 0.045f, Ancho, Alto, Radio);
        Renderer.setColor(ColorBorde);
        DibujarRectanguloRedondeado(Renderer, X, Y, Ancho, Alto, Radio);
        Renderer.setColor(ColorFondo);
        DibujarRectanguloRedondeado(Renderer, X + 0.045f, Y + 0.045f, Ancho - 0.09f, Alto - 0.09f, Math.max(0.02f, Radio - 0.035f));
        Renderer.setColor(new Color(1f, 1f, 1f, 0.06f));
        DibujarRectanguloRedondeado(Renderer, X + 0.18f, Y + Alto - 0.22f, Ancho - 0.36f, 0.08f, 0.04f);
    }

    private void DibujarRectanguloRedondeado(ShapeRenderer Renderer, float X, float Y, float Ancho, float Alto, float Radio) {
        float RadioSeguro = Math.min(Radio, Math.min(Ancho, Alto) / 2f);
        Renderer.rect(X + RadioSeguro, Y, Ancho - RadioSeguro * 2f, Alto);
        Renderer.rect(X, Y + RadioSeguro, Ancho, Alto - RadioSeguro * 2f);
        Renderer.circle(X + RadioSeguro, Y + RadioSeguro, RadioSeguro, 18);
        Renderer.circle(X + Ancho - RadioSeguro, Y + RadioSeguro, RadioSeguro, 18);
        Renderer.circle(X + RadioSeguro, Y + Alto - RadioSeguro, RadioSeguro, 18);
        Renderer.circle(X + Ancho - RadioSeguro, Y + Alto - RadioSeguro, RadioSeguro, 18);
    }

    private void DibujarTextos() {
        PrepararBatchTexto();
        Batch.begin();
        Fuente.setColor(Color.WHITE);
        FuenteInterfaz.setColor(Color.WHITE);
        if (!NivelCompletado()) {
            DibujarTextosHud();
        }
        if (NivelCompletado() && !ConfirmacionSalidaActiva) {
            DibujarTextosResultado();
        }
        if (ConfirmacionSalidaActiva) {
            DibujarTextosConfirmacionSalida();
        } else if (EstadoNivelActual == EstadoNivel.Fallando) {
            Fuente.getData().setScale(1.0f);
            DibujarTextoMundo(MensajeFallo, 1.15f, 4.15f);
        }
        Batch.end();
    }

    private void DibujarTextosHud() {
        DibujarTextoInterfazCentradoEnRectanguloMundo("Salir", BotonSalir, 1.35f);
        DibujarTextoInterfazCentradoMundo("Nivel " + DatosNivelActual.ObtenerNumero(), 1.72f, 7.60f, 1.22f);
        DibujarTextoInterfazCentradoMundo(EstrellasRecolectadas + "/3", 2.65f, 7.60f, 1.22f);
        DibujarTextoInterfazCentradoMundo(Math.round(TiempoNivel) + " s", 3.58f, 7.60f, 1.22f);
    }

    private void DibujarTextosResultado() {
        Fuente.setColor(Color.WHITE);
        Fuente.getData().setScale(1.05f);
        DibujarTextoCentradoMundo("Nivel completado", 2.4f, 5.84f);
        int PuntajeVisible = AnimacionResultadoActual == null ? PuntajeFinal : AnimacionResultadoActual.ObtenerPuntajeVisible();
        List<String> LineasResultado = RetoActual == null ? TextoPanelResultado.CrearLineas(EstrellasRecolectadas, PuntajeVisible, TiempoNivel, FallosNivel) : TextoPanelResultado.CrearLineasReto(RetoActual, ResultadoRetoActual, PuntajeVisible, TiempoNivel, FallosNivel);
        int LineasVisibles = AnimacionResultadoActual == null ? LineasResultado.size() : AnimacionResultadoActual.ObtenerCantidadLineasVisibles(LineasResultado.size());
        float InicioLineas = RetoActual == null ? 4.34f : 4.42f;
        float SeparacionLineas = RetoActual == null ? 0.48f : 0.38f;
        for (int Indice = 0; Indice < LineasVisibles; Indice++) {
            DibujarTextoInterfazCentradoMundo(LineasResultado.get(Indice), 2.4f, InicioLineas - Indice * SeparacionLineas, RetoActual == null ? 1.18f : 1.03f);
        }
        DibujarTextoInterfazCentradoEnRectanguloMundo("Salir", BotonResultadoSalir, 1.18f);
        DibujarTextoInterfazCentradoEnRectanguloMundo(RetoActual == null ? TextoPanelResultado.CrearTextoSiguiente(DatosNivelActual) : "Retos", BotonSiguiente, 1.18f);
    }

    private void DibujarTextosConfirmacionSalida() {
        Fuente.setColor(Color.WHITE);
        Fuente.getData().setScale(1.0f);
        DibujarTextoCentradoMundo("Salir del nivel?", 2.4f, 4.22f);
        DibujarTextoInterfazCentradoMundo("El intento se reiniciara.", 2.4f, 3.82f, 1.0f);
        DibujarTextoInterfazCentradoEnRectanguloMundo("Cancelar", BotonCancelarSalida, 1.12f);
        DibujarTextoInterfazCentradoEnRectanguloMundo("Salir", BotonConfirmarSalida, 1.12f);
    }

    private void PrepararBatchTexto() {
        Batch.setProjectionMatrix(Batch.getProjectionMatrix().setToOrtho2D(0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    private void DibujarTextoMundo(String Texto, float MundoX, float MundoY) {
        Vector2 PuntoPantalla = ConvertirMundoAPantalla(MundoX, MundoY);
        Fuente.draw(Batch, Texto, PuntoPantalla.x, PuntoPantalla.y);
    }

    private void DibujarTextoCentradoMundo(String Texto, float MundoX, float MundoY) {
        Vector2 PuntoPantalla = ConvertirMundoAPantalla(MundoX, MundoY);
        MedidorTexto.setText(Fuente, Texto);
        Fuente.draw(Batch, Texto, PuntoPantalla.x - MedidorTexto.width / 2f, PuntoPantalla.y);
    }

    private void DibujarTextoInterfazCentradoMundo(String Texto, float MundoX, float MundoY, float Escala) {
        FuenteInterfaz.getData().setScale(Escala);
        Vector2 PuntoPantalla = ConvertirMundoAPantalla(MundoX, MundoY);
        MedidorTextoInterfaz.setText(FuenteInterfaz, Texto);
        FuenteInterfaz.draw(Batch, Texto, PuntoPantalla.x - MedidorTextoInterfaz.width / 2f, PuntoPantalla.y);
    }

    private void DibujarTextoInterfazCentradoEnRectanguloMundo(String Texto, Rectangle Rectangulo, float Escala) {
        FuenteInterfaz.getData().setScale(Escala);
        Vector2 CentroPantalla = ConvertirMundoAPantalla(Rectangulo.x + Rectangulo.width / 2f, Rectangulo.y + Rectangulo.height / 2f);
        MedidorTextoInterfaz.setText(FuenteInterfaz, Texto);
        FuenteInterfaz.draw(Batch, Texto, CentroPantalla.x - MedidorTextoInterfaz.width / 2f, CentroPantalla.y + MedidorTextoInterfaz.height / 2f);
    }

    private Vector2 ConvertirMundoAPantalla(float MundoX, float MundoY) {
        float PantallaX = Viewport.getScreenX() + MundoX / ConstantesJuego.AnchoMundo * Viewport.getScreenWidth();
        float PantallaY = Viewport.getScreenY() + MundoY / ConstantesJuego.AltoMundo * Viewport.getScreenHeight();

        return new Vector2(PantallaX, PantallaY);
    }

    private Vector2 ConvertirPantallaAMundo(int ScreenX, int ScreenY) {
        Vector3 PuntoPantalla = new Vector3(ScreenX, ScreenY, 0f);
        Viewport.unproject(PuntoPantalla);

        return new Vector2(PuntoPantalla.x, PuntoPantalla.y);
    }

    private void DibujarTransicion() {
        float Alpha = AlphaEntrada;
        if (EstadoNivelActual == EstadoNivel.Fallando) {
            Alpha = Math.max(Alpha, Math.min(1f, TiempoEstadoFallo / 1.2f));
        }
        if (Alpha <= 0f) {
            return;
        }
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ShapeRendererActual.begin(ShapeRenderer.ShapeType.Filled);
        ShapeRendererActual.setColor(new Color(0f, 0f, 0f, Alpha));
        ShapeRendererActual.rect(0f, 0f, ConstantesJuego.AnchoMundo, ConstantesJuego.AltoMundo);
        ShapeRendererActual.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private boolean NivelCompletado() {
        return EstadoNivelActual == EstadoNivel.Ganado;
    }

    public ResultadoNivel ObtenerResultadoNivelActual() {
        return ResultadoNivelActual;
    }

    public ResultadoReto ObtenerResultadoRetoActual() {
        return ResultadoRetoActual;
    }

    @Override
    public void resize(int Width, int Height) {
        if (Width <= 0 || Height <= 0) {
            return;
        }
        Viewport.update(Width, Height, true);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (ShapeRendererActual != null) {
            ShapeRendererActual.dispose();
        }
        if (Batch != null) {
            Batch.dispose();
        }
        if (Fuente != null) {
            Fuente.dispose();
        }
        if (FuenteInterfaz != null) {
            FuenteInterfaz.dispose();
        }
        if (GestorTexturasActual != null) {
            GestorTexturasActual.dispose();
        }
        if (GestorAudioActual != null) {
            GestorAudioActual.dispose();
        }
        if (Mundo != null) {
            Mundo.dispose();
        }
    }
}

