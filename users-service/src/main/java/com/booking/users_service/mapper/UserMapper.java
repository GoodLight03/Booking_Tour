package com.booking.users_service.mapper;

import com.booking.users_service.dto.UserDto;
import com.booking.users_service.entity.User;

public class UserMapper {
    public static User maptoUser(UserDto userDto){
        User user=new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFullname(),
                userDto.getRole()
        );
        return user;
    }

    public static UserDto maptoUserDto(User user){
        UserDto userdto=new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFullname(),
                user.getRole()
        );
        return userdto;
    }
}
