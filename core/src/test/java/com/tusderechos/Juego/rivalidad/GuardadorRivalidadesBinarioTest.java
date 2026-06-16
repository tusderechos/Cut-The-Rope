/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GuardadorRivalidadesBinarioTest {
    @TempDir
    Path CarpetaTemporal;

    @Test
    void CargaListaVaciaSiElArchivoNoExiste() {
        List<SolicitudRivalidad> Solicitudes = GuardadorRivalidadesBinario.Cargar(CarpetaTemporal.resolve("rivalidades.bin"));

        assertTrue(Solicitudes.isEmpty());
    }

    @Test
    void GuardaYCargaSolicitudesConEstadoYResultados() throws Exception {
        Path Archivo = CarpetaTemporal.resolve("datos").resolve("rivalidades.bin");
        SolicitudRivalidad Solicitud = new SolicitudRivalidad(new DatosReto(CategoriaDificultad.Media, 3, "santos", 3200, 2), "santos", "clara_09");
        int NumeroNivelReal = GestorRetos.ObtenerNivelReto(Solicitud.ObtenerReto()).ObtenerNumero();
        GestorRivalidades.AceptarSolicitud(Solicitud, "clara_09");
        GestorRivalidades.RegistrarResultado(Solicitud, "clara_09", new ResultadoNivel(NumeroNivelReal, 2, 3600, 5f));

        GuardadorRivalidadesBinario.Guardar(Archivo, Arrays.asList(Solicitud));
        List<SolicitudRivalidad> Cargadas = GuardadorRivalidadesBinario.Cargar(Archivo);

        assertTrue(Files.exists(Archivo));
        assertEquals(1, Cargadas.size());
        assertEquals(EstadoRivalidad.TurnoRetador, Cargadas.get(0).ObtenerEstado());
        assertEquals("clara_09", Cargadas.get(0).ObtenerResultadoRetado().ObtenerUsernameJugador());
        assertEquals(3600, Cargadas.get(0).ObtenerResultadoRetado().ObtenerPuntaje());
    }
}
