/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.ColorMonstruo;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntidadesInteractivasTest {
    @Test
    void BurbujaPermaneceLibreHastaQueElDulceLaToca() {
        Burbuja BurbujaActual = new Burbuja(new Vector2(1f, 1f), 0.5f);

        assertFalse(BurbujaActual.EstaAdherida());
        BurbujaActual.SeguirDulce(new Vector2(3f, 4f));
        assertTrue(BurbujaActual.ContienePunto(new Vector2(1f, 1f)));
        assertFalse(BurbujaActual.ContienePunto(new Vector2(3f, 4f)));
        BurbujaActual.Reventar();
        assertTrue(BurbujaActual.EstaActiva());

        assertTrue(BurbujaActual.IntentarAdherir(new Vector2(1.3f, 1f)));
        assertTrue(BurbujaActual.EstaAdherida());
        BurbujaActual.SeguirDulce(new Vector2(3f, 4f));
        assertTrue(BurbujaActual.ContienePunto(new Vector2(3f, 4f)));

        BurbujaActual.Reventar();
        assertFalse(BurbujaActual.ContienePunto(new Vector2(3f, 4f)));
    }

    @Test
    void EstrellaSoloSeRecolectaUnaVez() {
        Estrella EstrellaActual = new Estrella(new Vector2(2f, 2f));

        assertTrue(EstrellaActual.IntentarRecolectar(new Vector2(2f, 2f)));
        assertFalse(EstrellaActual.IntentarRecolectar(new Vector2(2f, 2f)));
    }

    @Test
    void MonstruoAceptaDulceEnBordeDeContacto() {
        Monstruo MonstruoActual = new Monstruo(new Vector2(1f, 1f), new PersonalizacionMonstruo(ColorMonstruo.Verde));

        assertTrue(MonstruoActual.ContieneDulce(new Vector2(1.56f, 1f)));
        assertFalse(MonstruoActual.ContieneDulce(new Vector2(1.57f, 1f)));
    }

    @Test
    void MonstruoEntregaCopiaDeSuPosicionActual() {
        Monstruo MonstruoActual = new Monstruo(new Vector2(1f, 1f), new PersonalizacionMonstruo(ColorMonstruo.Verde));

        MonstruoActual.EstablecerPosicion(new Vector2(2f, 3f));
        Vector2 PosicionObtenida = MonstruoActual.ObtenerPosicion();
        PosicionObtenida.set(9f, 9f);

        assertEquals(new Vector2(2f, 3f), MonstruoActual.ObtenerPosicion());
    }
}

