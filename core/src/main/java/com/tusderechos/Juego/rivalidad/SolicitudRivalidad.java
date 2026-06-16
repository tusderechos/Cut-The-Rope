/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import java.io.Serializable;
import java.util.UUID;

public final class SolicitudRivalidad implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String Id;
    private final DatosReto Reto;
    private final String UsernameRetador;
    private final String UsernameRetado;
    private EstadoRivalidad Estado;
    private ResultadoTurnoRivalidad ResultadoRetado;
    private ResultadoTurnoRivalidad ResultadoRetador;

    public SolicitudRivalidad(DatosReto Reto, String UsernameRetador, String UsernameRetado) {
        this(UUID.randomUUID().toString(), Reto, UsernameRetador, UsernameRetado, EstadoRivalidad.Pendiente, null, null);
    }

    public SolicitudRivalidad(String Id, DatosReto Reto, String UsernameRetador, String UsernameRetado, EstadoRivalidad Estado, ResultadoTurnoRivalidad ResultadoRetado, ResultadoTurnoRivalidad ResultadoRetador) {
        if (Id == null || Id.trim().isEmpty() || Reto == null || UsernameRetador == null || UsernameRetador.trim().isEmpty() || UsernameRetado == null || UsernameRetado.trim().isEmpty() || Estado == null) {
            throw new IllegalArgumentException("Los datos de la solicitud de rivalidad no son validos");
        }
        String RetadorNormalizado = UsernameRetador.trim().toLowerCase();
        String RetadoNormalizado = UsernameRetado.trim().toLowerCase();
        if (RetadorNormalizado.equals(RetadoNormalizado)) {
            throw new IllegalArgumentException("El retador y el retado deben ser usuarios distintos");
        }
        this.Id = Id.trim();
        this.Reto = Reto;
        this.UsernameRetador = RetadorNormalizado;
        this.UsernameRetado = RetadoNormalizado;
        this.Estado = Estado;
        this.ResultadoRetado = ResultadoRetado;
        this.ResultadoRetador = ResultadoRetador;
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

    public EstadoRivalidad ObtenerEstado() {
        return Estado;
    }

    public ResultadoTurnoRivalidad ObtenerResultadoRetado() {
        return ResultadoRetado;
    }

    public ResultadoTurnoRivalidad ObtenerResultadoRetador() {
        return ResultadoRetador;
    }

    public String ObtenerUsernameConTurno() {
        if (Estado == EstadoRivalidad.TurnoRetado) {
            return UsernameRetado;
        }
        if (Estado == EstadoRivalidad.TurnoRetador) {
            return UsernameRetador;
        }

        return "";
    }

    public String ObtenerGanador() {
        if (Estado != EstadoRivalidad.Finalizada || ResultadoRetado == null || ResultadoRetador == null) {
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

    void EstablecerEstado(EstadoRivalidad Estado) {
        this.Estado = Estado;
    }

    void EstablecerResultadoRetado(ResultadoTurnoRivalidad ResultadoRetado) {
        this.ResultadoRetado = ResultadoRetado;
    }

    void EstablecerResultadoRetador(ResultadoTurnoRivalidad ResultadoRetador) {
        this.ResultadoRetador = ResultadoRetador;
    }
}
