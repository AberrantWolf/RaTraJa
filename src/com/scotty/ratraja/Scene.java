/**
 * Created by scott on 5/7/16.
 */
package com.scotty.ratraja;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Scene {
    private ArrayList<IRenderable> _objects;
    private int _width, _height;

    public Scene() {
        _objects = new ArrayList<IRenderable>();
        SetDimensions(1280, 720);
    }

    public void AddObject(IRenderable o) {
        if (!_objects.contains(o)) {
            _objects.add(o);
        }
    }

    public void SetDimensions(int iw, int ih) {
        _width = iw;
        _height = ih;
    }

    public BufferedImage Render() {
        BufferedImage result = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < _width; i++) {
            for (int j = 0; j < _height; j++) {
                // Create ray
                for (IRenderable obj : _objects) {

                }
            }
        }

        return result;
    }
}
