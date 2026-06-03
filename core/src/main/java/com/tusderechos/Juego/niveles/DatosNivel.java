/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DatosNivel {
    private final int Numero;
    private final String Nombre;
    private final DificultadNivel Dificultad;
    private final Vector2 PosicionDulce;
    private final Vector2 PosicionMonstruo;
    private final List<DatosCuerda> Cuerdas;
    private final List<DatosEstrella> Estrellas;
    private final List<DatosBurbuja> Burbujas;
    private final List<DatosObstaculo> Obstaculos;
    private final boolean PlataformaMovilActual;

    public DatosNivel(int Numero, String Nombre, DificultadNivel Dificultad, Vector2 PosicionDulce, Vector2 PosicionMonstruo, List<DatosCuerda> Cuerdas, List<DatosEstrella> Estrellas, List<DatosBurbuja> Burbujas, List<DatosObstaculo> Obstaculos, boolean PlataformaMovilActual) {
        if (Numero < 1 || Nombre == null || Nombre.trim().isEmpty() || Dificultad == null) {
            throw new IllegalArgumentException("Los datos principales del nivel no son validos");
        }
        ValidacionDatosNivel.ValidarVector(PosicionDulce, "La posicion del dulce");
        ValidacionDatosNivel.ValidarVector(PosicionMonstruo, "La posicion del monstruo");
        ValidacionDatosNivel.ValidarLista(Cuerdas, "Las Cuerdas");
        ValidacionDatosNivel.ValidarLista(Estrellas, "Las Estrellas");
        ValidacionDatosNivel.ValidarLista(Burbujas, "Las Burbujas");
        ValidacionDatosNivel.ValidarLista(Obstaculos, "Los Obstaculos");
        this.Numero = Numero;
        this.Nombre = Nombre;
        this.Dificultad = Dificultad;
        this.PosicionDulce = new Vector2(PosicionDulce);
        this.PosicionMonstruo = new Vector2(PosicionMonstruo);
        this.Cuerdas = new ArrayList<>(Cuerdas);
        this.Estrellas = new ArrayList<>(Estrellas);
        this.Burbujas = new ArrayList<>(Burbujas);
        this.Obstaculos = new ArrayList<>(Obstaculos);
        this.PlataformaMovilActual = PlataformaMovilActual;
    }

    public int ObtenerNumero() {
        return Numero;
    }
    public String ObtenerNombre() {
        return Nombre;
    }
    public DificultadNivel ObtenerDificultad() {
        return Dificultad;
    }
    public Vector2 ObtenerPosicionDulce() {
        return new Vector2(PosicionDulce);
    }
    public Vector2 ObtenerPosicionMonstruo() {
        return new Vector2(PosicionMonstruo);
    }
    public List<DatosCuerda> ObtenerCuerdas() {
        return Collections.unmodifiableList(Cuerdas);
    }
    public List<DatosEstrella> ObtenerEstrellas() {
        return Collections.unmodifiableList(Estrellas);
    }
    public List<DatosBurbuja> ObtenerBurbujas() {
        return Collections.unmodifiableList(Burbujas);
    }
    public List<DatosObstaculo> ObtenerObstaculos() {
        return Collections.unmodifiableList(Obstaculos);
    }
    public boolean TienePlataformaMovil() {
        return PlataformaMovilActual;
    }
}

