package com.github.cssrumi.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class KafkaClient {

    private String bootstrapServers;
    private String topic;
    private String groupId;
    private KafkaConsumer<String, String> consumer;
    private BlockingQueue<String> messages;
    private Properties properties;

    public KafkaClient(BlockingQueue<String> messages) {

        this.messages = messages;

        bootstrapServers = "localhost:9092";
        Random random = new Random();
        byte[] b = new byte[20];
        random.nextBytes(b);
        groupId = b.toString();
        System.out.println(b);
        topic = "test";
    }

    public void setup() {
        properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // create consumer
        consumer = new KafkaConsumer<>(properties);

        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic));
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    // poll for new data
    public void putMessages(){
        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

            for (ConsumerRecord<String, String> record : records) {
//                System.out.println(record);
//                System.out.println("Key: " + record.key() + ", Value: " + record.value());
                try {
                    messages.put(record.value());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
