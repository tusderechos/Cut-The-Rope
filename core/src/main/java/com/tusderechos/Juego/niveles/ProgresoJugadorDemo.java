package com.tusderechos.Juego.niveles;

import java.util.ArrayList;
import java.util.List;

public class ProgresoJugadorDemo {
    private final List<ResultadoNivel> mejoresResultados = new ArrayList<>();

    public boolean nivelEstaDesbloqueado(int numeroNivel) {
        return numeroNivel >= 1 && numeroNivel <= 5;
    }

    public void registrarResultado(ResultadoNivel resultadoNivel) {
        for (int indice = 0; indice < mejoresResultados.size(); indice++) {
            ResultadoNivel resultadoGuardado = mejoresResultados.get(indice);
            if (resultadoGuardado.obtenerNumeroNivel() == resultadoNivel.obtenerNumeroNivel()) {
                if (resultadoNivel.obtenerPuntaje() > resultadoGuardado.obtenerPuntaje()) {
                    mejoresResultados.set(indice, resultadoNivel);
                }
                return;
            }
        }
        mejoresResultados.add(resultadoNivel);
    }

    public List<ResultadoNivel> obtenerMejoresResultados() { return new ArrayList<>(mejoresResultados); }

    public int buscarSiguienteNivelDisponibleRecursivo(List<DatosNivel> niveles, int nivelActual) {
        return buscarDesdeIndice(niveles, 0, nivelActual, nivelActual);
    }

    private int buscarDesdeIndice(List<DatosNivel> niveles, int indice, int nivelActual, int mejorCandidato) {
        if (indice >= niveles.size()) {
            return mejorCandidato;
        }
        int candidato = niveles.get(indice).obtenerNumero();
        if (candidato > nivelActual && nivelEstaDesbloqueado(candidato)
            && (mejorCandidato == nivelActual || candidato < mejorCandidato)) {
            mejorCandidato = candidato;
        }
        return buscarDesdeIndice(niveles, indice + 1, nivelActual, mejorCandidato);
    }
}
