package com.booking.users_service.controller;

import com.booking.users_service.dto.ApiResponse;
import com.booking.users_service.dto.UserDto;
import com.booking.users_service.entity.User;
import com.booking.users_service.service.UserService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    ResponseEntity<User> save(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.add(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{name}")
    ResponseEntity<UserDto> get(@PathVariable String name){
        UserDto user = userService.getUserbyName(name);
        try {
            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
