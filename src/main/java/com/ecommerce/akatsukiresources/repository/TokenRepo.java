package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
}
