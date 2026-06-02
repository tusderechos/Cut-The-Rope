package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatosNivel {
    private final int numero;
    private final String nombre;
    private final DificultadNivel dificultad;
    private final Vector2 posicionDulce;
    private final Vector2 posicionMonstruo;
    private final List<DatosCuerda> cuerdas;
    private final List<DatosEstrella> estrellas;
    private final List<DatosBurbuja> burbujas;
    private final List<DatosObstaculo> obstaculos;
    private final boolean plataformaMovil;

    public DatosNivel(int numero, String nombre, DificultadNivel dificultad, Vector2 posicionDulce,
                      Vector2 posicionMonstruo, List<DatosCuerda> cuerdas, List<DatosEstrella> estrellas,
                      List<DatosBurbuja> burbujas, List<DatosObstaculo> obstaculos, boolean plataformaMovil) {
        this.numero = numero;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.posicionDulce = new Vector2(posicionDulce);
        this.posicionMonstruo = new Vector2(posicionMonstruo);
        this.cuerdas = new ArrayList<>(cuerdas);
        this.estrellas = new ArrayList<>(estrellas);
        this.burbujas = new ArrayList<>(burbujas);
        this.obstaculos = new ArrayList<>(obstaculos);
        this.plataformaMovil = plataformaMovil;
    }

    public int obtenerNumero() { return numero; }
    public String obtenerNombre() { return nombre; }
    public DificultadNivel obtenerDificultad() { return dificultad; }
    public Vector2 obtenerPosicionDulce() { return new Vector2(posicionDulce); }
    public Vector2 obtenerPosicionMonstruo() { return new Vector2(posicionMonstruo); }
    public List<DatosCuerda> obtenerCuerdas() { return Collections.unmodifiableList(cuerdas); }
    public List<DatosEstrella> obtenerEstrellas() { return Collections.unmodifiableList(estrellas); }
    public List<DatosBurbuja> obtenerBurbujas() { return Collections.unmodifiableList(burbujas); }
    public List<DatosObstaculo> obtenerObstaculos() { return Collections.unmodifiableList(obstaculos); }
    public boolean tienePlataformaMovil() { return plataformaMovil; }
}
