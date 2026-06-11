/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.niveles;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.utilidades.ConstantesJuego;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracionNivelesTest {
    @Test
    void TodosLosNivelesTienenTresEstrellasYPosicionesVisibles() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            assertEquals(3, Nivel.ObtenerEstrellas().size(), Nivel.ObtenerNombre());
            AssertDentroDelMundo(Nivel.ObtenerPosicionDulce().x, Nivel.ObtenerPosicionDulce().y);
            AssertDentroDelMundo(Nivel.ObtenerPosicionMonstruo().x, Nivel.ObtenerPosicionMonstruo().y);
            Nivel.ObtenerEstrellas().forEach(EstrellaActual -> AssertDentroDelMundo(EstrellaActual.ObtenerPosicion().x, EstrellaActual.ObtenerPosicion().y));
            Nivel.ObtenerCuerdas().forEach(CuerdaActual -> {
                AssertDentroDelMundo(CuerdaActual.ObtenerAncla().x, CuerdaActual.ObtenerAncla().y);
                assertEquals(Nivel.ObtenerPosicionDulce().dst(CuerdaActual.ObtenerAncla()), CuerdaActual.ObtenerLongitud(), 0.005f);
            });
            Nivel.ObtenerBurbujas().forEach(BurbujaActual -> AssertDentroDelMundo(BurbujaActual.ObtenerPosicion().x, BurbujaActual.ObtenerPosicion().y));
        }
    }

    @Test
    void NivelTresTieneUnaBurbujaLibreSeparadaDelDulce() {
        DatosNivel NivelTres = FabricaNiveles.ObtenerNivel(3);

        assertEquals(1, NivelTres.ObtenerBurbujas().size());
        DatosBurbuja BurbujaActual = NivelTres.ObtenerBurbujas().get(0);
        assertTrue(NivelTres.ObtenerPosicionDulce().dst(BurbujaActual.ObtenerPosicion()) > BurbujaActual.ObtenerRadio() + ConstantesJuego.RadioDulce);
    }

    @Test
    void NivelUnoPermiteRecolectarTresEstrellasEnLaCaidaTutorial() {
        DatosNivel NivelUno = FabricaNiveles.ObtenerNivel(1);
        float MargenRecolectable = ConstantesJuego.RadioDulce + ConstantesJuego.RadioEstrella;

        NivelUno.ObtenerEstrellas().forEach(EstrellaActual -> assertTrue(Math.abs(EstrellaActual.ObtenerPosicion().x - NivelUno.ObtenerPosicionDulce().x) <= MargenRecolectable, "Nivel 1 estrella fuera de la caida tutorial"));
    }

    @Test
    void NivelTresUsaBurbujaParaSubirHaciaEstrellasSuperiores() {
        DatosNivel NivelTres = FabricaNiveles.ObtenerNivel(3);
        DatosBurbuja BurbujaActual = NivelTres.ObtenerBurbujas().get(0);

        assertTrue(Math.abs(BurbujaActual.ObtenerPosicion().x - NivelTres.ObtenerPosicionDulce().x) <= 0.1f);
        assertTrue(BurbujaActual.ObtenerPosicion().y < NivelTres.ObtenerPosicionDulce().y);
        assertTrue(NivelTres.ObtenerEstrellas().stream().anyMatch(EstrellaActual -> EstrellaActual.ObtenerPosicion().y > BurbujaActual.ObtenerPosicion().y + 0.6f));
    }

    @Test
    void NivelCincoMantieneEstrellasEnRutaCompactaAlcanzable() {
        DatosNivel NivelCinco = FabricaNiveles.ObtenerNivel(5);

        NivelCinco.ObtenerEstrellas().forEach(EstrellaActual -> assertTrue(EstrellaActual.ObtenerPosicion().x >= 1.8f && EstrellaActual.ObtenerPosicion().x <= 3.0f, "Nivel 5 estrella demasiado lateral"));
        NivelCinco.ObtenerEstrellas().forEach(EstrellaActual -> assertTrue(EstrellaActual.ObtenerPosicion().y >= 2.5f && EstrellaActual.ObtenerPosicion().y <= 4.7f, "Nivel 5 estrella fuera de la ruta vertical jugable"));
        assertTrue(NivelCinco.ObtenerEstrellas().get(0).ObtenerPosicion().dst(NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion()) <= 1.2f);
        assertTrue(NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion().dst(NivelCinco.ObtenerEstrellas().get(2).ObtenerPosicion()) <= 1.2f);
        assertTrue(Math.abs(NivelCinco.ObtenerEstrellas().get(2).ObtenerPosicion().x - NivelCinco.ObtenerPosicionMonstruo().x) <= 0.75f);
    }

    @Test
    void NivelCincoUsaRutaDeTresEstrellasDescendente() {
        DatosNivel NivelCinco = FabricaNiveles.ObtenerNivel(5);

        assertTrue(NivelCinco.ObtenerEstrellas().get(0).ObtenerPosicion().x < NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion().x);
        assertTrue(NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion().x < NivelCinco.ObtenerEstrellas().get(2).ObtenerPosicion().x);
        assertTrue(NivelCinco.ObtenerEstrellas().get(0).ObtenerPosicion().y > NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion().y);
        assertTrue(NivelCinco.ObtenerEstrellas().get(1).ObtenerPosicion().y > NivelCinco.ObtenerEstrellas().get(2).ObtenerPosicion().y);
    }

    @Test
    void NingunNivelRecolectaEstrellasAlIniciar() {
        float DistanciaMinima = ConstantesJuego.RadioDulce + ConstantesJuego.RadioEstrella;

        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            Nivel.ObtenerEstrellas().forEach(EstrellaActual -> assertTrue(Nivel.ObtenerPosicionDulce().dst(EstrellaActual.ObtenerPosicion()) > DistanciaMinima, Nivel.ObtenerNombre()));
        }
    }

    @Test
    void NivelesMediosYDificilesTienenRutaDeEstrellasCompacta() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            if (Nivel.ObtenerCategoria() == CategoriaDificultad.Facil) {
                continue;
            }
            for (int Indice = 0; Indice < Nivel.ObtenerEstrellas().size() - 1; Indice++) {
                float Distancia = Nivel.ObtenerEstrellas().get(Indice).ObtenerPosicion().dst(Nivel.ObtenerEstrellas().get(Indice + 1).ObtenerPosicion());
                assertTrue(Distancia <= 1.45f, Nivel.ObtenerNombre() + " tiene una ruta de estrellas demasiado separada");
            }
        }
    }

    @Test
    void NivelesMediosYDificilesTerminanRutaCercaDelMonstruo() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            if (Nivel.ObtenerCategoria() == CategoriaDificultad.Facil) {
                continue;
            }
            DatosEstrella UltimaEstrella = Nivel.ObtenerEstrellas().get(Nivel.ObtenerEstrellas().size() - 1);
            float DistanciaHorizontal = Math.abs(UltimaEstrella.ObtenerPosicion().x - Nivel.ObtenerPosicionMonstruo().x);
            float AlturaSobreMonstruo = UltimaEstrella.ObtenerPosicion().y - Nivel.ObtenerPosicionMonstruo().y;

            assertTrue(DistanciaHorizontal <= 0.85f, Nivel.ObtenerNombre() + " termina muy lejos del monstruo");
            assertTrue(AlturaSobreMonstruo >= 0.85f && AlturaSobreMonstruo <= 2.0f, Nivel.ObtenerNombre() + " termina a una altura incomoda para alimentar al monstruo");
        }
    }

    @Test
    void NivelesMediosYDificilesNoPonenEstrellasPegadasAObstaculos() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            if (Nivel.ObtenerCategoria() == CategoriaDificultad.Facil) {
                continue;
            }
            Nivel.ObtenerEstrellas().forEach(EstrellaActual -> Nivel.ObtenerObstaculos().forEach(ObstaculoActual -> {
                float Separacion = CalcularSeparacionEstrellaObstaculo(EstrellaActual, ObstaculoActual);
                assertTrue(Separacion >= 0.36f, Nivel.ObtenerNombre() + " tiene una estrella injustamente pegada a un obstaculo");
            }));
        }
    }

    @Test
    void NivelesMediosYDificilesConBurbujaTienenEntradaYSalidaJugable() {
        for (DatosNivel Nivel : FabricaNiveles.CrearNiveles()) {
            if (Nivel.ObtenerCategoria() == CategoriaDificultad.Facil || Nivel.ObtenerBurbujas().isEmpty()) {
                continue;
            }
            DatosBurbuja BurbujaActual = Nivel.ObtenerBurbujas().get(0);

            assertTrue(Nivel.ObtenerPosicionDulce().dst(BurbujaActual.ObtenerPosicion()) > BurbujaActual.ObtenerRadio() + ConstantesJuego.RadioDulce, Nivel.ObtenerNombre() + " empieza demasiado cerca de la burbuja");
            assertTrue(Nivel.ObtenerEstrellas().stream().anyMatch(EstrellaActual -> EstrellaActual.ObtenerPosicion().dst(BurbujaActual.ObtenerPosicion()) <= 0.95f), Nivel.ObtenerNombre() + " no recompensa tocar la burbuja");
            assertTrue(Nivel.ObtenerEstrellas().stream().anyMatch(EstrellaActual -> EstrellaActual.ObtenerPosicion().y < BurbujaActual.ObtenerPosicion().y - 1.2f), Nivel.ObtenerNombre() + " no deja salida descendente despues de reventar la burbuja");
        }
    }

    @Test
    void NivelesReportadosMantienenRutaTresEstrellasSinObstaculoEnMedio() {
        DatosNivel[] NivelesReportados = {
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 3),
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 3)
        };

        for (DatosNivel Nivel : NivelesReportados) {
            Nivel.ObtenerObstaculos().forEach(ObstaculoActual -> {
                for (int Indice = 0; Indice < Nivel.ObtenerEstrellas().size() - 1; Indice++) {
                    float Distancia = CalcularDistanciaObstaculoARuta(Nivel.ObtenerEstrellas().get(Indice), Nivel.ObtenerEstrellas().get(Indice + 1), ObstaculoActual);
                    assertTrue(Distancia >= 0.35f, Nivel.ObtenerNombre() + " bloquea la ruta de tres estrellas");
                }
            });
        }
    }

    @Test
    void NivelesReportadosTienenEnergiaInicialSuficienteOSonVerticales() {
        DatosNivel[] NivelesReportados = {
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 2),
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 5),
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 1),
            FabricaNiveles.ObtenerNivel(CategoriaDificultad.Dificil, 3)
        };

        for (DatosNivel Nivel : NivelesReportados) {
            boolean RutaVertical = Math.abs(Nivel.ObtenerEstrellas().get(0).ObtenerPosicion().x - Nivel.ObtenerPosicionDulce().x) <= 0.45f
                && Math.abs(Nivel.ObtenerEstrellas().get(2).ObtenerPosicion().x - Nivel.ObtenerPosicionMonstruo().x) <= 0.45f;
            boolean PenduloConImpulso = Nivel.ObtenerCuerdas().stream().anyMatch(CuerdaActual -> Math.abs(CuerdaActual.ObtenerAncla().x - Nivel.ObtenerPosicionDulce().x) >= 0.75f);

            assertTrue(RutaVertical || PenduloConImpulso, Nivel.ObtenerNombre() + " no tiene caida vertical ni pendulo con impulso suficiente");
        }
    }

    @Test
    void NivelCincoIncluyePlataformaMovil() {
        assertTrue(FabricaNiveles.ObtenerNivel(5).TienePlataformaMovil());
    }

    private float CalcularSeparacionEstrellaObstaculo(DatosEstrella EstrellaActual, DatosObstaculo ObstaculoActual) {
        float MitadAncho = ObstaculoActual.ObtenerAncho() / 2f;
        float MitadAlto = ObstaculoActual.ObtenerAlto() / 2f;
        float DistanciaX = Math.max(Math.abs(EstrellaActual.ObtenerPosicion().x - ObstaculoActual.ObtenerPosicion().x) - MitadAncho, 0f);
        float DistanciaY = Math.max(Math.abs(EstrellaActual.ObtenerPosicion().y - ObstaculoActual.ObtenerPosicion().y) - MitadAlto, 0f);

        return (float) Math.sqrt(DistanciaX * DistanciaX + DistanciaY * DistanciaY);
    }

    private float CalcularDistanciaObstaculoARuta(DatosEstrella PrimeraEstrella, DatosEstrella SegundaEstrella, DatosObstaculo ObstaculoActual) {
        float DistanciaMinima = Float.MAX_VALUE;
        for (float Progreso = 0f; Progreso <= 1.0f; Progreso += 0.1f) {
            float PuntoX = PrimeraEstrella.ObtenerPosicion().x + (SegundaEstrella.ObtenerPosicion().x - PrimeraEstrella.ObtenerPosicion().x) * Progreso;
            float PuntoY = PrimeraEstrella.ObtenerPosicion().y + (SegundaEstrella.ObtenerPosicion().y - PrimeraEstrella.ObtenerPosicion().y) * Progreso;
            DatosEstrella PuntoRuta = new DatosEstrella(new com.badlogic.gdx.math.Vector2(PuntoX, PuntoY));
            DistanciaMinima = Math.min(DistanciaMinima, CalcularSeparacionEstrellaObstaculo(PuntoRuta, ObstaculoActual));
        }

        return DistanciaMinima;
    }

    private void AssertDentroDelMundo(float X, float Y) {
        assertTrue(X >= 0f && X <= ConstantesJuego.AnchoMundo);
        assertTrue(Y >= 0f && Y <= ConstantesJuego.AltoMundo);
    }
}

