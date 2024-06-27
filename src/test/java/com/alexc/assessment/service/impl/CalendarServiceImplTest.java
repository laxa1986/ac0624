package com.alexc.assessment.service.impl;

import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Calendar service is the most complex part of the system, so it deserve best test coverage
 */
@ExtendWith(MockitoExtension.class)
public class CalendarServiceImplTest {
    @InjectMocks
    CalendarServiceImpl calendarService;

    // June 2024
    // S S M T W T F S S M
    // 1 2 3 4 5 6 7 8 9 10
    @Nested
    class NoHolidays {
        @Test
        void shouldReturnOneWeekday() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 3), 1);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(0);
        }

        @Test
        void shouldReturnTwoWeekdays() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 3), 2);
            assertThat(term.getWeekDays()).isEqualTo(2);
            assertThat(term.getWeekendDays()).isEqualTo(0);
        }

        @Test
        void shouldReturnOneWeekendDay() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 1), 1);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(1);
        }

        @Test
        void shouldReturnTwoWeekendDays() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 1), 2);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(2);
        }

        @Test
        void shouldReturnOneWeekdayAndOneWeekendDay() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 7), 2);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(1);
        }

        @Test
        void shouldAccumulateDaysFromDifferentWeekends() {
            val term = calendarService.findTerm(LocalDate.of(2024, 6, 2), 9);
            assertThat(term.getWeekDays()).isEqualTo(6);
            assertThat(term.getWeekendDays()).isEqualTo(3);
        }
    }

    @Nested
    class IndependenceDay {
        // July 2024
        //       *
        // M T W T F S S M
        // 1 2 3 4 5 6 7 8
        @Test
        void shouldObserveIndependenceDayInWeekday() {
            var term = calendarService.findTerm(LocalDate.of(2024, 7, 4), 1);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(1);

            term = calendarService.findTerm(LocalDate.of(2024, 7, 3), 3);
            assertThat(term.getWeekDays()).isEqualTo(2);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(1);
        }

        // July 2020
        //     ! *
        // W T F S S M T W
        // 1 2 3 4 5 6 7 8
        @Test
        void shouldTransferIndependenceDayToFriday() {
            var term = calendarService.findTerm(LocalDate.of(2020, 7, 3), 1);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(1);

            term = calendarService.findTerm(LocalDate.of(2020, 7, 2), 3);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(1);
            assertThat(term.getHolidays()).isEqualTo(1);

            term = calendarService.findTerm(LocalDate.of(2020, 7, 6), 1);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(0);
        }

        // July 2021
        //       * !
        // T F S S M T W T
        // 1 2 3 4 5 6 7 8
        @Test
        void shouldTransferIndependenceDayToMonday() {
            var term = calendarService.findTerm(LocalDate.of(2021, 7, 5), 1);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(1);

            term = calendarService.findTerm(LocalDate.of(2021, 7, 2), 3);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(2);
            assertThat(term.getHolidays()).isEqualTo(0);
        }
    }

    @Nested
    class LaborDay {
        // September 2024
        //   *
        // S M T W T F S S M
        // 1 2 3 4 5 6 7 8 9
        @Test
        void shouldObservesLaborDay() {
            var term = calendarService.findTerm(LocalDate.of(2024, 9, 2), 1);
            assertThat(term.getWeekDays()).isEqualTo(0);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(1);

            term = calendarService.findTerm(LocalDate.of(2024, 9, 1), 3);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(1);
            assertThat(term.getHolidays()).isEqualTo(1);
        }

        @Test
        void shouldNotCelebrateSecondMonday() {
            var term = calendarService.findTerm(LocalDate.of(2024, 9, 9), 1);
            assertThat(term.getWeekDays()).isEqualTo(1);
            assertThat(term.getWeekendDays()).isEqualTo(0);
            assertThat(term.getHolidays()).isEqualTo(0);
        }
    }
}
