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
    private static final Color ColorSombra = new Color(0.28f, 0.20f, 0.12f, 0.75f);
    private static final Color ColorCuerpo = new Color(0.72f, 0.52f, 0.27f, 1f);
    private static final Color ColorBrillo = new Color(0.96f, 0.82f, 0.52f, 0.88f);
    private static final float GrosorSombra = 0.070f;
    private static final float GrosorCuerpo = 0.052f;
    private static final float GrosorBrillo = 0.018f;
    private final World Mundo;
    private final Body Ancla;
    private final Body CuerpoDulce;
    private DistanceJoint Joint;
    private boolean Cortada;

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

    private Vector2 ProyectarPuntoDeCorte(Vector2 PuntoMundo) {
        return GeometriaJuego.ProyectarPuntoSobreSegmento(PuntoMundo, Ancla.getPosition(), CuerpoDulce.getPosition());
    }

    @Override
    public boolean IntersectaTrazoDeCorte(Vector2 InicioTrazo, Vector2 FinTrazo) {
        return !Cortada && GeometriaJuego.SegmentosEstanCerca(InicioTrazo, FinTrazo, Ancla.getPosition(), CuerpoDulce.getPosition(), ConstantesJuego.MargenCorteCuerda);
    }

    public Vector2 ProyectarTrazoDeCorte(Vector2 InicioTrazo, Vector2 FinTrazo) {
        Vector2 PuntoMedioTrazo = new Vector2(InicioTrazo).add(FinTrazo).scl(0.5f);
        return ProyectarPuntoDeCorte(PuntoMedioTrazo);
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
        ShapeRendererActual.setColor(ColorSombra);
        ShapeRendererActual.rectLine(Ancla.getPosition(), CuerpoDulce.getPosition(), GrosorSombra);
        ShapeRendererActual.setColor(ColorCuerpo);
        ShapeRendererActual.rectLine(Ancla.getPosition(), CuerpoDulce.getPosition(), GrosorCuerpo);
        ShapeRendererActual.setColor(ColorBrillo);
        ShapeRendererActual.rectLine(Ancla.getPosition(), CuerpoDulce.getPosition(), GrosorBrillo);
    }
}

