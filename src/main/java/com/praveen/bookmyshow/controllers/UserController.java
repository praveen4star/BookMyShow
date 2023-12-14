package com.praveen.bookmyshow.controllers;

import com.praveen.bookmyshow.dtos.SignUpUserRequestDto;
import com.praveen.bookmyshow.dtos.SignUpUserResponseDto;
import com.praveen.bookmyshow.models.User;
import com.praveen.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto signUpUserRequestDto) {
        SignUpUserResponseDto signUpUserResponseDto = new SignUpUserResponseDto();
        User user = userService.signUpUser(signUpUserRequestDto.getEmail(), signUpUserRequestDto.getPassword());
        signUpUserResponseDto.setUserId(user.getId());
        return signUpUserResponseDto;
    }
}
