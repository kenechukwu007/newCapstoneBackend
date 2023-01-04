package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.dto.AppDto.AppUserDto;
import com.ecommerce.akatsukiresources.dto.AppDto.GetUserDto;
import com.ecommerce.akatsukiresources.dto.AuthResponseDto;
import com.ecommerce.akatsukiresources.dto.LoginDto;
import com.ecommerce.akatsukiresources.dto.LoginDtoReceipt;
import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.repository.AppUserRepo;
import com.ecommerce.akatsukiresources.service.AppUserService;
import com.ecommerce.akatsukiresources.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    VerificationService verificationService;

    @Autowired
    AppUserRepo appUserRepo;


    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody AppUserDto appUserDto) {
        return appUserService.register(appUserDto);
    }

    @PostMapping("/login")
    public AuthResponseDto logIn(@RequestBody LoginDto loginDto) {
        return appUserService.logIn(loginDto);
    }

    @GetMapping("")
    public Appuser getUser(@RequestHeader("authorization") String token){
        verificationService.verifyToken(token);
       return verificationService.getAppUser(token);

    }

}
