package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.AppDto.AppUserDto;
import com.ecommerce.akatsukiresources.dto.AppDto.GetUserDto;
import com.ecommerce.akatsukiresources.dto.AuthResponseDto;
import com.ecommerce.akatsukiresources.dto.CreateAppUserDto;
import com.ecommerce.akatsukiresources.dto.LoginDto;
import com.ecommerce.akatsukiresources.dto.ResponseDto;
import com.ecommerce.akatsukiresources.enums.Role;
import com.ecommerce.akatsukiresources.handler.CustomizedException;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.VerificationToken;
import com.ecommerce.akatsukiresources.repository.AppUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
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


    public AuthResponseDto register(AppUserDto appUserDto) throws CustomizedException{
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



        Appuser user = new Appuser(appUserDto.getFirstName(), appUserDto.getLastName(), appUserDto.getUsername(), encryptPassword, Role.user);
        Appuser newUser;

        try{
            newUser = appUserRepo.save(user);
            final VerificationToken verificationToken = new VerificationToken(newUser);
            verificationService.storeVerifiedToken(verificationToken);
            AuthResponseDto responseDto = new AuthResponseDto(201, "User created!", verificationToken.getToken() );
            return responseDto;
        } catch(Exception ex){
            throw new CustomizedException(ex.getMessage());
        }
    }

    private String encryptedPassword(String passwd) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(passwd.getBytes());
        byte[] mDigest = messageDigest.digest();
        String encrypt = DatatypeConverter.printHexBinary(mDigest).toUpperCase();
        return encrypt;
    }

    public ResponseDto getUser(GetUserDto getUserDto){
        ResponseDto responseDto = new ResponseDto(200, "user retrieved!");
        return responseDto;
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

    public AuthResponseDto createAppUser(String token, CreateAppUserDto createAppUserDto) throws CustomizedException{
        Appuser createAppUser = verificationService.getAppUser(token);
        if(!canCrudAppUser(createAppUser.getRole())){
            System.out.println("users cna't create new user");
        }
        String encryptPassword = createAppUserDto.getPassword();
        try{
            encryptPassword = encryptedPassword(createAppUserDto.getPassword());
        } catch(Exception e){
            throw new CustomizedException(e.getMessage());        }

        Appuser user = new Appuser(createAppUserDto.getFirstName(), createAppUserDto.getLastName(), createAppUserDto.getUsername(), encryptPassword, Role.user);
        Appuser newUser;

        try{
            newUser = appUserRepo.save(user);
            final VerificationToken verificationToken = new VerificationToken(newUser);
            verificationService.storeVerifiedToken(verificationToken);
            AuthResponseDto responseDto = new AuthResponseDto(201, "User created!", verificationToken.getToken() );
            return responseDto;
        } catch(Exception ex){
            throw new CustomizedException(ex.getMessage());
        }

    }

    boolean canCrudAppUser(Role role) {
        if (role == Role.admin) {
            return true;
        }
        return false;
    }

    boolean canCrudAppUser(Appuser userUpdating, Integer userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // admin and manager can crud any user
        if (role == Role.admin) {
            return true;
        }
        // user can update his own record, but not his role
        if (role == Role.user && userUpdating.getId() == userIdBeingUpdated) {
            return true;
        }
        return false;
    }
}
