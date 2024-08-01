package com.example.repobrowser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {
    private final String baseUrl;

    public ApplicationConfiguration(@Value("${github-api.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
