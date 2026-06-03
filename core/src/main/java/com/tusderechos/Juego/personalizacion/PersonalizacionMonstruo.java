/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.personalizacion;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.ColorMonstruo;

public class PersonalizacionMonstruo extends PersonalizacionVisual {
    private final ColorMonstruo ColorMonstruoElegido;

    public PersonalizacionMonstruo(ColorMonstruo ColorMonstruoElegido) {
        super(ColorMonstruoElegido.ObtenerColor());
        this.ColorMonstruoElegido = ColorMonstruoElegido;
    }

    public ColorMonstruo ObtenerColorMonstruo() {
        return ColorMonstruoElegido;
    }
}

