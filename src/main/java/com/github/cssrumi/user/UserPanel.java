package com.github.cssrumi.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private JTextField userField;
    private JButton setBtn;
    private UserListener userListener;

    public UserPanel() {
        init();
        actionInit();

    }

    private void init(){
        Dimension dim = getPreferredSize();
        dim.height = 200;

        userField = new JTextField(10);
        setBtn = new JButton("Set Nick");

        setLayout(new BorderLayout());

        add(userField, BorderLayout.CENTER);
        add(setBtn, BorderLayout.EAST);
    }

    private void actionInit() {
        setBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();

                UserEvent ev = new UserEvent(this, username);

                if(userListener != null) {
                    userListener.userEventOccurred(ev);
                }
            }
        });
    }


    public void setUserListener(UserListener listener) {
        this.userListener = listener;
    }

    public void clear() {
        userField.setText("");
    }
}
