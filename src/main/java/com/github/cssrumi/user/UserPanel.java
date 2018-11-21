package com.github.cssrumi.user;

import com.github.cssrumi.FormEvent;
import com.github.cssrumi.FormListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private JTextField userField;
    private JButton setBtn;
    private FormListener userListener;

    public UserPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        userField = new JTextField(200);

        setBtn = new JButton("Set Nickname");

        setLayout(new BorderLayout());

        add(userField, BorderLayout.CENTER);
        add(setBtn, BorderLayout.EAST);

        setBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = userField.getText();

                FormEvent ev = new FormEvent(this, message);

                if(userListener != null) {
                    userListener.formEventOccurred(ev);
                }
            }
        });
    }

    public void setUserListener(FormListener listener) {
        this.userListener = listener;
    }

}
