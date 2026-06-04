/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tusderechos.Juego.entidades;

/**
 *
 * @author Hp
 */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.tusderechos.Juego.interfaces.Cortable;
import com.tusderechos.Juego.interfaces.Dibujable;
import com.tusderechos.Juego.utilidades.ConstantesJuego;
import com.tusderechos.Juego.utilidades.GeometriaJuego;

public class Cuerda implements Cortable, Dibujable {
    private final World Mundo;
    private final Body Ancla;
    private final Body CuerpoDulce;
    private DistanceJoint Joint;
    private boolean Cortada;
    private static final float GrosorVisual = 0.035f;

    public Cuerda(World Mundo, Vector2 PosicionAncla, float Longitud, Body CuerpoDulce) {
        this.Mundo = Mundo;
        this.CuerpoDulce = CuerpoDulce;
        BodyDef DefinicionAncla = new BodyDef();
        DefinicionAncla.type = BodyDef.BodyType.StaticBody;
        DefinicionAncla.position.set(PosicionAncla);
        Ancla = Mundo.createBody(DefinicionAncla);
        DistanceJointDef DefinicionJoint = new DistanceJointDef();
        DefinicionJoint.initialize(Ancla, CuerpoDulce, Ancla.getPosition(), CuerpoDulce.getPosition());
        DefinicionJoint.length = Longitud;
        Joint = (DistanceJoint) Mundo.createJoint(DefinicionJoint);
    }

    public Vector2 ObtenerAncla() {
        return new Vector2(Ancla.getPosition());
    }
    public Vector2 ObtenerFin() {
        return new Vector2(CuerpoDulce.getPosition());
    }
    public float ObtenerDistanciaAlPunto(Vector2 PuntoMundo) {
        return GeometriaJuego.DistanciaPuntoASegmento(PuntoMundo, Ancla.getPosition(), CuerpoDulce.getPosition());
    }
    public Vector2 ProyectarPuntoDeCorte(Vector2 PuntoMundo) {
        return GeometriaJuego.ProyectarPuntoSobreSegmento(PuntoMundo, Ancla.getPosition(), CuerpoDulce.getPosition());
    }

    @Override
    public boolean ContienePuntoDeCorte(Vector2 PuntoMundo) {
        return !Cortada && ObtenerDistanciaAlPunto(PuntoMundo) <= ConstantesJuego.MargenCorteCuerda;
    }

    @Override
    public void Cortar() {
        if (Cortada) {
            return;
        }
        Mundo.destroyJoint(Joint);
        Joint = null;
        Cortada = true;
    }

    @Override
    public boolean EstaCortada() {
        return Cortada;
    }

    @Override
    public void Dibujar(ShapeRenderer ShapeRendererActual) {
        if (Cortada) {
            return;
        }
        ShapeRendererActual.setColor(new Color(0.82f, 0.70f, 0.46f, 1f));
        ShapeRendererActual.rectLine(Ancla.getPosition(), CuerpoDulce.getPosition(), GrosorVisual);
    }
}

