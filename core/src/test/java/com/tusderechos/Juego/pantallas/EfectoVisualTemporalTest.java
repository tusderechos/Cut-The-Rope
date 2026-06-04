/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

class EfectoVisualTemporalTest {
    @Test
    void FinalizaDespuesDeConsumirSuDuracion() {
        EfectoVisualTemporal Efecto = new EfectoVisualTemporal(new Vector2(1f, 2f), 0.2f, 0.6f, Color.GOLD);

        Efecto.Actualizar(0.3f);
        assertFalse(Efecto.EstaFinalizado());

        Efecto.Actualizar(0.3f);
        assertTrue(Efecto.EstaFinalizado());
    }
}
