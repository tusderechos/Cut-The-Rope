/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
public final class AnimacionPanelResultado {
    private static final float DuracionConteoPuntaje = 1.5f;
    private static final float IntervaloLinea = 0.35f;
    private final int PuntajeFinal;
    private float TiempoAnimacion;

    public AnimacionPanelResultado(int PuntajeFinal) {
        if (PuntajeFinal < 0) {
            throw new IllegalArgumentException("El puntaje final no puede ser negativo");
        }
        this.PuntajeFinal = PuntajeFinal;
    }

    public void Actualizar(float Delta) {
        if (!Float.isFinite(Delta) || Delta <= 0f) {
            return;
        }
        TiempoAnimacion += Delta;
    }

    public int ObtenerPuntajeVisible() {
        float Progreso = Math.min(1f, TiempoAnimacion / DuracionConteoPuntaje);
        return Math.round(PuntajeFinal * Progreso);
    }

    public int ObtenerCantidadLineasVisibles(int TotalLineas) {
        if (TotalLineas <= 0) {
            return 0;
        }
        int LineasVisibles = 1 + (int) (TiempoAnimacion / IntervaloLinea);
        return Math.min(TotalLineas, LineasVisibles);
    }
}
