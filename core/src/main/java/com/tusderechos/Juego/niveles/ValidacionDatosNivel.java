/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import java.util.List;

final class ValidacionDatosNivel {
    private ValidacionDatosNivel() {
    }

    static void ValidarVector(Vector2 Vector, String Nombre) {
        if (Vector == null || !Float.isFinite(Vector.x) || !Float.isFinite(Vector.y)) {
            throw new IllegalArgumentException(Nombre + " debe tener coordenadas validas");
        }
    }

    static void ValidarMedidaPositiva(float Medida, String Nombre) {
        if (!Float.isFinite(Medida) || Medida <= 0f) {
            throw new IllegalArgumentException(Nombre + " debe ser positiva");
        }
    }

    static void ValidarLista(List<?> Elementos, String Nombre) {
        if (Elementos == null || Elementos.contains(null)) {
            throw new IllegalArgumentException(Nombre + " no puede contener valores nulos");
        }
    }
}

