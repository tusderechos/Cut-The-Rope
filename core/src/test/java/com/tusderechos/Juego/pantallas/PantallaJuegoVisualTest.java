package com.tusderechos.Juego.pantallas;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.utilidades.ConstantesJuego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PantallaJuegoVisualTest {
    @Test
    void PlataformaVisualSeDibujaDebajoDelMonstruo() {
        Vector2 PosicionMonstruo = new Vector2(2.4f, 1.1f);

        Rectangle Plataforma = PantallaJuego.CrearRectanguloPlataformaVisual(PosicionMonstruo);

        assertEquals(PosicionMonstruo.x, Plataforma.x + Plataforma.width / 2f, 0.001f);
        assertTrue(Plataforma.y + Plataforma.height <= PosicionMonstruo.y - ConstantesJuego.RadioMonstruo + 0.001f);
        assertEquals(PantallaJuego.ObtenerAnchoPlataformaVisual(), Plataforma.width, 0.001f);
    }
}
