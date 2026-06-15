/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.progreso;

/**
 *
 * @author Hp
 */

import LogicaArchivos.Usuarios.Usuario;

public final class ProgresoNiveles {
    private ProgresoNiveles() {
    }

    public static boolean NivelEstaDesbloqueado(Usuario UsuarioActual, int NumeroNivel) {
        if (NumeroNivel < 1) {
            throw new IllegalArgumentException("El numero del nivel debe ser mayor a cero");
        }
        if (NumeroNivel == 1) {
            return true;
        }
        if (UsuarioActual == null) {
            return false;
        }

        return UsuarioActual.getNivelesCompletados() >= NumeroNivel - 1;
    }
}
