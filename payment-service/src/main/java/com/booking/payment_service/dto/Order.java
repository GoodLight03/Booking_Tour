package com.booking.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private double total;
    private String currency;
    private String method;
    private String intent;
    private String description;
}
