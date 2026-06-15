/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejoArchivos.Archivos;
import com.badlogic.gdx.Gdx;
import LogicaArchivos.Usuarios.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author HP
 */
public class ManejadorArchivos {
    private static final String ROOT_DIR = "Usuarios/";

    public static boolean guardarUsuario(Usuario usuario) {
        try {
            String userFolderPath = ROOT_DIR + usuario.getUsername() + "/";
            File carpetaUsuario = new File(userFolderPath);
            
            if (!carpetaUsuario.exists()) {
                carpetaUsuario.mkdirs(); 
            }
            
            File archivoBin = new File(userFolderPath + usuario.getUsername() + ".bin");
            
            FileOutputStream fos = new FileOutputStream(archivoBin);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(usuario); 
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            Gdx.app.error("ManejadorArchivos", "Error guardando usuario binario: " + e.getMessage());
            return false;
        }
    }

    public static Usuario cargarUsuario(String username) {
        String filePath = ROOT_DIR + username + "/" + username + ".bin";
        File archivoBin = new File(filePath);
        
        if (!archivoBin.exists()) {
            return null; 
        }
        
        try {
            FileInputStream fis = new FileInputStream(archivoBin);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Usuario usuario = (Usuario) ois.readObject();
            ois.close();
            fis.close();
            return usuario;
        } catch (Exception e) {
            Gdx.app.error("ManejadorArchivos", "Error cargando usuario binario: " + e.getMessage());
            return null;
        }
    }

    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuariosRegistrados = new ArrayList<>();
        File carpetaUsuarios = new File(ROOT_DIR);

        if (!carpetaUsuarios.exists() || !carpetaUsuarios.isDirectory()) {
            return usuariosRegistrados;
        }

        File[] carpetasEncontradas = carpetaUsuarios.listFiles(File::isDirectory);
        if (carpetasEncontradas == null) {
            return usuariosRegistrados;
        }

        for (File carpetaUsuario : carpetasEncontradas) {
            Usuario usuarioActual = cargarUsuario(carpetaUsuario.getName());
            if (usuarioActual != null) {
                usuariosRegistrados.add(usuarioActual);
            }
        }

        usuariosRegistrados.sort(Comparator.comparing(Usuario::getUsername, String.CASE_INSENSITIVE_ORDER));
        return usuariosRegistrados;
    }
}
