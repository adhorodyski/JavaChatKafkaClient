package com.github.cssrumi;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class MainFrame extends JFrame {

    private ChatPanel chatPanel;
    private FormPanel formPanel;

    public MainFrame(){
        super("RCHAT");
        setLayout(new BorderLayout());

        chatPanel = new ChatPanel();
        formPanel = new FormPanel();

        add(chatPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void appendMessage(String message) {
        chatPanel.appendText(message);
    }

}
