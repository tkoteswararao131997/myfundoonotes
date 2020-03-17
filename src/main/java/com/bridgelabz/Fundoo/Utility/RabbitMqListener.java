package com.bridgelabz.Fundoo.Utility;

import com.google.gson.Gson;

//@Component
public class RabbitMqListener {

    //@RabbitListener(queues="${rabbitmq.queueName}")
    public void listen(byte[] message) {
        String msg = new String(message);
        Notification not = new Gson().fromJson(msg, Notification.class);
        System.out.println("Received a new notification...");
        System.out.println(not.toString());
    }
}
