package com.booking.cart_service.repository;

import com.booking.cart_service.entity.Cart;
import com.booking.cart_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query("select u from Item u where u.cart.id =?1 and u.id_tour =?2")
    Item findByCartId(Long idCart,Long idTour);

    @Query("select u from Item u where u.cart.iduser =?1")
    List<Item> findByUserId( Long idUser);
}
