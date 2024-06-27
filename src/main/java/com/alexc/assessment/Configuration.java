package com.alexc.assessment;

import com.alexc.assessment.service.impl.ToolServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackageClasses = ToolServiceImpl.class)
@ImportAutoConfiguration({
        ValidationAutoConfiguration.class,
})
public class Configuration {
}
