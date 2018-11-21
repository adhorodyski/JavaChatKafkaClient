package com.github.cssrumi;

import com.github.cssrumi.user.UserPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel{

    private JTextField messageField;
    private JButton sendBtn;
    private FormListener formListener;
    private UserPanel userPanel;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);
        sendBtn = new JButton("SEND");
        userPanel = new UserPanel();

        setLayout(new BorderLayout());

        add(messageField, BorderLayout.CENTER);
        add(sendBtn, BorderLayout.EAST);
        add(userPanel, BorderLayout.WEST);

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
