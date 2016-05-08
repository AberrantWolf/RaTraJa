package com.ratraja.rendering;

/**
 * Created by scott on 5/7/16.
 */

public class Vector {
    public double x, y, z;

    public Vector(Vector copy) {
        x = copy.x;
        y = copy.y;
        z = copy.z;
    }

    public Vector(double ix, double iy, double iz) {
        x = ix;
        y = iy;
        z = iz;
    }

    public Vector Added(Vector b) {
        return new Vector(x + b.x, y + b.y, z + b.z);
    }

    public Vector Subtracted(Vector b) {
        return new Vector(x - b.x, y - b.y, z - b.z);
    }


    public Vector Multiplied(double dist) {
        Vector norm = Normalized();
        return new Vector(norm.x * dist, norm.y * dist, norm.z * dist);
    }

    public double Magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Normalize this vector in-place.
     */
    public void Normalize() {
        double mag = Magnitude();
        x /= mag;
        y /= mag;
        z /= mag;
    }

    /**
     * Create a normalized copy of this vector
     * @return a normalized vector along the same direction as this one
     */
    public Vector Normalized() {
        Vector norm = new Vector(this);
        norm.Normalize();
        return norm;
    }

    public double DotProduct(Vector v) {
        return x * v.x + y * v.y + z * v.z;
    }
}
