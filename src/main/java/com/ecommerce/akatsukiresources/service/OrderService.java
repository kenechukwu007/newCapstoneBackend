package com.ecommerce.akatsukiresources.service;

import com.ecommerce.akatsukiresources.dto.PlaceOrderDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeKey;

    public Session initSession(List<PlaceOrderDto> placeOrderDtoList) throws StripeException {
        String successUrl = baseUrl + "payment/success";
        String failureUrl = baseUrl + "payment/failed";
        Stripe.apiKey = stripeKey;
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for(PlaceOrderDto orderDto: placeOrderDtoList) {
            lineItems.add(initLineItem(orderDto));
        }

        SessionCreateParams createParams = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .setSuccessUrl(successUrl)
                .addAllLineItem(lineItems)
                .build();

        return Session.create(createParams);

    }

    private SessionCreateParams.LineItem initLineItem(PlaceOrderDto orderDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(addPriceData(orderDto))
                .setQuantity(Long.parseLong(String.valueOf(orderDto.getVolume())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData addPriceData(PlaceOrderDto orderDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("cad")
                .setUnitAmount((long)orderDto.getPrice()*100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(orderDto.getItemName())
                                .build()
                ).build();

    }
}
