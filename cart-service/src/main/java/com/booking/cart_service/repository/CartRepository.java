package com.booking.cart_service.repository;

import com.booking.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("select u from Cart u where u.iduser =?1")
    Cart findByUserId(Long id);
}
