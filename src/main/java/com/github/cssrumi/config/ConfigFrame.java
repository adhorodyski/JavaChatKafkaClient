package com.github.cssrumi.config;

import com.github.cssrumi.JavaClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ConfigFrame extends JFrame {

    private ConfigPanel configPanel;

    public ConfigFrame() {
        super("RCHAT CONFIG");
        setLayout(new BorderLayout());

        configPanel = new ConfigPanel();

        configPanel.setUserListener(new ConfigListener() {
            @Override
            public void configEventOccurred(ConfigEvent e) {
                JavaClient.setServerIP(e.getIp());
                exit();
            }
        });

        add(configPanel, BorderLayout.CENTER);

        setSize(200, 80);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }


    private void exit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
