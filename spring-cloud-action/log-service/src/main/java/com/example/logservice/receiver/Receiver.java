package com.example.logservice.receiver;


import com.example.logservice.service.SysLogService;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch=new CountDownLatch(1);

    @Autowired
    SysLogService sysLogService;

    public void receiveMessage(String message){
        System.out.println("Received<"+message+">");
        sysLogService.outLog(message);
        latch.countDown();
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

    
}




























