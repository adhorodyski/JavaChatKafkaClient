package com.github.cssrumi;

import com.github.cssrumi.chat.Message;
import com.github.cssrumi.kafka.KafkaClient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JavaClient {

    private static String serverIP;
    private static BlockingQueue<String> messages = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        setServerIP("10.111.120.19");
        KafkaClient kafkaClient = new KafkaClient(messages);
        kafkaClient.setBootstrapServers(getServerIP() + ":9092");
        kafkaClient.setRandomGroup();
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
                mainFrame.appendMessage(message);
            }

            private void consume() throws InterruptedException {
                String message;
                String rawMessage;
                while (true) {
                    rawMessage = messages.take();
                    message = Message.MessageParser(rawMessage);
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


        mainFrameThread.start();
        kafkaThread.start();

    }

    public static String getServerIP(){
        return serverIP;
    }

    public static void setServerIP(String IP){
        serverIP = IP;
    }

}
