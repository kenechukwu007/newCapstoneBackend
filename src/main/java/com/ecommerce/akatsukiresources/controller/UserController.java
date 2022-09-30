package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.dto.userDto.SignUpDto;
import com.ecommerce.akatsukiresources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }
}
