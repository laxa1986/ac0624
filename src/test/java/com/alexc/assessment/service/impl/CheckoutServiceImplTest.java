package com.alexc.assessment.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTest {
    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Test
    void shouldCheckout() {
        System.out.println("Checkout service");
    }
}
