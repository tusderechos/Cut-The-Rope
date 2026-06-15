/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.social;

import LogicaArchivos.Usuarios.Usuario;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Hp
 */
public final class BusquedaJugadores {

    private BusquedaJugadores() {
    }

    public static List<Usuario> FiltrarUsuarios(List<Usuario> Usuarios, String TextoBusqueda, String UsernameExcluido) {
        List<Usuario> UsuariosFiltrados = new ArrayList<>();
        String TextoNormalizado = Normalizar(TextoBusqueda);
        String UsernameNormalizado = Normalizar(UsernameExcluido);

        if (Usuarios == null) {
            return UsuariosFiltrados;
        }

        for (Usuario UsuarioActual : Usuarios) {
            if (UsuarioActual == null || EsMismoUsuario(UsuarioActual, UsernameNormalizado)) {
                continue;
            }

            if (TextoNormalizado.isEmpty() || CoincideConBusqueda(UsuarioActual, TextoNormalizado)) {
                UsuariosFiltrados.add(UsuarioActual);
            }
        }

        UsuariosFiltrados.sort(Comparator.comparing(Usuario::getUsername, String.CASE_INSENSITIVE_ORDER));
        return UsuariosFiltrados;
    }

    private static boolean EsMismoUsuario(Usuario UsuarioActual, String UsernameNormalizado) {
        return !UsernameNormalizado.isEmpty() && Normalizar(UsuarioActual.getUsername()).equals(UsernameNormalizado);
    }

    private static boolean CoincideConBusqueda(Usuario UsuarioActual, String TextoNormalizado) {
        return Normalizar(UsuarioActual.getUsername()).contains(TextoNormalizado)
                || Normalizar(UsuarioActual.getNombreCompleto()).contains(TextoNormalizado);
    }

    private static String Normalizar(String Texto) {
        if (Texto == null) {
            return "";
        }

        return Texto.trim().toLowerCase(Locale.ROOT);
    }
}
