package com.alexc.assessment.service.impl;

import com.alexc.assessment.model.Term;
import com.alexc.assessment.service.CalendarService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * There are only two (2) holidays in the calendar:
 * - Independence Day, July 4th - If falls on weekend, it is observed on the closest weekday (if Sat, then Friday before, if Sunday, then Monday after)
 * - Labor Day - First Monday in September
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    @Override
    public Term findTerm(LocalDate fromDate, int rentalDays) {
        int weekDays = 0;
        int weekendDays = 0;
        int holidays = 0;

        LocalDate date = fromDate.minusDays(1);
        for (int i = 0; i < rentalDays; i++) {
            date = date.plusDays(1);

            // first consider special cases
            boolean holiday = false;
            if (date.getMonth() == Month.JULY) {
                if (
                           date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY
                        || date.getDayOfMonth() == 4 && date.getDayOfWeek().getValue() < 6
                        || date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY
                ) {
                    holiday = true;
                }
            } else if (date.getMonth() == Month.SEPTEMBER) {
                if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 8) {
                    holiday = true;
                }
            }

            if (holiday) {
                holidays++;
            } else if (date.getDayOfWeek().getValue() < 6) {
                weekDays++;
            } else {
                weekendDays++;
            }
        }
        return Term.builder()
                .weekDays(weekDays)
                .weekendDays(weekendDays)
                .holidays(holidays)
                .build();
    }
}
