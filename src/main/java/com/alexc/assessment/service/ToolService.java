package com.alexc.assessment.service;

import com.alexc.assessment.model.Tool;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ToolService {
    @NotNull Tool getTool(@NotNull String toolCode);
}
