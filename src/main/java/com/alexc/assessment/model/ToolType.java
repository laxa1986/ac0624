package com.alexc.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Normally tool types would be stored in a database, but for this assessment, we will use an enum
 */
@Getter
@AllArgsConstructor
public enum ToolType {
    Ladder(BigDecimal.valueOf(1.99), true, true, false),
    Chainsaw(BigDecimal.valueOf(1.49), true, false, true),
    Jackhammer(BigDecimal.valueOf(2.99), true, false, false);

    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;
}
