package com.alexc.assessment.model;

import lombok.Builder;
import lombok.Getter;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Builder
public class RentalAgreement {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

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
        System.out.println("Tool brand: " + toolBrand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + dateFormatter.format(checkoutDate));
        System.out.println("Due date: " + dateFormatter.format(dueDate));
        System.out.println("Daily rental charge: " + currencyFormatter.format(dailyRentalCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormatter.format(preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: " + currencyFormatter.format(discountAmount));
        System.out.println("Final charge: " + currencyFormatter.format(finalCharge));
    }
}
