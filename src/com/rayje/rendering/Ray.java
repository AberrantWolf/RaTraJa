package com.rayje.rendering;

/**
 * Created by scott on 5/7/16.
 */

public class Ray {
    public Vector origin;
    public Vector direction;

    public Ray(Vector inOrigin, Vector inDirection) {
        origin = inOrigin;
        direction = inDirection;
    }

    public Vector PointAtDistance(double dist) {
        return origin.Added(direction.Multiplied(dist));
    }
}
