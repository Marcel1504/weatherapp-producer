package com.github.marcel1504.weatherapp.producer.service.api.weather;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDto;
import com.github.marcel1504.weatherapp.producer.service.rest.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Autowired
    private RestService restService;

    @Override
    public void postWeatherDataUpdate(WeatherDto weatherDto) {
        restService.postAll("/data/wep", weatherDto);
    }
}
