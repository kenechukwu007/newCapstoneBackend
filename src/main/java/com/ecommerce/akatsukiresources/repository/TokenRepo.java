package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<VerificationToken, Integer> {
   VerificationToken findByAppuser(Appuser appuser);
   VerificationToken findByToken(String token);
}
