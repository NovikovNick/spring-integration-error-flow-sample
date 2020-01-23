package com.metalheart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
@ComponentScan("com.metalheart")
@IntegrationComponentScan("com.metalheart")
public class TestConfiguration {

    @Autowired
    private TestHandler handler;

    @Bean
    public IntegrationFlow happyFlow() {

        return IntegrationFlows
            .from("myChannel")
            .handle(handler)
            .get();
    }

    @Bean
    public IntegrationFlow errorFlow() {

        return IntegrationFlows
            .from("myErrorChannel")
            .handle((payload, headers) -> {
                System.out.println("ERROR!");
                return false;
            })
            .get();
    }
}