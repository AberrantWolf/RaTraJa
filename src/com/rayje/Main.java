package com.rayje;

import com.rayje.controller.RayJEController;
import com.rayje.rendering.Camera;
import com.rayje.rendering.Scene;
import com.rayje.rendering.renderables.Sphere;
import com.rayje.ui.RayJERenderView;

public class Main {

    public static void main(String[] args) {
        Scene scene = new Scene();
        Camera camera = new Camera();

        Sphere sphere = new Sphere();
        sphere.SetRadius(1.0);
        sphere.SetPosition(0, 0, 2.2);

        Sphere sphere1 = new Sphere();
        sphere1.SetRadius(0.5);
        sphere1.SetPosition(0.75, 0.5, 1.5);

        scene.AddObject(sphere);
        scene.AddObject(sphere1);

        RayJEController controller = new RayJEController();
        controller.SetScene(scene);
        controller.SetCamera(camera);

        RayJERenderView window = new RayJERenderView();
        window.SetController(controller);
        window.pack();
        window.setVisible(true);
    }
}
