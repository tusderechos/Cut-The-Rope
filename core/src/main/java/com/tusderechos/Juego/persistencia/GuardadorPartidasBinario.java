/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.persistencia;

/**
 *
 * @author Hp
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GuardadorPartidasBinario {
    private GuardadorPartidasBinario() {
    }

    public static Thread GuardarEnHilo(Path RutaArchivo, RegistroPartida Registro) {
        if (RutaArchivo == null || Registro == null) {
            throw new IllegalArgumentException("La ruta y el registro son obligatorios");
        }
        Thread HiloGuardado = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Guardar(RutaArchivo, Registro);
                } catch (IOException Excepcion) {
                    System.err.println("No se pudo guardar la partida: " + Excepcion.getMessage());
                }
            }
        }, "GuardadoPartidaCutTheRope");
        HiloGuardado.setDaemon(true);
        HiloGuardado.start();

        return HiloGuardado;
    }

    public static synchronized void Guardar(Path RutaArchivo, RegistroPartida Registro) throws IOException {
        if (RutaArchivo == null || Registro == null) {
            throw new IllegalArgumentException("La ruta y el registro son obligatorios");
        }
        Path Carpeta = RutaArchivo.getParent();
        if (Carpeta != null) {
            Files.createDirectories(Carpeta);
        }
        List<RegistroPartida> Registros = new ArrayList<>(Leer(RutaArchivo));
        Registros.add(Registro);
        try (ObjectOutputStream Salida = new ObjectOutputStream(Files.newOutputStream(RutaArchivo))) {
            Salida.writeObject(Registros);
        }
    }

    public static synchronized List<RegistroPartida> Leer(Path RutaArchivo) throws IOException {
        if (RutaArchivo == null || !Files.exists(RutaArchivo)) {
            return Collections.emptyList();
        }
        try (ObjectInputStream Entrada = new ObjectInputStream(Files.newInputStream(RutaArchivo))) {
            Object ObjetoLeido = Entrada.readObject();
            if (!(ObjetoLeido instanceof List<?>)) {
                return Collections.emptyList();
            }
            List<?> ListaLeida = (List<?>) ObjetoLeido;
            List<RegistroPartida> Registros = new ArrayList<>();
            for (Object ObjetoActual : ListaLeida) {
                if (ObjetoActual instanceof RegistroPartida) {
                    Registros.add((RegistroPartida) ObjetoActual);
                }
            }

            return Collections.unmodifiableList(Registros);
        } catch (ClassNotFoundException Excepcion) {
            throw new IOException("El archivo de partidas contiene datos incompatibles", Excepcion);
        }
    }
}
