package com.example.paymentservice.consumer;

import com.example.paymentservice.dto.OrderCreatedDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RegionConsumer {
    @RabbitListener(queues="order.india.queue")
    public void handleIndiaOrder(OrderCreatedDto event){
        System.out.println("[TOPIC_INDIA] Recieved Order: "+event.getOrderId());
        System.out.println("[TOPIC_INDIA] Amount :"+event.getAmount());
        System.out.println("[TOPIC_INDIA] applying India regional processing...");
    }

    @RabbitListener(queues="order.usa.queue")
    public void handleUsOrder(OrderCreatedDto event){
        System.out.println("[TOPIC_USA] Recieved Order: "+event.getOrderId());
        System.out.println("[TOPIC_USA] Amount :"+event.getAmount());
        System.out.println("[TOPIC_USA] pplying Usa regional processing...");
    }}