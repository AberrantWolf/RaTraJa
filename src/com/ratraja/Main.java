package com.ratraja;

import com.ratraja.controller.RatrajaController;
import com.ratraja.rendering.Camera;
import com.ratraja.rendering.Scene;
import com.ratraja.rendering.renderables.Sphere;
import com.ratraja.ui.RatrajaWindow;

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

        RatrajaController controller = new RatrajaController();
        controller.SetScene(scene);
        controller.SetCamera(camera);

        RatrajaWindow window = new RatrajaWindow();
        window.SetController(controller);
        window.pack();
        window.setVisible(true);
    }
}
