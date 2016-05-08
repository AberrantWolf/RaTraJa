package com.ratraja.rendering.renderables;

import com.ratraja.rendering.Ray;
import com.ratraja.rendering.HitResult;
import com.ratraja.rendering.Vector;

import java.awt.*;

/**
 * Created by scott on 5/7/16.
 */

public abstract class IRenderable {
    protected Vector _position;

    public IRenderable() {
        _position = new Vector(0, 0, 0);
    }

    public void SetPosition(Vector v) {
        _position = v;
    }

    public void SetPosition(double x, double y, double z) {
        _position.x = x;
        _position.y = y;
        _position.z = z;
    }

    public abstract HitResult CheckCollision(Ray ray);
    public abstract Color GetColor(HitResult hit, java.util.List<IRenderable> objects, Color background); // TODO: add lights to scene
}
