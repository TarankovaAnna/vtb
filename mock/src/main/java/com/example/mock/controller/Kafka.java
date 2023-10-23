package com.example.mock.controller;

import com.example.mock.request.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.kafka.producer.bootstrap-servers}")
public class Kafka {
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Принимаем экземпляр класса KafkaTemplate<String, String>
    @Autowired
    public Kafka(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Получаем значение topic
    @Value("${topic}")
    private String topic;

    public void sendOrder(Data data) {
        Integer msg_id = data.getMsg_Id();
        long timestamp = data.getTimestamp();
        String msg = "{ \"msg_id\": \"" + msg_id + "\", \"timestamp\": \"" + timestamp + "\", \"method\": \"POST\", \"uri\": \"/post-message\" }";
        System.out.println(msg);
        // Отправляем сообщение в Kafka
        kafkaTemplate.send(topic, msg);
    }
}