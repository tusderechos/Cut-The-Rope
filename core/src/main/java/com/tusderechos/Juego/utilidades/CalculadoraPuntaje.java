package com.tusderechos.Juego.utilidades;

import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.util.List;

public final class CalculadoraPuntaje {
    private CalculadoraPuntaje() {
    }

    public static int calcularPuntajeIntento(int estrellas, float tiempo, int fallos) {
        if (estrellas < 0 || estrellas > 3 || !Float.isFinite(tiempo) || tiempo < 0f || fallos < 0) {
            throw new IllegalArgumentException("Los datos del intento no son validos");
        }
        long bonoEstrellas = estrellas * 1000L;
        long bonoTiempo = Math.max(0L, 2000L - Math.round(tiempo * 50f));
        long puntaje = Math.max(0L, bonoEstrellas + bonoTiempo - fallos * 150L);
        return (int) Math.min(Integer.MAX_VALUE, puntaje);
    }

    public static int calcularPuntajeAcumuladoRecursivo(List<ResultadoNivel> resultados) {
        return (int) Math.min(Integer.MAX_VALUE, sumarDesdeIndice(resultados, 0));
    }

    private static long sumarDesdeIndice(List<ResultadoNivel> resultados, int indice) {
        if (indice >= resultados.size()) {
            return 0L;
        }
        return resultados.get(indice).obtenerPuntaje() + sumarDesdeIndice(resultados, indice + 1);
    }
}
