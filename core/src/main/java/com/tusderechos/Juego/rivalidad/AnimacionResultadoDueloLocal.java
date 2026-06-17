/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

public final class AnimacionResultadoDueloLocal {
    private final float DuracionConteoJugador;
    private final float DuracionPausaAntesGanador;
    private final float DuracionRevealGanador;
    private float TiempoAcumulado;

    public AnimacionResultadoDueloLocal(float DuracionConteoJugador, float DuracionPausaAntesGanador, float DuracionRevealGanador) {
        if (!Float.isFinite(DuracionConteoJugador) || DuracionConteoJugador <= 0f || !Float.isFinite(DuracionPausaAntesGanador) || DuracionPausaAntesGanador < 0f || !Float.isFinite(DuracionRevealGanador) || DuracionRevealGanador <= 0f) {
            throw new IllegalArgumentException("Los tiempos de animacion del duelo no son validos");
        }
        this.DuracionConteoJugador = DuracionConteoJugador;
        this.DuracionPausaAntesGanador = DuracionPausaAntesGanador;
        this.DuracionRevealGanador = DuracionRevealGanador;
    }

    public void Avanzar(float Delta) {
        if (Float.isFinite(Delta) && Delta > 0f) {
            TiempoAcumulado += Delta;
        }
    }

    public int ObtenerPuntajeRetador(ResultadoTurnoRivalidad Resultado) {
        return CalcularPuntaje(Resultado, ProgresoConteoRetador());
    }

    public int ObtenerPuntajeRetado(ResultadoTurnoRivalidad Resultado) {
        return CalcularPuntaje(Resultado, ProgresoConteoRetado());
    }

    public boolean DebeMostrarGanador() {
        return TiempoAcumulado >= ObtenerInicioRevealGanador();
    }

    public boolean FinalizoRevealGanador() {
        return ObtenerProgresoRevealGanador() >= 1f;
    }

    public float ObtenerProgresoRevealGanador() {
        return Limitar01((TiempoAcumulado - ObtenerInicioRevealGanador()) / DuracionRevealGanador);
    }

    private int CalcularPuntaje(ResultadoTurnoRivalidad Resultado, float Progreso) {
        if (Resultado == null) {
            return 0;
        }

        return Math.round(Resultado.ObtenerPuntaje() * Progreso);
    }

    private float ProgresoConteoRetador() {
        return Limitar01(TiempoAcumulado / DuracionConteoJugador);
    }

    private float ProgresoConteoRetado() {
        return Limitar01((TiempoAcumulado - DuracionConteoJugador) / DuracionConteoJugador);
    }

    private float ObtenerInicioRevealGanador() {
        return (DuracionConteoJugador * 2f) + DuracionPausaAntesGanador;
    }

    private float Limitar01(float Valor) {
        if (Valor < 0f) {
            return 0f;
        }
        if (Valor > 1f) {
            return 1f;
        }

        return Valor;
    }
}