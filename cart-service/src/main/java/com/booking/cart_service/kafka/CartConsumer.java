package com.booking.cart_service.kafka;

import com.booking.cart_service.service.CartService;
import com.booking.tour_service.dto.TourEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartConsumer {
    @Autowired
    private CartService cartService;
    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(TourEvent event){
        //log.info(String.format("Order event received in stock service => %s", event.toString()));
        cartService.add2(event.getCart(),event.getTour());
        // save the order event into the database
    }
}
