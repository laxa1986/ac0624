package com.alexc.assessment.service.impl;

import com.alexc.assessment.exception.InvalidInputException;
import com.alexc.assessment.model.RentalAgreement;
import com.alexc.assessment.service.CalendarService;
import com.alexc.assessment.service.RentalFacade;
import com.alexc.assessment.service.ToolService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentalFacadeImpl implements RentalFacade {
    private final ToolService toolService;
    private final CalendarService calendarService;

    @Override
    public RentalAgreement checkout(String toolCode, LocalDate checkoutDate, int rentalDays, int discountPercent) {
        if (rentalDays < 1) {
            throw new InvalidInputException("Rental days must be at least 1");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidInputException("Discount percent must be between 0 and 100");
        }

        val tool = toolService.getTool(toolCode);
        val toolType = tool.getToolType();
        val term = calendarService.findTerm(checkoutDate, rentalDays);

        var chargeDays = 0;
        if (toolType.isWeekdayCharge()) {
            chargeDays += term.getWeekDays();
        }
        if (toolType.isWeekendCharge()) {
            chargeDays += term.getWeekendDays();
        }
        if (toolType.isHolidayCharge()) {
            chargeDays += term.getHolidays();
        }

        double discount = 1 - discountPercent / 100.0;
        double preDiscountCharge = chargeDays * toolType.getDailyCharge();
        double finalCharge = preDiscountCharge * discount;
        double roundedPreDiscountCharge = roundToPenny(preDiscountCharge);
        double roundedFinalCharge = roundToPenny(finalCharge);
        double roundedDiscountAmount = preDiscountCharge - finalCharge;

        return RentalAgreement.builder()
                .toolCode(toolCode)
                .toolType(toolType.name()) // enum name is readable enough, but could introduce another mapping toolType -> readable form
                .toolBrand(tool.getBrand())
                .rentalDays(rentalDays)
                .checkoutDate(checkoutDate)
                .dueDate(checkoutDate.plusDays(rentalDays - 1))
                .dailyRentalCharge(toolType.getDailyCharge())
                .chargeDays(chargeDays)
                .preDiscountCharge(roundedPreDiscountCharge)
                .discountPercent(discountPercent)
                .discountAmount(roundedDiscountAmount)
                .finalCharge(roundedFinalCharge)
                .build();
    }

    private double roundToPenny(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
