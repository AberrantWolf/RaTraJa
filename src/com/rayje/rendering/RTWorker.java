package com.rayje.rendering;

import com.rayje.rendering.renderables.IRenderable;
import sun.awt.Mutex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * RTWorker
 *
 * An object which draws a single ray in a thread.
 *
 * Created by scott on 5/8/16.
 */
class RTWorker {

    //===================================
    // STATIC ACCESS
    private static int _threadCount = 1;
    private static ArrayList<RTWorker> _workers;

    static RTWorker getWorker() {
        if (_workers == null) {
            _workers = new ArrayList<>(_threadCount);
        }
        ensureCorrectThreadCount();

        while(true) {
            for(RTWorker worker : _workers) {
                if (worker.isAvailable()) {
                    return worker;
                }
                Thread.yield();
            }
        }
    }

    private static void ensureCorrectThreadCount() {
        // Add up to thread count (if needed)
        while (_workers.size() < _threadCount) {
            _workers.add(new RTWorker());
        }

        // Remove down to thread count (if needed)
        while (_workers.size() > _threadCount) {
            _workers.remove(0);
        }
    }

    public static int setThreadCount(int count) {
        if (count > 0) {
            _threadCount = count;
        } else {
            _threadCount = 1;
        }

        ensureCorrectThreadCount();

        return _threadCount;
    }

    public static int getThreadCount() {
        return _threadCount;
    }

    static boolean waitForComplete() {
        boolean wasSuccessful = true;
        for (RTWorker worker : _workers) {
            worker._shouldRun = false;

            if (worker._thread.isAlive()) {
                try {
                    worker._thread.join();
                } catch (InterruptedException ex) {
                    System.out.println("RTWorker was interrupted and could not be joined.");
                    wasSuccessful = false;
                }
            }
        }

        _workers.clear();

        return wasSuccessful;
    }
    //===================================

    private boolean _shouldRun;
    private boolean _needsDone;
    private Thread _thread;
    private RTTask _task;

    private RTWorker() {
        _shouldRun = true;
        _task = new RTTask();
        _needsDone = false;

        _thread = new Thread() {
            @Override
            public synchronized void run() {
                while (_shouldRun) {
                    if (_needsDone) {
                        _task.run();
                        _needsDone = false;
                    }
                    yield();
                }
            }
        };

        _thread.start();
    }

    synchronized boolean runProcess(Ray ray,
                       List<IRenderable> objects,
                       int x,
                       int y,
                       Color defaultColor,
                       BufferedImage buffer,
                       Mutex mutex) {
        _task.setBuffer(buffer);
        _task.setDefaultColor(defaultColor);
        _task.setMtx(mutex);
        _task.setObjects(objects);
        _task.setRay(ray);
        _task.setCoordinates(x, y);
        _needsDone = true;
        Thread.yield();

        return true;
    }

    private boolean isAvailable() {
        return !_needsDone;
    }
}
