/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.rivalidad;

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
import java.util.List;

public final class GuardadorRivalidadesBinario {
    private GuardadorRivalidadesBinario() {
    }

    public static List<SolicitudRivalidad> Cargar(Path RutaArchivo) {
        if (RutaArchivo == null || !Files.exists(RutaArchivo)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream Entrada = new ObjectInputStream(Files.newInputStream(RutaArchivo))) {
            Object Objeto = Entrada.readObject();
            if (Objeto instanceof List<?>) {
                List<?> ListaOriginal = (List<?>) Objeto;
                List<SolicitudRivalidad> Solicitudes = new ArrayList<>();
                for (Object Elemento : ListaOriginal) {
                    if (Elemento instanceof SolicitudRivalidad) {
                        Solicitudes.add((SolicitudRivalidad) Elemento);
                    }
                }

                return Solicitudes;
            }
        } catch (IOException | ClassNotFoundException Excepcion) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    public static void Guardar(Path RutaArchivo, List<SolicitudRivalidad> Solicitudes) {
        if (RutaArchivo == null) {
            throw new IllegalArgumentException("La ruta de rivalidades no puede ser nula");
        }
        try {
            Path Carpeta = RutaArchivo.getParent();
            if (Carpeta != null) {
                Files.createDirectories(Carpeta);
            }
            try (ObjectOutputStream Salida = new ObjectOutputStream(Files.newOutputStream(RutaArchivo))) {
                Salida.writeObject(new ArrayList<>(Solicitudes == null ? new ArrayList<SolicitudRivalidad>() : Solicitudes));
            }
        } catch (IOException Excepcion) {
            throw new IllegalStateException("No se pudieron guardar las rivalidades", Excepcion);
        }
    }
}
