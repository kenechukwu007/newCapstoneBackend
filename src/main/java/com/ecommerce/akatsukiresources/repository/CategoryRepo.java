package com.ecommerce.akatsukiresources.repository;

import com.ecommerce.akatsukiresources.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer > {

    Category findByCategoryName(String categoryName);
}
