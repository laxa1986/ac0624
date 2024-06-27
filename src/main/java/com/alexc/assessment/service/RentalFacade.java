package com.alexc.assessment.service;

import com.alexc.assessment.model.RentalAgreement;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public interface RentalFacade {
    /**
     * Assumptions:
     * - there is infinite stock of each tool (no need to track inventory)
     * - only one tool rental can be processed at a time
     * - the rental period is always in whole days
     * - there is no validation on number of days (can be quite big number which is not typical for rental)
     * - checkoutDate is the current date, in other words - there is no Clock.now() and comparison that given date is not in the past
     */
    @NotNull
    RentalAgreement checkout(@NotNull String toolCode, @NotNull LocalDate checkoutDate, int rentalDays, int discountPercent);
}
