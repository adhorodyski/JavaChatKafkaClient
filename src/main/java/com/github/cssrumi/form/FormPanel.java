package com.github.cssrumi.form;

import com.github.cssrumi.user.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormPanel extends JPanel {

    private JTextField messageField;
    private JButton sendBtn;
    private FormListener formListener;
    private UserPanel userPanel;
    private ChangeUserPanel changeUserPanel;
    private User user;
    private String token;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.height = 200;

        messageField = new JTextField(200);
        sendBtn = new JButton("SEND");

        user = new User();
        userPanel = new UserPanel();
        changeUserPanel = new ChangeUserPanel();

        addUserListener();
        addChangeUserListener();

        setLayout(new BorderLayout());

        addUserPanel();
        add(messageField, BorderLayout.CENTER);
        add(sendBtn, BorderLayout.EAST);

        addSendListener();
        addMessageKeyListener();
    }

    public void addMessageKeyListener() {
        messageField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    String message = messageField.getText();

                    FormEvent ev = new FormEvent(this, message);

                    if (formListener != null && !message.equals("") && !user.getUsername().equals("")) {
                        formListener.formEventOccurred(ev);
                        messageField.setText("");
                    }
                }
            }
        });
    }

    public void addSendListener() {
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();

                FormEvent ev = new FormEvent(this, message);

                if (formListener != null && !message.equals("") && !user.getUsername().equals("")) {
                    formListener.formEventOccurred(ev);
                    messageField.setText("");
                }
            }
        });
    }

    public void addChangeUserListener() {
        changeUserPanel.setChangeUserListener(new ChangeUserListener() {
            @Override
            public void changeUserEventOccurred(ChangeUserEvent e) {
                removeChangeUserPanel();
                addUserPanel();
                user.clear();
                userPanel.clear();
                revalidate();
                repaint();
            }
        });
    }

    public void addUserListener() {
        userPanel.setUserListener(new UserListener() {
            @Override
            public void userEventOccurred(UserEvent e) {
                String username = e.getUsername();

                token = user.checkUserAndGetToken(username);
                if (token != null && !token.equals("")) {
                    user.setToken(token);
                    user.setUsername(username);
                    removeUserPanel();
                    addChangeUserPanel();
                    revalidate();
                    repaint();
                }
            }
        });
    }

    public void addChangeUserPanel() {
        changeUserPanel.setUsername(user.getUsername());
        add(changeUserPanel, BorderLayout.WEST);
    }

    public void addUserPanel() {
        add(userPanel, BorderLayout.WEST);
        user.setToken("");
        user.setUsername("");
    }

    public void removeChangeUserPanel() {
        remove(changeUserPanel);
    }

    public void removeUserPanel() {
        remove(userPanel);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getToken() {
        return user.getToken();
    }

}
