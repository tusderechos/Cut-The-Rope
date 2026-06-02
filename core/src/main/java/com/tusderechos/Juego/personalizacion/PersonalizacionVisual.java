package com.tusderechos.Juego.personalizacion;

import com.badlogic.gdx.graphics.Color;
import com.tusderechos.Juego.interfaces.Personalizable;

public class PersonalizacionVisual implements Personalizable {
    private final Color color;

    public PersonalizacionVisual(Color color) {
        this.color = color.cpy();
    }

    @Override
    public Color obtenerColor() {
        return color.cpy();
    }
}
