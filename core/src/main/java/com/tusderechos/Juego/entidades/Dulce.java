/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tusderechos.Juego.personalizacion.PersonalizacionDulce;
import com.tusderechos.Juego.utilidades.ConstantesJuego;

public class Dulce extends ElementoJuego {
    private final PersonalizacionDulce Personalizacion;
    private final Body Cuerpo;

    public Dulce(World Mundo, Vector2 PosicionInicial, PersonalizacionDulce Personalizacion) {
        this.Personalizacion = Personalizacion;
        BodyDef Definicion = new BodyDef();
        Definicion.type = BodyDef.BodyType.DynamicBody;
        Definicion.position.set(PosicionInicial);
        Cuerpo = Mundo.createBody(Definicion);
        CircleShape Forma = new CircleShape();
        Forma.setRadius(ConstantesJuego.RadioDulce);
        FixtureDef Fixture = new FixtureDef();
        Fixture.shape = Forma;
        Fixture.density = 1f;
        Fixture.friction = 0.25f;
        Fixture.restitution = 0.12f;
        Cuerpo.createFixture(Fixture);
        Forma.dispose();
    }

    public Body ObtenerCuerpo() {
        return Cuerpo;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        ShapeRendererActual.setColor(Personalizacion.ObtenerColor());
        ShapeRendererActual.circle(Cuerpo.getPosition().x, Cuerpo.getPosition().y, ConstantesJuego.RadioDulce, 32);
    }
}

