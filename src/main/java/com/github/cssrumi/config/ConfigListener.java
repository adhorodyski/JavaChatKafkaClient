package com.github.cssrumi.config;

import java.util.EventListener;

public interface ConfigListener extends EventListener {
    public void configEventOccurred(ConfigEvent e);
}
