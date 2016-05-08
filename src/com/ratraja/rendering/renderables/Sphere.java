package com.ratraja.rendering.renderables;

import com.ratraja.math.RatrajaMath;
import com.ratraja.rendering.*;
import com.ratraja.rendering.Vector;

import java.awt.*;

/**
 * Sphere
 *
 * Represents an idealized renderable sphere object.
 *
 * Created by scott on 5/7/16.
 */

public class Sphere extends IRenderable {
    private double _radius;
    private double _radiusSquared;

    public double GetRadius() {
        return _radius;
    }
    public void SetRadius(double r) {
        _radius = r;
        _radiusSquared = r * r;
    }

    @Override
    public HitResult CheckCollision(Ray ray) {
        Vector L = ray.origin.Subtracted(_position);//_position.Subtracted(ray.origin);
        double a = ray.direction.DotProduct(ray.direction);
        double b = 2 * ray.direction.DotProduct(L);
        double c = L.DotProduct(L) - _radiusSquared;

        RatrajaMath.QuadraticResult result = RatrajaMath.SolveQuadratic(a, b, c);

        if (!result.canSolve) {
            return null;
        }

        if (result.t0 < 0) {
            result.t0 = result.t1;
            if (result.t0 < 0) {
                return null;
            }
        }

        return new HitResult(this, ray.PointAtDistance(result.t0), ray);
    }

    @Override
    public Color GetColor(HitResult hit, java.util.List<IRenderable> objects, Color background) {
        int depth = (int)(256 * (1 - ((hit.position.z)) * 0.5));
        int x = (int)(256 * (1 + (hit.position.x)) * 0.5);
        int y = (int)(256 * (1 + (hit.position.y)) * 0.5);

        depth = Math.max(0, depth);
        depth = Math.min(255, depth);

        x = Math.max(0, x);
        x = Math.min(255, x);

        y = Math.max(0, y);
        y = Math.min(255, y);
        return new Color(x, y, depth);
    }
}
