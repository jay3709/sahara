package com.chegg.sahara.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ApplicationConfiguration {

    @Value("${api.token}")
    private String apiToken;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(apiToken, Duration.ofSeconds(60L));
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
