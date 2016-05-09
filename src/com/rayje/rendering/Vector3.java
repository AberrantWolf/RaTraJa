package com.rayje.rendering;

/**
 * Created by scott on 5/7/16.
 */

public class Vector3 {
    public double x, y, z;

    public Vector3(Vector3 copy) {
        x = copy.x;
        y = copy.y;
        z = copy.z;
    }

    public Vector3(double ix, double iy, double iz) {
        x = ix;
        y = iy;
        z = iz;
    }

    public Vector3 Added(Vector3 b) {
        return new Vector3(x + b.x, y + b.y, z + b.z);
    }

    public Vector3 Subtracted(Vector3 b) {
        return new Vector3(x - b.x, y - b.y, z - b.z);
    }


    public Vector3 Multiplied(double dist) {
        Vector3 norm = Normalized();
        return new Vector3(norm.x * dist, norm.y * dist, norm.z * dist);
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
    public Vector3 Normalized() {
        Vector3 norm = new Vector3(this);
        norm.Normalize();
        return norm;
    }

    public double DotProduct(Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }
}
