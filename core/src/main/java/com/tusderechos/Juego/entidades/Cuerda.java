package com.tusderechos.Juego.entidades;

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
    private final World mundo;
    private final Body ancla;
    private final Body cuerpoDulce;
    private DistanceJoint joint;
    private boolean cortada;

    public Cuerda(World mundo, Vector2 posicionAncla, float longitud, Body cuerpoDulce) {
        this.mundo = mundo;
        this.cuerpoDulce = cuerpoDulce;
        BodyDef definicionAncla = new BodyDef();
        definicionAncla.type = BodyDef.BodyType.StaticBody;
        definicionAncla.position.set(posicionAncla);
        ancla = mundo.createBody(definicionAncla);
        DistanceJointDef definicionJoint = new DistanceJointDef();
        definicionJoint.initialize(ancla, cuerpoDulce, ancla.getPosition(), cuerpoDulce.getPosition());
        definicionJoint.length = longitud;
        joint = (DistanceJoint) mundo.createJoint(definicionJoint);
    }

    public Vector2 obtenerAncla() { return new Vector2(ancla.getPosition()); }
    public Vector2 obtenerFin() { return new Vector2(cuerpoDulce.getPosition()); }

    @Override
    public boolean contienePuntoDeCorte(Vector2 puntoMundo) {
        return !cortada && GeometriaJuego.distanciaPuntoASegmento(puntoMundo, ancla.getPosition(), cuerpoDulce.getPosition())
            <= ConstantesJuego.MARGEN_CORTE_CUERDA;
    }

    @Override
    public void cortar(Vector2 puntoMundo) {
        if (cortada) return;
        mundo.destroyJoint(joint);
        joint = null;
        cortada = true;
    }

    @Override
    public boolean estaCortada() { return cortada; }

    @Override
    public void dibujar(ShapeRenderer shapeRenderer) {
        if (cortada) return;
        shapeRenderer.setColor(new Color(0.82f, 0.70f, 0.46f, 1f));
        shapeRenderer.line(ancla.getPosition(), cuerpoDulce.getPosition());
    }
}
