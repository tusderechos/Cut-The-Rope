/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.ranking;

import LogicaArchivos.Usuarios.Usuario;
import com.tusderechos.Juego.social.GestorSeguimiento;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Hp
 */
public final class CalculadoraRanking {
    private CalculadoraRanking() {
    }

    public static List<EntradaRanking> CrearRankingGlobal(List<Usuario> Usuarios) {
        ValidarUsuarios(Usuarios);

        return CrearRankingDesdeUsuarios(FiltrarUsuariosValidos(Usuarios));
    }

    public static List<EntradaRanking> CrearRankingAmigos(Usuario UsuarioActivo, List<Usuario> Usuarios) {
        if (UsuarioActivo == null) {
            throw new IllegalArgumentException("El usuario activo es obligatorio");
        }
        ValidarUsuarios(Usuarios);

        List<Usuario> UsuariosAmigos = new ArrayList<>();
        for (Usuario UsuarioActual : FiltrarUsuariosValidos(Usuarios)) {
            if (EsUsuarioActivo(UsuarioActivo, UsuarioActual) || GestorSeguimiento.SonRivalesMutuos(UsuarioActivo, UsuarioActual)) {
                UsuariosAmigos.add(UsuarioActual);
            }
        }

        return CrearRankingDesdeUsuarios(UsuariosAmigos);
    }

    private static List<Usuario> FiltrarUsuariosValidos(List<Usuario> Usuarios) {
        List<Usuario> UsuariosValidos = new ArrayList<>();
        for (Usuario UsuarioActual : Usuarios) {
            if (UsuarioActual != null && UsuarioActual.getUsername() != null && !UsuarioActual.getUsername().trim().isEmpty()) {
                UsuariosValidos.add(UsuarioActual);
            }
        }

        return UsuariosValidos;
    }

    private static List<EntradaRanking> CrearRankingDesdeUsuarios(List<Usuario> Usuarios) {
        List<Usuario> UsuariosOrdenados = new ArrayList<>(Usuarios);
        UsuariosOrdenados.sort(new Comparator<Usuario>() {
            @Override
            public int compare(Usuario PrimerUsuario, Usuario SegundoUsuario) {
                int ComparacionPuntaje = Integer.compare(SegundoUsuario.getPuntajeTotal(), PrimerUsuario.getPuntajeTotal());
                if (ComparacionPuntaje != 0) {
                    return ComparacionPuntaje;
                }

                return PrimerUsuario.getUsername().compareToIgnoreCase(SegundoUsuario.getUsername());
            }
        });

        List<EntradaRanking> Ranking = new ArrayList<>();
        for (int Indice = 0; Indice < UsuariosOrdenados.size(); Indice++) {
            Usuario UsuarioActual = UsuariosOrdenados.get(Indice);
            Ranking.add(new EntradaRanking(Indice + 1, UsuarioActual.getUsername(), UsuarioActual.getNombreCompleto(), UsuarioActual.getPuntajeTotal(), UsuarioActual.getEstrellasTotales(), UsuarioActual.getNivelesCompletados()));
        }

        return Ranking;
    }

    private static boolean EsUsuarioActivo(Usuario UsuarioActivo, Usuario UsuarioActual) {
        return UsuarioActivo.getUsername() != null
                && UsuarioActual.getUsername() != null
                && UsuarioActivo.getUsername().equalsIgnoreCase(UsuarioActual.getUsername());
    }

    private static void ValidarUsuarios(List<Usuario> Usuarios) {
        if (Usuarios == null) {
            throw new IllegalArgumentException("La lista de usuarios no puede ser nula");
        }
    }
}
