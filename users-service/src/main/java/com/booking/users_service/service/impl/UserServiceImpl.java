package com.booking.users_service.service.impl;

import com.booking.users_service.dto.UserDto;
import com.booking.users_service.entity.User;
import com.booking.users_service.mapper.UserMapper;
import com.booking.users_service.repository.UserRepository;
import com.booking.users_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(UserDto userDto) {
        return userRepository.save(UserMapper.maptoUser(userDto));
    }

    @Override
    public UserDto getUserbyName(String name) {
        User user=userRepository.findByUsername(name);
        if(user.getId()==null){
            return null;
        }
        return UserMapper.maptoUserDto(user);
    }
}
