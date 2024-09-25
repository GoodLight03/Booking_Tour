package com.booking.cart_service.mapper;


import com.booking.cart_service.dto.CartDto;
import com.booking.cart_service.entity.Cart;

public class CartMapper {
    public static Cart maptoCart(CartDto CartDto){
        Cart Cart = new Cart(
            //    ...
        );
        return Cart;
    }

    public static CartDto maptoCartDto(Cart Cart){
        CartDto CartDto = new CartDto(
               // ...
        );
        return CartDto;
    }
}
