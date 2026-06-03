/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.interfaces;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;

public interface Cortable {
    boolean ContienePuntoDeCorte(Vector2 PuntoMundo);
    void Cortar();
    boolean EstaCortada();
}

