package com.alexc.assessment.service.impl;

import com.alexc.assessment.model.Quote;
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
    public Quote calculateQuote(String toolCode, LocalDate checkoutDate, int rentalDays, int discountPercent) {
        val tool = toolService.getTool(toolCode);
        calendarService.toString();
        return null;
    }
}
