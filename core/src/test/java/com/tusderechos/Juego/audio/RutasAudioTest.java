/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.audio;

/**
 *
 * @author Hp
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class RutasAudioTest {
    @Test
    public void ObtenerRutasDebeIncluirTodosLosSonidosDelJuego() {
        List<String> Rutas = RutasAudio.ObtenerRutas();
        Set<String> RutasUnicas = new HashSet<>(Rutas);

        assertEquals(6, Rutas.size());
        assertEquals(Rutas.size(), RutasUnicas.size());
        assertTrue(Rutas.contains(RutasAudio.MusicaFondo));
        assertTrue(Rutas.contains(RutasAudio.CortarCuerda));
        assertTrue(Rutas.contains(RutasAudio.Estrella));
        assertTrue(Rutas.contains(RutasAudio.Burbuja));
        assertTrue(Rutas.contains(RutasAudio.Victoria));
        assertTrue(Rutas.contains(RutasAudio.Fallo));
    }

    @Test
    public void ArchivosDeAudioExistenYNoEstanVacios() throws Exception {
        for (String Ruta : RutasAudio.ObtenerRutas()) {
            Path Archivo = ResolverArchivoAsset(Ruta);

            assertTrue(Files.exists(Archivo), "No existe el audio " + Ruta);
            assertTrue(Files.size(Archivo) > 1024, "El audio esta vacio o corrupto " + Ruta);
        }
    }

    @Test
    public void ArchivosDeAudioTienenCabeceraWav() throws Exception {
        for (String Ruta : RutasAudio.ObtenerRutas()) {
            byte[] Cabecera = Files.readAllBytes(ResolverArchivoAsset(Ruta));

            assertEquals('R', Cabecera[0], Ruta);
            assertEquals('I', Cabecera[1], Ruta);
            assertEquals('F', Cabecera[2], Ruta);
            assertEquals('F', Cabecera[3], Ruta);
            assertEquals('W', Cabecera[8], Ruta);
            assertEquals('A', Cabecera[9], Ruta);
            assertEquals('V', Cabecera[10], Ruta);
            assertEquals('E', Cabecera[11], Ruta);
        }
    }

    private Path ResolverArchivoAsset(String Ruta) {
        Path RutaDesdeProyecto = Paths.get("assets").resolve(Ruta);
        if (Files.exists(RutaDesdeProyecto)) {
            return RutaDesdeProyecto;
        }

        return Paths.get("..").resolve("assets").resolve(Ruta);
    }
}
