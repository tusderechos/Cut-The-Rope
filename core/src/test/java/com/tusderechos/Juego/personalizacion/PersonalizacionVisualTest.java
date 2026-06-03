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
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalizacionVisualTest {
    @Test
    void ColorDulceNoExponeSuColorInterno() {
        Color ColorObtenido = ColorDulce.Rojo.ObtenerColor();

        ColorObtenido.set(Color.BLACK);

        assertEquals(new Color(0.95f, 0.18f, 0.20f, 1f), ColorDulce.Rojo.ObtenerColor());
        assertEquals(new Color(0.95f, 0.18f, 0.20f, 1f), new PersonalizacionDulce(ColorDulce.Rojo).ObtenerColor());
    }

    @Test
    void ColorMonstruoNoExponeSuColorInterno() {
        Color ColorObtenido = ColorMonstruo.Verde.ObtenerColor();

        ColorObtenido.set(Color.BLACK);

        assertEquals(new Color(0.25f, 0.80f, 0.32f, 1f), ColorMonstruo.Verde.ObtenerColor());
        assertEquals(new Color(0.25f, 0.80f, 0.32f, 1f), new PersonalizacionMonstruo(ColorMonstruo.Verde).ObtenerColor());
    }

    @Test
    void PersonalizacionVisualAislaColorDeEntradaYSalida() {
        Color ColorOriginal = new Color(0.12f, 0.34f, 0.56f, 1f);
        PersonalizacionVisual Personalizacion = new PersonalizacionVisual(ColorOriginal);

        ColorOriginal.set(Color.BLACK);
        Personalizacion.ObtenerColor().set(Color.WHITE);

        assertEquals(new Color(0.12f, 0.34f, 0.56f, 1f), Personalizacion.ObtenerColor());
    }
}

