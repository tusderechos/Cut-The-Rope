/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class GestorRetos {
    private GestorRetos() {
    }

    public static DatosNivel ObtenerNivelReto(DatosReto Reto) {
        ValidarReto(Reto);

        return FabricaNiveles.ObtenerNivel(Reto.ObtenerCategoria(), Reto.ObtenerNumeroEnCategoria());
    }

    public static ResultadoReto EvaluarResultado(DatosReto Reto, ResultadoNivel ResultadoJugador) {
        ValidarReto(Reto);
        if (ResultadoJugador == null) {
            throw new IllegalArgumentException("El resultado del jugador no puede ser nulo");
        }
        DatosNivel NivelReto = ObtenerNivelReto(Reto);
        if (ResultadoJugador.ObtenerNumeroNivel() != NivelReto.ObtenerNumero()) {
            throw new IllegalArgumentException("El resultado no pertenece al nivel del reto");
        }
        boolean PuntajeCumplido = ResultadoJugador.ObtenerPuntaje() >= Reto.ObtenerPuntajeObjetivo();
        boolean EstrellasCumplidas = ResultadoJugador.ObtenerEstrellas() >= Reto.ObtenerEstrellasObjetivo();

        return new ResultadoReto(Reto, ResultadoJugador.ObtenerPuntaje(), ResultadoJugador.ObtenerEstrellas(), PuntajeCumplido && EstrellasCumplidas);
    }

    public static List<DatosReto> CrearRetosDePrueba() {
        return Collections.unmodifiableList(Arrays.asList(
            new DatosReto(CategoriaDificultad.Facil, 3, "Admin", 2600, 2),
            new DatosReto(CategoriaDificultad.Media, 3, "Admin", 3200, 2),
            new DatosReto(CategoriaDificultad.Dificil, 3, "Admin", 3800, 2)
        ));
    }

    private static void ValidarReto(DatosReto Reto) {
        if (Reto == null) {
            throw new IllegalArgumentException("El reto no puede ser nulo");
        }
    }
}
