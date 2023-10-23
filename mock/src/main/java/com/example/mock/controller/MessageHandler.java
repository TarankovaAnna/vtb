package com.example.mock.controller;

import com.example.mock.request.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageHandler {
    private final Kafka kafka;

    @Autowired
    public MessageHandler(Kafka kafka) {
        this.kafka = kafka;
    }

    // Обрабатываем POST запрос
    @PostMapping(value = "/post_message")
    public ResponseEntity<?> create(@RequestBody Data data) {

        // Получаем текущее время в формате Unix-времени
        long timestamp = System.currentTimeMillis() / 1000L;
        data.setTimestamp(timestamp);
        // Вызываем метод sendOrder у экземпляра класса Kafka
        kafka.sendOrder(data);
        // Возвращаем ответ с кодом 200
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
