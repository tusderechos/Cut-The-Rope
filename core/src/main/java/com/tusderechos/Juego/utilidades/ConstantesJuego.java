package com.tusderechos.Juego.utilidades;

public final class ConstantesJuego {
    public static final int ANCHO_VENTANA = 480;
    public static final int ALTO_VENTANA = 800;
    public static final float PIXELES_POR_METRO = 100f;
    public static final float ANCHO_MUNDO = ANCHO_VENTANA / PIXELES_POR_METRO;
    public static final float ALTO_MUNDO = ALTO_VENTANA / PIXELES_POR_METRO;
    public static final float GRAVEDAD = -9.8f;
    public static final float RADIO_DULCE = 0.18f;
    public static final float RADIO_MONSTRUO = 0.38f;
    public static final float RADIO_ESTRELLA = 0.13f;
    public static final float MARGEN_CORTE_CUERDA = 0.16f;

    private ConstantesJuego() {
    }
}
