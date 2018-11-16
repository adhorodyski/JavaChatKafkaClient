package com.github.cssrumi;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel{

    private JTextField messageField;
    private JButton sendBtn;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);

        sendBtn = new JButton("SEND");

        setLayout(new BorderLayout());

        add(messageField, BorderLayout.CENTER);
        add(sendBtn, BorderLayout.EAST);
    }

}
