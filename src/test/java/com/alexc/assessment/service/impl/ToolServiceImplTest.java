package com.alexc.assessment.service.impl;

import com.alexc.assessment.exception.UnknownToolException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class ToolServiceImplTest {
    @InjectMocks
    ToolServiceImpl toolService;

    @Test
    void shouldThrowExceptionIfUnknownTool() {
        assertThrows(UnknownToolException.class, () -> toolService.getTool("ABC"));
    }

    @Test
    void shouldReturnKnownTool() {
        val tool = toolService.getTool("LADW");
        assertThat(tool).isNotNull();
    }
}
