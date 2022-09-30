package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.common.ApiResponse;
import com.ecommerce.akatsukiresources.dto.ProductDto;
import com.ecommerce.akatsukiresources.model.Category;
import com.ecommerce.akatsukiresources.model.Product;
import com.ecommerce.akatsukiresources.repository.CategoryRepo;
import com.ecommerce.akatsukiresources.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){

       Optional<Category>optionalCategory =  categoryRepo.findById(productDto.getCategoryId());
       if (!optionalCategory.isPresent()){
           return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
       }
       productService.createProduct(productDto, optionalCategory.get());
       return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProduct(){
        List<ProductDto> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);

    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {

        Optional<Category>optionalCategory =  categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }


}
