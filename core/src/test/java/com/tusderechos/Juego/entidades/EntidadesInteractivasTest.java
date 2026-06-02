package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntidadesInteractivasTest {
    @Test
    void burbujaSigueAlDulceHastaQueRevienta() {
        Burbuja burbuja = new Burbuja(new Vector2(1f, 1f), 0.5f);

        burbuja.seguirDulce(new Vector2(3f, 4f));
        assertTrue(burbuja.contienePunto(new Vector2(3f, 4f)));

        burbuja.reventar();
        assertFalse(burbuja.contienePunto(new Vector2(3f, 4f)));
    }

    @Test
    void estrellaSoloSeRecolectaUnaVez() {
        Estrella estrella = new Estrella(new Vector2(2f, 2f));

        assertTrue(estrella.intentarRecolectar(new Vector2(2f, 2f)));
        assertFalse(estrella.intentarRecolectar(new Vector2(2f, 2f)));
    }

    @Test
    void monstruoAceptaDulceEnBordeDeContacto() {
        Monstruo monstruo = new Monstruo(new Vector2(1f, 1f),
            new PersonalizacionMonstruo(ColorMonstruo.VERDE));

        assertTrue(monstruo.contieneDulce(new Vector2(1.56f, 1f)));
        assertFalse(monstruo.contieneDulce(new Vector2(1.57f, 1f)));
    }
}
