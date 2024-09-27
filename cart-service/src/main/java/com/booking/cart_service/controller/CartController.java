package com.booking.cart_service.controller;

import com.booking.cart_service.dto.CartDto;
import com.booking.cart_service.dto.PayDto;
import com.booking.cart_service.entity.Cart;
import com.booking.cart_service.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;

//    @PostMapping("/save")
//    ResponseEntity<?> save(@RequestBody CartDto cartDto){
//        cartService.add(cartDto);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/getbyuser/{id}")
    ResponseEntity<Cart> get(@PathVariable Long id){
        Cart cart = cartService.getByUser(id);
        try {
            return new ResponseEntity<>(
                    cart,
                    HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity("Cart Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@RequestBody CartDto cartDto){
        cartService.updateNumber(cartDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pay/{id}")
    ResponseEntity<?> pay(@PathVariable Long id){
        cartService.pay(id);
        return ResponseEntity.ok().build();
    }

}
