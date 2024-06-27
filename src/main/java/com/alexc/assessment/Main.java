package com.alexc.assessment;

import com.alexc.assessment.service.RentalFacade;
import lombok.val;
import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        val context = SpringApplication.run(new Class[]{ Configuration.class }, args);
        val rentalFacade = context.getBean(RentalFacade.class);
    }
}
