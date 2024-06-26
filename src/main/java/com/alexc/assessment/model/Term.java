package com.alexc.assessment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Term {
    // week days M-F excluding holidays
    private int weekDays;

    // weekend days S-S excluding holidays (though in current system a holiday is effectively always on weekday)
    private int weekendDays;

    private int holidays;
}
