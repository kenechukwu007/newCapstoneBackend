package com.ecommerce.akatsukiresources.controller;

import com.ecommerce.akatsukiresources.dto.PlaceOrderDto;
import com.ecommerce.akatsukiresources.dto.StripeResponse;
import com.ecommerce.akatsukiresources.service.OrderService;
import com.ecommerce.akatsukiresources.service.VerificationService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout-session")
    public ResponseEntity<StripeResponse> cartCheckout(@RequestBody List<PlaceOrderDto> placeOrderDtoList) throws StripeException {
        Session session = orderService.initSession(placeOrderDtoList);
        StripeResponse response = new StripeResponse(session.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
