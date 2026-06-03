/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.obstaculos;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObstaculoPeligrosoTest {
    @Test
    void DetectaContactoEnBordeDelRectangulo() {
        ObstaculoPeligroso ObstaculoActual = new ObstaculoPeligroso(new Vector2(2f, 2f), 1f, 1f);

        assertTrue(ObstaculoActual.TocaDulce(new Vector2(1.81f, 2.5f), 0.2f));
        assertFalse(ObstaculoActual.TocaDulce(new Vector2(1.79f, 2.5f), 0.2f));
    }
}

