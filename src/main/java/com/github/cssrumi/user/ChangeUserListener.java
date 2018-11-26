package com.github.cssrumi.user;

import java.util.EventListener;

public interface ChangeUserListener extends EventListener {
    public void changeUserEventOccurred(ChangeUserEvent e);
}
