package com.github.cssrumi.config;

import java.util.EventObject;

public class ConfigEvent extends EventObject {

    private String ip;

    public ConfigEvent(Object source) {
        super(source);
    }

    public ConfigEvent(Object source, String ip) {
        super(source);

        this.ip = ip;
    }

    public String getIp() { return ip; }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
