package com.tusderechos.Juego.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.tusderechos.Juego.entidades.Dulce;
import com.tusderechos.Juego.entidades.Burbuja;
import com.tusderechos.Juego.entidades.Cuerda;
import com.tusderechos.Juego.entidades.CuerdaCortadaVisual;
import com.tusderechos.Juego.entidades.Monstruo;
import com.tusderechos.Juego.entidades.Estrella;
import com.tusderechos.Juego.entidades.PlataformaMovil;
import com.tusderechos.Juego.niveles.DatosCuerda;
import com.tusderechos.Juego.niveles.DatosBurbuja;
import com.tusderechos.Juego.niveles.DatosEstrella;
import com.tusderechos.Juego.niveles.DatosObstaculo;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.utilidades.ConstantesJuego;
import com.tusderechos.Juego.utilidades.CalculadoraPuntaje;
import com.tusderechos.Juego.enums.EstadoNivel;
import com.tusderechos.Juego.obstaculos.Obstaculo;
import com.tusderechos.Juego.obstaculos.ObstaculoPeligroso;

public class PantallaJuego extends ScreenAdapter {
    private final Juego juego;
    private final DatosNivel datosNivel;
    private final PersonalizacionDulce personalizacionDulce;
    private final PersonalizacionMonstruo personalizacionMonstruo;
    private World mundo;
    private OrthographicCamera camara;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont fuente;
    private Dulce dulce;
    private Monstruo monstruo;
    private final Array<Cuerda> cuerdas = new Array<>();
    private final Array<CuerdaCortadaVisual> cuerdasCortadas = new Array<>();
    private final Array<Estrella> estrellas = new Array<>();
    private final Array<Burbuja> burbujas = new Array<>();
    private final Array<Obstaculo> obstaculos = new Array<>();
    private final Rectangle botonSalir = new Rectangle(0.15f, 7.45f, 0.95f, 0.38f);
    private final Rectangle botonResultadoSalir = new Rectangle(0.65f, 1.55f, 1.35f, 0.52f);
    private final Rectangle botonSiguiente = new Rectangle(2.75f, 1.55f, 1.35f, 0.52f);
    private float tiempoNivel;
    private int fallosNivel;
    private int estrellasRecolectadas;
    private boolean mostrandoResultado;
    private int puntajeFinal;
    private EstadoNivel estadoNivel = EstadoNivel.JUGANDO;
    private float tiempoEstadoFallo;
    private float tiempoDulceDetenido;
    private String mensajeFallo = "";
    private PlataformaMovil plataformaMovil;
    private float acumuladorFisica;
    private float alphaEntrada = 1f;
    private boolean cambiarPantallaAlTerminarFrame;

    public PantallaJuego(Juego juego, DatosNivel datosNivel, PersonalizacionDulce personalizacionDulce,
                         PersonalizacionMonstruo personalizacionMonstruo) {
        this.juego = juego;
        this.datosNivel = datosNivel;
        this.personalizacionDulce = personalizacionDulce;
        this.personalizacionMonstruo = personalizacionMonstruo;
    }

    @Override
    public void show() {
        mundo = new World(new Vector2(0f, ConstantesJuego.GRAVEDAD), true);
        camara = new OrthographicCamera(ConstantesJuego.ANCHO_MUNDO, ConstantesJuego.ALTO_MUNDO);
        viewport = new FitViewport(ConstantesJuego.ANCHO_MUNDO, ConstantesJuego.ALTO_MUNDO, camara);
        viewport.apply(true);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        fuente = new BitmapFont();
        dulce = new Dulce(mundo, datosNivel.obtenerPosicionDulce(), personalizacionDulce);
        monstruo = new Monstruo(datosNivel.obtenerPosicionMonstruo(), personalizacionMonstruo);
        for (DatosCuerda datosCuerda : datosNivel.obtenerCuerdas()) {
            cuerdas.add(new Cuerda(mundo, datosCuerda.obtenerAncla(), datosCuerda.obtenerLongitud(), dulce.obtenerCuerpo()));
        }
        for (DatosEstrella datosEstrella : datosNivel.obtenerEstrellas()) estrellas.add(new Estrella(datosEstrella.obtenerPosicion()));
        for (DatosBurbuja datosBurbuja : datosNivel.obtenerBurbujas()) burbujas.add(new Burbuja(datosBurbuja.obtenerPosicion(), datosBurbuja.obtenerRadio()));
        for (DatosObstaculo datosObstaculo : datosNivel.obtenerObstaculos()) {
            obstaculos.add(new ObstaculoPeligroso(datosObstaculo.obtenerPosicion(), datosObstaculo.obtenerAncho(), datosObstaculo.obtenerAlto()));
        }
        if (datosNivel.tienePlataformaMovil()) {
            plataformaMovil = new PlataformaMovil(new Vector2(2.4f, 0.82f), 1.0f, 3.8f, 0.85f);
            monstruo.establecerPosicion(plataformaMovil.obtenerPosicionMonstruo());
        }
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    volverASeleccion();
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 puntoPantalla = new Vector3(screenX, screenY, 0f);
                viewport.unproject(puntoPantalla);
                Vector2 puntoMundo = new Vector2(puntoPantalla.x, puntoPantalla.y);
                if (mostrandoResultado) {
                    manejarClicResultado(puntoMundo);
                } else if (botonSalir.contains(puntoMundo)) {
                    volverASeleccion();
                } else {
                    if (!reventarBurbujaCercana(puntoMundo)) cortarCuerdaCercana(puntoMundo);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.12f, 0.16f, 0.20f, 1f);
        alphaEntrada = Math.max(0f, alphaEntrada - delta * 1.6f);
        if (!mostrandoResultado && estadoNivel == EstadoNivel.JUGANDO) {
            tiempoNivel += delta;
            actualizarPlataforma(delta);
            actualizarFisica(delta);
            recolectarEstrellas();
            if (monstruo.contieneDulce(dulce.obtenerCuerpo().getPosition())) mostrarResultado();
            if (!mostrandoResultado) detectarFallo(delta);
        } else if (estadoNivel == EstadoNivel.FALLANDO) {
            actualizarTransicionFallo(delta);
        }
        if (cambiarPantallaAlTerminarFrame) {
            PantallaJuego reinicio = new PantallaJuego(juego, datosNivel, personalizacionDulce, personalizacionMonstruo);
            reinicio.fallosNivel = fallosNivel;
            juego.cambiarPantalla(reinicio);
            return;
        }
        for (CuerdaCortadaVisual cuerdaCortada : cuerdasCortadas) cuerdaCortada.actualizar(delta);
        for (int indice = cuerdasCortadas.size - 1; indice >= 0; indice--) {
            if (cuerdasCortadas.get(indice).estaFinalizada()) cuerdasCortadas.removeIndex(indice);
        }
        shapeRenderer.setProjectionMatrix(camara.combined);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Cuerda cuerda : cuerdas) cuerda.dibujar(shapeRenderer);
        for (CuerdaCortadaVisual cuerdaCortada : cuerdasCortadas) cuerdaCortada.dibujar(shapeRenderer);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Estrella estrella : estrellas) estrella.dibujar(shapeRenderer);
        for (Burbuja burbuja : burbujas) burbuja.dibujar(shapeRenderer);
        for (Obstaculo obstaculo : obstaculos) obstaculo.dibujar(shapeRenderer);
        if (plataformaMovil != null) plataformaMovil.dibujar(shapeRenderer);
        monstruo.dibujar(shapeRenderer);
        dulce.dibujar(shapeRenderer);
        dibujarBoton(shapeRenderer, botonSalir, new Color(0.55f, 0.18f, 0.18f, 1f));
        if (mostrandoResultado) dibujarPanelResultado(shapeRenderer);
        if (estadoNivel == EstadoNivel.FALLANDO) {
            float alpha = Math.min(1f, tiempoEstadoFallo / 1.2f);
            shapeRenderer.setColor(new Color(0f, 0f, 0f, alpha));
            shapeRenderer.rect(0f, 0f, ConstantesJuego.ANCHO_MUNDO, ConstantesJuego.ALTO_MUNDO);
        }
        if (alphaEntrada > 0f) {
            shapeRenderer.setColor(new Color(0f, 0f, 0f, alphaEntrada));
            shapeRenderer.rect(0f, 0f, ConstantesJuego.ANCHO_MUNDO, ConstantesJuego.ALTO_MUNDO);
        }
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        dibujarTextos();
    }

    private void cortarCuerdaCercana(Vector2 puntoMundo) {
        for (Cuerda cuerda : cuerdas) {
            if (!cuerda.contienePuntoDeCorte(puntoMundo)) continue;
            Vector2 ancla = cuerda.obtenerAncla();
            Vector2 fin = cuerda.obtenerFin();
            cuerda.cortar(puntoMundo);
            cuerdasCortadas.add(new CuerdaCortadaVisual(ancla, puntoMundo, fin));
            return;
        }
    }

    private void recolectarEstrellas() {
        for (Estrella estrella : estrellas) if (estrella.intentarRecolectar(dulce.obtenerCuerpo().getPosition())) estrellasRecolectadas++;
    }

    private boolean reventarBurbujaCercana(Vector2 puntoMundo) {
        for (Burbuja burbuja : burbujas) {
            if (!burbuja.contienePunto(puntoMundo)) continue;
            burbuja.reventar();
            return true;
        }
        return false;
    }

    private boolean hayBurbujaActiva() {
        for (Burbuja burbuja : burbujas) if (burbuja.estaActiva()) return true;
        return false;
    }

    private boolean hayCuerdaActiva() {
        for (Cuerda cuerda : cuerdas) if (!cuerda.estaCortada()) return true;
        return false;
    }

    private void aplicarFlotacion() {
        for (Burbuja burbuja : burbujas) {
            if (!burbuja.estaActiva()) continue;
            burbuja.seguirDulce(dulce.obtenerCuerpo().getPosition());
            dulce.obtenerCuerpo().applyForceToCenter(0f, 13f, true);
        }
    }

    private void actualizarFisica(float delta) {
        acumuladorFisica += Math.min(delta, 0.25f);
        while (acumuladorFisica >= 1f / 60f) {
            aplicarFlotacion();
            mundo.step(1f / 60f, 6, 2);
            acumuladorFisica -= 1f / 60f;
        }
    }

    private void detectarFallo(float delta) {
        Vector2 posicion = dulce.obtenerCuerpo().getPosition();
        if (posicion.y < -0.6f || posicion.x < -0.6f || posicion.x > ConstantesJuego.ANCHO_MUNDO + 0.6f
            || (posicion.y > ConstantesJuego.ALTO_MUNDO + 0.6f && hayBurbujaActiva())) {
            iniciarFallo("El dulce se perdio");
            return;
        }
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.tocaDulce(posicion, ConstantesJuego.RADIO_DULCE)) {
                iniciarFallo("El dulce toco un peligro");
                return;
            }
        }
        boolean detenido = dulce.obtenerCuerpo().getLinearVelocity().len() < 0.05f;
        tiempoDulceDetenido = detenido && !hayCuerdaActiva() && !hayBurbujaActiva() ? tiempoDulceDetenido + delta : 0f;
        if (tiempoDulceDetenido > 3f && !monstruo.contieneDulce(posicion)) iniciarFallo("Intento fallido");
    }

    private void actualizarPlataforma(float delta) {
        if (plataformaMovil == null) return;
        plataformaMovil.actualizar(delta);
        monstruo.establecerPosicion(plataformaMovil.obtenerPosicionMonstruo());
    }

    private void iniciarFallo(String mensaje) {
        estadoNivel = EstadoNivel.FALLANDO;
        mensajeFallo = mensaje;
        tiempoEstadoFallo = 0f;
        fallosNivel++;
    }

    private void actualizarTransicionFallo(float delta) {
        tiempoEstadoFallo += delta;
        if (tiempoEstadoFallo >= 1.5f) {
            cambiarPantallaAlTerminarFrame = true;
        }
    }

    private void mostrarResultado() {
        mostrandoResultado = true;
        puntajeFinal = CalculadoraPuntaje.calcularPuntajeIntento(estrellasRecolectadas, tiempoNivel, fallosNivel);
        juego.obtenerProgresoJugadorDemo().registrarResultado(
            new ResultadoNivel(datosNivel.obtenerNumero(), estrellasRecolectadas, puntajeFinal, tiempoNivel));
    }

    private void manejarClicResultado(Vector2 puntoMundo) {
        if (botonResultadoSalir.contains(puntoMundo)) {
            volverASeleccion();
        } else if (botonSiguiente.contains(puntoMundo) && datosNivel.obtenerNumero() < 5) {
            juego.cambiarPantalla(new PantallaJuego(juego, FabricaNiveles.obtenerNivel(datosNivel.obtenerNumero() + 1),
                personalizacionDulce, personalizacionMonstruo));
        } else if (botonSiguiente.contains(puntoMundo)) {
            volverASeleccion();
        }
    }

    private void volverASeleccion() {
        juego.cambiarPantalla(new PantallaSeleccionNivel(juego, personalizacionDulce.obtenerColorDulce(),
            personalizacionMonstruo.obtenerColorMonstruo()));
    }

    private void dibujarPanelResultado(ShapeRenderer renderer) {
        renderer.setColor(new Color(0.05f, 0.07f, 0.10f, 0.92f));
        renderer.rect(0.35f, 1.1f, 4.1f, 4.9f);
        dibujarBoton(renderer, botonResultadoSalir, new Color(0.58f, 0.20f, 0.20f, 1f));
        dibujarBoton(renderer, botonSiguiente, new Color(0.18f, 0.55f, 0.30f, 1f));
    }

    private void dibujarBoton(ShapeRenderer renderer, Rectangle rectangulo, Color color) {
        renderer.setColor(color);
        renderer.rect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
    }

    private void dibujarTextos() {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        fuente.getData().setScale(0.015f);
        fuente.draw(batch, "Nivel " + datosNivel.obtenerNumero(), 2.05f, 7.72f);
        fuente.draw(batch, "Salir", botonSalir.x + 0.2f, botonSalir.y + 0.27f);
        if (mostrandoResultado) {
            fuente.getData().setScale(0.022f);
            fuente.draw(batch, "Nivel completado", 1.25f, 5.45f);
            fuente.getData().setScale(0.016f);
            fuente.draw(batch, "Estrellas: " + estrellasRecolectadas + "/3", 1.2f, 4.75f);
            fuente.draw(batch, "Faltaron: " + (3 - estrellasRecolectadas), 1.2f, 4.28f);
            fuente.draw(batch, "Puntaje: " + puntajeFinal, 1.2f, 3.81f);
            fuente.draw(batch, "Tiempo: " + Math.round(tiempoNivel) + " s", 1.2f, 3.34f);
            fuente.draw(batch, "Fallos: " + fallosNivel, 1.2f, 2.87f);
            fuente.draw(batch, "Salir", 1.05f, 1.88f);
            fuente.draw(batch, datosNivel.obtenerNumero() < 5 ? "Siguiente" : "Final", 3.05f, 1.88f);
        } else if (estadoNivel == EstadoNivel.FALLANDO) {
            fuente.getData().setScale(0.021f);
            fuente.draw(batch, mensajeFallo, 1.15f, 4.15f);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        if (width <= 0 || height <= 0) return;
        viewport.update(width, height, true);
    }

    @Override
    public void hide() { Gdx.input.setInputProcessor(null); }

    @Override
    public void dispose() {
        if (shapeRenderer != null) shapeRenderer.dispose();
        if (batch != null) batch.dispose();
        if (fuente != null) fuente.dispose();
        if (mundo != null) mundo.dispose();
    }
}
