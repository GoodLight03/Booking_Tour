package com.booking.cart_service.kafka;

import com.booking.cart_service.dto.CartEvent;
import com.booking.tour_service.dto.TourEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class CartProcducer {
    private NewTopic topic;

    private KafkaTemplate<String, CartEvent> kafkaTemplate;

    public CartProcducer(NewTopic topic, KafkaTemplate<String, CartEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CartEvent event){
        // create Message
        Message<CartEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
