package com.booking.cart_service.dto;

import com.booking.cart_service.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartEvent {
    private String message;
    private String status;
    private Long idUser;
    private Long total;
    private List<Item> items;
}
