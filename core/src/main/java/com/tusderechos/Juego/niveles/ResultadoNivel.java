package com.tusderechos.Juego.niveles;

public class ResultadoNivel {
    private final int numeroNivel;
    private final int estrellas;
    private final int puntaje;
    private final float tiempo;

    public ResultadoNivel(int numeroNivel, int estrellas, int puntaje, float tiempo) {
        if (numeroNivel < 1 || numeroNivel > 5 || estrellas < 0 || estrellas > 3 || puntaje < 0
            || !Float.isFinite(tiempo) || tiempo < 0f) {
            throw new IllegalArgumentException("Los datos del resultado no son validos");
        }
        this.numeroNivel = numeroNivel;
        this.estrellas = estrellas;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
    }

    public int obtenerNumeroNivel() { return numeroNivel; }
    public int obtenerEstrellas() { return estrellas; }
    public int obtenerPuntaje() { return puntaje; }
    public float obtenerTiempo() { return tiempo; }
}
