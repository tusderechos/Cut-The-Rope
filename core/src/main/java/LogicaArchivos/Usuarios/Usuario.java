/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaArchivos.Usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nombreCompleto;
    private Date fechaRegistro;
    private Date ultimaSesion;

    private int nivelesCompletados;
    private float tiempoTotalJugado;
    private int partidasJugadas;
    private int estrellasTotales;

    private ArrayList<String> historialPartidas;
    private ArrayList<String> listaRivales;

    private float volumenMusica;
    private float volumenSonido;
    private String avatarPath;

    public Usuario(String username, String password, String nombreCompleto) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaRegistro = new Date();
        this.ultimaSesion = new Date();

        this.nivelesCompletados = 0;
        this.tiempoTotalJugado = 0.0f;
        this.partidasJugadas = 0;
        this.estrellasTotales = 0;

        this.historialPartidas = new ArrayList<>();
        this.listaRivales = new ArrayList<>();

        this.volumenMusica = 0.8f;
        this.volumenSonido = 0.8f;
        this.avatarPath = "avatars/default.png";
    }

    public void registrarPartida(int nivel, boolean gano, int estrellasObtenidas, float tiempoEnNivel) {
        this.partidasJugadas++;
        this.tiempoTotalJugado += tiempoEnNivel;

        if (gano) {
            this.estrellasTotales += estrellasObtenidas;
            if (nivel > this.nivelesCompletados) {
                this.nivelesCompletados = nivel;
            }
        }

        String resultado = "Nivel " + nivel + " - " + (gano ? "Completado" : "Fallido")
                + " | Estrellas: " + estrellasObtenidas + " | Tiempo: " + tiempoEnNivel + "s | Fecha: " + new Date();
        this.historialPartidas.add(resultado);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public int getNivelesCompletados() {
        return nivelesCompletados;
    }

    public float getTiempoTotalJugado() {
        return tiempoTotalJugado;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getEstrellasTotales() {
        return estrellasTotales;
    }

    public ArrayList<String> getHistorialPartidas() {
        return historialPartidas;
    }

    public ArrayList<String> getListaRivales() {
        return listaRivales;
    }

    public float getVolumenMusica() {
        return volumenMusica;
    }

    public void setVolumenMusica(float volumenMusica) {
        this.volumenMusica = volumenMusica;
    }

    public float getVolumenSonido() {
        return volumenSonido;
    }

    public void setVolumenSonido(float volumenSonido) {
        this.volumenSonido = volumenSonido;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
