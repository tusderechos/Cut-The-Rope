package com.tusderechos.Juego.personalizacion;

import com.tusderechos.Juego.enums.ColorMonstruo;

public class PersonalizacionMonstruo extends PersonalizacionVisual {
    private final ColorMonstruo colorMonstruo;

    public PersonalizacionMonstruo(ColorMonstruo colorMonstruo) {
        super(colorMonstruo.obtenerColor());
        this.colorMonstruo = colorMonstruo;
    }

    public ColorMonstruo obtenerColorMonstruo() {
        return colorMonstruo;
    }
}
