package com.tusderechos.Juego.personalizacion;

import com.tusderechos.Juego.enums.ColorDulce;

public class PersonalizacionDulce extends PersonalizacionVisual {
    private final ColorDulce colorDulce;

    public PersonalizacionDulce(ColorDulce colorDulce) {
        super(colorDulce.obtenerColor());
        this.colorDulce = colorDulce;
    }

    public ColorDulce obtenerColorDulce() {
        return colorDulce;
    }
}
