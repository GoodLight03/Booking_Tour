package com.booking.payment_service.controller;

import com.booking.cart_service.dto.CartEvent;
import com.booking.payment_service.dto.Order;
import com.booking.payment_service.kafka.PayProcducer;
import com.booking.payment_service.service.PaypalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.api.payments.Links;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaypalController {
    @Autowired
    PaypalService service;

    @Autowired
    PayProcducer payProcducer;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/pay")
    public String payment(@RequestBody CartEvent event, HttpServletRequest request) {

        Order order = new Order();
        order.setTotal(event.getTotal());
        order.setCurrency("USD");
        order.setMethod("paypal");
        order.setIntent("sale");
        order.setDescription("Payment for order ID: " + event.getIdUser());

        try {
            Payment payment = service.createPayment(order.getTotal()/23000, order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8084/api/payment/" + CANCEL_URL,
                    "http://localhost:8084/api/payment/" + SUCCESS_URL,event);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    log.info(link.getHref());
                    // Thay vì return "redirect:"+link.getHref();
                    return "forward:" + link.getHref();

                    //return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }



    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<String> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpServletRequest request, @RequestParam("orders") String ordersJson) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            String decodedOrders = URLDecoder.decode(ordersJson, StandardCharsets.UTF_8.toString());
            CartEvent orders = objectMapper.readValue(decodedOrders, new TypeReference<CartEvent>() {});

            log.info("Orders: " + orders.toString());

            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                payProcducer.sendMessage(orders);
                return new ResponseEntity<>("success",HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("no ok",HttpStatus.OK);
    }

}
