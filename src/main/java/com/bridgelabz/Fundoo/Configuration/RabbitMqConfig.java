package com.bridgelabz.Fundoo.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMqConfig {
@Autowired
private ConnectionFactory rabbitConnection;

@Bean
public DirectExchange routeExchange() {
return new DirectExchange("rmq.rube.exchange",true,false);
}
@Bean
public Queue routeQueue() {
return new Queue("rmq.rube.exchange", true);
}
@Bean
public Binding routeExchangeBinding(DirectExchange routeExchange,Queue routeQueue) {
return BindingBuilder.bind(routeQueue).to(routeExchange).with("rube.key");
}
@Bean
public RabbitTemplate routeExchangeTemplate() {
RabbitTemplate rabbitTemplate=new RabbitTemplate(rabbitConnection);
rabbitTemplate.setConnectionFactory(rabbitConnection);
rabbitTemplate.setExchange("rmq.rube.exchange");
rabbitTemplate.setRoutingKey("rube.key");
return rabbitTemplate;
}
}
