package com.devdream.blackjackaccountservice.infrastructure.publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Value("${rabbitmq.queueName}")
    private String queueName;

    @Bean
    public Queue queue(){
        return new Queue(queueName, true);
    }

}
