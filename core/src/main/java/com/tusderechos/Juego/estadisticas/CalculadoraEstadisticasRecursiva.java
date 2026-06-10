/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.estadisticas;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.persistencia.RegistroPartida;
import java.util.List;

public final class CalculadoraEstadisticasRecursiva {
    private CalculadoraEstadisticasRecursiva() {
    }

    public static int SumarEstrellas(List<RegistroPartida> Registros) {
        ValidarRegistros(Registros);

        return SumarEstrellasDesde(Registros, 0);
    }

    public static int ObtenerMejorPuntaje(List<RegistroPartida> Registros) {
        ValidarRegistros(Registros);
        if (Registros.isEmpty()) {
            return 0;
        }

        return ObtenerMejorPuntajeDesde(Registros, 0, 0);
    }

    private static int SumarEstrellasDesde(List<RegistroPartida> Registros, int IndiceActual) {
        if (IndiceActual >= Registros.size()) {
            return 0;
        }

        return Registros.get(IndiceActual).ObtenerEstrellas() + SumarEstrellasDesde(Registros, IndiceActual + 1);
    }

    private static int ObtenerMejorPuntajeDesde(List<RegistroPartida> Registros, int IndiceActual, int MejorPuntajeActual) {
        if (IndiceActual >= Registros.size()) {
            return MejorPuntajeActual;
        }
        int MejorPuntajeNuevo = Math.max(MejorPuntajeActual, Registros.get(IndiceActual).ObtenerPuntaje());

        return ObtenerMejorPuntajeDesde(Registros, IndiceActual + 1, MejorPuntajeNuevo);
    }

    private static void ValidarRegistros(List<RegistroPartida> Registros) {
        if (Registros == null) {
            throw new IllegalArgumentException("Los registros no pueden ser nulos");
        }
    }
}
