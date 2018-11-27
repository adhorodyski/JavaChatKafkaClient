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
                String newIP = e.getIp();
                if(!newIP.equals("Default"))
                    JavaClient.setServerIP(newIP);
                exit();
            }
        });

        add(configPanel, BorderLayout.CENTER);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }


    private void exit() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
