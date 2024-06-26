package com.alexc.assessment.service.impl;

import com.alexc.assessment.exception.UnknownToolException;
import com.alexc.assessment.model.Tool;
import com.alexc.assessment.model.ToolType;
import com.alexc.assessment.service.ToolService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ToolServiceImpl implements ToolService {
    /**
     * This typically would be in a DB, keeping in memory for simplicity
     */
    private final Map<String, Tool> inventory = new HashMap<>();

    public ToolServiceImpl() {
        inventory.put("CHNS", new Tool("CHNS", ToolType.Chainsaw, "Stihl"));
        inventory.put("LADW", new Tool("LADW", ToolType.Ladder, "Werner"));
        inventory.put("JAKD", new Tool("JAKD", ToolType.Jackhammer, "DeWalt"));
        inventory.put("JAKR", new Tool("JAKR", ToolType.Jackhammer, "Ridgid"));
    }

    @Override
    public Tool getTool(String toolCode) {
        val tool = inventory.get(toolCode);
        if (tool == null) {
            throw new UnknownToolException("Unknown tool: " + toolCode);
        }
        return tool;
    }
}
