/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.social;

import LogicaArchivos.Usuarios.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Hp
 */
public class GestorSeguimientoTest {

    @Test
    public void SeguirUsuarioAgregaRivalUnaSolaVez() {
        Usuario UsuarioActivo = new Usuario("chantos06", "123", "Carlos Reyes", "");
        Usuario UsuarioObjetivo = new Usuario("luna", "123", "Maria Lopez", "");

        assertTrue(GestorSeguimiento.SeguirUsuario(UsuarioActivo, UsuarioObjetivo));
        assertFalse(GestorSeguimiento.SeguirUsuario(UsuarioActivo, UsuarioObjetivo));
        assertEquals(1, UsuarioActivo.getListaRivales().size());
        assertEquals("luna", UsuarioActivo.getListaRivales().get(0));
    }

    @Test
    public void SeguirUsuarioRechazaMismoUsuario() {
        Usuario UsuarioActivo = new Usuario("chantos06", "123", "Carlos Reyes", "");
        Usuario UsuarioObjetivo = new Usuario("CHANTOS06", "123", "Carlos Reyes", "");

        assertFalse(GestorSeguimiento.SeguirUsuario(UsuarioActivo, UsuarioObjetivo));
        assertTrue(UsuarioActivo.getListaRivales().isEmpty());
    }

    @Test
    public void YaSigueIgnoraMayusculas() {
        Usuario UsuarioActivo = new Usuario("chantos06", "123", "Carlos Reyes", "");
        Usuario UsuarioObjetivo = new Usuario("luna", "123", "Maria Lopez", "");
        UsuarioActivo.getListaRivales().add("LUNA");

        assertTrue(GestorSeguimiento.YaSigue(UsuarioActivo, UsuarioObjetivo));
    }

    @Test
    public void SonRivalesMutuosSoloSiAmbosSeSiguen() {
        Usuario PrimerUsuario = new Usuario("chantos06", "123", "Carlos Reyes", "");
        Usuario SegundoUsuario = new Usuario("luna", "123", "Maria Lopez", "");

        PrimerUsuario.getListaRivales().add("luna");
        assertFalse(GestorSeguimiento.SonRivalesMutuos(PrimerUsuario, SegundoUsuario));

        SegundoUsuario.getListaRivales().add("chantos06");
        assertTrue(GestorSeguimiento.SonRivalesMutuos(PrimerUsuario, SegundoUsuario));
    }
}
