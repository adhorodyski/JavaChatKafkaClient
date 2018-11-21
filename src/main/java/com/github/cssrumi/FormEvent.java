package com.github.cssrumi;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String message;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String message) {
        super(source);

        this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) {
        this.message = message;
    }
}
