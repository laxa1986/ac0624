package com.alexc.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Normally tool types would be stored in a database, but for this assessment, we will use an enum
 */
@Getter
@AllArgsConstructor
public enum ToolType {
    Ladder(1.99, true, true, false),
    Chainsaw(1.49, true, false, true),
    Jackhammer(2.99, true, false, false);

    private final double dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;
}
