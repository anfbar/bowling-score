package jobsity.bowling.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan("jobsity.bowling.service.impl, jobsity.bowling.controller.impl")
public class TestConfig {
}
