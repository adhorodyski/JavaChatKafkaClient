package com.github.cssrumi.form;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ReturnEvent implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){

        }
    }
}
