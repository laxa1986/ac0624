package com.alexc.assessment;

import com.alexc.assessment.service.RentalFacade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IntegrationTest {
    RentalFacade rentalFacade;

    // Test 1
    @Test
    void shouldThrowExceptionIfDiscountIsOver100Percent() {
        rentalFacade.calculateQuote("JAKR", LocalDate.of(2015, 9, 3), 5, 101);
    }

    // Test 2
    @Test
    void should2() {
        rentalFacade.calculateQuote("LADW", LocalDate.of(2020, 7, 2), 3, 10);
    }

    // Test 3
    @Test
    void should3() {
        rentalFacade.calculateQuote("CHNS", LocalDate.of(2020, 7, 2), 5, 25);
    }

    // Test 4
    @Test
    void should4() {
        rentalFacade.calculateQuote("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
    }

    // Test 5
    @Test
    void should5() {
        rentalFacade.calculateQuote("JAKR", LocalDate.of(2015, 7, 2), 9, 0);
    }

    // Test 6
    @Test
    void should6() {
        rentalFacade.calculateQuote("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
    }
}
