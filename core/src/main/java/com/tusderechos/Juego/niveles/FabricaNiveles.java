/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FabricaNiveles {
    
    private static final List<DatosNivel> Niveles = Collections.unmodifiableList(Arrays.asList(CrearNivelUno(), CrearNivelDos(), CrearNivelTres(), CrearNivelCuatro(), CrearNivelCinco()));

    private FabricaNiveles() {
    }

    public static List<DatosNivel> CrearNiveles() {
        return Niveles;
    }

    public static DatosNivel ObtenerNivel(int NumeroNivel) {
        if (NumeroNivel < 1 || NumeroNivel > CantidadNiveles()) {
            throw new IllegalArgumentException("El nivel solicitado no existe");
        }
        return Niveles.get(NumeroNivel - 1);
    }

    public static int CantidadNiveles() {
        return Niveles.size();
    }

    private static DatosNivel CrearNivelUno() {
        return Nivel(1, "Basico", DificultadNivel.SuperFacil, new Vector2(2.4f, 5.8f), new Vector2(2.4f, 1.1f), Collections.singletonList(new DatosCuerda(new Vector2(2.4f, 7.2f), 1.4f)), Estrellas(new Vector2(2.4f, 4.6f), new Vector2(2.18f, 3.25f), new Vector2(2.62f, 2.25f)), Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelDos() {
        return Nivel(2, "Balanceo", DificultadNivel.Facil, new Vector2(2.4f, 5.5f), new Vector2(2.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(1.4f, 7.1f), 1.887f), new DatosCuerda(new Vector2(3.4f, 7.1f), 1.887f)), Estrellas(new Vector2(3.5f, 4.8f), new Vector2(3.0f, 4.1f), new Vector2(2.4f, 2.4f)), Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelTres() {
        return Nivel(3, "Burbuja", DificultadNivel.Intermedio, new Vector2(1.4f, 5.8f), new Vector2(1.4f, 1.1f), Collections.singletonList(new DatosCuerda(new Vector2(1.15f, 7.15f), 1.373f)), Estrellas(new Vector2(1.4f, 3.15f), new Vector2(1.62f, 5.45f), new Vector2(1.4f, 6.35f)), Collections.singletonList(new DatosBurbuja(new Vector2(1.4f, 4.65f), 0.38f)), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelCuatro() {
        return Nivel(4, "Peligro", DificultadNivel.Dificil, new Vector2(1.2f, 5.8f), new Vector2(3.6f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.1f), 1.334f), new DatosCuerda(new Vector2(2.3f, 6.9f), 1.556f)), Estrellas(new Vector2(2.3f, 4.5f), new Vector2(2.75f, 3.6f), new Vector2(3.25f, 2.45f)), Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(1.92f, 2.36f), 0.50f, 0.32f)), false);
    }

    private static DatosNivel CrearNivelCinco() {
        return Nivel(5, "Plataforma", DificultadNivel.MuyDificil, new Vector2(2.4f, 5.9f), new Vector2(2.4f, 1.2f), Arrays.asList(new DatosCuerda(new Vector2(1.3f, 7.1f), 1.628f), new DatosCuerda(new Vector2(3.5f, 7.1f), 1.628f)), Estrellas(new Vector2(1.95f, 4.15f), new Vector2(2.85f, 4.15f), new Vector2(2.4f, 2.8f)), Collections.emptyList(), Collections.emptyList(), true);
    }

    private static List<DatosEstrella> Estrellas(Vector2 Primera, Vector2 Segunda, Vector2 Tercera) {
        return Arrays.asList(new DatosEstrella(Primera), new DatosEstrella(Segunda), new DatosEstrella(Tercera));
    }

    private static DatosNivel Nivel(int Numero, String Nombre, DificultadNivel Dificultad, Vector2 DulceActual, Vector2 MonstruoActual, List<DatosCuerda> Cuerdas, List<DatosEstrella> Estrellas, List<DatosBurbuja> Burbujas, List<DatosObstaculo> Obstaculos, boolean PlataformaMovilActual) {
        return new DatosNivel(Numero, Nombre, Dificultad, DulceActual, MonstruoActual, Cuerdas, Estrellas, Burbujas, Obstaculos, PlataformaMovilActual);
    }
}

