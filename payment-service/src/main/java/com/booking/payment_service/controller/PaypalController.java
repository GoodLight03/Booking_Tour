package com.booking.payment_service.controller;

import com.booking.payment_service.dto.Order;
import com.booking.payment_service.service.PaypalService;
import com.paypal.api.payments.Links;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaypalController {
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/pay")
    public String payment(@RequestBody Order order, HttpServletRequest request) {
        request.getSession().setAttribute("order",order);
        log.info("Order: "+order.toString());
        log.info("Session: "+request.getSession().getAttribute("order").toString());
        try {
            Payment payment = service.createPayment(order.getTotal()/23000, order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8084/api/payment/" + CANCEL_URL,
                    "http://localhost:8084/api/payment/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    log.info(link.getHref());
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/payment")
    public ResponseEntity<String> paymentapi(@RequestBody Order order) {
        log.info(order.toString());
        try {
            // Tạo thanh toán thông qua service
            Payment payment = service.createPayment(
                    order.getTotal(),
                    order.getCurrency(),
                    order.getMethod(),
                    order.getIntent(),
                    order.getDescription(),
                    "http://localhost:8084/" + CANCEL_URL,
                    "http://localhost:8084/" + SUCCESS_URL
            );

            // Lấy link approval URL từ PayPal response
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    log.info(link.getRel());
//                    return ResponseEntity.status(HttpStatus.FOUND)  // Trả về mã trạng thái 302
//                            .header(HttpHeaders.LOCATION, link.getHref())  // Thêm header Location với URL cần redirect
//                            .build();
                    return ResponseEntity.ok(link.getHref());  // Trả về URL thanh toán
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred during payment creation");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Approval URL not found");
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<String> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request) {
        try {
           // Order order = (Order) request.getSession().getAttribute("order");
           // log.info(order.toString());
            log.info(request.getSession().getAttribute("order").toString());
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return new ResponseEntity<>("success",HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("no ok",HttpStatus.OK);
    }
}
