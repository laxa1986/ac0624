package com.alexc.assessment.service.impl;

import com.alexc.assessment.service.CheckoutService;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Override
    public void checkout() {
        System.out.println("Checkout service");
    }
}
