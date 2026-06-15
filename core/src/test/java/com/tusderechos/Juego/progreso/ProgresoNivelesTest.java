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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgresoNivelesTest {
    @Test
    void InvitadoSoloTieneDesbloqueadoElPrimerNivel() {
        assertTrue(ProgresoNiveles.NivelEstaDesbloqueado(null, 1));
        assertFalse(ProgresoNiveles.NivelEstaDesbloqueado(null, 2));
        assertFalse(ProgresoNiveles.NivelEstaDesbloqueado(null, 5));
    }

    @Test
    void UsuarioNuevoSoloTieneDesbloqueadoElPrimerNivel() {
        Usuario UsuarioActual = new Usuario("ana", "123", "Ana");

        assertTrue(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 1));
        assertFalse(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 2));
    }

    @Test
    void UsuarioDesbloqueaElSiguienteNivelAlCompletarElAnterior() {
        Usuario UsuarioActual = new Usuario("ana", "123", "Ana");
        UsuarioActual.registrarPartida(1, true, 2, 8f);

        assertTrue(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 1));
        assertTrue(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 2));
        assertFalse(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 3));
    }

    @Test
    void UsuarioConNivelCuatroCompletadoTieneTodosLosNivelesNormalesDisponibles() {
        Usuario UsuarioActual = new Usuario("ana", "123", "Ana");
        UsuarioActual.registrarPartida(4, true, 3, 8f);

        assertTrue(ProgresoNiveles.NivelEstaDesbloqueado(UsuarioActual, 5));
    }

    @Test
    void RechazaNumerosDeNivelInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> ProgresoNiveles.NivelEstaDesbloqueado(null, 0));
    }
}
