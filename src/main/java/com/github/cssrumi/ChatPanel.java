package com.github.cssrumi;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public ChatPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setAutoscrolls(true);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}
