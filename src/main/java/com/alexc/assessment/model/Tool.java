package com.alexc.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tool {
    private final String toolCode;
    private final ToolType toolType;
    private final String brand;
}
