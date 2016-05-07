package com.scotty.ratraja;

/**
 * Created by scott on 5/7/16.
 */

public class Vector {
    public double x, y, z;

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
