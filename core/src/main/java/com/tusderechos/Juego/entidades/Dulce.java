package com.tusderechos.Juego.entidades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Dulce extends ElementoJuego {
    private final PersonalizacionDulce personalizacion;

    public Dulce(World mundo, Vector2 posicionInicial, PersonalizacionDulce personalizacion) {
        this.personalizacion = personalizacion;
        BodyDef definicion = new BodyDef();
        definicion.type = BodyDef.BodyType.DynamicBody;
        definicion.position.set(posicionInicial);
        cuerpo = mundo.createBody(definicion);
        CircleShape forma = new CircleShape();
        forma.setRadius(ConstantesJuego.RADIO_DULCE);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = forma;
        fixture.density = 1f;
        fixture.friction = 0.25f;
        fixture.restitution = 0.12f;
        cuerpo.createFixture(fixture);
        forma.dispose();
    }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(personalizacion.obtenerColor());
        shapeRenderer.circle(cuerpo.getPosition().x, cuerpo.getPosition().y, ConstantesJuego.RADIO_DULCE, 32);
    }
}
