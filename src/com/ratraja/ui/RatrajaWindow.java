package com.ratraja.ui;

import com.ratraja.controller.RatrajaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by scott on 5/7/16.
 */
public class RatrajaWindow extends JFrame {
    private RatrajaController _controller;

    private Canvas _canvas;
    private JButton _renderButton;

    public RatrajaWindow() {
        super("RaTraJa GUI");

        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel drawPanel = new JPanel();
        _canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                BufferedImage bi = _controller.GetRenderResult();
                if (bi != null) {
                    g.drawImage(bi, 0, 0, null);
                }
            }
        };
        _canvas.setBackground(Color.blue);
        drawPanel.add(_canvas);
        topPanel.add(drawPanel);

        _renderButton = new JButton("Render");
        _renderButton.addActionListener((ActionEvent evt) -> {
            System.out.println("Render now!!");
            if (_controller != null) {
                _controller.RenderScene();
                _canvas.repaint();
            }
        });
        topPanel.add(_renderButton);

        add(topPanel);
        pack();
    }

    public void SetController(RatrajaController iController) {
        _controller = iController;
    }
}
