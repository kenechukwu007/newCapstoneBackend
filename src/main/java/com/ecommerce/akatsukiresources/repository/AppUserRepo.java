package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<Appuser, Integer> {
    Appuser findByUsername(String username);
}
