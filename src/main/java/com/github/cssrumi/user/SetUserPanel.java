package com.github.cssrumi.user;

import com.github.cssrumi.JavaClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUserPanel extends JPanel {
    private JTextField userField;
    private JButton setBtn;
    private UserListener userListener;
    private String token;
//    private String username;
    private UsernameChecker checker;

    public SetUserPanel() {
        init();
        actionInit();

    }

    private void init(){
        Dimension dim = getPreferredSize();
        dim.height = 200;

        userField = new JTextField(10);
        setBtn = new JButton("Set Nick");
        checker = new UsernameChecker(JavaClient.getServerIP());

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

    public String checkUserAndGetToken(String username){
        token = checker.ifValidGetToken(username);
        return token;
    }

    public void setUserListener(UserListener listener) {
        this.userListener = listener;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
