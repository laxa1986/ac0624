package com.alexc.assessment.service;

import com.alexc.assessment.model.Term;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public interface CalendarService {
    @NotNull Term findTerm(@NotNull LocalDate fromDate, int rentalDays);
}
