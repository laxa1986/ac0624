package com.alexc.assessment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Term {
    private int weekDays;
    private int weekendDays;
    private int holidays;
}
