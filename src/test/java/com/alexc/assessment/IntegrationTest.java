package com.alexc.assessment;

import com.alexc.assessment.exception.InvalidInputException;
import com.alexc.assessment.service.RentalFacade;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Here I use real application context, thus IntegrationTest name,
 *  sometimes called FullStackTest leaving IntegrationTest term for test that run against live application running in Docker
 * Here "*" denote a holiday, "!" denotes a weekday when the holiday is observed and "()" denote the rental period
 */
@SpringBootTest(classes = Configuration.class)
public class IntegrationTest {
    @Autowired
    RentalFacade rentalFacade;

    // Test 1
    @Test
    void shouldThrowExceptionIfDiscountIsOver100Percent() {
        val ex = assertThrows(InvalidInputException.class, () ->
            rentalFacade.checkout("JAKR", LocalDate.of(2015, 9, 3), 5, 101)
        );
        assertThat(ex.getMessage()).isEqualTo("Discount percent must be between 0 and 100");
    }

    // Test 2
    // July 2020
    //     ! *
    // W T F S S M T W T F
    // 1 2 3 4 5 6 7 8 9 10
    //   (   )
    @Test
    void shouldConsiderAllTypesOfDaysAndDiscount() {
        // LADW is a Ladder (holidays free) so only 2 days are charged * 1.99 rate - 10% discount => 3.58
        val rentalAgreement = rentalFacade.checkout("LADW", LocalDate.of(2020, 7, 2), 3, 10);
        assertThat(rentalAgreement.getFinalCharge()).isCloseTo(3.58, within(0.01));
    }

    // Test 3
    // July 2020
    //     ! *
    // W T F S S M T W T F
    // 1 2 3 4 5 6 7 8 9 10
    //   (       )
    @Test
    void shouldConsiderChainsawDiscountForWeekend() {
        // CHNS is a Chainsaw (weekend free) so only 3 days are charged * 1.49 rate - 25% discount => 3.3525
        val rentalAgreement = rentalFacade.checkout("CHNS", LocalDate.of(2020, 7, 2), 5, 25);
        assertThat(rentalAgreement.getFinalCharge()).isCloseTo(3.35, within(0.001));
    }

    // Test 4
    // Sep 2015
    //             *
    // T W T F S S M T W T
    // 1 2 3 4 5 6 7 8 9 10
    //     (         )
    @Test
    void shouldChargeOnlyWorkdaysForJackhammerAroundLaborDay() {
        // JAKD is a Jackhammer (only workdays are charged) so 3 days are charged * 2.99 rate => 8.97
        val rentalAgreement = rentalFacade.checkout("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
        assertThat(rentalAgreement.getFinalCharge()).isCloseTo(8.97, within(0.01));
    }

    // Test 5
    // July 2015
    //     ! *
    // W T F S S M T W T F
    // 1 2 3 4 5 6 7 8 9 10
    //   (                )
    @Test
    void shouldChargeOnlyWorkdaysForJackhammerCloseToJuly4In2015() {
        // JAKR is a Jackhammer (only workdays are charged) so 6 days are charged * 2.99 rate => 17.94
        val rentalAgreement = rentalFacade.checkout("JAKR", LocalDate.of(2015, 7, 2), 9, 0);
        assertThat(rentalAgreement.getFinalCharge()).isCloseTo(17.94, within(0.01));
    }

    // Test 6
    // July 2020
    //     ! *
    // W T F S S M T W T F
    // 1 2 3 4 5 6 7 8 9 10
    //   (     )
    @Test
    void shouldChargeOnlyWorkdaysForJackhammerCloseToJuly4In2020() {
        // JAKR is a Jackhammer (only workdays are charged) so 1 day is charged * 2.99 rate - 50% discount => 1.495
        val rentalAgreement = rentalFacade.checkout("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
        assertThat(rentalAgreement.getFinalCharge()).isCloseTo(1.50, within(0.001));
        rentalAgreement.print();
    }
}
