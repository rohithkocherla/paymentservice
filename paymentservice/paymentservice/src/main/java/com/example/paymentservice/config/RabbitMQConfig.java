package com.example.paymentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String DIRECT_EXCHANGE = "order.direct.exchange";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String DIRECT_ROUTING_KEY = "order.created";
    @Bean
    public DirectExchange directExchange() {

        return new DirectExchange(DIRECT_EXCHANGE);
    }
    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding directBinding(){
        return BindingBuilder.bind(paymentQueue())
                .to(directExchange())
                .with(DIRECT_ROUTING_KEY);
    }

    public static final String TOPIC_EXCHANGE = "order.topic.exchange";
    public static final String ORDER_INDIA_QUEUE = "order.india.queue";
    public static final String ORDER_USA_QUEUE = "order.usa.queue";
    public static final String TOPIC_ROUTING_INDIA= "order.india";
    public static final String TOPIC_ROUTING_USA= "order.usa";

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }


    @Bean
    public Queue orderIndiaQueue(){
        return new Queue(ORDER_INDIA_QUEUE);
    }

    @Bean
    public Queue orderUsaQueue(){
        return new Queue(ORDER_USA_QUEUE);
    }

    @Bean
    public Binding topicIndiaBinding(){
        return BindingBuilder
                .bind(orderIndiaQueue())
                .to(topicExchange())
                .with(TOPIC_ROUTING_INDIA);
    }


    @Bean
    public Binding topicUsaBinding(){
        return BindingBuilder
                .bind(orderIndiaQueue())
                .to(topicExchange())
                .with(TOPIC_ROUTING_USA);
    }

    // FANOUT EXCHANGE
    public static final String FANOUT_EXCHANGE = "order.fanout.exchange";
    public static final String NOTIFICATION_QUEUE = "notification.queue";
    public static final String ANALYTICS_QUEUE = "analytics.queue";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue analyticsQueue() {
        return new Queue(ANALYTICS_QUEUE);
    }

    // Binding (NO routing key needed)
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(notificationQueue())
                .to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(analyticsQueue())
                .to(fanoutExchange());
    }
    public static final String DLX_EXCHANGE = "order.dlx.exchange";
    public static final String DLQ_QUEUE = "payment.dlq.queue";
    public static final String DLX_ROUTING_KEY = "order.dlq.routing";

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ_QUEUE, true);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(dlxExchange())
                .with(DLX_ROUTING_KEY);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}