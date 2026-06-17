/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.ranking;

import LogicaArchivos.Usuarios.Usuario;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Hp
 */
class CalculadoraRankingTest {

    @Test
    void OrdenaRankingGlobalPorPuntajeTotal() {
        Usuario Santos = CrearUsuarioConPuntaje("santos", "Daniel Santos", 2200, 1300);
        Usuario Clara = CrearUsuarioConPuntaje("clara_09", "Clarissa", 4700);
        Usuario Ana = CrearUsuarioConPuntaje("ana", "Ana", 4700);

        List<EntradaRanking> Ranking = CalculadoraRanking.CrearRankingGlobal(Arrays.asList(Santos, Clara, Ana));

        assertEquals("ana", Ranking.get(0).ObtenerUsername());
        assertEquals(4700, Ranking.get(0).ObtenerPuntajeTotal());
        assertEquals(1, Ranking.get(0).ObtenerPosicion());
        assertEquals("clara_09", Ranking.get(1).ObtenerUsername());
        assertEquals("santos", Ranking.get(2).ObtenerUsername());
        assertEquals(3500, Ranking.get(2).ObtenerPuntajeTotal());
    }

    @Test
    void RankingDeAmigosIncluyeActivoYSeguimientosMutuos() {
        Usuario Activo = CrearUsuarioConPuntaje("santos", "Daniel Santos", 3000);
        Usuario AmigaMutua = CrearUsuarioConPuntaje("clara_09", "Clarissa", 4200);
        Usuario SoloSeguido = CrearUsuarioConPuntaje("miguel", "Miguel", 9000);
        Usuario Desconocida = CrearUsuarioConPuntaje("ana", "Ana", 8000);

        Activo.getListaRivales().add("clara_09");
        Activo.getListaRivales().add("miguel");
        AmigaMutua.getListaRivales().add("santos");

        List<EntradaRanking> Ranking = CalculadoraRanking.CrearRankingAmigos(Activo, Arrays.asList(AmigaMutua, SoloSeguido, Desconocida, Activo));

        assertEquals(2, Ranking.size());
        assertEquals("clara_09", Ranking.get(0).ObtenerUsername());
        assertEquals("santos", Ranking.get(1).ObtenerUsername());
    }

    @Test
    void RechazaDatosInvalidos() {
        Usuario UsuarioActivo = CrearUsuarioConPuntaje("santos", "Daniel Santos", 1000);

        assertThrows(IllegalArgumentException.class, () -> CalculadoraRanking.CrearRankingGlobal(null));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraRanking.CrearRankingAmigos(null, Arrays.asList(UsuarioActivo)));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraRanking.CrearRankingAmigos(UsuarioActivo, null));
    }

    private Usuario CrearUsuarioConPuntaje(String Username, String NombreCompleto, int... Puntajes) {
        Usuario UsuarioActual = new Usuario(Username, "1234", NombreCompleto, "");
        for (int Indice = 0; Indice < Puntajes.length; Indice++) {
            UsuarioActual.registrarPartida(Indice + 1, true, 3, 4f, Puntajes[Indice]);
        }

        return UsuarioActual;
    }
}
