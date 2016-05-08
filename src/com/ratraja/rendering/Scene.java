/**
 * Created by scott on 5/7/16.
 */
package com.ratraja.rendering;

import sun.awt.Mutex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene {
    private ArrayList<IRenderable> _objects;
    private Mutex _mtx;

    public Scene() {
        _objects = new ArrayList<IRenderable>();
    }

    public void AddObject(IRenderable o) {
        if (!_objects.contains(o)) {
            _objects.add(o);
        }
    }

    public final List<IRenderable> GetObjects() {
        return Collections.unmodifiableList(_objects);
    }
}
