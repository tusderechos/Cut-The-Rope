/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.interfaces.Dibujable;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Burbuja implements Dibujable {
    private final Vector2 Posicion;
    private final float Radio;
    private boolean Activa = true;
    private boolean Adherida;

    public Burbuja(Vector2 Posicion, float Radio) {
        this.Posicion = new Vector2(Posicion);
        this.Radio = Radio;
    }

    public boolean ContienePunto(Vector2 PuntoMundo) {
        return Activa && Posicion.dst(PuntoMundo) <= Radio;
    }
    public boolean EstaActiva() {
        return Activa;
    }
    public boolean EstaAdherida() {
        return Activa && Adherida;
    }
    public boolean IntentarAdherir(Vector2 PosicionDulce) {
        if (!Activa || Adherida || Posicion.dst(PosicionDulce) > Radio + ConstantesJuego.RadioDulce) {
            return false;
        }
        Adherida = true;
        Posicion.set(PosicionDulce);
        return true;
    }
    public void Reventar() {
        if (Adherida) {
            Activa = false;
        }
    }
    public void SeguirDulce(Vector2 PosicionDulce) {
        if (EstaAdherida()) {
            Posicion.set(PosicionDulce);
        }
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        if (!Activa) {
            return;
        }
        ShapeRendererActual.setColor(new Color(0.40f, 0.82f, 0.96f, 0.45f));
        ShapeRendererActual.circle(Posicion.x, Posicion.y, Radio, 32);
    }
}

