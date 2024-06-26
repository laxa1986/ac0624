package com.alexc.assessment.service.impl;

import com.alexc.assessment.model.Term;
import com.alexc.assessment.service.CalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Override
    public Term findTerm(LocalDate fromDate, int rentalDays) {
        return null;
    }
}
