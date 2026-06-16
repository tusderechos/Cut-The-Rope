/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.io.Serializable;

public final class ResultadoTurnoRivalidad implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String UsernameJugador;
    private final int Puntaje;
    private final int Estrellas;
    private final float Tiempo;

    public ResultadoTurnoRivalidad(String UsernameJugador, int Puntaje, int Estrellas, float Tiempo) {
        if (UsernameJugador == null || UsernameJugador.trim().isEmpty() || Puntaje < 0 || Estrellas < 0 || Estrellas > 3 || !Float.isFinite(Tiempo) || Tiempo < 0f) {
            throw new IllegalArgumentException("Los datos del turno de rivalidad no son validos");
        }
        this.UsernameJugador = UsernameJugador.trim().toLowerCase();
        this.Puntaje = Puntaje;
        this.Estrellas = Estrellas;
        this.Tiempo = Tiempo;
    }

    public static ResultadoTurnoRivalidad CrearDesdeResultadoNivel(String UsernameJugador, ResultadoNivel ResultadoNivelActual) {
        if (ResultadoNivelActual == null) {
            throw new IllegalArgumentException("El resultado del nivel no puede ser nulo");
        }

        return new ResultadoTurnoRivalidad(UsernameJugador, ResultadoNivelActual.ObtenerPuntaje(), ResultadoNivelActual.ObtenerEstrellas(), ResultadoNivelActual.ObtenerTiempo());
    }

    public String ObtenerUsernameJugador() {
        return UsernameJugador;
    }

    public int ObtenerPuntaje() {
        return Puntaje;
    }

    public int ObtenerEstrellas() {
        return Estrellas;
    }

    public float ObtenerTiempo() {
        return Tiempo;
    }
}
