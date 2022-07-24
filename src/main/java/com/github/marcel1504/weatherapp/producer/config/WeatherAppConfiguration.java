package com.github.marcel1504.weatherapp.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "weatherapp")
@Data
public class WeatherAppConfiguration {
    private List<ConsumerService> consumerServices;

    @Data
    public static class ConsumerService {
        private String url;
        private String username;
        private String password;
    }
}
