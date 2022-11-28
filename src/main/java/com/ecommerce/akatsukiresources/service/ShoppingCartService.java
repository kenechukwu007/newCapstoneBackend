package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.AddItemDto;
import com.ecommerce.akatsukiresources.dto.ItemDto;
import com.ecommerce.akatsukiresources.dto.SCartDto;
import com.ecommerce.akatsukiresources.handler.CustomizedException;
import com.ecommerce.akatsukiresources.model.Appuser;
import com.ecommerce.akatsukiresources.model.Product;
import com.ecommerce.akatsukiresources.model.ShoppingCart;
import com.ecommerce.akatsukiresources.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    public void addItem(AddItemDto addItemDto, Appuser appuser) {
        Product product1 = productService.findById(addItemDto.getProductid());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(appuser);
        shoppingCart.setProduct(product1);
        shoppingCart.setDateCreated(new Date());
        shoppingCart.setVolume(addItemDto.getVolume());
        shoppingCartRepo.save(shoppingCart);

    }

    public SCartDto getAllItems(Appuser user) {
        double price = 0;
      List<ShoppingCart> shoppingCartList =  shoppingCartRepo.findAllByUser(user);
      List<ItemDto> items = new ArrayList<>();
      for(ShoppingCart shoppingCart: shoppingCartList){
          ItemDto itemDto = new ItemDto(shoppingCart);
          price += (itemDto.getVolume() * shoppingCart.getProduct().getPrice());
          items.add(itemDto);
      }

      SCartDto sCartDto = new SCartDto();
      sCartDto.setTotalPrice(price);
      sCartDto.setItemsDtoList(items);
      return sCartDto;


    }

    public void deleteItem(Integer itemId, Appuser appuser) {
        // implement delete logic by retreiving from user
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findById(itemId);
        if(optionalShoppingCart.isPresent()){ // check if cart is empty or not
            throw new CustomizedException("this item id doesn't exist" + itemId);
        }

        ShoppingCart shoppingCart = optionalShoppingCart.get();
        if(shoppingCart.getUser() != appuser){
            throw new CustomizedException("this item doesn't match the specified user: " + itemId);
        }

        shoppingCartRepo.delete(shoppingCart);


    }
}
