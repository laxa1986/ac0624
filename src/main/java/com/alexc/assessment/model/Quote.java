package com.alexc.assessment.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Quote {
    private String toolCode;
    private String toolType;
    private BigDecimal finalCharge;
}
