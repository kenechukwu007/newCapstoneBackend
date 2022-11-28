package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.handler.VerificationException;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.VerificationToken;
import com.ecommerce.akatsukiresources.repository.TokenRepo;
import com.ecommerce.akatsukiresources.utilize.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VerificationService {

    @Autowired
    TokenRepo tokenRepo;
    public void storeVerifiedToken(VerificationToken verificationToken) {

        tokenRepo.save(verificationToken);
    }

    public VerificationToken getAuthToken(Appuser user) {
        return tokenRepo.findByUser(user);
    }

    public Appuser getAppUser(String token){
        final VerificationToken veToken = tokenRepo.findByToken(token);
        if(Objects.isNull(token)){
            return null;
        }
        return veToken.getUser();
    }

    public void verifyToken(String token) throws VerificationException {
        if (Objects.isNull(token)) {
            throw new VerificationException("Token doesn't exist!");
        }
        if (Objects.isNull(getAppUser(token))) {
            throw new VerificationException("Invalid token!");
        }
    }


}
