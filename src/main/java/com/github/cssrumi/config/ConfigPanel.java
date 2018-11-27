package com.github.cssrumi.config;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    private JTextField ipField;
    private JButton setBtn;
    private ConfigListener configListener;
    private ServerChecker serverChecker;

    public ConfigPanel() {
        init();
        initLayout();
        actionInit();
    }

    private void init() {
        serverChecker = new ServerChecker();

        ipField = new JTextField(15);
        setBtn = new JButton("Set IP");

        ipField.setText("Default");
    }

    private void initLayout() {
        Border innerBorder = BorderFactory.createTitledBorder("Server Config");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new BorderLayout());

        add(ipField, BorderLayout.CENTER);
        add(setBtn, BorderLayout.EAST);
    }

    private void actionInit() {
        setBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = ipField.getText();

                ConfigEvent ev = new ConfigEvent(this, ip);

                if(configListener != null) {
                    configListener.configEventOccurred(ev);
                }
            }
        });
    }

    public void setUserListener(ConfigListener listener) {
        this.configListener = listener;
    }

    public boolean isValid(String ip) {
        serverChecker.validate(ip);
        return serverChecker.isValid();
    }

}
