/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.pantallas;

/**
 *
 * @author Hp
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import Menus.Menu.ConfiguracionJuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextoHudNivelTest {
    @BeforeEach
    void ConfigurarIdiomaBase() {
        ConfiguracionJuego.idiomaActivo = "ESP";
    }

    @Test
    void CreaTextoDelHudSinPuntajeEstimado() {
        String TextoHud = TextoHudNivel.CrearTexto(3, 2, 18.6f);

        assertEquals("Nivel 3   Estrellas 2/3   Tiempo 19 s", TextoHud);
    }
}
