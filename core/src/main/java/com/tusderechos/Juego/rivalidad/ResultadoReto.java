/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

public final class ResultadoReto {
    private final DatosReto Reto;
    private final int PuntajeJugador;
    private final int EstrellasJugador;
    private final boolean RetoSuperado;

    public ResultadoReto(DatosReto Reto, int PuntajeJugador, int EstrellasJugador, boolean RetoSuperado) {
        if (Reto == null || PuntajeJugador < 0 || EstrellasJugador < 0 || EstrellasJugador > 3) {
            throw new IllegalArgumentException("Los datos del resultado del reto no son validos");
        }
        this.Reto = Reto;
        this.PuntajeJugador = PuntajeJugador;
        this.EstrellasJugador = EstrellasJugador;
        this.RetoSuperado = RetoSuperado;
    }

    public DatosReto ObtenerReto() {
        return Reto;
    }

    public int ObtenerPuntajeJugador() {
        return PuntajeJugador;
    }

    public int ObtenerEstrellasJugador() {
        return EstrellasJugador;
    }

    public int ObtenerDiferenciaPuntaje() {
        return PuntajeJugador - Reto.ObtenerPuntajeObjetivo();
    }

    public boolean RetoFueSuperado() {
        return RetoSuperado;
    }
}
