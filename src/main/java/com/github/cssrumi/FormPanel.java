package com.github.cssrumi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel{

    private JTextField messageField;
    private JButton sendBtn;
    private FormListener formListener;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);

        sendBtn = new JButton("SEND");

        setLayout(new BorderLayout());

        add(messageField, BorderLayout.CENTER);
        add(sendBtn, BorderLayout.EAST);

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();

                FormEvent ev = new FormEvent(this, message);

                if(formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

}
