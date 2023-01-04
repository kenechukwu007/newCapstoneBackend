package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.ProductDto;
import com.ecommerce.akatsukiresources.handler.InvalidProductException;
import com.ecommerce.akatsukiresources.model.Category;
import com.ecommerce.akatsukiresources.model.Product;
import com.ecommerce.akatsukiresources.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepo.save(product);

    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;

    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
       Optional<Product> product = productRepo.findById(productId);
       // throw an exception if not existing
       if(!product.isPresent()){
           throw new Exception("This product doesn't exist");
       }
       Product existingProduct = product.get();
       existingProduct.setDescription(productDto.getDescription());
       existingProduct.setImageURL(productDto.getImageURL());
       existingProduct.setName(productDto.getName());
       existingProduct.setPrice(productDto.getPrice());
       productRepo.save(existingProduct);

    }

    public Product findById(Integer productId) {
       Optional<Product> product =  productRepo.findById(productId);
        if(!product.isPresent()){
            throw new InvalidProductException("This doesn't exist => " + productId);
        }
        return product.get();
    }
}
