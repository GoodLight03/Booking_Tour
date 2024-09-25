package com.booking.cart_service.dto;

import com.booking.cart_service.entity.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long idtour;
    private String name;
    private Long price;
    private Long iduser;
    private Integer number;
}
