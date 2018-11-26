package com.github.cssrumi.form;

import com.github.cssrumi.user.User;
import com.github.cssrumi.user.UserEvent;
import com.github.cssrumi.user.UserListener;
import com.github.cssrumi.user.SetUserPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel{

    private JTextField messageField;
    private JButton sendBtn;
    private FormListener formListener;
    private SetUserPanel setUserPanel;
    private User user;
    private String token;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);
        sendBtn = new JButton("SEND");

        user = new User();
        setUserPanel = new SetUserPanel();
        setUserPanel.setUserListener(new UserListener() {
            @Override
            public void userEventOccurred(UserEvent e) {
                String username = e.getUsername();

                token = setUserPanel.checkUserAndGetToken(username);
                if(token != null)
                    user.setToken(token);
                    user.setUsername(username);
                }
            }
        );

        setLayout(new BorderLayout());

        add(setUserPanel, BorderLayout.WEST);
        add(messageField, BorderLayout.CENTER);
        add(sendBtn, BorderLayout.EAST);

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();

                FormEvent ev = new FormEvent(this, message);

                if(formListener != null && !message.equals("")) {
                    formListener.formEventOccurred(ev);
                    messageField.setText("");
                }
            }
        });
    }

    public void changeUserPanel()
    {

    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    public String getUsername() { return user.getUsername(); }
    public String getToken() { return user.getToken(); }

}
