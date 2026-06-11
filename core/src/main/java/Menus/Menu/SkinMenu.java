/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus.Menu;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class SkinMenu {
    private SkinMenu() {
    }

    public static Skin Crear() {
        Skin SkinActual = new Skin();
        BitmapFont Fuente = new BitmapFont();
        SkinActual.add("default", Fuente);

        SkinActual.add("white", CrearTextura(Color.WHITE));
        SkinActual.add("fondoCampo", CrearTextura(new Color(0.12f, 0.18f, 0.16f, 1f)));
        SkinActual.add("fondoBoton", CrearTextura(new Color(0.18f, 0.36f, 0.25f, 1f)));
        SkinActual.add("fondoBotonPresionado", CrearTextura(new Color(0.10f, 0.24f, 0.17f, 1f)));
        SkinActual.add("seleccion", CrearTextura(new Color(0.25f, 0.55f, 0.35f, 1f)));
        SkinActual.add("cursor", CrearTextura(Color.WHITE));

        CrearLabel(SkinActual, Fuente);
        CrearBoton(SkinActual, Fuente);
        CrearCampoTexto(SkinActual, Fuente);
        CrearCheckBox(SkinActual, Fuente);
        CrearSlider(SkinActual);
        CrearLista(SkinActual, Fuente);
        CrearScroll(SkinActual);
        CrearSelectBox(SkinActual, Fuente);

        return SkinActual;
    }

    private static void CrearLabel(Skin SkinActual, BitmapFont Fuente) {
        Label.LabelStyle Estilo = new Label.LabelStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;
        SkinActual.add("default", Estilo);
    }

    private static void CrearBoton(Skin SkinActual, BitmapFont Fuente) {
        TextButton.TextButtonStyle Estilo = new TextButton.TextButtonStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;
        Estilo.up = SkinActual.newDrawable("fondoBoton");
        Estilo.down = SkinActual.newDrawable("fondoBotonPresionado");
        SkinActual.add("default", Estilo);
    }

    private static void CrearCampoTexto(Skin SkinActual, BitmapFont Fuente) {
        TextField.TextFieldStyle Estilo = new TextField.TextFieldStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;
        Estilo.background = SkinActual.newDrawable("fondoCampo");
        Estilo.cursor = SkinActual.newDrawable("cursor");
        Estilo.selection = SkinActual.newDrawable("seleccion");
        SkinActual.add("default", Estilo);
    }

    private static void CrearCheckBox(Skin SkinActual, BitmapFont Fuente) {
        CheckBox.CheckBoxStyle Estilo = new CheckBox.CheckBoxStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;
        Estilo.checkboxOff = CrearDrawableCaja(new Color(0.12f, 0.18f, 0.16f, 1f));
        Estilo.checkboxOn = CrearDrawableCaja(new Color(0.25f, 0.65f, 0.35f, 1f));
        SkinActual.add("default", Estilo);
    }

    private static void CrearSlider(Skin SkinActual) {
        Slider.SliderStyle Estilo = new Slider.SliderStyle();
        Estilo.background = CrearDrawableRectangular(new Color(0.16f, 0.24f, 0.20f, 1f), 140, 8);
        Estilo.knob = CrearDrawableRectangular(new Color(0.42f, 0.80f, 0.52f, 1f), 14, 22);
        SkinActual.add("default-horizontal", Estilo);
    }

    private static void CrearLista(Skin SkinActual, BitmapFont Fuente) {
        List.ListStyle Estilo = new List.ListStyle();
        Estilo.font = Fuente;
        Estilo.fontColorSelected = Color.WHITE;
        Estilo.fontColorUnselected = Color.LIGHT_GRAY;
        Estilo.selection = SkinActual.newDrawable("seleccion");
        SkinActual.add("default", Estilo);
    }

    private static void CrearScroll(Skin SkinActual) {
        ScrollPane.ScrollPaneStyle Estilo = new ScrollPane.ScrollPaneStyle();
        Estilo.background = SkinActual.newDrawable("fondoCampo");
        SkinActual.add("default", Estilo);
    }

    private static void CrearSelectBox(Skin SkinActual, BitmapFont Fuente) {
        SelectBox.SelectBoxStyle Estilo = new SelectBox.SelectBoxStyle();
        Estilo.font = Fuente;
        Estilo.fontColor = Color.WHITE;
        Estilo.background = SkinActual.newDrawable("fondoBoton");
        Estilo.scrollStyle = SkinActual.get(ScrollPane.ScrollPaneStyle.class);
        Estilo.listStyle = SkinActual.get(List.ListStyle.class);
        SkinActual.add("default", Estilo);
    }

    private static Texture CrearTextura(Color ColorActual) {
        Pixmap PixmapActual = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorActual);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();

        return Textura;
    }

    private static Drawable CrearDrawableCaja(Color ColorActual) {
        return CrearDrawableRectangular(ColorActual, 18, 18);
    }

    private static Drawable CrearDrawableRectangular(Color ColorActual, int Ancho, int Alto) {
        Pixmap PixmapActual = new Pixmap(Ancho, Alto, Pixmap.Format.RGBA8888);
        PixmapActual.setColor(ColorActual);
        PixmapActual.fill();
        Texture Textura = new Texture(PixmapActual);
        PixmapActual.dispose();

        return new TextureRegionDrawable(new TextureRegion(Textura));
    }
}
