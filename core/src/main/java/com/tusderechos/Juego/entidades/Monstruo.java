package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tusderechos.Juego.personalizacion.PersonalizacionMonstruo;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Monstruo extends ElementoJuego {
    private final Vector2 posicion;
    private final PersonalizacionMonstruo personalizacion;

    public Monstruo(Vector2 posicion, PersonalizacionMonstruo personalizacion) {
        this.posicion = new Vector2(posicion);
        this.personalizacion = personalizacion;
    }

    public boolean contieneDulce(Vector2 posicionDulce) {
        return posicion.dst(posicionDulce) <= ConstantesJuego.RADIO_MONSTRUO + ConstantesJuego.RADIO_DULCE;
    }

    public Vector2 obtenerPosicion() { return new Vector2(posicion); }
    public void establecerPosicion(Vector2 nuevaPosicion) { posicion.set(nuevaPosicion); }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(personalizacion.obtenerColor());
        shapeRenderer.circle(posicion.x, posicion.y, ConstantesJuego.RADIO_MONSTRUO, 32);
    }
}
