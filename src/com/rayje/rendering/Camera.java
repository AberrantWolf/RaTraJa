package com.rayje.rendering;

import com.rayje.rendering.renderables.IRenderable;
import sun.awt.Mutex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;

/**
 * Created by scott on 5/7/16.
 */
public class Camera {
    private int _pWidth, _pHeight;
    private double _vLeft, _vRight, _vBottom, _vTop;
    private double _ratio; // width / height
    private double _fov;
    private double _focalLength;

    private Color _backgroundColor;

    private boolean _useFlatPlane = false;

    private Vector3 _position;
    //private Quaternion _orientation;  // TODO: Make a quaternion class and allow camera rotation in the renderer

    public Camera() {
        _position = new Vector3(0, 0, 0);
        SetDimensions(1280, 720);
        _backgroundColor = Color.black;
    }

    public Camera(int iw, int ih) {
        SetDimensions(iw, ih);
    }

    public void SetDimensions(int iw, int ih) {
        _pWidth = iw;
        _pHeight = ih;
        _ratio = iw / (double)ih;

        assert _focalLength != 0;
        _fov = Math.atan(0.5 / _focalLength);

        _vRight = 0.5 * _ratio;
        _vLeft = -_vRight;      // symmetrical view
        _vTop = 0.5;
        _vBottom = -0.5;
    }

    public Ray CreateRay(int w, int h) {
        double xRatioCenter = (w + 0.5) / _pWidth - 0.5;
        double yRatioCenter = (1 - (h + 0.5) / _pHeight) - 0.5;

        Vector3 origin = new Vector3(_position);
        Vector3 direction = new Vector3(0, 0, 1);

        double vX, vY, vZ;

        if (_useFlatPlane) {
            // TODO: Use flat plane for determining pixel coordinates
            System.out.println("WARNING: Using flat plane projection without having set it up properly");
        } else {
            double thetaX = xRatioCenter * _ratio * _fov;
            double thetaY = yRatioCenter * _fov;

            vX = Math.sin(thetaX);
            vY = Math.sin(thetaY);
            vZ = Math.cos(thetaX) * Math.cos(thetaY);

            direction.x = vX;
            direction.y = vY;
            direction.z = vZ;
        }

        // TODO: Rotate ray vector by orientation vector after adding quaternions (above)

        return new Ray(origin, direction);
    }



    public BufferedImage RenderScene(Scene scene) {
        List<IRenderable> objects = scene.GetObjects();
        BufferedImage result = new BufferedImage(_pWidth, _pHeight, BufferedImage.TYPE_INT_ARGB);

        final Mutex mtx = new Mutex();

        long startTime = System.nanoTime();
        for (int y = 0; y < _pHeight; y++) {
            for (int x = 0; x < _pWidth; x++) {
                final Ray ray = CreateRay(x, y);

                RTWorker worker = RTWorker.getWorker();
                worker.runProcess(ray, objects, x, y, _backgroundColor, result, mtx);
            }
        }

        boolean didCompleteSuccessfully = RTWorker.waitForComplete();
        if (!didCompleteSuccessfully) {
            System.out.println("Render threads did not all complete successfully");
        }
        long deltaTime = System.nanoTime() - startTime;
        System.out.println("RenderScene() complete in " + deltaTime/1000000000.0 + "s.");

        return result;
    }
}
