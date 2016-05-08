package com.ratraja;

import com.ratraja.controller.RatrajaController;
import com.ratraja.ui.RatrajaWindow;

public class Main {

    public static void main(String[] args) {
        RatrajaController controller = new RatrajaController();

        RatrajaWindow window = new RatrajaWindow();
        window.SetController(controller);
        window.pack();
        window.setVisible(true);
    }
}
