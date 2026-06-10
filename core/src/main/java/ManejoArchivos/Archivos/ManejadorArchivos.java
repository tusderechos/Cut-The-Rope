/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejoArchivos.Archivos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import LogicaArchivos.Usuarios.Usuario;
import java.io.*;
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
}
