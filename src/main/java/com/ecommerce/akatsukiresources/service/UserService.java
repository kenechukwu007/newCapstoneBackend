package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.dto.userDto.SignUpDto;
import com.ecommerce.akatsukiresources.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseDto signUp(SignUpDto signUpDto) {
        ResponseDto responseDto = new ResponseDto("success", "dummy respoinse");
        return responseDto;
    }
}
