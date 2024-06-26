package com.alexc.assessment;

import com.alexc.assessment.service.CheckoutService;
import lombok.val;
import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        val context = SpringApplication.run(new Class[]{ Configuration.class }, args);
        val checkoutService = context.getBean(CheckoutService.class);
        checkoutService.checkout();
    }
}
