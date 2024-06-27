package com.alexc.assessment;

import com.alexc.assessment.service.RentalFacade;
import lombok.val;
import org.springframework.boot.SpringApplication;

import java.time.LocalDate;

public class Main {
    /**
     * Assessment condition do not speak about how this software will be used: web server, mobile app, console app etc.
     * This is example how it can be used in console app
     */
    public static void main(String[] args) {
        val context = SpringApplication.run(new Class[]{ Configuration.class }, args);
        val rentalFacade = context.getBean(RentalFacade.class);

        // parse args to get tool code, checkout date, rental days and discount
        val toolCode = "LADW";
        val checkoutDate = LocalDate.now();
        val rentalDays = 3;
        val discountPercent = 0;
        val rentalAgreement = rentalFacade.checkout(toolCode, checkoutDate, rentalDays, discountPercent);
        rentalAgreement.print();
    }
}
