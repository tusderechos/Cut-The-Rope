/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.ranking;

/**
 *
 * @author Hp
 */
public final class EntradaRanking {
    private final int Posicion;
    private final String Username;
    private final String NombreCompleto;
    private final int PuntajeTotal;
    private final int EstrellasTotales;
    private final int NivelesCompletados;

    public EntradaRanking(int Posicion, String Username, String NombreCompleto, int PuntajeTotal, int EstrellasTotales, int NivelesCompletados) {
        if (Posicion < 1 || Username == null || Username.trim().isEmpty() || PuntajeTotal < 0 || EstrellasTotales < 0 || NivelesCompletados < 0) {
            throw new IllegalArgumentException("Los datos del ranking no son validos");
        }

        this.Posicion = Posicion;
        this.Username = Username;
        this.NombreCompleto = NombreCompleto == null || NombreCompleto.trim().isEmpty() ? Username : NombreCompleto;
        this.PuntajeTotal = PuntajeTotal;
        this.EstrellasTotales = EstrellasTotales;
        this.NivelesCompletados = NivelesCompletados;
    }

    public int ObtenerPosicion() {
        return Posicion;
    }

    public String ObtenerUsername() {
        return Username;
    }

    public String ObtenerNombreCompleto() {
        return NombreCompleto;
    }

    public int ObtenerPuntajeTotal() {
        return PuntajeTotal;
    }

    public int ObtenerEstrellasTotales() {
        return EstrellasTotales;
    }

    public int ObtenerNivelesCompletados() {
        return NivelesCompletados;
    }
}
