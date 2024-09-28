package com.booking.booking_service.kafka;

import com.booking.cart_service.dto.CartEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingCusumer {
    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(CartEvent event) {
        log.info(String.format("Order event received in stock service => %s", event.toString()));
        //cartService.add2(event.getCart(),event.getTour());
        // save the order event into the database

        // Thực hiện thanh toán

    }
}
