package com.rayje.controller;

import com.rayje.rendering.Camera;
import com.rayje.rendering.Scene;

import java.awt.image.BufferedImage;

/**
 * Created by scott on 5/7/16.
 */
public class RayJEController {
    private Scene _scene;
    private Camera _camera;
    private BufferedImage _image;

    public RayJEController() {
        _scene = null;
        _camera = null;
        _image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        _image.getGraphics().fillRect(0, 0, 200, 200);
    }

    public void SetScene(Scene scene) {
        _scene = scene;
    }

    public void SetCamera(Camera camera) {
        _camera = camera;
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
