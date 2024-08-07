package com.example.repobrowser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {
    private final String baseUrl;

    public ApplicationConfiguration(@Value("${github-api.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
