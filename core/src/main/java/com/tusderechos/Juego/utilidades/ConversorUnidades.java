package com.tusderechos.Juego.utilidades;

public final class ConversorUnidades {
    private ConversorUnidades() {
    }

    public static final float pixelesAMetros(float pixeles) {
        return pixeles / ConstantesJuego.PIXELES_POR_METRO;
    }

    public static final float metrosAPixeles(float metros) {
        return metros * ConstantesJuego.PIXELES_POR_METRO;
    }
}
