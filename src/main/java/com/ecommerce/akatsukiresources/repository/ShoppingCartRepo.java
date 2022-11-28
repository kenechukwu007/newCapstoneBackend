package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUser(Appuser user);

}
