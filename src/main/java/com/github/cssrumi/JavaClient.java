package com.github.cssrumi;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JavaClient {

    private static BlockingQueue<String> messages = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        Thread mainFrameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
            }

            public void sendMessage(String message){

            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


//        Thread kafkaThread = new Thread();
        KafkaClient kafkaClient = new KafkaClient(messages);

    }

    private static void consumer() throws InterruptedException {
        while(true) {
            String message = messages.take();
            mainFrame
        }

    }
}
