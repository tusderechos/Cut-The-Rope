/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.personalizacion;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.tusderechos.Juego.interfaces.Personalizable;

public class PersonalizacionVisual implements Personalizable {
    private final Color ColorActual;

    public PersonalizacionVisual(Color ColorActual) {
        this.ColorActual = ColorActual.cpy();
    }

    @Override
    public final Color ObtenerColor() {
        return ColorActual.cpy();
    }
}

