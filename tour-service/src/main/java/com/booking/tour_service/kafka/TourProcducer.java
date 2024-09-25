package com.booking.tour_service.kafka;

import com.booking.tour_service.dto.TourEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class TourProcducer {
    private NewTopic topic;

    private KafkaTemplate<String, TourEvent> kafkaTemplate;

    public TourProcducer(NewTopic topic, KafkaTemplate<String, TourEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TourEvent event){
        // create Message
        Message<TourEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
