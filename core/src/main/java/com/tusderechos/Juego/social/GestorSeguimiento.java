/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.social;

import LogicaArchivos.Usuarios.Usuario;

/**
 *
 * @author Hp
 */
public final class GestorSeguimiento {

    private GestorSeguimiento() {
    }

    public static boolean SeguirUsuario(Usuario UsuarioActivo, Usuario UsuarioObjetivo) {
        if (!PuedeSeguir(UsuarioActivo, UsuarioObjetivo)) {
            return false;
        }

        UsuarioActivo.getListaRivales().add(UsuarioObjetivo.getUsername());
        return true;
    }

    public static boolean PuedeSeguir(Usuario UsuarioActivo, Usuario UsuarioObjetivo) {
        return UsuarioActivo != null
                && UsuarioObjetivo != null
                && !EsMismoUsuario(UsuarioActivo, UsuarioObjetivo)
                && !YaSigue(UsuarioActivo, UsuarioObjetivo);
    }

    public static boolean YaSigue(Usuario UsuarioActivo, Usuario UsuarioObjetivo) {
        if (UsuarioActivo == null || UsuarioObjetivo == null || UsuarioActivo.getListaRivales() == null) {
            return false;
        }

        for (String UsernameRival : UsuarioActivo.getListaRivales()) {
            if (UsernameRival != null && UsernameRival.equalsIgnoreCase(UsuarioObjetivo.getUsername())) {
                return true;
            }
        }

        return false;
    }

    public static boolean SonRivalesMutuos(Usuario PrimerUsuario, Usuario SegundoUsuario) {
        return YaSigue(PrimerUsuario, SegundoUsuario) && YaSigue(SegundoUsuario, PrimerUsuario);
    }

    private static boolean EsMismoUsuario(Usuario UsuarioActivo, Usuario UsuarioObjetivo) {
        return UsuarioActivo.getUsername() != null
                && UsuarioActivo.getUsername().equalsIgnoreCase(UsuarioObjetivo.getUsername());
    }
}
