/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.personalizacion;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.ColorDulce;

public class PersonalizacionDulce extends PersonalizacionVisual {
    private final ColorDulce ColorDulceElegido;

    public PersonalizacionDulce(ColorDulce ColorDulceElegido) {
        super(ColorDulceElegido.ObtenerColor());
        this.ColorDulceElegido = ColorDulceElegido;
    }

    public ColorDulce ObtenerColorDulce() {
        return ColorDulceElegido;
    }
}

