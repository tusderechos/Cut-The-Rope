/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import java.util.UUID;

public final class DueloLocal {
    private final String Id;
    private final DatosReto Reto;
    private final String UsernameRetador;
    private final String UsernameRetado;
    private ResultadoTurnoRivalidad ResultadoRetador;
    private ResultadoTurnoRivalidad ResultadoRetado;

    public DueloLocal(DatosReto Reto, String UsernameRetador, String UsernameRetado) {
        if (Reto == null || UsernameRetador == null || UsernameRetador.trim().isEmpty() || UsernameRetado == null || UsernameRetado.trim().isEmpty()) {
            throw new IllegalArgumentException("Los datos del duelo local no son validos");
        }
        String RetadorNormalizado = UsernameRetador.trim().toLowerCase();
        String RetadoNormalizado = UsernameRetado.trim().toLowerCase();
        if (RetadorNormalizado.equals(RetadoNormalizado)) {
            throw new IllegalArgumentException("Los jugadores del duelo deben ser distintos");
        }
        this.Id = UUID.randomUUID().toString();
        this.Reto = Reto;
        this.UsernameRetador = RetadorNormalizado;
        this.UsernameRetado = RetadoNormalizado;
    }

    public String ObtenerId() {
        return Id;
    }

    public DatosReto ObtenerReto() {
        return Reto;
    }

    public String ObtenerUsernameRetador() {
        return UsernameRetador;
    }

    public String ObtenerUsernameRetado() {
        return UsernameRetado;
    }

    public ResultadoTurnoRivalidad ObtenerResultadoRetador() {
        return ResultadoRetador;
    }

    public ResultadoTurnoRivalidad ObtenerResultadoRetado() {
        return ResultadoRetado;
    }

    public String ObtenerUsernameConTurno() {
        if (ResultadoRetador == null) {
            return UsernameRetador;
        }
        if (ResultadoRetado == null) {
            return UsernameRetado;
        }

        return "";
    }

    public boolean EstaFinalizado() {
        return ResultadoRetador != null && ResultadoRetado != null;
    }

    public String ObtenerGanador() {
        if (!EstaFinalizado()) {
            return "";
        }
        int ComparacionPuntaje = Integer.compare(ResultadoRetador.ObtenerPuntaje(), ResultadoRetado.ObtenerPuntaje());
        if (ComparacionPuntaje > 0) {
            return UsernameRetador;
        }
        if (ComparacionPuntaje < 0) {
            return UsernameRetado;
        }
        int ComparacionEstrellas = Integer.compare(ResultadoRetador.ObtenerEstrellas(), ResultadoRetado.ObtenerEstrellas());
        if (ComparacionEstrellas > 0) {
            return UsernameRetador;
        }
        if (ComparacionEstrellas < 0) {
            return UsernameRetado;
        }
        int ComparacionTiempo = Float.compare(ResultadoRetador.ObtenerTiempo(), ResultadoRetado.ObtenerTiempo());
        if (ComparacionTiempo < 0) {
            return UsernameRetador;
        }
        if (ComparacionTiempo > 0) {
            return UsernameRetado;
        }

        return "Empate";
    }

    void RegistrarResultado(String UsernameJugador, ResultadoTurnoRivalidad Resultado) {
        String UsernameNormalizado = UsernameJugador.trim().toLowerCase();
        if (UsernameRetador.equals(UsernameNormalizado) && ResultadoRetador == null) {
            ResultadoRetador = Resultado;
            return;
        }
        if (UsernameRetado.equals(UsernameNormalizado) && ResultadoRetador != null && ResultadoRetado == null) {
            ResultadoRetado = Resultado;
            return;
        }
        throw new IllegalStateException("El jugador no tiene turno disponible en este duelo");
    }
}
