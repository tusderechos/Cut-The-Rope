/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.textos;

/**
 *
 * @author Hp
 */

import Menus.Menu.ConfiguracionJuego;
import com.tusderechos.Juego.enums.CategoriaDificultad;
import java.util.Locale;

public final class TextosIdioma {
    private TextosIdioma() {
    }

    public static String Obtener(String Clave) {
        return ObtenerConIdioma(ConfiguracionJuego.idiomaActivo, Clave);
    }

    public static String Formatear(String Clave, Object... Valores) {
        return FormatearConIdioma(ConfiguracionJuego.idiomaActivo, Clave, Valores);
    }

    public static String FormatearCategoria(CategoriaDificultad Categoria, int NumeroNivel, int Puntaje, int Estrellas) {
        return Formatear("RetoResumen", ObtenerCategoria(Categoria), NumeroNivel, Puntaje, Estrellas);
    }

    public static String ObtenerCategoria(CategoriaDificultad Categoria) {
        if (Categoria == null) {
            return "";
        }

        return Obtener("Categoria" + Categoria.name());
    }

    public static String ObtenerConIdioma(String Idioma, String Clave) {
        switch (NormalizarIdioma(Idioma)) {
            case "ING":
                return ObtenerIngles(Clave);
            case "FRA":
                return ObtenerFrances(Clave);
            case "GAR":
                return ObtenerGarifuna(Clave);
            case "HEB":
                return ObtenerHebreo(Clave);
            default:
                return ObtenerEspanol(Clave);
        }
    }

    public static String FormatearConIdioma(String Idioma, String Clave, Object... Valores) {
        return String.format(Locale.ROOT, ObtenerConIdioma(Idioma, Clave), Valores);
    }

    private static String NormalizarIdioma(String Idioma) {
        if (Idioma == null) {
            return "ESP";
        }

        String Codigo = Idioma.trim().toUpperCase(Locale.ROOT);
        if (Codigo.equals("ENG")) {
            return "ING";
        }

        if (Codigo.equals("FR")) {
            return "FRA";
        }

        return Codigo;
    }

    private static String ObtenerEspanol(String Clave) {
        switch (Clave) {
            case "Personalizacion":
                return "Personalizacion";
            case "Invitado":
                return "Invitado";
            case "NivelHud":
                return "Nivel %d";
            case "TextoHudCompleto":
                return "Nivel %d   Estrellas %d/3   Tiempo %d s";
            case "JuegoCompletado":
                return "Juego completado";
            case "NivelCompletado":
                return "Nivel completado";
            case "CompletasteTodos":
                return "Completaste todos los niveles";
            case "PuntajeConseguido":
                return "Puntaje conseguido: %d";
            case "TiempoUsado":
                return "Tiempo usado: %d s";
            case "FallosIntento":
                return "Fallos del intento: %d";
            case "EstrellasFaltantes":
                return "Estrellas faltantes: %d";
            case "ObjetivoReto":
                return "Objetivo: %d pts / %d estrellas";
            case "RetoSuperado":
                return "Reto superado";
            case "RetoFallido":
                return "Reto fallido";
            case "Siguiente":
                return "Siguiente";
            case "Final":
                return "Final";
            case "SalirDelNivel":
                return "Salir del nivel?";
            case "IntentoReinicia":
                return "El intento se reiniciara.";
            case "DulcePerdido":
                return "El dulce se perdio";
            case "DulcePeligro":
                return "El dulce toco un peligro";
            case "IntentoFallido":
                return "Intento fallido";
            case "BuscarCampo":
                return "Username o nombre";
            case "BuscarTitulo":
                return "Buscar jugadores";
            case "BuscarSinSesion":
                return "Inicia sesion para seguir jugadores.";
            case "BuscarSinResultados":
                return "No se encontraron jugadores.";
            case "BuscarResultados":
                return "Toca seguir para preparar futuras rivalidades.";
            case "Bloqueado":
                return "Bloqueado";
            case "Siguiendo":
                return "Siguiendo";
            case "Seguir":
                return "Seguir";
            case "Perfil":
                return "Perfil";
            case "PerfilNoEncontrado":
                return "Perfil no encontrado";
            case "Volver":
                return "Volver";
            case "IniciaSesion":
                return "Inicia sesion";
            case "RivalidadPendiente":
                return "Rivalidad disponible cuando ambos se sigan";
            case "RivalidadTitulo":
                return "Rivalidad";
            case "Nivel":
                return "Nivel";
            case "Estrellas":
                return "Estrellas";
            case "Puntaje":
                return "Puntaje";
            case "RetoResumen":
                return "%s %d - %d pts / %d estrellas";
            case "CategoriaFacil":
                return "Facil";
            case "CategoriaMedia":
                return "Media";
            case "CategoriaDificil":
                return "Dificil";
            case "RetosSinSesion":
                return "Inicia sesion para ver retos";
            case "RetosVacio":
                return "No hay retos todavia";
            case "Rival":
                return "Rival: %s";
            case "Ganador":
                return "Ganador: %s";
            case "Pendiente":
                return "Pendiente";
            case "Esperando":
                return "Esperando";
            case "Rechazada":
                return "Rechazada";
            case "TurnoDe":
                return "Turno de %s";
            case "TurnoDeLinea":
                return "Turno de\n%s";
            case "DueloLocal":
                return "Duelo local";
            case "DueloNoEncontrado":
                return "No se encontro el duelo local";
            case "ListoParaJugar":
                return "Listo para jugar";
            case "EsperandoTurno":
                return "Esperando turno";
            case "Resultado":
                return "Resultado";
            case "EstrellasResultado":
                return "Estrellas: %d/3";
            case "TiempoResultado":
                return "Tiempo: %d s";
            case "PuntajeResultado":
                return "Puntaje: %d";
            case "Empate":
                return "Empate";
            default:
                return Clave;
        }
    }

    private static String ObtenerIngles(String Clave) {
        switch (Clave) {
            case "Personalizacion":
                return "Customization";
            case "Invitado":
                return "Guest";
            case "NivelHud":
                return "Level %d";
            case "TextoHudCompleto":
                return "Level %d   Stars %d/3   Time %d s";
            case "JuegoCompletado":
                return "Game completed";
            case "NivelCompletado":
                return "Level completed";
            case "CompletasteTodos":
                return "You completed every level";
            case "PuntajeConseguido":
                return "Score: %d";
            case "TiempoUsado":
                return "Time used: %d s";
            case "FallosIntento":
                return "Attempt fails: %d";
            case "EstrellasFaltantes":
                return "Missing stars: %d";
            case "ObjetivoReto":
                return "Goal: %d pts / %d stars";
            case "RetoSuperado":
                return "Challenge cleared";
            case "RetoFallido":
                return "Challenge failed";
            case "Siguiente":
                return "Next";
            case "Final":
                return "Final";
            case "SalirDelNivel":
                return "Leave level?";
            case "IntentoReinicia":
                return "The attempt will restart.";
            case "DulcePerdido":
                return "The candy was lost";
            case "DulcePeligro":
                return "The candy hit danger";
            case "IntentoFallido":
                return "Attempt failed";
            case "BuscarCampo":
                return "Username or name";
            case "BuscarTitulo":
                return "Find players";
            case "BuscarSinSesion":
                return "Log in to follow players.";
            case "BuscarSinResultados":
                return "No players found.";
            case "BuscarResultados":
                return "Follow players to prepare future rivalries.";
            case "Bloqueado":
                return "Locked";
            case "Siguiendo":
                return "Following";
            case "Seguir":
                return "Follow";
            case "Perfil":
                return "Profile";
            case "PerfilNoEncontrado":
                return "Profile not found";
            case "Volver":
                return "Back";
            case "IniciaSesion":
                return "Log in";
            case "RivalidadPendiente":
                return "Rivalry available when both follow each other";
            case "RivalidadTitulo":
                return "Rivalry";
            case "Nivel":
                return "Level";
            case "Estrellas":
                return "Stars";
            case "Puntaje":
                return "Score";
            case "RetoResumen":
                return "%s %d - %d pts / %d stars";
            case "CategoriaFacil":
                return "Easy";
            case "CategoriaMedia":
                return "Medium";
            case "CategoriaDificil":
                return "Hard";
            case "RetosSinSesion":
                return "Log in to see challenges";
            case "RetosVacio":
                return "No challenges yet";
            case "Rival":
                return "Rival: %s";
            case "Ganador":
                return "Winner: %s";
            case "Pendiente":
                return "Pending";
            case "Esperando":
                return "Waiting";
            case "Rechazada":
                return "Rejected";
            case "TurnoDe":
                return "%s's turn";
            case "TurnoDeLinea":
                return "%s's\nturn";
            case "DueloLocal":
                return "Local duel";
            case "DueloNoEncontrado":
                return "Local duel not found";
            case "ListoParaJugar":
                return "Ready to play";
            case "EsperandoTurno":
                return "Waiting turn";
            case "Resultado":
                return "Result";
            case "EstrellasResultado":
                return "Stars: %d/3";
            case "TiempoResultado":
                return "Time: %d s";
            case "PuntajeResultado":
                return "Score: %d";
            case "Empate":
                return "Tie";
            default:
                return ObtenerEspanol(Clave);
        }
    }

    private static String ObtenerFrances(String Clave) {
        switch (Clave) {
            case "Personalizacion":
                return "Personnalisation";
            case "Invitado":
                return "Invite";
            case "NivelHud":
                return "Niveau %d";
            case "TextoHudCompleto":
                return "Niveau %d   Etoiles %d/3   Temps %d s";
            case "JuegoCompletado":
                return "Jeu termine";
            case "NivelCompletado":
                return "Niveau termine";
            case "CompletasteTodos":
                return "Tu as termine tous les niveaux";
            case "PuntajeConseguido":
                return "Score obtenu: %d";
            case "TiempoUsado":
                return "Temps utilise: %d s";
            case "FallosIntento":
                return "Echecs: %d";
            case "EstrellasFaltantes":
                return "Etoiles manquantes: %d";
            case "ObjetivoReto":
                return "Objectif: %d pts / %d etoiles";
            case "RetoSuperado":
                return "Defi reussi";
            case "RetoFallido":
                return "Defi echoue";
            case "Siguiente":
                return "Suivant";
            case "Final":
                return "Final";
            case "SalirDelNivel":
                return "Quitter le niveau?";
            case "IntentoReinicia":
                return "L'essai recommencera.";
            case "DulcePerdido":
                return "Le bonbon est perdu";
            case "DulcePeligro":
                return "Le bonbon a touche un danger";
            case "IntentoFallido":
                return "Essai echoue";
            case "BuscarCampo":
                return "Username ou nom";
            case "BuscarTitulo":
                return "Chercher joueurs";
            case "BuscarSinSesion":
                return "Connecte-toi pour suivre des joueurs.";
            case "BuscarSinResultados":
                return "Aucun joueur trouve.";
            case "BuscarResultados":
                return "Suis des joueurs pour preparer des rivalites.";
            case "Bloqueado":
                return "Bloque";
            case "Siguiendo":
                return "Suivi";
            case "Seguir":
                return "Suivre";
            case "Perfil":
                return "Profil";
            case "PerfilNoEncontrado":
                return "Profil introuvable";
            case "Volver":
                return "Retour";
            case "IniciaSesion":
                return "Connexion";
            case "RivalidadPendiente":
                return "Rivalite disponible quand les deux se suivent";
            case "RivalidadTitulo":
                return "Rivalite";
            case "Nivel":
                return "Niveau";
            case "Estrellas":
                return "Etoiles";
            case "Puntaje":
                return "Score";
            case "RetoResumen":
                return "%s %d - %d pts / %d etoiles";
            case "CategoriaFacil":
                return "Facile";
            case "CategoriaMedia":
                return "Moyen";
            case "CategoriaDificil":
                return "Difficile";
            case "RetosSinSesion":
                return "Connecte-toi pour voir les defis";
            case "RetosVacio":
                return "Aucun defi pour l'instant";
            case "Rival":
                return "Rival: %s";
            case "Ganador":
                return "Gagnant: %s";
            case "Pendiente":
                return "En attente";
            case "Esperando":
                return "Attente";
            case "Rechazada":
                return "Refusee";
            case "TurnoDe":
                return "Tour de %s";
            case "TurnoDeLinea":
                return "Tour de\n%s";
            case "DueloLocal":
                return "Duel local";
            case "DueloNoEncontrado":
                return "Duel local introuvable";
            case "ListoParaJugar":
                return "Pret a jouer";
            case "EsperandoTurno":
                return "Tour en attente";
            case "Resultado":
                return "Resultat";
            case "EstrellasResultado":
                return "Etoiles: %d/3";
            case "TiempoResultado":
                return "Temps: %d s";
            case "PuntajeResultado":
                return "Score: %d";
            case "Empate":
                return "Egalite";
            default:
                return ObtenerEspanol(Clave);
        }
    }

    private static String ObtenerGarifuna(String Clave) {
        switch (Clave) {
            case "Personalizacion":
                return "Larigiyei";
            case "Invitado":
                return "Aban";
            case "NivelHud":
                return "Nivelu %d";
            case "TextoHudCompleto":
                return "Nivelu %d   Estrellas %d/3   Tiempo %d s";
            case "JuegoCompletado":
                return "Juegu furendei";
            case "NivelCompletado":
                return "Nivelu furendei";
            case "CompletasteTodos":
                return "Furendei lun nivelu";
            case "PuntajeConseguido":
                return "Puntaje: %d";
            case "TiempoUsado":
                return "Tiempo: %d s";
            case "FallosIntento":
                return "Fallu: %d";
            case "EstrellasFaltantes":
                return "Estrellas faltan: %d";
            case "ObjetivoReto":
                return "Meta: %d pts / %d estrellas";
            case "RetoSuperado":
                return "Reto ganadu";
            case "RetoFallido":
                return "Reto fallidu";
            case "Siguiente":
                return "Siguiente";
            case "Final":
                return "Final";
            case "SalirDelNivel":
                return "Salir del nivelu?";
            case "IntentoReinicia":
                return "Intentu reiniciara.";
            case "DulcePerdido":
                return "Dulce se perdio";
            case "DulcePeligro":
                return "Dulce toco peligro";
            case "IntentoFallido":
                return "Intentu fallidu";
            case "BuscarCampo":
                return "Username o nombre";
            case "BuscarTitulo":
                return "Buscar jugadores";
            case "BuscarSinSesion":
                return "Inicia sesion para seguir jugadores.";
            case "BuscarSinResultados":
                return "No se encontraron jugadores.";
            case "BuscarResultados":
                return "Toca seguir para preparar rivalidades.";
            case "Bloqueado":
                return "Bloqueado";
            case "Siguiendo":
                return "Siguiendo";
            case "Seguir":
                return "Seguir";
            case "Perfil":
                return "Perfil";
            case "PerfilNoEncontrado":
                return "Perfil no encontrado";
            case "Volver":
                return "Volver";
            case "IniciaSesion":
                return "Inicia sesion";
            case "RivalidadPendiente":
                return "Rivalidad disponible cuando ambos se sigan";
            case "RivalidadTitulo":
                return "Rivalidad";
            case "Nivel":
                return "Nivelu";
            case "Estrellas":
                return "Estrellas";
            case "Puntaje":
                return "Puntaje";
            case "RetoResumen":
                return "%s %d - %d pts / %d estrellas";
            case "CategoriaFacil":
                return "Facil";
            case "CategoriaMedia":
                return "Media";
            case "CategoriaDificil":
                return "Dificil";
            case "RetosSinSesion":
                return "Inicia sesion para ver retos";
            case "RetosVacio":
                return "No hay retos todavia";
            case "Rival":
                return "Rival: %s";
            case "Ganador":
                return "Ganador: %s";
            case "Pendiente":
                return "Pendiente";
            case "Esperando":
                return "Esperando";
            case "Rechazada":
                return "Rechazada";
            case "TurnoDe":
                return "Turno de %s";
            case "TurnoDeLinea":
                return "Turno de\n%s";
            case "DueloLocal":
                return "Duelo local";
            case "DueloNoEncontrado":
                return "No se encontro el duelo local";
            case "ListoParaJugar":
                return "Listo para jugar";
            case "EsperandoTurno":
                return "Esperando turno";
            case "Resultado":
                return "Resultado";
            case "EstrellasResultado":
                return "Estrellas: %d/3";
            case "TiempoResultado":
                return "Tiempo: %d s";
            case "PuntajeResultado":
                return "Puntaje: %d";
            case "Empate":
                return "Empate";
            default:
                return ObtenerEspanol(Clave);
        }
    }

    private static String ObtenerHebreo(String Clave) {
        switch (Clave) {
            case "Personalizacion":
                return "Hitama";
            case "Invitado":
                return "Oreach";
            case "NivelHud":
                return "Shlav %d";
            case "TextoHudCompleto":
                return "Shlav %d   Kochavim %d/3   Zman %d s";
            case "JuegoCompletado":
                return "Mishak hoshlem";
            case "NivelCompletado":
                return "Shlav hoshlem";
            case "CompletasteTodos":
                return "Kol hashlevim hoshlemu";
            case "PuntajeConseguido":
                return "Nikod: %d";
            case "TiempoUsado":
                return "Zman: %d s";
            case "FallosIntento":
                return "Kshalonot: %d";
            case "EstrellasFaltantes":
                return "Kochavim chaserim: %d";
            case "ObjetivoReto":
                return "Yaad: %d pts / %d kochavim";
            case "RetoSuperado":
                return "Etgar hutzlach";
            case "RetoFallido":
                return "Etgar nichshal";
            case "Siguiente":
                return "Haba";
            case "Final":
                return "Sof";
            case "SalirDelNivel":
                return "Latset mehashlav?";
            case "IntentoReinicia":
                return "Hanisayon yatchil mehadash.";
            case "DulcePerdido":
                return "Hamatak avad";
            case "DulcePeligro":
                return "Hamatak naga besakana";
            case "IntentoFallido":
                return "Nisayon nichshal";
            case "BuscarCampo":
                return "Username o name";
            case "BuscarTitulo":
                return "Chapes sakanim";
            case "BuscarSinSesion":
                return "Hitchaber kedei laakov.";
            case "BuscarSinResultados":
                return "Lo nimtzu sakanim.";
            case "BuscarResultados":
                return "Akov acharei sakanim l'rivalyot.";
            case "Bloqueado":
                return "Naul";
            case "Siguiendo":
                return "Okev";
            case "Seguir":
                return "Akov";
            case "Perfil":
                return "Profil";
            case "PerfilNoEncontrado":
                return "Profil lo nimtza";
            case "Volver":
                return "Chazor";
            case "IniciaSesion":
                return "Hitchaber";
            case "RivalidadPendiente":
                return "Rivalyut zmina kshe shneyhem okvim";
            case "RivalidadTitulo":
                return "Rivalyut";
            case "Nivel":
                return "Shlav";
            case "Estrellas":
                return "Kochavim";
            case "Puntaje":
                return "Nikod";
            case "RetoResumen":
                return "%s %d - %d pts / %d kochavim";
            case "CategoriaFacil":
                return "Kal";
            case "CategoriaMedia":
                return "Beinoni";
            case "CategoriaDificil":
                return "Kashe";
            case "RetosSinSesion":
                return "Hitchaber kedei lirot etgarim";
            case "RetosVacio":
                return "Ein etgarim adayin";
            case "Rival":
                return "Yariv: %s";
            case "Ganador":
                return "Menatzeach: %s";
            case "Pendiente":
                return "Mamtin";
            case "Esperando":
                return "Mechake";
            case "Rechazada":
                return "Nidcha";
            case "TurnoDe":
                return "Tor shel %s";
            case "TurnoDeLinea":
                return "Tor shel\n%s";
            case "DueloLocal":
                return "Du-krav mekomi";
            case "DueloNoEncontrado":
                return "Du-krav lo nimtza";
            case "ListoParaJugar":
                return "Muchan lesachek";
            case "EsperandoTurno":
                return "Mechake lator";
            case "Resultado":
                return "Totzaa";
            case "EstrellasResultado":
                return "Kochavim: %d/3";
            case "TiempoResultado":
                return "Zman: %d s";
            case "PuntajeResultado":
                return "Nikod: %d";
            case "Empate":
                return "Teko";
            default:
                return ObtenerEspanol(Clave);
        }
    }
}
