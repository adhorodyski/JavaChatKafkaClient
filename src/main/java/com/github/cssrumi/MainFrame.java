package com.github.cssrumi;

import com.github.cssrumi.chat.ChatPanel;
import com.github.cssrumi.chat.Message;
import com.github.cssrumi.form.FormEvent;
import com.github.cssrumi.form.FormListener;
import com.github.cssrumi.form.FormPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ChatPanel chatPanel;
    private FormPanel formPanel;

    public MainFrame(){
        super("RCHAT");
        setLayout(new BorderLayout());

        chatPanel = new ChatPanel();
        formPanel = new FormPanel();

        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                String message = e.getMessage();
                chatPanel.sendMessage(
                        Message.createJsonMessage(
                                formPanel.getUsername(),
                                message,
                                formPanel.getToken())
                );
            }
        });

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
