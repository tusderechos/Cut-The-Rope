/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.persistencia;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import com.tusderechos.Juego.rivalidad.DatosReto;
import java.io.Serializable;
import java.time.LocalDateTime;

public final class RegistroPartida implements Serializable {
    private static final long serialVersionUID = 1L;
    private final CategoriaDificultad Categoria;
    private final int NumeroEnCategoria;
    private final int NumeroGlobalNivel;
    private final int Estrellas;
    private final int Puntaje;
    private final float Tiempo;
    private final int Fallos;
    private final boolean FueReto;
    private final int PuntajeObjetivo;
    private final int EstrellasObjetivo;
    private final LocalDateTime FechaRegistro;

    private RegistroPartida(DatosNivel Nivel, ResultadoNivel Resultado, int Fallos, DatosReto Reto) {
        if (Nivel == null || Resultado == null || Fallos < 0) {
            throw new IllegalArgumentException("Los datos de la partida no son validos");
        }
        Categoria = Nivel.ObtenerCategoria();
        NumeroEnCategoria = Nivel.ObtenerNumeroEnCategoria();
        NumeroGlobalNivel = Nivel.ObtenerNumero();
        Estrellas = Resultado.ObtenerEstrellas();
        Puntaje = Resultado.ObtenerPuntaje();
        Tiempo = Resultado.ObtenerTiempo();
        this.Fallos = Fallos;
        FueReto = Reto != null;
        PuntajeObjetivo = Reto == null ? 0 : Reto.ObtenerPuntajeObjetivo();
        EstrellasObjetivo = Reto == null ? 0 : Reto.ObtenerEstrellasObjetivo();
        FechaRegistro = LocalDateTime.now();
    }

    public static RegistroPartida CrearDesdeResultado(DatosNivel Nivel, ResultadoNivel Resultado, int Fallos, DatosReto Reto) {
        return new RegistroPartida(Nivel, Resultado, Fallos, Reto);
    }

    public CategoriaDificultad ObtenerCategoria() {
        return Categoria;
    }

    public int ObtenerNumeroEnCategoria() {
        return NumeroEnCategoria;
    }

    public int ObtenerNumeroGlobalNivel() {
        return NumeroGlobalNivel;
    }

    public int ObtenerEstrellas() {
        return Estrellas;
    }

    public int ObtenerPuntaje() {
        return Puntaje;
    }

    public float ObtenerTiempo() {
        return Tiempo;
    }

    public int ObtenerFallos() {
        return Fallos;
    }

    public boolean FueReto() {
        return FueReto;
    }

    public int ObtenerPuntajeObjetivo() {
        return PuntajeObjetivo;
    }

    public int ObtenerEstrellasObjetivo() {
        return EstrellasObjetivo;
    }

    public LocalDateTime ObtenerFechaRegistro() {
        return FechaRegistro;
    }
}
