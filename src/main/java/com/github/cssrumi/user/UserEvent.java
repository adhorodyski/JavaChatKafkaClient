package com.github.cssrumi.user;

import java.util.EventObject;

public class UserEvent extends EventObject {

    private String username;

    public UserEvent(Object source) {
        super(source);
    }

    public UserEvent(Object source, String username) {
        super(source);

        this.username = username;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }
}
