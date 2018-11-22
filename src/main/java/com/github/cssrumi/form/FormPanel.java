package com.github.cssrumi.form;

import com.github.cssrumi.user.UserEvent;
import com.github.cssrumi.user.UserListener;
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
    private String token;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);
        sendBtn = new JButton("SEND");
        userPanel = new UserPanel();

        userPanel.setUserListener(new UserListener() {
            @Override
            public void userEventOccurred(UserEvent e) {
                String username = e.getUsername();

                token = userPanel.checkUserAndGetToken(username);
                if(token != null)
                    userPanel.setUsername(username);
                }
            }
        );

        setLayout(new BorderLayout());

        add(userPanel, BorderLayout.WEST);
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

    public String getUsername() { return userPanel.getUsername(); }
    public String getToken() { return userPanel.getToken(); }

}
