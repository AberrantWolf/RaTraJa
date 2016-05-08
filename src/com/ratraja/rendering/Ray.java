package com.ratraja.rendering;

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

    public void Add(Vector vec) {
        direction.Add(vec);
    }

    public void Normalize() {

    }
}
