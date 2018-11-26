package com.github.cssrumi.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUserPanel extends JPanel {
    private JTextField userField;
    private JButton changeBtn;
    private ChangeUserListener changeUserListener;
    private String username;


    public ChangeUserPanel() {
        init();
        actionInit();

    }

    private void init(){
        Dimension dim = getPreferredSize();
        dim.height = 200;

        userField = new JTextField(10);
        changeBtn = new JButton("Change");

        setLayout(new BorderLayout());
        userField.setEditable(false);

        add(userField, BorderLayout.CENTER);
        add(changeBtn, BorderLayout.EAST);
    }

    private void actionInit() {
        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ChangeUserEvent ev = new ChangeUserEvent(this);

                if(changeUserListener != null) {
                    changeUserListener.changeUserEventOccurred(ev);
                }
            }
        });
    }

    public void setUsername(String username) {
        userField.setText(username);
    }

    public void setChangeUserListener(ChangeUserListener changeUserListener) {
        this.changeUserListener = changeUserListener;
    }
}
