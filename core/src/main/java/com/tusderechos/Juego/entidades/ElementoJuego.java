package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.tusderechos.Juego.interfaces.Actualizable;
import com.tusderechos.Juego.interfaces.Dibujable;

public abstract class ElementoJuego implements Actualizable, Dibujable {
    protected Body cuerpo;

    public Body obtenerCuerpo() { return cuerpo; }
    public boolean tieneCuerpo() { return cuerpo != null; }

    @Override
    public void actualizar(float delta) {
    }

    @Override
    public abstract void dibujar(ShapeRenderer shapeRenderer);
}
