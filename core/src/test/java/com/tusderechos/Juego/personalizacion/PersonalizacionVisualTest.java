package com.tusderechos.Juego.personalizacion;

import com.badlogic.gdx.graphics.Color;
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalizacionVisualTest {
    @Test
    void colorDulceNoExponeSuColorInterno() {
        Color colorObtenido = ColorDulce.ROJO.obtenerColor();

        colorObtenido.set(Color.BLACK);

        assertEquals(new Color(0.95f, 0.18f, 0.20f, 1f), ColorDulce.ROJO.obtenerColor());
        assertEquals(new Color(0.95f, 0.18f, 0.20f, 1f),
            new PersonalizacionDulce(ColorDulce.ROJO).obtenerColor());
    }

    @Test
    void colorMonstruoNoExponeSuColorInterno() {
        Color colorObtenido = ColorMonstruo.VERDE.obtenerColor();

        colorObtenido.set(Color.BLACK);

        assertEquals(new Color(0.25f, 0.80f, 0.32f, 1f), ColorMonstruo.VERDE.obtenerColor());
        assertEquals(new Color(0.25f, 0.80f, 0.32f, 1f),
            new PersonalizacionMonstruo(ColorMonstruo.VERDE).obtenerColor());
    }

    @Test
    void personalizacionVisualAislaColorDeEntradaYSalida() {
        Color colorOriginal = new Color(0.12f, 0.34f, 0.56f, 1f);
        PersonalizacionVisual personalizacion = new PersonalizacionVisual(colorOriginal);

        colorOriginal.set(Color.BLACK);
        personalizacion.obtenerColor().set(Color.WHITE);

        assertEquals(new Color(0.12f, 0.34f, 0.56f, 1f), personalizacion.obtenerColor());
    }
}
