package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.common.ApiResponse;
import com.ecommerce.akatsukiresources.model.Category;
import com.ecommerce.akatsukiresources.repository.CategoryRepo;
import com.ecommerce.akatsukiresources.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@RequestMapping("/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "create a new category"), HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public List<Category> listCategory(){
        return categoryService.listCategory();

    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        System.out.println("categoryId" + categoryId);
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been created"), HttpStatus.OK);
    }
}
