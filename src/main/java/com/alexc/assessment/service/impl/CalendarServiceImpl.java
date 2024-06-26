package com.alexc.assessment.service.impl;

import com.alexc.assessment.model.Term;
import com.alexc.assessment.service.CalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * There are only two (2) holidays in the calendar:
 * - Independence Day, July 4th - If falls on weekend, it is observed on the closest weekday (if Sat, then Friday before, if Sunday, then Monday after)
 * - Labor Day - First Monday in September
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    @Override
    public Term findTerm(LocalDate fromDate, int rentalDays) {
        return null;
    }
}
