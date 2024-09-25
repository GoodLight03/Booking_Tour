package com.booking.cart_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_tour;
    private Integer number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id",referencedColumnName = "cart_id")
    private Cart cart;

}
