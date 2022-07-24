package com.github.marcel1504.weatherapp.producer.service.rest;

import com.github.marcel1504.weatherapp.producer.config.WeatherAppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestServiceImpl implements RestService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private WeatherAppConfiguration weatherAppConfiguration;

    @Override
    public List<RestTemplate> getAllRestTemplates() {
        return weatherAppConfiguration
                .getConsumerServices()
                .stream()
                .map(service -> restTemplateBuilder
                        .rootUri(service.getUrl())
                        .basicAuthentication(service.getUsername(), service.getPassword())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void putAll(String url, Object body) {
        this.getAllRestTemplates().forEach(t -> {
            try {
                t.put(url, body);
            } catch (RestClientException e) {
                log.warn("Could not send put request in template: {}", e.getMessage());
            }
        });
    }
}
