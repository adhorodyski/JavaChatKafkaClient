package com.github.cssrumi;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    private JTextArea textArea;

    public ChatPanel() {
        textArea = new JTextArea();
        setAutoscrolls(true);
        textArea.setEditable(false);

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text);
    }
}
