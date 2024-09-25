package com.booking.cart_service.service;

import com.booking.cart_service.dto.CartDto;
import com.booking.cart_service.entity.Cart;

import java.util.List;

public interface CartService {
    void add(CartDto cartDto);
    Cart getByUser(Long id);

    void updateNumber(CartDto cartDto);

    void add2(com.booking.tour_service.dto.CartDto cart,com.booking.tour_service.entity.Tour tour);
}
