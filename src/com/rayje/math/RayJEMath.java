package com.rayje.math;

/**
 * Created by scott on 5/7/16.
 */
public class RayJEMath {
    public static class QuadraticResult {
        public double a, b, c;
        public double t0, t1;
        public boolean canSolve;
    }

    public static QuadraticResult SolveQuadratic(double a, double b, double c) {
        QuadraticResult result = new QuadraticResult();

        double discr  = b * b - 4 * a * c;
        if (discr < 0) {
            result.canSolve = false;
            return result;
        }
        result.canSolve = true;

        if (discr == 0) {
            result.t0 = result.t1 = -0.5 * b / a;
        } else {
            double q = (b > 0) ?
                    -0.5 * (b + Math.sqrt(discr)) :
                    -0.5 * (b - Math.sqrt(discr));
            result.t0 = q / a;
            result.t1 = c / q;
        }
        if (result.t1 < result.t0) {
            double temp = result.t1;
            result.t1 = result.t0;
            result.t0 = temp;
        }

        return result;
    }
}
