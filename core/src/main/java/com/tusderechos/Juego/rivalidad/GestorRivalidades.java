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
import java.util.List;

public final class GestorRivalidades {
    private GestorRivalidades() {
    }

    public static SolicitudRivalidad CrearSolicitud(DatosReto Reto, String UsernameRetador, String UsernameRetado) {
        return new SolicitudRivalidad(Reto, UsernameRetador, UsernameRetado);
    }

    public static void AceptarSolicitud(SolicitudRivalidad Solicitud, String UsernameActual) {
        ValidarSolicitud(Solicitud);
        ValidarUsuario(UsernameActual);
        if (Solicitud.ObtenerEstado() != EstadoRivalidad.Pendiente || !Solicitud.ObtenerUsernameRetado().equals(NormalizarUsuario(UsernameActual))) {
            throw new IllegalStateException("Solo el usuario retado puede aceptar una solicitud pendiente");
        }
        Solicitud.EstablecerEstado(EstadoRivalidad.TurnoRetado);
    }

    public static void RechazarSolicitud(SolicitudRivalidad Solicitud, String UsernameActual) {
        ValidarSolicitud(Solicitud);
        ValidarUsuario(UsernameActual);
        if (Solicitud.ObtenerEstado() != EstadoRivalidad.Pendiente || !Solicitud.ObtenerUsernameRetado().equals(NormalizarUsuario(UsernameActual))) {
            throw new IllegalStateException("Solo el usuario retado puede rechazar una solicitud pendiente");
        }
        Solicitud.EstablecerEstado(EstadoRivalidad.Rechazada);
    }

    public static void RegistrarResultado(SolicitudRivalidad Solicitud, String UsernameActual, ResultadoNivel ResultadoNivelActual) {
        ValidarSolicitud(Solicitud);
        ValidarUsuario(UsernameActual);
        if (ResultadoNivelActual == null) {
            throw new IllegalArgumentException("El resultado del nivel no puede ser nulo");
        }
        if (ResultadoNivelActual.ObtenerNumeroNivel() != GestorRetos.ObtenerNivelReto(Solicitud.ObtenerReto()).ObtenerNumero()) {
            throw new IllegalArgumentException("El resultado no pertenece al nivel de la rivalidad");
        }
        String UsernameNormalizado = NormalizarUsuario(UsernameActual);
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.TurnoRetado && Solicitud.ObtenerUsernameRetado().equals(UsernameNormalizado)) {
            Solicitud.EstablecerResultadoRetado(ResultadoTurnoRivalidad.CrearDesdeResultadoNivel(UsernameNormalizado, ResultadoNivelActual));
            Solicitud.EstablecerEstado(EstadoRivalidad.TurnoRetador);
            return;
        }
        if (Solicitud.ObtenerEstado() == EstadoRivalidad.TurnoRetador && Solicitud.ObtenerUsernameRetador().equals(UsernameNormalizado)) {
            Solicitud.EstablecerResultadoRetador(ResultadoTurnoRivalidad.CrearDesdeResultadoNivel(UsernameNormalizado, ResultadoNivelActual));
            Solicitud.EstablecerEstado(EstadoRivalidad.Finalizada);
            return;
        }
        throw new IllegalStateException("El usuario no tiene el turno de esta rivalidad");
    }

    public static SolicitudRivalidad BuscarPorId(List<SolicitudRivalidad> Solicitudes, String Id) {
        if (Solicitudes == null || Id == null || Id.trim().isEmpty()) {
            return null;
        }
        for (SolicitudRivalidad Solicitud : Solicitudes) {
            if (Solicitud != null && Solicitud.ObtenerId().equals(Id.trim())) {
                return Solicitud;
            }
        }

        return null;
    }

    public static boolean ParticipaUsuario(SolicitudRivalidad Solicitud, String Username) {
        if (Solicitud == null || Username == null || Username.trim().isEmpty()) {
            return false;
        }
        String UsernameNormalizado = NormalizarUsuario(Username);

        return Solicitud.ObtenerUsernameRetador().equals(UsernameNormalizado) || Solicitud.ObtenerUsernameRetado().equals(UsernameNormalizado);
    }

    public static boolean TieneTurno(SolicitudRivalidad Solicitud, String Username) {
        if (Solicitud == null || Username == null || Username.trim().isEmpty()) {
            return false;
        }

        return Solicitud.ObtenerUsernameConTurno().equals(NormalizarUsuario(Username));
    }

    private static void ValidarSolicitud(SolicitudRivalidad Solicitud) {
        if (Solicitud == null) {
            throw new IllegalArgumentException("La solicitud de rivalidad no puede ser nula");
        }
    }

    private static void ValidarUsuario(String Username) {
        if (Username == null || Username.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacio");
        }
    }

    private static String NormalizarUsuario(String Username) {
        return Username.trim().toLowerCase();
    }
}
