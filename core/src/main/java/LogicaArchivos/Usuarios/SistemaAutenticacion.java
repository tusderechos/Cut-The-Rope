/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaArchivos.Usuarios;

import ManejoArchivos.Archivos.ManejadorArchivos;
import java.util.Date;

/**
 *
 * @author HP
 */
public class SistemaAutenticacion {

    private static Usuario usuarioActivo = null;

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static void cerrarSesion() {
        if (usuarioActivo != null) {
            ManejadorArchivos.guardarUsuario(usuarioActivo);
            usuarioActivo = null;
        }
    }

    public static String registrarNuevoUsuario(String username, String password, String nombreCompleto, String rutaFotoPerfil) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return "Campos vacios no permitidos.";
        }

        String userLimpio = username.trim().toLowerCase();

        if (ManejadorArchivos.cargarUsuario(userLimpio) != null) {
            return "El nombre de usuario ya se encuentra registrado.";
        }

        Usuario nuevoUsuario = new Usuario(userLimpio, password, nombreCompleto, rutaFotoPerfil);

        nuevoUsuario.setCuentaActiva(true);

        boolean exito = ManejadorArchivos.guardarUsuario(nuevoUsuario);

        if (exito) {
            return "REGISTRO_EXITOSO";
        } else {
            return "Error critico al escribir el archivo de guardado.";
        }
    }

    public static String intentarIniciarSesion(String username, String password) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return "DATOS_INCOMPLETOS";
        }

        String userLimpio = username.trim().toLowerCase();
        Usuario encontrado = ManejadorArchivos.cargarUsuario(userLimpio);

        if (encontrado != null && encontrado.getPassword().equals(password)) {
            if (!encontrado.isCuentaActiva()) {
                return "CUENTA_DESACTIVADA";
            }

            usuarioActivo = encontrado;
            usuarioActivo.setUltimaSesion(new Date());
            ManejadorArchivos.guardarUsuario(usuarioActivo);
            return "LOGIN_EXITOSO";
        }

        return "CREDENCIALES_ERRONEAS";
    }
}
