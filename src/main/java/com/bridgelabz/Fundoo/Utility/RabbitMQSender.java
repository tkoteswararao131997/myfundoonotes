//package com.bridgelabz.Fundoo.Utility;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
////@Component
//public class RabbitMQSender {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    RabbitMQProperties rabbitMQProperties;
//
//    public void sendMessage(Notification msg){
//        System.out.println("Send msg = " + msg.toString());
//        rabbitTemplate.convertAndSend("","", msg);
//    }
//}