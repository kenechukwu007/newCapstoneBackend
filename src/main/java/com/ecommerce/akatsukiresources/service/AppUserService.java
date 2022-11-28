package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.AppDto.AppUserDto;
import com.ecommerce.akatsukiresources.dto.AuthResponseDto;
import com.ecommerce.akatsukiresources.dto.LoginDto;
import com.ecommerce.akatsukiresources.dto.LoginDtoReceipt;
import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.handler.CustomizedException;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.VerificationToken;
import com.ecommerce.akatsukiresources.repository.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class AppUserService  {

    @Autowired
     AppUserRepo appUserRepo;

    @Autowired
     VerificationService verificationService;


    public AuthResponseDto register(AppUserDto appUserDto){
        // verify if user exist
       if (Objects.nonNull(appUserRepo.findByUsername(appUserDto.getUsername())))  {

           throw new CustomizedException("This user already exist");
       }

       // encrypt password
        String encryptPassword = appUserDto.getPassword();
       try{
           encryptPassword = encryptedPassword(appUserDto.getPassword());

       } catch (NoSuchAlgorithmException ex) {
           ex.getMessage();
       }

       // then save to the app user
       Appuser appuser = new Appuser();
       appuser.setUsername(appUserDto.getUsername());
       appuser.setFirstname(appUserDto.getFirstName());
       appuser.setLastname(appUserDto.getLastName());
       appuser.setPassword(encryptPassword);

       appUserRepo.save(appuser);

       final VerificationToken verificationToken = new VerificationToken(appuser);
       verificationService.storeVerifiedToken(verificationToken);

        AuthResponseDto responseDto = new AuthResponseDto(201, "User created!", verificationToken.getToken() );

        return responseDto;
    }

    private String encryptedPassword(String passwd) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(passwd.getBytes());
        byte[] mDigest = messageDigest.digest();
        String encrypt = DatatypeConverter.printHexBinary(mDigest).toUpperCase();
        return encrypt;
    }


    public AuthResponseDto logIn(LoginDto loginDto) {
        Appuser appuser = appUserRepo.findByUsername(loginDto.getUsername());
        // check if a user with the userbame exist
        if(Objects.isNull(appuser)){
            System.out.println("this is user doesn't exist");
        }

        // checks if the password match the one in the database
        try{
            if(!appuser.getPassword().equals(encryptedPassword(loginDto.getPassword()))){
                System.out.println("Wrong password, please try again!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        VerificationToken verificationToken = verificationService.getAuthToken(appuser);
        if(Objects.isNull(verificationToken)){
            System.out.println(" token doesn't exist");
        } else {
            System.out.println("Great!");
        }

        return new AuthResponseDto(200, "success", verificationToken.getToken());


    }
}
