package com.booking.users_service.repository;

import com.booking.users_service.dto.UserDto;
import com.booking.users_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.username =?1")
    User findByUsername(String name);
}
