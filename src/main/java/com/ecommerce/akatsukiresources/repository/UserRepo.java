package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<user, Integer> {
}
