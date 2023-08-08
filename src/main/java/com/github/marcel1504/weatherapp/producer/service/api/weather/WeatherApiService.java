package com.github.marcel1504.weatherapp.producer.service.api.weather;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDto;

public interface WeatherApiService {

    void postWeatherDataUpdate(WeatherDto weatherDto);
}
