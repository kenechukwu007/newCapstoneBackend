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
    public void storeVerifiedtoken(VerificationToken verificationToken) {

        tokenRepo.save(verificationToken);
    }

    public VerificationToken getAuthToken(Appuser appuser) {
        return tokenRepo.findByAppuser(appuser);
    }

    public Appuser getAppuser(String token){
        final VerificationToken veToken = tokenRepo.findByToken(token);
        if(Objects.isNull(token)){
            return null;
        }
        return veToken.getUser1();
    }

    public void verifyToken(String token) throws VerificationException {
        if (Objects.isNull(token)) {
            throw new VerificationException("Token doesn't exist!");
        }
        if (Objects.isNull(getAppuser(token))) {
            throw new VerificationException("Invalid token!");
        }
    }


}
