package com.ratraja.controller;

import com.ratraja.rendering.Camera;
import com.ratraja.rendering.Scene;

import java.awt.image.BufferedImage;

/**
 * Created by scott on 5/7/16.
 */
public class RatrajaController {
    private Scene _scene;
    private Camera _camera;
    private BufferedImage _image;

    public RatrajaController() {
        _scene = null;
        _camera = null;
        _image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
    }

    public void RenderScene() {
        if (_scene != null && _camera != null) {
            _image = _camera.RenderScene(_scene);
        }
    }

    public BufferedImage GetRenderResult() {
        return _image;
    }
}
