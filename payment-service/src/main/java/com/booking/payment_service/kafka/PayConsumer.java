package com.booking.payment_service.kafka;

import com.booking.cart_service.dto.CartEvent;
import com.booking.payment_service.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PayConsumer {
//    @Autowired
//    private CartService cartService;

    @Autowired
    private RestTemplate restTemplate;  // Inject RestTemplate

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(CartEvent event) {
        log.info(String.format("Order event received in stock service => %s", event.toString()));
        //cartService.add2(event.getCart(),event.getTour());
        // save the order event into the database

        // Thực hiện thanh toán
        Order order = new Order();  // Khởi tạo một Order dựa trên event nếu cần
        order.setTotal(event.getTotal());
        order.setCurrency("USD");  // Hoặc lấy từ sự kiện nếu có
        order.setMethod("paypal"); // Giả định dùng Paypal, bạn có thể tùy chỉnh
        order.setIntent("sale");
        order.setDescription("Payment for order ID: " + event.getIdUser());

        processPayment(order);  // Gọi hàm xử lý thanh toán
    }

    private String processPayment(Order order) {

        String paymentApiUrl = "http://localhost:8084/api/payment/pay";  // Đường dẫn tới API thanh toán

        try {
            // Gửi yêu cầu POST đến API thanh toán
            ResponseEntity<String> response = restTemplate.postForEntity(paymentApiUrl, order, String.class);

            // Kiểm tra phản hồi từ API
            if (response.getStatusCode() == HttpStatus.OK) {
                // Trả về link thanh toán từ phản hồi
                return response.getBody();
                // Giả định link thanh toán được trả về trong body
            } else {
                log.error("Failed to create payment, status code: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            log.error("Error occurred while calling payment API: " + e.getMessage());
        }

        return null;  // Nếu không lấy được link thanh toán

    }
}
