package com.github.cssrumi;

import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JavaClient {

    private static BlockingQueue<String> messages = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {


        KafkaClient kafkaClient = new KafkaClient(messages);
        kafkaClient.setBootstrapServers("localhost:9092");
        kafkaClient.setup();

        Thread mainFrameThread = new Thread(new Runnable() {
            private MainFrame mainFrame;
            @Override
            public void run() {
                mainFrame = new MainFrame();
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void sendMessage(String message){
                mainFrame.appendMessage(message + "\n");
            }

            private void consume() throws InterruptedException {
                while (true) {
                    String message = messages.take();
//                    System.out.println(message);
                    sendMessage(message);
                }
            }
        });

        Thread kafkaThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    kafkaClient.putMessages();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        Thread consumerThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    consumer();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        mainFrameThread.start();
        kafkaThread.start();
//        consumerThread.start();

    }

//    private static void consumer() throws InterruptedException {
//        while(true) {
//            String message = messages.take();
//            System.out.println(message);
//        }
//
//    }
}
