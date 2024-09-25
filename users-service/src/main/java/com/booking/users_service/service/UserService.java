package com.booking.users_service.service;

import com.booking.users_service.dto.UserDto;
import com.booking.users_service.entity.User;

public interface UserService {
    User add(UserDto userDto);

    UserDto getUserbyName(String name);
}
