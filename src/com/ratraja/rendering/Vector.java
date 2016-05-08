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

    public Vector Add(Vector b) {
        return new Vector(x + b.x, y + b.y, z + b.z);
    }

    public static void AddAtoB(Vector a, Vector b) {
        b.x += a.x;
        b.y += a.y;
        b.z += a.z;
    }

    public double Magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public void Normalize() {
        double mag = Magnitude();
        x /= mag;
        y /= mag;
        z /= mag;
    }
}
