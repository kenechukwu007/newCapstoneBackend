package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.model.Category;
import com.ecommerce.akatsukiresources.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }


    public List<Category> listCategory() {

        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory) {
        Category category1 = categoryRepo.getReferenceById(categoryId);
        category1.setCategoryName(updateCategory.getCategoryName());
        category1.setDescription(updateCategory.getDescription());
        category1.setImageUrl(updateCategory.getImageUrl());
        categoryRepo.save(category1);

    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }
}
