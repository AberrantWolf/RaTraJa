package com.ratraja.rendering;

import java.awt.*;

/**
 * Created by scott on 5/7/16.
 */

public interface IRenderable {
    TraceIntersection CheckCollision(Ray ray);
    Color GetColor(Ray ray, java.util.List<IRenderable> objects, Color background); // TODO: add lights to scene
}
