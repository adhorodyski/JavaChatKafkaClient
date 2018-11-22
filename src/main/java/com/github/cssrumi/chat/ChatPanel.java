package com.github.cssrumi.chat;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextArea textArea;
    private DefaultCaret caret;
    private JScrollPane scrollPane;
    private MessageSender messageSender;

    public ChatPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setAutoscrolls(true);

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