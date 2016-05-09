package com.rayje.rendering;

import com.rayje.rendering.renderables.IRenderable;
import sun.awt.Mutex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by scott on 5/8/16.
 */
public class RTTask implements Runnable {

    public void setDefaultColor(Color _defaultColor) {
        this._defaultColor = _defaultColor;
    }

    public void setRay(Ray _ray) {
        this._ray = _ray;
    }

    public void setObjects(List<IRenderable> _objects) {
        this._objects = _objects;
    }

    public void setCoordinates(int _x, int _y) {
        this._x = _x;
        this._y = _y;
    }

    public void setBuffer(BufferedImage _buffer) {
        this._buffer = _buffer;
    }

    public void setMtx(Mutex _mtx) {
        this._mtx = _mtx;
    }

    private Color _defaultColor;
    private Ray _ray;
    private List<IRenderable> _objects;
    private int _x, _y;
    private BufferedImage _buffer;
    private Mutex _mtx;

    private Color TraceRay(Ray ray, java.util.List<IRenderable> objects) {
        HitResult hit = null;
        double distance = Double.MAX_VALUE;

        for (IRenderable obj : objects) {
            HitResult newHit = obj.CheckCollision(ray);
            if (newHit != null) {
                double newDist = newHit.position.Magnitude();
                if (distance > newDist) {
                    hit = newHit;
                    distance = newDist;
                }
            }
        }

        if (hit != null) {
            return hit.renderable.GetColor(hit, objects, _defaultColor);
        }

        return _defaultColor;
    }

    @Override
    public void run() {
        Color c = TraceRay(_ray, _objects);
        _mtx.lock();
        _buffer.setRGB(_x, _y, c.getRGB());
        _mtx.unlock();
    }
}
