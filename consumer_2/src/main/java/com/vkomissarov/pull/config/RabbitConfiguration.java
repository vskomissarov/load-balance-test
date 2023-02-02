package com.vkomissarov.pull.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfiguration {

    @Value("${rabbitmq.exchange-producer}")
    private String exchangeProducer;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue}")
    private String queue;

    @Bean
    public FanoutExchange exchangeProducer() {
        return new FanoutExchange(exchangeProducer);
    }

    @Bean
    public Queue consumerQueue() {
        return new Queue(queue);
    }

    @Bean
    public FanoutExchange consumerOutExchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding bindingConsumerOutExchange(FanoutExchange exchangeProducer, Queue consumerQueue) {
        return BindingBuilder.bind(consumerQueue)
                .to(exchangeProducer);
    }
}
