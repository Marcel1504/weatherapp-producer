package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Value("${weatherdataproducer.consumerService.url}")
    private String url;

    @Value("${weatherdataproducer.consumerService.username}")
    private String username;

    @Value("${weatherdataproducer.consumerService.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri(url)
                .basicAuthentication(username, password)
                .build();
    }
}