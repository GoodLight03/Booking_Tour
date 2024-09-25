package com.booking.cart_service.service.Impl;

import com.booking.cart_service.dto.CartDto;
import com.booking.cart_service.entity.Cart;
import com.booking.cart_service.entity.Item;
import com.booking.cart_service.repository.CartRepository;
import com.booking.cart_service.repository.ItemRepository;
import com.booking.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void add(CartDto cartDto) {
        Cart cartF = cartRepository.findByUserId(cartDto.getIduser());
        if (cartF == null) {
            cartF = new Cart();
            cartF.setIduser(cartDto.getIduser());
            cartF.setItems(new HashSet<>());
            cartRepository.save(cartF);
        }
        Cart cart = cartRepository.findByUserId(cartDto.getIduser());

        Item item = itemRepository.findByCartId(cart.getId(), cartDto.getIdtour());
        if (item == null) {
            Item item1 = new Item();
            item1.setId_tour(cartDto.getIdtour());
            item1.setCart(cart);
            item1.setNumber(1);
            item1.setName(cartDto.getName());
            item1.setPrice(cartDto.getPrice());
            itemRepository.save(item1);
        } else {
            item.setNumber(item.getNumber() + 1);
            itemRepository.save(item);
        }

    }

    @Override
    public Cart getByUser(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Override
    public void updateNumber(CartDto cartDto) {
        Cart cart = cartRepository.findByUserId(cartDto.getIduser());
        Item item = itemRepository.findByCartId(cart.getId(), cartDto.getIdtour());
        item.setNumber(cartDto.getNumber());
        itemRepository.save(item);
    }

    @Override
    public void add2(com.booking.tour_service.dto.CartDto cart,com.booking.tour_service.entity.Tour tour) {
        com.booking.cart_service.dto.CartDto cartDto=new CartDto(
                cart.getId(), cart.getIdtour(), tour.getName(), tour.getPrice(), cart.getIduser(), cart.getNumber()
        );

        add(cartDto);
    }

}
