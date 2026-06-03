/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import java.util.ArrayList;
import java.util.List;

public class ProgresoJugadorDemo {
    private final List<ResultadoNivel> MejoresResultados = new ArrayList<>();

    public boolean NivelEstaDesbloqueado(int NumeroNivel) {
        return NumeroNivel >= 1 && NumeroNivel <= FabricaNiveles.CantidadNiveles();
    }

    public void RegistrarResultado(ResultadoNivel ResultadoNivelActual) {
        if (ResultadoNivelActual == null) {
            throw new IllegalArgumentException("El resultado no puede ser nulo");
        }
        for (int Indice = 0; Indice < MejoresResultados.size(); Indice++) {
            ResultadoNivel ResultadoGuardado = MejoresResultados.get(Indice);
            if (ResultadoGuardado.ObtenerNumeroNivel() == ResultadoNivelActual.ObtenerNumeroNivel()) {
                if (ResultadoNivelActual.ObtenerPuntaje() > ResultadoGuardado.ObtenerPuntaje()) {
                    MejoresResultados.set(Indice, ResultadoNivelActual);
                }
                return;
            }
        }
        MejoresResultados.add(ResultadoNivelActual);
    }

    public List<ResultadoNivel> ObtenerMejoresResultados() {
        return new ArrayList<>(MejoresResultados);
    }

    public int BuscarSiguienteNivelDisponibleRecursivo(List<DatosNivel> NivelesActuales, int NivelActual) {
        return BuscarDesdeIndice(NivelesActuales, 0, NivelActual, NivelActual);
    }

    private int BuscarDesdeIndice(List<DatosNivel> NivelesActuales, int Indice, int NivelActual, int MejorCandidato) {
        if (Indice >= NivelesActuales.size()) {
            return MejorCandidato;
        }
        int Candidato = NivelesActuales.get(Indice).ObtenerNumero();
        if (Candidato > NivelActual && NivelEstaDesbloqueado(Candidato) && (MejorCandidato == NivelActual || Candidato < MejorCandidato)) {
            MejorCandidato = Candidato;
        }
        return BuscarDesdeIndice(NivelesActuales, Indice + 1, NivelActual, MejorCandidato);
    }
}

