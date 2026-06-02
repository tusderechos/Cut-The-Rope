package com.tusderechos.Juego.niveles;

import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.enums.DificultadNivel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FabricaNiveles {
    private FabricaNiveles() {
    }

    public static List<DatosNivel> crearNiveles() {
        return Arrays.asList(crearNivelUno(), crearNivelDos(), crearNivelTres(), crearNivelCuatro(), crearNivelCinco());
    }

    public static DatosNivel obtenerNivel(int numeroNivel) {
        if (numeroNivel < 1 || numeroNivel > 5) {
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 5");
        }
        return crearNiveles().get(numeroNivel - 1);
    }

    private static DatosNivel crearNivelUno() {
        return nivel(1, "Basico", DificultadNivel.SUPER_FACIL, new Vector2(2.4f, 5.8f), new Vector2(2.4f, 1.1f),
            Collections.singletonList(new DatosCuerda(new Vector2(2.4f, 7.2f), 1.4f)),
            estrellas(new Vector2(2.4f, 4.6f), new Vector2(2.1f, 3.2f), new Vector2(2.7f, 2.2f)),
            Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelDos() {
        return nivel(2, "Balanceo", DificultadNivel.FACIL, new Vector2(2.4f, 5.5f), new Vector2(2.4f, 1.1f),
            Arrays.asList(new DatosCuerda(new Vector2(1.4f, 7.1f), 1.9f), new DatosCuerda(new Vector2(3.4f, 7.1f), 1.9f)),
            estrellas(new Vector2(1.8f, 4.8f), new Vector2(3.0f, 4.1f), new Vector2(2.4f, 2.4f)),
            Collections.emptyList(), Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelTres() {
        return nivel(3, "Burbuja", DificultadNivel.INTERMEDIO, new Vector2(1.4f, 3.2f), new Vector2(3.5f, 1.2f),
            Collections.singletonList(new DatosCuerda(new Vector2(1.2f, 5.2f), 1.9f)),
            estrellas(new Vector2(1.6f, 4.5f), new Vector2(2.8f, 5.5f), new Vector2(3.4f, 2.4f)),
            Collections.singletonList(new DatosBurbuja(new Vector2(1.4f, 3.2f), 0.34f)), Collections.emptyList(), false);
    }

    private static DatosNivel crearNivelCuatro() {
        return nivel(4, "Peligro", DificultadNivel.DIFICIL, new Vector2(1.2f, 5.8f), new Vector2(3.6f, 1.1f),
            Arrays.asList(new DatosCuerda(new Vector2(0.9f, 7.1f), 1.4f), new DatosCuerda(new Vector2(2.3f, 6.9f), 1.8f)),
            estrellas(new Vector2(1.4f, 4.5f), new Vector2(2.6f, 3.6f), new Vector2(3.5f, 2.2f)),
            Collections.emptyList(), Collections.singletonList(new DatosObstaculo(new Vector2(2.45f, 2.5f), 0.55f, 0.35f)), false);
    }

    private static DatosNivel crearNivelCinco() {
        return nivel(5, "Plataforma", DificultadNivel.MUY_DIFICIL, new Vector2(2.4f, 5.9f), new Vector2(2.4f, 1.2f),
            Arrays.asList(new DatosCuerda(new Vector2(1.3f, 7.1f), 1.7f), new DatosCuerda(new Vector2(3.5f, 7.1f), 1.7f)),
            estrellas(new Vector2(1.3f, 4.5f), new Vector2(3.5f, 4.5f), new Vector2(2.4f, 2.6f)),
            Collections.emptyList(), Collections.emptyList(), true);
    }

    private static List<DatosEstrella> estrellas(Vector2 primera, Vector2 segunda, Vector2 tercera) {
        return Arrays.asList(new DatosEstrella(primera), new DatosEstrella(segunda), new DatosEstrella(tercera));
    }

    private static DatosNivel nivel(int numero, String nombre, DificultadNivel dificultad, Vector2 dulce, Vector2 monstruo,
                                    List<DatosCuerda> cuerdas, List<DatosEstrella> estrellas, List<DatosBurbuja> burbujas,
                                    List<DatosObstaculo> obstaculos, boolean plataformaMovil) {
        return new DatosNivel(numero, nombre, dificultad, dulce, monstruo, cuerdas, estrellas, burbujas, obstaculos, plataformaMovil);
    }
}
