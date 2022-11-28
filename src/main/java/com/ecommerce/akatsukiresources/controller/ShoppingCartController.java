package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.common.ApiResponse;
import com.ecommerce.akatsukiresources.dto.AddItemDto;
import com.ecommerce.akatsukiresources.dto.SCartDto;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.Product;
import com.ecommerce.akatsukiresources.service.ProductService;
import com.ecommerce.akatsukiresources.service.ShoppingCartService;
import com.ecommerce.akatsukiresources.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private VerificationService verificationService;

    /**
     * Controller to add items to the shopping cart
     * @param addItemDto
     * @param token
     * @return
     */
    @PostMapping("/addItem")
    public ResponseEntity<ApiResponse> addItem(@RequestBody AddItemDto addItemDto, @RequestParam("token") String token) {

        // verify the user token
        verificationService.verifyToken(token);
        Appuser appuser = verificationService.getAppUser(token);
        shoppingCartService.addItem(addItemDto,appuser );
        return new ResponseEntity<>(new ApiResponse(true, "Product is in the basket!"), HttpStatus.CREATED);


    }

    @GetMapping("/getItem")
    public ResponseEntity<SCartDto> getItem(@RequestParam("token") String token) {

        verificationService.verifyToken(token);
        Appuser appuser = verificationService.getAppUser(token);
        SCartDto sCartDto = shoppingCartService.getAllItems(appuser);
        return new ResponseEntity<>(sCartDto, HttpStatus.OK);


    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable("itemId") Integer itemId, @RequestParam("token") String token){
        verificationService.verifyToken(token);
        Appuser appuser = verificationService.getAppUser(token);
        shoppingCartService.deleteItem(itemId, appuser);

        return new ResponseEntity<>(new ApiResponse(true, "Product has been deleted from the basket!"), HttpStatus.OK);


    }
}
