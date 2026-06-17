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
import java.util.HashMap;
import java.util.Map;

public final class GestorDueloLocal {
    private static final String PrefijoIdDuelo = "duelo-local:";
    private static final Map<String, DueloLocal> DuelosActivos = new HashMap<>();

    private GestorDueloLocal() {
    }

    public static DueloLocal CrearDuelo(DatosReto Reto, String UsernameRetador, String UsernameRetado) {
        DueloLocal Duelo = new DueloLocal(Reto, UsernameRetador, UsernameRetado);
        DuelosActivos.put(Duelo.ObtenerId(), Duelo);

        return Duelo;
    }

    public static String CrearIdTurno(DueloLocal Duelo, String UsernameJugador) {
        if (Duelo == null || UsernameJugador == null || UsernameJugador.trim().isEmpty()) {
            throw new IllegalArgumentException("Los datos del turno local no son validos");
        }

        return PrefijoIdDuelo + Duelo.ObtenerId() + ":" + UsernameJugador.trim().toLowerCase();
    }

    public static boolean EsIdDueloLocal(String IdTurno) {
        return IdTurno != null && IdTurno.startsWith(PrefijoIdDuelo);
    }

    public static DueloLocal ObtenerDueloDesdeId(String IdTurno) {
        if (!EsIdDueloLocal(IdTurno)) {
            return null;
        }
        String[] Partes = IdTurno.split(":", 3);
        if (Partes.length < 3) {
            return null;
        }

        return DuelosActivos.get(Partes[1]);
    }

    public static String ObtenerUsernameDesdeId(String IdTurno) {
        if (!EsIdDueloLocal(IdTurno)) {
            return "";
        }
        String[] Partes = IdTurno.split(":", 3);
        if (Partes.length < 3) {
            return "";
        }

        return Partes[2].trim().toLowerCase();
    }

    public static void RegistrarResultado(String IdTurno, ResultadoNivel ResultadoNivelActual) {
        if (!IntentarRegistrarResultado(IdTurno, ResultadoNivelActual)) {
            throw new IllegalArgumentException("El turno del duelo local no existe");
        }
    }

    public static boolean IntentarRegistrarResultado(String IdTurno, ResultadoNivel ResultadoNivelActual) {
        DueloLocal Duelo = ObtenerDueloDesdeId(IdTurno);
        String UsernameJugador = ObtenerUsernameDesdeId(IdTurno);
        if (Duelo == null || UsernameJugador.isEmpty()) {
            return false;
        }
        if (ResultadoNivelActual == null) {
            throw new IllegalArgumentException("El resultado del duelo local no puede ser nulo");
        }
        if (ResultadoNivelActual.ObtenerNumeroNivel() != GestorRetos.ObtenerNivelReto(Duelo.ObtenerReto()).ObtenerNumero()) {
            throw new IllegalArgumentException("El resultado no pertenece al nivel del duelo local");
        }
        Duelo.RegistrarResultado(UsernameJugador, ResultadoTurnoRivalidad.CrearDesdeResultadoNivel(UsernameJugador, ResultadoNivelActual));

        return true;
    }
}
