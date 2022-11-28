package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.dto.AppDto.AppUserDto;
import com.ecommerce.akatsukiresources.dto.AuthResponseDto;
import com.ecommerce.akatsukiresources.dto.LoginDto;
import com.ecommerce.akatsukiresources.dto.LoginDtoReceipt;
import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;


    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody AppUserDto appUserDto) {
        return appUserService.register(appUserDto);
    }

    @PostMapping("/login")
    public AuthResponseDto logIn(@RequestBody LoginDto loginDto) {
        return appUserService.logIn(loginDto);
    }

}
