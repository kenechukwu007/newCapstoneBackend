package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUser(Appuser user);

}
