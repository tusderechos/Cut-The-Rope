/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.graficos;

/**
 *
 * @author Hp
 */
import com.tusderechos.Juego.enums.ColorDulce;
import com.tusderechos.Juego.enums.ColorMonstruo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class RutasTexturas {
    public static final String Estrella = "imagenes/estrella.png";
    public static final String EstrellaVacia = "imagenes/estrella_placeholder.png";
    private static final Map<ColorDulce, String> RutasDulces = new EnumMap<>(ColorDulce.class);
    private static final Map<ColorMonstruo, String> RutasMonstruos = new EnumMap<>(ColorMonstruo.class);
    private static final List<String> RutasFondos = Arrays.asList("imagenes/fondo_1.PNG", "imagenes/fondo_2.PNG", "imagenes/fondo_3.png", "imagenes/fondo_4.png", "imagenes/fondo_5.png");

    static {
        RutasDulces.put(ColorDulce.Rojo, "imagenes/dulce_rojo.png");
        RutasDulces.put(ColorDulce.Azul, "imagenes/dulce_azul.png");
        RutasDulces.put(ColorDulce.Verde, "imagenes/dulce_verde.png");
        RutasDulces.put(ColorDulce.Morado, "imagenes/dulce_morado.png");
        RutasMonstruos.put(ColorMonstruo.Verde, "imagenes/monstruo_verde.png");
        RutasMonstruos.put(ColorMonstruo.Morado, "imagenes/monstruo_morado.png");
        RutasMonstruos.put(ColorMonstruo.Naranja, "imagenes/monstruo_naranja.png");
        RutasMonstruos.put(ColorMonstruo.Azul, "imagenes/monstruo_azul.png");
    }

    private RutasTexturas() {
    }

    public static String ObtenerDulce(ColorDulce ColorDulceActual) {
        return RutasDulces.get(ColorDulceActual);
    }

    public static String ObtenerMonstruo(ColorMonstruo ColorMonstruoActual) {
        return RutasMonstruos.get(ColorMonstruoActual);
    }

    public static String ObtenerFondoNivel(int NumeroNivel) {
        if (NumeroNivel < 1 || NumeroNivel > RutasFondos.size()) {
            throw new IllegalArgumentException("El fondo solicitado no existe");
        }
        return RutasFondos.get(NumeroNivel - 1);
    }

    public static List<String> ObtenerRutas() {
        List<String> Rutas = new ArrayList<>();
        Rutas.addAll(RutasDulces.values());
        Rutas.addAll(RutasMonstruos.values());
        Rutas.addAll(RutasFondos);
        Rutas.addAll(Arrays.asList(Estrella, EstrellaVacia));
        return Rutas;
    }
}
