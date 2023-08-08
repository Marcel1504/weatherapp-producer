package com.github.marcel1504.weatherapp.producer.service.weather.mapping;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDto;
import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;

public interface WeatherMappingService {

    WeatherDto mapFromWeatherEntityToWeatherDTO(WeatherEntity entity);
}
