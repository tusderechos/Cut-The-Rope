/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

public class ResultadoNivel {
    private final int NumeroNivel;
    private final int Estrellas;
    private final int Puntaje;
    private final float Tiempo;

    public ResultadoNivel(int NumeroNivel, int Estrellas, int Puntaje, float Tiempo) {
        if (NumeroNivel < 1 || NumeroNivel > FabricaNiveles.CantidadNiveles() || Estrellas < 0 || Estrellas > 3 || Puntaje < 0 || !Float.isFinite(Tiempo) || Tiempo < 0f) {
            throw new IllegalArgumentException("Los datos del resultado no son validos");
        }
        
        this.NumeroNivel = NumeroNivel;
        this.Estrellas = Estrellas;
        this.Puntaje = Puntaje;
        this.Tiempo = Tiempo;
    }

    public int ObtenerNumeroNivel() {
        return NumeroNivel;
    }
    public int ObtenerEstrellas() {
        return Estrellas;
    }
    public int ObtenerPuntaje() {
        return Puntaje;
    }
    public float ObtenerTiempo() {
        return Tiempo;
    }
}

