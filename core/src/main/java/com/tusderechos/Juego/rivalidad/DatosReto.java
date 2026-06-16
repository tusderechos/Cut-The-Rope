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
import com.tusderechos.Juego.niveles.FabricaNiveles;
import java.io.Serializable;

public final class DatosReto implements Serializable {
    private static final long serialVersionUID = 1L;

    private final CategoriaDificultad Categoria;
    private final int NumeroEnCategoria;
    private final String NombreRetador;
    private final int PuntajeObjetivo;
    private final int EstrellasObjetivo;

    public DatosReto(CategoriaDificultad Categoria, int NumeroEnCategoria, String NombreRetador, int PuntajeObjetivo, int EstrellasObjetivo) {
        if (Categoria == null || NumeroEnCategoria < 1 || NombreRetador == null || NombreRetador.trim().isEmpty() || PuntajeObjetivo < 0 || EstrellasObjetivo < 0 || EstrellasObjetivo > 3) {
            throw new IllegalArgumentException("Los datos del reto no son validos");
        }
        FabricaNiveles.ObtenerNivel(Categoria, NumeroEnCategoria);
        this.Categoria = Categoria;
        this.NumeroEnCategoria = NumeroEnCategoria;
        this.NombreRetador = NombreRetador.trim();
        this.PuntajeObjetivo = PuntajeObjetivo;
        this.EstrellasObjetivo = EstrellasObjetivo;
    }

    public CategoriaDificultad ObtenerCategoria() {
        return Categoria;
    }

    public int ObtenerNumeroEnCategoria() {
        return NumeroEnCategoria;
    }

    public String ObtenerNombreRetador() {
        return NombreRetador;
    }

    public int ObtenerPuntajeObjetivo() {
        return PuntajeObjetivo;
    }

    public int ObtenerEstrellasObjetivo() {
        return EstrellasObjetivo;
    }
}
