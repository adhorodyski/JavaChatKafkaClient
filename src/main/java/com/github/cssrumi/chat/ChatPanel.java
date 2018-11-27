package com.github.cssrumi.chat;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private MessageSender messageSender;

    public ChatPanel() {
        init();
    }

    private void init() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setAutoscrolls(true);

        new SmartScroller(scrollPane, SmartScroller.VERTICAL, SmartScroller.END);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        messageSender = new MessageSender();
    }

    public void appendText(String text) {
        textArea.append(text);

    }

    public void sendMessage(JSONObject message) {
        messageSender.send(message);
    }
}
