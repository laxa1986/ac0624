package com.alexc.assessment.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;

    /**
     * No matter when the tool was taken during the day, it is considered rented for the whole day
     * rentalDays = 1 means that tool needs to be returned the same day
     * Justification: tool needs to be in stock when store opens next day to be rented out for entire day
     * So in case of rentalDays = 1, the dueDate is the same as checkoutDate
     */
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;

    /**
     * I considered use of BigDecimal here, but it makes code less readable
     *  and very unlikely to go out of double precision on single tool rental
     */
    private double finalCharge;

    public void print() {
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Final charge: " + finalCharge);
    }
}
