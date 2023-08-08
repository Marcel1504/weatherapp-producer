package com.github.marcel1504.weatherapp.producer.service.rest;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface RestService {
    List<RestTemplate> getAllRestTemplates();

    void postAll(String url, Object body);
}
