package com.example.logservice.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void log(String msg){
        amqpTemplate.convertAndSend(msg);

    }
}
