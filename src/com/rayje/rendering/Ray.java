package com.rayje.rendering;

/**
 * Created by scott on 5/7/16.
 */

public class Ray {
    public Vector3 origin;
    public Vector3 direction;

    public Ray(Vector3 inOrigin, Vector3 inDirection) {
        origin = inOrigin;
        direction = inDirection;
    }

    public Vector3 PointAtDistance(double dist) {
        return origin.Added(direction.Multiplied(dist));
    }
}
