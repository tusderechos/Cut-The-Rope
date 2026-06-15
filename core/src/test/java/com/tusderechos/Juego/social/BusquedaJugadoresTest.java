/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.social;

import LogicaArchivos.Usuarios.Usuario;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Hp
 */
public class BusquedaJugadoresTest {

    @Test
    public void FiltrarUsuariosEncuentraPorUsername() {
        List<Usuario> Usuarios = Arrays.asList(
                new Usuario("chantos06", "123", "Carlos Reyes", ""),
                new Usuario("luna", "123", "Maria Lopez", "")
        );

        List<Usuario> Resultado = BusquedaJugadores.FiltrarUsuarios(Usuarios, "chan", "luna");

        assertEquals(1, Resultado.size());
        assertEquals("chantos06", Resultado.get(0).getUsername());
    }

    @Test
    public void FiltrarUsuariosEncuentraPorNombreCompleto() {
        List<Usuario> Usuarios = Arrays.asList(
                new Usuario("chantos06", "123", "Carlos Reyes", ""),
                new Usuario("luna", "123", "Maria Lopez", "")
        );

        List<Usuario> Resultado = BusquedaJugadores.FiltrarUsuarios(Usuarios, "maria", "chantos06");

        assertEquals(1, Resultado.size());
        assertEquals("luna", Resultado.get(0).getUsername());
    }

    @Test
    public void FiltrarUsuariosExcluyeUsuarioActivo() {
        List<Usuario> Usuarios = Arrays.asList(
                new Usuario("chantos06", "123", "Carlos Reyes", ""),
                new Usuario("luna", "123", "Maria Lopez", "")
        );

        List<Usuario> Resultado = BusquedaJugadores.FiltrarUsuarios(Usuarios, "", "chantos06");

        assertEquals(1, Resultado.size());
        assertEquals("luna", Resultado.get(0).getUsername());
    }
}
