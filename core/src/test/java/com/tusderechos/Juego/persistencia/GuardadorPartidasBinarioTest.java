/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.persistencia;

/**
 *
 * @author Hp
 */

import com.tusderechos.Juego.enums.CategoriaDificultad;
import com.tusderechos.Juego.niveles.DatosNivel;
import com.tusderechos.Juego.niveles.FabricaNiveles;
import com.tusderechos.Juego.niveles.ResultadoNivel;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GuardadorPartidasBinarioTest {
    @TempDir
    Path CarpetaTemporal;

    @Test
    void GuardaYLeeRegistroEnArchivoBinario() throws Exception {
        Path Archivo = CarpetaTemporal.resolve("partidas.bin");
        DatosNivel Nivel = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Facil, 2);
        ResultadoNivel Resultado = new ResultadoNivel(Nivel.ObtenerNumero(), 3, 4200, 5f);
        RegistroPartida Registro = RegistroPartida.CrearDesdeResultado(Nivel, Resultado, 1, null);

        GuardadorPartidasBinario.Guardar(Archivo, Registro);
        List<RegistroPartida> Registros = GuardadorPartidasBinario.Leer(Archivo);

        assertEquals(1, Registros.size());
        assertEquals(CategoriaDificultad.Facil, Registros.get(0).ObtenerCategoria());
        assertEquals(2, Registros.get(0).ObtenerNumeroEnCategoria());
        assertEquals(3, Registros.get(0).ObtenerEstrellas());
        assertEquals(4200, Registros.get(0).ObtenerPuntaje());
        assertEquals(1, Registros.get(0).ObtenerFallos());
    }

    @Test
    void GuardaRegistroDesdeHiloSinBloquearLaLlamada() throws Exception {
        Path Archivo = CarpetaTemporal.resolve("hilo").resolve("partidas.bin");
        DatosNivel Nivel = FabricaNiveles.ObtenerNivel(CategoriaDificultad.Media, 1);
        ResultadoNivel Resultado = new ResultadoNivel(Nivel.ObtenerNumero(), 2, 3600, 7f);
        RegistroPartida Registro = RegistroPartida.CrearDesdeResultado(Nivel, Resultado, 0, null);

        Thread HiloGuardado = GuardadorPartidasBinario.GuardarEnHilo(Archivo, Registro);
        HiloGuardado.join(3000);

        assertTrue(Archivo.toFile().exists());
        assertEquals(1, GuardadorPartidasBinario.Leer(Archivo).size());
    }
}
