/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaArchivos.Usuarios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    private String rutaFotoPerfil;
    private Date fechaRegistro;
    private Date ultimaSesion;

    private int nivelesCompletados;
    private float tiempoTotalJugado;
    private int partidasJugadas;
    private int estrellasTotales;
    private int puntajeTotal;
    private int mejorPuntaje;

    private ArrayList<String> historialPartidas;
    private ArrayList<String> listaRivales;

    private float volumenMusica;
    private float volumenSonido;
    private String avatarPath;

    private boolean cuentaActiva;

    public Usuario(String username, String password, String nombreCompleto, String rutaFotoPerfil) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.rutaFotoPerfil = rutaFotoPerfil;
        this.cuentaActiva = true;
        this.fechaRegistro = new Date();
        this.ultimaSesion = new Date();
        this.nivelesCompletados = 0;
        this.tiempoTotalJugado = 0.0f;
        this.partidasJugadas = 0;
        this.estrellasTotales = 0;
        this.puntajeTotal = 0;
        this.mejorPuntaje = 0;
        this.historialPartidas = new ArrayList<>();
        this.listaRivales = new ArrayList<>();
        this.volumenMusica = 0.8f;
        this.volumenSonido = 0.8f;
        this.avatarPath = "avatars/default.png";
    }

    public boolean isCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public void registrarPartida(int nivel, boolean gano, int estrellasObtenidas, float tiempoEnNivel) {
        registrarPartida(nivel, gano, estrellasObtenidas, tiempoEnNivel, 0);
    }

    public void registrarPartida(int nivel, boolean gano, int estrellasObtenidas, float tiempoEnNivel, int puntajeObtenido) {
        this.partidasJugadas++;
        this.tiempoTotalJugado += tiempoEnNivel;

        if (gano) {
            this.estrellasTotales += estrellasObtenidas;
            this.puntajeTotal += Math.max(0, puntajeObtenido);
            this.mejorPuntaje = Math.max(this.mejorPuntaje, puntajeObtenido);
            if (nivel > this.nivelesCompletados) {
                this.nivelesCompletados = nivel;
            }
        }

        String resultado = "Nivel " + nivel + " - " + (gano ? "Completado" : "Fallido")
                + " | Estrellas: " + estrellasObtenidas + " | Tiempo: " + tiempoEnNivel + "s | Fecha: " + new Date();
        this.historialPartidas.add(resultado);
    }

    public String getFechaIngreso() {
        if (this.fechaRegistro == null) {
            return "No registrada";
        }
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(this.fechaRegistro);
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

    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }

    public void setRutaFotoPerfil(String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
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

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public int getMejorPuntaje() {
        return mejorPuntaje;
    }

    public ArrayList<String> getHistorialPartidas() {
        return historialPartidas;
    }

    public ArrayList<String> getListaRivales() {
        if (listaRivales == null) {
            listaRivales = new ArrayList<>();
        }
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
