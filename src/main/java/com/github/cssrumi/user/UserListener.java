package com.github.cssrumi.user;

import java.util.EventListener;

public interface UserListener extends EventListener {
    public void formEventOccurred(UserEvent e);
}
