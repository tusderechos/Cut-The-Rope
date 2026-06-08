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
import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FabricaNiveles {
    
    private static final List<DatosNivel> Niveles = Collections.unmodifiableList(Arrays.asList(CrearNivelUno(), CrearNivelDos(), CrearNivelTres(), CrearNivelCuatro(), CrearNivelCinco(), CrearNivelMedioUno(), CrearNivelMedioDos(), CrearNivelMedioTres(), CrearNivelMedioCuatro(), CrearNivelMedioCinco(), CrearNivelDificilUno(), CrearNivelDificilDos(), CrearNivelDificilTres(), CrearNivelDificilCuatro(), CrearNivelDificilCinco()));

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

    public static List<DatosNivel> CrearNivelesPorCategoria(CategoriaDificultad Categoria) {
        if (Categoria == null) {
            throw new IllegalArgumentException("La categoria solicitada no existe");
        }
        List<DatosNivel> NivelesCategoria = new ArrayList<>();
        for (DatosNivel NivelActual : Niveles) {
            if (NivelActual.ObtenerCategoria() == Categoria) {
                NivelesCategoria.add(NivelActual);
            }
        }
        return Collections.unmodifiableList(NivelesCategoria);
    }

    public static DatosNivel ObtenerNivel(CategoriaDificultad Categoria, int NumeroEnCategoria) {
        for (DatosNivel NivelActual : CrearNivelesPorCategoria(Categoria)) {
            if (NivelActual.ObtenerNumeroEnCategoria() == NumeroEnCategoria) {
                return NivelActual;
            }
        }
        throw new IllegalArgumentException("El nivel solicitado no existe en esa categoria");
    }

    public static int CantidadNiveles(CategoriaDificultad Categoria) {
        return CrearNivelesPorCategoria(Categoria).size();
    }

    private static DatosNivel CrearNivelUno() {
        return Nivel(1, CategoriaDificultad.Facil, 1, "Basico", DificultadNivel.SuperFacil, new Vector2(2.4f, 5.8f), new Vector2(2.4f, 1.1f), Collections.singletonList(new DatosCuerda(new Vector2(2.4f, 7.2f), 1.4f)), Estrellas(new Vector2(2.4f, 4.6f), new Vector2(2.18f, 3.25f), new Vector2(2.62f, 2.25f)), Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelDos() {
        return Nivel(2, CategoriaDificultad.Facil, 2, "Balanceo", DificultadNivel.Facil, new Vector2(2.4f, 5.5f), new Vector2(2.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(1.4f, 7.1f), 1.887f), new DatosCuerda(new Vector2(3.4f, 7.1f), 1.887f)), Estrellas(new Vector2(3.5f, 4.8f), new Vector2(3.0f, 4.1f), new Vector2(2.4f, 2.4f)), Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelTres() {
        return Nivel(3, CategoriaDificultad.Facil, 3, "Burbuja", DificultadNivel.Intermedio, new Vector2(1.4f, 5.8f), new Vector2(1.4f, 1.1f), Collections.singletonList(new DatosCuerda(new Vector2(1.15f, 7.15f), 1.373f)), Estrellas(new Vector2(1.4f, 3.15f), new Vector2(1.62f, 5.45f), new Vector2(1.4f, 6.35f)), Collections.singletonList(new DatosBurbuja(new Vector2(1.4f, 4.65f), 0.38f)), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelCuatro() {
        return Nivel(4, CategoriaDificultad.Facil, 4, "Peligro", DificultadNivel.Dificil, new Vector2(1.2f, 5.8f), new Vector2(3.6f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.1f), 1.334f), new DatosCuerda(new Vector2(2.3f, 6.9f), 1.556f)), Estrellas(new Vector2(2.3f, 4.5f), new Vector2(2.75f, 3.6f), new Vector2(3.25f, 2.45f)), Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(1.92f, 2.36f), 0.50f, 0.32f)), false);
    }

    private static DatosNivel CrearNivelCinco() {
        return Nivel(5, CategoriaDificultad.Facil, 5, "Plataforma", DificultadNivel.MuyDificil, new Vector2(2.4f, 5.9f), new Vector2(2.4f, 1.2f), Arrays.asList(new DatosCuerda(new Vector2(1.3f, 7.1f), 1.628f), new DatosCuerda(new Vector2(3.5f, 7.1f), 1.628f)), Estrellas(new Vector2(1.86f, 4.55f), new Vector2(2.34f, 3.65f), new Vector2(2.92f, 2.72f)), Collections.emptyList(), Collections.emptyList(), true);
    }

    private static DatosNivel CrearNivelMedioUno() {
        return Nivel(6, CategoriaDificultad.Media, 1, "Doble Balanceo", DificultadNivel.Intermedio, new Vector2(1.5f, 5.8f), new Vector2(3.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.8f, 7.1f), 1.476f), new DatosCuerda(new Vector2(2.5f, 7.0f), 1.562f)), Estrellas(new Vector2(2.35f, 4.8f), new Vector2(2.95f, 3.55f), new Vector2(3.38f, 2.35f)), Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelMedioDos() {
        return Nivel(7, CategoriaDificultad.Media, 2, "Burbuja Cruzada", DificultadNivel.Intermedio, new Vector2(2.4f, 5.8f), new Vector2(2.4f, 1.1f), Collections.singletonList(new DatosCuerda(new Vector2(1.55f, 7.1f), 1.553f)), Estrellas(new Vector2(2.15f, 4.75f), new Vector2(2.55f, 3.55f), new Vector2(2.40f, 2.55f)), Collections.singletonList(new DatosBurbuja(new Vector2(2.4f, 4.35f), 0.38f)), Collections.emptyList(), false);
    }

    private static DatosNivel CrearNivelMedioTres() {
        return Nivel(8, CategoriaDificultad.Media, 3, "Peligro Guiado", DificultadNivel.Dificil, new Vector2(1.2f, 5.8f), new Vector2(3.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.1f), 1.334f), new DatosCuerda(new Vector2(2.5f, 6.95f), 1.736f)), Estrellas(new Vector2(1.95f, 4.65f), new Vector2(2.75f, 3.55f), new Vector2(3.35f, 2.35f)), Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(0.95f, 2.70f), 0.55f, 0.32f)), false);
    }

    private static DatosNivel CrearNivelMedioCuatro() {
        return Nivel(9, CategoriaDificultad.Media, 4, "Plataforma Lateral", DificultadNivel.Dificil, new Vector2(3.2f, 5.8f), new Vector2(2.4f, 1.2f), Arrays.asList(new DatosCuerda(new Vector2(2.1f, 7.1f), 1.703f), new DatosCuerda(new Vector2(4.1f, 7.0f), 1.5f)), Estrellas(new Vector2(3.55f, 4.75f), new Vector2(2.75f, 3.65f), new Vector2(2.35f, 2.70f)), Collections.emptyList(), Collections.emptyList(), true);
    }

    private static DatosNivel CrearNivelMedioCinco() {
        return Nivel(10, CategoriaDificultad.Media, 5, "Burbuja Peligrosa", DificultadNivel.MuyDificil, new Vector2(2.4f, 6.0f), new Vector2(2.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(1.4f, 7.2f), 1.562f), new DatosCuerda(new Vector2(3.4f, 7.2f), 1.562f)), Estrellas(new Vector2(2.40f, 4.75f), new Vector2(2.50f, 3.45f), new Vector2(2.40f, 2.45f)), Collections.singletonList(new DatosBurbuja(new Vector2(2.4f, 4.30f), 0.38f)), Collections.singletonList(new DatosObstaculo(new Vector2(3.35f, 2.80f), 0.45f, 0.30f)), false);
    }

    private static DatosNivel CrearNivelDificilUno() {
        return Nivel(11, CategoriaDificultad.Dificil, 1, "Doble Corte", DificultadNivel.Dificil, new Vector2(2.4f, 6.1f), new Vector2(2.4f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.35f), 1.953f), new DatosCuerda(new Vector2(3.9f, 7.35f), 1.953f)), Estrellas(new Vector2(2.00f, 4.65f), new Vector2(2.40f, 3.45f), new Vector2(2.40f, 2.35f)), Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(3.20f, 2.95f), 0.52f, 0.32f)), false);
    }

    private static DatosNivel CrearNivelDificilDos() {
        return Nivel(12, CategoriaDificultad.Dificil, 2, "Burbuja Alta", DificultadNivel.MuyDificil, new Vector2(1.1f, 6.0f), new Vector2(3.35f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.65f, 7.25f), 1.329f), new DatosCuerda(new Vector2(2.3f, 7.1f), 1.628f)), Estrellas(new Vector2(1.65f, 4.85f), new Vector2(2.25f, 3.75f), new Vector2(3.00f, 2.60f)), Collections.singletonList(new DatosBurbuja(new Vector2(2.35f, 4.75f), 0.38f)), Collections.singletonList(new DatosObstaculo(new Vector2(1.20f, 2.45f), 0.52f, 0.30f)), false);
    }

    private static DatosNivel CrearNivelDificilTres() {
        return Nivel(13, CategoriaDificultad.Dificil, 3, "Cruce Peligroso", DificultadNivel.MuyDificil, new Vector2(3.0f, 6.0f), new Vector2(1.75f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(2.0f, 7.2f), 1.562f), new DatosCuerda(new Vector2(4.0f, 7.1f), 1.487f)), Estrellas(new Vector2(2.90f, 4.75f), new Vector2(2.35f, 3.65f), new Vector2(1.80f, 2.50f)), Collections.emptyList(), Arrays.asList(new DatosObstaculo(new Vector2(3.55f, 2.85f), 0.45f, 0.32f), new DatosObstaculo(new Vector2(0.95f, 2.45f), 0.45f, 0.32f)), false);
    }

    private static DatosNivel CrearNivelDificilCuatro() {
        return Nivel(14, CategoriaDificultad.Dificil, 4, "Plataforma Extrema", DificultadNivel.MuyDificil, new Vector2(2.4f, 6.15f), new Vector2(2.4f, 1.2f), Arrays.asList(new DatosCuerda(new Vector2(1.0f, 7.35f), 1.844f), new DatosCuerda(new Vector2(3.8f, 7.35f), 1.844f)), Estrellas(new Vector2(1.75f, 4.65f), new Vector2(2.35f, 3.55f), new Vector2(2.40f, 2.45f)), Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(1.15f, 2.75f), 0.45f, 0.30f)), true);
    }

    private static DatosNivel CrearNivelDificilCinco() {
        return Nivel(15, CategoriaDificultad.Dificil, 5, "Reto Final", DificultadNivel.MuyDificil, new Vector2(1.15f, 6.0f), new Vector2(3.45f, 1.1f), Arrays.asList(new DatosCuerda(new Vector2(0.75f, 7.25f), 1.312f), new DatosCuerda(new Vector2(2.55f, 7.2f), 1.844f)), Estrellas(new Vector2(1.85f, 4.80f), new Vector2(2.60f, 3.75f), new Vector2(3.35f, 2.55f)), Collections.singletonList(new DatosBurbuja(new Vector2(2.75f, 4.75f), 0.38f)), Arrays.asList(new DatosObstaculo(new Vector2(1.40f, 2.75f), 0.50f, 0.30f), new DatosObstaculo(new Vector2(2.45f, 2.35f), 0.45f, 0.30f)), true);
    }

    private static List<DatosEstrella> Estrellas(Vector2 Primera, Vector2 Segunda, Vector2 Tercera) {
        return Arrays.asList(new DatosEstrella(Primera), new DatosEstrella(Segunda), new DatosEstrella(Tercera));
    }

    private static DatosNivel Nivel(int Numero, CategoriaDificultad Categoria, int NumeroEnCategoria, String Nombre, DificultadNivel Dificultad, Vector2 DulceActual, Vector2 MonstruoActual, List<DatosCuerda> Cuerdas, List<DatosEstrella> Estrellas, List<DatosBurbuja> Burbujas, List<DatosObstaculo> Obstaculos, boolean PlataformaMovilActual) {
        return new DatosNivel(Numero, Categoria, NumeroEnCategoria, Nombre, Dificultad, DulceActual, MonstruoActual, Cuerdas, Estrellas, Burbujas, Obstaculos, PlataformaMovilActual);
    }
}

