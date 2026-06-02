package com.tusderechos.Juego.obstaculos;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObstaculoPeligrosoTest {
    @Test
    void detectaContactoEnBordeDelRectangulo() {
        ObstaculoPeligroso obstaculo = new ObstaculoPeligroso(new Vector2(2f, 2f), 1f, 1f);

        assertTrue(obstaculo.tocaDulce(new Vector2(1.81f, 2.5f), 0.2f));
        assertFalse(obstaculo.tocaDulce(new Vector2(1.79f, 2.5f), 0.2f));
    }
}
