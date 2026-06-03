/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.lwjgl3;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tusderechos.Juego.Juego;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Lwjgl3Launcher {
    public static void main(String[] Args) {
        if (StartupHelper.startNewJvmIfRequired()) {
            return;
        }
        CreateApplication();
    }

    private static Lwjgl3Application CreateApplication() {
        return new Lwjgl3Application(new Juego(), GetDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration GetDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration Configuration = new Lwjgl3ApplicationConfiguration();
        Configuration.setTitle("CutTheRope");
        Configuration.useVsync(true);
        Configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        Configuration.setWindowedMode(ConstantesJuego.AnchoVentana, ConstantesJuego.AltoVentana);
        Configuration.setResizable(false);
        Configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return Configuration;
    }
}

